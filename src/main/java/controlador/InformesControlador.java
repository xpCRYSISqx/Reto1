package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class InformesControlador extends Controlador implements Initializable {

    @FXML
    private AnchorPane panelInformes;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    }

    @FXML
    void imprimirDepartamentos(ActionEvent event) {
    	String pathReserva = modelo.escritorFicheros.preguntarGuadar("departamentos");
    	if (pathReserva != null) {
    		modelo.escritorFicheros.imprimirDepartamentos(pathReserva, false);
    	}
    }

    @FXML
    void imprimirEmpleados(ActionEvent event) {
    	String pathReserva = modelo.escritorFicheros.preguntarGuadar("empleados");
    	if (pathReserva != null) {
    		modelo.escritorFicheros.imprimirEmpleados(pathReserva, false);
    	}
    }

    @FXML
    void imprimirTodo(ActionEvent event) {
    	String pathReserva = modelo.escritorFicheros.preguntarGuadar("departamentos-empleados");
    	if (pathReserva != null) {
    		modelo.escritorFicheros.imprimirTodo(pathReserva);
    	}
    }

}