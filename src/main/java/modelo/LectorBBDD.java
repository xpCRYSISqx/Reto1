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
	
	public LectorBBDD(Modelo modelo) {
		this.modelo = modelo;
		this.pool = modelo.pool;
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
			modelo.escritorFicheros.crearLog(e.toString(), new Object() {} .getClass().getEnclosingMethod().getName(), new Object() {} .getClass().getName());
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
			modelo.escritorFicheros.crearLog(e.toString(), new Object() {} .getClass().getEnclosingMethod().getName(), new Object() {} .getClass().getName());
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
			modelo.escritorFicheros.crearLog(e.toString(), new Object() {} .getClass().getEnclosingMethod().getName(), new Object() {} .getClass().getName());
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
			modelo.escritorFicheros.crearLog(e.toString(), new Object() {} .getClass().getEnclosingMethod().getName(), new Object() {} .getClass().getName());
			System.exit(0);
		} finally {
			pool.desconectar();
		}
		return empleados;
	}

}
