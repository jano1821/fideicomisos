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

    public RolModel addRol(RolModel rolModel, ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception;

    public void removeRol(Integer id);

    public CrudRolModel listRolByDescripcionPaginado(String descripcion,
                                                     Integer idEmpresaSesion,
                                                     String tipoUsuarioSesion,
                                                     Integer pagina,
                                                     Integer cant) throws Exception;

    public boolean updateMenuRol(String[] idMenu, Integer idRol, ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception;

    public List<RolModel> listAllRolesByEstadoRegistro(String estadoRegistro) throws Exception;
}
