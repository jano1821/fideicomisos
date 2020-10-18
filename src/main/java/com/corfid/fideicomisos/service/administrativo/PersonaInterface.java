package com.corfid.fideicomisos.service.administrativo;

import java.util.List;

import com.corfid.fideicomisos.entity.administrativo.Persona;
import com.corfid.fideicomisos.model.administrativo.PersonaModel;
import com.corfid.fideicomisos.model.cruds.CrudPersonaModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public interface PersonaInterface {

    public List<PersonaModel> listAllPersona() throws Exception;

    public CrudPersonaModel listPersonaByNombrePaginado(String nombres,
                                                        String busquedaTipoPersona,
                                                        String usuarioSesion,
                                                        String tipoUsuarioSesion,
                                                        Integer idEmpresaSeleccionadaSesion,
                                                        Integer pagina,
                                                        Integer cant) throws Exception;

    public Persona findPersonaById(Integer id);

    public PersonaModel findPersonaByIdModel(Integer id) throws Exception;

    public PersonaModel addPersona(PersonaModel personaModel,
                                   ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception;

    public Boolean removePersona(Integer id) throws Exception;

    public CrudPersonaModel obtenerEmpresaByPersona(Integer id) throws Exception;

    public List<PersonaModel> obtenerAllEmpresas(String tipoUsuarioSesion, Integer idEmpresaSesion) throws Exception;

    public List<PersonaModel> obtenerPersonasNoVinculadasUsuarios() throws Exception;

    public List<PersonaModel> obtenerPersonasPorNumeroDocumento(String tipoDocumento, String numeroDocumento) throws Exception;

    public PersonaModel validarFormulario(PersonaModel personaModel) throws Exception;
}
