package com.corfid.fideicomisos.model.administrativo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.corfid.fideicomisos.model.utilities.GenericModel;

public class UsuarioModel extends GenericModel {

    private Integer idUsuario;
    private String usuario;
    private String password;
    private String tipoUsuario;
    private Integer idPersona;
    private Boolean estadoActividadUsuario;
    private String estadoActividad;
    private String descEstadoActividad;
    private String descTipoUsuario;
    private String estadoRegistro;
    private String descEstadoRegistro;
    private Integer idUsuarioRegistro;
    private String cambiar;
    private String generar;
    private String persona;
    private String rol;
    private List<RolModel> listRoles = new ArrayList<RolModel>();

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Boolean isEstadoActividadUsuario() {
        return estadoActividadUsuario;
    }

    public void setEstadoActividadUsuario(Boolean estadoActividadUsuario) {
        this.estadoActividadUsuario = estadoActividadUsuario;
    }

    public String getEstadoActividad() {
        return estadoActividad;
    }

    public void setEstadoActividad(String estadoActividad) {
        this.estadoActividad = estadoActividad;
    }

    public String getDescEstadoActividad() {
        return descEstadoActividad;
    }

    public void setDescEstadoActividad(String descEstadoActividad) {
        this.descEstadoActividad = descEstadoActividad;
    }

    public String getDescTipoUsuario() {
        return descTipoUsuario;
    }

    public void setDescTipoUsuario(String descTipoUsuario) {
        this.descTipoUsuario = descTipoUsuario;
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

    public Integer getIdUsuarioRegistro() {
        return idUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(Integer idUsuarioRegistro) {
        this.idUsuarioRegistro = idUsuarioRegistro;
    }

    public String getCambiar() {
        return cambiar;
    }

    public void setCambiar(String cambiar) {
        this.cambiar = cambiar;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getGenerar() {
        return generar;
    }

    public void setGenerar(String generar) {
        this.generar = generar;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Boolean getEstadoActividadUsuario() {
        return estadoActividadUsuario;
    }

    public List<RolModel> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<RolModel> listRoles) {
        this.listRoles = listRoles;
    }

}
