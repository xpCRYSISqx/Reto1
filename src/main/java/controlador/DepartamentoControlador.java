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
import modelo.Cargo;
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
			int posicion = buscarDepartamentoArray(departamento);
			if (posicion != -1) {
				mostrarDatosDepart(posicion);
			} else {
				mostrarMensaje(panelDepartamento, "Departamento no encontrado");
			}

		});

	}
	
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@FXML
	void anterior(ActionEvent event) {
		int posicion = buscarDepartamentoArray(departamento);
		if (posicion != -1 && posicion > 0) {
			posicion--;
			this.departamento = modelo.departamentos.get(posicion);
			mostrarDatosDepart(posicion);
		} else {
			mostrarMensaje(panelDepartamento, "Este es el primer departamento");
		}
	}

	@FXML
	void primero(ActionEvent event) {
		this.departamento = modelo.departamentos.get(0);
		mostrarDatosDepart(0);
	}

	@FXML
	void siguiente(ActionEvent event) {
		int posicion = buscarDepartamentoArray(departamento);
		if (posicion != -1 && posicion < modelo.departamentos.size() - 1) {
			posicion++;
			this.departamento = modelo.departamentos.get(posicion);
			mostrarDatosDepart(posicion);
		} else {
			mostrarMensaje(panelDepartamento, "Este es el ultimo departamento");
		}
	}

	@FXML
	void ultimo(ActionEvent event) {
		this.departamento = modelo.departamentos.get(modelo.departamentos.size() - 1);
		mostrarDatosDepart(modelo.empleados.size() - 1);
	}
	
	public int buscarDepartamentoArray(Departamento departamento) {
		for(int i = 0; i < modelo.departamentos.size(); i++) {
			if (modelo.departamentos.get(i).getCodDepartamento() == departamento.getCodDepartamento()) {
				return i;
			}
		}
		return -1;
	}
	
	public void mostrarDatosDepart(int posicion) {
		
		this.nombre.setText(departamento.getNombre());
		this.codigo.setText(String.valueOf(departamento.getCodDepartamento()));
		this.localizacion.setText(departamento.getLocalizacion());
	}

}
