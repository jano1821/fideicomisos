package com.corfid.fideicomisos.service.administrativo;

import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public interface ClienteEmpresaInterface {

    public Boolean registrarClienteEmpresa(Integer idCliente,
                                           Integer idEmpresa,
                                           ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception;

    public Boolean removerClienteEmpresa(Integer idCliente, Integer idEmpresa) throws Exception;

}
