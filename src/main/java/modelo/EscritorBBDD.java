package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EscritorBBDD {
	
	Modelo modelo = null;
	PoolConexiones pool = null;
	EscritorFicheros escritorFicheros = new EscritorFicheros();
	
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
	public void insertarDepartamento(Departamento departamento) {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		String query = "INSERT INTO DEPARTAMENTOS (COD_DEPART, NOMBRE, LOCALIZACION) VALUES (?, ?, ?)";
		try {
			stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, departamento.getCodDepartamento());
			stmt.setString(2, departamento.getNombre());
			stmt.setString(3, departamento.getLocalizacion());
			stmt.executeUpdate();
		} catch (SQLException e) {
//			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error al intentar guardar los datos", JOptionPane.ERROR_MESSAGE);
			escritorFicheros.crearLog(new Date(), e.toString());
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
	public void insertarCargo(Cargo cargo) {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		String query = "INSERT INTO CARGOS (COD_CARGO, NOMBRE) VALUES (?, ?)";
		try {
			stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, cargo.getCodCargo());
			stmt.setString(2, cargo.getNombre());
			stmt.executeUpdate();
		} catch (SQLException e) {
//			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error al intentar guardar los datos", JOptionPane.ERROR_MESSAGE);
			escritorFicheros.crearLog(new Date(), e.toString());
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
	public void insertarEmpleado(Empleado empleado) {
		Connection conexion = pool.conectar();
		PreparedStatement stmt = null;
		String query = "INSERT INTO EMPLEADOS (COD_EMPLE, NOMBRE, APELLIDOS, SUELDO, COD_DEPART, COD_CARGO, COD_JEFE, ES_JEFE, FECHA_ALTA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW())";
		if (empleado.getCodJefe() == 0) {
			empleado.setCodJefe((Integer) null);
		}
		try {
			stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, empleado.getCodEmpleado());
			stmt.setString(2, empleado.getNombre());
			stmt.setString(3, empleado.getApellidos());
			stmt.setFloat(4, empleado.getSueldo());
			stmt.setInt(5, empleado.getCodDepartamento());
			stmt.setInt(6, empleado.getCodCargo());
			stmt.setObject(7, empleado.getCodJefe(), java.sql.Types.INTEGER);
			stmt.setBoolean(8, empleado.getEsJefe());
			stmt.executeUpdate();
		} catch (SQLException e) {
//			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error al intentar guardar los datos", JOptionPane.ERROR_MESSAGE);
			escritorFicheros.crearLog(new Date(), e.toString());
//			System.exit(0);
		} finally {
			pool.desconectar();
		}
	}

}
