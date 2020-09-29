package com.corfid.fideicomisos.component.administrativo;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.administrativo.Empresa;
import com.corfid.fideicomisos.model.administrativo.EmpresaModel;

@Component("empresaConverter")
public class EmpresaConverter {
	public Empresa convertMenuModelToMenu(EmpresaModel empresaModel) {
		Empresa empresa = new Empresa();
		empresa.setIdEmpresa(empresaModel.getIdEmpresa());
		empresa.setEstadoRegistro(empresaModel.getEstadoRegistro());

		return empresa;
	}

	public EmpresaModel convertMenuToMenuModel(Empresa empresa) {
		EmpresaModel empresaModel = new EmpresaModel();

		empresaModel.setIdEmpresa(empresa.getIdEmpresa());
		empresaModel.setIdPersona(empresa.getPersona().getIdPersona());
		empresaModel.setRazonSocial(empresa.getPersona().getRazonSocial());
		empresaModel.setNumeroDocumento(empresa.getPersona().getNumeroDocumento());
		empresaModel.setEstadoRegistro(empresa.getEstadoRegistro());

		return empresaModel;
	}
}
