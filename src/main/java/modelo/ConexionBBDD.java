package modelo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ConexionBBDD {
	
	Connection conexion = null;
	Pool pool = null;
	
	public ModificarBBDD() {
		pool = new Pool();
		conectar();
	}
	
	/**
	 * Pide una conexion al pool de conexiones y la almacena en el atributo 'conn'
	 */
	private void conectar() {
		try {
			conexion = pool.getConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

}
