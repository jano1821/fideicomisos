package com.corfid.fideicomisos.utilities;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//esta clase es solo para encriptar y mostrar una clave por consola
public class TestCrypt {

    public static void main(String[] args) {
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        //String psw;

        //System.out.println(pe.encode("123"));//esto es para generar codificada la clave "user" correrlo como run java aplication
        System.out.println(genererNombre());
        /*
         * psw = getPassword(12); System.out.println(psw);
         */

        //System.out.println(passwordVerificado(null,true,10));
    }

    public static String NUMEROS = "0123456789";

    public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

    public static String ESPECIALES = "@#$%^&+=";

    //
    public static String getPinNumber() {
        return getPassword(NUMEROS, 4);
    }

    public static String getPassword() {
        return getPassword(8);
    }

    public static String getPassword(int length) {
        return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS + ESPECIALES, length);
    }

    public static String getPassword(String key, int length) {
        String pswd = "";

        for (int i = 0; i < length; i++) {
            pswd += (key.charAt((int) (Math.random() * key.length())));
        }

        return pswd;
    }

    public static String passwordVerificado(String password, boolean generar, Integer tamanio) {
        Integer contNumero = 0, contLetraMay = 0, contLetraMin = 0, contEspecialesMin = 0;
        Map<String, String> output = new HashMap<String, String>();
        output.put("error", ConstantesError.ERROR_0);
        String mensaje = "";
        Integer contador = 0;

        password = StringUtil.toBlank(password);

        if ((!generar && StringUtil.isEmpty(password))) {
            output.put("error", ConstantesError.ERROR_1);
            output.put("mensaje", "Password vacío");
            mensaje = "Password vacío";
        } else if (generar && tamanio < 6) {
            output.put("error", ConstantesError.ERROR_1);
            output.put("mensaje", "Tamaño a generar en muy pequeño");
            mensaje = "Tamaño a generar en muy pequeño";
        } else if (!generar && password.length() < 6) {
            output.put("error", ConstantesError.ERROR_1);
            output.put("mensaje", "Password muy pequeño, mínimo de 6");
            mensaje = "Password muy pequeño, mínimo de 6 caracteres";
        } else {
            do {

                char clave;
                contNumero = 0;
                contLetraMay = 0;
                contLetraMin = 0;
                contEspecialesMin = 0;
                if (StringUtil.isEmpty(StringUtil.toBlank(password)) || ((generar && !StringUtil.isEmpty(StringUtil.toBlank(password))) && contador > 0)) {
                    password = getPassword(tamanio);
                }
                for (byte i = 0; i < password.length(); i++) {
                    clave = password.charAt(i);
                    String passValue = String.valueOf(clave);
                    if (passValue.matches("[A-Z]")) {
                        contLetraMay++;
                    } else if (passValue.matches("[a-z]")) {
                        contLetraMin++;
                    } else if (passValue.matches("[0-9]")) {
                        contNumero++;
                    } else if (passValue.matches("[@#$%^&+=]")) {
                        contEspecialesMin++;
                    }
                }
                if (!generar) {
                    if (!(contLetraMay > 0 && contLetraMin > 0 && contNumero > 0 && contEspecialesMin > 0)) {
                        output.put("error", ConstantesError.ERROR_1);

                        if (contLetraMay <= 0) {
                            mensaje += "Debe contener Mayúsculas<br>";
                        }
                        if (contLetraMin <= 0) {
                            mensaje += "Debe contener Minúsculas<br>";
                        }
                        if (contNumero <= 0) {
                            mensaje += "Debe contener Números<br>";
                        }
                        if (contEspecialesMin <= 0) {
                            mensaje += "Debe contener Caracteres Especiales<br>";
                        }

                        output.put("mensaje", mensaje);
                    }
                } else {
                    if (contLetraMay > 0 && contLetraMin > 0 && contNumero > 0 && contEspecialesMin > 0) {
                        output.put("mensaje", password);
                        mensaje = password;
                    }
                }
                contador++;
            } while (!(contLetraMay > 0 && contLetraMin > 0 && contNumero > 0 && contEspecialesMin > 0) && generar);

        }
        return mensaje;
    }

    public static int genererNombre() {
        return (int) (50000 * Math.random());
    }
}
