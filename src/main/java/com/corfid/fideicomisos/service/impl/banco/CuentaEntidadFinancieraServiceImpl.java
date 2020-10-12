package com.corfid.fideicomisos.service.impl.banco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.banco.CuentaEntidadFinancieraConverter;
import com.corfid.fideicomisos.model.banco.CuentaEntidadFinancieraModel;
import com.corfid.fideicomisos.repository.banco.CuentaEntidadFinancieraRepository;
import com.corfid.fideicomisos.service.banco.CuentaEntidadFinancieraInterface;
import com.corfid.fideicomisos.utilities.AbstractService;

@Service("cuentaEntidadFinancieraServiceImpl")
public class CuentaEntidadFinancieraServiceImpl extends AbstractService implements CuentaEntidadFinancieraInterface {

	@Autowired
	@Qualifier("cuentaEntidadFinancieraRepository")
	private CuentaEntidadFinancieraRepository cuentaEntidadFinancieraRepository;

	@Autowired
	@Qualifier("cuentaEntidadFinancieraConverter")
	private CuentaEntidadFinancieraConverter cuentaEntidadFinancieraConverter;

	public CuentaEntidadFinancieraModel getCuentaEntidadFinancieraByIdCuenta(
			Integer identificadorCuentaEntidadFinanciera) {

		CuentaEntidadFinancieraModel cuentaEntidadFinancieraModel = new CuentaEntidadFinancieraModel();

		cuentaEntidadFinancieraModel = cuentaEntidadFinancieraConverter
				.convertCuentaEntidadFinancieraToCuentaEntidadFinancieraModel(cuentaEntidadFinancieraRepository
						.findByIdentificadorCuentaEntidadFinanciera(identificadorCuentaEntidadFinanciera));

		return cuentaEntidadFinancieraModel;
	}

}
