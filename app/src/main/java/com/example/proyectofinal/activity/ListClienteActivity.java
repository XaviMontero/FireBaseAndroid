package com.example.proyectofinal.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.proyectofinal.R;
import com.example.proyectofinal.model.Cliente;
import com.example.proyectofinal.model.User;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListClienteActivity extends AppCompatActivity {

    private ListView listview;
    private ArrayList<String> names;
    private Realm realm =Realm.getDefaultInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cliente);
        listview = findViewById(R.id.lista_cliente);
        names = getNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listview.setAdapter(adapter);

    }

    private ArrayList<String> getNames() {
        ArrayList<String> datos= new ArrayList<>();
        for(Cliente user:getUser()){
            datos.add("Nombre "+user.getNombre() +" Cedula "+ user.getCedula());

        }
        return datos;
    }


    private RealmResults<Cliente> getUser( ) {

        RealmResults<Cliente> result2 = realm.where(Cliente.class).findAll();
        return result2;

    }
}