package com.corfid.fideicomisos.model.utilities;

public class ConstantesSistemaModel extends GenericModel {

    private Integer idConstraint;
    private String nombreConstraint;
    private String descripcion;
    private String valor;
    private String abrevConstraint;
    private String estadoRegistro;

    public Integer getIdConstraint() {
        return idConstraint;
    }

    public void setIdConstraint(Integer idConstraint) {
        this.idConstraint = idConstraint;
    }

    public String getNombreConstraint() {
        return nombreConstraint;
    }

    public void setNombreConstraint(String nombreConstraint) {
        this.nombreConstraint = nombreConstraint;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getAbrevConstraint() {
        return abrevConstraint;
    }

    public void setAbrevConstraint(String abrevConstraint) {
        this.abrevConstraint = abrevConstraint;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

}
