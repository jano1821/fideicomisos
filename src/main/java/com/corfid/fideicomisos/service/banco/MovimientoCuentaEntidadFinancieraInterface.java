package com.corfid.fideicomisos.service.banco;

import com.corfid.fideicomisos.model.banco.MovimientoCuentaEntidadFinancieraModel;

public interface MovimientoCuentaEntidadFinancieraInterface {

	public MovimientoCuentaEntidadFinancieraModel getMovimientoCuentaEntidadFinancieraByIdCuenta(
			Integer identificadorCuentaEntidadFinanciera, Integer pagina, Integer cantRegistros);

}
