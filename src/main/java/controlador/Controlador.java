package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.Cargo;
import modelo.Departamento;
import modelo.Empleado;
import modelo.Modelo;

public class Controlador {
	
	public Modelo modelo;
	public Stage stage;
	
	public Controlador(Modelo modelo, Stage stage) {
		this.modelo = modelo;
		this.stage = stage;
		cargarArchivos();
		centrarStage();
		cambiarScene("Menu.fxml");
	}
	
	/**
	 * Centra el stage (frame principal) en la pantalla
	 */
	public void centrarStage() {
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
	}
	
	/**
	 * Cambia de escena (vista)
	 * @param FXMLLink
	 */
	public void cambiarScene(String FXMLLink) {
		Parent FXML = loadFXML(FXMLLink);
		if (FXML != null) {			
			Scene scene = new Scene(FXML);
			stage.setTitle("Aplicacion");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		}
	}
	
	/**
	 * Carga un archivo FXML
	 * @param FXMLLink
	 * @return
	 */
	public Parent loadFXML(String FXMLLink) {
		Parent FXML = null;
		try {
			FXML = FXMLLoader.load(getClass().getResource("/vista/" + FXMLLink));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return FXML;
	}
	
	public void cargarArchivos() {
		//Insertar departmentos leidos del fichero
		ArrayList<Departamento> departamentos = modelo.lectorArchivos.leerDepartamentoXML("departamentos.xml");
		for(int i = 0; i < departamentos.size(); i++) {
			Departamento depart = departamentos.get(i);
			ArrayList<Departamento> departamentosBD = modelo.lectorBBDD.obtenerDepartamento(depart.getNombre(), depart.getLocalizacion());
			if (departamentosBD.size() == 0) {
				modelo.escritorBBDD.insertarDepartamento(depart.getCodDepartamento(), depart.getNombre(), depart.getLocalizacion());
				System.out.println("Insertando departamento " + depart.getNombre());
			} else {
				System.out.println("El departamento " + depart.getNombre() + " ya existe en la localizacion " + depart.getLocalizacion());
			}
		}
		
		//Insertar cargos leidos del fichero
		ArrayList<Cargo> cargos = modelo.lectorArchivos.leerCargosCSV("cargos.csv");
		for(int i = 0; i < cargos.size(); i++) {
			Cargo cargo = cargos.get(i);
			ArrayList<Cargo> cargosBD = modelo.lectorBBDD.obtenerCargo(cargo.getNombre());
			if (cargosBD.size() == 0) {
				modelo.escritorBBDD.insertarCargo(cargo.getCodCargo(), cargo.getNombre());
				System.out.println("Insertando cargo " + cargo.getNombre());
			} else {
				System.out.println("El cargo " + cargo.getNombre() + " ya existe ");
			}
		}
		
		//Insertar empleados leidos del fichero
		ArrayList<Empleado> empleados = modelo.lectorArchivos.leerEmpleadoXML("empleados.xml");
		for(int i = 0; i < empleados.size(); i++) {
			Empleado empleado = empleados.get(i);
			ArrayList<Empleado> empleadosBD = modelo.lectorBBDD.obtenerEmpleado(empleado.getNombre(), empleado.getApellidos());
			if (empleadosBD.size() == 0) {
				modelo.escritorBBDD.insertarEmpleado(empleado.getCodEmpleado(), empleado.getNombre(), empleado.getApellidos(), empleado.getSueldo(), empleado.getCodDepartamento(), empleado.getCodCargo(), empleado.getCodJefe(), empleado.getEsJefe());
				System.out.println("Insertando empleado " + empleado.getNombre());
			} else {
				System.out.println("El empleado " + empleado.getNombre() + " ya existe en la base de datos");
			}
		}
	}
}