package com.corfid.fideicomisos.entity.administrativo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MenuRolId implements java.io.Serializable {
	private static final long serialVersionUID = -7673176717403739137L;

	@Column(name = "n_idmenu", nullable = false, insertable = true, updatable = true)
	private Integer idMenu;

	@Column(name = "n_iderol", nullable = false, insertable = true, updatable = true)
	private Integer idRol;

	public Integer getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public MenuRolId(Integer idMenu, Integer idRol) {
		super();
		this.idMenu = idMenu;
		this.idRol = idRol;
	}

	public MenuRolId() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMenu == null) ? 0 : idMenu.hashCode());
		result = prime * result + ((idRol == null) ? 0 : idRol.hashCode());
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
		MenuRolId other = (MenuRolId) obj;
		if (idMenu == null) {
			if (other.idMenu != null)
				return false;
		} else if (!idMenu.equals(other.idMenu))
			return false;
		if (idRol == null) {
			if (other.idRol != null)
				return false;
		} else if (!idRol.equals(other.idRol))
			return false;
		return true;
	}

}
