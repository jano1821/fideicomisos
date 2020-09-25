package com.corfid.fideicomisos.component.banco;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.banco.CuentaEntidadFinanciera;
import com.corfid.fideicomisos.entity.banco.Fideicomiso;
import com.corfid.fideicomisos.model.banco.PosicionBancoModel;

@Component("posicionBancoConverter")
public class PosicionBancoConverter {

	public PosicionBancoModel convertToPosicionBancoModel(Fideicomiso fideicomiso,
			CuentaEntidadFinanciera cuentaEntidadFinanciera) {

		PosicionBancoModel posicionBancoModel = new PosicionBancoModel();

		posicionBancoModel.setIdentificadorFideicomiso(fideicomiso.getIdentificadorFideicomiso());
		posicionBancoModel.setNombreFideicomiso(fideicomiso.getNombreFideicomiso());
		posicionBancoModel.setNombreEntidadFinanciera(cuentaEntidadFinanciera.getNombreEntidadFinanciera());
		posicionBancoModel.setDescripcionMonedaCuentaEntidadFinanciera(cuentaEntidadFinanciera.getDescripcionMonedaCuentaEntidadFinanciera());
		posicionBancoModel.setNumeroCuentaEntidadFinanciera(cuentaEntidadFinanciera.getNumeroCuentaEntidadFinanciera());
		posicionBancoModel.setDescripcionCuentaEntidadFinanciera(cuentaEntidadFinanciera.getDescripcionCuentaEntidadFinanciera());
		posicionBancoModel.setSaldoContableActual(cuentaEntidadFinanciera.getSaldoContableActual());
		posicionBancoModel.setSaldoDisponibleActual(cuentaEntidadFinanciera.getSaldoDisponibleActual());
		posicionBancoModel.setFechaUltimaActualizacion(cuentaEntidadFinanciera.getFechaUltimaActualizacion());

		return posicionBancoModel;
	}

}
