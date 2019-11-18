package modelo;

import java.util.ArrayList;

public class CargadorDatos {
	
	private Modelo modelo;
	
	public CargadorDatos(Modelo modelo) {
		this.modelo = modelo;
	}
	
	public void actualizarTodosLosDatos() {
		actualizarDepartamentos();
		actualizarCargos();
		actualizarEmpleados();
		actualizarJefes();
	}

	public void actualizarDepartamentos() {
		this.modelo.departamentos = this.modelo.lectorBBDD.obtenerTodosLosDepartamento();
	}

	public void actualizarCargos() {
		this.modelo.cargos = this.modelo.lectorBBDD.obtenerTodosLosCargos();
	}

	public void actualizarEmpleados() {
		this.modelo.empleados = this.modelo.lectorBBDD.obtenerTodosLosEmpleados();
	}

	public void actualizarJefes() {
		this.modelo.jefes = this.modelo.lectorBBDD.obtenerJefes();
	}
	
	/**
	 * Insertar departamentos leidos del fichero
	 * @return
	 */
	public String cargarDepartamentos() {
		String info = "";
		ArrayList<Departamento> departamentos = modelo.lectorArchivos.leerDepartamentoXML("departamentos.xml");
		for(int i = 0; i < departamentos.size(); i++) {
			Departamento depart = departamentos.get(i);
			if (modelo.comprobador.comprobarDepartamentoCodigo(depart.getCodDepartamento())) {
				info += "Ya existe un departamento con código '" + depart.getCodDepartamento() + "'\n";
			} else if (modelo.comprobador.comprobarDepartamentoNombre(depart.getNombre(), depart.getLocalizacion())) {
				info += "Ya existe un departamento con nombre '" + depart.getNombre() + "' en la localizacion '" + depart.getLocalizacion() + "'\n";
			} else {
				modelo.escritorBBDD.insertarDepartamento(depart);
				info += "Insertado departamento " + depart.getNombre() + "\n";
			}
		}
		return info;
	}
	
	/**
	 * Insertar cargos leidos del fichero
	 * @return
	 */
	public String cargarCargos() {
		String info = "";
		ArrayList<Cargo> cargos = modelo.lectorArchivos.leerCargosCSV("cargos.csv");
		for(int i = 0; i < cargos.size(); i++) {
			Cargo cargo = cargos.get(i);
			if (modelo.comprobador.comprobarCargoCodigo(cargo.getCodCargo())) {
				info += "Ya existe un cargo con código '" + cargo.getCodCargo() + "'\n";
			} else if (modelo.comprobador.comprobarCargoNombre(cargo.getNombre())) {
				info += "Ya existe un cargo con nombre '" + cargo.getNombre() + "'\n";
			} else {
				modelo.escritorBBDD.insertarCargo(cargo);
				info += "Insertado cargo " + cargo.getNombre() + "\n";
			}
		}
		return info;
	}
	
	/**
	 * Insertar empleados leidos del fichero
	 * @return
	 */
	public String cargarEmpleados() {
		String info = "";
		ArrayList<Empleado> empleados = modelo.lectorArchivos.leerEmpleadoXML("empleados.xml");
		for(int i = 0; i < empleados.size(); i++) {
			Empleado empleado = empleados.get(i);
			if (modelo.comprobador.comprobarEmpleadoCodigo(empleado.getCodEmpleado())) {
				info += "Ya existe un empleado con el código '" + empleado.getCodEmpleado() + "' en la base de datos" + "\n";
			} else if (modelo.comprobador.comprobarEmpleadoNombre(empleado.getNombre(), empleado.getApellidos())) {
				info += "Ya existe un empleado con nombre '" + empleado.getNombre() + "' y apellidos '" + empleado.getApellidos() + "' en la base de datos" + "\n";
			} else {
				modelo.escritorBBDD.insertarEmpleado(empleado);
				info += "Insertado empleado " + empleado.getNombre() + "\n";
			}
		}
		return info;
	}	

}
