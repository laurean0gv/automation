package utils;

import java.util.ResourceBundle;

public class Configuracion {

	public static String getPropiedad (String test, String clave) {
		ResourceBundle recursoBundle =ResourceBundle.getBundle("recursos."+test);
		return recursoBundle.getString(clave);
	}
}
