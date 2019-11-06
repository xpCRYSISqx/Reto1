package modelo;

public class Comprobadores {
	
	private Modelo modelo;
	
	public Comprobadores(Modelo modelo) {
		this.modelo = modelo;
	}

	public boolean comprobarNumerico(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
	
	public boolean comprobarEmpleadoCodigo(int codEmple) {
		for(int i = 0; i < modelo.empleados.size(); i++) {
			Empleado emple = modelo.empleados.get(i);
			if (codEmple == emple.getCodEmpleado()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean comprobarEmpleadoNombre(String nombre, String apellidos) {
		for(int i = 0; i < modelo.empleados.size(); i++) {
			Empleado emple = modelo.empleados.get(i);
			if (nombre == emple.getNombre() && apellidos == emple.getApellidos()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean comprobarDepartamentoCodigo(int codDepart) {
		for(int i = 0; i < modelo.departamentos.size(); i++) {
			if (codDepart == modelo.departamentos.get(i).getCodDepartamento()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean comprobarDepartamentoNombre(String nombre, String loc) {
		for(int i = 0; i < modelo.departamentos.size(); i++) {
			if (nombre == modelo.departamentos.get(i).getNombre() && loc == modelo.departamentos.get(i).getLocalizacion()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean comprobarCargoCodigo(int codCargo) {
		for(int i = 0; i < modelo.cargos.size(); i++) {
			if (codCargo == modelo.cargos.get(i).getCodCargo()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean comprobarCargoNombre(String nombre) {
		for(int i = 0; i < modelo.cargos.size(); i++) {
			if (nombre == modelo.cargos.get(i).getNombre()) {
				return true;
			}
		}
		return false;
	}
}