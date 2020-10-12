package com.corfid.fideicomisos.model.banco;

import java.util.Date;

public class TipoCambioSBSModel {

	private Integer identificadorTipoCambioSBS;
	private Date fechaProceso;
	private Double montoTipoCambio;

	public Integer getIdentificadorTipoCambioSBS() {
		return identificadorTipoCambioSBS;
	}

	public void setIdentificadorTipoCambioSBS(Integer identificadorTipoCambioSBS) {
		this.identificadorTipoCambioSBS = identificadorTipoCambioSBS;
	}

	public Date getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public Double getMontoTipoCambio() {
		return montoTipoCambio;
	}

	public void setMontoTipoCambio(Double montoTipoCambio) {
		this.montoTipoCambio = montoTipoCambio;
	}

}
