package com.corfid.fideicomisos.entity.banco;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FideicomisoFideicomitenteId implements java.io.Serializable{

	public FideicomisoFideicomitenteId() {

	}	
	
	public FideicomisoFideicomitenteId(Integer identificadorFideicomiso, Integer identificadorFideicomitente) {
		super();
		this.identificadorFideicomiso = identificadorFideicomiso;
		this.identificadorFideicomitente = identificadorFideicomitente;
	}

	@Column(name = "n_idfico", nullable = false, insertable = true, updatable = true)
	private Integer identificadorFideicomiso;
	
	@Column(name = "n_idfite", nullable = false, insertable = true, updatable = true)
	private Integer identificadorFideicomitente;

	public Integer getIdentificadorFideicomiso() {
		return identificadorFideicomiso;
	}

	public void setIdentificadorFideicomiso(Integer identificadorFideicomiso) {
		this.identificadorFideicomiso = identificadorFideicomiso;
	}

	public Integer getIdentificadorFideicomitente() {
		return identificadorFideicomitente;
	}

	public void setIdentificadorFideicomitente(Integer identificadorFideicomitente) {
		this.identificadorFideicomitente = identificadorFideicomitente;
	}
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FideicomisoFideicomitenteId))
			return false;
		FideicomisoFideicomitenteId castOther = (FideicomisoFideicomitenteId) other;

		return ((this.getIdentificadorFideicomiso() == castOther.getIdentificadorFideicomiso()) || (this
				.getIdentificadorFideicomiso() != null
				&& castOther.getIdentificadorFideicomiso() != null && this.getIdentificadorFideicomiso().equals(
				castOther.getIdentificadorFideicomiso())))
				&& ((this.getIdentificadorFideicomitente() == castOther.getIdentificadorFideicomitente()) || (this
						.getIdentificadorFideicomitente() != null
						&& castOther.getIdentificadorFideicomitente() != null && this.getIdentificadorFideicomitente()
						.equals(castOther.getIdentificadorFideicomitente())));
	}
	
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIdentificadorFideicomiso() == null ? 0 : this.getIdentificadorFideicomiso().hashCode());
		result = 37 * result
				+ (getIdentificadorFideicomitente() == null ? 0 : this.getIdentificadorFideicomitente().hashCode());
		return result;
	}

}
