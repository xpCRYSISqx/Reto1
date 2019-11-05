package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LectorBBDD {
	
	Modelo modelo = null;
	PoolConexiones pool = null;
	EscritorFicheros escritorFicheros = new EscritorFicheros();
	
	public LectorBBDD(Modelo modelo) {
		this.modelo = modelo;
		this.pool = modelo.pool;
	}
	
	public ArrayList<Departamento> obtenerDepartamento(Departamento departamento) {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
		String query = "SELECT * FROM DEPARTAMENTOS WHERE NOMBRE = ? AND LOCALIZACION = ?";
		try {
			stmt = conexion.prepareStatement(query);
			stmt.setString(1, departamento.getNombre());
			stmt.setString(2, departamento.getLocalizacion());
			result = stmt.executeQuery();
			departamentos = modelo.converter.convertDepartamentos(result);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			escritorFicheros.crearLog(new Date(), e.toString());
			System.exit(0);
		} finally {
			pool.desconectar();
		}
		return departamentos;
	}
	
	public ArrayList<Cargo> obtenerCargo(Cargo cargo) {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList<Cargo> cargos = new ArrayList<Cargo>();
		String query = "SELECT * FROM CARGOS WHERE NOMBRE = ?";
		try {
			stmt = conexion.prepareStatement(query);
			stmt.setString(1, cargo.getNombre());
			result = stmt.executeQuery();
			cargos = modelo.converter.convertCargos(result);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			escritorFicheros.crearLog(new Date(), e.toString());
			System.exit(0);
		} finally {
			pool.desconectar();
		}
		return cargos;
	}
	
	public ArrayList<Empleado> obtenerEmpleado(Empleado empleado) {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		String query = "SELECT * FROM EMPLEADOS WHERE NOMBRE = ? AND APELLIDOS = ?";
		try {
			stmt = conexion.prepareStatement(query);
			stmt.setString(1, empleado.getNombre());
			stmt.setString(2, empleado.getApellidos());
			result = stmt.executeQuery();
			empleados = modelo.converter.convertEmpleados(result);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			escritorFicheros.crearLog(new Date(), e.toString());
			System.exit(0);
		} finally {
			pool.desconectar();
		}
		return empleados;
	}
	
	public ArrayList<Departamento> obtenerTodosLosDepartamento() {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
		String query = "SELECT * FROM DEPARTAMENTOS";
		try {
			stmt = conexion.prepareStatement(query);
			result = stmt.executeQuery();
			departamentos = modelo.converter.convertDepartamentos(result);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			escritorFicheros.crearLog(new Date(), e.toString());
			System.exit(0);
		} finally {
			pool.desconectar();
		}
		return departamentos;
	}
	
	public ArrayList<Cargo> obtenerTodosLosCargos() {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList<Cargo> cargos = new ArrayList<Cargo>();
		String query = "SELECT * FROM CARGOS";
		try {
			stmt = conexion.prepareStatement(query);
			result = stmt.executeQuery();
			cargos = modelo.converter.convertCargos(result);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			escritorFicheros.crearLog(new Date(), e.toString());
			System.exit(0);
		} finally {
			pool.desconectar();
		}
		return cargos;
	}
	
	public ArrayList<Empleado> obtenerTodosLosEmpleados() {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		String query = "SELECT * FROM EMPLEADOS";
		try {
			stmt = conexion.prepareStatement(query);
			result = stmt.executeQuery();
			empleados = modelo.converter.convertEmpleados(result);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			escritorFicheros.crearLog(new Date(), e.toString());
			System.exit(0);
		} finally {
			pool.desconectar();
		}
		return empleados;
	}
	
	public ArrayList<Empleado> obtenerJefes() {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		String query = "SELECT * FROM EMPLEADOS WHERE ES_JEFE = 1";
		try {
			stmt = conexion.prepareStatement(query);
			result = stmt.executeQuery();
			empleados = modelo.converter.convertEmpleados(result);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			escritorFicheros.crearLog(new Date(), e.toString());
			System.exit(0);
		} finally {
			pool.desconectar();
		}
		return empleados;
	}
	
	public Empleado obtenerEmpleadoPorCodigo(int codigo) {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		ResultSet result = null;
		Empleado empleado = new Empleado();
		String query = "SELECT * FROM EMPLEADOS WHERE COD_EMPLE = ?";
		try {
			stmt = conexion.prepareStatement(query);
			stmt.setInt(1, codigo);
			result = stmt.executeQuery();
			empleado.setNombre(result.getString("NOMBRE"));
			empleado.setApellidos(result.getString("APELLIDOS"));
			empleado.setSueldo(result.getInt("SUELDO"));
			empleado.setCodDepartamento(result.getInt("COD_DEPART"));
			empleado.setCodCargo(result.getInt("COD_CARGO"));
			empleado.setCodJefe(result.getInt("COD_JEFE"));
			empleado.setEsJefe(result.getBoolean("ES_JEFE"));
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			escritorFicheros.crearLog(new Date(), e.toString());
			System.exit(0);
		} finally {
			pool.desconectar();
		}
		return empleado;
	}
}
