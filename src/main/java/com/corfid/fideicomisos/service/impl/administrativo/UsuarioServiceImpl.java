package com.corfid.fideicomisos.service.impl.administrativo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.administrativo.UsuarioConverter;
import com.corfid.fideicomisos.entity.administrativo.Persona;
import com.corfid.fideicomisos.entity.administrativo.Usuario;
import com.corfid.fideicomisos.model.administrativo.RolModel;
import com.corfid.fideicomisos.model.administrativo.UsuarioModel;
import com.corfid.fideicomisos.model.cruds.CrudUsuarioModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;
import com.corfid.fideicomisos.repository.administrativo.UsuarioRepository;
import com.corfid.fideicomisos.service.administrativo.UsuarioInterface;
import com.corfid.fideicomisos.service.administrativo.UsuarioRolInterface;
import com.corfid.fideicomisos.service.impl.utilities.ErrorControladoException;
import com.corfid.fideicomisos.utilities.AbstractService;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.ConstantesError;
import com.corfid.fideicomisos.utilities.StringUtil;

import org.springframework.data.domain.*;

@Service("usuarioServiceImpl")
public class UsuarioServiceImpl extends AbstractService implements UsuarioInterface {

    @Autowired
    @Qualifier("usuarioRepository")
    private UsuarioRepository usuarioRepository;

    @Autowired
    @Qualifier("usuarioConverter")
    private UsuarioConverter usuarioConverter;

    @Autowired
    @Qualifier("usuarioRolServiceImpl")
    UsuarioRolInterface usuarioRolInterface;

    @Override
    public List<UsuarioModel> listAllUsuarios() throws Exception {
        List<Usuario> listUsuario = usuarioRepository.findAll();
        List<UsuarioModel> listUsuarioModel = new ArrayList<UsuarioModel>();

        for (Usuario usuario : listUsuario) {
            listUsuarioModel.add(usuarioConverter.convertUsuarioToUsuarioModel(usuario));
        }

        return listUsuarioModel;
    }

    @Override
    public CrudUsuarioModel listUsuarioByUsernamePaginado(String userName,
                                                          String tipoUsuarioSesion,
                                                          String usuarioSession,
                                                          Integer idEmpresaSesion,
                                                          Integer pagina,
                                                          Integer cant) throws Exception {
        List<Usuario> listUsuario;
        List<UsuarioModel> listUsuarioModel = new ArrayList<UsuarioModel>();
        Page<Usuario> pageUsuario;
        CrudUsuarioModel crudUsuarioModel = new CrudUsuarioModel();

        String cadenaUsuario = Constante.COMODIN_LIKE + userName + Constante.COMODIN_LIKE;

        if (_equiv(tipoUsuarioSesion, Constante.TIPO_USUARIO_SUPER_ADMIN)) {
            pageUsuario = usuarioRepository.listUsuarioByUserNamePaginado(cadenaUsuario,
                                                                          obtenerIndexPorPagina(pagina,
                                                                                                cant,
                                                                                                "usuario",
                                                                                                true,
                                                                                                false));
        } else {
            pageUsuario = usuarioRepository.listUsuarioByUserNameAndEmpresaVinculadaPaginado(cadenaUsuario,
                                                                                             usuarioSession,
                                                                                             idEmpresaSesion,
                                                                                             obtenerIndexPorPagina(pagina,
                                                                                                                   cant,
                                                                                                                   "usuario",
                                                                                                                   true,
                                                                                                                   false));
        }

        listUsuario = pageUsuario.getContent();
        crudUsuarioModel.setPaginaFinal(pageUsuario.getTotalPages());
        crudUsuarioModel.setCantidadRegistros(_toInteger(pageUsuario.getTotalElements()));

        for (Usuario usuario : listUsuario) {
            listUsuarioModel.add(usuarioConverter.convertUsuarioToUsuarioModel(usuario));
        }

        crudUsuarioModel.setRows(listUsuarioModel);

        return crudUsuarioModel;
    }

    @Override
    public Usuario findUsuarioById(Integer id) {
        return usuarioRepository.findByIdUsuario(id);
    }

    @Override
    public UsuarioModel findUsuarioByUsuario(String userName) throws Exception {
        UsuarioModel usuarioModel = null;
        try {
            usuarioModel = usuarioConverter.convertUsuarioToUsuarioModel(usuarioRepository.findByUsuario(userName));
            return usuarioModel;
        } catch (Exception e) {
            return usuarioModel;
        }
    }

    @Override
    public UsuarioModel findUsuarioByIdModel(Integer id) throws Exception {
        try {
            return usuarioConverter.convertUsuarioToUsuarioModel(findUsuarioById(id));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UsuarioModel addUsuario(UsuarioModel usuarioModel,
                                   ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception {
        try {
            Map<String, String> password = new HashMap<String, String>();
            Usuario usuario = findUsuarioById(usuarioModel.getIdUsuario());
            UsuarioModel usuarioModelActual = new UsuarioModel();

            if (_isEmpty(usuario)) {

                password = passwordVerificado(null, true, 8);
                if (_equiv(_toStr(password.get("error")), ConstantesError.ERROR_0)) {
                    usuarioModel.setPassword(_toStr(password.get("mensaje")));
                } else {
                    throw new ErrorControladoException(ConstantesError.ERROR_9);
                }

                usuario = usuarioConverter.convertUsuarioModelToUsuario(usuarioModel);
                setInsercionAuditoria(usuario, parametrosAuditoriaModel);
                usuario.setIndicadorPrimerIngreso(true);
            } else {
                if (!_isEmpty(usuarioModel.getCambiar())) {
                    if (_isEmpty(usuarioModel.getGenerar())) {
                        if (_isEmpty(usuarioModel.getPassword())) {
                            throw new ErrorControladoException(ConstantesError.ERROR_5);
                        } else {
                            password = passwordVerificado(usuarioModel.getPassword(), false, 0);
                            if (_equiv(_toStr(password.get("error")), ConstantesError.ERROR_0)) {
                                usuarioModel.setPassword(_toStr(password.get("mensaje")));
                            } else {
                                throw new ErrorControladoException(ConstantesError.ERROR_1, _toStr(password.get("mensaje")));
                            }
                        }
                    } else {
                        password = passwordVerificado(null, true, 8);
                        if (_equiv(_toStr(password.get("error")), ConstantesError.ERROR_0)) {
                            usuarioModel.setPassword(_toStr(password.get("mensaje")));
                        } else {
                            throw new ErrorControladoException(ConstantesError.ERROR_9);
                        }
                    }
                }

                usuario = usuarioConverter.convertUsuarioModelToUsuarioExistente(usuario, usuarioModel);
                setModificacionAuditoria(usuario, parametrosAuditoriaModel);
            }

            usuario = usuarioRepository.save(usuario);
            usuarioModelActual = usuarioConverter.convertUsuarioToUsuarioModel(usuario);

            usuarioModelActual.setIdUsuarioRegistro(usuarioModel.getIdUsuarioRegistro());
            usuarioModelActual.setRol(usuarioModel.getRol());

            if (!_isEmpty(usuario)) {
                List<RolModel> listRolesActual = usuarioModelActual.getListRoles();

                usuarioRolInterface.updateUsuarioRol(usuarioModelActual.getRol().split(","),
                                                     usuarioModelActual.getIdUsuario(),
                                                     listRolesActual,
                                                     parametrosAuditoriaModel);
            }

            usuarioModelActual.setCodigoError(ConstantesError.ERROR_0);

            return usuarioModelActual;

        } catch (ErrorControladoException e) {
            usuarioModel.setCodigoError(e.getCodigoError());
            if (_isEmpty(e.getMensajeError())) {
                usuarioModel.setDescripcionError(ConstantesError.ERROR_0);
            } else {
                usuarioModel.setDescripcionError(e.getMensajeError());
            }
            return usuarioModel;
        } catch (Exception e) {
            usuarioModel.setCodigoError(ConstantesError.ERROR_1);
            usuarioModel.setDescripcionError(obtenerMensajeError(ConstantesError.ERROR_1));
            return usuarioModel;
        }
    }

    @Override
    public void removeUsuario(Integer id) {
        Usuario usuario = findUsuarioById(id);
        if (null != usuario) {
            usuarioRepository.delete(usuario);
        }
    }

    @Override
    public UsuarioModel findUsuarioByIdPersona(Persona persona) throws Exception {
        try {
            return usuarioConverter.convertUsuarioToUsuarioModel(usuarioRepository.findUsuarioByPersona(persona));
        } catch (Exception e) {
            return null;
        }
    }

}
