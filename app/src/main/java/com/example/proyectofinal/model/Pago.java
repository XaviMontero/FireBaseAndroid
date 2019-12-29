package com.example.proyectofinal.model;

import io.realm.RealmObject;

public class Pago extends RealmObject {
    private  String cedula;
    private  Double total;

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
