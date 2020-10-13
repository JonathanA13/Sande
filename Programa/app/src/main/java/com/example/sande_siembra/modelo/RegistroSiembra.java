package com.example.sande_siembra.modelo;

import java.util.Date;

public class RegistroSiembra {

    private int id; // El ID de la BD
    private String fecha;
    private int semana;
    private String especie;
    private String finca;
    private int valvula;
    private int bloque;
    private String lado;
    private String etiqueta;
    private int cama;
    private String proce;
    private String tamaniocama;
    private String brote;
    private String tiposiembra;
    private String origen;
    private String variedad;
    private String pruebas1;
    private String pruebas2;
    private String otraspruebas;
    private int semanacabe;
    private int bloquecabe;
    private String fincacabe;
    private int metros;
    private String calibre;
    private int bulbos;


    public RegistroSiembra(String fecha, int semana, String especie, String finca, int bloque, int valvula, String lado, String etiqueta, int cama, String proce, String tamaniocama, String brote, String tiposiembra, String origen, String variedad, String pruebas1, String pruebas2, String otraspruebas, int semanacabe, int bloquecabe, String fincacabe, int metros, String calibre, int bulbos) {
        this.fecha = fecha;
        this.semana = semana;
        this.especie = especie;
        this.finca = finca;
        this.valvula = valvula;
        this.bloque = bloque;
        this.lado = lado;
        this.etiqueta = etiqueta;
        this.cama = cama;
        this.proce = proce;
        this.tamaniocama = tamaniocama;
        this.brote = brote;
        this.tiposiembra = tiposiembra;
        this.origen = origen;
        this.variedad = variedad;
        this.pruebas1 = pruebas1;
        this.pruebas2 = pruebas2;
        this.otraspruebas = otraspruebas;
        this.semanacabe = semanacabe;
        this.bloquecabe = bloquecabe;
        this.fincacabe = fincacabe;
        this.metros = metros;
        this.calibre = calibre;
        this.bulbos = bulbos;
    }



    // Constructor para cuando instanciamos desde la BD


    public RegistroSiembra(int id, String fecha, int semana, String especie, String finca, int bloque, int valvula,  String lado, String etiqueta, int cama, String proce, String tamaniocama, String brote, String tiposiembra, String origen, String variedad, String pruebas1, String pruebas2, String otraspruebas, int semanacabe, int bloquecabe, String fincacabe, int metros, String calibre, int bulbos) {
        this.fecha = fecha;
        this.semana = semana;
        this.especie = especie;
        this.finca = finca;
        this.valvula = valvula;
        this.bloque = bloque;
        this.lado = lado;
        this.etiqueta = etiqueta;
        this.cama = cama;
        this.proce = proce;
        this.tamaniocama = tamaniocama;
        this.brote = brote;
        this.tiposiembra = tiposiembra;
        this.origen = origen;
        this.variedad = variedad;
        this.pruebas1 = pruebas1;
        this.pruebas2 = pruebas2;
        this.otraspruebas = otraspruebas;
        this.semanacabe = semanacabe;
        this.bloquecabe = bloquecabe;
        this.fincacabe = fincacabe;
        this.metros = metros;
        this.calibre = calibre;
        this.bulbos = bulbos;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getFinca() {
        return finca;
    }

    public void setFinca(String finca) {
        this.finca = finca;
    }

    public int getValvula() {
        return valvula;
    }

    public void setValvula(int valvula) {
        this.valvula = valvula;
    }

    public int getBloque() {
        return bloque;
    }

    public void setBloque(int bloque) {
        this.bloque = bloque;
    }

    public String getLado() {
        return lado;
    }

    public void setLado(String lado) {
        this.lado = lado;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public int getCama() {
        return cama;
    }

    public void setCama(int cama) {
        this.cama = cama;
    }

    public String getProce() {
        return proce;
    }

    public void setProce(String proce) {
        this.proce = proce;
    }

    public String getTamaniocama() {
        return tamaniocama;
    }

    public void setTamaniocama(String tamaniocama) {
        this.tamaniocama = tamaniocama;
    }

    public String getBrote() {
        return brote;
    }

    public void setBrote(String brote) {
        this.brote = brote;
    }

    public String getTiposiembra() {
        return tiposiembra;
    }

    public void setTiposiembra(String tiposiembra) {
        this.tiposiembra = tiposiembra;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getVariedad() {
        return variedad;
    }

    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }

    public String getPruebas1() {
        return pruebas1;
    }

    public void setPruebas1(String pruebas1) {
        this.pruebas1 = pruebas1;
    }

    public String getPruebas2() {
        return pruebas2;
    }

    public void setPruebas2(String pruebas2) {
        this.pruebas2 = pruebas2;
    }

    public String getOtraspruebas() {
        return otraspruebas;
    }

    public void setOtraspruebas(String otraspruebas) {
        this.otraspruebas = otraspruebas;
    }

    public int getSemanacabe() {
        return semanacabe;
    }

    public void setSemanacabe(int semanacabe) {
        this.semanacabe = semanacabe;
    }

    public int getBloquecabe() {
        return bloquecabe;
    }

    public void setBloquecabe(int bloquecabe) {
        this.bloquecabe = bloquecabe;
    }

    public String getFincacabe() {
        return fincacabe;
    }

    public void setFincacabe(String fincacabe) {
        this.fincacabe = fincacabe;
    }

    public int getMetros() {
        return metros;
    }

    public void setMetros(int metros) {
        this.metros = metros;
    }

    public String getCalibre() {
        return calibre;
    }

    public void setCalibre(String calibre) {
        this.calibre = calibre;
    }

    public int getBulbos() {
        return bulbos;
    }

    public void setBulbos(int bulbos) {
        this.bulbos = bulbos;
    }

    @Override
    public String toString() {
        return "RegistroSiembra{" +
                "fecha=" + fecha +
                ", semana=" + semana +
                ", especie='" + especie + '\'' +
                ", finca='" + finca + '\'' +
                ", valvula=" + valvula +
                ", bloque=" + bloque +
                ", lado='" + lado + '\'' +
                ", etiqueta='" + etiqueta + '\'' +
                ", cama='" + cama + '\'' +
                ", proce='" + proce + '\'' +
                ", tamaniocama='" + tamaniocama + '\'' +
                ", brote='" + brote + '\'' +
                ", tiposiembra='" + tiposiembra + '\'' +
                ", origen='" + origen + '\'' +
                ", variedad='" + variedad + '\'' +
                ", pruebas1='" + pruebas1 + '\'' +
                ", pruebas2='" + pruebas2 + '\'' +
                ", otraspruebas='" + otraspruebas + '\'' +
                ", semanacabe='" + semanacabe + '\'' +
                ", bloquecabe='" + bloquecabe + '\'' +
                ", fincacabe='" + fincacabe + '\'' +
                ", metros=" + metros +
                ", calibre='" + calibre + '\'' +
                ", bulbos=" + bulbos +
                '}';
    }
}
