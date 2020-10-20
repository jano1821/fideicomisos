package com.corfid.fideicomisos.service.impl.administrativo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.administrativo.UsuarioConverter;
import com.corfid.fideicomisos.entity.administrativo.ClienteEmpresa;
import com.corfid.fideicomisos.entity.administrativo.Persona;
import com.corfid.fideicomisos.entity.administrativo.Usuario;
import com.corfid.fideicomisos.model.administrativo.CatalogoMailModel;
import com.corfid.fideicomisos.model.administrativo.RolModel;
import com.corfid.fideicomisos.model.administrativo.UsuarioModel;
import com.corfid.fideicomisos.model.cruds.CrudUsuarioModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;
import com.corfid.fideicomisos.repository.administrativo.UsuarioRepository;
import com.corfid.fideicomisos.service.administrativo.CatalogoMailInterface;
import com.corfid.fideicomisos.service.administrativo.ClienteEmpresaInterface;
import com.corfid.fideicomisos.service.administrativo.EmpresaInterface;
import com.corfid.fideicomisos.service.administrativo.PersonaInterface;
import com.corfid.fideicomisos.service.administrativo.UsuarioInterface;
import com.corfid.fideicomisos.service.administrativo.UsuarioRolInterface;
import com.corfid.fideicomisos.service.impl.utilities.ErrorControladoException;
import com.corfid.fideicomisos.service.utilities.EnvioMailInterface;
import com.corfid.fideicomisos.utilities.AbstractService;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.ConstantesError;
import com.corfid.fideicomisos.utilities.StringUtil;

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

    @Autowired
    @Qualifier("clienteEmpresaServiceimpl")
    ClienteEmpresaInterface clienteEmpresaInterface;

    @Autowired
    @Qualifier("personaServiceImpl")
    PersonaInterface personaInterface;

    @Autowired
    @Qualifier("envioEmail")
    EnvioMailInterface envioMailInterface;

    @Autowired
    @Qualifier("catalogoMailServiceImpl")
    CatalogoMailInterface catalogoMailInterface;

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
        Usuario usuario;
        try {
            usuario = usuarioRepository.findByUsuario(userName);
            if (!_isEmpty(usuario)) {
                usuarioModel = usuarioConverter.convertUsuarioToUsuarioModel(usuario);
            }
            return usuarioModel;
        } catch (Exception e) {
            return usuarioModel;
        }
    }

    public Usuario findUsuarioByUserName(String userName) throws Exception {
        Usuario usuario = null;
        try {
            usuario = usuarioRepository.findByUsuario(userName);
            return usuario;
        } catch (Exception e) {
            return usuario;
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
            Boolean tipoGuardado = false;

            if (_isEmpty(usuario)) {
                tipoGuardado = true;
                if (!_isEmpty(findUsuarioByUsuario(usuarioModel.getUsuario()))) {
                    throw new ErrorControladoException(ConstantesError.ERROR_23);
                }

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

                if (_equiv(usuarioModel.getIdUsuarioSesion(),
                           usuarioModel.getIdUsuario()) && _equiv(usuarioModel.getEstadoRegistro(),
                                                                  Constante.ESTADO_REGISTRO_NO_VIGENTE)) {
                    throw new ErrorControladoException(ConstantesError.ERROR_21);
                }

                if (!_equiv(usuarioModel.getTipoUsuarioSesion(), Constante.TIPO_USUARIO_SUPER_ADMIN)) {
                    usuarioModel.setUsuario(usuario.getUsuario());
                    if (_equiv(usuarioModel.getEstadoRegistro(), Constante.ESTADO_REGISTRO_NO_VIGENTE)) {
                        clienteEmpresaInterface.removerClienteEmpresa(_toInteger(usuarioModel.getPersona()),
                                                                      usuarioModel.getIdEmpresaSesion());
                        usuarioModel.setEstadoRegistro(Constante.ESTADO_REGISTRO_VIGENTE);
                        //usuarioModel.setEstadoActividad(Constante.INDICADOR_ACTIVIDAD);
                    }
                } else {
                    if (_equiv(usuarioModel.getEstadoRegistro(), Constante.ESTADO_REGISTRO_NO_VIGENTE)) {
                        usuarioModel.setEstadoActividad("0");
                    }
                }

                usuario = usuarioConverter.convertUsuarioModelToUsuarioExistente(usuario, usuarioModel);
                setModificacionAuditoria(usuario, parametrosAuditoriaModel);
            }

            usuario = usuarioRepository.save(usuario);

            usuarioModelActual = usuarioConverter.convertUsuarioToUsuarioModel(usuario);

            if (!_isEmpty(usuario)) {
                if (tipoGuardado) {
                    if (!_isEmpty(usuarioModelActual.getCorreo())) {
                        String cuerpoMail;
                        CatalogoMailModel catalogoMailModel = catalogoMailInterface.obtenerCorreoPorCodigo(Constante.CODIGO_EMAIL_BIENVENIDA);

                        cuerpoMail = catalogoMailModel.getContenido().replace("NOMBRE_COMPLETO",
                                                                              usuarioModelActual.getNombreCompleto());
                        cuerpoMail = cuerpoMail.replace("PASSWORD", _toStr(password.get("mensaje")));

                        envioMailInterface.sendEmail(usuarioModelActual.getCorreo(),
                                                     catalogoMailModel.getAsunto(),
                                                     cuerpoMail);
                    }
                }
            }

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
                usuarioModel.setDescripcionError(obtenerMensajeError(e.getCodigoError()));
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
    public CrudUsuarioModel removeUsuario(Integer id,
                                          Integer idUsuarioSesion,
                                          Integer idEmpresaSesion) throws Exception {
        CrudUsuarioModel crudUsuarioModel = new CrudUsuarioModel();
        try {
            List<ClienteEmpresa> listClienteEmpresa;

            if (_equiv(idUsuarioSesion, id)) {
                throw new ErrorControladoException(ConstantesError.ERROR_21);
            }

            listClienteEmpresa = clienteEmpresaInterface.findClienteEmpresaByUsuarioAndNotEmpresa(idUsuarioSesion,
                                                                                                  idEmpresaSesion);
            if (!_isEmpty(listClienteEmpresa)) {
                if (listClienteEmpresa.size() > 0) {
                    throw new ErrorControladoException(ConstantesError.ERROR_22);
                }
            }

            Usuario usuario = findUsuarioById(id);
            if (null != usuario) {
                usuarioRepository.delete(usuario);
            }
            crudUsuarioModel.setCodigoError(ConstantesError.ERROR_0);
            return crudUsuarioModel;
        } catch (ErrorControladoException e) {
            crudUsuarioModel.setCodigoError(e.getCodigoError());
            if (_isEmpty(e.getMensajeError())) {
                crudUsuarioModel.setMensajeError(obtenerMensajeError(e.getCodigoError()));
            } else {
                crudUsuarioModel.setMensajeError(e.getMensajeError());
            }
            return crudUsuarioModel;
        } catch (Exception e) {
            crudUsuarioModel.setCodigoError(ConstantesError.ERROR_1);
            crudUsuarioModel.setMensajeError(obtenerMensajeError(ConstantesError.ERROR_1));
            return crudUsuarioModel;
        }
    }

    @Override
    public String actualizarContrasenia(String userName, String password) throws Exception {
        try {
            if (_isEmpty(userName)) {
                throw new ErrorControladoException(ConstantesError.ERROR_9);
            }

            Usuario usuario = findUsuarioByUserName(userName);
            if (null != usuario) {
                BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
                usuario.setPassword(pe.encode(password));

                usuario = usuarioRepository.save(usuario);
                if (_isEmpty(usuario)) {
                    throw new ErrorControladoException(ConstantesError.ERROR_9);
                }
            }

            return ConstantesError.ERROR_0;
        } catch (ErrorControladoException e) {
            return e.getCodigoError();
        } catch (Exception e) {
            return ConstantesError.ERROR_1;
        }
    }

    @Override
    public String validarContrasenia(String password, String password2) throws Exception {
        Map<String, String> resultado = new HashMap<String, String>();
        try {
            if (_isEmpty(password)) {
                throw new ErrorControladoException(ConstantesError.ERROR_9);
            }

            if (_isEmpty(password2)) {
                throw new ErrorControladoException(ConstantesError.ERROR_9);
            }

            resultado = passwordVerificado(password, false, 0);
            if (!_equiv(_toStr(resultado.get("error")), ConstantesError.ERROR_0)) {
                throw new ErrorControladoException(_toStr(resultado.get("error")), _toStr(resultado.get("mensaje")));
            }

            resultado = passwordVerificado(password2, false, 0);
            if (!_equiv(_toStr(resultado.get("error")), ConstantesError.ERROR_0)) {
                throw new ErrorControladoException(_toStr(resultado.get("error")), _toStr(resultado.get("mensaje")));
            }

            return ConstantesError.ERROR_0;
        } catch (ErrorControladoException e) {
            return e.getMensajeError();
        } catch (Exception e) {
            return ConstantesError.ERROR_1;
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

    public Map<String, String> actualizarAndDevolverContrasenia(Integer IdUsuario) throws Exception {
        Map<String, String> password = new HashMap<String, String>();
        password.put("error", ConstantesError.ERROR_0);
        try {
            if (_isEmpty(IdUsuario)) {
                throw new ErrorControladoException(ConstantesError.ERROR_9);
            }

            Usuario usuario = findUsuarioById(IdUsuario);
            if (null != usuario) {

                password = passwordVerificado(null, true, 8);
                if (_equiv(_toStr(password.get("error")), ConstantesError.ERROR_0)) {
                    BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
                    usuario.setPassword(pe.encode(_toStr(password.get("mensaje"))));
                } else {
                    throw new ErrorControladoException(ConstantesError.ERROR_9);
                }

                usuario = usuarioRepository.save(usuario);
                if (_isEmpty(usuario)) {
                    throw new ErrorControladoException(ConstantesError.ERROR_9);
                }
            }

            return password;
        } catch (ErrorControladoException e) {
            password.put("error", e.getCodigoError());
            return password;
        } catch (Exception e) {
            password.put("error", ConstantesError.ERROR_1);
            return password;
        }
    }

    public UsuarioModel findUsuarioByPersona(Integer idPersona) throws Exception {
        UsuarioModel usuarioModel = null;
        Persona persona;
        try {
            persona = personaInterface.findPersonaById(idPersona);
            usuarioModel = findUsuarioByIdPersona(persona);
            return usuarioModel;
        } catch (Exception e) {
            return usuarioModel;
        }
    }
}
