package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class nuevoDepartControlador extends Controlador implements Initializable {

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

    }
}