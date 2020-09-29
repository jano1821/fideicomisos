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
@Table(name = "banmfico")
public class Fideicomiso extends Auditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_idfico", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer identificadorFideicomiso;

	@Column(name = "c_nomfid", nullable = false, insertable = true, updatable = true, length = 100)
	private String nombreFideicomiso;

	@Column(name = "c_codest", nullable = false, insertable = true, updatable = true, length = 2)
	private String codigoEstado;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fideicomiso")
	private List<CuentaEntidadFinanciera> lstCuentaEntidadFinanciera = new ArrayList<CuentaEntidadFinanciera>(0);

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fideicomiso")
	private List<FideicomisoFideicomitente> lstFideicomisoFideicomitente = new ArrayList<FideicomisoFideicomitente>(0);

	public Integer getIdentificadorFideicomiso() {
		return identificadorFideicomiso;
	}

	public void setIdentificadorFideicomiso(Integer identificadorFideicomiso) {
		this.identificadorFideicomiso = identificadorFideicomiso;
	}

	public String getNombreFideicomiso() {
		return nombreFideicomiso;
	}

	public void setNombreFideicomiso(String nombreFideicomiso) {
		this.nombreFideicomiso = nombreFideicomiso;
	}

	public String getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public List<CuentaEntidadFinanciera> getLstCuentaEntidadFinanciera() {
		return lstCuentaEntidadFinanciera;
	}

	public void setLstCuentaEntidadFinanciera(List<CuentaEntidadFinanciera> lstCuentaEntidadFinanciera) {
		this.lstCuentaEntidadFinanciera = lstCuentaEntidadFinanciera;
	}

	public Fideicomiso(Integer identificadorFideicomiso, String nombreFideicomiso, String codigoEstado,
			List<CuentaEntidadFinanciera> lstCuentaEntidadFinanciera) {
		super();
		this.identificadorFideicomiso = identificadorFideicomiso;
		this.nombreFideicomiso = nombreFideicomiso;
		this.codigoEstado = codigoEstado;
		this.lstCuentaEntidadFinanciera = lstCuentaEntidadFinanciera;
	}

	public Fideicomiso() {

	}
}
