package com.corfid.fideicomisos.service.banco;

import com.corfid.fideicomisos.model.banco.PosicionBancoModel;

public interface FideicomisoInterface {

	public PosicionBancoModel getListFideicomisoCuentaEntidadFinancieraFromFideicomisario(
			String identificadorFideicomisario, Integer pagina, Integer cantRegistros);

}
