package com.corfid.fideicomisos.entity.administrativo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admroles")
public class Rol extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "n_iderol", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
    private Integer idRol;

    @Column(name = "c_descri", nullable = false, length = 100, insertable = true, updatable = true)
    private String descripcion;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "c_idtius", nullable = false, insertable = true, updatable = true)
    private TipoUsuario tipoUsuario;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<Usuario> usuarios = new HashSet<Usuario>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<Menu> menus = new HashSet<Menu>();

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "n_idempr", nullable = true, insertable = true, updatable = true)
    private Empresa empresa;

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Rol(Integer idRol, String descripcion, TipoUsuario tipoUsuario, Set<Usuario> usuarios, Set<Menu> menus, Empresa empresa) {
        super();
        this.idRol = idRol;
        this.descripcion = descripcion;
        this.tipoUsuario = tipoUsuario;
        this.usuarios = usuarios;
        this.menus = menus;
        this.empresa = empresa;
    }

    public Rol() {

    }

}
