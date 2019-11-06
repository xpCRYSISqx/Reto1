package aplicacion;

import controlador.Controlador;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		Controlador controlador = new Controlador();
		controlador.setStage(primaryStage);
		controlador.centrarStage();
		controlador.cambiarScene("Menu.fxml");
	}
}