package com.example.proyectofinal.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Producto extends RealmObject {
    public static  Double LIMIT_QUANTITY= 10.0;
    @PrimaryKey
    private String id;
    private String nombre;
    private Double precio;
    private String descripcion;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Producto() {
    }
}
