package com.corfid.fideicomisos.service.impl.administrativo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.entity.administrativo.Empresa;
import com.corfid.fideicomisos.repository.administrativo.EmpresaRepository;
import com.corfid.fideicomisos.service.administrativo.EmpresaInterface;
import com.corfid.fideicomisos.utilities.AbstractService;

@Service("empresaServiceImpl")
public class EmpresaServiceImpl  extends AbstractService implements EmpresaInterface {
	@Autowired
	@Qualifier("empresaRepository")
	private EmpresaRepository empresaRepository;
	
	public Empresa findEmpresaById(Integer id) {
		Empresa empresa = new Empresa();
		
		empresa = empresaRepository.findByIdEmpresa(id);
		
		return empresa;
	}
}
