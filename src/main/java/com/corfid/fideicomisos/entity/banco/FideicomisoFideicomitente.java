package com.corfid.fideicomisos.entity.banco;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "bandfife")
public class FideicomisoFideicomitente extends Auditoria {

	@EmbeddedId
	private FideicomisoFideicomitenteId fideicomisoFideicomitenteId;

	@Column(name = "c_codest", nullable = false, insertable = true, updatable = true)
	private String codigoEstado;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@MapsId("identificadorFideicomiso")
	@JoinColumn(name = "n_idfico", nullable = false, insertable = false, updatable = false)
	private Fideicomiso fideicomiso;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@MapsId("identificadorFideicomitente")
	@JoinColumn(name = "n_idfite", nullable = false, insertable = false, updatable = false)
	private Fideicomitente fideicomitente;

	public FideicomisoFideicomitenteId getFideicomisoFideicomitenteId() {
		return fideicomisoFideicomitenteId;
	}

	public void setFideicomisoFideicomitenteId(FideicomisoFideicomitenteId fideicomisoFideicomitenteId) {
		this.fideicomisoFideicomitenteId = fideicomisoFideicomitenteId;
	}

	public String getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public FideicomisoFideicomitente(FideicomisoFideicomitenteId fideicomisoFideicomitenteId, String codigoEstado) {
		super();
		this.fideicomisoFideicomitenteId = fideicomisoFideicomitenteId;
		this.codigoEstado = codigoEstado;
	}

	public FideicomisoFideicomitente() {

	}

}
