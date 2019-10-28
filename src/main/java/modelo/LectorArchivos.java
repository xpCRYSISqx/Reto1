package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
		String path = "ficheros" + File.separator + nombreArchivo;
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
	
	public ArrayList<Cargo> leerCargosCSV(String nombreArchivo) {
		CSVReader reader = null;
		int vuelta = 0;
		ArrayList<Cargo> cargos = new ArrayList();
		String path = "ficheros" + File.separator + nombreArchivo;
		try {
			reader = new CSVReader(new FileReader(path));
			String[] nextLine=null;
			while ((nextLine = reader.readNext()) != null) {
				Cargo cargo = new Cargo();
				if(vuelta != 0) {
					cargo.setCodCargo(Integer.parseInt(nextLine[0]));
					cargo.setNombre(nextLine[1]);
				}
				vuelta++;
				cargos.add(cargo);
			}
		} catch (Exception e) {
			//Excepción que corresponda
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		}
		return cargos;
	}
	
	public ArrayList<Departamento> leerDepartamentoXML(String nombreArchivo) {	
		String filePath = "ficheros" + File.separator + nombreArchivo;
        File xmlFile = new File(filePath);
        ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("departamento");
            for (int i = 0; i < nodeList.getLength(); i++) {
        		Node nNode = nodeList.item(i);
        		Departamento departamento = new Departamento();
        		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
        			Element eElement = (Element) nNode;
        			departamento.setCodDepartamento(Integer.parseInt(eElement.getElementsByTagName("cod_depart").item(0).getTextContent()));
        			departamento.setNombre(eElement.getElementsByTagName("nombre").item(0).getTextContent());
        			departamento.setLocalizacion(eElement.getElementsByTagName("localizacion").item(0).getTextContent());
        		}
        		departamentos.add(departamento);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departamentos;
	}
	
	public ArrayList<Empleado> leerEmpleadoXML(String nombreArchivo) {	
		String filePath = "ficheros" + File.separator + nombreArchivo;
        File xmlFile = new File(filePath);
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("empleado");
            for (int i = 0; i < nodeList.getLength(); i++) {
        		Node nNode = nodeList.item(i);
        		Empleado empleado = new Empleado();
        		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
        			
        			Element eElement = (Element) nNode;
        			
        			empleado.setNombre(eElement.getElementsByTagName("nombre").item(0).getTextContent());
        			empleado.setApellidos(eElement.getElementsByTagName("apellidos").item(0).getTextContent());
        			empleado.setSueldo(Integer.parseInt(eElement.getElementsByTagName("sueldo").item(0).getTextContent()));
        			empleado.setCodDepartamento(Integer.parseInt(eElement.getElementsByTagName("cod_depart").item(0).getTextContent()));
        			empleado.setCodCargo(Integer.parseInt(eElement.getElementsByTagName("cod_cargo").item(0).getTextContent()));
        			empleado.setCodJefe(Integer.parseInt(eElement.getElementsByTagName("cod_jefe").item(0).getTextContent()));
        			empleado.setEsJefe(Boolean.parseBoolean(eElement.getElementsByTagName("es_jefe").item(0).getTextContent()));
        		}
        		empleados.add(empleado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleados;
	}
}