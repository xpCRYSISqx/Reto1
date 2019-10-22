package modelo;

public class Modelo {
	
	public ModificarBBDD modificarBBDD = null;
	
	public Modelo() {
		modificarBBDD = new ModificarBBDD();
	}
	
	public int insertarDepartamento(String nombre, String localizacion) {
		int result = modificarBBDD.insertarDepartamento(nombre, localizacion);
		return result;
	}

}
