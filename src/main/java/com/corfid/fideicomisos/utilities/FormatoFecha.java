package com.corfid.fideicomisos.utilities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormatoFecha {
	public static String obtenerFechaActual() {
		int dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int mes = Calendar.getInstance().get(Calendar.MONTH);
		int anio = Calendar.getInstance().get(Calendar.YEAR);
		String strDia = dia < 10 ? "0" + dia : dia + "";
		String strMes = mes < 9 ? "0" + (mes + 1) : (mes + 1) + "";
		String strAnio = String.valueOf(anio);
		String fecha = strDia + "/" + strMes + "/" + strAnio;
		return fecha;
	}

	public static String obtenerHora() {
		Calendar calendar = Calendar.getInstance();
		int intHora = calendar.get(Calendar.HOUR_OF_DAY);
		int intMinutos = calendar.get(Calendar.MINUTE);
		int intSegundos = calendar.get(Calendar.SECOND);
		String strHora = intHora < 10 ? "0" + intHora : intHora + "";
		String strMinutos = intMinutos < 10 ? "0" + (intMinutos) : (intMinutos) + "";
		String strSegundos = intSegundos < 10 ? "0" + (intSegundos) : (intSegundos) + "";
		String strHoraCompleta = strHora + ":" + strMinutos + ":" + strSegundos;
		return strHoraCompleta;
	}

	public static Timestamp stringToTimestampddMMyyyyhhmmss(String dateString, String hourString) {

		java.sql.Timestamp timestamp = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			java.util.Date parsedDate = dateFormat.parse(dateString + " " + hourString);

			timestamp = new java.sql.Timestamp(parsedDate.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return timestamp;
	}
}
