package modelo;

import java.io.*;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EscritorFicheros {
	
	public void crearLog(Date fecha, String motivo) {
		String ruta = "ficheros" + File.separator + "log.txt";
		FileWriter fichero = null;
		PrintWriter escritor = null;
		try {
			File log = new File(ruta);
			if(!log.exists())
				JOptionPane.showMessageDialog(new JFrame(), "Fichero log.txt no encontrado.", null, 0);
			fichero = new FileWriter(ruta, true);
			escritor = new PrintWriter(fichero);
			
			escritor.println();
			escritor.println("===================================================================================================================");
			escritor.println("Fecha: " + fecha + " - Motivo del error: " + motivo);
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally {
			try {
				fichero.close();
			}
			catch(Exception e) {
				System.out.println(e);	
			}
		}
	}
}