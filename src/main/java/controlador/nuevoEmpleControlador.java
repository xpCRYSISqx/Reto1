package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import modelo.*;

public class nuevoEmpleControlador extends Controlador implements Initializable {

	Empleado empleado = new Empleado();
	
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

    @FXML
    private Button btnGuardar;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	Platform.runLater(() -> {
    		// inicializa el combobox departamentos
	    	ObservableList<Departamento> listaDepart = FXCollections.observableArrayList(modelo.lectorBBDD.obtenerTodosLosDepartamento());
	    	departEmpleComboBox.setItems(listaDepart);
	    	departEmpleComboBox.getSelectionModel().selectFirst();
	    	
	    	// inicializa el combobox cargos
	    	ObservableList<Cargo> listaCargos = FXCollections.observableArrayList(modelo.lectorBBDD.obtenerTodosLosCargos());
	    	cargoEmpleComboBox.setItems(listaCargos);
	    	cargoEmpleComboBox.getSelectionModel().selectFirst();
	    	
	    	// inicializa el combobox jefes
	    	ObservableList<Empleado> listaJefes = FXCollections.observableArrayList(modelo.lectorBBDD.obtenerJefes());
	    	jefeEmpleComboBox.setItems(listaJefes);
	    	jefeEmpleComboBox.getSelectionModel().selectFirst();
    	});
    }
    
    @FXML
    void guardar(ActionEvent event) {
    	 if(validarDatos()) {
    		 int codEmple = Integer.parseInt(codigoEmpleText.getText());
	    	 if(this.modelo.lectorBBDD.obtenerEmpleadoPorCodigo(codEmple).size() != 0) {
	    		 this.mostrarMensaje(panelNuevoEmpleado, "Ya existe un empleado con ese codigo");
	    	 } else {
	    		 empleado.setCodEmpleado(codEmple);
	    		 empleado.setNombre(nombreEmpleText.getText());
	    		 empleado.setApellidos(apellidosEmpleText.getText());
	    		 empleado.setSueldo(Integer.parseInt(sueldoEmpleText.getText()));
	    		 empleado.setCodDepartamento(departEmpleComboBox.getSelectionModel().getSelectedItem().getCodDepartamento());
	    		 empleado.setCodCargo(cargoEmpleComboBox.getSelectionModel().getSelectedItem().getCodCargo());
	    		 empleado.setCodJefe(jefeEmpleComboBox.getSelectionModel().getSelectedItem().getCodEmpleado());
	    		 if(siEmpleRadioButton.isSelected()) {
					empleado.setEsJefe(true);
	    		 } else if(noEmpleRadioButton.isSelected()) {
					empleado.setEsJefe(false);
	    		 }
	    		 this.modelo.escritorBBDD.insertarEmpleado(empleado);
	    	 }
    	 }
    }
    
    boolean validarDatos() {
    	Comprobadores comprobar = new Comprobadores();
    	
    	// validar codigo
    	if(codigoEmpleText.getText().equals("")) {
    		this.mostrarMensaje2(panelNuevoEmpleado, "El campo 'codigo' no puede estar vacio");
    		return false;
    	} else if(!comprobar.comprobarNumerico(codigoEmpleText.getText())) {
    		this.mostrarMensaje2(panelNuevoEmpleado, "El campo 'codigo' debe ser un valor numérico");
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
    	} else if(!comprobar.comprobarNumerico(sueldoEmpleText.getText())) {
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
}