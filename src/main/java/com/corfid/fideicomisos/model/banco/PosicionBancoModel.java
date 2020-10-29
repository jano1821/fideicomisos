package com.corfid.fideicomisos.model.banco;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PosicionBancoModel extends PaginacionGeneralModel {

	private Integer identificadorFideicomiso;
	private Integer identificadorCuentaEntidadFinanciera;
	private String nombreFideicomisario;
	private String nombreFideicomiso;
	private String numeroCuentaEntidadFinanciera;
	private String nombreEntidadFinanciera;
	private String acronimoEntidadFinanciera;
	private String isoMonedaCuentaEntidadFinanciera;
	private String descripcionMonedaCuentaEntidadFinanciera;
	private String descripcionCuentaEntidadFinanciera;
	private Double saldoContableActual;
	private Double saldoDisponibleActual;
	private String fechaUltimaActualizacion;

	public List<PosicionBancoModel> rows = new ArrayList<PosicionBancoModel>();

	public Integer getIdentificadorFideicomiso() {
		return identificadorFideicomiso;
	}

	public void setIdentificadorFideicomiso(Integer identificadorFideicomiso) {
		this.identificadorFideicomiso = identificadorFideicomiso;
	}

	public Integer getIdentificadorCuentaEntidadFinanciera() {
		return identificadorCuentaEntidadFinanciera;
	}

	public void setIdentificadorCuentaEntidadFinanciera(Integer identificadorCuentaEntidadFinanciera) {
		this.identificadorCuentaEntidadFinanciera = identificadorCuentaEntidadFinanciera;
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

	public String getAcronimoEntidadFinanciera() {
		return acronimoEntidadFinanciera;
	}

	public void setAcronimoEntidadFinanciera(String acronimoEntidadFinanciera) {
		this.acronimoEntidadFinanciera = acronimoEntidadFinanciera;
	}

	public String getIsoMonedaCuentaEntidadFinanciera() {
		return isoMonedaCuentaEntidadFinanciera;
	}

	public void setIsoMonedaCuentaEntidadFinanciera(String isoMonedaCuentaEntidadFinanciera) {
		this.isoMonedaCuentaEntidadFinanciera = isoMonedaCuentaEntidadFinanciera;
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

	public String getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(String fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	public List<PosicionBancoModel> getRows() {
		return rows;
	}

	public void setRows(List<PosicionBancoModel> rows) {
		this.rows = rows;
	}

}
