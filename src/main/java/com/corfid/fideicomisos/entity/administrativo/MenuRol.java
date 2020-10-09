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
@Table(name = "admmerol")
public class MenuRol extends Auditoria {
	@EmbeddedId
	private MenuRolId menuRolId;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@MapsId("idMenu")
	@JoinColumn(name = "n_idmenu", nullable = false, insertable = false, updatable = false)
	private Menu menu;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@MapsId("idRol")
	@JoinColumn(name = "n_iderol", nullable = false, insertable = false, updatable = false)
	private Rol rol;

	public MenuRolId getMenuRolId() {
		return menuRolId;
	}

	public void setMenuRolId(MenuRolId menuRolId) {
		this.menuRolId = menuRolId;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public MenuRol(MenuRolId menuRolId, Menu menu, Rol rol) {
		super();
		this.menuRolId = menuRolId;
		this.menu = menu;
		this.rol = rol;
	}

	public MenuRol() {

	}
}
