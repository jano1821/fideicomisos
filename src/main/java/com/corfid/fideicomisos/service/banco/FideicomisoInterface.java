package com.corfid.fideicomisos.service.banco;

import java.util.Date;
import java.util.List;

import com.corfid.fideicomisos.model.banco.FideicomisoModel;
import com.corfid.fideicomisos.model.banco.PosicionBancoModel;
import com.corfid.fideicomisos.model.banco.SaldoTotalMonedaModel;

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

	public SaldoTotalMonedaModel obtenerSaldoTotalMonedaFideicomiso(String cadenaBusqueda, String numeroDocumento,
			String codigoMoneda, Date fechaProceso);

	public SaldoTotalMonedaModel getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisario(
			Integer identificadorFideicomisario, Date fechaProceso);

	public SaldoTotalMonedaModel getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisarioNombreFideicomiso(
			Integer identificadorFideicomisario, String nombreFideicomiso, Date fechaProceso);

	public SaldoTotalMonedaModel getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisarioMoneda(
			Integer identificadorFideicomisario, String codigoMoneda, Date fechaProceso);

	public SaldoTotalMonedaModel getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisarioMonedaNombreFideicomiso(
			Integer identificadorFideicomisario, String nombreFideicomiso, String codigoMoneda, Date fechaProceso);

	public FideicomisoModel getFideicomisoModel(Integer identificadorCuentaEntidadFinanciera);
	
	public FideicomisoModel getFideicomisoByIdFideicomisoModel(Integer identificadorFideicomiso);
	
    public List<FideicomisoModel> getFideicomisoModel(String rucFideicomisario) throws Exception;
}
