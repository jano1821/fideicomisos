package com.corfid.fideicomisos.model.administrativo;

public class CatalogoConstraintModel {
	private Integer idConstraint;

	private String nombreConstraint;

	private String nombreTabla;

	private String descConstraint;

	private String valorConstraint;

	private String abrevConstraint;

	public String getAbrevConstraint() {
		return abrevConstraint;
	}

	public void setAbrevConstraint(String abrevConstraint) {
		this.abrevConstraint = abrevConstraint;
	}

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

}
