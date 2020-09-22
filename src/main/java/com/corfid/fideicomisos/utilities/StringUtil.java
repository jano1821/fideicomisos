package com.corfid.fideicomisos.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtil {
	/**
	 * Metodo convierte una cadena a long.
	 * 
	 * 
	 * @param cadena
	 * @return
	 */
	public static Long toLong(Object objeto) {
		if (isEmpty(objeto)) {
			return null;
		}
		try {
			return Long.parseLong(objeto.toString().replaceAll("\\,", ""));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Metodo convierte una cadena a integer.
	 * 
	 * @param cadena
	 * @return
	 */
	public static Integer toInteger(Object objeto) {
		if (isEmpty(objeto)) {
			return null;
		}
		try {
			return Integer.parseInt(objeto.toString().replaceAll("\\,", ""));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Metodo convierte una cadena a short.
	 * 
	 * @param cadena
	 * @return
	 */
	public static Short toShort(Object objeto) {
		if (isEmpty(objeto)) {
			return null;
		}
		try {
			return Short.parseShort(objeto.toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Metodo convierte una cadena a double.
	 * 
	 * @param cadena
	 * @return
	 */
	public static Double toDouble(Object objeto) {
		if (isEmpty(objeto)) {
			return null;
		}
		try {
			return Double.parseDouble(objeto.toString().replaceAll("\\,", ""));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Metodo convierte una cadena a double.
	 * @return
	 */
	public static Double toDouble(Object objeto, Double valorPorDefecto) {
		if (isEmpty(objeto)) {
			return valorPorDefecto;
		}
		try {
			return Double.parseDouble(objeto.toString().replaceAll("\\,", ""));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Metodo convierte una cadena a float.
	 * 
	 * 
	 * @param cadena
	 * @return
	 */
	public static Float toFloat(Object objeto) {
		if (isEmpty(objeto)) {
			return null;
		}
		try {
			return Float.parseFloat(objeto.toString().replaceAll("\\,", ""));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Metodo convierte un objeto a cadena
	 * 
	 * @param cadena
	 * @return
	 */
	public static String toStr(Object cadena) {
		return isEmpty(cadena) ? null : toBlank(cadena.toString());
	}

	/**
	 * Metodo devuelve una cadena formateada.
	 * 
	 * @param cadena
	 * @return
	 */
	public static String toBlank(String cadena) {
		return isEmpty(cadena) ? "" : cadena;
	}

	/**
	 * Metodo devuelve una cadena formateada.
	 * @param cadena
	 * @return
	 */
	public static String toBlank(String cadena, String defaultValue) {
		return isEmpty(cadena) ? defaultValue : cadena;
	}

	/**
	 * Metodo devuelve una cadena, cadena vacia si el objeto es null (uso para
	 * grilla).
	 * @param object
	 * @return
	 */
	public static String toBlankObject(Object object) {
		return isEmpty(object) ? "" : object.toString();
	}

	/**
	 * Metodo devuelve una cadena, cadena vacia si el objeto es null
	 * @param object
	 * @return
	 */
	public static Object toBlankObject(Object object, Object defaultValue) {
		return isEmpty(object) ? defaultValue : object;
	}

	/**
	 * Verifica si un objecto es vacio: - Object: Verifica si es nulo (o vacio de
	 * ser List) - String: El valor es nulo o vacio
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object) {
		if (object == null) {
			return true;
		}
		if (object instanceof String) {
			return object.toString().trim().length() == 0;
		}
		if (object instanceof StringBuilder) {
			return object.toString().trim().length() == 0;
		}
		if (object instanceof List<?> || object instanceof ArrayList<?>) {
			return ((List<?>) object).isEmpty();
		}
		// Inicio [S14-020] 02.03.2016 gmeza
		if (object instanceof Map<?, ?> || object instanceof HashMap<?, ?>) {
			return ((Map<?, ?>) object).isEmpty();
		}
		// Fin [S14-020]
		return false;
	}

	/**
	 * Verifica si todas las posiciones de un array son vacios.
	 * @param object
	 * @return
	 */
	public static boolean isEmptyArray(Object[] array) {
		if (array == null) {
			return true;
		}
		if (array.length == 0) {
			return true;
		}
		for (Object object : array) {
			if (!isEmpty(object)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Compara si el valor de dos objetos es igual
	 * @param object1
	 * @param object2
	 * @return
	 */
	public static boolean equiv(Object object1, Object object2) {
		if (isEmpty(object1) && !isEmpty(object2) || !isEmpty(object1) && isEmpty(object2)) {
			return false;
		}
		if (isEmpty(object1) && isEmpty(object2) || object1 == object2) {
			return true;
		}

		if (object1 instanceof String && object2 instanceof String) {
			return toBlank(object1.toString()).equals(toBlank(object2.toString()));
		}
		return object1.equals(object2);
	}

	/**
	 * Metodo busca una cadena en una arreglo de cadenas.
	 * @param cadena
	 * @param valores
	 * @return
	 */
	public static boolean inList(String cadena, String... valores) {
		for (String valor : valores) {
			// Inicio [Req 15-031] jtomasto 20.05.2015
			if (equiv(cadena, valor)) {
				// fin [Req 15-031]
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo que elimina una saltos de línea en una cadena.
	 * @param cadena
	 * @return
	 */
	public static String quitarSaltosLinea(String cadena) {
		if (!isEmpty(cadena)) {
			return cadena.replaceAll("[\n\r]", "");
		}

		return cadena;
	}

	/**
	 * Metodo que devuelve un subString desde la posicion 0 hasta una posicion
	 * final,
	 * @param cadena
	 * @param indexFin
	 * @return
	 */
	public static String subStr(String cadena, int indexFin) {
		return subStr(cadena, 0, indexFin);
	}

	/**
	 * Metodo que devuelve un subString desde una posicion inicial hasta una
	 * posicion final,
	 * 
	 * @param cadena
	 * @param indexInicio
	 * @param indexFin
	 * @return
	 */
	public static String subStr(String cadena, int indexInicio, int indexFin) {
		if (isEmpty(cadena)) {
			return cadena;
		}
		int posicionFinal = cadena.length() - 1;
		if (posicionFinal < indexInicio) {
			return null;
		}
		if (posicionFinal < indexFin) {
			return cadena.substring(indexInicio);
		}

		return cadena.substring(indexInicio, indexFin);
	}

	/**
	 * Metodo devuelve una cadena formateada.
	 * 
	 * @param cadena
	 * @return
	 */
	public static String toUpper(String cadena) {
		return isEmpty(cadena) ? cadena : cadena.toUpperCase();
	}

	/**
	 * Metodo devuelve una cadena formateada.
	 * 
	 * @param cadena
	 * @return
	 */
	public static String toLower(String cadena) {
		return isEmpty(cadena) ? cadena : cadena.toLowerCase();
	}

	/**
	 * Metodo devuelve una cadena formateada.
	 * 
	 * @param cadena
	 * @return
	 */
	public static String toUpperBlank(String cadena) {
		return toBlank(cadena).toUpperCase();
	}

	/**
	 * Metodo devuelve una cadena formateada.
	 * 
	 * @param cadena
	 * @return
	 */
	public static String toLowerBlank(String cadena) {
		return toBlank(cadena).toLowerCase();
	}

	/**
	 * Metodo valida la existencia de una valor en una lista de valores
	 * 
	 * @param valor
	 * @param valores
	 * @return
	 */
	public static boolean inList(Object valor, Object... valores) {
		for (Object valorIt : valores) {
			if (equiv(valor, valorIt)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo mapear campos
	 * 
	 * @param clase
	 * @return Map<String, Field>
	 */
	public static Map<String, Field> _toMapFields(final Class<?> clase) {
		final Field[] fields = clase.getDeclaredFields();
		final Map<String, Field> mapFields = new HashMap<String, Field>(0);
		for (final Field field : fields) {
			mapFields.put(field.getName(), field);
		}
		return mapFields;
	}

	/**
	 * Metodo mapear metodos
	 * 
	 * @param clase
	 * @return Map<String, Method>
	 */
	public static Map<String, Method> _toMapMethods(final Class<?> clase) {
		final Method[] methods = clase.getDeclaredMethods();
		final Map<String, Method> mapMethods = new HashMap<String, Method>(0);
		for (final Method method : methods) {
			mapMethods.put(method.getName(), method);
		}
		return mapMethods;
	}

	/**
	 * @param clob
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static String clobToString(Clob clob) throws IOException, SQLException {
		if (clob == null)
			return "";
		StringBuffer str = new StringBuffer();
		String strng;
		BufferedReader bufferRead = new BufferedReader(clob.getCharacterStream());
		while ((strng = bufferRead.readLine()) != null)
			str.append(strng);
		return str.toString();
	}

	/**
	 * Metodo que splitea una cadena mediante un separador en un array String
	 *
	 * @param cadena
	 * @param separador
	 * @return String[]
	 */
	public static String[] split(Object cadena, String separador) {
		if (isEmpty(cadena)) {
			return new String[0];
		}
		if (isEmpty(separador)) {
			return new String[0];
		}
		return toStr(cadena).split(separador);
	}

	/**
	 * Valida expresiones regulares segun el diccionario de Entel reemplazandolo por
	 * un caracter vacio
	 *
	 * @param cadenaEvaluada
	 * @return cadenaCorregida
	 */
	public static String validarEntelREGEX(String cadenaEvaluada) {
		String cadenaCorregida = null;
		if (!isEmpty(cadenaEvaluada)) {
			cadenaCorregida = cadenaEvaluada.replaceAll("&", "y");
			cadenaCorregida = cadenaCorregida.replaceAll("[\"|;&$#\\(\\)\\[\\]\\{\\}']", Constante.CONST_VACIA);
		}
		return cadenaCorregida;
	}
}
