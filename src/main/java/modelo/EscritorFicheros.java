package modelo;

import java.io.*;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EscritorFicheros {
	
	/**
	 * 
	 * Metodo utilizado para generar logs de excepciones del sistema
	 * 
	 * @param fecha Fecha en la que ha ocurrido el error
	 * @param motivo Motivo por el cual a ocurrido el error
	 */
	public void crearLog(Date fecha, String motivo) {
		String ruta = "ficheros" + File.separator + "log.txt"; //Guarda la ruta del fichero
		FileWriter fichero = null;
		PrintWriter escritor = null;
		try {
			File log = new File(ruta); //Busca si existe el fichero en la ruta especificada
			if(!log.exists())
				JOptionPane.showMessageDialog(new JFrame(), "Fichero log.txt no encontrado.", null, 0);
			fichero = new FileWriter(ruta, true); //Invoca FileWriter para la ruta especificada, con el true le indicamos que no borre el contedino del fichero, simplemente escrivira a continuacion del contenido actual del fichero
			escritor = new PrintWriter(fichero); //Invoca PrintWriter en el fichero que le especificamos y de la manera que le hemos indicado con FileWriter
			
			//Se escribe en el archivo
			escritor.println();
			escritor.println("===================================================================================================================");
			escritor.println("Fecha: " + fecha + " - Motivo del error: " + motivo);
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
}