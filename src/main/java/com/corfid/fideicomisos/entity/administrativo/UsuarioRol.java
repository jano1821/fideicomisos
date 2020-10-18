package com.corfid.fideicomisos.entity.administrativo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admusrol")
public class UsuarioRol extends Auditoria {

    @EmbeddedId
    private UsuarioRolId usuarioRolId;

    public UsuarioRolId getUsuarioRolId() {
        return usuarioRolId;
    }

    public void setUsuarioRolId(UsuarioRolId usuarioRolId) {
        this.usuarioRolId = usuarioRolId;
    }

    public UsuarioRol(UsuarioRolId usuarioRolId) {
        super();
        this.usuarioRolId = usuarioRolId;
    }

    public UsuarioRol() {

    }

}
