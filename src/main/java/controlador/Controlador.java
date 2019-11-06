package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import modelo.Cargo;
import modelo.Departamento;
import modelo.Empleado;
import modelo.Modelo;

public class Controlador implements Initializable {
	
	protected Modelo modelo;
	protected JFXSnackbar snackbar;
    
    @FXML
    public AnchorPane cabecera, sidebar, contenido;

    @FXML
    private Text logo;
    
    public Controlador() {
		this.modelo = Modelo.getModelo();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		mostrarEmpleados(null);
		cargarArchivos();
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
		controller.contenido = contenido;
		return FXML;
	}
	
	/**
	 * 
	 * @param anchorpane
	 * @param mensaje
	 */
	public void mostrarMensaje(AnchorPane anchorpane, String mensaje) {
		Text nodo = new Text(mensaje);
		nodo.setFill(Color.WHITE);
		snackbar = new JFXSnackbar(anchorpane);
		snackbar.enqueue(new SnackbarEvent(nodo));
		snackbar.setPrefWidth(720.0);
	}
	
	/**
	 * 
	 * @param anchorpane
	 * @param mensaje
	 * @param width
	 */
	public void mostrarMensaje2(AnchorPane anchorpane, String mensaje, int width) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Advertencia");
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.setPrefWidth(width);
		dialogPane.getStylesheets().add("assets/css/styles.css");
		alert.showAndWait();
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
			if (modelo.comprobador.comprobarDepartamentoCodigo(depart.getCodDepartamento())) {
				info += "Ya existe un departamento con código '" + depart.getCodDepartamento() + "'\n";
			} else if (modelo.comprobador.comprobarDepartamentoNombre(depart.getNombre(), depart.getLocalizacion())) {
				info += "Ya existe un departamento con nombre '" + depart.getNombre() + "' en la localizacion '" + depart.getLocalizacion() + "'\n";
			} else {
				modelo.escritorBBDD.insertarDepartamento(depart);
				info += "Insertado departamento " + depart.getNombre() + "\n";
			}
		}
		
		//Insertar cargos leidos del fichero
		ArrayList<Cargo> cargos = modelo.lectorArchivos.leerCargosCSV("cargos.csv");
		for(int i = 0; i < cargos.size(); i++) {
			Cargo cargo = cargos.get(i);
			if (modelo.comprobador.comprobarCargoCodigo(cargo.getCodCargo())) {
				info += "Ya existe un cargo con código '" + cargo.getCodCargo() + "'\n";
			} else if (modelo.comprobador.comprobarCargoNombre(cargo.getNombre())) {
				info += "Ya existe un cargo con nombre '" + cargo.getNombre() + "'\n";
			} else {
				modelo.escritorBBDD.insertarCargo(cargo);
				info += "Insertado cargo " + cargo.getNombre() + "\n";
			}
		}
		
		//Insertar empleados leidos del fichero
		ArrayList<Empleado> empleados = modelo.lectorArchivos.leerEmpleadoXML("empleados.xml");
		for(int i = 0; i < empleados.size(); i++) {
			Empleado empleado = empleados.get(i);
			if (modelo.comprobador.comprobarEmpleadoCodigo(empleado.getCodEmpleado())) {
				info += "Ya existe un empleado con el código '" + empleado.getCodEmpleado() + "' en la base de datos" + "\n";
			} else if (modelo.comprobador.comprobarEmpleadoNombre(empleado.getNombre(), empleado.getApellidos())) {
				info += "Ya existe un empleado con nombre '" + empleado.getNombre() + "' y apellidos '" + empleado.getApellidos() + "' en la base de datos" + "\n";
			} else {
				modelo.escritorBBDD.insertarEmpleado(empleado);
				info += "Insertado empleado " + empleado.getNombre() + "\n";
			}
		}
		
		mostrarMensaje2(contenido, info, 600); //anchorpane, texto, anchura
	}
}