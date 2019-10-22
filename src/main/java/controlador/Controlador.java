package controlador;

import modelo.Modelo;
import vista.Vista;

public class Controlador {
	
	public Vista vista;
	public Modelo modelo;
	
	public Controlador(Vista vista, Modelo modelo) {
		this.vista = vista;
		this.modelo = modelo;
	}
}