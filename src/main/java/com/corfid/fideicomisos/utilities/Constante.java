package com.corfid.fideicomisos.utilities;

public class Constante {
    //URL's
    public static final String MENU = "principal";
    public static final String LOGIN = "login";
    public static final String SITIO_EN_CONSTRUCCION = "mantenimiento/administrativo/construccion";
    public static final String SELECCION = "seleccion";
    public static final String URL_SELECCION = "/seleccion/seleccion";
    public static final String URL_LISTA_USUARIOS = "/usuario/lista_usuarios";
    public static final String URL_LISTA_ROLES = "/rol/lista_roles";
    public static final String URL_LISTA_MENUS = "/menu/lista_menus";
    public static final String URL_LISTA_PERSONAS = "/persona/lista_personas";
    public static final String LISTA_USUARIOS = "mantenimiento/administrativo/lista_usuarios";
    public static final String FORM_USUARIO = "mantenimiento/administrativo/form_usuario";
    public static final String LISTA_ROLES = "mantenimiento/administrativo/lista_roles";
    public static final String FORM_ROL = "mantenimiento/administrativo/form_rol";
    public static final String LISTA_MENUS = "mantenimiento/administrativo/lista_menus";
    public static final String FORM_MENU = "mantenimiento/administrativo/form_menu";
    public static final String LISTA_PERSONAS = "mantenimiento/administrativo/lista_personas";
    public static final String FORM_PERSONA = "mantenimiento/administrativo/form_persona";
    public static final String URL_LISTA_FIDEICOMISO = "bancos/posicionBanco/form_fideicomiso";
    public static final String URL_LISTA_FIDEICOMISO_SOLES = "bancos/posicionBanco/form_fideicomiso_soles";
    public static final String URL_LISTA_FIDEICOMISO_DOLARES = "bancos/posicionBanco/form_fideicomiso_dolares";
    public static final String URL_LISTA_MOVIMIENTOS_CUENTA = "bancos/posicionBanco/form_child_movimientos";
    
    //TABLAS
    public static final String TABLA_USUARIO = "admusuar";
    public static final String TABLA_ROLES = "admroles";
    public static final String TABLA_MENU = "admcmenu";
    public static final String TABLA_PERSONA = "admperso";
    
    //CONSTRAINTS
    public static final String ESTADO_REGISTRO = "c_estreg";
    public static final String INDICADOR_ACTIVIDAD = "b_estusu";
    public static final String TIPO_USUARIO = "c_tiusad";
    public static final String TIPO_MENU = "c_tipmen";
    public static final String TIPO_PERSONA = "c_tipper";
    public static final String TIPO_DOC = "c_tipdoc";
    
    //VALORES
    public static final String CONST_VACIA = "";
    public static final Integer PAGINADO_5_ROWS= 5;
    public static final String COMODIN_LIKE = "%";
    public static final String IZQUIERDA = "1";
    public static final String DERECHA = "1";
    public static final String CONST_CERO = "0";
    public static final String PAGINA_INICIAL = "1";
    public static final String RESULT_CORRECTO = "1";
    public static final String RESULT_ERROR = "0";
    public static final String FIDEICOMISARIO = "F";
    public static final String FIDEICOMITENTE = "C";
    public static final String ESTADO_REGISTRO_VIGENTE = "S";
    public static final String ESTADO_REGISTRO_NO_VIGENTE = "N";
    public static final String MENSAJE_ERROR = "E";
    public static final String MENSAJE_INFORMATIVO = "I";
    public static final String MENSAJE_SATISFACTORIO = "S";
    public static final String SI_ES_CLIENTE = "S";
    public static final String NO_ES_CLIENTE = "N";
    public static final String TIPO_PERSONA_NATURAL = "N";
    public static final String TIPO_PERSONA_JURIDICA = "J";
    public static final String MODO_EDICION = "S";
    public static final String MODO_NUEVO = "N";
    public static final String TIPO_USUARIO_SUPER_ADMIN = "S";
    public static final String SELECCION_MULTIPLE = "M";
    public static final String SELECCION_SIMPLE = "S";
    public static final String NUMEROS = "0123456789";
    public static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    public static final String ESPECIALES = "@#$%^&+=";
    public static final String SI_ES_EMPRESA = "S";
    public static final String NO_ES_EMPRESA = "N";
    public static final String NO_HAY_REGISTROS_A_LA_IZQUIERDA = "3";
    public static final String NO_HAY_REGISTROS_A_LA_DERECHA = "2";
    public static final String CODIGO_MONEDA_SOLES = "1";
    public static final String CODIGO_MONEDA_DOLARES = "2";
}
