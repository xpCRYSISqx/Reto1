package aplicacion;

import java.util.ArrayList;

import controlador.Controlador;
import modelo.Cargo;
import modelo.LectorArchivos;
import modelo.LectorBBDD;
import modelo.Departamento;
import modelo.Empleado;
import modelo.EscritorBBDD;
import modelo.Modelo;
import vista.Vista;

public class Launcher {
	
	public static void main(String[] args) {
		Vista vista = new Vista();
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador(vista, modelo);
		
		//Insertar departmentos leidos del fichero
		ArrayList<Departamento> departamentos = modelo.lectorArchivos.leerDepartamentoXML("departamentos.xml");
		for(int i = 0; i < departamentos.size(); i++) {
			Departamento depart = departamentos.get(i);
			ArrayList<Departamento> departamentosBD = modelo.lectorBBDD.obtenerDepartamento(depart.getNombre(), depart.getLocalizacion());
			if (departamentosBD.size() == 0) {
				modelo.escritorBBDD.insertarDepartamento(depart.getCodDepartamento(), depart.getNombre(), depart.getLocalizacion());
				System.out.println("Insertando departamento " + depart.getNombre());
			} else {
				System.out.println("El departamento " + depart.getNombre() + " ya existe en la localizacion " + depart.getLocalizacion());
			}
		}
		
		//Insertar cargos leidos del fichero
		ArrayList<Cargo> cargos = modelo.lectorArchivos.leerCargosCSV("cargos.csv");
		for(int i = 0; i < cargos.size(); i++) {
			Cargo cargo = cargos.get(i);
			ArrayList<Cargo> cargosBD = modelo.lectorBBDD.obtenerCargo(cargo.getNombre());
			if (cargosBD.size() == 0) {
				modelo.escritorBBDD.insertarCargo(cargo.getCodCargo(), cargo.getNombre());
				System.out.println("Insertando cargo " + cargo.getNombre());
			} else {
				System.out.println("El cargo " + cargo.getNombre() + " ya existe ");
			}
		}
		
		//Insertar empleados leidos del fichero
		ArrayList<Empleado> empleados = modelo.lectorArchivos.leerEmpleadoXML("empleados.xml");
		for(int i = 0; i < empleados.size(); i++) {
			Empleado empleado = empleados.get(i);
			ArrayList<Empleado> empleadosBD = modelo.lectorBBDD.obtenerEmpleado(empleado.getNombre(), empleado.getApellidos());
			if (empleadosBD.size() == 0) {
				modelo.escritorBBDD.insertarEmpleado(empleado.getCodEmpleado(), empleado.getNombre(), empleado.getApellidos(), empleado.getSueldo(), empleado.getCodDepartamento(), empleado.getCodCargo(), empleado.getCodJefe(), empleado.getEsJefe());
				System.out.println("Insertando empleado " + empleado.getNombre());
			} else {
				System.out.println("El empleado " + empleado.getNombre() + " ya existe en la base de datos");
			}
		}
		
	}
}