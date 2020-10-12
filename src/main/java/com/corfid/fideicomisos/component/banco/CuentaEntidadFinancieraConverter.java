package com.corfid.fideicomisos.component.banco;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.banco.CuentaEntidadFinanciera;
import com.corfid.fideicomisos.model.banco.CuentaEntidadFinancieraModel;

@Component("cuentaEntidadFinancieraConverter")
public class CuentaEntidadFinancieraConverter {
	
	public CuentaEntidadFinancieraModel convertCuentaEntidadFinancieraToCuentaEntidadFinancieraModel(CuentaEntidadFinanciera cuentaEntidadFinanciera) {
		
		CuentaEntidadFinancieraModel cuentaEntidadFinancieraModel = new CuentaEntidadFinancieraModel();
		
		String descripcionMonedaIntegrada = cuentaEntidadFinanciera.getIsoMonedaCuentaEntidadFinanciera().concat(" - ").concat(cuentaEntidadFinanciera.getDescripcionMonedaCuentaEntidadFinanciera());
		
		cuentaEntidadFinancieraModel.setIdentificadorCuentaEntidadFinanciera(cuentaEntidadFinanciera.getIdentificadorCuentaEntidadFinanciera());
		cuentaEntidadFinancieraModel.setIdentificadorFideicomiso(cuentaEntidadFinanciera.getFideicomiso().getIdentificadorFideicomiso());
		cuentaEntidadFinancieraModel.setNumeroCuentaEntidadFinanciera(cuentaEntidadFinanciera.getNumeroCuentaEntidadFinanciera());
		cuentaEntidadFinancieraModel.setNombreEntidadFinanciera(cuentaEntidadFinanciera.getNombreEntidadFinanciera());
		cuentaEntidadFinancieraModel.setAcronimoEntidadFinanciera(cuentaEntidadFinanciera.getAcronimoEntidadFinanciera());
		cuentaEntidadFinancieraModel.setTipoEntidadFinanciera(cuentaEntidadFinanciera.getTipoEntidadFinanciera());
		cuentaEntidadFinancieraModel.setDescripcionCuentaEntidadFinanciera(cuentaEntidadFinanciera.getDescripcionCuentaEntidadFinanciera());
		cuentaEntidadFinancieraModel.setCodigoMoneda(cuentaEntidadFinanciera.getCodigoMoneda());
		cuentaEntidadFinancieraModel.setIsoMonedaCuentaEntidadFinanciera(cuentaEntidadFinanciera.getIsoMonedaCuentaEntidadFinanciera());
		cuentaEntidadFinancieraModel.setDescripcionMonedaCuentaEntidadFinanciera(cuentaEntidadFinanciera.getDescripcionMonedaCuentaEntidadFinanciera());
		cuentaEntidadFinancieraModel.setDescripcionMonedaIntegrada(descripcionMonedaIntegrada);
		cuentaEntidadFinancieraModel.setSaldoContableActual(cuentaEntidadFinanciera.getSaldoContableActual());
		cuentaEntidadFinancieraModel.setSaldoDisponibleActual(cuentaEntidadFinanciera.getSaldoDisponibleActual());
		cuentaEntidadFinancieraModel.setCodigoEstado(cuentaEntidadFinanciera.getCodigoEstado());
		cuentaEntidadFinancieraModel.setDescripcionEstado(cuentaEntidadFinanciera.getDescripcionEstado());
		cuentaEntidadFinancieraModel.setFechaUltimaActualizacion(cuentaEntidadFinanciera.getFechaUltimaActualizacion());
				
		return cuentaEntidadFinancieraModel;
		
	}
	

}
