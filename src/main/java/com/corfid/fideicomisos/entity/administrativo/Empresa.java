package com.corfid.fideicomisos.entity.administrativo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admempre")
public class Empresa extends Auditoria implements Serializable {

    private static final long serialVersionUID = 983483323578679849L;
    @Id
    @Column(name = "n_idempr", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
    private Integer idEmpresa;

    @OneToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "n_idempr", nullable = false, insertable = true, updatable = true)
    private Persona persona;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "empresas")
    private Set<Cliente> clientes = new HashSet<Cliente>();

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Empresa(Integer idEmpresa, Persona persona, Set<Cliente> clientes) {
        super();
        this.idEmpresa = idEmpresa;
        this.persona = persona;
        this.clientes = clientes;
    }

    public Empresa() {
    }

}
