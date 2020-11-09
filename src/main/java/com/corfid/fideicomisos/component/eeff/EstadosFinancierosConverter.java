package com.corfid.fideicomisos.component.eeff;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.eeff.EstadosFinancieros;
import com.corfid.fideicomisos.model.eeff.EstadosFinancierosModel;
import com.corfid.fideicomisos.utilities.StringUtil;

@Component("estadosFinancierosConverter")
public class EstadosFinancierosConverter {

    /*
     * @Autowired
     * @Qualifier("tipoUsuarioServiceImpl") TipoUsuarioInterface tipoUsuarioInterface;
     */

    public EstadosFinancieros convertEstadosFinancierosModelToEstadosFinancieros(EstadosFinancierosModel estadosFinancierosModel) {
        EstadosFinancieros estadosFinancieros = new EstadosFinancieros();
        /*
         * TipoUsuario tipoUsuario = new TipoUsuario(); menu.setIdMenu(menuModel.getIdMenu()); menu.setDescripcion(menuModel.getDescripcion()); menu.setUrl(menuModel.getUrl());
         * menu.setTipoMenu(menuModel.getTipoMenu()); menu.setIdMenuPadre(menuModel.getIdMenuPadre()); menu.setOrden(menuModel.getOrden()); menu.setEstadoRegistro(menuModel.getEstadoRegistro());
         * tipoUsuario = tipoUsuarioInterface.findTipoUsuarioById(EstadosFinancierosModel.getTipoUsuario()); menu.setTipoUsuario(tipoUsuario);
         */

        return estadosFinancieros;

    }

    public EstadosFinancierosModel convertEstadosFinancierosToEstadosFinancierosModel(EstadosFinancieros estadosFinancieros) {
        EstadosFinancierosModel estadosFinancierosModel = new EstadosFinancierosModel();

        estadosFinancierosModel.setIdEstadosFinancieros(estadosFinancieros.getIdEstadosFinancieros());
        estadosFinancierosModel.setArchivo(estadosFinancieros.getArchivo());
        estadosFinancierosModel.setEstado(estadosFinancieros.getEstado());
        estadosFinancierosModel.setFechaCorte(estadosFinancieros.getFechaCorte());
        estadosFinancierosModel.setNombreFideicomiso(estadosFinancieros.getFideicomiso().getNombreFideicomiso());
        estadosFinancierosModel.setEstadoRegistro(estadosFinancieros.getEstadoRegistro());
        estadosFinancierosModel.setMonedaElaboracionInforme(estadosFinancieros.getMonedaElaboracionInforme());
        estadosFinancierosModel.setMonedaExpresionInforme(estadosFinancieros.getMonedaExpresionInforme());
        estadosFinancierosModel.setNombreArchivo(estadosFinancieros.getNombreArchivo());
        estadosFinancierosModel.setPesoArchivo(estadosFinancieros.getPesoArchivo());
        estadosFinancierosModel.setRuta(estadosFinancieros.getRuta());
        estadosFinancierosModel.setTipoArchivo(estadosFinancieros.getTipoArchivo());
        estadosFinancierosModel.setTipoInforme(estadosFinancieros.getTipoInforme());

        if (StringUtil.equiv(estadosFinancieros.getEstadoRegistro(), "S")) {
            estadosFinancierosModel.setDescEstadoRegistro("Vigente");
        } else {
            estadosFinancierosModel.setDescEstadoRegistro("Desconocido");
        }

        if (StringUtil.equiv(estadosFinancieros.getEstado(), "A")) {
            estadosFinancierosModel.setDescEstado("Activo");
        } else {
            estadosFinancierosModel.setDescEstado("Inactivo");
        }
        
        if (StringUtil.equiv(estadosFinancieros.getTipoInforme(), "01")) {
            estadosFinancierosModel.setDescTipoInforme("Estado de Situación Financiera");
        } else {
            estadosFinancierosModel.setDescTipoInforme(" Estado de Resultados");
        }

        return estadosFinancierosModel;
    }

}
