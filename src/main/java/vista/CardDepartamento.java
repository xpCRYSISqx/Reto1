package vista;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class CardDepartamento extends AnchorPane implements Initializable {
	
    @FXML
    private AnchorPane paneBase, card;

    @FXML
    private Text nombre, localizacion;

    @FXML
    private Label descripcion;
    
    private Departamento departamento;
    private float tAnimacion;
    private AnchorPane contenido;
    
	public CardDepartamento(Departamento alojamiento, float tAnimacion, AnchorPane contenido) {
		
		this.departamento = alojamiento;
		this.tAnimacion = tAnimacion;
		this.contenido = contenido;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/cardDepartamento.fxml"));
	    fxmlLoader.setRoot(this);
	    fxmlLoader.setController(this);

	    try {
	        fxmlLoader.load();
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	    
	    inizializarAnimacion();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.nombre.setText(this.departamento.getNombre());
		this.localizacion.setText(this.departamento.getLocalizacion());
//		this.descripcion.setText(this.departamento.getDescripcion());
	}
	
	@FXML
    void verDepartamento(ActionEvent event) {
		Parent FXML = null;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/departamento.fxml"));
		try {
			FXML = fxmlLoader.load();
		} catch (IOException e) {
//			modelo.escritorFicheros.crearLog(new Date(), e.toString());
		} 
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