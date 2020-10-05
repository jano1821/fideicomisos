package com.corfid.fideicomisos.component.administrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.administrativo.Cliente;
import com.corfid.fideicomisos.entity.administrativo.Empresa;
import com.corfid.fideicomisos.model.administrativo.ClienteModel;
import com.corfid.fideicomisos.model.administrativo.EmpresaModel;
import com.corfid.fideicomisos.utilities.StringUtil;

@Component("clienteConverter")
public class ClienteConverter {

    @Autowired
    @Qualifier("empresaConverter")
    EmpresaConverter empresaConverter;

    public Cliente convertClienteModelToCliente(ClienteModel clienteModel) {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(clienteModel.getIdCliente());
        cliente.setEstadoRegistro(clienteModel.getEstadoRegistro());

        return cliente;
    }

    public ClienteModel convertClienteToClienteModel(Cliente cliente) {
        ClienteModel clienteModel = new ClienteModel();
        List<EmpresaModel> listEmpresaModel = new ArrayList<EmpresaModel>();

        clienteModel.setIdCliente(cliente.getIdCliente());
        clienteModel.setEstadoRegistro(cliente.getEstadoRegistro());

        if (!StringUtil.isEmpty(cliente.getEmpresas())) {
            for (Empresa empresa : cliente.getEmpresas()) {
                listEmpresaModel.add(empresaConverter.convertEmpresaToEmpresaModel(empresa));
            }
            clienteModel.setListEmpresas(listEmpresaModel);
        }

        return clienteModel;
    }
}
