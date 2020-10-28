package com.corfid.fideicomisos.model.cruds;

import java.util.ArrayList;
import java.util.List;

import com.corfid.fideicomisos.model.administrativo.DireccionModel;
import com.corfid.fideicomisos.model.utilities.CrudModel;

public class CrudDireccionModel extends CrudModel {

    private List<DireccionModel> rows = new ArrayList<DireccionModel>();
    private Integer idPersona;
    private String nombreCompletoPersona;

    public List<DireccionModel> getRows() {
        return rows;
    }

    public void setRows(List<DireccionModel> rows) {
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
