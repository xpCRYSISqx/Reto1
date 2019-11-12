package aplicacion;

import java.io.IOException;
import java.util.Date;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.Modelo;

public class Launcher extends Application {
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.version"));
		System.out.println(System.getProperty("javafx.version"));
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		// cargar vista principal
		Parent FXML = null;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/menu.fxml"));
		try {
			FXML = fxmlLoader.load();
		} catch (IOException e) {
			Modelo.getModelo().escritorFicheros.crearLog(new Date(), e.toString());
		}
		
		// configurar vista principal
		configurarStage(primaryStage, FXML);
	}
	
	/**
	 * Configura la vista principal
	 * @param FXMLLink
	 */
	public void configurarStage(Stage vista, Parent FXML) {
		if (FXML != null) {		
			
			// añadir scene a stage
			Scene scene = new Scene(FXML);
			vista.setScene(scene);
			
			// titulo de la aplicacion
			vista.setTitle("Aplicacion");
			
			// configurar tamaño
			// si ponemos resizable en false hay que añadir primero el setWidth y
			// setHeight para que no aparezca espacio extra a la derecha y la izquierda
			vista.setWidth(1050);
			vista.setHeight(750);
			vista.setResizable(false);
			
			// centrar en la pantalla
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
			vista.setX((primScreenBounds.getWidth() - vista.getWidth()) / 2);
			vista.setY((primScreenBounds.getHeight() - vista.getHeight()) / 2);
			
			//poner el foco por defecto en la scena en vez de en el primer boton
			FXML.requestFocus();
			
			// añadir hoja de estilos
			scene.getStylesheets().add("assets/css/styles.css");
			
			// mostrar
			vista.show();
		}
	}

}