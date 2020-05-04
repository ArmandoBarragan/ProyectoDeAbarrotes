package sample;

import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField fldNombreProducto;
    public TextField fldIdBuscarProducto;
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
    public TextField fldNombreNuevaCategoria;
    public TextArea areaDescripcionNuevaCategoria;
    public TableView tablarResultados;
    public Button btnNuevaCategoria;
    public Button btnOrdenarAlfabeto;
    public Button btnOrdenarPrecio;
    public TableColumn colNombre;
    public TableColumn colDescripcion;
    public TableColumn colId;
    public TableColumn colPrecio;
    public TableColumn colCategoria;
    public Button btnEliminar;
    private Connection conexionSql;
    private Conexion conexionLocal;
    private Statement declaracion;
    private ResultSet resultSet;
    private PreparedStatement ps;
    private ObservableList<Producto> listaProductos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conexionLocal = new Conexion();
        conexionSql = conexionLocal.getConexion();

        try {
            declaracion = conexionSql.createStatement();
            resultSet = declaracion.executeQuery("SELECT nombre_categoria from categoria");
            while(resultSet.next()){
                cbBusqudaCategoria.getItems().add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Se usaran estos para que el buscador sepa segun que parametros hacer el select
        campoIdIsInUse = true;
        campoNombreIsInUse = false;
    }


    @FXML
    public void buscar(){
        conexionLocal = new Conexion();
        conexionSql = conexionLocal.getConexion();
        listaProductos = FXCollections.observableArrayList();
        ps = null;
        resultSet = null;
        try {
            if(campoNombreIsInUse){
                ps = conexionSql.prepareStatement("SELECT * from producto where nombre_producto = (?)");
                ps.setString(1,fldNombreProducto.getText());
            }
            else{
                ps = conexionSql.prepareStatement("SELECT * from producto where id_producto = (?)");
                ps.setString(1, fldIdBuscarProducto.getText());
            }

            resultSet = ps.executeQuery();

            while(resultSet.next()){
                int id_producto = resultSet.getInt("id_producto");
                String nombre_producto = resultSet.getString("nombre_producto");
                String descripcion_producto = resultSet.getString("descripcion_producto");
                String precio = resultSet.getString("precio");
                int id_categoria = resultSet.getInt("id_categoria");

                Producto producto = new Producto();
                producto.setNombre_producto(nombre_producto);
                producto.setId_producto(id_producto);
                producto.setDescripcion_producto(descripcion_producto);
                producto.setPrecio(precio);
                producto.setId_categoria(id_categoria);

                listaProductos.add(producto);
            }
            colId.setCellValueFactory(new PropertyValueFactory<>("id_producto"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre_producto"));
            colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion_producto"));
            colCategoria.setCellValueFactory(new PropertyValueFactory<>("id_categoria"));
            colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

            tablarResultados.setItems(listaProductos);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void agregarProducto(){
        String nombre = "";
        String descripcion = "";
        String precio = "";
        String id_categoria = "";

        if (!fldNombreNuevoProducto.getText().equals(""))
            nombre = fldNombreNuevoProducto.getText();
        if(!areaDescripcionNuevoProducto.getText().equals(""))
            descripcion = areaDescripcionNuevoProducto.getText();
        if (!fldPrecioNuevoProducto.getText().equals(""))
            precio = fldPrecioNuevoProducto.getText();
        if (!fldCategoria.getText().equals(""))
            id_categoria = fldCategoria.getText();

        PreparedStatement ps = null;
        try{
            Conexion conexion = new Conexion();
            Connection conexion2 = conexion.getConexion();

            if(!areaDescripcionNuevoProducto.getText().equals("")) {
                ps = conexion2.prepareStatement("INSERT INTO producto (nombre_producto, " +
                        "descripcion_producto, precio, id_categoria) values (?,?,?,?)");
                ps.setString(1, nombre);
                ps.setString(2, descripcion);
                ps.setString(3, precio);
                ps.setInt(4, Integer.parseInt(id_categoria));

            }
            else {
                declaracion = conexion2.prepareStatement("INSERT INTO producto (nombre_producto, precio, id_categoria) values (?,?,?)");
                ps.setString(1, nombre);
                ps.setString(2, precio);
                ps.setString(3, id_categoria);
            }
            int resultado = ps.executeUpdate();
            if (resultado > 0){
                JOptionPane.showMessageDialog(null, "Registrado correctamente");
            }
            else{
                JOptionPane.showMessageDialog(null, "Algo ha salido mal");
            }

//            resultSet = declaracion.executeQuery()
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void ordenarPorAlfabeto(ActionEvent actionEvent) {
        conexionLocal = new Conexion();
        conexionSql = conexionLocal.getConexion();
        listaProductos = FXCollections.observableArrayList();
        PreparedStatement ps = null;
        resultSet = null;
        try {
            ps = conexionSql.prepareStatement("SELECT * from producto order by nombre_producto");
            resultSet = ps.executeQuery();

            while(resultSet.next()){
                int id_producto = resultSet.getInt("id_producto");
                String nombre_producto = resultSet.getString("nombre_producto");
                String descripcion_producto = resultSet.getString("descripcion_producto");
                String precio = resultSet.getString("precio");
                int id_categoria = resultSet.getInt("id_categoria");

                Producto producto = new Producto();
                producto.setNombre_producto(nombre_producto);
                producto.setId_producto(id_producto);
                producto.setDescripcion_producto(descripcion_producto);
                producto.setPrecio(precio);
                producto.setId_categoria(id_categoria);

                listaProductos.add(producto);
            }
            colId.setCellValueFactory(new PropertyValueFactory<>("id_producto"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre_producto"));
            colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion_producto"));
            colCategoria.setCellValueFactory(new PropertyValueFactory<>("id_categoria"));
            colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

            tablarResultados.setItems(listaProductos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ordenarPorPrecio(ActionEvent actionEvent) {
        conexionLocal = new Conexion();
        conexionSql = conexionLocal.getConexion();
        listaProductos = FXCollections.observableArrayList();
        ps = null;
        resultSet = null;
        try {
            declaracion = conexionSql.createStatement();
            String orderBy = "";

            ps = conexionSql.prepareStatement("SELECT * from producto order by precio");
            resultSet = ps.executeQuery();

            while(resultSet.next()){
                int id_producto = resultSet.getInt("id_producto");
                String nombre_producto = resultSet.getString("nombre_producto");
                String descripcion_producto = resultSet.getString("descripcion_producto");
                String precio = resultSet.getString("precio");
                int id_categoria = resultSet.getInt("id_categoria");

                Producto producto = new Producto();
                producto.setNombre_producto(nombre_producto);
                producto.setId_producto(id_producto);
                producto.setDescripcion_producto(descripcion_producto);
                producto.setPrecio(precio);
                producto.setId_categoria(id_categoria);

                listaProductos.add(producto);
            }
            colId.setCellValueFactory(new PropertyValueFactory<>("id_producto"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre_producto"));
            colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion_producto"));
            colCategoria.setCellValueFactory(new PropertyValueFactory<>("id_categoria"));
            colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

            tablarResultados.setItems(listaProductos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void ordenar(boolean porPrecio){

    }
    public void cambiarACampoId(MouseEvent actionEvent) {
        campoIdIsInUse = true;
        campoNombreIsInUse = false;
        System.out.printf("Campo id");
    }

    public void cambiarACampoNombre(MouseEvent actionEvent) {
        campoNombreIsInUse = true;
        campoIdIsInUse = false;
        System.out.println("Campo nombre");
    }

    public void eliminar(ActionEvent actionEvent) {
        System.out.println(ps);
//        try{
//            System.out.println(preparedStatement);
//        }
//        catch(SQLException ex){
//            ex.printStackTrace();
//        }
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
