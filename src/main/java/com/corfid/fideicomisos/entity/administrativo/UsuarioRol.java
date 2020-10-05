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

    /*
     * @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     * @MapsId("idUsuario")
     * @JoinColumn(name = "n_idusua", nullable = false, insertable = false, updatable = false) private Usuario usuario;
     * @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     * @MapsId("idRol")
     * @JoinColumn(name = "n_iderol", nullable = false, insertable = false, updatable = false) private Rol rol;
     */
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
