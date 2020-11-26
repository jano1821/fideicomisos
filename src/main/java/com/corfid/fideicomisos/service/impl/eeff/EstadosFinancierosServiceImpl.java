package com.corfid.fideicomisos.service.impl.eeff;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.eeff.EstadosFinancierosConverter;
import com.corfid.fideicomisos.entity.eeff.EstadosFinancieros;
import com.corfid.fideicomisos.model.cruds.CrudEstadosFinancierosModel;
import com.corfid.fideicomisos.model.eeff.EstadosFinancierosModel;
import com.corfid.fideicomisos.repository.eeff.EstadosFinancierosRepository;
import com.corfid.fideicomisos.service.eeff.EstadosFinancierosInterface;
import com.corfid.fideicomisos.utilities.AbstractService;
import com.corfid.fideicomisos.utilities.ConstantesError;

@Service("estadosFinancierosServiceImpl")
public class EstadosFinancierosServiceImpl extends AbstractService implements EstadosFinancierosInterface {
    
    @Autowired
    @Qualifier("estadosFinancierosRepository")
    private EstadosFinancierosRepository estadosFinancierosRepository;

    @Autowired
    @Qualifier("estadosFinancierosConverter")
    private EstadosFinancierosConverter estadosFinancierosConverter;
    
    @Override
    public CrudEstadosFinancierosModel listEEFFByFideicomisoPaginado(String fideicomiso,
                                                                     String mesFechaCorte,
                                                                     String anioFechaCorte,
                                                                     String rucFideicomisario,
                                                                     Integer pagina,
                                                                     Integer cant) throws Exception {
        List<EstadosFinancieros> listEstadosFinancieros;
        List<EstadosFinancierosModel> listEstadosFinancierosModel = new ArrayList<EstadosFinancierosModel>();
        Page<EstadosFinancieros> pageEstadosFinancieros = null;
        CrudEstadosFinancierosModel CcudEstadosFinancierosModel = new CrudEstadosFinancierosModel();
        Date fechaDate;
        Integer fechaNumero;

        try {
            /*if (_equiv(anioFechaCorte, "0000") || _equiv(mesFechaCorte, "00") || _isEmpty(anioFechaCorte) || _isEmpty(mesFechaCorte)) {
                if ((_equiv(anioFechaCorte, "0000") || _isEmpty(anioFechaCorte)) && (!_equiv(mesFechaCorte, "00") && !_isEmpty(mesFechaCorte))) {
                    fechaNumero = _toInteger(mesFechaCorte);
                    pageEstadosFinancieros = estadosFinancierosRepository.listEEFFByFideicomisoPaginadoByMes(fideicomiso,
                                                                                                             rucFideicomisario,
                                                                                                             fechaNumero,
                                                                                                             obtenerIndexPorPagina(pagina,
                                                                                                                                   cant,
                                                                                                                                   "fechaCorte",
                                                                                                                                   true,
                                                                                                                                   false));
                }
                if ((!_equiv(anioFechaCorte, "0000") && !_isEmpty(anioFechaCorte)) && (_equiv(mesFechaCorte, "00") || _isEmpty(mesFechaCorte))) {
                    fechaNumero = _toInteger(anioFechaCorte);
                    pageEstadosFinancieros = estadosFinancierosRepository.listEEFFByFideicomisoPaginadoByAnio(fideicomiso,
                                                                                                              rucFideicomisario,
                                                                                                              fechaNumero,
                                                                                                              obtenerIndexPorPagina(pagina,
                                                                                                                                    cant,
                                                                                                                                    "fechaCorte",
                                                                                                                                    true,
                                                                                                                                    false));
                }
                if ((_equiv(anioFechaCorte, "0000") || _isEmpty(anioFechaCorte)) && (_equiv(mesFechaCorte, "00") || _isEmpty(mesFechaCorte))) {

                    pageEstadosFinancieros = estadosFinancierosRepository.listEEFFByFideicomisoPaginadoSinFecha(fideicomiso,
                                                                                                                rucFideicomisario,
                                                                                                                obtenerIndexPorPagina(pagina,
                                                                                                                                      cant,
                                                                                                                                      "fechaCorte",
                                                                                                                                      true,
                                                                                                                                      false));
                }
            } else {
                Calendar calendario = Calendar.getInstance();
                calendario.set(_toInteger(anioFechaCorte), _toInteger(mesFechaCorte) - 1, 1);
                Integer ultimoDiaMes = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);

                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                fechaDate = formato.parse(_toStr(ultimoDiaMes) + "/" + _toStr(mesFechaCorte) + "/" + _toStr(anioFechaCorte));
                
                pageEstadosFinancieros = estadosFinancierosRepository.listEEFFByFideicomisoPaginado(fideicomiso,
                                                                                                    rucFideicomisario,
                                                                                                    fechaDate,
                                                                                                    obtenerIndexPorPagina(pagina,
                                                                                                                          cant,
                                                                                                                          "fechaCorte",
                                                                                                                          true,
                                                                                                                          false));
            }*/
            
            pageEstadosFinancieros = estadosFinancierosRepository.listEEFFByFideicomisoPaginadoByPeriodo(fideicomiso,
                                                                                                         rucFideicomisario,
                                                                                                         anioFechaCorte+mesFechaCorte,
                                                                                                         obtenerIndexPorPagina(pagina,
                                                                                                                               cant,
                                                                                                                               "fechaCorte",
                                                                                                                               true,
                                                                                                                               false));

            listEstadosFinancieros = pageEstadosFinancieros.getContent();
            CcudEstadosFinancierosModel.setPaginaFinal(pageEstadosFinancieros.getTotalPages());
            CcudEstadosFinancierosModel.setCantidadRegistros(_toInteger(pageEstadosFinancieros.getTotalElements()));

            for (EstadosFinancieros estadosFinancieros : listEstadosFinancieros) {
                listEstadosFinancierosModel.add(estadosFinancierosConverter.convertEstadosFinancierosToEstadosFinancierosModel(estadosFinancieros));
            }

            CcudEstadosFinancierosModel.setRows(listEstadosFinancierosModel);
            CcudEstadosFinancierosModel.setCodigoError(ConstantesError.ERROR_0);

            return CcudEstadosFinancierosModel;
        } catch (Exception e) {
            CcudEstadosFinancierosModel.setCodigoError(ConstantesError.ERROR_18);
            CcudEstadosFinancierosModel.setMensajeError(obtenerMensajeError(ConstantesError.ERROR_18));
            return CcudEstadosFinancierosModel;
        }
    }
    
    public void guardarpdf(Integer id, byte[] filebyte) throws Exception {
        EstadosFinancieros estadosFinancieros = new EstadosFinancieros();
        estadosFinancieros = estadosFinancierosRepository.findByIdEstadosFinancieros(id);
        
        estadosFinancieros.setArchivo(filebyte);
        
        estadosFinancierosRepository.save(estadosFinancieros);
    }
    
    public EstadosFinancierosModel obtenerEEFF(Integer id) throws Exception {
        EstadosFinancieros estadosFinancieros = new EstadosFinancieros();
        EstadosFinancierosModel estadosFinancierosModel;
        
        estadosFinancieros = estadosFinancierosRepository.findByIdEstadosFinancieros(id);
        
        estadosFinancierosModel = estadosFinancierosConverter.convertEstadosFinancierosToEstadosFinancierosModel(estadosFinancieros);
        
        return estadosFinancierosModel;
    }
}
