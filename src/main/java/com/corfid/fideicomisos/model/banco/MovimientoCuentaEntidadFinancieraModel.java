package com.corfid.fideicomisos.model.banco;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovimientoCuentaEntidadFinancieraModel extends PaginacionGeneralModel {

	private Integer identificadorMovimientoCuentaEntidadFinanciera;
	private String descripcionMovimiento;
	private String abreviaturaDescripcionMovimiento;
	private String numeroOperacionMovimiento;
	private String descripcionOficinaMovimiento;
	private Date fechaProcesoMovimiento;
	private Date fechaValutaMovimiento;
	private Double montoCargoMovimiento;
	private Double montoAbonoMovimiento;

	public List<MovimientoCuentaEntidadFinancieraModel> rows = new ArrayList<MovimientoCuentaEntidadFinancieraModel>();

	public Integer getIdentificadorMovimientoCuentaEntidadFinanciera() {
		return identificadorMovimientoCuentaEntidadFinanciera;
	}

	public void setIdentificadorMovimientoCuentaEntidadFinanciera(
			Integer identificadorMovimientoCuentaEntidadFinanciera) {
		this.identificadorMovimientoCuentaEntidadFinanciera = identificadorMovimientoCuentaEntidadFinanciera;
	}

	public String getDescripcionMovimiento() {
		return descripcionMovimiento;
	}

	public void setDescripcionMovimiento(String descripcionMovimiento) {
		this.descripcionMovimiento = descripcionMovimiento;
	}

	public String getAbreviaturaDescripcionMovimiento() {
		return abreviaturaDescripcionMovimiento;
	}

	public void setAbreviaturaDescripcionMovimiento(String abreviaturaDescripcionMovimiento) {
		this.abreviaturaDescripcionMovimiento = abreviaturaDescripcionMovimiento;
	}

	public String getNumeroOperacionMovimiento() {
		return numeroOperacionMovimiento;
	}

	public void setNumeroOperacionMovimiento(String numeroOperacionMovimiento) {
		this.numeroOperacionMovimiento = numeroOperacionMovimiento;
	}

	public String getDescripcionOficinaMovimiento() {
		return descripcionOficinaMovimiento;
	}

	public void setDescripcionOficinaMovimiento(String descripcionOficinaMovimiento) {
		this.descripcionOficinaMovimiento = descripcionOficinaMovimiento;
	}

	public Date getFechaProcesoMovimiento() {
		return fechaProcesoMovimiento;
	}

	public void setFechaProcesoMovimiento(Date fechaProcesoMovimiento) {
		this.fechaProcesoMovimiento = fechaProcesoMovimiento;
	}

	public Date getFechaValutaMovimiento() {
		return fechaValutaMovimiento;
	}

	public void setFechaValutaMovimiento(Date fechaValutaMovimiento) {
		this.fechaValutaMovimiento = fechaValutaMovimiento;
	}

	public Double getMontoCargoMovimiento() {
		return montoCargoMovimiento;
	}

	public void setMontoCargoMovimiento(Double montoCargoMovimiento) {
		this.montoCargoMovimiento = montoCargoMovimiento;
	}

	public Double getMontoAbonoMovimiento() {
		return montoAbonoMovimiento;
	}

	public void setMontoAbonoMovimiento(Double montoAbonoMovimiento) {
		this.montoAbonoMovimiento = montoAbonoMovimiento;
	}

	public List<MovimientoCuentaEntidadFinancieraModel> getRows() {
		return rows;
	}

	public void setRows(List<MovimientoCuentaEntidadFinancieraModel> rows) {
		this.rows = rows;
	}

}
