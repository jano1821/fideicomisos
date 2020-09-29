package com.corfid.fideicomisos.entity.banco;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "banddemv")
public class DetalleMovCuentaEntidadFinanciera extends Auditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_iddemv", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer identificadorDetalleMovCuentaFinanciera;

	@Column(name = "n_correl", nullable = false, insertable = true, updatable = true, precision = 2, scale = 0)
	private Integer correlativoDetalleMovimiento;

	@Column(name = "c_descri", nullable = false, insertable = true, updatable = true, length = 50)
	private String descripcionDetalleMovimiento;

	@Column(name = "n_monmov", nullable = false, insertable = true, updatable = true, precision = 12, scale = 0)
	private Double montoMovimiento;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "n_idmvcf", nullable = false, insertable = true, updatable = true)
	private MovimientoCuentaEntidadFinanciera movimientoCuentaEntidadFinanciera;

	public Integer getIdentificadorDetalleMovCuentaFinanciera() {
		return identificadorDetalleMovCuentaFinanciera;
	}

	public void setIdentificadorDetalleMovCuentaFinanciera(Integer identificadorDetalleMovCuentaFinanciera) {
		this.identificadorDetalleMovCuentaFinanciera = identificadorDetalleMovCuentaFinanciera;
	}

	public Integer getCorrelativoDetalleMovimiento() {
		return correlativoDetalleMovimiento;
	}

	public void setCorrelativoDetalleMovimiento(Integer correlativoDetalleMovimiento) {
		this.correlativoDetalleMovimiento = correlativoDetalleMovimiento;
	}

	public String getDescripcionDetalleMovimiento() {
		return descripcionDetalleMovimiento;
	}

	public void setDescripcionDetalleMovimiento(String descripcionDetalleMovimiento) {
		this.descripcionDetalleMovimiento = descripcionDetalleMovimiento;
	}

	public Double getMontoMovimiento() {
		return montoMovimiento;
	}

	public void setMontoMovimiento(Double montoMovimiento) {
		this.montoMovimiento = montoMovimiento;
	}

	public MovimientoCuentaEntidadFinanciera getMovimientoCuentaEntidadFinanciera() {
		return movimientoCuentaEntidadFinanciera;
	}

	public void setMovimientoCuentaEntidadFinanciera(
			MovimientoCuentaEntidadFinanciera movimientoCuentaEntidadFinanciera) {
		this.movimientoCuentaEntidadFinanciera = movimientoCuentaEntidadFinanciera;
	}

	public DetalleMovCuentaEntidadFinanciera(Integer identificadorDetalleMovCuentaFinanciera,
			Integer correlativoDetalleMovimiento, String descripcionDetalleMovimiento, Double montoMovimiento,
			MovimientoCuentaEntidadFinanciera movimientoCuentaEntidadFinanciera) {
		super();
		this.identificadorDetalleMovCuentaFinanciera = identificadorDetalleMovCuentaFinanciera;
		this.correlativoDetalleMovimiento = correlativoDetalleMovimiento;
		this.descripcionDetalleMovimiento = descripcionDetalleMovimiento;
		this.montoMovimiento = montoMovimiento;
		this.movimientoCuentaEntidadFinanciera = movimientoCuentaEntidadFinanciera;
	}

	public DetalleMovCuentaEntidadFinanciera() {

	}

}
