package com.corfid.fideicomisos.service.administrativo;

import java.util.List;

import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public interface MenuRolInterface {
	public List<String> obtenerMenuByRol(Integer idRol);
	
	public void addMenuRol(Integer idRol, Integer idMenu, ParametrosAuditoriaModel parametrosAuditoriaModel);
	
	public void removeMenuRol(Integer idRol, Integer idMenu, ParametrosAuditoriaModel parametrosAuditoriaModel);
}
