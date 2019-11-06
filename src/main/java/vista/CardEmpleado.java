package vista;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import controlador.EmpleadoControlador;
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
import modelo.Empleado;
import modelo.Modelo;

public class CardEmpleado extends AnchorPane implements Initializable {
	
    @FXML
    private AnchorPane paneBase, card;

    @FXML
    private Text nombre, departamento;

    @FXML
    private Label cargo;
    
    private Empleado empleado;
    private float tAnimacion;
    private AnchorPane contenido;
    private Modelo modelo;
    
	public CardEmpleado(Empleado empleado, float tAnimacion, AnchorPane contenido, Modelo modelo) {
		
		this.empleado = empleado;
		this.tAnimacion = tAnimacion;
		this.contenido = contenido;
		this.modelo = modelo;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/cardEmpleado.fxml"));
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
		this.nombre.setText(this.empleado.getNombre());
//		this.departamento.setText(this.empleado.getCodDepartamento());
//		this.cargo.setText(this.empleado.getCodCargo());
//		this.descripcion.setText(this.empleado.getDescripcion());
//		this.precio.setText(precio + "€");

	}
	
	@FXML
    void verEmpleado(ActionEvent event) {
		Parent FXML = null;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/empleado.fxml"));
		try {
			FXML = fxmlLoader.load();
		} catch (IOException e) {
			modelo.escritorFicheros.crearLog(new Date(), e.toString());
		}
		EmpleadoControlador controller = fxmlLoader.<EmpleadoControlador>getController();
		controller.setEmpleado(empleado);
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