package com.corfid.fideicomisos.service.impl.banco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.banco.FideicomisarioConverter;
import com.corfid.fideicomisos.model.banco.FideicomisarioModel;
import com.corfid.fideicomisos.repository.banco.FideicomisarioRepository;
import com.corfid.fideicomisos.service.banco.FideicomisarioInterface;
import com.corfid.fideicomisos.utilities.AbstractService;

@Service("fideicomisarioServiceImpl")
public class FideicomisarioServiceImpl extends AbstractService implements FideicomisarioInterface {

	@Autowired
	@Qualifier("fideicomisarioConverter")
	private FideicomisarioConverter fideicomisarioConverter;

	@Autowired
	@Qualifier("fideicomisarioRepository")
	private FideicomisarioRepository fideicomisarioRepository;

	public FideicomisarioModel getFideicomisarioByNumeroDocumento(String numeroDocumento) {

		return fideicomisarioConverter.convertFideicomisarioToFideicomisarioModel(
				fideicomisarioRepository.findByNumeroDocumento(numeroDocumento));
	}

}
