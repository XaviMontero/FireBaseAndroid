package com.example.proyectofinal.app;

import android.app.Application;
import android.os.SystemClock;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm").build();
        Realm.setDefaultConfiguration(config);
        // Este es solo para poder ver el Splash Screen durante 3 segundos
        SystemClock.sleep(3000);

}
}
