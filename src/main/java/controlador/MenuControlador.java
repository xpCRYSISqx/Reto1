package controlador;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MenuControlador extends Controlador {

    @FXML
    private AnchorPane cabecera, sidebar, contenido;

    @FXML
    private Text logo;

    @FXML
    private JFXButton btnEmpleados, btnDepart, btnInformes;

    @FXML
    void mostrarEmpleados(ActionEvent event) {
    	// quita la vista actual del panel contenido
		contenido.getChildren().clear();
		
		// carga la vista empleados en el panel contenido
		Parent FXML = loadFXML("empleados.fxml");
		contenido.getChildren().add(FXML);
    }
    
    @FXML
    void mostrarDepartamentos(ActionEvent event) {
    	// quita la vista actual del panel contenido
		contenido.getChildren().clear();
		// carga la vista empleados en el panel contenido
		Parent FXML = loadFXML("departamentos.fxml");
		contenido.getChildren().add(FXML);
    }

    @FXML
    void mostrarInformes(ActionEvent event) {

    }

}
