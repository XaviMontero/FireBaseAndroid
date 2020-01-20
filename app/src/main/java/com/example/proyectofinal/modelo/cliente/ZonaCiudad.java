package com.example.proyectofinal.modelo.cliente;

public class ZonaCiudad {
    private Integer zona_id;
    private String zona_decsripcion;
    private Integer zona_ciudad_id;

    public ZonaCiudad() {
    }

    public Integer getZona_id() {
        return zona_id;
    }

    public void setZona_id(Integer zona_id) {
        this.zona_id = zona_id;
    }

    public String getZona_decsripcion() {
        return zona_decsripcion;
    }

    public void setZona_decsripcion(String zona_decsripcion) {
        this.zona_decsripcion = zona_decsripcion;
    }

    public Integer getZona_ciudad_id() {
        return zona_ciudad_id;
    }

    public void setZona_ciudad_id(Integer zona_ciudad_id) {
        this.zona_ciudad_id = zona_ciudad_id;
    }
}
