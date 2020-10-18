package com.corfid.fideicomisos.service.administrativo;

import java.util.List;

import com.corfid.fideicomisos.entity.administrativo.Persona;
import com.corfid.fideicomisos.entity.administrativo.Usuario;
import com.corfid.fideicomisos.model.administrativo.UsuarioModel;
import com.corfid.fideicomisos.model.cruds.CrudUsuarioModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public interface UsuarioInterface {

    public abstract UsuarioModel addUsuario(UsuarioModel usuarioModel,
                                            ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception;

    public abstract List<UsuarioModel> listAllUsuarios() throws Exception;

    public abstract CrudUsuarioModel listUsuarioByUsernamePaginado(String userName,
                                                                   String tipoUsuarioSesion,
                                                                   String usuarioSession,
                                                                   Integer idEmpresaSesion,
                                                                   Integer pagina,
                                                                   Integer cant) throws Exception;

    public abstract Usuario findUsuarioById(Integer id);

    public abstract UsuarioModel findUsuarioByUsuario(String userName) throws Exception;

    public abstract UsuarioModel findUsuarioByIdModel(Integer id) throws Exception;

    public CrudUsuarioModel removeUsuario(Integer id,
                                          Integer idUsuarioSesion,
                                          Integer idEmpresaSesion) throws Exception;

    public abstract UsuarioModel findUsuarioByIdPersona(Persona persona) throws Exception;
}
