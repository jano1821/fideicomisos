package com.corfid.fideicomisos.service.banco;

import com.corfid.fideicomisos.model.banco.PosicionBancoModel;

public interface FideicomisoInterface {

	public PosicionBancoModel getListaFideicomiso(String cadenaBusqueda, String numeroDocumento, String codigoMoneda,
			Integer pagina, Integer cantRegistros);

	public PosicionBancoModel getListFideicomisoCuentaEntidadFinancieraByFideicomisario(
			Integer identificadorFideicomisario, Integer pagina, Integer cantRegistros);

	public PosicionBancoModel getListFideicomisoCuentaEntidadFinancieraByFideicomisarioNombreFideicomiso(
			Integer identificadorFideicomisario, String nombreFideicomiso, Integer pagina, Integer cantRegistros);

	public PosicionBancoModel getListFideicomisoCuentaEntidadFinancieraByFideicomisarioMoneda(
			Integer identificadorFideicomisario, String monedaCuenta, Integer pagina, Integer cantRegistros);

	public PosicionBancoModel getListFideicomisoCuentaEntidadFinancieraByFideicomisarioMonedaNombreFideicomiso(
			Integer identificadorFideicomisario, String nombreFideicomiso, String monedaCuenta, Integer pagina,
			Integer cantRegistros);

}
