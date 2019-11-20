package controlador;

import java.io.IOException;
import java.net.URL;
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
		String info = "";
		modelo.actualizarTodosLosDatos();
		info += modelo.cargadorDatos.cargarDepartamentos();
		info += modelo.cargadorDatos.cargarCargos();
		info += modelo.cargadorDatos.cargarEmpleados();
		mostrarMensaje2(contenido, info, 600); //anchorpane, texto, anchura
		modelo.actualizarTodosLosDatos();
		mostrarEmpleados(null);
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
    	Parent FXML = loadFXML("informes.fxml");
		contenido.getChildren().clear();
		contenido.getChildren().add(FXML);
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
			modelo.escritorFicheros.crearLog(e.toString(), new Object() {} .getClass().getEnclosingMethod().getName(), new Object() {} .getClass().getName());
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

}