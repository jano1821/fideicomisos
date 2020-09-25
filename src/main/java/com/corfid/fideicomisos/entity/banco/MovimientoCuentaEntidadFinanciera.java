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
@Table(name = "bandmvcf")
public class MovimientoCuentaEntidadFinanciera extends Auditoria {

	public MovimientoCuentaEntidadFinanciera() {

	}

	public MovimientoCuentaEntidadFinanciera(Integer identificadorMovimientoCuentaEntidadFinanciera,
			String descripcionMovimiento, String numeroOperacionMovimiento, String descripcionOficinaMovimiento,
			Date fechaProcesoMovimiento, Date fechaValutaMovimiento, Double montoCargoMovimiento,
			Double montoAbonoMovimiento, CuentaEntidadFinanciera cuentaEntidadFinanciera) {
		super();
		this.identificadorMovimientoCuentaEntidadFinanciera = identificadorMovimientoCuentaEntidadFinanciera;
		this.descripcionMovimiento = descripcionMovimiento;
		this.numeroOperacionMovimiento = numeroOperacionMovimiento;
		this.descripcionOficinaMovimiento = descripcionOficinaMovimiento;
		this.fechaProcesoMovimiento = fechaProcesoMovimiento;
		this.fechaValutaMovimiento = fechaValutaMovimiento;
		this.montoCargoMovimiento = montoCargoMovimiento;
		this.montoAbonoMovimiento = montoAbonoMovimiento;
		this.cuentaEntidadFinanciera = cuentaEntidadFinanciera;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_idmvcf", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer identificadorMovimientoCuentaEntidadFinanciera;

	@Column(name = "c_desmov", nullable = false, insertable = true, updatable = true, length = 50)
	private String descripcionMovimiento;

	@Column(name = "c_numope", nullable = false, insertable = true, updatable = true, length = 20)
	private String numeroOperacionMovimiento;

	@Column(name = "c_desofi", nullable = false, insertable = true, updatable = true, length = 50)
	private String descripcionOficinaMovimiento;

	@Temporal(TemporalType.DATE)
	@Column(name = "d_fecpro", nullable = false, insertable = true, updatable = true, length = 7)
	private Date fechaProcesoMovimiento;

	@Temporal(TemporalType.DATE)
	@Column(name = "d_fecval", nullable = false, insertable = true, updatable = true, length = 7)
	private Date fechaValutaMovimiento;

	@Column(name = "n_moncar", nullable = false, insertable = true, updatable = true, precision = 12, scale = 2)
	private Double montoCargoMovimiento;

	@Column(name = "n_monabo", nullable = false, insertable = true, updatable = true, precision = 12, scale = 2)
	private Double montoAbonoMovimiento;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "n_idcufi", nullable = false, insertable = true, updatable = true)
	private CuentaEntidadFinanciera cuentaEntidadFinanciera;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "movimientoCuentaEntidadFinanciera")
	private List<DetalleMovCuentaEntidadFinanciera> lstDetalleMovCuentaEntidadFinanciera = new ArrayList<DetalleMovCuentaEntidadFinanciera>(
			0);

	public Integer getIdentificadorMovimientoCuentaEntidadFinanciera() {
		return identificadorMovimientoCuentaEntidadFinanciera;
	}

	public void setIdentificadorMovimientoCuentaEntidadFinanciera(
			Integer identificadorMovimientoCuentaEntidadFinanciera) {
		this.identificadorMovimientoCuentaEntidadFinanciera = identificadorMovimientoCuentaEntidadFinanciera;
	}

	public String getDescripcionMovimiento() {
		return descripcionMovimiento;
	}

	public void setDescripcionMovimiento(String descripcionMovimiento) {
		this.descripcionMovimiento = descripcionMovimiento;
	}

	public String getNumeroOperacionMovimiento() {
		return numeroOperacionMovimiento;
	}

	public void setNumeroOperacionMovimiento(String numeroOperacionMovimiento) {
		this.numeroOperacionMovimiento = numeroOperacionMovimiento;
	}

	public String getDescripcionOficinaMovimiento() {
		return descripcionOficinaMovimiento;
	}

	public void setDescripcionOficinaMovimiento(String descripcionOficinaMovimiento) {
		this.descripcionOficinaMovimiento = descripcionOficinaMovimiento;
	}

	public Date getFechaProcesoMovimiento() {
		return fechaProcesoMovimiento;
	}

	public void setFechaProcesoMovimiento(Date fechaProcesoMovimiento) {
		this.fechaProcesoMovimiento = fechaProcesoMovimiento;
	}

	public Date getFechaValutaMovimiento() {
		return fechaValutaMovimiento;
	}

	public void setFechaValutaMovimiento(Date fechaValutaMovimiento) {
		this.fechaValutaMovimiento = fechaValutaMovimiento;
	}

	public Double getMontoCargoMovimiento() {
		return montoCargoMovimiento;
	}

	public void setMontoCargoMovimiento(Double montoCargoMovimiento) {
		this.montoCargoMovimiento = montoCargoMovimiento;
	}

	public Double getMontoAbonoMovimiento() {
		return montoAbonoMovimiento;
	}

	public void setMontoAbonoMovimiento(Double montoAbonoMovimiento) {
		this.montoAbonoMovimiento = montoAbonoMovimiento;
	}

	public CuentaEntidadFinanciera getCuentaEntidadFinanciera() {
		return cuentaEntidadFinanciera;
	}

	public void setCuentaEntidadFinanciera(CuentaEntidadFinanciera cuentaEntidadFinanciera) {
		this.cuentaEntidadFinanciera = cuentaEntidadFinanciera;
	}

	public List<DetalleMovCuentaEntidadFinanciera> getLstDetalleMovCuentaEntidadFinanciera() {
		return lstDetalleMovCuentaEntidadFinanciera;
	}

	public void setLstDetalleMovCuentaEntidadFinanciera(
			List<DetalleMovCuentaEntidadFinanciera> lstDetalleMovCuentaEntidadFinanciera) {
		this.lstDetalleMovCuentaEntidadFinanciera = lstDetalleMovCuentaEntidadFinanciera;
	}

}
