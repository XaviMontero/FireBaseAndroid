package com.example.proyectofinal.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.R;
import com.example.proyectofinal.model.User;
import com.example.proyectofinal.util.Util;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.concurrent.Executor;

import io.realm.Realm;
import io.realm.RealmResults;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView registrar;
    private User userReaml;
    private Realm realm =Realm.getDefaultInstance();
    private Button biometricLoginButton;
    private Handler handler = new Handler();
    private Executor executor = new Executor() {
        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    };
    private SharedPreferences prefs;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Switch switchRemember;
    private Button btnLogin;
    private ImageView ivFacebook;
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        reference();
        call();
        builCredenciales();
        google();

    }

    private void google() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    private void builCredenciales() {
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        setCredentialsIfExist();

        btnLogin.setOnClickListener(this);
        ivFacebook.setOnClickListener(this);
        registrar.setOnClickListener(this);
    }

    private void call() {
        userReaml= new User();
        biometricLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBiometricPrompt();
            }
        });
    }

    private void reference() {

        // Prompt appears when user clicks "Log in"

          biometricLoginButton = findViewById(R.id.login_bio);
          //Share Preference
        editTextEmail = findViewById(R.id.etUsername);
        editTextPassword = findViewById(R.id.etPassword);
        switchRemember =  findViewById(R.id.recuerdame);
        btnLogin =  findViewById(R.id.login);
        ivFacebook = findViewById(R.id.ivFacebook);
        mAuth = FirebaseAuth.getInstance();
        registrar= findViewById(R.id.tvSignUp);

    }


    //Metodo Biometrico

    private void showBiometricPrompt() {
        BiometricPrompt.PromptInfo promptInfo =
                new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("Biometric login for my app")
                        .setSubtitle("Log in using your biometric credential")
                        .setNegativeButtonText("Cancel")
                        .build();

        BiometricPrompt biometricPrompt = new BiometricPrompt(LoginActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                        "Authentication error: " + errString, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                BiometricPrompt.CryptoObject authenticatedCryptoObject =
                        result.getCryptoObject();


                if (login("admin@admin.ec", "admin")) {
                    saveOnPreferences("admin@admin.ec", "admin");
                    goToMain();

                }else{
                    userReaml.setId(contador());
                    userReaml.setName("admin@admin.ec");
                    userReaml.setPass("admin");
                    userReaml.setNombre("Administrador ");
                    realm.beginTransaction();
                    realm.insert(userReaml);
                    realm.commitTransaction();

                    Toast.makeText(getApplicationContext(), "Cuenta de administrador creada ",
                            Toast.LENGTH_SHORT)
                            .show();
                }



                // User has verified the signature, cipher, or message
                // authentication code (MAC) associated with the crypto object,
                // so you can use it in your app's crypto-driven workflows.
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        // Displays the "log in" prompt.
        biometricPrompt.authenticate(promptInfo);
    }

    //Fin de metodo biometrico



    //Inicio de Secion

    private void setCredentialsIfExist() {
        String email = Util.getUserMailPrefs(prefs);
        String password = Util.getUserPassPrefs(prefs);
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            editTextEmail.setText(email);
            editTextPassword.setText(password);
            switchRemember.setChecked(true);
            goToMain();
        }
    }

    private boolean login(String email, String password) {
        if (!isValidEmail(email)) {
            Toast.makeText(this, "email incorrecto", Toast.LENGTH_LONG).show();
            return false;
        } else if (!isValidPassword(password)) {
            Toast.makeText(this, "Password Mayor que 4 caracteres", Toast.LENGTH_LONG).show();
            return false;
        } else {

            RealmResults<User> result2 = realm.where(User.class)
                    .equalTo("name", email)
                    .and()
                    .equalTo("pass", password)
                    .findAll();
            if(result2.size()==0){
                Toast.makeText(this, "NO existe un usuario con esos caracteres Registrate ", Toast.LENGTH_LONG).show();
                return false;
            }else{
                return true;
            }


        }
    }

    private void saveOnPreferences(String email, String password) {

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email", email);
            editor.putString("pass", password);
            editor.apply();


    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 4;
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (login(email, password)) {
                    goToMain();
                    saveOnPreferences(email, password);
                }

                break;
            case R.id.ivFacebook:
                signIn();
                break;
            case R.id.tvSignUp:
                signUp();
                break;
        }

    }

    private void signUp() {
        Intent intent = new Intent(this, UserActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    //Fin de inicio de secion


    //Inicio con google
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
                //firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();


    }

    // [START auth_with_google]
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]

        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (login(user.getEmail(), user.getUid())) {
                                saveOnPreferences(user.getEmail(), user.getUid());
                                goToMain();

                            }else{
                                userReaml.setId(contador());
                                userReaml.setName(user.getEmail());
                                userReaml.setPass(user.getUid());
                                userReaml.setNombre(user.getDisplayName());
                                realm.beginTransaction();
                                realm.insert(userReaml);
                                realm.commitTransaction();
                                Toast.makeText(getApplicationContext(), "Cuenta de Google  creada ",
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }

                        // [START_EXCLUDE]

                        // [END_EXCLUDE]
                    }
                });
    }
    // [END auth_with_google]

    private long contador() {
        RealmResults<User> result2 = realm.where(User.class).findAll();
        return result2.size()+1;
    }




}


