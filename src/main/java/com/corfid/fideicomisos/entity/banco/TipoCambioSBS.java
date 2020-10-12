package com.corfid.fideicomisos.entity.banco;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "banmtica")
public class TipoCambioSBS extends Auditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_idtica", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer identificadorTipoCambioSBS;

	@Temporal(TemporalType.DATE)
	@Column(name = "d_fecpro", nullable = false, insertable = true, updatable = true, length = 7)
	private Date fechaProceso;

	@Column(name = "n_tipcam", nullable = false, insertable = true, updatable = true, precision = 8, scale = 4)
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

	public TipoCambioSBS(Integer identificadorTipoCambioSBS, Date fechaProceso, Double montoTipoCambio) {
		super();
		this.identificadorTipoCambioSBS = identificadorTipoCambioSBS;
		this.fechaProceso = fechaProceso;
		this.montoTipoCambio = montoTipoCambio;
	}

	public TipoCambioSBS() {

	}

}
