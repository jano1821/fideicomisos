package com.corfid.fideicomisos.service.banco;

import com.corfid.fideicomisos.model.banco.PosicionBancoModel;

public interface FideicomisoInterface {

	public PosicionBancoModel getListaFideicomiso(String cadenaBusqueda, String numeroDocumento, Integer pagina,
			Integer cantRegistros);

	public PosicionBancoModel getListFideicomisoCuentaEntidadFinancieraByFideicomisario(String numeroDocumento,
			Integer pagina, Integer cantRegistros);

	public PosicionBancoModel getListFideicomisoCuentaEntidadFinancieraByFideicomisarioNombreFideicomiso(
			String nombreFideicomiso, String numeroDocumento, Integer pagina, Integer cantRegistros);

	public PosicionBancoModel getListFideicomisoCuentaEntidadFinancieraByFideicomisarioMoneda(String numeroDocumento,
			String monedaCuenta, Integer pagina, Integer cantRegistros);

	public PosicionBancoModel getListFideicomisoCuentaEntidadFinancieraByFideicomisarioMonedaNombreFideicomiso(
			String nombreFideicomiso, String numeroDocumento, String monedaCuenta, Integer pagina,
			Integer cantRegistros);

}
