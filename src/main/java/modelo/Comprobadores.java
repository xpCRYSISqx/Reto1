package modelo;

public class Comprobadores {

	
	public boolean comprobarNumerico(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		}
		catch(NumberFormatException nfe) {
			return false;
		}
	}
}