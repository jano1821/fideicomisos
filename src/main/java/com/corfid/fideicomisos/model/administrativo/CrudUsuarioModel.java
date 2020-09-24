package com.corfid.fideicomisos.model.administrativo;

import java.util.ArrayList;
import java.util.List;

import com.corfid.fideicomisos.model.utilities.CrudModel;

public class CrudUsuarioModel extends CrudModel {

	public List<UsuarioModel> rows = new ArrayList<UsuarioModel>();
	public String busqueda;

	public List<UsuarioModel> getRows() {
		return rows;
	}

	public void setRows(List<UsuarioModel> listUsuarioModel) {
		this.rows.addAll(listUsuarioModel);
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public CrudUsuarioModel(String id, String descripcion, String usuario, String tipo, String acciones, String result,
			List<UsuarioModel> rows, String busqueda, Integer paginaActual, Integer paginaFinal) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.tipo = tipo;
		this.acciones = acciones;
		this.result = result;
		this.rows.addAll(rows);
		this.busqueda = busqueda;
		this.paginaActual = paginaActual;
		this.paginaFinal = paginaFinal;
	}

	public CrudUsuarioModel() {

	}
}
