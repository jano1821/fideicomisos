package com.corfid.fideicomisos.service.banco;

import java.util.Date;

import com.corfid.fideicomisos.model.banco.TipoCambioSBSModel;

public interface TipoCambioSBSInterface {
	
	public TipoCambioSBSModel getTipoCambioSBSByFechaProceso(Date fechaProceso);
	
	public TipoCambioSBSModel getTipoCambioSBS();

}
