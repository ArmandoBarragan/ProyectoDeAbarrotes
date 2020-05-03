package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField fldNombreProducto;
    public TextField fldIdProducto;
    public ChoiceBox cbBusqudaCategoria;
    public Button btnReporte;
    public TextField fldNombreNuevoProducto;
    public TextArea areaDescripcionNuevoProducto;
    public TextField fldPrecioNuevoProducto;
    public TextField fldCategoria;
    public Button btnNuevoProducto;
    public Button btnBuscar;
    public boolean campoNombreIsInUse;
    public boolean campoIdIsInUse;
    public boolean campoCategoriaIsInUse;
    public TextField fldNombreNuevaCategoria;
    public TextArea areaDescripcionNuevaCategoria;
    private Connection conexionSql;
    Conexion conexionLocal;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Conexion conexion = new Conexion();
        Connection conexionSql = conexion.getConexion();

        try {
            Statement declaracion = conexionSql.createStatement();
            ResultSet resultSet = declaracion.executeQuery("SELECT nombre_categoria from categoria");
            while(resultSet.next()){
                cbBusqudaCategoria.getItems().add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Se usaran estos para que el buscador sepa segun que parametros hacer el select
        campoNombreIsInUse = true;
        campoIdIsInUse = false;
        campoCategoriaIsInUse = false;
    }


    @FXML
    protected void buscar(){
        if (campoNombreIsInUse);
    }

    @FXML
    protected void generarReporte(){}

    @FXML
    protected void agregarProducto(){}

    public void cambiarACampoId(ActionEvent actionEvent) {
        campoIdIsInUse = true;
        campoCategoriaIsInUse = false;
        campoNombreIsInUse = false;
    }

    public void cambiarACampoNombre(ActionEvent actionEvent) {
        campoNombreIsInUse = true;
        campoCategoriaIsInUse = false;
        campoIdIsInUse = false;
    }

    public void cambiarACampoCategoria(ActionEvent actionEvent) {
        campoCategoriaIsInUse = true;
        campoIdIsInUse = false;
        campoNombreIsInUse = false;
    }

//    protected void crearConexion(){
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conectar = DriverManager.getConnection (
//                    "jdbc:mysql://localhost:3306/abarrotes", "root","18550696"
//            );
//            System.out.println("Conectado");
//        }
//        catch(ClassNotFoundException ex){
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        catch(SQLException ex){
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
//        }
//    }
}
