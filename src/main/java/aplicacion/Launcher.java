package aplicacion;

import controlador.Controlador;
import modelo.Modelo;
import vista.Vista;

public class Launcher {
	
	public static void main(String[] args) {
		Vista vista = new Vista();
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador(vista, modelo);
		
		// ESTO ES DE PRUEBA !!
		int codDepart = modelo.insertarDepartamento("Informatica", "Elorrieta");
		System.out.println(codDepart);
	}
}