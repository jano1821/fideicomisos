package com.corfid.fideicomisos.service.impl.banco;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.banco.TipoCambioSBSConverter;
import com.corfid.fideicomisos.entity.banco.TipoCambioSBS;
import com.corfid.fideicomisos.model.banco.TipoCambioSBSModel;
import com.corfid.fideicomisos.repository.banco.TipoCambioSBSRepository;
import com.corfid.fideicomisos.service.banco.TipoCambioSBSInterface;
import com.corfid.fideicomisos.utilities.AbstractService;

@Service("tipoCambioSBSServiceImpl")
public class TipoCambioSBSServiceImpl extends AbstractService implements TipoCambioSBSInterface {

	@Autowired
	@Qualifier("tipoCambioSBSRepository")
	TipoCambioSBSRepository tipoCambioSBSRepository;

	@Autowired
	@Qualifier("tipoCambioSBSConverter")
	TipoCambioSBSConverter tipoCambioSBSConverter;

	public TipoCambioSBSModel getTipoCambioSBSByFechaProceso(Date fechaProceso) {

		TipoCambioSBSModel tipoCambioSBSModel = new TipoCambioSBSModel();

		tipoCambioSBSModel = tipoCambioSBSConverter
				.convertTipoCambioSBSToTipoCambioSBSModel(tipoCambioSBSRepository.findByFechaProceso(fechaProceso));

		return tipoCambioSBSModel;
	}

	public TipoCambioSBSModel getTipoCambioSBS() {

		TipoCambioSBSModel tipoCambioSBSModel = new TipoCambioSBSModel();

		List<TipoCambioSBS> listTipoCambioSBS = new ArrayList<TipoCambioSBS>();

		listTipoCambioSBS = tipoCambioSBSRepository.getListTipoCambioSBS();

		for (TipoCambioSBS tipoCambioSBS : listTipoCambioSBS) {
			tipoCambioSBSModel = tipoCambioSBSConverter.convertTipoCambioSBSToTipoCambioSBSModel(tipoCambioSBS);
			break;
		}

		return tipoCambioSBSModel;

	}

}
