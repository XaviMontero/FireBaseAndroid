package com.example.proyectofinal.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.MainAdapter;
import com.example.proyectofinal.R;
import com.example.proyectofinal.model.Producto;
import com.example.proyectofinal.model.User;
import com.example.proyectofinal.util.Util;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.util.AbstractQueue;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private User userReaml;
    private Realm realm =Realm.getDefaultInstance();
    private SharedPreferences prefs;
    private TextView textView;
    private Producto producto;
    String email;
    private DatabaseReference mDatabase;// ...
    private RecyclerView.LayoutManager layoutManager;


    private ArrayList<Producto> list_usuarios = new ArrayList<Producto>();
    private RecyclerView recyclerView;
    public MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference("productos");




       producto=new Producto();
        producto.setId("skjghjgfkjljhugfbf");
        producto.setDescripcion("Caldo Magui para las sopas de familia  ");
        producto.setNombre("Caldo magi ");
        producto.setUrl("https://www.nestle.com.ve/sites/g/files/pydnoa526/files/asset-library/publishingimages/productos/maggi/productos/cubito%20de%20pollo%2092g_3-4.jpg");
        producto.setPrecio(0.5);
        mDatabase.child(producto.getId()).setValue(producto);








        setContentView(R.layout.activity_main);
        userReaml= new User();
        textView = findViewById(R.id.user_main);
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        email = Util.getUserMailPrefs(prefs);
        userReaml= getUser(email);
        textView.setText("Binvenido: "+userReaml.getNombre());

        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Producto users = snapshot.getValue(Producto.class);

                     if ( !removerId(users.getId())){
                         list_usuarios.add(users);


                     }else{
                         list_usuarios.remove(removerIdEli(users.getId()));
                         list_usuarios.add(users);
                     }
                    mainAdapter.notifyDataSetChanged();
                }
                //tuAdapter.notifyDataSetChanged();
            }

            private boolean removerId(String id) {
                int i =0;
               for (Producto p : list_usuarios){
                   if(p.getId().equals(id)){
                       return true;
                   }
                   i++;
               }
               return false;
            }

            private int removerIdEli(String id) {
                int i =0;
                for (Producto p : list_usuarios){
                    if(p.getId().equals(id)){
                        return i;
                    }
                    i++;
                }
                return i;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };


        mDatabase.addValueEventListener(userListener);
        recyclerView = findViewById(R.id.recicle_view);
        layoutManager = new LinearLayoutManager(this);
        // Observa como pasamos el activity, con this. Podríamos declarar
        // Activity o Context en el constructor y funcionaría pasando el mismo valor, this
        mainAdapter = new MainAdapter(list_usuarios, R.layout.card_view, this, new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto fruit, int position) {

            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mainAdapter);
    }

    private User getUser(String email) {

            RealmResults<User> result2 = realm.where(User.class).equalTo("name",email).findAll();
            return result2.get(0);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_logout:
                Toast.makeText(this,":D ",Toast.LENGTH_LONG).show();
                cerrar();

                return true;
            case R.id.menu_forget_logout:
              cerrar();
                return true;
            case R.id.menu_usuarios:
                usuariosActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void cerrar() {
        Util.removeSharedPreferences(prefs);
        FirebaseAuth.getInstance().signOut();

        logOut();
    }

    private void logOut() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void usuariosActivity() {
        Intent intent = new Intent(this, ListActivity.class);
         startActivity(intent);
    }
}
