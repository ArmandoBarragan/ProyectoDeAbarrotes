package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField fldNombreProducto;
    public TextField fldIdProducto;
    public ChoiceBox cbBusqudaCategoria;
    public Button btnReporte;
    public TextField fldNombreNuevoProducto;
    public TextField fldDescripcionNuevoProducto;
    public TextField fldPrecioNuevoProducto;
    public TextField fldCategoria;
    public Button btnNuevoProducto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
