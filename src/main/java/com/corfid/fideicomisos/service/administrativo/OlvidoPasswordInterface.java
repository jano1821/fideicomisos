package com.corfid.fideicomisos.service.administrativo;

import java.util.Map;

public interface OlvidoPasswordInterface {

    public String recuperarContrasenia(String numeroDocumento,
                                       String opcion,
                                       String telefono,
                                       String correo) throws Exception;

    public Map<String, String> buscarUsuarioRecuperacion(String numeroDocumento) throws Exception;

    public String validarPin(String codigo, String pin, String password, String numeroDocumento) throws Exception;
}
