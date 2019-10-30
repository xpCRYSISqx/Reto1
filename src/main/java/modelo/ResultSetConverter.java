package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ResultSetConverter {
	
	EscritorFicheros escritorFicheros = new EscritorFicheros();
	
	/**
	 * 
	 * @param result
	 * @return
	 */
	public ArrayList<Departamento> convertDepartamentos(ResultSet result) {
		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
		Departamento depart = new Departamento();
		try {
			while (result.next()) {
				depart.setNombre(result.getString("NOMBRE"));
				depart.setLocalizacion(result.getString("LOCALIZACION"));
				departamentos.add(depart);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
			escritorFicheros.crearLog(new Date(), e.toString());
		}
		return departamentos;
	}
	
	/**
	 * 
	 * @param result
	 * @return
	 */
	public ArrayList<Cargo> convertCargos(ResultSet result) {
		ArrayList<Cargo> cargos = new ArrayList<Cargo>();
		Cargo cargo = new Cargo();
		try {
			while (result.next()) {
				cargo.setNombre(result.getString("NOMBRE"));
				cargos.add(cargo);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
			escritorFicheros.crearLog(new Date(), e.toString());
		}
		return cargos;
	}
	
	/**
	 * 
	 * @param result
	 * @return
	 */
	public ArrayList<Empleado> convertEmpleados(ResultSet result) {
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		Empleado emple = new Empleado();
		try {
			while (result.next()) {
				emple.setNombre(result.getString("NOMBRE"));
				emple.setApellidos(result.getString("APELLIDOS"));
				emple.setSueldo(result.getInt("SUELDO"));
				emple.setCodDepartamento(result.getInt("COD_DEPART"));
				emple.setCodCargo(result.getInt("COD_CARGO"));
				emple.setCodJefe(result.getInt("COD_JEFE"));
				emple.setEsJefe(result.getBoolean("ES_JEFE"));
				empleados.add(emple);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
			escritorFicheros.crearLog(new Date(), e.toString());
		}
		return empleados;
	}

}
