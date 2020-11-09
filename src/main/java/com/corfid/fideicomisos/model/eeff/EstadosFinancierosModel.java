package com.corfid.fideicomisos.model.eeff;

import java.util.Date;

import com.corfid.fideicomisos.model.utilities.GenericModel;

public class EstadosFinancierosModel extends GenericModel {

    private Integer idEstadosFinancieros;
    private String nombreFideicomiso;
    private String tipoInforme;
    private Date fechaCorte;
    private String fechaCorteFormato;
    private String monedaElaboracionInforme;
    private String monedaExpresionInforme;
    private String ruta;
    private byte[] archivo;
    private String nombreArchivo;
    private String pesoArchivo;
    private String tipoArchivo;
    private String estado;
    private String descEstadoRegistro;
    private String descEstado;
    private String estadoRegistro;
    private String descTipoInforme;

    public Integer getIdEstadosFinancieros() {
        return idEstadosFinancieros;
    }

    public void setIdEstadosFinancieros(Integer idEstadosFinancieros) {
        this.idEstadosFinancieros = idEstadosFinancieros;
    }

    public String getNombreFideicomiso() {
        return nombreFideicomiso;
    }

    public void setNombreFideicomiso(String nombreFideicomiso) {
        this.nombreFideicomiso = nombreFideicomiso;
    }

    public String getTipoInforme() {
        return tipoInforme;
    }

    public void setTipoInforme(String tipoInforme) {
        this.tipoInforme = tipoInforme;
    }

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public String getFechaCorteFormato() {
        return fechaCorteFormato;
    }

    public void setFechaCorteFormato(String fechaCorteFormato) {
        this.fechaCorteFormato = fechaCorteFormato;
    }

    public String getMonedaElaboracionInforme() {
        return monedaElaboracionInforme;
    }

    public void setMonedaElaboracionInforme(String monedaElaboracionInforme) {
        this.monedaElaboracionInforme = monedaElaboracionInforme;
    }

    public String getMonedaExpresionInforme() {
        return monedaExpresionInforme;
    }

    public void setMonedaExpresionInforme(String monedaExpresionInforme) {
        this.monedaExpresionInforme = monedaExpresionInforme;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getPesoArchivo() {
        return pesoArchivo;
    }

    public void setPesoArchivo(String pesoArchivo) {
        this.pesoArchivo = pesoArchivo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescEstadoRegistro() {
        return descEstadoRegistro;
    }

    public void setDescEstadoRegistro(String descEstadoRegistro) {
        this.descEstadoRegistro = descEstadoRegistro;
    }

    public String getDescEstado() {
        return descEstado;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getDescTipoInforme() {
        return descTipoInforme;
    }

    public void setDescTipoInforme(String descTipoInforme) {
        this.descTipoInforme = descTipoInforme;
    }

}
