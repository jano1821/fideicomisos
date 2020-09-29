package com.corfid.fideicomisos.entity.banco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "banmfisa")
public class Fideicomisario extends Auditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_idfisa", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer identificadorFideicomisario;

	@Column(name = "c_nomfis", nullable = false, insertable = true, updatable = true, length = 100)
	private String nombreFideicomisario;

	@Column(name = "c_tipdoc", nullable = false, insertable = true, updatable = true, length = 6)
	private String tipoDocumento;

	@Column(name = "c_numdoc", nullable = false, insertable = true, updatable = true, length = 11)
	private String numeroDocumento;

	public Integer getIdentificadorFideicomisario() {
		return identificadorFideicomisario;
	}

	public void setIdentificadorFideicomisario(Integer identificadorFideicomisario) {
		this.identificadorFideicomisario = identificadorFideicomisario;
	}

	public String getNombreFideicomisario() {
		return nombreFideicomisario;
	}

	public void setNombreFideicomisario(String nombreFideicomisario) {
		this.nombreFideicomisario = nombreFideicomisario;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Fideicomisario(Integer identificadorFideicomisario, String nombreFideicomisario, String tipoDocumento,
			String numeroDocumento) {
		super();
		this.identificadorFideicomisario = identificadorFideicomisario;
		this.nombreFideicomisario = nombreFideicomisario;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
	}

	public Fideicomisario() {

	}

}
