package com.corfid.fideicomisos.model.utilities;

import java.util.List;

import com.corfid.fideicomisos.model.administrativo.PersonaModel;

public class AjaxResponseBody {

    String msg;
    List<PersonaModel> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<PersonaModel> getResult() {
        return result;
    }

    public void setResult(List<PersonaModel> result) {
        this.result = result;
    }
}
