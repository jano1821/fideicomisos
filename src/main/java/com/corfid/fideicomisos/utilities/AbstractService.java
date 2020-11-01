package com.corfid.fideicomisos.utilities;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;
import com.corfid.fideicomisos.model.utilities.CatalogoErrorModel;
import com.corfid.fideicomisos.model.utilities.ConstantesSistemaModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;
import com.corfid.fideicomisos.service.utilities.CatalogoErrorInterface;
import com.corfid.fideicomisos.service.utilities.ConstantesSistemaInterface;

public class AbstractService {

    @Autowired
    @Qualifier("catalogoErrorServiceImpl")
    CatalogoErrorInterface catalogoErrorInterface;

    @Autowired
    @Qualifier("constantesSistemaImpl")
    ConstantesSistemaInterface constantesSistemaInterface;

    protected Long _toLong(Object objeto) {
        return StringUtil.toLong(objeto);
    }

    protected Integer _toInteger(Object objeto) {
        return StringUtil.toInteger(objeto);
    }

    protected Short _toShort(Object objeto) {
        return StringUtil.toShort(objeto);
    }

    protected Double _toDouble(Object objeto) {
        return StringUtil.toDouble(objeto);
    }

    protected Float _toFloat(Object objeto) {
        return StringUtil.toFloat(objeto);
    }

    protected String _toBlank(String cadena) {
        return StringUtil.toBlank(cadena);
    }

    protected String _toBlankObject(Object object) {
        return StringUtil.toBlankObject(object);
    }

    protected String _toStr(Object object) {
        return StringUtil.toStr(object);
    }

    protected boolean _isEmpty(Object object) {
        return StringUtil.isEmpty(object);
    }

    protected String _subStr(String cadena, int indexFin) {
        return StringUtil.subStr(cadena, indexFin);
    }

    protected String _subStr(String cadena, int indexInicio, int indexFin) {
        return StringUtil.subStr(cadena, indexInicio, indexFin);
    }

    protected String _isEmpty(String object, String defaultValue) {
        return _isEmpty(object) ? defaultValue : object;
    }

    protected boolean _equiv(Object cadena1, Object cadena2) {
        return StringUtil.equiv(cadena1, cadena2);
    }

    protected boolean _inList(String cadena, String... valores) {
        return StringUtil.inList(cadena, valores);
    }

    /**
     * Metodo setea los campos de Auditoria de Insercion a una entidad
     *
     * @param auditable
     * @param beanParametrosAuditoria
     */
    protected void setInsercionAuditoria(Auditoria auditoria, ParametrosAuditoriaModel parametrosAuditoriaModel) {
        Date fechaInsercion;
        String usuarioInsercion;
        String ipInsercion;

        fechaInsercion = parametrosAuditoriaModel.getFechaInsercion();
        usuarioInsercion = parametrosAuditoriaModel.getUsuarioInsercion();
        ipInsercion = parametrosAuditoriaModel.getIpInsercion();

        auditoria.setFechaInsercion(fechaInsercion);
        auditoria.setUsuarioInsercion(usuarioInsercion);
        auditoria.setIpInsercion(ipInsercion);
    }

    /**
     * Metodo setea los campos de Auditoria de modificacion a una entidad
     *
     * @param auditable
     * @param beanParametrosAuditoria
     */
    protected void setModificacionAuditoria(Auditoria auditoria, ParametrosAuditoriaModel parametrosAuditoriaModel) {
        Date fechaModificacion;
        String usuarioModificacion;
        String ipModificacion;

        fechaModificacion = parametrosAuditoriaModel.getFechaModificacion();
        usuarioModificacion = parametrosAuditoriaModel.getUsuarioModificacion();
        ipModificacion = parametrosAuditoriaModel.getIpModificacion();

        auditoria.setFechaModificacion(fechaModificacion);
        auditoria.setUsuarioModificacion(usuarioModificacion);
        auditoria.setIpModificacion(ipModificacion);
    }

    /**
     * Este metodo devuelbe una clase Pageable con datos cargados segun parametros
     * 
     * @param pagina
     * @param cantidad
     * @param campo
     * @param ordenar
     * @param descendente
     * @return
     */
    protected Pageable obtenerIndexPorPagina(Integer pagina,
                                             Integer cantidad,
                                             String campo,
                                             boolean ordenar,
                                             boolean descendente) {
        Integer index = 0;
        Pageable pageable;

        index = (pagina - 1);

        if (ordenar) {
            if (descendente) {
                pageable = PageRequest.of(index, cantidad, Sort.by(campo).descending());
            } else {
                pageable = PageRequest.of(index, cantidad, Sort.by(campo));
            }
        } else {
            pageable = PageRequest.of(index, cantidad);
        }
        return pageable;
    }

    protected String obtenerMensajeError(String codigoError) throws Exception {
        CatalogoErrorModel catalogoErrorModel = new CatalogoErrorModel();

        catalogoErrorModel = catalogoErrorInterface.obtenerMensajeError(codigoError);
        return catalogoErrorModel.getMensaje();
    }

    protected String getPinNumber() {
        return getPassword(Constante.NUMEROS, 4);
    }

    protected String getPassword() {
        return getPassword(8);
    }

    protected String getPassword(int length) {
        return getPassword(Constante.NUMEROS + Constante.MAYUSCULAS + Constante.MINUSCULAS + Constante.ESPECIALES,
                           length);
    }

    protected String getPassword(String key, int length) {
        String pswd = "";

        for (int i = 0; i < length; i++) {
            pswd += (key.charAt((int) (Math.random() * key.length())));
        }

        return pswd;
    }

    protected Map<String, String> passwordVerificado(String password,
                                                     boolean generar,
                                                     Integer tamanio) throws Exception {
        Integer contNumero = 0, contLetraMay = 0, contLetraMin = 0, contEspecialesMin = 0;
        Map<String, String> output = new HashMap<String, String>();
        output.put("error", ConstantesError.ERROR_0);
        String mensaje = "";
        Integer contador = 0;
        ConstantesSistemaModel constantesSistemaModelMin = constantesSistemaInterface.obtenerConstanteByNombre(Constante.TAMANIO_MINIMO_PASSWORD);
        ConstantesSistemaModel constantesSistemaModelMax = constantesSistemaInterface.obtenerConstanteByNombre(Constante.TAMANIO_MAXIMO_PASSWORD);

        password = _toBlank(password);

        if ((!generar && _isEmpty(password))) {
            output.put("error", ConstantesError.ERROR_1);
            output.put("mensaje", "Password vacío");
        } else if (generar && tamanio < 8) {
            output.put("error", ConstantesError.ERROR_1);
            output.put("mensaje", "Tamaño a generar en muy pequeño");
        } else if (!generar && password.length() < _toInteger(constantesSistemaModelMin.getValor())) {
            output.put("error", ConstantesError.ERROR_1);
            output.put("mensaje",
                       "Password muy pequeño, mínimo de " + constantesSistemaModelMin.getValor() + " caracteres");
            mensaje = "Password muy pequeño, mínimo de " + constantesSistemaModelMin.getValor() + " caracteres";
        } else if (!generar && password.length() > _toInteger(constantesSistemaModelMax.getValor())) {
            output.put("error", ConstantesError.ERROR_1);
            output.put("mensaje",
                       "Password muy grande, máximo de " + constantesSistemaModelMin.getValor() + " caracteres");
            mensaje = "Password muy grande, máximo de "
                            + "" + constantesSistemaModelMin.getValor() + " caracteres";
        } else {
            do {
                char clave;
                contNumero = 0;
                contLetraMay = 0;
                contLetraMin = 0;
                contEspecialesMin = 0;
                if (_isEmpty(_toBlank(password)) || ((generar && !StringUtil.isEmpty(_toBlank(password))) && contador > 0)) {
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
                            mensaje += "Contraseña ingresada debe contener Mayúsculas<br>";
                        }
                        if (contLetraMin <= 0) {
                            mensaje += "Contraseña ingresada debe contener Minúsculas<br>";
                        }
                        if (contNumero <= 0) {
                            mensaje += "Contraseña ingresada debe contener Números<br>";
                        }
                        if (contEspecialesMin <= 0) {
                            mensaje += "Contraseña ingresada debe contener Caracteres Especiales<br>";
                        }

                        output.put("mensaje", mensaje);
                    }
                } else {
                    if (contLetraMay > 0 && contLetraMin > 0 && contNumero > 0 && contEspecialesMin > 0) {
                        output.put("mensaje", password);
                    }
                }

                contador++;
            } while (!(contLetraMay > 0 && contLetraMin > 0 && contNumero > 0 && contEspecialesMin > 0) && generar);

        }
        return output;
    }

    protected Map<String, String> passwordVerificado(String password, boolean generar) throws Exception {
        return passwordVerificado(password, generar, 6);
    }

    protected Map<String, String> passwordVerificado(Integer tamanio) throws Exception {
        return passwordVerificado("", true, tamanio);
    }

    protected Map<String, String> passwordVerificado() throws Exception {
        return passwordVerificado("", true, 6);
    }

    protected String dateUtilToStringYYYYMMDD(java.util.Date date) {
        if (date == null)
            return null;
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    protected Date stringToDateyyyyMMdd(String fecha) {
        Date dFecha = null;
        if (fecha == null)
            return null;

        try {
            dFecha = new SimpleDateFormat("yyyy/MM/dd").parse(fecha);
        } catch (Exception e) {
            e.printStackTrace();
            dFecha = null;
        }
        return dFecha;
    }

    public static String rellenar(String cadena, String relleno, int cantidad, String direccion) {
        while (cadena.length() < cantidad)
            if (direccion == "0")// derecha
                cadena = cadena + relleno;
            else // izquierda
                cadena = relleno + cadena;
        return cadena;
    }

    public static String formatoImpresionDecimal(double valor, int numeroDecimales) {
        DecimalFormat df = null;
        DecimalFormatSymbols dformater_rules = new DecimalFormatSymbols();
        dformater_rules.setDecimalSeparator('.');
        dformater_rules.setGroupingSeparator(',');
        String strResultado = null;
        String formato = "";
        String strDec = "";
        if (numeroDecimales <= 2) {
            formato = "###,###,##0.00";
        } else {

            strDec = rellenar(strDec, "0", numeroDecimales, "0");
            formato = "###,###,##0." + strDec;
        }
        try {
            df = new DecimalFormat(formato, dformater_rules);
            strResultado = df.format(valor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strResultado;
    }
}
