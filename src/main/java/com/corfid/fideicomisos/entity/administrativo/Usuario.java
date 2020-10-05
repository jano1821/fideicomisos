package com.corfid.fideicomisos.entity.administrativo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admusuar")
public class Usuario extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "n_idusua", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
    private Integer idUsuario;

    @Column(name = "c_codusu", nullable = false, length = 100, insertable = true, updatable = true)
    private String usuario;

    @Column(name = "c_contra", nullable = false, length = 100, insertable = true, updatable = true)
    private String password;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "c_idtius", nullable = false, insertable = true, updatable = true)
    private TipoUsuario tipoUsuario;

    @OneToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "n_idpers", nullable = true, insertable = true, updatable = true)
    private Persona persona;

    @Column(name = "b_estusu", nullable = false, insertable = true, updatable = true)
    private boolean estadoActividadUsuario;

    @Column(name = "n_idusre", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
    private Integer idUsuarioRegistro;

    @Column(name = "b_inprin", nullable = false, insertable = true, updatable = true)
    private boolean indicadorPrimerIngreso;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "admusrol", joinColumns = @JoinColumn(name = "n_idusua"), inverseJoinColumns = @JoinColumn(name = "n_iderol"))
    private Set<Rol> roles = new HashSet<Rol>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<HistorialContrasenia> lstHistorialContrasenia = new ArrayList<HistorialContrasenia>(0);

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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getIdUsuarioRegistro() {
        return idUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(Integer idUsuarioRegistro) {
        this.idUsuarioRegistro = idUsuarioRegistro;
    }

    public boolean isIndicadorPrimerIngreso() {
        return indicadorPrimerIngreso;
    }

    public void setIndicadorPrimerIngreso(boolean indicadorPrimerIngreso) {
        this.indicadorPrimerIngreso = indicadorPrimerIngreso;
    }

    public List<HistorialContrasenia> getLstHistorialContrasenia() {
        return lstHistorialContrasenia;
    }

    public void setLstHistorialContrasenia(List<HistorialContrasenia> lstHistorialContrasenia) {
        this.lstHistorialContrasenia = lstHistorialContrasenia;
    }

    public Usuario(Integer idUsuario, String usuario, String password, TipoUsuario tipoUsuario, Persona persona, boolean estadoActividadUsuario, Integer idUsuarioRegistro, boolean indicadorPrimerIngreso, Set<Rol> roles, List<HistorialContrasenia> lstHistorialContrasenia) {
        super();
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.persona = persona;
        this.estadoActividadUsuario = estadoActividadUsuario;
        this.idUsuarioRegistro = idUsuarioRegistro;
        this.indicadorPrimerIngreso = indicadorPrimerIngreso;
        this.roles = roles;
        this.lstHistorialContrasenia = lstHistorialContrasenia;
    }

    public Usuario() {

    }

}
