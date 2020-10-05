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

	public Fideicomiso getFideicomiso() {
		return fideicomiso;
	}

	public void setFideicomiso(Fideicomiso fideicomiso) {
		this.fideicomiso = fideicomiso;
	}

	public Fideicomitente getFideicomitente() {
		return fideicomitente;
	}

	public void setFideicomitente(Fideicomitente fideicomitente) {
		this.fideicomitente = fideicomitente;
	}

	public FideicomisoFideicomitente(FideicomisoFideicomitenteId fideicomisoFideicomitenteId, Fideicomiso fideicomiso,
			Fideicomitente fideicomitente) {
		super();
		this.fideicomisoFideicomitenteId = fideicomisoFideicomitenteId;
		this.fideicomiso = fideicomiso;
		this.fideicomitente = fideicomitente;
	}

	public FideicomisoFideicomitente() {

	}
}
