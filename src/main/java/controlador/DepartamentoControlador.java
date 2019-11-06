package controlador;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import modelo.Departamento;
import modelo.Empleado;

public class DepartamentoControlador extends Controlador implements Initializable  {
	
	@FXML
    private AnchorPane panelDepartamento;

	@FXML
	private Text nombre;

	@FXML
	private Label codigo, localizacion;
	
	private Departamento departamento;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Platform.runLater(() -> {


		});

	}
	
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@FXML
	void anterior(ActionEvent event) {

	}

	@FXML
	void primero(ActionEvent event) {

	}

	@FXML
	void siguiente(ActionEvent event) {

	}

	@FXML
	void ultimo(ActionEvent event) {

	}

}
