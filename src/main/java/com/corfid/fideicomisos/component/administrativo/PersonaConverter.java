package com.corfid.fideicomisos.component.administrativo;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.administrativo.Persona;
import com.corfid.fideicomisos.model.administrativo.PersonaModel;
import com.corfid.fideicomisos.utilities.StringUtil;

@Component("personaConverter")
public class PersonaConverter {

	public Persona convertPersonaModelToPersona(PersonaModel personaModel) {
		Persona persona = new Persona();
		persona.setIdPersona(personaModel.getIdPersona());
		persona.setNombres(personaModel.getNombres());
		persona.setApePat(personaModel.getApePat());
		persona.setApeMat(personaModel.getApeMat());
		persona.setRazonSocial(personaModel.getRazonSocial());
		persona.setTipoPersona(personaModel.getTipoPersona());
		persona.setNombreCompleto(personaModel.getNombres() + " " + personaModel.getApePat() + " " + personaModel.getApeMat());
		persona.setTipoDocumento(personaModel.getTipoDocumento());
		persona.setNumeroDocumento(personaModel.getNumeroDocumento());
		persona.setEstadoRegistro(personaModel.getEstadoRegistro());

		return persona;

	}

	public Persona convertPersonaModelToPersonaExistente(Persona persona, PersonaModel personaModel) {
		persona.setIdPersona(personaModel.getIdPersona());
		persona.setNombres(personaModel.getNombres());
		persona.setApePat(personaModel.getApePat());
		persona.setApeMat(personaModel.getApeMat());
		persona.setRazonSocial(personaModel.getRazonSocial());
		persona.setTipoPersona(personaModel.getTipoPersona());
		persona.setNombreCompleto(personaModel.getNombres() + " " + personaModel.getApePat() + " " + personaModel.getApeMat());
		persona.setTipoDocumento(personaModel.getTipoDocumento());
		persona.setNumeroDocumento(personaModel.getNumeroDocumento());
		persona.setEstadoRegistro(personaModel.getEstadoRegistro());

		return persona;

	}

	public PersonaModel convertPersonaToPersonaModel(Persona persona) {
		PersonaModel personaModel = new PersonaModel();
		personaModel.setIdPersona(persona.getIdPersona());
		personaModel.setNombres(persona.getNombres());
		personaModel.setApePat(persona.getApePat());
		personaModel.setApeMat(persona.getApeMat());
		personaModel.setRazonSocial(persona.getRazonSocial());
		personaModel.setTipoPersona(persona.getTipoPersona());
		personaModel.setEstadoRegistro(persona.getEstadoRegistro());
		personaModel.setNombreCompleto(persona.getNombreCompleto());
		personaModel.setTipoDocumento(persona.getTipoDocumento());
		personaModel.setDescTipoDocumento(persona.getTipoDocumento());
		personaModel.setNumeroDocumento(persona.getNumeroDocumento());

		if (StringUtil.equiv(persona.getTipoPersona(), "N")) {
			personaModel.setDescTipoPersona("Natural");
		} else if (StringUtil.equiv(persona.getTipoPersona(), "J")) {
			personaModel.setDescTipoPersona("Juridica");
		} else {
			personaModel.setDescTipoPersona("Desconocido");
		}

		if (StringUtil.equiv(persona.getEstadoRegistro(), "S")) {
			personaModel.setDescEstadoRegistro("Vigente");
		} else if (StringUtil.equiv(persona.getEstadoRegistro(), "N")) {
			personaModel.setDescEstadoRegistro("No Vigente");
		} else {
			personaModel.setDescEstadoRegistro("Desconocido");
		}

		return personaModel;
	}

}
