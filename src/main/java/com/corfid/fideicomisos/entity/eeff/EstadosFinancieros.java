package com.corfid.fideicomisos.entity.eeff;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;
import com.corfid.fideicomisos.entity.banco.Fideicomiso;

@Entity
@Table(name = "efestfin")
public class EstadosFinancieros extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "n_idesfi", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
    private Integer idEstadosFinancieros;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "n_idfico", nullable = false, insertable = true, updatable = true)
    private Fideicomiso fideicomiso;

    @Column(name = "c_tipinf", nullable = false, length = 2, insertable = true, updatable = true)
    private String tipoInforme;

    @Temporal(TemporalType.DATE)
    @Column(name = "d_fecinf", nullable = false, insertable = true, updatable = true, length = 7)
    private Date fechaCorte;

    @Column(name = "c_moninf", nullable = false, length = 3, insertable = true, updatable = true)
    private String monedaElaboracionInforme;

    @Column(name = "c_monexp", nullable = false, length = 3, insertable = true, updatable = true)
    private String monedaExpresionInforme;

    @Column(name = "c_rutinf", nullable = true, length = 500, insertable = true, updatable = true)
    private String ruta;

    @Lob
    @Column(name = "l_archiv", nullable = true, insertable = true, updatable = true)
    private byte[] archivo;

    @Column(name = "c_nomarc", nullable = false, length = 250, insertable = true, updatable = true)
    private String nombreArchivo;

    @Column(name = "c_pesarc", nullable = false, length = 15, insertable = true, updatable = true)
    private String pesoArchivo;

    @Column(name = "c_tiparc", nullable = false, length = 25, insertable = true, updatable = true)
    private String tipoArchivo;

    @Column(name = "c_estado", nullable = false, length = 1, insertable = true, updatable = true)
    private String estado;

    @Column(name = "c_periodo", nullable = true, length = 6, insertable = true, updatable = true)
    private String periodo;

    public Integer getIdEstadosFinancieros() {
        return idEstadosFinancieros;
    }

    public void setIdEstadosFinancieros(Integer idEstadosFinancieros) {
        this.idEstadosFinancieros = idEstadosFinancieros;
    }

    public Fideicomiso getFideicomiso() {
        return fideicomiso;
    }

    public void setFideicomiso(Fideicomiso fideicomiso) {
        this.fideicomiso = fideicomiso;
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

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public EstadosFinancieros(Integer idEstadosFinancieros, Fideicomiso fideicomiso, String tipoInforme, Date fechaCorte, String monedaElaboracionInforme, String monedaExpresionInforme, String ruta, byte[] archivo, String nombreArchivo, String pesoArchivo, String tipoArchivo, String estado, String periodo) {
        super();
        this.idEstadosFinancieros = idEstadosFinancieros;
        this.fideicomiso = fideicomiso;
        this.tipoInforme = tipoInforme;
        this.fechaCorte = fechaCorte;
        this.monedaElaboracionInforme = monedaElaboracionInforme;
        this.monedaExpresionInforme = monedaExpresionInforme;
        this.ruta = ruta;
        this.archivo = archivo;
        this.nombreArchivo = nombreArchivo;
        this.pesoArchivo = pesoArchivo;
        this.tipoArchivo = tipoArchivo;
        this.estado = estado;
        this.periodo = periodo;
    }

    public EstadosFinancieros() {
    }
}
