package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LectorBBDD {
	
PoolConexiones pool = null;
	
	public LectorBBDD() {
		pool = PoolConexiones.getPool();
	}
	
	public ResultSet obtenerDepartamento(String nombre, String localizacion) {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM DEPARTAMENTOS WHERE NOMBRE = ? AND LOCALIZACION = ?";

		try {
			stmt = conexion.prepareStatement(query);
			stmt.setString(1, nombre);
			stmt.setString(2, localizacion);
			result = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return result;
	}
	
	public boolean comprobarDepartamento(String nombre, String localizacion) {
		ResultSet result = obtenerDepartamento(nombre, localizacion);	
		try {
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return false;
	}
	
	public ResultSet obtenerEmpleado(String nombre, String apellidos) {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM EMPLEADOS WHERE NOMBRE = ?  AND APELLIDOS = ?";

		try {
			stmt = conexion.prepareStatement(query);
			stmt.setString(1, nombre);
			stmt.setString(2, apellidos);
			result = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return result;
	}
	
	public boolean comprobarEmpleado(String nombre, String apellidos) {
		ResultSet result = obtenerEmpleado(nombre, apellidos);	
		try {
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return false;
	}
	
	public ResultSet obtenerCargo(String nombre) {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM EMPLEADOS WHERE NOMBRE = ?";

		try {
			stmt = conexion.prepareStatement(query);
			stmt.setString(1, nombre);
			result = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return result;
	}
	
	public boolean comprobarCargo(String nombre) {
		ResultSet result = obtenerCargo(nombre);	
		try {
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return false;
	}

}
