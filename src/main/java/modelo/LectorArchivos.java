package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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
	
	EscritorFicheros escritorFicheros = new EscritorFicheros();
	
	public String[] leerDatosConexion(String nombreArchivo) {
		String nombreFichero = "ficheros" + File.separator + nombreArchivo;
		String[] datos = new String[4];
		String linea, clave, dato = "";
		FileReader fileReader = null;
		BufferedReader buffer = null;
		try {
			fileReader = new FileReader(nombreFichero);
			buffer = new BufferedReader(fileReader);
			while ((linea = buffer.readLine()) != null) {
				clave = linea.substring(0, linea.indexOf("="));
				dato = linea.substring(linea.indexOf("=") + 1);
				switch (clave) {
					case "url": datos[0] = dato; break;
					case "database": datos[1] = dato; break;
					case "user": datos[2] = dato; break;
					case "password": datos[3] = dato; break;
				}
			}
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos",JOptionPane.ERROR_MESSAGE);
			escritorFicheros.crearLog(new Date(), e.toString());
			System.exit(0);
		} finally {
			try {
				fileReader.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				escritorFicheros.crearLog(new Date(), e2.toString());
			}
		}
		return datos;
	}
	
	public ArrayList<Cargo> leerCargosCSV(String nombreArchivo) {
		CSVReader reader = null;
		int vuelta = 0;
		ArrayList<Cargo> cargos = new ArrayList<Cargo>();
		String path = "ficheros" + File.separator + nombreArchivo;
		String[] nextLine = null;
		try {
			reader = new CSVReader(new FileReader(path));
			while ((nextLine = reader.readNext()) != null) {
				//La vuelta 0 es cuando lee los encabezados, por eso nos la saltamos
				if(vuelta != 0) {
					Cargo cargo = new Cargo();
					cargo.setCodCargo(Integer.parseInt(nextLine[0]));
					cargo.setNombre(nextLine[1]);
					cargos.add(cargo);
				}
				vuelta++;
			}
		} catch (Exception e) {
			escritorFicheros.crearLog(new Date(), e.toString());
			//Excepción que corresponda
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
					escritorFicheros.crearLog(new Date(), e.toString());
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
            escritorFicheros.crearLog(new Date(), e.toString());
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
        			empleado.setCodEmpleado(Integer.parseInt(eElement.getElementsByTagName("cod_empleado").item(0).getTextContent()));
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
            escritorFicheros.crearLog(new Date(), e.toString());
        }
        return empleados;
	}
}