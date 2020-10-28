package com.corfid.fideicomisos.service.administrativo;

import java.util.Map;

public interface OlvidoPasswordInterface {

    public String recuperarContrasenia(String numeroDocumento,
                                       String opcion,
                                       String telefono,
                                       String correo) throws Exception;

    public Map<String, String> buscarUsuarioRecuperacion(String numeroDocumento) throws Exception;

    public String validarPin(String codigo,
                             String pin,
                             String password,
                             String numeroDocumento,
                             String opcion) throws Exception;

    public String cambiarContrasenia(String userName, String password) throws Exception;

    public String cambiarContraseniaAndIndicador(String userName, String password) throws Exception;

    public String validarContrasenia(String password, String password2) throws Exception;
}
