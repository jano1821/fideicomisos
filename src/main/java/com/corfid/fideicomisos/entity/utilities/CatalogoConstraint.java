package com.corfid.fideicomisos.entity.utilities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admcatco")
public class CatalogoConstraint extends Auditoria {
	@Id
	@GeneratedValue
	@Column(name = "n_idcons", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer idConstraint;

	@Column(name = "c_nomcon", nullable = false, length = 8, insertable = true, updatable = true)
	private String nombreConstraint;

	@Column(name = "c_nomtab", nullable = false, length = 8, insertable = true, updatable = true)
	private String nombreTabla;

	@Column(name = "c_descon", nullable = false, length = 250, insertable = true, updatable = true)
	private String descConstraint;

	@Column(name = "c_valcon", nullable = true, length = 250, insertable = true, updatable = true)
	private String valorConstraint;

	@Column(name = "c_abrcon", nullable = false, length = 100, insertable = true, updatable = true)
	private String abrevConstraint;

	public Integer getIdConstraint() {
		return idConstraint;
	}

	public void setIdConstraint(Integer idConstraint) {
		this.idConstraint = idConstraint;
	}

	public String getNombreConstraint() {
		return nombreConstraint;
	}

	public void setNombreConstraint(String nombreConstraint) {
		this.nombreConstraint = nombreConstraint;
	}

	public String getNombreTabla() {
		return nombreTabla;
	}

	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}

	public String getDescConstraint() {
		return descConstraint;
	}

	public void setDescConstraint(String descConstraint) {
		this.descConstraint = descConstraint;
	}

	public String getValorConstraint() {
		return valorConstraint;
	}

	public void setValorConstraint(String valorConstraint) {
		this.valorConstraint = valorConstraint;
	}

	public String getAbrevConstraint() {
		return abrevConstraint;
	}

	public void setAbrevConstraint(String abrevConstraint) {
		this.abrevConstraint = abrevConstraint;
	}

	public CatalogoConstraint(Integer idConstraint, String nombreConstraint, String nombreTabla, String descConstraint,
			String valorConstraint, String abrevConstraint, String estadoRegistro, String usuarioInsercion,
			Date fechaInsercion, String ipInsercion, String usuarioModificacion, Date fechaModificacion,
			String ipModificacion) {
		super();
		this.idConstraint = idConstraint;
		this.nombreConstraint = nombreConstraint;
		this.nombreTabla = nombreTabla;
		this.descConstraint = descConstraint;
		this.valorConstraint = valorConstraint;
		this.abrevConstraint = abrevConstraint;

		this.estadoRegistro = estadoRegistro;
		this.usuarioInsercion = usuarioInsercion;
		this.fechaInsercion = fechaInsercion;
		this.ipInsercion = ipInsercion;
		this.usuarioModificacion = usuarioModificacion;
		this.fechaModificacion = fechaModificacion;
		this.ipModificacion = ipModificacion;
	}

	public CatalogoConstraint() {

	}
}
