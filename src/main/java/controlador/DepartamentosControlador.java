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
import vista.CardDepartamento;
import vista.CardEmpleado;

public class DepartamentosControlador extends Controlador implements Initializable  {

    @FXML
    private Button btnNuevo;
    
    @FXML
    private AnchorPane contenedor;
    
    @FXML
    private TextField textoBusquedaD;

    @FXML
    void nuevo(ActionEvent event) {
    	Parent FXML = loadFXML("nuevoDepartamento.fxml");
		contenido.getChildren().clear();
		contenido.getChildren().add(FXML);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Platform.runLater(() -> {

			GridPane grid = crearGrid();
	        int i = 0;
	    	for(i = 0; i < modelo.departamentos.size(); i++) {
	    		
	    		Departamento depart = modelo.departamentos.get(i);
	    		
	    		// crea la tarjeta con la informacion del alojamiento
	    		CardDepartamento card = new CardDepartamento(depart, (float)(i)/16 + 1F, contenido, modelo);
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
    
    public void busquedaD() {
    	Platform.runLater(() -> {

			GridPane grid = crearGrid();
	        int i = 0;
	    	for(i = 0; i < modelo.empleados.size(); i++) {
	    		if(textoBusquedaD.getText().equals("") || textoBusquedaD.getText().toLowerCase().equals(modelo.departamentos.get(i).getNombre().toLowerCase().substring(0, textoBusquedaD.getText().length()))) {
	    			Departamento depart = modelo.departamentos.get(i);
		    		
		    		// crea la tarjeta con la informacion del alojamiento
		    		CardDepartamento card = new CardDepartamento(depart, (float)(i)/16 + 1F, contenido, modelo);
		    		JFXDepthManager.setDepth(card, 1);
		    		
		        	// añade la tarjeta al grid
					grid.add(card, 0, i); 
		    	}
	    	}
		});
    }
}
