package com.corfid.fideicomisos.service.impl.banco;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.banco.PosicionBancoConverter;
import com.corfid.fideicomisos.model.banco.FideicomisarioModel;
import com.corfid.fideicomisos.model.banco.PosicionBancoModel;
import com.corfid.fideicomisos.repository.banco.FideicomisoRepository;
import com.corfid.fideicomisos.service.banco.FideicomisarioInterface;
import com.corfid.fideicomisos.service.banco.FideicomisoInterface;
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

	public PosicionBancoModel getListaFideicomiso(String cadenaBusqueda, String numeroDocumento, Integer pagina,
			Integer cantRegistros) {

		PosicionBancoModel posicionBancoModel = new PosicionBancoModel();

		if (StringUtil.isEmpty(cadenaBusqueda)) {
			posicionBancoModel = getListFideicomisoCuentaEntidadFinancieraByFideicomisario(numeroDocumento, pagina,
					cantRegistros);
		} else {
			String cadenaFideicomiso = Constante.COMODIN_LIKE + cadenaBusqueda + Constante.COMODIN_LIKE;
			posicionBancoModel = getListFideicomisoCuentaEntidadFinancieraByFideicomisarioNombreFideicomiso(
					cadenaFideicomiso, numeroDocumento, pagina, cantRegistros);
		}

		return posicionBancoModel;
	}

	public PosicionBancoModel getListaFideicomisoMoneda(String cadenaBusqueda, String numeroDocumento,
			String monedaCuenta, Integer pagina, Integer cantRegistros) {

		PosicionBancoModel posicionBancoModel = new PosicionBancoModel();

		if (StringUtil.isEmpty(cadenaBusqueda)) {
			posicionBancoModel = getListFideicomisoCuentaEntidadFinancieraByFideicomisarioMoneda(numeroDocumento,
					monedaCuenta, pagina, cantRegistros);
		} else {
			String cadenaFideicomiso = Constante.COMODIN_LIKE + cadenaBusqueda + Constante.COMODIN_LIKE;
			posicionBancoModel = getListFideicomisoCuentaEntidadFinancieraByFideicomisarioMonedaNombreFideicomiso(
					cadenaFideicomiso, numeroDocumento, monedaCuenta, pagina, cantRegistros);
		}

		return posicionBancoModel;

	}

	public PosicionBancoModel getListFideicomisoCuentaEntidadFinancieraByFideicomisario(String numeroDocumento,
			Integer pagina, Integer cantRegistros) {

		List<Object> listFideicomisoCuentaEntidadFinanciera;
		List<PosicionBancoModel> listPosicionBancoModel;

		Page<Object> pageFideicomisoCuentaEntidadFinanciera;

		PosicionBancoModel posicionBancoModel = new PosicionBancoModel();
		FideicomisarioModel fideicomisarioModel = new FideicomisarioModel();

		fideicomisarioModel = fideicomisarioInterface.getFideicomisarioByNumeroDocumento(numeroDocumento);

		pageFideicomisoCuentaEntidadFinanciera = fideicomisoRepository
				.getListFideicomisoCuentaEntidadFinancieraByIdFideicomisario(
						fideicomisarioModel.getIdentificadorFideicomisario(),
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
			String nombreFideicomiso, String numeroDocumento, Integer pagina, Integer cantRegistros) {

		List<Object> listFideicomisoCuentaEntidadFinanciera;
		List<PosicionBancoModel> listPosicionBancoModel;

		Page<Object> pageFideicomisoCuentaEntidadFinanciera;

		PosicionBancoModel posicionBancoModel = new PosicionBancoModel();
		FideicomisarioModel fideicomisarioModel = new FideicomisarioModel();

		fideicomisarioModel = fideicomisarioInterface.getFideicomisarioByNumeroDocumento(numeroDocumento);

		pageFideicomisoCuentaEntidadFinanciera = fideicomisoRepository
				.getListFideicomisoCuentaEntidadFinancieraByIdFideicomisarioNombreFideicomiso(
						fideicomisarioModel.getIdentificadorFideicomisario(), nombreFideicomiso,
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

	public PosicionBancoModel getListFideicomisoCuentaEntidadFinancieraByFideicomisarioMoneda(String numeroDocumento,
			String monedaCuenta, Integer pagina, Integer cantRegistros) {

		List<Object> listFideicomisoCuentaEntidadFinanciera;
		List<PosicionBancoModel> listPosicionBancoModel;

		Page<Object> pageFideicomisoCuentaEntidadFinanciera;

		PosicionBancoModel posicionBancoModel = new PosicionBancoModel();
		FideicomisarioModel fideicomisarioModel = new FideicomisarioModel();

		fideicomisarioModel = fideicomisarioInterface.getFideicomisarioByNumeroDocumento(numeroDocumento);

		pageFideicomisoCuentaEntidadFinanciera = fideicomisoRepository
				.getListFideicomisoCuentaEntidadFinancieraByIdFideicomisarioMoneda(
						fideicomisarioModel.getIdentificadorFideicomisario(), monedaCuenta,
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

	public PosicionBancoModel getListFideicomisoCuentaEntidadFinancieraByFideicomisarioMonedaNombreFideicomiso(
			String nombreFideicomiso, String numeroDocumento, String monedaCuenta, Integer pagina,
			Integer cantRegistros) {

		List<Object> listFideicomisoCuentaEntidadFinanciera;
		List<PosicionBancoModel> listPosicionBancoModel;

		Page<Object> pageFideicomisoCuentaEntidadFinanciera;

		PosicionBancoModel posicionBancoModel = new PosicionBancoModel();
		FideicomisarioModel fideicomisarioModel = new FideicomisarioModel();

		fideicomisarioModel = fideicomisarioInterface.getFideicomisarioByNumeroDocumento(numeroDocumento);

		pageFideicomisoCuentaEntidadFinanciera = fideicomisoRepository
				.getListFideicomisoCuentaEntidadFinancieraByIdFideicomisarioMonedaNombreFideicomiso(
						fideicomisarioModel.getIdentificadorFideicomisario(), monedaCuenta, nombreFideicomiso,
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

}
