package vista;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import controlador.DepartamentoControlador;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import modelo.Departamento;
import modelo.LogginLevels;
import modelo.Modelo;

public class CardDepartamento extends AnchorPane implements Initializable {
	
    @FXML
    private AnchorPane paneBase, card;

    @FXML
    private Text nombre, localizacion;
    
    private Departamento departamento;
    private float tAnimacion;
    private AnchorPane contenido;
    private Modelo modelo;
    
	public CardDepartamento(Departamento departamento, float tAnimacion, AnchorPane contenido, Modelo modelo) {
		
		this.departamento = departamento;
		this.tAnimacion = tAnimacion;
		this.contenido = contenido;
		this.modelo = modelo;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/cardDepartamento.fxml"));
	    fxmlLoader.setRoot(this);
	    fxmlLoader.setController(this);

	    try {
	        fxmlLoader.load();
	    } catch (IOException e) {
	    	modelo.escritorFicheros.crearLog(this.getClass().getName(), this.getClass().getEnclosingMethod().getName(), e.toString());
	        throw new RuntimeException(e);
	        
	    }
	    
	    inizializarAnimacion();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.nombre.setText(this.departamento.getNombre());
		this.localizacion.setText(this.departamento.getLocalizacion());
	}
	
	@FXML
    void verDepartamento(ActionEvent event) {
		Parent FXML = null;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/departamento.fxml"));
		try {
			FXML = fxmlLoader.load();
		} catch (IOException e) {
			modelo.escritorFicheros.crearLog(this.getClass().getName(), this.getClass().getEnclosingMethod().getName(), e.toString());
		} 
		DepartamentoControlador controller = fxmlLoader.<DepartamentoControlador>getController();
		controller.setDepartamento(departamento);
    	contenido.getChildren().clear();
    	contenido.getChildren().add(FXML);
    }
	
	/**
	 * Inicializa la animacion de carga de los paneles de los alojamientos
	 */
    private void inizializarAnimacion() {
    	TranslateTransition transicion = new TranslateTransition();
    	transicion.setFromY(800);
    	transicion.setToY(0);
    	transicion.setDuration(Duration.seconds(tAnimacion));
    	transicion.setNode(paneBase);
    	transicion.play();	
	}
	
}