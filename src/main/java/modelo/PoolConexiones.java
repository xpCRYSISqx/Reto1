package modelo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.dbcp2.BasicDataSource;


public class PoolConexiones {
	
	private static PoolConexiones pool;
	private BasicDataSource dataSource;
	private Connection conexion;
	private Modelo modelo;
	private EscritorFicheros escritorFicheros;
		
	private PoolConexiones(Modelo modelo) {
		this.modelo = modelo;
		this.escritorFicheros = new EscritorFicheros(modelo);
		crearPoolConexiones();
	}
	
	public static PoolConexiones getPool(Modelo modelo) {
		if (pool == null){
			pool = new PoolConexiones(modelo);
		}
		return pool;
	}
		
	private void crearPoolConexiones() {
	    String[] datos = getDatos();
	    dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:mysql://" + datos[0] + "/" + datos[1] + "?serverTimezone=UTC");
		dataSource.setUsername(datos[2]);
		dataSource.setPassword(datos[3]);
		dataSource.setMinIdle(5);
		dataSource.setMaxIdle(10);
		dataSource.setMaxOpenPreparedStatements(100);
	}	
	
	/**
	 * Pide una conexion al pool de conexiones y la almacena en el atributo 'conexion'
	 */
	protected Connection conectar() {
		try {
			conexion = dataSource.getConnection();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos: error de conexion", JOptionPane.ERROR_MESSAGE);
			escritorFicheros.crearLog(e.toString(), new Object() {} .getClass().getEnclosingMethod().getName(), new Object() {} .getClass().getName());
		}
		return conexion;
	}
	
	/**
	 * Cierra la conexion
	 */
	protected void desconectar() {
		if (conexion != null) {
			try {
				conexion.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos: error de desconexion", JOptionPane.ERROR_MESSAGE);
				escritorFicheros.crearLog(e.toString(), new Object() {} .getClass().getEnclosingMethod().getName(), new Object() {} .getClass().getName());
			}
		}
	}
    
	/**
	 * Lee las credenciales de conexion a la base de datos
	 * @return
	 */
    private String[] getDatos() {
    	return modelo.lectorArchivos.leerDatosConexion("datosBD.txt");
    }
}
