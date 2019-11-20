package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.effects.JFXDepthManager;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import modelo.Departamento;
import vista.CardDepartamento;

public class DepartamentosControlador extends Controlador implements Initializable  {

    @FXML
    private Button btnNuevo;
    
    @FXML
    private AnchorPane contenedor;
    
    @FXML
    private TextField textoBusquedaD;
    
    @FXML
    private ComboBox<String> tipoBusquedaD;

    @FXML
    void nuevo(ActionEvent event) {
    	Parent FXML = loadFXML("nuevoDepartamento.fxml");
		contenido.getChildren().clear();
		contenido.getChildren().add(FXML);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<String> tipos = new ArrayList<String>();
		tipos.add("Nombre");
		tipos.add("Codigo");
		ObservableList<String> listaTipos = FXCollections.observableArrayList(tipos);
		tipoBusquedaD.setItems(listaTipos);
		tipoBusquedaD.getSelectionModel().selectFirst();
		Platform.runLater(() -> {

			GridPane grid = crearGrid();
	        int i = 0;
	    	for(i = 0; i < modelo.departamentos.size(); i++) {
	    		CardDepartamento card = crearCard(i);
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
	    	for(i = 0; i < modelo.departamentos.size(); i++) {
	    		if(tipoBusquedaD.getSelectionModel().getSelectedItem().toString() == "Nombre") {
		    		if(textoBusquedaD.getText().length() <= modelo.departamentos.get(i).getNombre().length()) {
			    		if(textoBusquedaD.getText().equals("") || textoBusquedaD.getText().toLowerCase().equals(modelo.departamentos.get(i).getNombre().toLowerCase().substring(0, textoBusquedaD.getText().length()))) {
			    			
				    		CardDepartamento card = crearCard(i);
				    		JFXDepthManager.setDepth(card, 1);
				        	// añade la tarjeta al grid
							grid.add(card, 0, i); 
				    	}
		    		}
	    		}
	    		else {
	    			if(textoBusquedaD.getText().length() <= String.valueOf(modelo.departamentos.get(i).getCodDepartamento()).length()) {
			    		if(textoBusquedaD.getText().equals("") || textoBusquedaD.getText().equals(String.valueOf(modelo.departamentos.get(i).getCodDepartamento()).substring(0, textoBusquedaD.getText().length()))) {
			    			
			    			CardDepartamento card = crearCard(i);
				    		JFXDepthManager.setDepth(card, 1);
				        	// añade la tarjeta al grid
							grid.add(card, 0, i); 
				    	}
	    			}
	    		}
	    	}
		});
    }
    
    public CardDepartamento crearCard(int i) {
    	Departamento depart = modelo.departamentos.get(i);
		// crea la tarjeta con la informacion del alojamiento
		CardDepartamento card = new CardDepartamento(depart, (float)(i)/16 + 1F, contenido, modelo);
		return card;
    }
}
