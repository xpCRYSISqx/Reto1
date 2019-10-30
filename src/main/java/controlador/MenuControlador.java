package controlador;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MenuControlador {

    @FXML
    private AnchorPane cabecera;

    @FXML
    private Text logo;

    @FXML
    private AnchorPane sidebar;

    @FXML
    private JFXButton btnEmpleados;

    @FXML
    private JFXButton btnDepart;

    @FXML
    private JFXButton btnInformes;

    @FXML
    private AnchorPane contenido;

    @FXML
    void mostrarEmpleados(ActionEvent event) {
		contenido.getChildren().clear();
		try {
			contenido.getChildren().add(FXMLLoader.load(getClass().getResource("/vista/empleados.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void mostrarDepartamentos(ActionEvent event) {
		contenido.getChildren().clear();
		try {
			contenido.getChildren().add(FXMLLoader.load(getClass().getResource("/vista/departamentos.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void mostrarInformes(ActionEvent event) {

    }

}
