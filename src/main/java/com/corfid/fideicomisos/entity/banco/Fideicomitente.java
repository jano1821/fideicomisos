package com.corfid.fideicomisos.entity.banco;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "banmfite")
public class Fideicomitente extends Auditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_idfite", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer identificadorFideicomitente;

	@Column(name = "c_nomfit", nullable = false, insertable = true, updatable = true, length = 100)
	private String nombreFideicomitente;

	@Column(name = "c_tipdoc", nullable = false, insertable = true, updatable = true, length = 6)
	private String tipoDocumento;

	@Column(name = "c_numdoc", nullable = false, insertable = true, updatable = true, length = 15)
	private String numeroDocumento;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fideicomitente")
	private List<FideicomisoFideicomitente> lstFideicomisoFideicomitente = new ArrayList<FideicomisoFideicomitente>(0);

	public Integer getIdentificadorFideicomitente() {
		return identificadorFideicomitente;
	}

	public void setIdentificadorFideicomitente(Integer identificadorFideicomitente) {
		this.identificadorFideicomitente = identificadorFideicomitente;
	}

	public String getNombreFideicomitente() {
		return nombreFideicomitente;
	}

	public void setNombreFideicomitente(String nombreFideicomitente) {
		this.nombreFideicomitente = nombreFideicomitente;
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

	public Fideicomitente(Integer identificadorFideicomitente, String nombreFideicomitente, String tipoDocumento,
			String numeroDocumento) {
		super();
		this.identificadorFideicomitente = identificadorFideicomitente;
		this.nombreFideicomitente = nombreFideicomitente;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
	}

	public Fideicomitente() {

	}
}
