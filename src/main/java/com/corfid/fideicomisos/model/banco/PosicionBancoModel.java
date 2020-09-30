package com.corfid.fideicomisos.model.banco;

import java.util.Date;

public class PosicionBancoModel extends PaginacionGeneralModel{

	private Integer identificadorFideicomiso;
	private String nombreFideicomiso;
	private String numeroCuentaEntidadFinanciera;
	private String nombreEntidadFinanciera;
	private String descripcionMonedaCuentaEntidadFinanciera;
	private String descripcionCuentaEntidadFinanciera;
	private Double saldoContableActual;
	private Double saldoDisponibleActual;
	private Date fechaUltimaActualizacion;

	public Integer getIdentificadorFideicomiso() {
		return identificadorFideicomiso;
	}

	public void setIdentificadorFideicomiso(Integer identificadorFideicomiso) {
		this.identificadorFideicomiso = identificadorFideicomiso;
	}

	public String getNombreFideicomiso() {
		return nombreFideicomiso;
	}

	public void setNombreFideicomiso(String nombreFideicomiso) {
		this.nombreFideicomiso = nombreFideicomiso;
	}

	public String getNumeroCuentaEntidadFinanciera() {
		return numeroCuentaEntidadFinanciera;
	}

	public void setNumeroCuentaEntidadFinanciera(String numeroCuentaEntidadFinanciera) {
		this.numeroCuentaEntidadFinanciera = numeroCuentaEntidadFinanciera;
	}

	public String getNombreEntidadFinanciera() {
		return nombreEntidadFinanciera;
	}

	public void setNombreEntidadFinanciera(String nombreEntidadFinanciera) {
		this.nombreEntidadFinanciera = nombreEntidadFinanciera;
	}

	public String getDescripcionMonedaCuentaEntidadFinanciera() {
		return descripcionMonedaCuentaEntidadFinanciera;
	}

	public void setDescripcionMonedaCuentaEntidadFinanciera(String descripcionMonedaCuentaEntidadFinanciera) {
		this.descripcionMonedaCuentaEntidadFinanciera = descripcionMonedaCuentaEntidadFinanciera;
	}

	public String getDescripcionCuentaEntidadFinanciera() {
		return descripcionCuentaEntidadFinanciera;
	}

	public void setDescripcionCuentaEntidadFinanciera(String descripcionCuentaEntidadFinanciera) {
		this.descripcionCuentaEntidadFinanciera = descripcionCuentaEntidadFinanciera;
	}

	public Double getSaldoContableActual() {
		return saldoContableActual;
	}

	public void setSaldoContableActual(Double saldoContableActual) {
		this.saldoContableActual = saldoContableActual;
	}

	public Double getSaldoDisponibleActual() {
		return saldoDisponibleActual;
	}

	public void setSaldoDisponibleActual(Double saldoDisponibleActual) {
		this.saldoDisponibleActual = saldoDisponibleActual;
	}

	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

}
