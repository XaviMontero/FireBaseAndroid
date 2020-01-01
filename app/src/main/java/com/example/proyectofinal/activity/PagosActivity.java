package com.example.proyectofinal.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.proyectofinal.R;
import com.example.proyectofinal.model.Cliente;
import com.example.proyectofinal.model.Pago;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class PagosActivity extends AppCompatActivity {

    private ListView listview;
    private ArrayList<String> names;
    private Realm realm =Realm.getDefaultInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagos);
        listview = findViewById(R.id.lista_pagos);
        names = getNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listview.setAdapter(adapter);

    }

    private ArrayList<String> getNames() {
        ArrayList<String> datos= new ArrayList<>();
        for(Pago user:getUser()){
            datos.add("Nombre "+ getNameCliente(user.getCedula()) +" Total "+ user.getTotal());

        }
        return datos;
    }


    private RealmResults<Pago> getUser( ) {

        RealmResults<Pago> result2 = realm.where(Pago.class).findAll();
        return result2;

    }

    private String getNameCliente(String cedula ) {

        RealmResults<Cliente> result2 = realm.where(Cliente.class).equalTo("cedula",cedula).findAll();
        return result2.get(0).getNombre();

    }
}
