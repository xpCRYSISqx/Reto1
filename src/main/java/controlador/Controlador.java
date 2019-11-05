package controlador;

import java.io.IOException;
import java.util.Date;

import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.Modelo;

public class Controlador {
	
	protected Modelo modelo;
	protected Stage stage;
	protected JFXSnackbar snackbar;
    protected AnchorPane contenido;
	
	public Controlador() {
	}
	
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setContent(AnchorPane contenido) {
		this.contenido = contenido;
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
			//si ponemos resizable en false hay que añadir primero el setWidth y
			//setHeight para que no aparezca espacio extra a la derecha y la izquierda
			stage.setWidth(1050);
			stage.setHeight(750);
			stage.setResizable(false);
			stage.show();
			FXML.requestFocus();//poner el foco por defecto en la scena en vez de en el primer boton
			scene.getStylesheets().add("assets/css/styles.css");
		}
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
		controller.setModelo(modelo);
		return FXML;
	}
	
	public void mostrarMensaje(AnchorPane anchorpane, String mensaje) {
		Text nodo = new Text(mensaje);
		nodo.setFill(Color.WHITE);
		snackbar = new JFXSnackbar(anchorpane);
		snackbar.enqueue(new SnackbarEvent(nodo));
		snackbar.setPrefWidth(720.0);
	}
	
	public void mostrarMensaje2(AnchorPane anchorpane, String mensaje) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Advertencia");
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.setPrefWidth(550);
		dialogPane.getStylesheets().add("assets/css/styles.css");
		alert.showAndWait();
	}
}