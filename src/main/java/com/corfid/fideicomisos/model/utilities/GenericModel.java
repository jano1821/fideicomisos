package com.corfid.fideicomisos.model.utilities;

public class GenericModel {

    private String id;
    private String descripcion;
    private String codigoError;
    private String descripcionError;
    private String tipoUsuarioSesion;
    private String edicion;
    private Integer idEmpresaSesion;
    private Integer idUsuarioSesion;
    private String rucEmpresaSesion;
    private String nombreEmpresaSesion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

    public String getTipoUsuarioSesion() {
        return tipoUsuarioSesion;
    }

    public void setTipoUsuarioSesion(String tipoUsuarioSesion) {
        this.tipoUsuarioSesion = tipoUsuarioSesion;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public Integer getIdEmpresaSesion() {
        return idEmpresaSesion;
    }

    public void setIdEmpresaSesion(Integer idEmpresaSesion) {
        this.idEmpresaSesion = idEmpresaSesion;
    }

    public Integer getIdUsuarioSesion() {
        return idUsuarioSesion;
    }

    public void setIdUsuarioSesion(Integer idUsuarioSesion) {
        this.idUsuarioSesion = idUsuarioSesion;
    }

    public String getRucEmpresaSesion() {
        return rucEmpresaSesion;
    }

    public void setRucEmpresaSesion(String rucEmpresaSesion) {
        this.rucEmpresaSesion = rucEmpresaSesion;
    }

    public String getNombreEmpresaSesion() {
        return nombreEmpresaSesion;
    }

    public void setNombreEmpresaSesion(String nombreEmpresaSesion) {
        this.nombreEmpresaSesion = nombreEmpresaSesion;
    }

}
