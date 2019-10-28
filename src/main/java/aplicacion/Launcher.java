package aplicacion;

import java.util.ArrayList;

import controlador.Controlador;
import modelo.Cargo;
import modelo.LectorArchivos;
import modelo.Departamento;
import modelo.Empleado;
import modelo.Modelo;
import vista.Vista;

public class Launcher {
	
	public static void main(String[] args) {
		Vista vista = new Vista();
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador(vista, modelo);
		
		
		// ESTO ES DE PRUEBA !!
		
		ArrayList<Departamento> departamentos = modelo.lectorArchivos.leerDepartamentoXML("departamentos.xml");
		for(int i = 0; i < departamentos.size(); i++) {
			Departamento depart = departamentos.get(i);
			if (!modelo.lectorBBDD.comprobarDepartamento(depart.getNombre(), depart.getLocalizacion())) {
				modelo.escritorBBDD.insertarDepartamento(depart.getNombre(), depart.getLocalizacion());
				System.out.println("Insertando departamento " + depart.getNombre());
			} else {
				System.out.println("El departamento " + depart.getNombre() + " ya existe en la base de datos");
			}
		}
		
		/*ArrayList<Empleado> empleados = modelo.lectorArchivos.leerEmpleadoXML("empleados.xml");
		for(int i = 0; i < empleados.size(); i++) {
			Empleado empleado = empleados.get(i);
			if (!modelo.lectorBBDD.comprobarEmpleado(empleado.getNombre(), empleado.getApellidos())) {
				modelo.escritorBBDD.insertarEmpleado(empleado.getNombre(), empleado.getApellidos(), empleado.getSueldo(), empleado.getCodDepartamento(), empleado.getCodCargo(), empleado.getCodJefe(), empleado.getEsJefe());
				System.out.println("Insertando empleado " + empleado.getNombre());
			} else {
				System.out.println("El empleado " + empleado.getNombre() + " ya existe en la base de datos");
			}
		}*/
		
		ArrayList<Cargo> cargo = modelo.lectorArchivos.leerCargosCSV("cargos.csv");
		
	}
}