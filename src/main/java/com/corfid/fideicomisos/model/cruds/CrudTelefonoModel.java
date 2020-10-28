package com.corfid.fideicomisos.model.cruds;

import java.util.ArrayList;
import java.util.List;

import com.corfid.fideicomisos.model.administrativo.TelefonoModel;
import com.corfid.fideicomisos.model.utilities.CrudModel;

public class CrudTelefonoModel extends CrudModel {

    private List<TelefonoModel> rows = new ArrayList<TelefonoModel>();
    private Integer idPersona;
    private String nombreCompletoPersona;

    public List<TelefonoModel> getRows() {
        return rows;
    }

    public void setRows(List<TelefonoModel> rows) {
        this.rows = rows;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombreCompletoPersona() {
        return nombreCompletoPersona;
    }

    public void setNombreCompletoPersona(String nombreCompletoPersona) {
        this.nombreCompletoPersona = nombreCompletoPersona;
    }

}
