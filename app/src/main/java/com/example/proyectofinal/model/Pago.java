package com.example.proyectofinal.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Pago extends RealmObject {
    @PrimaryKey
    private long id;
    private  String cedula;
    private  Double total;
    private Double latitud;
    private Double longitud;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    public Pago() {
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
