package com.corfid.fideicomisos.model.administrativo;

import java.util.ArrayList;
import java.util.List;

public class ClienteModel {

    private Integer idCliente;
    private String estadoRegistro;
    private List<EmpresaModel> listEmpresas = new ArrayList<EmpresaModel>();

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public List<EmpresaModel> getListEmpresas() {
        return listEmpresas;
    }

    public void setListEmpresas(List<EmpresaModel> listEmpresas) {
        this.listEmpresas = listEmpresas;
    }

}
