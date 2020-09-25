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

@Entity
@Table(name = "bandfifo")
public class FideicomisoFideicomisario {

	public FideicomisoFideicomisario() {

	}

	public FideicomisoFideicomisario(FideicomisoFideicomisarioId fideicomisoFideicomisarioId, String codigoEstado) {
		super();
		this.fideicomisoFideicomisarioId = fideicomisoFideicomisarioId;
		this.codigoEstado = codigoEstado;
	}

	@EmbeddedId
	private FideicomisoFideicomisarioId fideicomisoFideicomisarioId;

	@Column(name = "c_codest", nullable = false, insertable = true, updatable = true)
	private String codigoEstado;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@MapsId("identificadorFideicomiso")
	@JoinColumn(name = "n_idfico", nullable = false, insertable = false, updatable = false)
	private Fideicomiso fideicomiso;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@MapsId("identificadorFideicomisario")
	@JoinColumn(name = "n_idfisa", nullable = false, insertable = false, updatable = false)
	private Fideicomisario fideicomisario;

	public FideicomisoFideicomisarioId getFideicomisoFideicomisarioId() {
		return fideicomisoFideicomisarioId;
	}

	public void setFideicomisoFideicomisarioId(FideicomisoFideicomisarioId fideicomisoFideicomisarioId) {
		this.fideicomisoFideicomisarioId = fideicomisoFideicomisarioId;
	}

	public String getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

}
