package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ModificarBBDD {
	
	ConexionBBDD conexionBBDD = null;
	
	public ModificarBBDD() {
		conexionBBDD = new ConexionBBDD();
	}
	
	public int insertarDepartamento(String nombre, String localizacion) {
		Connection conexion = conexionBBDD.conectar();
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
			conexionBBDD.desconectar();
		}
		return codDepart;
	}

}
