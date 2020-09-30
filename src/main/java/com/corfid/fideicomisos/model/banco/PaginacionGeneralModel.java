package com.corfid.fideicomisos.model.banco;

import java.util.ArrayList;
import java.util.List;

public class PaginacionGeneralModel {

	private Integer paginaActual;
	private Integer paginaFinal;
	private Integer cantidadRegistros;

	public List<PosicionBancoModel> rows = new ArrayList<PosicionBancoModel>();

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

	public Integer getCantidadRegistros() {
		return cantidadRegistros;
	}

	public void setCantidadRegistros(Integer cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}

	public List<PosicionBancoModel> getRows() {
		return rows;
	}

	public void setRows(List<PosicionBancoModel> rows) {
		this.rows = rows;
	}

}
