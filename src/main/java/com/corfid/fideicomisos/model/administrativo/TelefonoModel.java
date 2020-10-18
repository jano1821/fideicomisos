package com.corfid.fideicomisos.model.administrativo;

import com.corfid.fideicomisos.entity.administrativo.Persona;
import com.corfid.fideicomisos.model.utilities.GenericModel;

public class TelefonoModel extends GenericModel {

    private Integer idTelefono;
    private String numero;
    private Persona persona;
    private String operador;
    private Integer idPersona;
    private Integer idOperador;
    private String descOperador;
    private String estadoRegistro;
    private String descEstadoRegistro;

    public Integer getIdTelefono() {
        return idTelefono;
    }

    public void setIdTelefono(Integer idTelefono) {
        this.idTelefono = idTelefono;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(Integer idOperador) {
        this.idOperador = idOperador;
    }

    public String getDescOperador() {
        return descOperador;
    }

    public void setDescOperador(String descOperador) {
        this.descOperador = descOperador;
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

}
