package com.corfid.fideicomisos.entity.administrativo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admmoem")
public class CatalogoMail extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "n_idmoem", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
    private Integer idCorreo;

    @Column(name = "c_asunto", nullable = false, length = 250, insertable = true, updatable = true)
    private String asunto;

    @Column(name = "c_conten", nullable = false, length = 4000, insertable = true, updatable = true)
    private String contenido;

    @Column(name = "c_codema", nullable = false, length = 45, insertable = true, updatable = true)
    private String codigo;

    public Integer getIdCorreo() {
        return idCorreo;
    }

    public void setIdCorreo(Integer idCorreo) {
        this.idCorreo = idCorreo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public CatalogoMail(Integer idCorreo, String asunto, String contenido, String codigo) {
        super();
        this.idCorreo = idCorreo;
        this.asunto = asunto;
        this.contenido = contenido;
        this.codigo = codigo;
    }

    public CatalogoMail() {
    }
}
