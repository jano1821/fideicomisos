package com.corfid.fideicomisos.model.banco;

public class DetalleMovCuentaEntidadFinancieraModel {

	private Integer identificadorDetalleMovCuentaFinanciera;
	private Integer correlativoDetalleMovimiento;
	private String descripcionDetalleMovimiento;
	private Double montoMovimiento;

	public Integer getIdentificadorDetalleMovCuentaFinanciera() {
		return identificadorDetalleMovCuentaFinanciera;
	}

	public void setIdentificadorDetalleMovCuentaFinanciera(Integer identificadorDetalleMovCuentaFinanciera) {
		this.identificadorDetalleMovCuentaFinanciera = identificadorDetalleMovCuentaFinanciera;
	}

	public Integer getCorrelativoDetalleMovimiento() {
		return correlativoDetalleMovimiento;
	}

	public void setCorrelativoDetalleMovimiento(Integer correlativoDetalleMovimiento) {
		this.correlativoDetalleMovimiento = correlativoDetalleMovimiento;
	}

	public String getDescripcionDetalleMovimiento() {
		return descripcionDetalleMovimiento;
	}

	public void setDescripcionDetalleMovimiento(String descripcionDetalleMovimiento) {
		this.descripcionDetalleMovimiento = descripcionDetalleMovimiento;
	}

	public Double getMontoMovimiento() {
		return montoMovimiento;
	}

	public void setMontoMovimiento(Double montoMovimiento) {
		this.montoMovimiento = montoMovimiento;
	}

}
