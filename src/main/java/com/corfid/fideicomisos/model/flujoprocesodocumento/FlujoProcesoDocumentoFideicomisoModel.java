package com.corfid.fideicomisos.model.flujoprocesodocumento;

import java.util.ArrayList;
import java.util.List;

import com.corfid.fideicomisos.model.banco.PaginacionGeneralModel;

public class FlujoProcesoDocumentoFideicomisoModel extends PaginacionGeneralModel {

	private Integer identificadorFideicomiso;
	private String nombreFideicomisario;
	private String nombreFideicomiso;
	private String descripcionEstado;
	private Integer cantidadRegistrosConvenioRetribucion;
	private Integer cantidadRegistrosContratoFideicomiso;

	public List<FlujoProcesoDocumentoFideicomisoModel> rows = new ArrayList<FlujoProcesoDocumentoFideicomisoModel>();

	public Integer getIdentificadorFideicomiso() {
		return identificadorFideicomiso;
	}

	public void setIdentificadorFideicomiso(Integer identificadorFideicomiso) {
		this.identificadorFideicomiso = identificadorFideicomiso;
	}

	public String getNombreFideicomisario() {
		return nombreFideicomisario;
	}

	public void setNombreFideicomisario(String nombreFideicomisario) {
		this.nombreFideicomisario = nombreFideicomisario;
	}

	public String getNombreFideicomiso() {
		return nombreFideicomiso;
	}

	public void setNombreFideicomiso(String nombreFideicomiso) {
		this.nombreFideicomiso = nombreFideicomiso;
	}

	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	public Integer getCantidadRegistrosConvenioRetribucion() {
		return cantidadRegistrosConvenioRetribucion;
	}

	public void setCantidadRegistrosConvenioRetribucion(Integer cantidadRegistrosConvenioRetribucion) {
		this.cantidadRegistrosConvenioRetribucion = cantidadRegistrosConvenioRetribucion;
	}

	public Integer getCantidadRegistrosContratoFideicomiso() {
		return cantidadRegistrosContratoFideicomiso;
	}

	public void setCantidadRegistrosContratoFideicomiso(Integer cantidadRegistrosContratoFideicomiso) {
		this.cantidadRegistrosContratoFideicomiso = cantidadRegistrosContratoFideicomiso;
	}

	public List<FlujoProcesoDocumentoFideicomisoModel> getRows() {
		return rows;
	}

	public void setRows(List<FlujoProcesoDocumentoFideicomisoModel> rows) {
		this.rows = rows;
	}

}
