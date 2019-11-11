package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import modelo.Departamento;

public class nuevoDepartControlador extends Controlador implements Initializable {

	@FXML
	private AnchorPane panelNuevoDepartamento;
	
    @FXML
    private TextField codigoDepartText;

    @FXML
    private TextField nombreDepartText;

    @FXML
    private TextField localizDepartText;

    @FXML
    private Button btnGuardar;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    }

    @FXML
    void guardar(ActionEvent event) {
    	if(validarDatos()) {
    		int cod = Integer.parseInt(this.codigoDepartText.getText());
    		String nombre = this.nombreDepartText.getText();
    		String loc = this.localizDepartText.getText();
    		if(modelo.comprobador.comprobarDepartamentoCodigo(cod)) {
    			this.mostrarMensaje2(panelNuevoDepartamento, "Ya existe un departemento con ese codigo", 400);
    		}
    		else if(modelo.comprobador.comprobarDepartamentoNombre(nombre, loc)){
    			this.mostrarMensaje2(panelNuevoDepartamento, "Ya existe un departemento con ese nombre en esa localización", 400);
    		}
    		else {
    			guardarDepartamento();
    			mostrarMensaje(panelNuevoDepartamento, "Nuevo departamento creado");
    			modelo.actualizarDepartamentos();
    			resetearFormulario();
    		}
    	}
    }
    
    public boolean validarDatos() {
    	if(this.codigoDepartText.getText().equals("")) {
    		this.mostrarMensaje2(panelNuevoDepartamento, "El campo 'codigo' no puede estar vacio", 400);
    		return false;
    	}
    	
    	if(this.nombreDepartText.getText().equals("")) {
    		this.mostrarMensaje2(panelNuevoDepartamento, "El campo 'nombre' no puede estar vacio", 400);
    		return false;
    	}
    	
    	if(this.localizDepartText.getText().equals("")) {
    		this.mostrarMensaje2(panelNuevoDepartamento, "El campo 'localización' no puede estar vacio", 400);
    		return false;
    	}
    	return true;
    }
    
    public void guardarDepartamento() {
    	Departamento departamento = new Departamento();
    	
    	departamento.setCodDepartamento(Integer.parseInt(this.codigoDepartText.getText()));
    	departamento.setNombre(this.nombreDepartText.getText());
    	departamento.setLocalizacion(this.localizDepartText.getText());
    	
    	modelo.escritorBBDD.insertarDepartamento(departamento);
    }
    
    public void resetearFormulario() {
    	this.codigoDepartText.setText("");
    	this.nombreDepartText.setText("");
    	this.localizDepartText.setText("");
    }
}