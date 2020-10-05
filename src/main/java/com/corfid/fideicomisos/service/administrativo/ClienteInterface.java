package com.corfid.fideicomisos.service.administrativo;

import com.corfid.fideicomisos.model.administrativo.ClienteModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public interface ClienteInterface {
    public ClienteModel findByIdCliente(Integer idPersona) throws Exception;
	
	public Boolean registrarCliente(Integer idCliente, ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception;
	
	public Boolean removerCliente(Integer idCliente) throws Exception;
}
