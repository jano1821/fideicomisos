package com.corfid.fideicomisos.component.banco;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.banco.Fideicomisario;
import com.corfid.fideicomisos.model.banco.FideicomisarioModel;

@Component("fideicomisarioConverter")
public class FideicomisarioConverter {

	public FideicomisarioModel convertFideicomisarioToFideicomisarioModel(Fideicomisario fideicomisario) {

		FideicomisarioModel fideicomisarioModel = new FideicomisarioModel();
		
		fideicomisarioModel.setIdentificadorFideicomisario(fideicomisario.getIdentificadorFideicomisario());
		fideicomisarioModel.setNombreFideicomisario(fideicomisario.getNombreFideicomisario());
		fideicomisarioModel.setTipoDocumento(fideicomisario.getTipoDocumento());
		fideicomisarioModel.setNumeroDocumento(fideicomisario.getNumeroDocumento());
		
		return fideicomisarioModel;
	}

}
