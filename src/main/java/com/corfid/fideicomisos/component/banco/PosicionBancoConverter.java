package com.corfid.fideicomisos.component.banco;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.stereotype.Component;
import com.corfid.fideicomisos.model.banco.PosicionBancoModel;

@Component("posicionBancoConverter")
public class PosicionBancoConverter {

	public PosicionBancoModel convertToPosicionBancoModel(Object[] objectPosicionBanco) {

		PosicionBancoModel posicionBancoModel = new PosicionBancoModel();

		posicionBancoModel.setIdentificadorFideicomiso((Integer) objectPosicionBanco[0]);
		posicionBancoModel.setNombreFideicomiso((String) objectPosicionBanco[1]);
		posicionBancoModel.setAcronimoEntidadFinanciera((String) objectPosicionBanco[2]);
		posicionBancoModel.setIsoMonedaCuentaEntidadFinanciera((String) objectPosicionBanco[3]);
		posicionBancoModel.setNumeroCuentaEntidadFinanciera((String) objectPosicionBanco[4]);
		posicionBancoModel.setDescripcionCuentaEntidadFinanciera((String) objectPosicionBanco[5]);
		posicionBancoModel.setSaldoContableActual(((BigDecimal) objectPosicionBanco[6]).doubleValue());
		posicionBancoModel.setSaldoDisponibleActual(((BigDecimal) objectPosicionBanco[7]).doubleValue());
		posicionBancoModel.setFechaUltimaActualizacion((Date) objectPosicionBanco[8]);
		posicionBancoModel.setIdentificadorCuentaEntidadFinanciera((Integer) objectPosicionBanco[9]);

		return posicionBancoModel;
	}

}
