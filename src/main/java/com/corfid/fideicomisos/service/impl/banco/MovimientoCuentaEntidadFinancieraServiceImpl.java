package com.corfid.fideicomisos.service.impl.banco;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.banco.MovimientoCuentaEntidadFinancieraConverter;
import com.corfid.fideicomisos.entity.banco.MovimientoCuentaEntidadFinanciera;
import com.corfid.fideicomisos.model.banco.MovimientoCuentaEntidadFinancieraModel;
import com.corfid.fideicomisos.repository.banco.MovimientoCuentaEntidadFinancieraRepository;
import com.corfid.fideicomisos.service.banco.MovimientoCuentaEntidadFinancieraInterface;
import com.corfid.fideicomisos.utilities.AbstractService;

@Service("movimientoCuentaEntidadFinancieraServiceImpl")
public class MovimientoCuentaEntidadFinancieraServiceImpl extends AbstractService
		implements MovimientoCuentaEntidadFinancieraInterface {

	@Autowired
	@Qualifier("movimientoCuentaEntidadFinancieraRepository")
	private MovimientoCuentaEntidadFinancieraRepository movimientoCuentaEntidadFinancieraRepository;

	@Autowired
	@Qualifier("movimientoCuentaEntidadFinancieraConverter")
	private MovimientoCuentaEntidadFinancieraConverter movimientoCuentaEntidadFinancieraConverter;

	public MovimientoCuentaEntidadFinancieraModel getMovimientoCuentaEntidadFinancieraByIdCuenta(
			Integer identificadorCuentaEntidadFinanciera, Integer pagina, Integer cantRegistros) {

		Page<MovimientoCuentaEntidadFinanciera> pageMovimientoCuentaEntidadFinanciera;

		List<MovimientoCuentaEntidadFinanciera> listMovimientoCuentaEntidadFinanciera;
		List<MovimientoCuentaEntidadFinancieraModel> listMovimientoCuentaEntidadFinancieraModel;

		MovimientoCuentaEntidadFinancieraModel movimientoCuentaEntidadFinancieraModel = new MovimientoCuentaEntidadFinancieraModel();

		pageMovimientoCuentaEntidadFinanciera = movimientoCuentaEntidadFinancieraRepository
				.getListMovimientoCuentaEntidadFinancieraByIdCuenta(identificadorCuentaEntidadFinanciera,
						obtenerIndexPorPagina(pagina, cantRegistros, null, false, false));

		movimientoCuentaEntidadFinancieraModel.setPaginaFinal(pageMovimientoCuentaEntidadFinanciera.getTotalPages());
		movimientoCuentaEntidadFinancieraModel
				.setCantidadRegistros(_toInteger(pageMovimientoCuentaEntidadFinanciera.getTotalElements()));

		listMovimientoCuentaEntidadFinanciera = pageMovimientoCuentaEntidadFinanciera.getContent();

		listMovimientoCuentaEntidadFinancieraModel = new ArrayList<MovimientoCuentaEntidadFinancieraModel>();

		for (MovimientoCuentaEntidadFinanciera movimientoCuentaEntidadFinanciera : listMovimientoCuentaEntidadFinanciera) {
			listMovimientoCuentaEntidadFinancieraModel.add(movimientoCuentaEntidadFinancieraConverter.convertMovimientoCuentaEntidadFinancieraToMovimientoCuentaEntidadFinancieraModel(movimientoCuentaEntidadFinanciera));
		}
		
		movimientoCuentaEntidadFinancieraModel.setRows(listMovimientoCuentaEntidadFinancieraModel);

		return movimientoCuentaEntidadFinancieraModel;
	}

}
