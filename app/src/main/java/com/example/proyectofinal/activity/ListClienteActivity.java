package com.example.proyectofinal.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.proyectofinal.R;
import com.example.proyectofinal.app.MyApp;

import com.example.proyectofinal.model.User;
import com.example.proyectofinal.modelo.cliente.Cliente;

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

        if (getUser().size()< 100 ){
            for(Cliente user: MyApp.clientes){
                com.example.proyectofinal.model.Cliente clie = new com.example.proyectofinal.model.Cliente();
                clie.setCedula(user.getStrCedulaCliente());
                clie.setNombre(user.getCliNom()+" "+user.getCliApe());
                clie.setTelefono(user.getCliTel());
                clie.setDireccion(user.getStrDireccionCliente());
                realm.beginTransaction();
                realm.insert(clie);
                realm.commitTransaction();
            }
        }

    }

    private ArrayList<String> getNames() {
        ArrayList<String> datos= new ArrayList<>();
        for(Cliente user: MyApp.clientes){
            datos.add("Nombre "+user.getCliNom() +" Cedula "+ user.getStrCedulaCliente());

        }
        return datos;
    }

    private RealmResults<com.example.proyectofinal.model.Cliente> getUser( ) {

        RealmResults<com.example.proyectofinal.model.Cliente> result2 = realm.where(com.example.proyectofinal.model.Cliente.class).findAll();
        return result2;

    }

}