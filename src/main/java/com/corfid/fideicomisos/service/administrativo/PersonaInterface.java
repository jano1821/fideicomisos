package com.corfid.fideicomisos.service.administrativo;

import java.util.List;

import com.corfid.fideicomisos.entity.administrativo.Persona;
import com.corfid.fideicomisos.model.administrativo.PersonaModel;
import com.corfid.fideicomisos.model.cruds.CrudPersonaModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public interface PersonaInterface {
	public List<PersonaModel> listAllPersona();

	public CrudPersonaModel listPersonaByNombrePaginado(String nombres, Integer pagina, Integer cant);

	public Persona findPersonaById(Integer id);

	public PersonaModel findPersonaByIdModel(Integer id);

	public PersonaModel addPersona(PersonaModel personaModel, ParametrosAuditoriaModel parametrosAuditoriaModel);

	public void removePersona(Integer id);
	
	public CrudPersonaModel obtenerEmpresaByPersona(Integer id);
}
