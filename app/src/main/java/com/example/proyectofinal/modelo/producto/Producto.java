package com.example.proyectofinal.modelo.producto;


public class Producto   {
    private String ProCod;
    private String ProNom;
    private Integer ProMin;
    private Double ProSto;
    private Double ProPre;
    private String ProUniMed;
    private String ProFec;
    private Double ProCatCod;
    private String ProUbi;
    private String ProCtaCod;
    private Boolean ProIVA;
    private Double ProPreVen;
    private Double ProPreMay;
    private String ProObs;
    private String ProFecExp;
    private Boolean blnActivo;
    private Double dblPeso;
    private Double PrecioCombo;
    private Double CantMaxAplicaPrecioMayor;
    private Double CantidadPorCombo;
    private Integer unidad_medida_id;
    private Boolean es_gasto;
    private Double Precio3;
    private Double Precio4;
    private CATEGORIAS CATEGORIAS;
    private UnidadMedida UnidadMedida;
    private Boolean aplica_nc;







    public Producto() {
    }

    public String getProCod() {
        return ProCod;
    }

    public void setProCod(String proCod) {
        ProCod = proCod;
    }

    public String getProNom() {
        return ProNom;
    }

    public void setProNom(String proNom) {
        ProNom = proNom;
    }

    public Integer getProMin() {
        return ProMin;
    }

    public void setProMin(Integer proMin) {
        ProMin = proMin;
    }

    public Double getProSto() {
        return ProSto;
    }

    public void setProSto(Double proSto) {
        ProSto = proSto;
    }

    public Double getProPre() {
        return ProPre;
    }

    public void setProPre(Double proPre) {
        ProPre = proPre;
    }

    public String getProUniMed() {
        return ProUniMed;
    }

    public void setProUniMed(String proUniMed) {
        ProUniMed = proUniMed;
    }

    public String getProFec() {
        return ProFec;
    }

    public void setProFec(String proFec) {
        ProFec = proFec;
    }

    public Double getProCatCod() {
        return ProCatCod;
    }

    public void setProCatCod(Double proCatCod) {
        ProCatCod = proCatCod;
    }

    public String getProUbi() {
        return ProUbi;
    }

    public void setProUbi(String proUbi) {
        ProUbi = proUbi;
    }

    public String getProCtaCod() {
        return ProCtaCod;
    }

    public void setProCtaCod(String proCtaCod) {
        ProCtaCod = proCtaCod;
    }

    public Boolean getProIVA() {
        return ProIVA;
    }

    public void setProIVA(Boolean proIVA) {
        ProIVA = proIVA;
    }

    public Double getProPreVen() {
        return ProPreVen;
    }

    public void setProPreVen(Double proPreVen) {
        ProPreVen = proPreVen;
    }

    public Double getProPreMay() {
        return ProPreMay;
    }

    public void setProPreMay(Double proPreMay) {
        ProPreMay = proPreMay;
    }

    public String getProObs() {
        return ProObs;
    }

    public void setProObs(String proObs) {
        ProObs = proObs;
    }

    public String getProFecExp() {
        return ProFecExp;
    }

    public void setProFecExp(String proFecExp) {
        ProFecExp = proFecExp;
    }

    public Boolean getBlnActivo() {
        return blnActivo;
    }

    public void setBlnActivo(Boolean blnActivo) {
        this.blnActivo = blnActivo;
    }

    public Double getDblPeso() {
        return dblPeso;
    }

    public void setDblPeso(Double dblPeso) {
        this.dblPeso = dblPeso;
    }

    public Double getPrecioCombo() {
        return PrecioCombo;
    }

    public void setPrecioCombo(Double precioCombo) {
        PrecioCombo = precioCombo;
    }

    public Double getCantMaxAplicaPrecioMayor() {
        return CantMaxAplicaPrecioMayor;
    }

    public void setCantMaxAplicaPrecioMayor(Double cantMaxAplicaPrecioMayor) {
        CantMaxAplicaPrecioMayor = cantMaxAplicaPrecioMayor;
    }

    public Double getCantidadPorCombo() {
        return CantidadPorCombo;
    }

    public void setCantidadPorCombo(Double cantidadPorCombo) {
        CantidadPorCombo = cantidadPorCombo;
    }

    public Integer getUnidad_medida_id() {
        return unidad_medida_id;
    }

    public void setUnidad_medida_id(Integer unidad_medida_id) {
        this.unidad_medida_id = unidad_medida_id;
    }

    public Boolean getEs_gasto() {
        return es_gasto;
    }

    public void setEs_gasto(Boolean es_gasto) {
        this.es_gasto = es_gasto;
    }

    public Boolean getAplica_nc() {
        return aplica_nc;
    }

    public void setAplica_nc(Boolean aplica_nc) {
        this.aplica_nc = aplica_nc;
    }

    public Double getPrecio3() {
        return Precio3;
    }

    public void setPrecio3(Double precio3) {
        Precio3 = precio3;
    }

    public Double getPrecio4() {
        return Precio4;
    }

    public void setPrecio4(Double precio4) {
        Precio4 = precio4;
    }

    public com.example.proyectofinal.modelo.producto.CATEGORIAS getCATEGORIAS() {
        return CATEGORIAS;
    }

    public void setCATEGORIAS(com.example.proyectofinal.modelo.producto.CATEGORIAS CATEGORIAS) {
        this.CATEGORIAS = CATEGORIAS;
    }

    public com.example.proyectofinal.modelo.producto.UnidadMedida getUnidadMedida() {
        return UnidadMedida;
    }

    public void setUnidadMedida(com.example.proyectofinal.modelo.producto.UnidadMedida unidadMedida) {
        UnidadMedida = unidadMedida;
    }
}

