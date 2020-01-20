package com.example.proyectofinal.modelo.cliente;

public class TipoIdentificacion {
    String tipo_identificacion_id;
    String tipo_identificacion_descripcion;
    Boolean tipo_identificacion_activo;

    public TipoIdentificacion() {
    }

    public String getTipo_identificacion_id() {
        return tipo_identificacion_id;
    }

    public void setTipo_identificacion_id(String tipo_identificacion_id) {
        this.tipo_identificacion_id = tipo_identificacion_id;
    }

    public String getTipo_identificacion_descripcion() {
        return tipo_identificacion_descripcion;
    }

    public void setTipo_identificacion_descripcion(String tipo_identificacion_descripcion) {
        this.tipo_identificacion_descripcion = tipo_identificacion_descripcion;
    }

    public Boolean getTipo_identificacion_activo() {
        return tipo_identificacion_activo;
    }

    public void setTipo_identificacion_activo(Boolean tipo_identificacion_activo) {
        this.tipo_identificacion_activo = tipo_identificacion_activo;
    }
}

