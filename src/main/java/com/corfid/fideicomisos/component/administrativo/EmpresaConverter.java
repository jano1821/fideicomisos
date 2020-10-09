package com.corfid.fideicomisos.component.administrativo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.administrativo.Empresa;
import com.corfid.fideicomisos.model.administrativo.EmpresaModel;
import com.corfid.fideicomisos.service.administrativo.EmpresaInterface;
import com.corfid.fideicomisos.service.administrativo.PersonaInterface;

@Component("empresaConverter")
public class EmpresaConverter {
    
    @Autowired
    @Qualifier("personaServiceImpl")
    PersonaInterface personaInterface;
    
	public Empresa convertEmpresaModelToEmpresa(EmpresaModel empresaModel) {
		Empresa empresa = new Empresa();
		empresa.setIdEmpresa(empresaModel.getIdEmpresa());
		empresa.setEstadoRegistro(empresaModel.getEstadoRegistro());
		empresa.setPersona(personaInterface.findPersonaById(empresaModel.getIdPersona()));

		return empresa;
	}

	public EmpresaModel convertEmpresaToEmpresaModel(Empresa empresa) {
		EmpresaModel empresaModel = new EmpresaModel();

		empresaModel.setIdEmpresa(empresa.getIdEmpresa());
		empresaModel.setIdPersona(empresa.getIdEmpresa());
		empresaModel.setRazonSocial(empresa.getPersona().getRazonSocial());
		empresaModel.setNumeroDocumento(empresa.getPersona().getNumeroDocumento());
		empresaModel.setEstadoRegistro(empresa.getEstadoRegistro());

		return empresaModel;
	}
}
