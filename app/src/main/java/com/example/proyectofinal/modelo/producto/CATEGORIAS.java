package com.example.proyectofinal.modelo.producto;

import io.realm.RealmObject;

public class CATEGORIAS   {
    private Double CatCod;
    private String CatNom ;
    private String CatDes;

    public CATEGORIAS() {
    }

    public Double getCatCod() {
        return CatCod;
    }

    public void setCatCod(Double catCod) {
        CatCod = catCod;
    }

    public String getCatNom() {
        return CatNom;
    }

    public void setCatNom(String catNom) {
        CatNom = catNom;
    }

    public String getCatDes() {
        return CatDes;
    }

    public void setCatDes(String catDes) {
        CatDes = catDes;
    }
}
