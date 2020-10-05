package com.corfid.fideicomisos.service.administrativo;

import java.util.List;

import com.corfid.fideicomisos.model.administrativo.RolModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public interface UsuarioRolInterface {

    public boolean updateUsuarioRol(String[] idRol,
                                    Integer idUsuario,
                                    List<RolModel> listRolModelActual,
                                    ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception;

    public Boolean registrarUsuarioRol(Integer idUsuario,
                                       Integer idRol,
                                       ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception;

    public Boolean removerUsuarioRol(Integer idUsuario, Integer idRol) throws Exception;
}
