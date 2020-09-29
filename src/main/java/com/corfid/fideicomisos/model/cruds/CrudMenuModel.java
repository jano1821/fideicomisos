package com.corfid.fideicomisos.model.cruds;

import java.util.ArrayList;
import java.util.List;

import com.corfid.fideicomisos.model.administrativo.MenuModel;
import com.corfid.fideicomisos.model.utilities.CrudModel;

public class CrudMenuModel extends CrudModel {
	private List<MenuModel> rows = new ArrayList<MenuModel>();
	private String busqueda;

	public List<MenuModel> getRows() {
		return rows;
	}

	public void setRows(List<MenuModel> rows) {
		this.rows = rows;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

}
