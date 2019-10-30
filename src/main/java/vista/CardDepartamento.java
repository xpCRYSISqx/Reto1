package vista;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import modelo.Departamento;

public class CardDepartamento extends AnchorPane implements Initializable {
	
    @FXML
    private AnchorPane paneBase;

    @FXML
    private AnchorPane card;

    @FXML
    private Text nombre;

    @FXML
    private Text localizacion;

    @FXML
    private Label descripcion;
    
    private Departamento departamento;
    
    private float tAnimacion;
    
	public CardDepartamento(Departamento alojamiento, float tAnimacion, boolean animacionActivada) {
		
		this.departamento = alojamiento;
		this.tAnimacion = tAnimacion;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/empleado.fxml"));
	    fxmlLoader.setRoot(this);
	    fxmlLoader.setController(this);

	    try {
	        fxmlLoader.load();
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	    
	    if(animacionActivada) 
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