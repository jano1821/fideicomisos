package com.corfid.fideicomisos.entity.banco;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FideicomisoFideicomisarioId implements java.io.Serializable {

	private static final long serialVersionUID = -8687655040073456600L;

	@Column(name = "n_idfico", nullable = false, insertable = true, updatable = true)
	private Integer identificadorFideicomiso;

	@Column(name = "n_idfisa", nullable = false, insertable = true, updatable = true)
	private Integer identificadorFideicomisario;

	public Integer getIdentificadorFideicomiso() {
		return identificadorFideicomiso;
	}

	public void setIdentificadorFideicomiso(Integer identificadorFideicomiso) {
		this.identificadorFideicomiso = identificadorFideicomiso;
	}

	public Integer getIdentificadorFideicomisario() {
		return identificadorFideicomisario;
	}

	public void setIdentificadorFideicomisario(Integer identificadorFideicomisario) {
		this.identificadorFideicomisario = identificadorFideicomisario;
	}
	
	public FideicomisoFideicomisarioId(Integer identificadorFideicomiso, Integer identificadorFideicomisario) {
		super();
		this.identificadorFideicomiso = identificadorFideicomiso;
		this.identificadorFideicomisario = identificadorFideicomisario;
	}
	
	public FideicomisoFideicomisarioId() {

	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FideicomisoFideicomisarioId))
			return false;
		FideicomisoFideicomisarioId castOther = (FideicomisoFideicomisarioId) other;

		return ((this.getIdentificadorFideicomiso() == castOther.getIdentificadorFideicomiso())
				|| (this.getIdentificadorFideicomiso() != null && castOther.getIdentificadorFideicomiso() != null
						&& this.getIdentificadorFideicomiso().equals(castOther.getIdentificadorFideicomiso())))
				&& ((this.getIdentificadorFideicomisario() == castOther.getIdentificadorFideicomisario())
						|| (this.getIdentificadorFideicomisario() != null
								&& castOther.getIdentificadorFideicomisario() != null
								&& this.getIdentificadorFideicomisario()
										.equals(castOther.getIdentificadorFideicomisario())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIdentificadorFideicomiso() == null ? 0 : this.getIdentificadorFideicomiso().hashCode());
		result = 37 * result
				+ (getIdentificadorFideicomisario() == null ? 0 : this.getIdentificadorFideicomisario().hashCode());
		return result;
	}

}
