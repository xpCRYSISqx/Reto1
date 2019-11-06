package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import modelo.*;

public class nuevoEmpleControlador extends Controlador implements Initializable {

	@FXML
	private AnchorPane panelNuevoEmpleado;

	@FXML
	private TextField codigoEmpleText, nombreEmpleText, apellidosEmpleText, sueldoEmpleText;

	@FXML
	private ComboBox<Departamento> departEmpleComboBox;

	@FXML
	private ComboBox<Cargo> cargoEmpleComboBox;

	@FXML
	private ComboBox<Empleado> jefeEmpleComboBox;

	@FXML
	private RadioButton siEmpleRadioButton, noEmpleRadioButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(() -> {
			// inicializa el combobox departamentos
			ObservableList<Departamento> listaDepart = FXCollections.observableArrayList(modelo.departamentos);
			departEmpleComboBox.setItems(listaDepart);
			departEmpleComboBox.getSelectionModel().selectFirst();

			// inicializa el combobox cargos
			ObservableList<Cargo> listaCargos = FXCollections.observableArrayList(modelo.cargos);
			cargoEmpleComboBox.setItems(listaCargos);
			cargoEmpleComboBox.getSelectionModel().selectFirst();

			// inicializa el combobox jefes
			ObservableList<Empleado> listaJefes = FXCollections.observableArrayList(modelo.jefes);
			jefeEmpleComboBox.setItems(listaJefes);
			jefeEmpleComboBox.getSelectionModel().selectFirst();
		});
	}

	@FXML
	void guardar(ActionEvent event) {
		if(validarDatos()) {
			int codEmple = Integer.parseInt(codigoEmpleText.getText());
			String nombre = nombreEmpleText.getText();
			String apellidos = apellidosEmpleText.getText();
			if(modelo.comprobador.comprobarEmpleadoCodigo(codEmple)) {
				this.mostrarMensaje(panelNuevoEmpleado, "Ya existe un empleado con ese codigo");
			} else if(modelo.comprobador.comprobarEmpleadoNombre(nombre, apellidos)) {
				this.mostrarMensaje(panelNuevoEmpleado, "Ya existe un empleado con el mismo nombre y apellidos");
			} else {
				guardarEmpleado();
				mostrarMensaje(panelNuevoEmpleado, "Nuevo empleado creado");
				modelo.actualizarEmpleados();
				resetearFormulario();
			}
		}
	}

	boolean validarDatos() {

		// validar codigo
		if(codigoEmpleText.getText().equals("")) {
			this.mostrarMensaje2(panelNuevoEmpleado, "El campo 'codigo' no puede estar vacio", 400);
			return false;
		} else if(!modelo.comprobador.comprobarNumerico(codigoEmpleText.getText())) {
			this.mostrarMensaje2(panelNuevoEmpleado, "El campo 'codigo' debe ser un valor numérico", 400);
			return false;
		}

		// validar nombre
		if(nombreEmpleText.getText().equals("")) {
			this.mostrarMensaje(panelNuevoEmpleado, "El campo 'nombre' no puede estar vacío");
			return false;
		}

		// validar apellidos
		if(apellidosEmpleText.getText().equals("")) {
			this.mostrarMensaje(panelNuevoEmpleado, "El campo 'apellidos' no puede estar vacío");
			return false;
		}

		// validar sueldo
		if(sueldoEmpleText.getText().equals("")) {
			this.mostrarMensaje(panelNuevoEmpleado, "El campo 'sueldo' no puede estar vacio");
			return false;
		} else if(!modelo.comprobador.comprobarNumerico(sueldoEmpleText.getText())) {
			this.mostrarMensaje(panelNuevoEmpleado, "El campo 'sueldo' debe ser un valor numérico");
			return false;
		}

		// validar esJefe
		if(!siEmpleRadioButton.isSelected() && !noEmpleRadioButton.isSelected()) {
			this.mostrarMensaje(panelNuevoEmpleado, "Debe seleccionar si el empleado es jefe o no");
			return false;
		}

		// si todo es valido devuelve true
		return true;
	}

	public void guardarEmpleado() {
		Empleado empleado = new Empleado();
		empleado.setCodEmpleado(Integer.parseInt(codigoEmpleText.getText()));
		empleado.setNombre(nombreEmpleText.getText());
		empleado.setApellidos(apellidosEmpleText.getText());
		empleado.setSueldo(Integer.parseInt(sueldoEmpleText.getText()));
		empleado.setCodDepartamento(departEmpleComboBox.getSelectionModel().getSelectedItem().getCodDepartamento());
		empleado.setCodCargo(cargoEmpleComboBox.getSelectionModel().getSelectedItem().getCodCargo());
		empleado.setCodJefe(jefeEmpleComboBox.getSelectionModel().getSelectedItem().getCodEmpleado());
		if(siEmpleRadioButton.isSelected()) {
			empleado.setEsJefe(true);
		} else {
			empleado.setEsJefe(false);
		}
		this.modelo.escritorBBDD.insertarEmpleado(empleado);
	}

	public void resetearFormulario() {
		codigoEmpleText.setText("");
		nombreEmpleText.setText("");
		apellidosEmpleText.setText("");
		sueldoEmpleText.setText("");
		departEmpleComboBox.getSelectionModel().selectFirst();
		cargoEmpleComboBox.getSelectionModel().selectFirst();
		jefeEmpleComboBox.getSelectionModel().selectFirst();
		siEmpleRadioButton.setSelected(false);
		noEmpleRadioButton.setSelected(false);
	}
}