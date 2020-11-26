package com.corfid.fideicomisos.service.utilities;


public interface RestClientEstadosFinancierosInterface {
    public String getObtenerToken() throws Exception;

    public byte[] ObtenerArchivos(String token, String ruta, String archivo) throws Exception;
}
