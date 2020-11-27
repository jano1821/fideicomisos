package com.corfid.fideicomisos.component.flujoprocesodocumento;

import java.math.BigInteger;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.model.flujoprocesodocumento.FlujoProcesoDocumentoFideicomisoModel;

@Component("flujoProcesoDocumentoFideicomisoConverter")
public class FlujoProcesoDocumentoFideicomisoConverter {

	public FlujoProcesoDocumentoFideicomisoModel convertToFlujoProcesoDocumentoFideicomisoModel(
			Object[] objectFlujoProcesoDocumentoFideicomiso) {

		FlujoProcesoDocumentoFideicomisoModel flujoProcesoDocumentoFideicomisoModel = new FlujoProcesoDocumentoFideicomisoModel();
		
		flujoProcesoDocumentoFideicomisoModel.setIdentificadorFideicomiso((Integer) objectFlujoProcesoDocumentoFideicomiso[0]);
		flujoProcesoDocumentoFideicomisoModel.setNombreFideicomiso((String) objectFlujoProcesoDocumentoFideicomiso[1]);
		flujoProcesoDocumentoFideicomisoModel.setDescripcionEstado((String) objectFlujoProcesoDocumentoFideicomiso[2]);
		flujoProcesoDocumentoFideicomisoModel.setCantidadRegistrosConvenioRetribucion(((BigInteger) objectFlujoProcesoDocumentoFideicomiso[3]).intValue());
		flujoProcesoDocumentoFideicomisoModel.setCantidadRegistrosContratoFideicomiso(((BigInteger) objectFlujoProcesoDocumentoFideicomiso[4]).intValue());

		return flujoProcesoDocumentoFideicomisoModel;
	}

}
