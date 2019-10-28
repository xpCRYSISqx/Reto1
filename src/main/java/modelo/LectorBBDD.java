package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LectorBBDD {
	
	Modelo modelo = null;
	PoolConexiones pool = null;
	
	public LectorBBDD(Modelo modelo) {
		this.modelo = modelo;
		this.pool = modelo.pool;
	}
	
	public ArrayList<Departamento> obtenerDepartamento(String nombre, String localizacion) {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
		String query = "SELECT * FROM DEPARTAMENTOS WHERE NOMBRE = ? AND LOCALIZACION = ?";
		try {
			stmt = conexion.prepareStatement(query);
			stmt.setString(1, nombre);
			stmt.setString(2, localizacion);
			result = stmt.executeQuery();
			departamentos = modelo.converter.convertDepartamentos(result);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} finally {
			pool.desconectar();
		}
		return departamentos;
	}
	
	public ArrayList<Cargo> obtenerCargo(String nombre) {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList<Cargo> cargos = new ArrayList<Cargo>();
		String query = "SELECT * FROM CARGOS WHERE NOMBRE = ?";
		try {
			stmt = conexion.prepareStatement(query);
			stmt.setString(1, nombre);
			result = stmt.executeQuery();
			cargos = modelo.converter.convertCargos(result);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} finally {
			pool.desconectar();
		}
		return cargos;
	}
	
	public ArrayList<Empleado> obtenerEmpleado(String nombre, String apellidos) {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		String query = "SELECT * FROM EMPLEADOS WHERE NOMBRE = ? AND APELLIDOS = ?";
		try {
			stmt = conexion.prepareStatement(query);
			stmt.setString(1, nombre);
			stmt.setString(2, apellidos);
			result = stmt.executeQuery();
			empleados = modelo.converter.convertEmpleados(result);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} finally {
			pool.desconectar();
		}
		return empleados;
	}

}
