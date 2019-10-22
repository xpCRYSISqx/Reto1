package modelo;

import Reto4Grupo1BBDD.BasicDataSource;

public class Pool {

	private BasicDataSource dataSource;
		
	public Pool() {
			crearPoolConexiones();
	}
		
	public void crearPoolConexiones() {
	    String[] datos = getDatos();
	    dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:mysql://" + datos[0] + "/" + datos[1] + "?serverTimezone=UTC");
		dataSource.setUsername(datos[2]);
		dataSource.setPassword(datos[3]);
		dataSource.setMinIdle(5);
		dataSource.setMaxIdle(10);
		dataSource.setMaxOpenPreparedStatements(100);
	}		   
}
