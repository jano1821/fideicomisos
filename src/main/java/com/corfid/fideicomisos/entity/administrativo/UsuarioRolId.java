package com.corfid.fideicomisos.entity.administrativo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UsuarioRolId implements java.io.Serializable {
	private static final long serialVersionUID = 4268303814130344164L;

	@Column(name = "n_idusua", nullable = false, insertable = true, updatable = true)
	private Integer idUsuario;

	@Column(name = "n_iderol", nullable = false, insertable = true, updatable = true)
	private Integer idRol;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public UsuarioRolId(Integer idUsuario, Integer idRol) {
		super();
		this.idUsuario = idUsuario;
		this.idRol = idRol;
	}

	public UsuarioRolId() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRol == null) ? 0 : idRol.hashCode());
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioRolId other = (UsuarioRolId) obj;
		if (idRol == null) {
			if (other.idRol != null)
				return false;
		} else if (!idRol.equals(other.idRol))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}

}
