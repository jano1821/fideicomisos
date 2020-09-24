package com.corfid.fideicomisos.model.utilities;

public class CrudModel {
	protected String id;
	protected String descripcion;
	protected String usuario;
	protected String tipo;
	protected String acciones;
	protected String result;
	protected Integer paginaActual;
	protected Integer paginaFinal;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAcciones() {
		return acciones;
	}

	public void setAcciones(String acciones) {
		this.acciones = acciones;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(Integer paginaActual) {
		this.paginaActual = paginaActual;
	}

	public Integer getPaginaFinal() {
		return paginaFinal;
	}

	public void setPaginaFinal(Integer paginaFinal) {
		this.paginaFinal = paginaFinal;
	}

}
