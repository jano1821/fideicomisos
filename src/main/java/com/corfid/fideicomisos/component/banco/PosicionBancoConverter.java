package com.corfid.fideicomisos.component.banco;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.banco.CuentaEntidadFinanciera;
import com.corfid.fideicomisos.entity.banco.Fideicomiso;
import com.corfid.fideicomisos.model.banco.PosicionBancoModel;

@Component("posicionBancoConverter")
public class PosicionBancoConverter {

	public PosicionBancoModel convertToPosicionBancoModel(Object[] objectPosicionBanco) {

		PosicionBancoModel posicionBancoModel = new PosicionBancoModel();

		posicionBancoModel.setIdentificadorFideicomiso((Integer) objectPosicionBanco[0]);
		posicionBancoModel.setNombreFideicomiso((String) objectPosicionBanco[1]);
		posicionBancoModel.setNombreEntidadFinanciera((String) objectPosicionBanco[2]);
		posicionBancoModel.setDescripcionMonedaCuentaEntidadFinanciera((String) objectPosicionBanco[3]);
		posicionBancoModel.setNumeroCuentaEntidadFinanciera((String) objectPosicionBanco[4]);
		posicionBancoModel.setDescripcionCuentaEntidadFinanciera((String) objectPosicionBanco[5]);
		posicionBancoModel.setSaldoContableActual((Double) objectPosicionBanco[6]);
		posicionBancoModel.setSaldoDisponibleActual((Double) objectPosicionBanco[7]);
		posicionBancoModel.setFechaUltimaActualizacion((Date) objectPosicionBanco[8]);

		return posicionBancoModel;
	}

}
