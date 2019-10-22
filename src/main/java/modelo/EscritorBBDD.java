package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EscritorBBDD {
	
	PoolConexiones pool = null;
	
	public EscritorBBDD() {
		pool = PoolConexiones.getPool();
	}
	
	public int insertarDepartamento(String nombre, String localizacion) {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "INSERT INTO DEPARTAMENTOS (NOMBRE, LOCALIZACION) VALUES (?, ?)";
		int codDepart = -1;
		try {
			stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, nombre);
			stmt.setString(2, localizacion);
			stmt.executeUpdate();
			result = stmt.getGeneratedKeys();
			result.next();
			codDepart = result.getInt(1);
		} catch (SQLException e) {
//			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error al intentar guardar los datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} finally {
			pool.desconectar();
		}
		return codDepart;
	}
	
	public int insertarCargo(String nombre) {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "INSERT INTO CARGOS (NOMBRE) VALUES (?)";
		int codCargo = -1;
		try {
			stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, nombre);
			stmt.executeUpdate();
			result = stmt.getGeneratedKeys();
			result.next();
			codCargo = result.getInt(1);
		} catch (SQLException e) {
//			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error al intentar guardar los datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} finally {
			pool.desconectar();
		}
		return codCargo;
	}
	
	public int insertarEmpleado(String nombre, String apellidos, float sueldo, int codDepart, int codCargo, int codJefe, boolean esJefe, Timestamp fechaAlta) {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "INSERT INTO EMPLEADOS (NOMBRE, LOCALIZACION) VALUES (?, ?)";
		int codEmpleado = -1;
		try {
			stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, nombre);
			stmt.setString(2, apellidos);
			stmt.setFloat(3, sueldo);
			stmt.setInt(4, codDepart);
			stmt.setInt(5, codCargo);
			stmt.setInt(6, codJefe);
			stmt.setBoolean(7, esJefe);
			stmt.setTimestamp(8, fechaAlta);
			stmt.executeUpdate();
			result = stmt.getGeneratedKeys();
			result.next();
			codEmpleado = result.getInt(1);
		} catch (SQLException e) {
//			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error al intentar guardar los datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} finally {
			pool.desconectar();
		}
		return codEmpleado;
	}

}
