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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import modelo.Empleado;
import vista.CardEmpleado;

public class EmpleadosControlador extends Controlador implements Initializable {

    @FXML
    private Button btnNuevo;
    
    @FXML
    private AnchorPane contenedor;

    @FXML
    void nuevo(ActionEvent event) {
    	Parent FXML = loadFXML("nuevoEmpleado.fxml");
    	contenido.getChildren().clear();
    	contenido.getChildren().add(FXML);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Platform.runLater(() -> {

			modelo.empleados = modelo.lectorBBDD.obtenerTodosLosEmpleados();
			GridPane grid = crearGrid();
	        int i = 0;
	    	for(i = 0; i < modelo.empleados.size(); i++) {
	    		
	    		Empleado empleado = modelo.empleados.get(i);
	    		
	    		// crea la tarjeta con la informacion del empleado
	    		CardEmpleado card = new CardEmpleado(empleado, (float)(i)/16 + 1F, contenido, modelo);	    		
	    		JFXDepthManager.setDepth(card, 1);
	    		
	        	// a�ade la tarjeta al grid
				grid.add(card, 0, i); 

	    	}

	    });
		
	}
	
	/**
     * Crea y a�ade el grid al anchorpane 'contenedor', creado por defecto
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

}
