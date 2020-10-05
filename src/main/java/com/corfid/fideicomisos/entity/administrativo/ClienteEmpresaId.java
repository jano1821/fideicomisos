package com.corfid.fideicomisos.entity.administrativo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ClienteEmpresaId implements java.io.Serializable {

    private static final long serialVersionUID = 1807479790484651007L;

    @Column(name = "n_idclie", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
    private Integer idCliente;

    @Column(name = "n_idempr", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
    private Integer idEmpresa;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public ClienteEmpresaId(Integer idCliente, Integer idEmpresa) {
        super();
        this.idCliente = idCliente;
        this.idEmpresa = idEmpresa;
    }

    public ClienteEmpresaId() {

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
        result = prime * result + ((idEmpresa == null) ? 0 : idEmpresa.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ClienteEmpresaId other = (ClienteEmpresaId) obj;
        if (idCliente == null) {
            if (other.idCliente != null)
                return false;
        } else if (!idCliente.equals(other.idCliente))
            return false;
        if (idEmpresa == null) {
            if (other.idEmpresa != null)
                return false;
        } else if (!idEmpresa.equals(other.idEmpresa))
            return false;
        return true;
    }

}
