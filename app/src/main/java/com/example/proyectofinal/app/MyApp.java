package com.example.proyectofinal.app;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.widget.Toast;

import com.example.proyectofinal.modelo.ClienService;
import com.example.proyectofinal.modelo.PostService;
import com.example.proyectofinal.modelo.cliente.Cliente;
import com.example.proyectofinal.modelo.producto.Producto;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApp extends Application {
    String API_BASE_URL="http://174.142.32.198/invent_web_api/api/";


    public  static List<Producto> productos= new ArrayList<>();
    public  static List<Cliente> clientes= new ArrayList<>();
    boolean estado = true;
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm").build();
        Realm.setDefaultConfiguration(config);
        // Este es solo para poder ver el Splash Screen durante 3 segundos
       if (permisos()) {
           getPosts();
           getPostsCliente();
           SystemClock.sleep(3000);
           Toast.makeText(getApplicationContext(),"Con permisos", Toast.LENGTH_LONG).show();

       }else {
           Toast.makeText(getApplicationContext(),"Sin permisos", Toast.LENGTH_LONG).show();

       }




}

    private boolean permisos() {
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public void getPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostService postService = retrofit.create(PostService.class);
        Call<List<Producto>> call = postService.getPost();

        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                productos= response.body();
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
            }
        });
    }

    private void getPostsCliente() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ClienService postService = retrofit.create(ClienService.class);
        Call<List<Cliente>> call = postService.getPost();

        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                clientes= response.body();
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
            }
        });
    }
}
