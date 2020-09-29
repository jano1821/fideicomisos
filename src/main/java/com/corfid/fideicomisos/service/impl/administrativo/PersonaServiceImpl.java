package com.corfid.fideicomisos.service.impl.administrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.administrativo.PersonaConverter;
import com.corfid.fideicomisos.entity.administrativo.Persona;
import com.corfid.fideicomisos.model.administrativo.PersonaModel;
import com.corfid.fideicomisos.model.cruds.CrudMenuModel;
import com.corfid.fideicomisos.model.cruds.CrudPersonaModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;
import com.corfid.fideicomisos.repository.administrativo.PersonaRepository;
import com.corfid.fideicomisos.service.administrativo.PersonaInterface;
import com.corfid.fideicomisos.utilities.AbstractService;
import com.corfid.fideicomisos.utilities.Constante;

@Service("personaServiceImpl")
public class PersonaServiceImpl  extends AbstractService implements PersonaInterface{
	@Autowired
	@Qualifier("personaRepository")
	private PersonaRepository personaRepository;

	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter;

	@Override
	public List<PersonaModel> listAllPersona() {
		List<Persona> listUsuario = personaRepository.findAll();
		List<PersonaModel> listUsuarioModel = new ArrayList<PersonaModel>();

		for (Persona persona : listUsuario) {
			listUsuarioModel.add(personaConverter.convertPersonaToPersonaModel(persona));
		}

		return listUsuarioModel;
	}

	@Override
	public CrudPersonaModel listPersonaByNombrePaginado(String nombres, Integer pagina, Integer cant) {
		List<Persona> listPersona;
		List<PersonaModel> listPersonaModel = new ArrayList<PersonaModel>();
		Page<Persona> pagePersona;
		CrudPersonaModel crudPersonaModel = new CrudPersonaModel();

		String cadenaPersona = Constante.COMODIN_LIKE + nombres + Constante.COMODIN_LIKE;

		pagePersona = personaRepository.listPersonaByNombrePaginado(cadenaPersona,
				obtenerIndexPorPagina(pagina, cant, "nombres", true, false));

		listPersona = pagePersona.getContent();
		crudPersonaModel.setPaginaFinal(pagePersona.getTotalPages());
		crudPersonaModel.setCantidadRegistros(_toInteger(pagePersona.getTotalElements()));

		for (Persona persona : listPersona) {
			listPersonaModel.add(personaConverter.convertPersonaToPersonaModel(persona));
		}

		crudPersonaModel.setRows(listPersonaModel);

		return crudPersonaModel;
	}

	@Override
	public Persona findPersonaById(Integer id) {
		return personaRepository.findByIdPersona(id);
	}

	@Override
	public PersonaModel findPersonaByIdModel(Integer id) {
		return personaConverter.convertPersonaToPersonaModel(findPersonaById(id));
	}

	@Override
	public PersonaModel addPersona(PersonaModel personaModel, ParametrosAuditoriaModel parametrosAuditoriaModel) {
		Persona persona = findPersonaById(personaModel.getIdPersona());

		if (_isEmpty(persona)) {
			persona = personaConverter.convertPersonaModelToPersona(personaModel);
			setInsercionAuditoria(persona, parametrosAuditoriaModel);
		} else {
			persona = personaConverter.convertPersonaModelToPersonaExistente(persona, personaModel);
			setModificacionAuditoria(persona, parametrosAuditoriaModel);
		}

		persona = personaRepository.save(persona);

		return personaConverter.convertPersonaToPersonaModel(persona);
	}

	@Override
	public void removePersona(Integer id) {
		Persona persona = findPersonaById(id);
		if (null != persona) {
			personaRepository.delete(persona);
		}
	}
	
	@Override
	public CrudPersonaModel obtenerEmpresaByPersona(Integer id) {
		List<Persona> listPersona = new ArrayList<Persona>();
		List<PersonaModel> listPersonaModel = new ArrayList<PersonaModel>();
		CrudPersonaModel crudPersonaModel = new CrudPersonaModel();
		
		listPersona = personaRepository.listEmpresaByPersona(id);
		
		for (Persona persona : listPersona) {
			listPersonaModel.add(personaConverter.convertPersonaToPersonaModel(persona));
		}
		
		crudPersonaModel.setRows(listPersonaModel);
		
		return crudPersonaModel;
	}
}
