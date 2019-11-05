package controlador;

import java.io.IOException;
import java.util.Date;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MenuControlador extends Controlador {

    @FXML
    public AnchorPane cabecera, sidebar, contenido;

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
		controller.setContent(contenido);
		return FXML;
	}

}
