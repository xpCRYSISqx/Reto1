package aplicacion;

import controlador.Controlador;
import javafx.application.Application;
import javafx.stage.Stage;
import modelo.Modelo;

public class Launcher extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		new Controlador(new Modelo(), primaryStage);
	}
}