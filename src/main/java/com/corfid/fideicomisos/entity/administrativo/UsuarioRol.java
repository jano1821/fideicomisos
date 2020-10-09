package com.corfid.fideicomisos.entity.administrativo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admusrol")
public class UsuarioRol extends Auditoria {

    @EmbeddedId
    private UsuarioRolId usuarioRolId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("idUsuario")
    @JoinColumn(name = "n_idusua", nullable = false, insertable = false, updatable = false)
    private Usuario usuario;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("idRol")
    @JoinColumn(name = "n_iderol", nullable = false, insertable = false, updatable = false)
    private Rol rol;

    public UsuarioRolId getUsuarioRolId() {
        return usuarioRolId;
    }

    public void setUsuarioRolId(UsuarioRolId usuarioRolId) {
        this.usuarioRolId = usuarioRolId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public UsuarioRol(UsuarioRolId usuarioRolId, Usuario usuario, Rol rol) {
        super();
        this.usuarioRolId = usuarioRolId;
        this.usuario = usuario;
        this.rol = rol;
    }

    public UsuarioRol() {

    }

}
