package com.corfid.fideicomisos.service.administrativo;

import java.util.List;

import com.corfid.fideicomisos.entity.administrativo.ClienteEmpresa;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public interface ClienteEmpresaInterface {

    public Boolean registrarClienteEmpresa(Integer idCliente,
                                           Integer idEmpresa,
                                           ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception;

    public Boolean removerClienteEmpresa(Integer idCliente, Integer idEmpresa) throws Exception;

    public List<ClienteEmpresa> findClienteEmpresaByUsuarioAndNotEmpresa(Integer idUsuario,
                                                                         Integer idEmpresaSesion) throws Exception;

}
