package com.corfid.fideicomisos.model.cruds;

import java.util.ArrayList;
import java.util.List;

import com.corfid.fideicomisos.model.administrativo.EmailModel;
import com.corfid.fideicomisos.model.utilities.CrudModel;

public class CrudEmailModel extends CrudModel {

    private List<EmailModel> rows = new ArrayList<EmailModel>();
    private Integer idPersona;
    private String nombreCompletoPersona;

    public List<EmailModel> getRows() {
        return rows;
    }

    public void setRows(List<EmailModel> rows) {
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
