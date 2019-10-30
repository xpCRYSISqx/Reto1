package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class nuevoEmpleControlador {

    @FXML
    private TextField codigoEmpleText;

    @FXML
    private TextField nombreEmpleText;

    @FXML
    private TextField apellidosEmpleText;

    @FXML
    private TextField sueldoEmpleText;

    @FXML
    private ComboBox<?> departEmpleComboBox;

    @FXML
    private ComboBox<?> cargoEmpleComboBox;

    @FXML
    private ComboBox<?> jefeEmpleComboBox;

    @FXML
    private RadioButton siEmpleRadioButton;

    @FXML
    private RadioButton noEmpleRadioButton;

    @FXML
    private Button btnGuardar;

    @FXML
    void guardar(ActionEvent event) {

    }

}
