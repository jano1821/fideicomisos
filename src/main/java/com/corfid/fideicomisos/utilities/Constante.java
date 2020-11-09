package com.corfid.fideicomisos.utilities;

public class Constante {
    //URL's
    public static final String MENU = "principal";
    public static final String OLVIDO = "mantenimiento/administrativo/cambio_password_ingreso_numero_doc";
    public static final String PRIMERA_VEZ = "mantenimiento/administrativo/cambio_password_primera_vez";
    public static final String INTERNO = "mantenimiento/administrativo/cambio_password_interno";
    public static final String SELECCION_MODO_RECUPERACION = "mantenimiento/administrativo/cambio_password_seleccion_modo";
    public static final String SELECCION_CAMBIO_PASSWORD = "mantenimiento/administrativo/cambio_password";
    public static final String SELECCION_CAMBIO_PASSWORD_CONFIRMACION = "mantenimiento/administrativo/cambio_password_confirmacion";
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
    public static final String LISTA_DIRECCIONES = "mantenimiento/administrativo/lista_direccion";
    public static final String LISTA_EMAIL = "mantenimiento/administrativo/lista_email";
    public static final String LISTA_TELEFONO = "mantenimiento/administrativo/lista_telefono";
    public static final String FORM_PERSONA = "mantenimiento/administrativo/form_persona";
    public static final String FORM_DIRECCION = "mantenimiento/administrativo/form_direccion";
    public static final String FORM_EMAIL = "mantenimiento/administrativo/form_email";
    public static final String FORM_TELEFONO = "mantenimiento/administrativo/form_telefono";
    public static final String URL_FIDEICOMISO = "/fideicomiso/getListFideicomisos";
    public static final String URL_FIDEICOMISO_SOLES = "/fideicomiso/getListFideicomisosSoles";
    public static final String URL_FIDEICOMISO_DOLARES = "/fideicomiso/getListFideicomisosDolares";
    public static final String URL_LISTA_FIDEICOMISO = "bancos/posicionBanco/form_fideicomiso";
    public static final String URL_LISTA_FIDEICOMISO_SOLES = "bancos/posicionBanco/form_fideicomiso_soles";
    public static final String URL_LISTA_FIDEICOMISO_DOLARES = "bancos/posicionBanco/form_fideicomiso_dolares";
    public static final String URL_LISTA_MOVIMIENTOS_CUENTA = "bancos/posicionBanco/form_child_movimientos";
    public static final String LISTA_EEFF = "estadosFinancieros/lista_eeff";
    
    //TABLAS
    public static final String TABLA_USUARIO = "admusuar";
    public static final String TABLA_USUARIO_ADM = "admusuaa";
    public static final String TABLA_ROLES = "admroles";
    public static final String TABLA_MENU = "admcmenu";
    public static final String TABLA_PERSONA = "admperso";
    public static final String TABLA_TELEFONO = "admtelef";
    public static final String TABLA_SERVICIO = "servicio";
    
    //CONSTRAINTS
    public static final String ESTADO_REGISTRO = "c_estreg";
    public static final String INDICADOR_ACTIVIDAD = "b_estusu";
    public static final String TIPO_USUARIO = "c_tiusad";
    public static final String TIPO_MENU = "c_tipmen";
    public static final String TIPO_PERSONA = "c_tipper";
    public static final String TIPO_DOC = "c_tipdoc";
    public static final String OPERADOR = "c_idoper";
    public static final String PARAMETROS = "paramet";
    
    //VALORES
    public static final String CONST_VACIA = "";
    public static final Integer PAGINADO_5_ROWS= 5;
    public static final String COMODIN_LIKE = "%";
    public static final String IZQUIERDA = "1";
    public static final String DERECHA = "1";
    public static final String CONST_CERO = "0";
    public static final String PAGINA_INICIAL = "1";
    public static final String RESULT_CORRECTO = "0";
    public static final String RESULT_ERROR = "1";
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
    public static final String ESTADO_ACTIVIDAD_ACTIVO = "1";
    public static final String ESTADO_ACTIVIDAD_BLOQUEADO = "0";
    public static final String FORMULARIO_FIDEICOMISO = "FIDEICOMISO";
    public static final String FORMULARIO_FIDEICOMISO_SOLES = "FIDEICOMISO_SOLES";
    public static final String FORMULARIO_FIDEICOMISO_DOLARES = "FIDEICOMISO_DOLARES";
    
    //NOMBRE DE MODULOS
    public static final String MODULO_MENU = "Menús";
    public static final String MODULO_ROL = "Roles";
    public static final String MODULO_USUARIO = "Usuarios";
    public static final String MODULO_PERSONA = "Personas";
    public static final String MODULO_DIRECCION = "Direcciones";
    public static final String MODULO_TELEFONO = "Telefonos";
    public static final String MODULO_EMAIL = "Email";
    public static final String CODIGO_EMAIL_CAMBIO_PIN = "CON_CAMBIO";
    public static final String CODIGO_EMAIL_PASSWORD = "CON_PASSWORD";
    public static final String CODIGO_EMAIL_BIENVENIDA = "CON_BIENVENIDA";
    public static final String CODIGO_EMAIL_VALIDACION_PIN = "CON_PIN";
    public static final String MODULO_CAMBIO_INTERNO = "Cambio de Password";
    public static final String TAMANIO_MINIMO_PASSWORD = "TAM_MIN_PASS";
    public static final String TAMANIO_MAXIMO_PASSWORD = "TAM_MAX_PAS";
    public static final String CANTIDAD_REGISTROS_X_PAGINA = "CANT_PAG";
    public static final String MODULO_EEFF = "Estado Financiero";
    public static final String ORIGEN_ARC = "ORIGEN_ARC";
    public static final String POR_FTP = "S";
}
