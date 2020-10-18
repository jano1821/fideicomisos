package com.corfid.fideicomisos.service.utilities;

public interface RestClientInterface {

    public String getObtenerPinValidacion(String numero) throws Exception;

    public String validarPin(String codigo, String pin) throws Exception;
}
