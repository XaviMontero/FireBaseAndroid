package com.example.proyectofinal.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User  extends RealmObject {
    @PrimaryKey
    private long id;
    private String name;
    private String pass;
    private String nombre;


    public User() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public User(long id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}