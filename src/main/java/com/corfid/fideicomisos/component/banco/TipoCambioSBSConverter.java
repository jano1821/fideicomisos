package com.corfid.fideicomisos.component.banco;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.banco.TipoCambioSBS;
import com.corfid.fideicomisos.model.banco.TipoCambioSBSModel;

@Component("tipoCambioSBSConverter")
public class TipoCambioSBSConverter {
	
	public TipoCambioSBSModel convertTipoCambioSBSToTipoCambioSBSModel(TipoCambioSBS tipoCambioSBS) {
		
		TipoCambioSBSModel tipoCambioSBSModel = new TipoCambioSBSModel();
		
		tipoCambioSBSModel.setIdentificadorTipoCambioSBS(tipoCambioSBS.getIdentificadorTipoCambioSBS());
		tipoCambioSBSModel.setFechaProceso(tipoCambioSBS.getFechaProceso());
		tipoCambioSBSModel.setMontoTipoCambio(tipoCambioSBS.getMontoTipoCambio());
		
		return tipoCambioSBSModel;
	}

}
