package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.opencsv.CSVReader;

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
	
	public String leerArchivoCSV(String nombreArchivo) {
		CSVReader reader = null;
		String resultado = "";
		String path = "biblioteca" + File.separator + nombreArchivo; //films_score.csv
		try {
			reader = new CSVReader(new FileReader(path));
			String[] nextLine=null;
			while ((nextLine = reader.readNext()) != null) {
				System.out.println(Arrays.toString(nextLine));
			}
		} catch (Exception e) {
			//Excepci�n que corresponda
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		}
		return resultado;
	}
	
	public String leerArchivoXML(String nombreArchivo) {	
		String filePath = "biblioteca" + File.separator + nombreArchivo; //books.xml
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        String root = "";
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            root = "Root element: " + doc.getDocumentElement().getNodeName() + "\n";
            root += "----------------------------\n";
            NodeList nodeList = doc.getElementsByTagName("book");
            for (int i = 0; i < nodeList.getLength(); i++) {
        		Node nNode = nodeList.item(i);
        		root += "Current Element: " + nNode.getNodeName() + "\n";
        		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

        			Element eElement = (Element) nNode;

        			root += "Title: " + eElement.getElementsByTagName("author").item(0).getTextContent() + "\n";
        			root += "Title: " + eElement.getElementsByTagName("title").item(0).getTextContent() + "\n";
        			root += "Genre: " + eElement.getElementsByTagName("genre").item(0).getTextContent() + "\n";
        			root += "Price: " + eElement.getElementsByTagName("price").item(0).getTextContent() + "\n";
        			root += "Publish Date: " + eElement.getElementsByTagName("publish_date").item(0).getTextContent() + "\n";
        			root += "Description: " + eElement.getElementsByTagName("description").item(0).getTextContent() + "\n";
        			root += "----------------------------\n";
        		}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return root;
	}
}