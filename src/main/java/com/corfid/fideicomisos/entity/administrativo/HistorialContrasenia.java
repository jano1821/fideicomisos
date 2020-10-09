package com.corfid.fideicomisos.entity.administrativo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "admhusua")
public class HistorialContrasenia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "n_idhist", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
    private Integer idHistorial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "n_idusua", nullable = false, insertable = true, updatable = true)
    private Usuario usuario;

    @Column(name = "c_contra", nullable = false, length = 100, insertable = true, updatable = true)
    private String password;

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HistorialContrasenia(Integer idHistorial, Usuario usuario, String password) {
        super();
        this.idHistorial = idHistorial;
        this.usuario = usuario;
        this.password = password;
    }

    public HistorialContrasenia() {

    }

}
