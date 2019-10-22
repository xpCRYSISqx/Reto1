package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ModificarBBDD {
	
	ConexionBBDD conexion = null;
	Connection conn = null;
	
	public ModificarBBDD() {
		conexion = new ConexionBBDD();
		conn = conexion.conectar();
	}
	
	public int insertarDepartamento(String nombre, String localizacion) {
		CallableStatement stmt = null;
		String query = "{call insertarReserva(?, ?, ?)}";
		int codDepart = -1;
		
		try {
			stmt = conn.prepareCall(query);
			stmt.setString(1, nombre);
			stmt.setString(2, localizacion);
			stmt.registerOutParameter(3, Types.INTEGER);
			stmt.executeQuery();
			codDepart = stmt.getInt(3);
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error en la base de datos: error en insert", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		return codDepart;
	}

}
