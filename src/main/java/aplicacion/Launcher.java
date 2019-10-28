package aplicacion;

import java.util.ArrayList;

import controlador.Controlador;
import modelo.Cargo;
import modelo.Departamento;
import modelo.LectorArchivos;
import modelo.Modelo;
import vista.Vista;

public class Launcher {
	
	public static void main(String[] args) {
		Vista vista = new Vista();
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador(vista, modelo);
		
		// ESTO ES DE PRUEBA !!
		int codDepart = modelo.escritorBBDD.insertarDepartamento("Informatica", "Elorrieta");
		System.out.println("Departamento: " + codDepart);
		
		int codCargo = modelo.escritorBBDD.insertarCargo("Director");
		System.out.println("Cargo: " + codCargo);
		
		int codEmpleado = modelo.escritorBBDD.insertarEmpleado("Nombre", "Apellido", 2000, 1, 2, 1, true);
		System.out.println("Empleado: " + codEmpleado);
	}
}