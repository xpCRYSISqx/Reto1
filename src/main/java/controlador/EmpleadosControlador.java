package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.effects.JFXDepthManager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import modelo.Cargo;
import modelo.Departamento;
import modelo.Empleado;
import vista.CardEmpleado;

public class EmpleadosControlador extends Controlador implements Initializable {

    @FXML
    private Button btnNuevo;
    
    @FXML
    private AnchorPane contenedor;
    
    @FXML
    private TextField textoBusqueda;

    @FXML
    void nuevo(ActionEvent event) {
    	Parent FXML = loadFXML("nuevoEmpleado.fxml");
    	contenido.getChildren().clear();
    	contenido.getChildren().add(FXML);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.runLater(() -> {

			GridPane grid = crearGrid();
	        int i = 0;
	    	for(i = 0; i < modelo.empleados.size(); i++) {
		    	Empleado empleado = modelo.empleados.get(i);
		    	Departamento departamento = null;
		    	Cargo cargo = null;
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
		    		
		    	// crea la tarjeta con la informacion del empleado
		    	CardEmpleado card = new CardEmpleado(empleado, departamento, cargo, (float)(i)/16 + 1F, contenido, modelo);	    		
		    	JFXDepthManager.setDepth(card, 1);
		    		
		        // añade la tarjeta al grid
				grid.add(card, 0, i); 
	    	}
		});
	}
	
	/**
     * Crea y añade el grid al anchorpane 'contenedor', creado por defecto
     * @return
     */
    public GridPane crearGrid() {
    	GridPane grid = new GridPane();
    	ColumnConstraints column1 = new ColumnConstraints();
    	column1.setPercentWidth(100);
    	grid.getColumnConstraints().add(column1);
    	grid.setStyle("-fx-background-color: transparent;");
    	contenedor.getChildren().setAll(grid);
    	return grid;
    }
    
    public void busqueda() {
    	Platform.runLater(() -> {

			GridPane grid = crearGrid();
	        int i = 0;
	    	for(i = 0; i < modelo.empleados.size(); i++) {
	    		if(textoBusqueda.getText().equals("") || textoBusqueda.getText().toLowerCase().equals(modelo.empleados.get(i).getNombre().toLowerCase().substring(0, textoBusqueda.getText().length()))) {
			    	Empleado empleado = modelo.empleados.get(i);
			    	Departamento departamento = null;
			    	Cargo cargo = null;
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
			    		
			    	// crea la tarjeta con la informacion del empleado
			    	CardEmpleado card = new CardEmpleado(empleado, departamento, cargo, (float)(i)/16 + 1F, contenido, modelo);	    		
			    	JFXDepthManager.setDepth(card, 1);
			    		
			        // añade la tarjeta al grid
					grid.add(card, 0, i); 
		    	}
	    	}
		});
    }
}