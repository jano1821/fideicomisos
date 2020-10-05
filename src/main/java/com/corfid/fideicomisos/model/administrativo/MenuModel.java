package com.corfid.fideicomisos.model.administrativo;

public class MenuModel {

    private Integer idMenu;

    private String descripcion;

    private String url;

    private String tipoMenu;

    private String descTipoMenu;

    private Integer idMenuPadre;

    private String estadoRegistro;

    private String descEstadoRegistro;

    private String tipoUsuarioSesion;

    private Integer idUsuarioRegistro;

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTipoMenu() {
        return tipoMenu;
    }

    public void setTipoMenu(String tipoMenu) {
        this.tipoMenu = tipoMenu;
    }

    public String getDescTipoMenu() {
        return descTipoMenu;
    }

    public void setDescTipoMenu(String descTipoMenu) {
        this.descTipoMenu = descTipoMenu;
    }

    public Integer getIdMenuPadre() {
        return idMenuPadre;
    }

    public void setIdMenuPadre(Integer idMenuPadre) {
        this.idMenuPadre = idMenuPadre;
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

    public String getTipoUsuarioSesion() {
        return tipoUsuarioSesion;
    }

    public void setTipoUsuarioSesion(String tipoUsuarioSesion) {
        this.tipoUsuarioSesion = tipoUsuarioSesion;
    }

    public Integer getIdUsuarioRegistro() {
        return idUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(Integer idUsuarioRegistro) {
        this.idUsuarioRegistro = idUsuarioRegistro;
    }

}
