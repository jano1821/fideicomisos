package com.corfid.fideicomisos.model.administrativo;

import java.util.ArrayList;
import java.util.List;

public class RolModel {
	private Integer idRol;

	private String descripcion;

	private String estadoRegistro;

	private String descEstadoRegistro;

	private String menu;

	private List<MenuModel> listMenu = new ArrayList<MenuModel>();

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public List<MenuModel> getListMenu() {
		return listMenu;
	}

	public void setListMenu(List<MenuModel> listMenu) {
		this.listMenu = listMenu;
	}

}
