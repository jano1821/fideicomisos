package com.corfid.fideicomisos.component.banco;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.banco.Fideicomiso;
import com.corfid.fideicomisos.model.banco.FideicomisoModel;

@Component("fideicomisoConverter")
public class FideicomisoConverter {

	public FideicomisoModel convertFideicomisoToFideicomisoModel(Fideicomiso fideicomiso) {

		FideicomisoModel fideicomisoModel = new FideicomisoModel();

		fideicomisoModel.setIdentificadorFideicomiso(fideicomiso.getIdentificadorFideicomiso());
		fideicomisoModel.setNombreFideicomiso(fideicomiso.getNombreFideicomiso());
		fideicomisoModel.setCodigoEstado(fideicomiso.getCodigoEstado());
		fideicomisoModel.setDescripcionEstado(fideicomiso.getDescripcionEstado());

		return fideicomisoModel;
	}

}
