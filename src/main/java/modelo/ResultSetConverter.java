package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultSetConverter {
	
	private Modelo modelo;
	private EscritorFicheros escritorFicheros;
	
	public ResultSetConverter(Modelo modelo) {
		this.modelo = modelo;
		this.escritorFicheros = new EscritorFicheros(modelo);
	}
	
	/**
	 * 
	 * @param result
	 * @return
	 */
	public ArrayList<Departamento> convertDepartamentos(ResultSet result) {
		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
		try {
			while (result.next()) {
				Departamento depart = new Departamento();
				depart.setCodDepartamento(result.getInt("COD_DEPART"));
				depart.setNombre(result.getString("NOMBRE"));
				depart.setLocalizacion(result.getString("LOCALIZACION"));
				departamentos.add(depart);
	        }
		} catch (SQLException e) {
			escritorFicheros.crearLog(this.getClass().getName(), this.getClass().getEnclosingMethod().getName(), e.toString());
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
		try {
			while (result.next()) {
				Cargo cargo = new Cargo();
				cargo.setCodCargo(result.getInt("COD_CARGO"));
				cargo.setNombre(result.getString("NOMBRE"));
				cargos.add(cargo);
	        }
		} catch (SQLException e) {
			escritorFicheros.crearLog(this.getClass().getName(), this.getClass().getEnclosingMethod().getName(), e.toString());
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
		try {
			while (result.next()) {
				Empleado emple = new Empleado();
				emple.setCodEmpleado(result.getInt("COD_EMPLE"));
				emple.setNombre(result.getString("NOMBRE"));
				emple.setApellidos(result.getString("APELLIDOS"));
				emple.setSueldo(result.getInt("SUELDO"));
				emple.setCodDepartamento(result.getInt("COD_DEPART"));
				emple.setCodCargo(result.getInt("COD_CARGO"));
				emple.setCodJefe(result.getInt("COD_JEFE"));
				emple.setEsJefe(result.getBoolean("ES_JEFE"));
				emple.setFechaAlta(result.getDate("FECHA_ALTA"));
				empleados.add(emple);
	        }
		} catch (SQLException e) {
			escritorFicheros.crearLog(this.getClass().getName(), this.getClass().getEnclosingMethod().getName(), e.toString());
		}
		return empleados;
	}

}
