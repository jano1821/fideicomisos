package com.corfid.fideicomisos.component.banco;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.banco.DetalleMovCuentaEntidadFinanciera;
import com.corfid.fideicomisos.model.banco.DetalleMovCuentaEntidadFinancieraModel;

@Component("detalleMovCuentaEntidadFinancieraConverter")
public class DetalleMovCuentaEntidadFinancieraConverter {

	public DetalleMovCuentaEntidadFinancieraModel convertDetalleMovCuentaEntidadFinancieraToDetalleMovCuentaEntidadFinancieraModel(
			DetalleMovCuentaEntidadFinanciera detalleMovCuentaEntidadFinanciera) {

		DetalleMovCuentaEntidadFinancieraModel detalleMovCuentaEntidadFinancieraModel = new DetalleMovCuentaEntidadFinancieraModel();

		detalleMovCuentaEntidadFinancieraModel.setIdentificadorDetalleMovCuentaFinanciera(
				detalleMovCuentaEntidadFinanciera.getIdentificadorDetalleMovCuentaFinanciera());

		detalleMovCuentaEntidadFinancieraModel.setCorrelativoDetalleMovimiento(detalleMovCuentaEntidadFinanciera.getCorrelativoDetalleMovimiento());
		detalleMovCuentaEntidadFinancieraModel.setDescripcionDetalleMovimiento(detalleMovCuentaEntidadFinanciera.getDescripcionDetalleMovimiento());
		detalleMovCuentaEntidadFinancieraModel.setMontoMovimiento(detalleMovCuentaEntidadFinanciera.getMontoMovimiento());
		
		return detalleMovCuentaEntidadFinancieraModel;

	}

}
