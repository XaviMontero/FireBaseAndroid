package com.example.proyectofinal.modelo.cliente;

import java.util.List;

public class Ciudad {
    private Integer ciudad_id;
    private String ciudad_descripcion;
    private Boolean ciudad_activa;
    private List<ZonaCiudad>ZonaCiudad;

    public Ciudad() {
    }

    public Integer getCiudad_id() {
        return ciudad_id;
    }

    public void setCiudad_id(Integer ciudad_id) {
        this.ciudad_id = ciudad_id;
    }

    public String getCiudad_descripcion() {
        return ciudad_descripcion;
    }

    public void setCiudad_descripcion(String ciudad_descripcion) {
        this.ciudad_descripcion = ciudad_descripcion;
    }

    public Boolean getCiudad_activa() {
        return ciudad_activa;
    }

    public void setCiudad_activa(Boolean ciudad_activa) {
        this.ciudad_activa = ciudad_activa;
    }

    public List<com.example.proyectofinal.modelo.cliente.ZonaCiudad> getZonaCiudad() {
        return ZonaCiudad;
    }

    public void setZonaCiudad(List<com.example.proyectofinal.modelo.cliente.ZonaCiudad> zonaCiudad) {
        ZonaCiudad = zonaCiudad;
    }
}
