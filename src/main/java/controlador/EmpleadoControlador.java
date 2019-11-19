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

public class EmpleadoControlador extends Controlador implements Initializable {
	
	@FXML
    private AnchorPane panelEmpleado;

	@FXML
	private Text nombreApellidos, jefeTextField;

	@FXML
	private Label codigo, departamento, cargo, sueldo, jefe, fecha;
	
	private Empleado empleado;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Platform.runLater(() -> {
			int posicion = buscarEmpleadoArray(empleado);
			if (posicion != -1) {
				mostrarDatosEmple(posicion);
			} else {
				mostrarMensaje(panelEmpleado, "Empleado no encontrado");
			}
		});

	}
	
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	@FXML
	void anterior(ActionEvent event) {
		int posicion = buscarEmpleadoArray(empleado);
		if (posicion != -1 && posicion > 0) {
			posicion--;
			this.empleado = modelo.empleados.get(posicion);
			mostrarDatosEmple(posicion);
		} else {
			mostrarMensaje(panelEmpleado, "Este es el primer empleado");
		}
	}

	@FXML
	void primero(ActionEvent event) {
		this.empleado = modelo.empleados.get(0);
		mostrarDatosEmple(0);
	}

	@FXML
	void siguiente(ActionEvent event) {
		int posicion = buscarEmpleadoArray(empleado);
		if (posicion != -1 && posicion < modelo.empleados.size() - 1) {
			posicion++;
			this.empleado = modelo.empleados.get(posicion);
			mostrarDatosEmple(posicion);
		} else {
			mostrarMensaje(panelEmpleado, "Este es el ultimo empleado");
		}
	}

	@FXML
	void ultimo(ActionEvent event) {
		this.empleado = modelo.empleados.get(modelo.empleados.size() - 1);
		mostrarDatosEmple(modelo.empleados.size() - 1);
	}
	
	public int buscarEmpleadoArray(Empleado empleado) {
		for(int i = 0; i < modelo.empleados.size(); i++) {
			if (modelo.empleados.get(i).getCodEmpleado() == empleado.getCodEmpleado()) {
				return i;
			}
		}
		return -1;
	}
	
	public void mostrarDatosEmple(int posicion) {
		Departamento departamento = null;
		Cargo cargo = null;
		Empleado jefe = null;
		
		this.empleado = modelo.empleados.get(posicion);
		
		for(int j = 0; j < modelo.departamentos.size(); j++) {
			if(empleado.getCodDepartamento() == modelo.departamentos.get(j).getCodDepartamento()) {
				departamento = modelo.departamentos.get(j);
			}
		}
		
		for(int j = 0; j < modelo.cargos.size(); j++) {
			if(empleado.getCodCargo() == modelo.cargos.get(j).getCodCargo()) {
				cargo = modelo.cargos.get(j);
			}
		}
		
		for(int j = 0; j < modelo.jefes.size(); j++) {
			if (empleado.getCodJefe() == 0) {
				this.jefeTextField.setVisible(false);
				this.jefe.setVisible(false);
			} else if(empleado.getCodJefe() == modelo.jefes.get(j).getCodEmpleado()) {
				this.jefeTextField.setVisible(true);
				this.jefe.setVisible(true);
				jefe = modelo.jefes.get(j);
			}
		}
		
		this.nombreApellidos.setText(empleado.getNombre() + " " + empleado.getApellidos());
		this.codigo.setText(String.valueOf(empleado.getCodEmpleado()));
		this.departamento.setText(departamento.getNombre());
		this.cargo.setText(cargo.getNombre());
		this.sueldo.setText(String.valueOf(empleado.getSueldo()) + "�");
		if (empleado.getCodJefe() != 0) {
			this.jefe.setText(jefe.getNombre() + " " + jefe.getApellidos());
		}
		this.fecha.setText(String.valueOf(empleado.getFechaAlta()));
	}

}
