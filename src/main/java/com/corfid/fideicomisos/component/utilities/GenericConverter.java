package com.corfid.fideicomisos.component.utilities;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.corfid.fideicomisos.model.utilities.CatalogoErrorModel;
import com.corfid.fideicomisos.service.utilities.CatalogoErrorInterface;

public class GenericConverter {

	@Autowired
	@Qualifier("catalogoErrorServiceImpl")
	CatalogoErrorInterface catalogoErrorInterface;

	protected String obtenerMensajeError(String codigoError) throws Exception {
		CatalogoErrorModel catalogoErrorModel = new CatalogoErrorModel();
		catalogoErrorModel = catalogoErrorInterface.obtenerMensajeError(codigoError);

		return catalogoErrorModel.getMensaje();
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
