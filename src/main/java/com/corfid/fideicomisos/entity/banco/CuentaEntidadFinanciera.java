package com.corfid.fideicomisos.entity.banco;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "banmcufi")
public class CuentaEntidadFinanciera extends Auditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_idcufi", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer identificadorCuentaEntidadFinanciera;

	@Column(name = "c_numcta", nullable = false, insertable = true, updatable = true, length = 50)
	private String numeroCuentaEntidadFinanciera;

	@Column(name = "c_desenf", nullable = false, insertable = true, updatable = true, length = 100)
	private String nombreEntidadFinanciera;

	@Column(name = "c_acoenf", nullable = false, insertable = true, updatable = true, length = 10)
	private String acronimoEntidadFinanciera;

	@Column(name = "c_destef", nullable = false, insertable = true, updatable = true, length = 25)
	private String tipoEntidadFinanciera;

	@Column(name = "c_descef", nullable = false, insertable = true, updatable = true, length = 100)
	private String descripcionCuentaEntidadFinanciera;

	@Column(name = "c_codmon", nullable = false, insertable = true, updatable = true, length = 1)
	private String codigoMoneda;

	@Column(name = "c_isomon", nullable = false, insertable = true, updatable = true, length = 3)
	private String isoMonedaCuentaEntidadFinanciera;

	@Column(name = "c_desmon", nullable = false, insertable = true, updatable = true, length = 8)
	private String descripcionMonedaCuentaEntidadFinanciera;

	@Column(name = "n_salcon", nullable = false, insertable = true, updatable = true, precision = 12, scale = 2)
	private Double saldoContableActual;

	@Column(name = "n_saldis", nullable = false, insertable = true, updatable = true, precision = 12, scale = 2)
	private Double saldoDisponibleActual;

	@Column(name = "c_codest", nullable = false, insertable = true, updatable = true, length = 2)
	private String codigoEstado;

	@Column(name = "c_desest", nullable = false, insertable = true, updatable = true, length = 20)
	private String descripcionEstado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "d_ulfeac", nullable = false, insertable = true, updatable = true, length = 7)
	private Date fechaUltimaActualizacion;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "n_idfico", nullable = false, insertable = true, updatable = true)
	private Fideicomiso fideicomiso;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentaEntidadFinanciera")
	private List<MovimientoCuentaEntidadFinanciera> lstMovimientoCuentaEntidadFinanciera = new ArrayList<MovimientoCuentaEntidadFinanciera>(
			0);

	public Integer getIdentificadorCuentaEntidadFinanciera() {
		return identificadorCuentaEntidadFinanciera;
	}

	public void setIdentificadorCuentaEntidadFinanciera(Integer identificadorCuentaEntidadFinanciera) {
		this.identificadorCuentaEntidadFinanciera = identificadorCuentaEntidadFinanciera;
	}

	public String getNumeroCuentaEntidadFinanciera() {
		return numeroCuentaEntidadFinanciera;
	}

	public void setNumeroCuentaEntidadFinanciera(String numeroCuentaEntidadFinanciera) {
		this.numeroCuentaEntidadFinanciera = numeroCuentaEntidadFinanciera;
	}

	public String getNombreEntidadFinanciera() {
		return nombreEntidadFinanciera;
	}

	public void setNombreEntidadFinanciera(String nombreEntidadFinanciera) {
		this.nombreEntidadFinanciera = nombreEntidadFinanciera;
	}

	public String getAcronimoEntidadFinanciera() {
		return acronimoEntidadFinanciera;
	}

	public void setAcronimoEntidadFinanciera(String acronimoEntidadFinanciera) {
		this.acronimoEntidadFinanciera = acronimoEntidadFinanciera;
	}

	public String getTipoEntidadFinanciera() {
		return tipoEntidadFinanciera;
	}

	public void setTipoEntidadFinanciera(String tipoEntidadFinanciera) {
		this.tipoEntidadFinanciera = tipoEntidadFinanciera;
	}

	public String getDescripcionCuentaEntidadFinanciera() {
		return descripcionCuentaEntidadFinanciera;
	}

	public void setDescripcionCuentaEntidadFinanciera(String descripcionCuentaEntidadFinanciera) {
		this.descripcionCuentaEntidadFinanciera = descripcionCuentaEntidadFinanciera;
	}

	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	public String getIsoMonedaCuentaEntidadFinanciera() {
		return isoMonedaCuentaEntidadFinanciera;
	}

	public void setIsoMonedaCuentaEntidadFinanciera(String isoMonedaCuentaEntidadFinanciera) {
		this.isoMonedaCuentaEntidadFinanciera = isoMonedaCuentaEntidadFinanciera;
	}

	public String getDescripcionMonedaCuentaEntidadFinanciera() {
		return descripcionMonedaCuentaEntidadFinanciera;
	}

	public void setDescripcionMonedaCuentaEntidadFinanciera(String descripcionMonedaCuentaEntidadFinanciera) {
		this.descripcionMonedaCuentaEntidadFinanciera = descripcionMonedaCuentaEntidadFinanciera;
	}

	public Double getSaldoContableActual() {
		return saldoContableActual;
	}

	public void setSaldoContableActual(Double saldoContableActual) {
		this.saldoContableActual = saldoContableActual;
	}

	public Double getSaldoDisponibleActual() {
		return saldoDisponibleActual;
	}

	public void setSaldoDisponibleActual(Double saldoDisponibleActual) {
		this.saldoDisponibleActual = saldoDisponibleActual;
	}

	public String getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	public Fideicomiso getFideicomiso() {
		return fideicomiso;
	}

	public void setFideicomiso(Fideicomiso fideicomiso) {
		this.fideicomiso = fideicomiso;
	}

	public List<MovimientoCuentaEntidadFinanciera> getLstMovimientoCuentaEntidadFinanciera() {
		return lstMovimientoCuentaEntidadFinanciera;
	}

	public void setLstMovimientoCuentaEntidadFinanciera(
			List<MovimientoCuentaEntidadFinanciera> lstMovimientoCuentaEntidadFinanciera) {
		this.lstMovimientoCuentaEntidadFinanciera = lstMovimientoCuentaEntidadFinanciera;
	}

	public CuentaEntidadFinanciera(Integer identificadorCuentaEntidadFinanciera, String numeroCuentaEntidadFinanciera,
			String nombreEntidadFinanciera, String acronimoEntidadFinanciera, String tipoEntidadFinanciera,
			String descripcionCuentaEntidadFinanciera, String codigoMoneda, String isoMonedaCuentaEntidadFinanciera,
			String descripcionMonedaCuentaEntidadFinanciera, Double saldoContableActual, Double saldoDisponibleActual,
			String codigoEstado, String descripcionEstado, Date fechaUltimaActualizacion, Fideicomiso fideicomiso,
			List<MovimientoCuentaEntidadFinanciera> lstMovimientoCuentaEntidadFinanciera) {
		super();
		this.identificadorCuentaEntidadFinanciera = identificadorCuentaEntidadFinanciera;
		this.numeroCuentaEntidadFinanciera = numeroCuentaEntidadFinanciera;
		this.nombreEntidadFinanciera = nombreEntidadFinanciera;
		this.acronimoEntidadFinanciera = acronimoEntidadFinanciera;
		this.tipoEntidadFinanciera = tipoEntidadFinanciera;
		this.descripcionCuentaEntidadFinanciera = descripcionCuentaEntidadFinanciera;
		this.codigoMoneda = codigoMoneda;
		this.isoMonedaCuentaEntidadFinanciera = isoMonedaCuentaEntidadFinanciera;
		this.descripcionMonedaCuentaEntidadFinanciera = descripcionMonedaCuentaEntidadFinanciera;
		this.saldoContableActual = saldoContableActual;
		this.saldoDisponibleActual = saldoDisponibleActual;
		this.codigoEstado = codigoEstado;
		this.descripcionEstado = descripcionEstado;
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
		this.fideicomiso = fideicomiso;
		this.lstMovimientoCuentaEntidadFinanciera = lstMovimientoCuentaEntidadFinanciera;
	}

	public CuentaEntidadFinanciera() {

	}
}
