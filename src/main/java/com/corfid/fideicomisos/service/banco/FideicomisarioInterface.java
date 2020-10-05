package com.corfid.fideicomisos.service.banco;

import com.corfid.fideicomisos.model.banco.FideicomisarioModel;

public interface FideicomisarioInterface {

	public FideicomisarioModel getFideicomisarioByNumeroDocumento(String numeroDocumento);
	
}
