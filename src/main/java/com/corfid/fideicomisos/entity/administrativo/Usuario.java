package com.corfid.fideicomisos.entity.administrativo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admusuar")
public class Usuario extends Auditoria {

	@Id
	@GeneratedValue
	@Column(name = "n_idusua", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer idUsuario;

	@Column(name = "c_codusu", nullable = false, length = 100, insertable = true, updatable = true)
	private String usuario;

	@Column(name = "c_contra", nullable = false, length = 100, insertable = true, updatable = true)
	private String password;

	@Column(name = "c_tiusad", nullable = false, length = 1, insertable = true, updatable = true)
	private String tipoUsuario;

	@OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "n_idpers", nullable = false, insertable = true, updatable = true)
	private Persona persona;

	@Column(name = "b_estusu", nullable = false, insertable = true, updatable = true)
	private boolean estadoActividadUsuario;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "admusrol", joinColumns = @JoinColumn(name = "n_idusua"), inverseJoinColumns = @JoinColumn(name = "n_iderol"))
	private Set<Rol> roles = new HashSet<Rol>();

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public boolean isEstadoActividadUsuario() {
		return estadoActividadUsuario;
	}

	public void setEstadoActividadUsuario(boolean estadoActividadUsuario) {
		this.estadoActividadUsuario = estadoActividadUsuario;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public Usuario(Integer idUsuario, String usuario, String password, String tipoUsuario, Persona persona,
			boolean estadoActividadUsuario, String estadoRegistro, String usuarioInsercion, Date fechaInsercion,
			String ipInsercion, String usuarioModificacion, Date fechaModificacion, String ipModificacion) {
		super();
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.password = password;
		this.tipoUsuario = tipoUsuario;
		this.persona = persona;
		this.estadoActividadUsuario = estadoActividadUsuario;

		this.estadoRegistro = estadoRegistro;
		this.usuarioInsercion = usuarioInsercion;
		this.fechaInsercion = fechaInsercion;
		this.ipInsercion = ipInsercion;
		this.usuarioModificacion = usuarioModificacion;
		this.fechaModificacion = fechaModificacion;
		this.ipModificacion = ipModificacion;

	}

	public Usuario() {

	}

}
