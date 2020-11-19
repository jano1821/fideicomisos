package com.corfid.fideicomisos.service.impl.flujoprocesodocumento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.flujoprocesodocumento.DetalleDocumentoFideicomisoConverter;
import com.corfid.fideicomisos.entity.flujoprocesodocumento.DetalleDocumentoFideicomiso;
import com.corfid.fideicomisos.model.flujoprocesodocumento.DetalleDocumentoFideicomisoModel;
import com.corfid.fideicomisos.repository.flujoprocesodocumento.DetalleDocumentoFideicomisoRepository;
import com.corfid.fideicomisos.service.flujoprocesodocumento.DetalleDocumentoFideicomisoInterface;
import com.corfid.fideicomisos.utilities.AbstractService;

@Service("detalleDocumentoFideicomisoServiceImpl")
public class DetalleDocumentoFideicomisoServiceImpl extends AbstractService implements DetalleDocumentoFideicomisoInterface{

	@Autowired
	@Qualifier("detalleDocumentoFideicomisoRepository")
	private DetalleDocumentoFideicomisoRepository detalleDocumentoFideicomisoRepository;

	@Autowired
	@Qualifier("detalleDocumentoFideicomisoConverter")
	private DetalleDocumentoFideicomisoConverter detalleDocumentoFideicomisoConverter;

	public DetalleDocumentoFideicomisoModel getListDetalleDocumentoFideicomiso(
			Integer identificadorDocumentoFideicomiso) {

		DetalleDocumentoFideicomisoModel detalleDocumentoFideicomisoModel = new DetalleDocumentoFideicomisoModel();
		List<DetalleDocumentoFideicomiso> listDetalleDocumentoFideicomiso = new ArrayList<DetalleDocumentoFideicomiso>();
		List<DetalleDocumentoFideicomisoModel> listDetalleDocumentoFideicomisoModel = new ArrayList<DetalleDocumentoFideicomisoModel>();

		listDetalleDocumentoFideicomiso = detalleDocumentoFideicomisoRepository
				.getListDetalleDocumentoFideicomisoByIdDocumentoFideicomiso(identificadorDocumentoFideicomiso);

		for (DetalleDocumentoFideicomiso detalleDocumentoFideicomiso : listDetalleDocumentoFideicomiso) {
			listDetalleDocumentoFideicomisoModel.add(detalleDocumentoFideicomisoConverter
					.convertToDetalleDocumentoFideicomisoModel(detalleDocumentoFideicomiso));
		}

		detalleDocumentoFideicomisoModel.setRows(listDetalleDocumentoFideicomisoModel);

		return detalleDocumentoFideicomisoModel;
	}

}
