package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import modelo.Cargo;
import modelo.Departamento;
import modelo.Empleado;

public class MenuControlador extends Controlador implements Initializable {

    @FXML
    public AnchorPane cabecera, sidebar, contenido;

    @FXML
    private Text logo;

    @FXML
    private JFXButton btnEmpleados, btnDepart, btnInformes;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	Platform.runLater(() -> {
    		mostrarEmpleados(null);
    		cargarArchivos();
    	});		
	}

    @FXML
    void mostrarEmpleados(ActionEvent event) {
    	Parent FXML = loadFXML("empleados.fxml");
		contenido.getChildren().clear();
		contenido.getChildren().add(FXML);
    }
    
    @FXML
    void mostrarDepartamentos(ActionEvent event) {
    	Parent FXML = loadFXML("departamentos.fxml");
		contenido.getChildren().clear();
		contenido.getChildren().add(FXML);
    }

    @FXML
    void mostrarInformes(ActionEvent event) {

    }
    
    /**
	 * Carga un archivo FXML
	 * @param FXMLLink
	 * @return
	 */
	public Parent loadFXML(String FXMLLink) {
		Parent FXML = null;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/" + FXMLLink));
		try {
			FXML = fxmlLoader.load();
		} catch (IOException e) {
			modelo.escritorFicheros.crearLog(new Date(), e.toString());
		}   
		Controlador controller = fxmlLoader.<Controlador>getController();
		controller.setContentPane(contenido);
		return FXML;
	}
	
	/**
	 * Carga los datos de los archivos si la base de datos esta vacia
	 */
	public void cargarArchivos() {
		
		String info = "";
		
		//Insertar departamentos leidos del fichero
		ArrayList<Departamento> departamentos = modelo.lectorArchivos.leerDepartamentoXML("departamentos.xml");
		for(int i = 0; i < departamentos.size(); i++) {
			Departamento depart = departamentos.get(i);
			ArrayList<Departamento> departamentosBD = modelo.lectorBBDD.obtenerDepartamento(depart);
			if (departamentosBD.size() == 0) {
				modelo.escritorBBDD.insertarDepartamento(depart);
				info += "Insertado departamento " + depart.getNombre() + "\n";
			} else {
				info += "El departamento " + depart.getNombre() + " ya existe en la localizacion " + depart.getLocalizacion() + "\n";
			}
		}
		
		//Insertar cargos leidos del fichero
		ArrayList<Cargo> cargos = modelo.lectorArchivos.leerCargosCSV("cargos.csv");
		for(int i = 0; i < cargos.size(); i++) {
			Cargo cargo = cargos.get(i);
			ArrayList<Cargo> cargosBD = modelo.lectorBBDD.obtenerCargo(cargo);
			if (cargosBD.size() == 0) {
				modelo.escritorBBDD.insertarCargo(cargo);
				info += "Insertado cargo " + cargo.getNombre() + "\n";
			} else {
				info += "El cargo " + cargo.getNombre() + " ya existe " + "\n";
			}
		}
		
		//Insertar empleados leidos del fichero
		ArrayList<Empleado> empleados = modelo.lectorArchivos.leerEmpleadoXML("empleados.xml");
		for(int i = 0; i < empleados.size(); i++) {
			Empleado empleado = empleados.get(i);
			ArrayList<Empleado> empleadosBD = modelo.lectorBBDD.obtenerEmpleado(empleado);
			if (empleadosBD.size() == 0) {
				modelo.escritorBBDD.insertarEmpleado(empleado);
				info += "Insertado empleado " + empleado.getNombre() + "\n";
			} else {
				info += "El empleado " + empleado.getNombre() + " ya existe en la base de datos" + "\n";
			}
		}
		
		mostrarMensaje2(contenido, info, 600); //anchorpane, texto, anchura
	}

}
