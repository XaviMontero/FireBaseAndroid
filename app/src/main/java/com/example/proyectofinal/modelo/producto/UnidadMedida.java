package com.example.proyectofinal.modelo.producto;

import io.realm.RealmObject;

public class UnidadMedida  {
    private Integer unidad_id;
    private String unidad_nombre;
    private String unidad_descripcion;

    public UnidadMedida() {
    }

    public Integer getUnidad_id() {
        return unidad_id;
    }

    public void setUnidad_id(Integer unidad_id) {
        this.unidad_id = unidad_id;
    }

    public String getUnidad_nombre() {
        return unidad_nombre;
    }

    public void setUnidad_nombre(String unidad_nombre) {
        this.unidad_nombre = unidad_nombre;
    }

    public String getUnidad_descripcion() {
        return unidad_descripcion;
    }

    public void setUnidad_descripcion(String unidad_descripcion) {
        this.unidad_descripcion = unidad_descripcion;
    }
}
