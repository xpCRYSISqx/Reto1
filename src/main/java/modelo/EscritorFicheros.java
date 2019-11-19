package modelo;

import java.awt.FileDialog;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EscritorFicheros {
	
	private Modelo modelo;
	
	public EscritorFicheros(Modelo modelo) {
		this.modelo = modelo;
	}
	
	/**
	 * 
	 * Metodo utilizado para generar logs de excepciones del sistema
	 * 
	 * @param fecha Fecha en la que ha ocurrido el error
	 * @param motivo Motivo por el cual a ocurrido el error
	 */
	public void crearLog(String motivo, String metodo, String clase) {
		String ruta = "ficheros" + File.separator + "log.txt"; //Guarda la ruta del fichero
		FileWriter fichero = null;
		PrintWriter escritor = null;
		// fecha
		LocalDateTime fecha = LocalDateTime.now().withNano(0);
		try {
			File log = new File(ruta); //Busca si existe el fichero en la ruta especificada
			if(!log.exists())
				JOptionPane.showMessageDialog(new JFrame(), "Fichero log.txt no encontrado.", null, 0);
			fichero = new FileWriter(ruta, true); //Invoca FileWriter para la ruta especificada, con el true le indicamos que no borre el contedino del fichero, simplemente escrivira a continuacion del contenido actual del fichero
			escritor = new PrintWriter(fichero); //Invoca PrintWriter en el fichero que le especificamos y de la manera que le hemos indicado con FileWriter
			
			//Se escribe en el archivo
			escritor.println(fecha.toLocalDate() + " | " + fecha.toLocalTime() + " | Clase: " + clase + " | Metodo: " + metodo + " | Motivo del error: " + motivo);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally { //Una vez terminado, se cierra el fichero
			try {
				fichero.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Genera una ventana de dialogo que permite elegir donde guardar el archivo
	 * @return ruta que el usuario a elegido
	 */
	public String preguntarGuadar(String tipoInforme) {
		FileDialog fDialog = new FileDialog(new JFrame(), "Save", FileDialog.SAVE);
		String filename = "Informe-" + tipoInforme + ".txt";
		String pathReserva = null;
		fDialog.setFile(filename);
		fDialog.setVisible(true);
		if (fDialog.getDirectory() != null & fDialog.getFile() != null) {
			pathReserva = fDialog.getDirectory() + fDialog.getFile();
		}
		return pathReserva;
	}
	
	public void imprimirTodo(String rutaFichero) {
		this.imprimirDepartamentos(rutaFichero, false);
		this.imprimirEmpleados(rutaFichero, true);
	}
	
	public void imprimirEmpleados(String rutaFichero, boolean append) {
		FileWriter fichero = null;
		PrintWriter escritor = null;
		ArrayList<Departamento> departamentos = modelo.departamentos;
		ArrayList<Empleado> empleados = modelo.empleados;
		ArrayList<Cargo> cargos = modelo.cargos;
		
		try {
			fichero = new FileWriter(rutaFichero, append);
			escritor = new PrintWriter(fichero);
			
			escritor.println();
			escritor.println("EMPLEADOS");
			escritor.println("======================================================================");
			escritor.println();
			
			for(int i = 0; i < empleados.size(); i++) {
				escritor.println("Nombre: " + empleados.get(i).getNombre());
				escritor.println("Apellidos: " + empleados.get(i).getApellidos());
				escritor.println("Código: " + empleados.get(i).getCodEmpleado());
				escritor.println("Sueldo: " + empleados.get(i).getSueldo() + "€");
				for(int j = 0; j < departamentos.size(); j++) {
					if(empleados.get(i).getCodDepartamento() == departamentos.get(j).getCodDepartamento())
						escritor.println("Departamento: " + departamentos.get(j).getNombre());
				}
				
				if (empleados.get(i).getCodJefe() == 0 ) {
					escritor.println("Jefe: No tiene jefe");
				} else {
					for(int j = 0; j < empleados.size(); j++) {
					if(empleados.get(i).getCodJefe() == empleados.get(j).getCodEmpleado())
						escritor.println("Jefe: " + empleados.get(j).getNombre() + " " + empleados.get(j).getApellidos());
					}			
				}
				for(int j = 0; j < cargos.size(); j++) {
					if(empleados.get(i).getCodCargo() == cargos.get(j).getCodCargo())
						escritor.println("Cargo: " + cargos.get(j).getNombre());
				}
				if(empleados.get(i).getEsJefe())
					escritor.println("Es jefe: Si");
				else
					escritor.println("Es jefe: No");
			    
				escritor.println("Fecha de alta: " + empleados.get(i).getFechaAlta().toLocalDate() + " " + empleados.get(i).getFechaAlta().toLocalTime());
				
				escritor.println();
				if (i < empleados.size() - 1) {
					escritor.println("----------------------------------------------------------------------------");
					escritor.println();
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			crearLog(e.toString(), new Object() {} .getClass().getEnclosingMethod().getName(), new Object() {} .getClass().getName());
		}
		finally {
			try {
				fichero.close();
			}
			catch(Exception e) {
				e.printStackTrace();
				crearLog(e.toString(), new Object() {} .getClass().getEnclosingMethod().getName(), new Object() {} .getClass().getName());
			}
		}
	}
	
	public void imprimirDepartamentos(String rutaFichero, boolean append) {
		FileWriter fichero = null;
		PrintWriter escritor = null;
		ArrayList<Departamento> departamentos = modelo.departamentos;
		
		try {
			fichero = new FileWriter(rutaFichero, append);
			escritor = new PrintWriter(fichero);
			
			escritor.println();
			escritor.println("DEPARTAMENTOS");
			escritor.println("======================================================================");
			escritor.println();
			
			for(int i = 0; i < departamentos.size(); i++) {
				escritor.println("Nombre del departamento: " + departamentos.get(i).getNombre());
				escritor.println("Localización del departamento: " + departamentos.get(i).getLocalizacion());
				escritor.println("Código del departamento: " + departamentos.get(i).getCodDepartamento());
				
				escritor.println();
				if (i < departamentos.size() - 1) {
					escritor.println("----------------------------------------------------------------------------");
					escritor.println();
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			crearLog(e.toString(), new Object() {} .getClass().getEnclosingMethod().getName(), new Object() {} .getClass().getName());
		}
		finally {
			try {
				fichero.close();
			}
			catch(Exception e) {
				e.printStackTrace();
				crearLog(e.toString(), new Object() {} .getClass().getEnclosingMethod().getName(), new Object() {} .getClass().getName());
			}
		}
	}
}