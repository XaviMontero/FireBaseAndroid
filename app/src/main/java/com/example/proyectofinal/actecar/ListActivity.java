package com.example.proyectofinal.actecar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.proyectofinal.R;
import com.example.proyectofinal.model.User;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListActivity extends AppCompatActivity {
    private ListView listview;
    private ArrayList<String> names;
    private Realm realm =Realm.getDefaultInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listview = findViewById(R.id.lista_usuario);
        names = getNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listview.setAdapter(adapter);

    }

    private ArrayList<String> getNames() {
        ArrayList<String> datos= new ArrayList<>();
        for(User user:getUser()){
            datos.add(user.getNombre());

        }
        return datos;
    }


    private  RealmResults<User>  getUser( ) {

        RealmResults<User> result2 = realm.where(User.class).findAll();
        return result2;

    }
}
