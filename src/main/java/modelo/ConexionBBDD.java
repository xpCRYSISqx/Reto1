package modelo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ConexionBBDD {
	
	Connection conexion = null;
	Pool pool = null;
	
	public ConexionBBDD() {
		pool = new Pool();
		conectar();
	}
	
	/**
	 * Pide una conexion al pool de conexiones y la almacena en el atributo 'conexion'
	 */
	protected Connection conectar() {
		try {
			conexion = pool.getConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos: error de conexion", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return conexion;
	}
	
	protected void desconectar() {
		if (conexion != null) {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
