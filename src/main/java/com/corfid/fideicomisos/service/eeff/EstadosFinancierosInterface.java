package com.corfid.fideicomisos.service.eeff;

import com.corfid.fideicomisos.model.cruds.CrudEstadosFinancierosModel;
import com.corfid.fideicomisos.model.eeff.EstadosFinancierosModel;

public interface EstadosFinancierosInterface {

    public CrudEstadosFinancierosModel listEEFFByFideicomisoPaginado(String fideicomiso,
                                                                     String mesFechaCorte,
                                                                     String anioFechaCorte,
                                                                     String rucFideicomisario,
                                                                     Integer pagina,
                                                                     Integer cant) throws Exception;

    public void guardarpdf(Integer id, byte[] filebyte) throws Exception;

    public EstadosFinancierosModel obtenerEEFF(Integer id) throws Exception;
}
