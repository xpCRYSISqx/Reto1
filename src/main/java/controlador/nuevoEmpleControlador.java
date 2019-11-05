package controlador;

import java.net.URL;
import java.util.ResourceBundle;

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

public class nuevoEmpleControlador extends Controlador implements Initializable{

	Empleado empleado = new Empleado();
	
	@FXML
	private AnchorPane panelNuevoEmpleado;
	
    @FXML
    private TextField codigoEmpleText;

    @FXML
    private TextField nombreEmpleText;

    @FXML
    private TextField apellidosEmpleText;

    @FXML
    private TextField sueldoEmpleText;

    @FXML
    private ComboBox<Departamento> departEmpleComboBox;

    @FXML
    private ComboBox<Cargo> cargoEmpleComboBox;

    @FXML
    private ComboBox<Empleado> jefeEmpleComboBox;

    @FXML
    private RadioButton siEmpleRadioButton;

    @FXML
    private RadioButton noEmpleRadioButton;

    @FXML
    private Button btnGuardar;

    @FXML
    void guardar(ActionEvent event) {
    	 if(validarDatos()) {
	    	 if(this.modelo.lectorBBDD.obtenerEmpleadoPorCodigo(empleado.getCodEmpleado()) != null) {
	    		 this.mostrarMensaje(panelNuevoEmpleado, "Ya existe un empleado con ese codigo");
	    	 }
	    	 else
	    		 this.modelo.escritorBBDD.insertarEmpleado(empleado);
    	 }
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	ObservableList<Departamento> listaDepart = FXCollections.observableArrayList(this.modelo.lectorBBDD.obtenerTodosLosDepartamento());
    	departEmpleComboBox.setItems(listaDepart);
    }
    
    boolean validarDatos() {
    	Comprobadores comprobar = new Comprobadores();
    	
    	if(comprobar.comprobarNumerico(codigoEmpleText.getText()))
    		empleado.setCodEmpleado(Integer.parseInt(codigoEmpleText.getText()));
    	else {
    		this.mostrarMensaje(panelNuevoEmpleado, "El campo de codigo no puede estar vacio y tiene que ser un valor numérico");
    		return false;
    	}
   	 
    	if(nombreEmpleText.getText().equals("")) {
    		this.mostrarMensaje(panelNuevoEmpleado, "El campo de nombre no puede estar vacío");
    		return false;
    	}
    	else
    		empleado.setNombre(nombreEmpleText.getText());
   	 
    	if(apellidosEmpleText.getText().equals("")) {
    		this.mostrarMensaje(panelNuevoEmpleado, "El campo de nombre no puede estar vacío");
    		return false;
    	}
    	else
    		empleado.setApellidos(apellidosEmpleText.getText());
    	
    	if(comprobar.comprobarNumerico(sueldoEmpleText.getText()))
    		empleado.setSueldo(Integer.parseInt(sueldoEmpleText.getText()));
    	else {
    		this.mostrarMensaje(panelNuevoEmpleado, "El campo de sueldo no puede estar vacio y tiene que ser un valor numérico");
    		return false;
    	}
    	
    	if(siEmpleRadioButton.isSelected())
    		empleado.setEsJefe(true);
    	else if(noEmpleRadioButton.isSelected())
    		empleado.setEsJefe(false);
    	else {
    		this.mostrarMensaje(panelNuevoEmpleado, "Se debe seleccionar si el empleado es jefe o no");
    		return false;
    	}
    	return true;
    }
}