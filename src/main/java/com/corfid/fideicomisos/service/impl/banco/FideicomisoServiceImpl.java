package com.corfid.fideicomisos.service.impl.banco;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.banco.PosicionBancoConverter;
import com.corfid.fideicomisos.model.banco.PaginacionGeneralModel;
import com.corfid.fideicomisos.model.banco.PosicionBancoModel;
import com.corfid.fideicomisos.repository.banco.FideicomisoRepository;
import com.corfid.fideicomisos.service.banco.FideicomisoInterface;
import com.corfid.fideicomisos.utilities.AbstractService;

@Service("fideicomisoServiceImpl")
public class FideicomisoServiceImpl extends AbstractService implements FideicomisoInterface {

	@Autowired
	@Qualifier("fideicomisoRepository")
	private FideicomisoRepository fideicomisoRepository;

	@Autowired
	@Qualifier("posicionBancoConverter")
	private PosicionBancoConverter posicionBancoConverter;

	public PosicionBancoModel getListFideicomisoCuentaEntidadFinancieraFromFideicomisario(
			String identificadorFideicomisario, Integer pagina, Integer cantRegistros) {

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

}
