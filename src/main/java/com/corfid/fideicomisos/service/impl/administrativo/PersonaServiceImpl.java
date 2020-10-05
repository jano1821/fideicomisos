package com.corfid.fideicomisos.service.impl.administrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.administrativo.PersonaConverter;
import com.corfid.fideicomisos.entity.administrativo.Cliente;
import com.corfid.fideicomisos.entity.administrativo.Persona;
import com.corfid.fideicomisos.model.administrativo.ClienteModel;
import com.corfid.fideicomisos.model.administrativo.PersonaModel;
import com.corfid.fideicomisos.model.administrativo.UsuarioModel;
import com.corfid.fideicomisos.model.cruds.CrudPersonaModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;
import com.corfid.fideicomisos.repository.administrativo.PersonaRepository;
import com.corfid.fideicomisos.service.administrativo.ClienteInterface;
import com.corfid.fideicomisos.service.administrativo.EmpresaInterface;
import com.corfid.fideicomisos.service.administrativo.PersonaInterface;
import com.corfid.fideicomisos.service.administrativo.UsuarioInterface;
import com.corfid.fideicomisos.service.impl.utilities.ErrorControladoException;
import com.corfid.fideicomisos.utilities.AbstractService;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.ConstantesError;
import com.corfid.fideicomisos.utilities.StringUtil;

@Service("personaServiceImpl")
public class PersonaServiceImpl extends AbstractService implements PersonaInterface {

    @Autowired
    @Qualifier("personaRepository")
    private PersonaRepository personaRepository;

    @Autowired
    @Qualifier("personaConverter")
    private PersonaConverter personaConverter;

    @Autowired
    @Qualifier("clienteServiceImpl")
    private ClienteInterface clienteInterface;

    @Autowired
    @Qualifier("usuarioServiceImpl")
    private UsuarioInterface usuarioInterface;

    @Autowired
    @Qualifier("empresaServiceImpl")
    private EmpresaInterface empresaInterface;

    @Override
    public List<PersonaModel> listAllPersona() throws Exception {
        List<Persona> listUsuario = personaRepository.findAll();
        List<PersonaModel> listUsuarioModel = new ArrayList<PersonaModel>();
        try {
            for (Persona persona : listUsuario) {
                listUsuarioModel.add(personaConverter.convertPersonaToPersonaModel(persona));
            }

            return listUsuarioModel;
        } catch (Exception e) {
            return listUsuarioModel;
        }
    }

    @Override
    public CrudPersonaModel listPersonaByNombrePaginado(String nombres,
                                                        String busquedaTipoPersona,
                                                        Integer pagina,
                                                        Integer cant) throws Exception {
        List<Persona> listPersona;
        List<PersonaModel> listPersonaModel = new ArrayList<PersonaModel>();
        Page<Persona> pagePersona;
        CrudPersonaModel crudPersonaModel = new CrudPersonaModel();
        try {
            String cadenaPersona = Constante.COMODIN_LIKE + nombres + Constante.COMODIN_LIKE;
            String tipoPersona = Constante.COMODIN_LIKE + busquedaTipoPersona;

            pagePersona = personaRepository.listPersonaByNombrePaginado(cadenaPersona,
                                                                        tipoPersona,
                                                                        obtenerIndexPorPagina(pagina,
                                                                                              cant,
                                                                                              "nombres",
                                                                                              true,
                                                                                              false));

            listPersona = pagePersona.getContent();
            crudPersonaModel.setPaginaFinal(pagePersona.getTotalPages());
            crudPersonaModel.setCantidadRegistros(_toInteger(pagePersona.getTotalElements()));

            for (Persona persona : listPersona) {
                listPersonaModel.add(personaConverter.convertPersonaToPersonaModel(persona));
            }

            crudPersonaModel.setRows(listPersonaModel);

            return crudPersonaModel;
        } catch (Exception e) {
            return crudPersonaModel;
        }
    }

    @Override
    public Persona findPersonaById(Integer id) {
        return personaRepository.findByIdPersona(id);
    }

    @Override
    public PersonaModel findPersonaByIdModel(Integer id) throws Exception {
        try {
            return personaConverter.convertPersonaToPersonaModel(findPersonaById(id));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public PersonaModel addPersona(PersonaModel personaModel,
                                   ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception {
        UsuarioModel usuarioModel;
        PersonaModel personaModelActual;
        Persona persona;
        try {
            persona = findPersonaById(personaModel.getIdPersona());

            if (_isEmpty(persona)) {
                persona = personaConverter.convertPersonaModelToPersona(personaModel);
                setInsercionAuditoria(persona, parametrosAuditoriaModel);
            } else {
                usuarioModel = usuarioInterface.findUsuarioByIdPersona(persona);

                if (!_isEmpty(usuarioModel) && _equiv(personaModel.getCliente(), Constante.SI_ES_CLIENTE)) {
                    if (_equiv(usuarioModel.getTipoUsuario(), Constante.TIPO_USUARIO_SUPER_ADMIN)) {
                        throw new ErrorControladoException(ConstantesError.ERROR_3);
                    }
                }

                if (!_isEmpty(usuarioModel) && _equiv(personaModel.getPermiteVinculacion(), Constante.SI_ES_EMPRESA)) {
                    if (_equiv(usuarioModel.getTipoUsuario(), Constante.TIPO_USUARIO_SUPER_ADMIN)) {
                        throw new ErrorControladoException(ConstantesError.ERROR_10);
                    }
                }

                persona = personaConverter.convertPersonaModelToPersonaExistente(persona, personaModel);
                setModificacionAuditoria(persona, parametrosAuditoriaModel);
            }

            persona = personaRepository.save(persona);

            personaModelActual = personaConverter.convertPersonaToPersonaModel(persona);
            personaModelActual.setIdUsuarioRegistro(personaModel.getIdUsuarioRegistro());
            personaModelActual.setEmpresa(personaModel.getEmpresa());

            if (!_isEmpty(persona)) {
                if (_equiv(personaModel.getCliente(), Constante.SI_ES_CLIENTE)) {
                    if (StringUtil.isEmpty(clienteInterface.findByIdCliente(persona.getIdPersona()))) {
                        clienteInterface.registrarCliente(persona.getIdPersona(), parametrosAuditoriaModel);
                    }
                } else {
                    if (!StringUtil.isEmpty(clienteInterface.findByIdCliente(persona.getIdPersona()))) {
                        clienteInterface.removerCliente(persona.getIdPersona());
                    }
                }
                if (_equiv(personaModel.getPermiteVinculacion(), Constante.SI_ES_EMPRESA)) {
                    if (StringUtil.isEmpty(empresaInterface.findEmpresaById(persona.getIdPersona()))) {
                        empresaInterface.registrarEmpresa(persona.getIdPersona(), parametrosAuditoriaModel);
                    }
                } else {
                    if (!StringUtil.isEmpty(empresaInterface.findEmpresaById(persona.getIdPersona()))) {
                        empresaInterface.removerEmpresa(persona.getIdPersona());
                    }
                }
                if (!_isEmpty(personaModel.getEmpresa()) && _equiv(personaModel.getCliente(),
                                                                   Constante.SI_ES_CLIENTE)) {
                    empresaInterface.registrarEmpresasOfCadena(personaModelActual.getEmpresa(),
                                                               personaModelActual.getListEmpresa(),
                                                               personaModelActual.getIdUsuarioRegistro(),
                                                               personaModelActual.getIdPersona(),
                                                               parametrosAuditoriaModel);
                }
            }

            personaModelActual.setCodigoError(ConstantesError.ERROR_0);

            return personaModelActual;
        } catch (ErrorControladoException e) {
            personaModel.setCodigoError(e.getCodigoError());
            personaModel.setDescripcionError(obtenerMensajeError(e.getCodigoError()));
            return personaModel;
        } catch (Exception e) {
            personaModel.setCodigoError(ConstantesError.ERROR_1);
            personaModel.setDescripcionError(obtenerMensajeError(ConstantesError.ERROR_1));
            return personaModel;
        }

    }

    @Override
    public void removePersona(Integer id) {
        Persona persona = findPersonaById(id);
        if (null != persona) {
            personaRepository.delete(persona);
        }
    }

    @Override
    public CrudPersonaModel obtenerEmpresaByPersona(Integer id) throws Exception {
        List<Persona> listPersona = new ArrayList<Persona>();
        List<PersonaModel> listPersonaModel = new ArrayList<PersonaModel>();
        CrudPersonaModel crudPersonaModel = new CrudPersonaModel();
        try {
            listPersona = personaRepository.listEmpresaByPersona(id);

            for (Persona persona : listPersona) {
                listPersonaModel.add(personaConverter.convertPersonaToPersonaModel(persona));
            }

            crudPersonaModel.setRows(listPersonaModel);

            return crudPersonaModel;
        } catch (Exception e) {
            return crudPersonaModel;
        }
    }

    @Override
    public List<PersonaModel> obtenerAllEmpresas() throws Exception {
        List<PersonaModel> listPersonaModel = new ArrayList<PersonaModel>();
        List<Persona> listPersona;
        try {
            listPersona = personaRepository.listAllEmpresas();

            for (Persona persona : listPersona) {
                listPersonaModel.add(personaConverter.convertPersonaToPersonaModel(persona));
            }

            return listPersonaModel;
        } catch (Exception e) {
            return listPersonaModel;
        }
    }

    @Override
    public List<PersonaModel> obtenerPersonasNoVinculadasUsuarios() throws Exception {
        List<PersonaModel> listPersonaModel = new ArrayList<PersonaModel>();
        List<Persona> listPersona;
        try {
            listPersona = personaRepository.listPersonasSinVinculacion();

            for (Persona persona : listPersona) {
                listPersonaModel.add(personaConverter.convertPersonaToPersonaModel(persona));
            }

            return listPersonaModel;
        } catch (Exception e) {
            return listPersonaModel;
        }
    }

    @Override
    public List<PersonaModel> obtenerPersonasPorNumeroDocumento(String tipoDocumento,
                                                                String numeroDocumento) throws Exception {
        List<PersonaModel> listPersonaModel = new ArrayList<PersonaModel>();
        List<Persona> listPersona;
        try {
            listPersona = personaRepository.listPersonasByNumeroDocumento(tipoDocumento,numeroDocumento);

            for (Persona persona : listPersona) {
                listPersonaModel.add(personaConverter.convertPersonaToPersonaModel(persona));
            }

            return listPersonaModel;
        } catch (Exception e) {
            return listPersonaModel;
        }
    }
}
