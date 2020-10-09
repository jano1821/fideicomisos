package com.corfid.fideicomisos.component.banco;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.banco.CuentaEntidadFinanciera;
import com.corfid.fideicomisos.model.banco.CuentaEntidadFinancieraModel;

@Component("cuentaEntidadFinancieraConverter")
public class CuentaEntidadFinancieraConverter {
	
	public CuentaEntidadFinancieraModel convertCuentaEntidadFinancieraToCuentaEntidadFinancieraModel(CuentaEntidadFinanciera cuentaEntidadFinanciera) {
		
		CuentaEntidadFinancieraModel cuentaEntidadFinancieraModel = new CuentaEntidadFinancieraModel();
		
		cuentaEntidadFinancieraModel.setIdentificadorCuentaEntidadFinanciera(cuentaEntidadFinanciera.getIdentificadorCuentaEntidadFinanciera());
		cuentaEntidadFinancieraModel.setIdentificadorFideicomiso(cuentaEntidadFinanciera.getFideicomiso().getIdentificadorFideicomiso());
		cuentaEntidadFinancieraModel.setNumeroCuentaEntidadFinanciera(cuentaEntidadFinanciera.getNumeroCuentaEntidadFinanciera());
		cuentaEntidadFinancieraModel.setNombreEntidadFinanciera(cuentaEntidadFinanciera.getNombreEntidadFinanciera());
		cuentaEntidadFinancieraModel.setAcronimoEntidadFinanciera(cuentaEntidadFinanciera.getAcronimoEntidadFinanciera());
		cuentaEntidadFinancieraModel.setTipoEntidadFinanciera(cuentaEntidadFinanciera.getTipoEntidadFinanciera());
		/*cuentaEntidadFinancieraModel.
		cuentaEntidadFinancieraModel.
		cuentaEntidadFinancieraModel.
		cuentaEntidadFinancieraModel.
		cuentaEntidadFinancieraModel.
		cuentaEntidadFinancieraModel.
		cuentaEntidadFinancieraModel.
		cuentaEntidadFinancieraModel.
		cuentaEntidadFinancieraModel.
		cuentaEntidadFinancieraModel.
		cuentaEntidadFinancieraModel.
		cuentaEntidadFinancieraModel.*/
		
		return cuentaEntidadFinancieraModel;
		
	}
	

}
