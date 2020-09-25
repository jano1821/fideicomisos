package com.corfid.fideicomisos.entity.administrativo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admcmenu")
public class Menu extends Auditoria {
	@Id
	@GeneratedValue
	@Column(name = "n_idmenu", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer idMenu;

	@Column(name = "c_descri", nullable = false, length = 100, insertable = true, updatable = true)
	private String descripcion;

	@Column(name = "c_urlmen", nullable = false, length = 100, insertable = true, updatable = true)
	private String url;

	@Column(name = "c_tipmen", nullable = false, length = 100, insertable = true, updatable = true)
	private String tipoMenu;

	@Column(name = "n_idmede", nullable = true, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer idMenuPadre;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "admmerol", joinColumns = @JoinColumn(name = "n_idmenu"), inverseJoinColumns = @JoinColumn(name = "n_iderol"))
	private Set<Rol> roles = new HashSet<Rol>();

	public Integer getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTipoMenu() {
		return tipoMenu;
	}

	public void setTipoMenu(String tipoMenu) {
		this.tipoMenu = tipoMenu;
	}

	public Integer getIdMenuPadre() {
		return idMenuPadre;
	}

	public void setIdMenuPadre(Integer idMenuPadre) {
		this.idMenuPadre = idMenuPadre;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public Menu(Integer idMenu, String descripcion, String url, String tipoMenu, Integer idMenuPadre,
			String estadoRegistro, String usuarioInsercion, Date fechaInsercion, String ipInsercion,
			String usuarioModificacion, Date fechaModificacion, String ipModificacion) {
		super();
		this.idMenu = idMenu;
		this.descripcion = descripcion;
		this.url = url;
		this.tipoMenu = tipoMenu;
		this.idMenuPadre = idMenuPadre;

		this.estadoRegistro = estadoRegistro;
		this.usuarioInsercion = usuarioInsercion;
		this.fechaInsercion = fechaInsercion;
		this.ipInsercion = ipInsercion;
		this.usuarioModificacion = usuarioModificacion;
		this.fechaModificacion = fechaModificacion;
		this.ipModificacion = ipModificacion;
	}

	public Menu() {

	}

}
