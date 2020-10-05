package com.corfid.fideicomisos.service.administrativo;

import java.util.List;

import com.corfid.fideicomisos.entity.administrativo.Rol;
import com.corfid.fideicomisos.model.administrativo.RolModel;
import com.corfid.fideicomisos.model.cruds.CrudRolModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public interface RolInterface {
	public List<RolModel> listAllRoles();

	public Rol findRolById(Integer id);

	public RolModel findRolByIdModel(Integer id);

	public RolModel addRol(RolModel rolModel, ParametrosAuditoriaModel parametrosAuditoriaModel);

	public void removeRol(Integer id);

	public CrudRolModel listRolByDescripcionPaginado(String descripcion, Integer pagina, Integer cant);

	public boolean updateMenuRol(String[] idMenu, Integer idRol, ParametrosAuditoriaModel parametrosAuditoriaModel);
	
	public List<RolModel> listAllRolesByEstadoRegistro(String estadoRegistro) throws Exception;
}
