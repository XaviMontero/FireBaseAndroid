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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.R;
import com.example.proyectofinal.adapter.MainAdapter;
import com.example.proyectofinal.adapter.ProductoAdapter;
import com.example.proyectofinal.app.MyApp;
import com.example.proyectofinal.model.User;
import com.example.proyectofinal.modelo.producto.Producto;
import com.example.proyectofinal.util.Util;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ProductosActivity extends AppCompatActivity implements View.OnClickListener {

    private User userReaml;
    private Realm realm =Realm.getDefaultInstance();
    private SharedPreferences prefs;
    private TextView textView;
    private Producto producto;
    String email;


    private RecyclerView.LayoutManager layoutManager;
    private List<Producto> list_usuarios = new ArrayList<Producto>();
    private RecyclerView recyclerView;
    public ProductoAdapter mainAdapter;
    private Double total = 0.0;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_productos);

        userReaml= new User();
        textView = findViewById(R.id.user_main2);
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        email = Util.getUserMailPrefs(prefs);
        userReaml= getUser(email);
        textView.setText("Binvenido: "+userReaml.getNombre());


        list_usuarios= MyApp.productos;



        recyclerView = findViewById(R.id.recicle_view_producto);
        layoutManager = new LinearLayoutManager(this);
        // Observa como pasamos el activity, con this. Podríamos declarar
        // Activity o Context en el constructor y funcionaría pasando el mismo valor, this
        mainAdapter = new ProductoAdapter(list_usuarios, R.layout.card_view_producto, this, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto fruit, int position) {
                total =total+fruit.getProPre();
                Toast.makeText(getApplicationContext(),"Producto agregado al carrito  "+fruit.getProNom()+""+ "total es "+ total, Toast.LENGTH_LONG).show();
            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mainAdapter);
        fab= findViewById(R.id.floatingActionButton3);
        fab.setOnClickListener(this);
        mainAdapter.notifyDataSetChanged();

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
            case R.id.menu_cliente:
                clienteActivity();
                return  true;
            case R.id.menu_pedidos:
                mapas();

                return true;
            case R.id.menu_productos:
                pagos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void pagos() {
        Intent intent = new Intent(this, PagosActivity.class);
        startActivity(intent);
    }

    private void mapas() {
        Intent intent = new Intent(this, MapasActivity.class);
        startActivity(intent);
    }

    private void clienteActivity() {
        Intent intent = new Intent(this, ListClienteActivity.class);
        startActivity(intent);
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

    @Override
    public void onClick(View v) {
        if(total!= 0.0){
            Intent myIntent = new Intent(this, ClienteActivity.class);
            myIntent.putExtra("total",total);
            startActivity(myIntent);
            total =0.0;

        } else{
            Toast.makeText(getApplicationContext(),"No a ingresado nigun producto al carrito", Toast.LENGTH_LONG).show();

        }
    }
}
