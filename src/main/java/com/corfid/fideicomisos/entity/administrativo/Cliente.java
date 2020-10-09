package com.corfid.fideicomisos.entity.administrativo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admclien")
public class Cliente extends Auditoria {

    @Id
    @Column(name = "n_idclie", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
    private Integer idCliente;

    @OneToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "n_idclie", unique = true, nullable = false, insertable = true, updatable = true)
    private Persona persona;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "admemcli", joinColumns = @JoinColumn(name = "n_idclie"), inverseJoinColumns = @JoinColumn(name = "n_idempr"))
    private Set<Empresa> empresas = new HashSet<Empresa>();

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Set<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(Set<Empresa> empresas) {
        this.empresas = empresas;
    }

    public Cliente(Integer idCliente, Persona persona, Set<Empresa> empresas) {
        super();
        this.idCliente = idCliente;
        this.persona = persona;
        this.empresas = empresas;
    }

    public Cliente() {

    }
}
