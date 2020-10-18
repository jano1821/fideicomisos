package com.corfid.fideicomisos.model.administrativo;

import java.util.ArrayList;
import java.util.List;

import com.corfid.fideicomisos.model.utilities.GenericModel;

public class PersonaModel extends GenericModel {

    private Integer idPersona;

    private String nombres;

    private String apePat;

    private String apeMat;

    private String nombreCompleto;

    private String razonSocial;

    private String tipoPersona;

    private String estadoRegistro;

    private String descEstadoRegistro;

    private String descTipoPersona;

    private String tipoDocumento;

    private String descTipoDocumento;

    private String numeroDocumento;

    private String permiteVinculacion;

    private String permiteVinculacionCliente;

    private List<EmpresaModel> listEmpresa = new ArrayList<EmpresaModel>();

    private String empresa;

    private Integer idUsuarioRegistro;

    private String numeroTelefono;

    private String direccionEmail;

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApePat() {
        return apePat;
    }

    public void setApePat(String apePat) {
        this.apePat = apePat;
    }

    public String getApeMat() {
        return apeMat;
    }

    public void setApeMat(String apeMat) {
        this.apeMat = apeMat;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getDescEstadoRegistro() {
        return descEstadoRegistro;
    }

    public void setDescEstadoRegistro(String descEstadoRegistro) {
        this.descEstadoRegistro = descEstadoRegistro;
    }

    public String getDescTipoPersona() {
        return descTipoPersona;
    }

    public void setDescTipoPersona(String descTipoPersona) {
        this.descTipoPersona = descTipoPersona;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDescTipoDocumento() {
        return descTipoDocumento;
    }

    public void setDescTipoDocumento(String descTipoDocumento) {
        this.descTipoDocumento = descTipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getPermiteVinculacion() {
        return permiteVinculacion;
    }

    public void setPermiteVinculacion(String permiteVinculacion) {
        this.permiteVinculacion = permiteVinculacion;
    }

    public List<EmpresaModel> getListEmpresa() {
        return listEmpresa;
    }

    public void setListEmpresa(List<EmpresaModel> listEmpresa) {
        this.listEmpresa = listEmpresa;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Integer getIdUsuarioRegistro() {
        return idUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(Integer idUsuarioRegistro) {
        this.idUsuarioRegistro = idUsuarioRegistro;
    }

    public String getPermiteVinculacionCliente() {
        return permiteVinculacionCliente;
    }

    public void setPermiteVinculacionCliente(String permiteVinculacionCliente) {
        this.permiteVinculacionCliente = permiteVinculacionCliente;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getDireccionEmail() {
        return direccionEmail;
    }

    public void setDireccionEmail(String direccionEmail) {
        this.direccionEmail = direccionEmail;
    }

}
