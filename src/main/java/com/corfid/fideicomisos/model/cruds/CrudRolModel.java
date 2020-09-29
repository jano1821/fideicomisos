package com.corfid.fideicomisos.model.cruds;

import java.util.ArrayList;
import java.util.List;

import com.corfid.fideicomisos.model.administrativo.RolModel;
import com.corfid.fideicomisos.model.utilities.CrudModel;

public class CrudRolModel extends CrudModel{
	public List<RolModel> rows = new ArrayList<RolModel>();
	public String busqueda;

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public List<RolModel> getRows() {
		return rows;
	}

	public void setRows(List<RolModel> rows) {
		this.rows = rows;
	}

}
