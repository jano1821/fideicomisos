package com.corfid.fideicomisos.service.impl.flujoprocesodocumento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.flujoprocesodocumento.ActividadDetalleDocumentoFideicomisoConverter;
import com.corfid.fideicomisos.entity.flujoprocesodocumento.ActividadDetalleDocumentoFideicomiso;
import com.corfid.fideicomisos.model.flujoprocesodocumento.ActividadDetalleDocumentoFideicomisoModel;
import com.corfid.fideicomisos.repository.flujoprocesodocumento.ActividadDetalleDocumentoFideicomisoRepository;
import com.corfid.fideicomisos.service.flujoprocesodocumento.ActividadDetalleDocumentoFideicomisoInterface;
import com.corfid.fideicomisos.utilities.AbstractService;

@Service("actividadDetalleDocumentoFideicomisoServiceImpl")
public class ActividadDetalleDocumentoFideicomisoServiceImpl extends AbstractService
		implements ActividadDetalleDocumentoFideicomisoInterface {

	@Autowired
	@Qualifier("actividadDetalleDocumentoFideicomisoRepository")
	private ActividadDetalleDocumentoFideicomisoRepository actividadDetalleDocumentoFideicomisoRepository;

	@Autowired
	@Qualifier("actividadDetalleDocumentoFideicomisoConverter")
	private ActividadDetalleDocumentoFideicomisoConverter actividadDetalleDocumentoFideicomisoConverter;

	public List<ActividadDetalleDocumentoFideicomisoModel> getListActividadDetalleDocumentoFideicomisoModel(
			Integer identificadorDocumentoFideicomiso) {

		List<ActividadDetalleDocumentoFideicomiso> listActividadDetalleDocumentoFideicomiso = new ArrayList<ActividadDetalleDocumentoFideicomiso>();
		List<ActividadDetalleDocumentoFideicomisoModel> listActividadDetalleDocumentoFideicomisoModel = new ArrayList<ActividadDetalleDocumentoFideicomisoModel>();

		listActividadDetalleDocumentoFideicomiso = actividadDetalleDocumentoFideicomisoRepository
				.getListActividadDetalleDocumentoFideicomisoByIdDocumentoFideicomiso(identificadorDocumentoFideicomiso);

		for (ActividadDetalleDocumentoFideicomiso actividadDetalleDocumentoFideicomiso : listActividadDetalleDocumentoFideicomiso) {

			listActividadDetalleDocumentoFideicomisoModel.add(actividadDetalleDocumentoFideicomisoConverter
					.convertToActividadDetalleDocumentoFideicomisoModel(actividadDetalleDocumentoFideicomiso));
		}

		return listActividadDetalleDocumentoFideicomisoModel;
	}

	public String getCadenaActividadDetalleDocumentoFideicomiso(
			List<ActividadDetalleDocumentoFideicomisoModel> listActividadDetalleDocumentoFideicomisoModel) {

		String cadenaHtml = "";

		cadenaHtml = "<div class=\"ui mini steps\">";

		for (ActividadDetalleDocumentoFideicomisoModel actividadDetalleDocumentoFideicomisoModel : listActividadDetalleDocumentoFideicomisoModel) {

			cadenaHtml += "<div class=\"step\">";
			cadenaHtml += "<div class=\"content\">";
			cadenaHtml += "<div class=\"title\">" + actividadDetalleDocumentoFideicomisoModel.getDescripcionActividad()
					+ "</div>";
			cadenaHtml += "<div class=\"description\"> ET = x"
					+ actividadDetalleDocumentoFideicomisoModel.getNivelServicio() + " horas/días</div>";
			cadenaHtml += "</div>";
			cadenaHtml += "</div>";

		}

		cadenaHtml += "</div>";

		return cadenaHtml;
	}

}
