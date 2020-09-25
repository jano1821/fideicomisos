package com.corfid.fideicomisos.model.utilities;

public class PaginadoModel {
	private Integer paginaActual;
	private Integer paginaFinal;
	private boolean movIzquierda;
	private boolean movDerecha;

	public boolean isMovIzquierda() {
		return movIzquierda;
	}

	public void setMovIzquierda(boolean movIzquierda) {
		this.movIzquierda = movIzquierda;
	}

	public boolean isMovDerecha() {
		return movDerecha;
	}

	public void setMovDerecha(boolean movDerecha) {
		this.movDerecha = movDerecha;
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

	public PaginadoModel(Integer paginaActual, Integer paginaFinal, boolean movIzquierda, boolean movDerecha) {
		super();
		this.paginaActual = paginaActual;
		this.paginaFinal = paginaFinal;
		this.movIzquierda = movIzquierda;
		this.movDerecha = movDerecha;
	}

	public PaginadoModel() {

	}
}
