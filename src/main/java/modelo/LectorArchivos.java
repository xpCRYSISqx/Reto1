package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LectorArchivos {
	
	public String leerArchivoTXT(String nombreArchivo) {
		String path = "ficheros" + File.separator + nombreArchivo;;
		BufferedReader buffer = null;
		String resultado = "";
		try {
			buffer = new BufferedReader(new FileReader(path));
			String linea = "";
			while ((linea = buffer.readLine()) != null) {
				resultado = resultado+linea +"\n";					
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return resultado;
	}
}