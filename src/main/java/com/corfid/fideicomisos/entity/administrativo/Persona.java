package com.corfid.fideicomisos.entity.administrativo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admperso")
public class Persona extends Auditoria {

	@Id
	@GeneratedValue
	@Column(name = "n_idpers", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer idPersona;

	@Column(name = "c_nomper", nullable = false, length = 200, insertable = true, updatable = true)
	private String nombres;

	@Column(name = "c_apepat", nullable = false, length = 200, insertable = true, updatable = true)
	private String apePat;

	@Column(name = "c_apemat", nullable = false, length = 200, insertable = true, updatable = true)
	private String apeMat;

	@Column(name = "c_nomcom", nullable = false, length = 300, insertable = true, updatable = true)
	private String nombreCompleto;

	@Column(name = "c_razons", nullable = true, length = 200, insertable = true, updatable = true)
	private String razonSocial;

	@Column(name = "c_tipper", nullable = false, length = 1, insertable = true, updatable = true)
	private String tipoPersona;

	@Column(name = "c_tipdoc", nullable = false, length = 3, insertable = true, updatable = true)
	private String tipoDocumento;

	@Column(name = "c_numdoc", nullable = false, length = 25, insertable = true, updatable = true)
	private String numeroDocumento;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "persona")
	private List<Direccion> lstDireccion = new ArrayList<Direccion>(0);

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "persona")
	private List<Email> lstEmail = new ArrayList<Email>(0);

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "persona")
	private List<Telefono> lstTelefono = new ArrayList<Telefono>(0);

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "persona")
	private Empresa empresa = new Empresa();

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getApePat() {
		return apePat;
	}

	public void setApePat(String apePat) {
		this.apePat = apePat;
	}

	public String getApeMat() {
		return apeMat;
	}

	public void setApeMat(String apeMat) {
		this.apeMat = apeMat;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
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

	public List<Direccion> getLstDireccion() {
		return lstDireccion;
	}

	public void setLstDireccion(List<Direccion> lstDireccion) {
		this.lstDireccion = lstDireccion;
	}

	public List<Email> getLstEmail() {
		return lstEmail;
	}

	public void setLstEmail(List<Email> lstEmail) {
		this.lstEmail = lstEmail;
	}

	public List<Telefono> getLstTelefono() {
		return lstTelefono;
	}

	public void setLstTelefono(List<Telefono> lstTelefono) {
		this.lstTelefono = lstTelefono;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Persona(Integer idPersona, String nombres, String apePat, String apeMat, String nombreCompleto,
			String razonSocial, String tipoPersona, String tipoDocumento, String numeroDocumento,
			List<Direccion> lstDireccion, List<Email> lstEmail, List<Telefono> lstTelefono, Empresa empresa) {
		super();
		this.idPersona = idPersona;
		this.nombres = nombres;
		this.apePat = apePat;
		this.apeMat = apeMat;
		this.nombreCompleto = nombreCompleto;
		this.razonSocial = razonSocial;
		this.tipoPersona = tipoPersona;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.lstDireccion = lstDireccion;
		this.lstEmail = lstEmail;
		this.lstTelefono = lstTelefono;
		this.empresa = empresa;
	}

	public Persona() {

	}

}
