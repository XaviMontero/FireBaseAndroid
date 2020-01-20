package com.example.proyectofinal.modelo.cliente;

public class Cliente {
    String strCedulaCliente;
    String CliApe;
    String CliNom;
    String CliPai;
    String CliCRP;
    String CliTel;
    String CliEstCiv;
    String CliSex;
    String CliFecNac;
    String CliPro;
    String CliPrv;
    String CliCiu;
    String CliMay;
    String CliHos;
    String CliObs;
    String strDireccionCliente;
    Boolean blnMoroso;
    Double CupoCredito;
    Boolean ParteRelacionada;
    String tipo_identificacion_id;
    Integer ciudad_id;
    Integer zona_id;
    String vendedor_id;
    Integer dias_credito;
    Integer dias_tolerancia_credito;
    Integer precio_aplica;
    String cliente_id_cuenta_contable;
    String cliente_id_grupo_contable;
    Ciudad Ciudad;
    ZonaCiudad ZonaCiudad;
    TipoIdentificacion TipoIdentificacion;

    public Cliente() {
    }

    public String getStrCedulaCliente() {
        return strCedulaCliente;
    }

    public void setStrCedulaCliente(String strCedulaCliente) {
        this.strCedulaCliente = strCedulaCliente;
    }

    public String getCliApe() {
        return CliApe;
    }

    public void setCliApe(String cliApe) {
        CliApe = cliApe;
    }

    public String getCliNom() {
        return CliNom;
    }

    public void setCliNom(String cliNom) {
        CliNom = cliNom;
    }

    public String getCliPai() {
        return CliPai;
    }

    public void setCliPai(String cliPai) {
        CliPai = cliPai;
    }

    public String getCliCRP() {
        return CliCRP;
    }

    public void setCliCRP(String cliCRP) {
        CliCRP = cliCRP;
    }

    public String getCliTel() {
        return CliTel;
    }

    public void setCliTel(String cliTel) {
        CliTel = cliTel;
    }

    public String getCliEstCiv() {
        return CliEstCiv;
    }

    public void setCliEstCiv(String cliEstCiv) {
        CliEstCiv = cliEstCiv;
    }

    public String getCliSex() {
        return CliSex;
    }

    public void setCliSex(String cliSex) {
        CliSex = cliSex;
    }

    public String getCliFecNac() {
        return CliFecNac;
    }

    public void setCliFecNac(String cliFecNac) {
        CliFecNac = cliFecNac;
    }

    public String getCliPro() {
        return CliPro;
    }

    public void setCliPro(String cliPro) {
        CliPro = cliPro;
    }

    public String getCliPrv() {
        return CliPrv;
    }

    public void setCliPrv(String cliPrv) {
        CliPrv = cliPrv;
    }

    public String getCliCiu() {
        return CliCiu;
    }

    public void setCliCiu(String cliCiu) {
        CliCiu = cliCiu;
    }

    public String getCliMay() {
        return CliMay;
    }

    public void setCliMay(String cliMay) {
        CliMay = cliMay;
    }

    public String getCliHos() {
        return CliHos;
    }

    public void setCliHos(String cliHos) {
        CliHos = cliHos;
    }

    public String getCliObs() {
        return CliObs;
    }

    public void setCliObs(String cliObs) {
        CliObs = cliObs;
    }

    public String getStrDireccionCliente() {
        return strDireccionCliente;
    }

    public void setStrDireccionCliente(String strDireccionCliente) {
        this.strDireccionCliente = strDireccionCliente;
    }

    public Boolean getBlnMoroso() {
        return blnMoroso;
    }

    public void setBlnMoroso(Boolean blnMoroso) {
        this.blnMoroso = blnMoroso;
    }

    public Double getCupoCredito() {
        return CupoCredito;
    }

    public void setCupoCredito(Double cupoCredito) {
        CupoCredito = cupoCredito;
    }

    public Boolean getParteRelacionada() {
        return ParteRelacionada;
    }

    public void setParteRelacionada(Boolean parteRelacionada) {
        ParteRelacionada = parteRelacionada;
    }

    public String getTipo_identificacion_id() {
        return tipo_identificacion_id;
    }

    public void setTipo_identificacion_id(String tipo_identificacion_id) {
        this.tipo_identificacion_id = tipo_identificacion_id;
    }

    public Integer getCiudad_id() {
        return ciudad_id;
    }

    public void setCiudad_id(Integer ciudad_id) {
        this.ciudad_id = ciudad_id;
    }

    public Integer getZona_id() {
        return zona_id;
    }

    public void setZona_id(Integer zona_id) {
        this.zona_id = zona_id;
    }

    public String getVendedor_id() {
        return vendedor_id;
    }

    public void setVendedor_id(String vendedor_id) {
        this.vendedor_id = vendedor_id;
    }

    public Integer getDias_credito() {
        return dias_credito;
    }

    public void setDias_credito(Integer dias_credito) {
        this.dias_credito = dias_credito;
    }

    public Integer getDias_tolerancia_credito() {
        return dias_tolerancia_credito;
    }

    public void setDias_tolerancia_credito(Integer dias_tolerancia_credito) {
        this.dias_tolerancia_credito = dias_tolerancia_credito;
    }

    public Integer getPrecio_aplica() {
        return precio_aplica;
    }

    public void setPrecio_aplica(Integer precio_aplica) {
        this.precio_aplica = precio_aplica;
    }

    public String getCliente_id_cuenta_contable() {
        return cliente_id_cuenta_contable;
    }

    public void setCliente_id_cuenta_contable(String cliente_id_cuenta_contable) {
        this.cliente_id_cuenta_contable = cliente_id_cuenta_contable;
    }

    public String getCliente_id_grupo_contable() {
        return cliente_id_grupo_contable;
    }

    public void setCliente_id_grupo_contable(String cliente_id_grupo_contable) {
        this.cliente_id_grupo_contable = cliente_id_grupo_contable;
    }

    public com.example.proyectofinal.modelo.cliente.Ciudad getCiudad() {
        return Ciudad;
    }

    public void setCiudad(com.example.proyectofinal.modelo.cliente.Ciudad ciudad) {
        Ciudad = ciudad;
    }

    public com.example.proyectofinal.modelo.cliente.ZonaCiudad getZonaCiudad() {
        return ZonaCiudad;
    }

    public void setZonaCiudad(com.example.proyectofinal.modelo.cliente.ZonaCiudad zonaCiudad) {
        ZonaCiudad = zonaCiudad;
    }

    public com.example.proyectofinal.modelo.cliente.TipoIdentificacion getTipoIdentificacion() {
        return TipoIdentificacion;
    }

    public void setTipoIdentificacion(com.example.proyectofinal.modelo.cliente.TipoIdentificacion tipoIdentificacion) {
        TipoIdentificacion = tipoIdentificacion;
    }
}






