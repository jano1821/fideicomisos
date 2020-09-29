package com.corfid.fideicomisos.model.cruds;

import java.util.ArrayList;
import java.util.List;

import com.corfid.fideicomisos.model.administrativo.PersonaModel;
import com.corfid.fideicomisos.model.utilities.CrudModel;

public class CrudPersonaModel extends CrudModel {
	private List<PersonaModel> rows = new ArrayList<PersonaModel>();
	private String busqueda;

	public List<PersonaModel> getRows() {
		return rows;
	}

	public void setRows(List<PersonaModel> rows) {
		this.rows = rows;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

}
