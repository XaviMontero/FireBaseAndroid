package com.example.proyectofinal.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectofinal.R;
import com.example.proyectofinal.model.User;

import io.realm.Realm;
import io.realm.RealmResults;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    private   Realm realm =Realm.getDefaultInstance();
    private EditText nombre;
    private EditText pass;
    private EditText passConfirm;
    private EditText usuario;
    private Button save;
    private User user;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        reference();
        inicializacion();

    }

    private void inicializacion() {
    save.setOnClickListener(this);
    }

    private void reference() {
        user= new User();
        nombre = findViewById(R.id.etUsernameEn);
        pass = findViewById(R.id.etPasswordEN);
        passConfirm = findViewById(R.id.tvPasswordEnTow);
        save = findViewById(R.id.save);
        usuario = findViewById(R.id.nombre_user);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                if(passConfirm.getText().toString().equals(pass.getText().toString()) && usuario.length()>=5){
                        if(login(nombre.getText().toString(),pass.getText().toString())){
                            user.setId(contador());
                            user.setName(nombre.getText().toString());
                            user.setPass(pass.getText().toString());
                            user.setNombre(usuario.getText().toString());
                            realm.beginTransaction();
                            realm.insert(user);
                            realm.commitTransaction();
                            logOut();
                        }

                } else {
                    Toast.makeText(this,"La contraseña no coincide  y el nombre debe tener 5 caracteres o mas ",Toast.LENGTH_LONG).show();
                }
                break;
                default:
                    break;
        }
    }

    private long contador() {
        RealmResults<User> result2 = realm.where(User.class).findAll();
        return result2.size()+1;
    }

    private boolean login(String email, String password) {
        if (!isValidEmail(email)) {
            Toast.makeText(this, "email incorrecto", Toast.LENGTH_LONG).show();
            return false;
        } else if (!isValidPassword(password)) {
            Toast.makeText(this, "Password Mayor que 4 caracteres", Toast.LENGTH_LONG).show();
            return false;
        } else {

                return true;



        }
    }
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 4;
    }

    private void logOut() {
        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);
    }
}
