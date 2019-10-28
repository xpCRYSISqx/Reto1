package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EscritorBBDD {
	
	Modelo modelo = null;
	PoolConexiones pool = null;
	
	public EscritorBBDD(Modelo modelo) {
		this.modelo = modelo;
		this.pool = modelo.pool;
	}
	
	/**
	 * 
	 * @param codDepart
	 * @param nombre
	 * @param localizacion
	 */
	public void insertarDepartamento(int codDepart, String nombre, String localizacion) {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		String query = "INSERT INTO DEPARTAMENTOS (COD_DEPART, NOMBRE, LOCALIZACION) VALUES (?, ?, ?)";
		try {
			stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, codDepart);
			stmt.setString(2, nombre);
			stmt.setString(3, localizacion);
			stmt.executeUpdate();
		} catch (SQLException e) {
//			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error al intentar guardar los datos", JOptionPane.ERROR_MESSAGE);
//			System.exit(0);
		} finally {
			pool.desconectar();
		}
	}
	
	/**
	 * 
	 * @param codCargo
	 * @param nombre
	 */
	public void insertarCargo(int codCargo, String nombre) {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		String query = "INSERT INTO CARGOS (COD_CARGO, NOMBRE) VALUES (?, ?)";
		try {
			stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, codCargo);
			stmt.setString(2, nombre);
			stmt.executeUpdate();
		} catch (SQLException e) {
//			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error al intentar guardar los datos", JOptionPane.ERROR_MESSAGE);
//			System.exit(0);
		} finally {
			pool.desconectar();
		}
	}
	
	/**
	 * 
	 * @param codEmpleado
	 * @param nombre
	 * @param apellidos
	 * @param sueldo
	 * @param codDepart
	 * @param codCargo
	 * @param codJefe
	 * @param esJefe
	 */
	public void insertarEmpleado(int codEmpleado, String nombre, String apellidos, float sueldo, int codDepart, int codCargo, Integer codJefe, boolean esJefe) {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		String query = "INSERT INTO EMPLEADOS (COD_EMPLE, NOMBRE, APELLIDOS, SUELDO, COD_DEPART, COD_CARGO, COD_JEFE, ES_JEFE, FECHA_ALTA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW())";
		if (codJefe == 0) {
			codJefe = null;
		}
		try {
			stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, codEmpleado);
			stmt.setString(2, nombre);
			stmt.setString(3, apellidos);
			stmt.setFloat(4, sueldo);
			stmt.setInt(5, codDepart);
			stmt.setInt(6, codCargo);
			stmt.setObject(7, codJefe, java.sql.Types.INTEGER);
			stmt.setBoolean(8, esJefe);
			stmt.executeUpdate();
		} catch (SQLException e) {
//			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error al intentar guardar los datos", JOptionPane.ERROR_MESSAGE);
//			System.exit(0);
		} finally {
			pool.desconectar();
		}
	}

}
