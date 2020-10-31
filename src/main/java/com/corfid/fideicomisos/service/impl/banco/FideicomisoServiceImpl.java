package com.corfid.fideicomisos.service.impl.banco;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.banco.PosicionBancoConverter;
import com.corfid.fideicomisos.entity.banco.CuentaEntidadFinanciera;
import com.corfid.fideicomisos.model.banco.FideicomisarioModel;
import com.corfid.fideicomisos.model.banco.FideicomisoModel;
import com.corfid.fideicomisos.model.banco.PosicionBancoModel;
import com.corfid.fideicomisos.model.banco.SaldoTotalMonedaModel;
import com.corfid.fideicomisos.model.banco.TipoCambioSBSModel;
import com.corfid.fideicomisos.repository.banco.CuentaEntidadFinancieraRepository;
import com.corfid.fideicomisos.repository.banco.FideicomisoRepository;
import com.corfid.fideicomisos.service.banco.FideicomisarioInterface;
import com.corfid.fideicomisos.service.banco.FideicomisoInterface;
import com.corfid.fideicomisos.service.banco.TipoCambioSBSInterface;
import com.corfid.fideicomisos.utilities.AbstractService;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.StringUtil;

@Service("fideicomisoServiceImpl")
public class FideicomisoServiceImpl extends AbstractService implements FideicomisoInterface {

	@Autowired
	@Qualifier("fideicomisoRepository")
	private FideicomisoRepository fideicomisoRepository;

	@Autowired
	@Qualifier("posicionBancoConverter")
	private PosicionBancoConverter posicionBancoConverter;

	@Autowired
	@Qualifier("fideicomisarioServiceImpl")
	private FideicomisarioInterface fideicomisarioInterface;

	@Autowired
	@Qualifier("tipoCambioSBSServiceImpl")
	private TipoCambioSBSInterface tipoCambioSBSInterface;

	@Autowired
	@Qualifier("cuentaEntidadFinancieraRepository")
	private CuentaEntidadFinancieraRepository cuentaEntidadFinancieraRepository;

	public PosicionBancoModel getListaFideicomiso(String cadenaBusqueda, String numeroDocumento, String codigoMoneda,
			Integer pagina, Integer cantRegistros) {

		PosicionBancoModel posicionBancoModel = new PosicionBancoModel();

		String cadenaFideicomiso = null;
		String nombreFideicomisario = null;

		Integer identificadorFideicomisario = null;

		FideicomisarioModel fideicomisarioModel = new FideicomisarioModel();

		fideicomisarioModel = fideicomisarioInterface.getFideicomisarioByNumeroDocumento(numeroDocumento);
		identificadorFideicomisario = fideicomisarioModel.getIdentificadorFideicomisario();
		nombreFideicomisario = fideicomisarioModel.getNombreFideicomisario();

		if (StringUtil.isEmpty(codigoMoneda)) {
			if (StringUtil.isEmpty(cadenaBusqueda)) {
				posicionBancoModel = getListFideicomisoCuentaEntidadFinancieraByFideicomisario(
						identificadorFideicomisario, pagina, cantRegistros);
			} else {
				cadenaFideicomiso = Constante.COMODIN_LIKE + cadenaBusqueda + Constante.COMODIN_LIKE;
				posicionBancoModel = getListFideicomisoCuentaEntidadFinancieraByFideicomisarioNombreFideicomiso(
						identificadorFideicomisario, cadenaFideicomiso, pagina, cantRegistros);
			}
		} else {
			if (StringUtil.isEmpty(cadenaBusqueda)) {
				posicionBancoModel = getListFideicomisoCuentaEntidadFinancieraByFideicomisarioMoneda(
						identificadorFideicomisario, codigoMoneda, pagina, cantRegistros);
			} else {
				cadenaFideicomiso = Constante.COMODIN_LIKE + cadenaBusqueda + Constante.COMODIN_LIKE;
				posicionBancoModel = getListFideicomisoCuentaEntidadFinancieraByFideicomisarioMonedaNombreFideicomiso(
						identificadorFideicomisario, cadenaFideicomiso, codigoMoneda, pagina, cantRegistros);
			}

		}

		posicionBancoModel.setNombreFideicomisario(nombreFideicomisario);

		return posicionBancoModel;
	}

	public PosicionBancoModel getListFideicomisoCuentaEntidadFinancieraByFideicomisario(
			Integer identificadorFideicomisario, Integer pagina, Integer cantRegistros) {

		List<Object> listFideicomisoCuentaEntidadFinanciera;
		List<PosicionBancoModel> listPosicionBancoModel;

		Page<Object> pageFideicomisoCuentaEntidadFinanciera;

		PosicionBancoModel posicionBancoModel = new PosicionBancoModel();

		pageFideicomisoCuentaEntidadFinanciera = fideicomisoRepository
				.getListFideicomisoCuentaEntidadFinancieraByIdFideicomisario(identificadorFideicomisario,
						obtenerIndexPorPagina(pagina, cantRegistros, null, false, false));

		posicionBancoModel.setPaginaFinal(pageFideicomisoCuentaEntidadFinanciera.getTotalPages());
		posicionBancoModel.setCantidadRegistros(_toInteger(pageFideicomisoCuentaEntidadFinanciera.getTotalElements()));

		listFideicomisoCuentaEntidadFinanciera = pageFideicomisoCuentaEntidadFinanciera.getContent();

		listPosicionBancoModel = new ArrayList<PosicionBancoModel>();

		for (int i = 0; i < listFideicomisoCuentaEntidadFinanciera.size(); i++) {
			Object[] objectPosicionBanco = (Object[]) listFideicomisoCuentaEntidadFinanciera.get(i);
			listPosicionBancoModel.add(posicionBancoConverter.convertToPosicionBancoModel(objectPosicionBanco));
		}

		posicionBancoModel.setRows(listPosicionBancoModel);

		return posicionBancoModel;
	}

	public PosicionBancoModel getListFideicomisoCuentaEntidadFinancieraByFideicomisarioNombreFideicomiso(
			Integer identificadorFideicomisario, String nombreFideicomiso, Integer pagina, Integer cantRegistros) {

		List<Object> listFideicomisoCuentaEntidadFinanciera;
		List<PosicionBancoModel> listPosicionBancoModel;

		Page<Object> pageFideicomisoCuentaEntidadFinanciera;

		PosicionBancoModel posicionBancoModel = new PosicionBancoModel();

		pageFideicomisoCuentaEntidadFinanciera = fideicomisoRepository
				.getListFideicomisoCuentaEntidadFinancieraByIdFideicomisarioNombreFideicomiso(
						identificadorFideicomisario, nombreFideicomiso,
						obtenerIndexPorPagina(pagina, cantRegistros, null, false, false));

		posicionBancoModel.setPaginaFinal(pageFideicomisoCuentaEntidadFinanciera.getTotalPages());
		posicionBancoModel.setCantidadRegistros(_toInteger(pageFideicomisoCuentaEntidadFinanciera.getTotalElements()));

		listFideicomisoCuentaEntidadFinanciera = pageFideicomisoCuentaEntidadFinanciera.getContent();

		listPosicionBancoModel = new ArrayList<PosicionBancoModel>();

		for (int i = 0; i < listFideicomisoCuentaEntidadFinanciera.size(); i++) {
			Object[] objectPosicionBanco = (Object[]) listFideicomisoCuentaEntidadFinanciera.get(i);
			listPosicionBancoModel.add(posicionBancoConverter.convertToPosicionBancoModel(objectPosicionBanco));
		}

		posicionBancoModel.setRows(listPosicionBancoModel);

		return posicionBancoModel;
	}

	public PosicionBancoModel getListFideicomisoCuentaEntidadFinancieraByFideicomisarioMoneda(
			Integer identificadorFideicomisario, String monedaCuenta, Integer pagina, Integer cantRegistros) {

		List<Object> listFideicomisoCuentaEntidadFinanciera;
		List<PosicionBancoModel> listPosicionBancoModel;

		Page<Object> pageFideicomisoCuentaEntidadFinanciera;

		PosicionBancoModel posicionBancoModel = new PosicionBancoModel();

		pageFideicomisoCuentaEntidadFinanciera = fideicomisoRepository
				.getListFideicomisoCuentaEntidadFinancieraByIdFideicomisarioMoneda(identificadorFideicomisario,
						monedaCuenta, obtenerIndexPorPagina(pagina, cantRegistros, null, false, false));

		posicionBancoModel.setPaginaFinal(pageFideicomisoCuentaEntidadFinanciera.getTotalPages());
		posicionBancoModel.setCantidadRegistros(_toInteger(pageFideicomisoCuentaEntidadFinanciera.getTotalElements()));

		listFideicomisoCuentaEntidadFinanciera = pageFideicomisoCuentaEntidadFinanciera.getContent();

		listPosicionBancoModel = new ArrayList<PosicionBancoModel>();

		for (int i = 0; i < listFideicomisoCuentaEntidadFinanciera.size(); i++) {
			Object[] objectPosicionBanco = (Object[]) listFideicomisoCuentaEntidadFinanciera.get(i);
			listPosicionBancoModel.add(posicionBancoConverter.convertToPosicionBancoModel(objectPosicionBanco));
		}

		posicionBancoModel.setRows(listPosicionBancoModel);

		return posicionBancoModel;
	}

	public PosicionBancoModel getListFideicomisoCuentaEntidadFinancieraByFideicomisarioMonedaNombreFideicomiso(
			Integer identificadorFideicomisario, String nombreFideicomiso, String monedaCuenta, Integer pagina,
			Integer cantRegistros) {

		List<Object> listFideicomisoCuentaEntidadFinanciera;
		List<PosicionBancoModel> listPosicionBancoModel;

		Page<Object> pageFideicomisoCuentaEntidadFinanciera;

		PosicionBancoModel posicionBancoModel = new PosicionBancoModel();

		pageFideicomisoCuentaEntidadFinanciera = fideicomisoRepository
				.getListFideicomisoCuentaEntidadFinancieraByIdFideicomisarioMonedaNombreFideicomiso(
						identificadorFideicomisario, monedaCuenta, nombreFideicomiso,
						obtenerIndexPorPagina(pagina, cantRegistros, null, false, false));

		posicionBancoModel.setPaginaFinal(pageFideicomisoCuentaEntidadFinanciera.getTotalPages());
		posicionBancoModel.setCantidadRegistros(_toInteger(pageFideicomisoCuentaEntidadFinanciera.getTotalElements()));

		listFideicomisoCuentaEntidadFinanciera = pageFideicomisoCuentaEntidadFinanciera.getContent();

		listPosicionBancoModel = new ArrayList<PosicionBancoModel>();

		for (int i = 0; i < listFideicomisoCuentaEntidadFinanciera.size(); i++) {
			Object[] objectPosicionBanco = (Object[]) listFideicomisoCuentaEntidadFinanciera.get(i);
			listPosicionBancoModel.add(posicionBancoConverter.convertToPosicionBancoModel(objectPosicionBanco));
		}

		posicionBancoModel.setRows(listPosicionBancoModel);

		return posicionBancoModel;
	}

	public SaldoTotalMonedaModel obtenerSaldoTotalMonedaFideicomiso(String cadenaBusqueda, String numeroDocumento,
			String codigoMoneda, Date fechaProceso) {

		FideicomisarioModel fideicomisarioModel = new FideicomisarioModel();
		SaldoTotalMonedaModel saldoTotalMonedaModel = new SaldoTotalMonedaModel();

		Integer identificadorFideicomisario = null;

		String cadenaFideicomiso = null;

		fideicomisarioModel = fideicomisarioInterface.getFideicomisarioByNumeroDocumento(numeroDocumento);
		identificadorFideicomisario = fideicomisarioModel.getIdentificadorFideicomisario();

		if (StringUtil.isEmpty(codigoMoneda)) {
			if (StringUtil.isEmpty(cadenaBusqueda)) {
				saldoTotalMonedaModel = getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisario(
						identificadorFideicomisario, fechaProceso);
			} else {
				cadenaFideicomiso = Constante.COMODIN_LIKE + cadenaBusqueda + Constante.COMODIN_LIKE;
				saldoTotalMonedaModel = getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisarioNombreFideicomiso(
						identificadorFideicomisario, cadenaFideicomiso, fechaProceso);
			}

		} else {
			if (StringUtil.isEmpty(cadenaBusqueda)) {
				saldoTotalMonedaModel = getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisarioMoneda(
						identificadorFideicomisario, codigoMoneda, fechaProceso);
			} else {
				cadenaFideicomiso = Constante.COMODIN_LIKE + cadenaBusqueda + Constante.COMODIN_LIKE;
				saldoTotalMonedaModel = getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisarioMonedaNombreFideicomiso(
						identificadorFideicomisario, cadenaFideicomiso, codigoMoneda, fechaProceso);

			}
		}

		return saldoTotalMonedaModel;

	}

	public SaldoTotalMonedaModel getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisario(
			Integer identificadorFideicomisario, Date fechaProceso) {

		Double tipoCambioSBS;
		Double saldoContableSolesCuentaFideicomiso = new Double(0);
		Double saldoDisponibleSolesCuentaFideicomiso = new Double(0);
		Double saldoContableDolaresCuentaFideicomiso = new Double(0);
		Double saldoDisponibleDolaresCuentaFideicomiso = new Double(0);

		Double saldoConsolidadoContableSolesCuentaFideicomiso = new Double(0);
		Double saldoConsolidadoDisponibleSolesCuentaFideicomiso = new Double(0);
		Double saldoConsolidadoContableDolaresCuentaFideicomiso = new Double(0);
		Double saldoConsolidadoDisponibleDolaresCuentaFideicomiso = new Double(0);

		String codigoMoneda = null;

		TipoCambioSBSModel tipoCambioSBSModel = new TipoCambioSBSModel();
		SaldoTotalMonedaModel saldoTotalMonedaModel = new SaldoTotalMonedaModel();

		List<Object> listFideicomisoCuentaEntidadFinanciera;

		tipoCambioSBSModel = tipoCambioSBSInterface.getTipoCambioSBSByFechaProceso(fechaProceso);
		tipoCambioSBS = tipoCambioSBSModel.getMontoTipoCambio();

		listFideicomisoCuentaEntidadFinanciera = fideicomisoRepository
				.getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisario(
						identificadorFideicomisario);

		for (int i = 0; i < listFideicomisoCuentaEntidadFinanciera.size(); i++) {

			Object[] fila = (Object[]) listFideicomisoCuentaEntidadFinanciera.get(i);

			codigoMoneda = (String) fila[10];

			if (StringUtil.equiv(Constante.CODIGO_MONEDA_SOLES, codigoMoneda)) {
				saldoContableSolesCuentaFideicomiso = saldoContableSolesCuentaFideicomiso
						+ ((BigDecimal) fila[6]).doubleValue();
				saldoDisponibleSolesCuentaFideicomiso = saldoDisponibleSolesCuentaFideicomiso
						+ ((BigDecimal) fila[7]).doubleValue();
			} else {
				saldoContableDolaresCuentaFideicomiso = saldoContableDolaresCuentaFideicomiso
						+ ((BigDecimal) fila[6]).doubleValue();
				saldoDisponibleDolaresCuentaFideicomiso = saldoDisponibleDolaresCuentaFideicomiso
						+ ((BigDecimal) fila[7]).doubleValue();
			}

		}

		saldoConsolidadoContableSolesCuentaFideicomiso = saldoContableSolesCuentaFideicomiso
				+ (saldoContableDolaresCuentaFideicomiso * tipoCambioSBS);
		saldoConsolidadoDisponibleSolesCuentaFideicomiso = saldoDisponibleSolesCuentaFideicomiso
				+ (saldoDisponibleDolaresCuentaFideicomiso * tipoCambioSBS);

		saldoConsolidadoContableDolaresCuentaFideicomiso = saldoContableDolaresCuentaFideicomiso
				+ (saldoContableSolesCuentaFideicomiso / tipoCambioSBS);
		saldoConsolidadoDisponibleDolaresCuentaFideicomiso = saldoDisponibleDolaresCuentaFideicomiso
				+ (saldoDisponibleSolesCuentaFideicomiso / tipoCambioSBS);

		saldoTotalMonedaModel.setMontoTipoCambio(tipoCambioSBS);
		saldoTotalMonedaModel.setSaldoTotalContableSoles(saldoContableSolesCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalContableDolares(saldoContableDolaresCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalDisponibleSoles(saldoDisponibleSolesCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalDisponibleDolares(saldoDisponibleDolaresCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalConsolidadoContableSoles(saldoConsolidadoContableSolesCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalConsolidadoDisponibleSoles(saldoConsolidadoDisponibleSolesCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalConsolidadoContableDolares(saldoConsolidadoContableDolaresCuentaFideicomiso);
		saldoTotalMonedaModel
				.setSaldoTotalConsolidadoDisponibleDolares(saldoConsolidadoDisponibleDolaresCuentaFideicomiso);

		return saldoTotalMonedaModel;
	}

	public SaldoTotalMonedaModel getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisarioNombreFideicomiso(
			Integer identificadorFideicomisario, String nombreFideicomiso, Date fechaProceso) {

		Double tipoCambioSBS;
		Double saldoContableSolesCuentaFideicomiso = new Double(0);
		Double saldoDisponibleSolesCuentaFideicomiso = new Double(0);
		Double saldoContableDolaresCuentaFideicomiso = new Double(0);
		Double saldoDisponibleDolaresCuentaFideicomiso = new Double(0);

		Double saldoConsolidadoContableSolesCuentaFideicomiso = new Double(0);
		Double saldoConsolidadoDisponibleSolesCuentaFideicomiso = new Double(0);
		Double saldoConsolidadoContableDolaresCuentaFideicomiso = new Double(0);
		Double saldoConsolidadoDisponibleDolaresCuentaFideicomiso = new Double(0);

		String codigoMoneda = null;

		TipoCambioSBSModel tipoCambioSBSModel = new TipoCambioSBSModel();
		SaldoTotalMonedaModel saldoTotalMonedaModel = new SaldoTotalMonedaModel();

		List<Object> listFideicomisoCuentaEntidadFinanciera;

		tipoCambioSBSModel = tipoCambioSBSInterface.getTipoCambioSBSByFechaProceso(fechaProceso);
		tipoCambioSBS = tipoCambioSBSModel.getMontoTipoCambio();

		listFideicomisoCuentaEntidadFinanciera = fideicomisoRepository
				.getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisarioNombreFideicomiso(
						identificadorFideicomisario, nombreFideicomiso);

		for (int i = 0; i < listFideicomisoCuentaEntidadFinanciera.size(); i++) {

			Object[] fila = (Object[]) listFideicomisoCuentaEntidadFinanciera.get(i);

			codigoMoneda = (String) fila[10];

			if (StringUtil.equiv(Constante.CODIGO_MONEDA_SOLES, codigoMoneda)) {
				saldoContableSolesCuentaFideicomiso = saldoContableSolesCuentaFideicomiso
						+ ((BigDecimal) fila[6]).doubleValue();
				saldoDisponibleSolesCuentaFideicomiso = saldoDisponibleSolesCuentaFideicomiso
						+ ((BigDecimal) fila[7]).doubleValue();
			} else {
				saldoContableDolaresCuentaFideicomiso = saldoContableDolaresCuentaFideicomiso
						+ ((BigDecimal) fila[6]).doubleValue();
				saldoDisponibleDolaresCuentaFideicomiso = saldoDisponibleDolaresCuentaFideicomiso
						+ ((BigDecimal) fila[7]).doubleValue();
			}

		}

		saldoConsolidadoContableSolesCuentaFideicomiso = saldoContableSolesCuentaFideicomiso
				+ (saldoContableDolaresCuentaFideicomiso * tipoCambioSBS);
		saldoConsolidadoDisponibleSolesCuentaFideicomiso = saldoDisponibleSolesCuentaFideicomiso
				+ (saldoDisponibleDolaresCuentaFideicomiso * tipoCambioSBS);

		saldoConsolidadoContableDolaresCuentaFideicomiso = saldoContableDolaresCuentaFideicomiso
				+ (saldoContableSolesCuentaFideicomiso / tipoCambioSBS);
		saldoConsolidadoDisponibleDolaresCuentaFideicomiso = saldoDisponibleDolaresCuentaFideicomiso
				+ (saldoDisponibleSolesCuentaFideicomiso / tipoCambioSBS);

		saldoTotalMonedaModel.setMontoTipoCambio(tipoCambioSBS);
		saldoTotalMonedaModel.setSaldoTotalContableSoles(saldoContableSolesCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalContableDolares(saldoContableDolaresCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalDisponibleSoles(saldoDisponibleSolesCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalDisponibleDolares(saldoDisponibleDolaresCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalConsolidadoContableSoles(saldoConsolidadoContableSolesCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalConsolidadoDisponibleSoles(saldoConsolidadoDisponibleSolesCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalConsolidadoContableDolares(saldoConsolidadoContableDolaresCuentaFideicomiso);
		saldoTotalMonedaModel
				.setSaldoTotalConsolidadoDisponibleDolares(saldoConsolidadoDisponibleDolaresCuentaFideicomiso);

		return saldoTotalMonedaModel;
	}

	public SaldoTotalMonedaModel getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisarioMoneda(
			Integer identificadorFideicomisario, String codigoMoneda, Date fechaProceso) {

		Double tipoCambioSBS;
		Double saldoContableSolesCuentaFideicomiso = new Double(0);
		Double saldoDisponibleSolesCuentaFideicomiso = new Double(0);
		Double saldoContableDolaresCuentaFideicomiso = new Double(0);
		Double saldoDisponibleDolaresCuentaFideicomiso = new Double(0);

		Double saldoConsolidadoContableSolesCuentaFideicomiso = new Double(0);
		Double saldoConsolidadoDisponibleSolesCuentaFideicomiso = new Double(0);
		Double saldoConsolidadoContableDolaresCuentaFideicomiso = new Double(0);
		Double saldoConsolidadoDisponibleDolaresCuentaFideicomiso = new Double(0);

		TipoCambioSBSModel tipoCambioSBSModel = new TipoCambioSBSModel();
		SaldoTotalMonedaModel saldoTotalMonedaModel = new SaldoTotalMonedaModel();

		List<Object> listFideicomisoCuentaEntidadFinanciera;

		tipoCambioSBSModel = tipoCambioSBSInterface.getTipoCambioSBSByFechaProceso(fechaProceso);
		tipoCambioSBS = tipoCambioSBSModel.getMontoTipoCambio();

		listFideicomisoCuentaEntidadFinanciera = fideicomisoRepository
				.getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisarioMoneda(
						identificadorFideicomisario, codigoMoneda);

		for (int i = 0; i < listFideicomisoCuentaEntidadFinanciera.size(); i++) {

			Object[] fila = (Object[]) listFideicomisoCuentaEntidadFinanciera.get(i);

			if (StringUtil.equiv(Constante.CODIGO_MONEDA_SOLES, codigoMoneda)) {
				saldoContableSolesCuentaFideicomiso = saldoContableSolesCuentaFideicomiso
						+ ((BigDecimal) fila[6]).doubleValue();
				saldoDisponibleSolesCuentaFideicomiso = saldoDisponibleSolesCuentaFideicomiso
						+ ((BigDecimal) fila[7]).doubleValue();
			} else {
				saldoContableDolaresCuentaFideicomiso = saldoContableDolaresCuentaFideicomiso
						+ ((BigDecimal) fila[6]).doubleValue();
				saldoDisponibleDolaresCuentaFideicomiso = saldoDisponibleDolaresCuentaFideicomiso
						+ ((BigDecimal) fila[7]).doubleValue();
			}

		}

		saldoConsolidadoContableSolesCuentaFideicomiso = saldoContableSolesCuentaFideicomiso
				+ (saldoContableDolaresCuentaFideicomiso * tipoCambioSBS);
		saldoConsolidadoDisponibleSolesCuentaFideicomiso = saldoDisponibleSolesCuentaFideicomiso
				+ (saldoDisponibleDolaresCuentaFideicomiso * tipoCambioSBS);

		saldoConsolidadoContableDolaresCuentaFideicomiso = saldoContableDolaresCuentaFideicomiso
				+ (saldoContableSolesCuentaFideicomiso / tipoCambioSBS);
		saldoConsolidadoDisponibleDolaresCuentaFideicomiso = saldoDisponibleDolaresCuentaFideicomiso
				+ (saldoDisponibleSolesCuentaFideicomiso / tipoCambioSBS);

		saldoTotalMonedaModel.setMontoTipoCambio(tipoCambioSBS);
		saldoTotalMonedaModel.setSaldoTotalContableSoles(saldoContableSolesCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalContableDolares(saldoContableDolaresCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalDisponibleSoles(saldoDisponibleSolesCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalDisponibleDolares(saldoDisponibleDolaresCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalConsolidadoContableSoles(saldoConsolidadoContableSolesCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalConsolidadoDisponibleSoles(saldoConsolidadoDisponibleSolesCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalConsolidadoContableDolares(saldoConsolidadoContableDolaresCuentaFideicomiso);
		saldoTotalMonedaModel
				.setSaldoTotalConsolidadoDisponibleDolares(saldoConsolidadoDisponibleDolaresCuentaFideicomiso);

		return saldoTotalMonedaModel;
	}

	public SaldoTotalMonedaModel getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisarioMonedaNombreFideicomiso(
			Integer identificadorFideicomisario, String nombreFideicomiso, String codigoMoneda, Date fechaProceso) {

		Double tipoCambioSBS;
		Double saldoContableSolesCuentaFideicomiso = new Double(0);
		Double saldoDisponibleSolesCuentaFideicomiso = new Double(0);
		Double saldoContableDolaresCuentaFideicomiso = new Double(0);
		Double saldoDisponibleDolaresCuentaFideicomiso = new Double(0);

		Double saldoConsolidadoContableSolesCuentaFideicomiso = new Double(0);
		Double saldoConsolidadoDisponibleSolesCuentaFideicomiso = new Double(0);
		Double saldoConsolidadoContableDolaresCuentaFideicomiso = new Double(0);
		Double saldoConsolidadoDisponibleDolaresCuentaFideicomiso = new Double(0);

		TipoCambioSBSModel tipoCambioSBSModel = new TipoCambioSBSModel();
		SaldoTotalMonedaModel saldoTotalMonedaModel = new SaldoTotalMonedaModel();

		List<Object> listFideicomisoCuentaEntidadFinanciera;

		tipoCambioSBSModel = tipoCambioSBSInterface.getTipoCambioSBSByFechaProceso(fechaProceso);
		tipoCambioSBS = tipoCambioSBSModel.getMontoTipoCambio();

		listFideicomisoCuentaEntidadFinanciera = fideicomisoRepository
				.getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisarioMonedaNombreFideicomiso(
						identificadorFideicomisario, codigoMoneda, nombreFideicomiso);

		for (int i = 0; i < listFideicomisoCuentaEntidadFinanciera.size(); i++) {

			Object[] fila = (Object[]) listFideicomisoCuentaEntidadFinanciera.get(i);

			if (StringUtil.equiv(Constante.CODIGO_MONEDA_SOLES, codigoMoneda)) {
				saldoContableSolesCuentaFideicomiso = saldoContableSolesCuentaFideicomiso
						+ ((BigDecimal) fila[6]).doubleValue();
				saldoDisponibleSolesCuentaFideicomiso = saldoDisponibleSolesCuentaFideicomiso
						+ ((BigDecimal) fila[7]).doubleValue();
			} else {
				saldoContableDolaresCuentaFideicomiso = saldoContableDolaresCuentaFideicomiso
						+ ((BigDecimal) fila[6]).doubleValue();
				saldoDisponibleDolaresCuentaFideicomiso = saldoDisponibleDolaresCuentaFideicomiso
						+ ((BigDecimal) fila[7]).doubleValue();
			}

		}

		saldoConsolidadoContableSolesCuentaFideicomiso = saldoContableSolesCuentaFideicomiso
				+ (saldoContableDolaresCuentaFideicomiso * tipoCambioSBS);
		saldoConsolidadoDisponibleSolesCuentaFideicomiso = saldoDisponibleSolesCuentaFideicomiso
				+ (saldoDisponibleDolaresCuentaFideicomiso * tipoCambioSBS);

		saldoConsolidadoContableDolaresCuentaFideicomiso = saldoContableDolaresCuentaFideicomiso
				+ (saldoContableSolesCuentaFideicomiso / tipoCambioSBS);
		saldoConsolidadoDisponibleDolaresCuentaFideicomiso = saldoDisponibleDolaresCuentaFideicomiso
				+ (saldoDisponibleSolesCuentaFideicomiso / tipoCambioSBS);

		saldoTotalMonedaModel.setMontoTipoCambio(tipoCambioSBS);
		saldoTotalMonedaModel.setSaldoTotalContableSoles(saldoContableSolesCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalContableDolares(saldoContableDolaresCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalDisponibleSoles(saldoDisponibleSolesCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalDisponibleDolares(saldoDisponibleDolaresCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalConsolidadoContableSoles(saldoConsolidadoContableSolesCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalConsolidadoDisponibleSoles(saldoConsolidadoDisponibleSolesCuentaFideicomiso);
		saldoTotalMonedaModel.setSaldoTotalConsolidadoContableDolares(saldoConsolidadoContableDolaresCuentaFideicomiso);
		saldoTotalMonedaModel
				.setSaldoTotalConsolidadoDisponibleDolares(saldoConsolidadoDisponibleDolaresCuentaFideicomiso);

		return saldoTotalMonedaModel;
	}

	public FideicomisoModel getFideicomisoModel(Integer identificadorCuentaEntidadFinanciera) {

		FideicomisoModel fideicomisoModel = new FideicomisoModel();

		CuentaEntidadFinanciera cuentaEntidadFinanciera = new CuentaEntidadFinanciera();

		cuentaEntidadFinanciera = cuentaEntidadFinancieraRepository
				.findByIdentificadorCuentaEntidadFinanciera(identificadorCuentaEntidadFinanciera);

		fideicomisoModel
				.setIdentificadorFideicomiso(cuentaEntidadFinanciera.getFideicomiso().getIdentificadorFideicomiso());
		fideicomisoModel.setNombreFideicomiso(cuentaEntidadFinanciera.getFideicomiso().getNombreFideicomiso());
		fideicomisoModel.setCodigoEstado(cuentaEntidadFinanciera.getFideicomiso().getCodigoEstado());
		fideicomisoModel.setDescripcionEstado(cuentaEntidadFinanciera.getFideicomiso().getDescripcionEstado());

		return fideicomisoModel;
	}

}
