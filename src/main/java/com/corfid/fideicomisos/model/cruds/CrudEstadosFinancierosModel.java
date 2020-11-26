package com.corfid.fideicomisos.model.cruds;

import java.util.ArrayList;
import java.util.List;

import com.corfid.fideicomisos.model.eeff.EstadosFinancierosModel;
import com.corfid.fideicomisos.model.utilities.CrudModel;

public class CrudEstadosFinancierosModel extends CrudModel {

    private String busqueda;
    private String mesFechaCorte;
    private String anioFechaCorte;
    private List<EstadosFinancierosModel> rows = new ArrayList<EstadosFinancierosModel>();

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public String getMesFechaCorte() {
        return mesFechaCorte;
    }

    public void setMesFechaCorte(String mesFechaCorte) {
        this.mesFechaCorte = mesFechaCorte;
    }

    public String getAnioFechaCorte() {
        return anioFechaCorte;
    }

    public void setAnioFechaCorte(String anioFechaCorte) {
        this.anioFechaCorte = anioFechaCorte;
    }

    public List<EstadosFinancierosModel> getRows() {
        return rows;
    }

    public void setRows(List<EstadosFinancierosModel> rows) {
        this.rows = rows;
    }

}
