package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Producto {
    private IntegerProperty id_producto = new SimpleIntegerProperty();
    private StringProperty nombre_producto = new SimpleStringProperty();
    private StringProperty descripcion_producto = new SimpleStringProperty();
    private StringProperty precio = new SimpleStringProperty();
    private IntegerProperty id_categoria = new SimpleIntegerProperty();

    public Producto(){}

    public int getId_producto() {
        return id_producto.get();
    }

    public IntegerProperty id_productoProperty() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto.set(id_producto);
    }

    public String getNombre_producto() {
        return nombre_producto.get();
    }

    public StringProperty nombre_productoProperty() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto.set(nombre_producto);
    }

    public String getDescripcion_producto() {
        return descripcion_producto.get();
    }

    public StringProperty descripcion_productoProperty() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto.set(descripcion_producto);
    }

    public String getPrecio() {
        return precio.get();
    }

    public StringProperty precioProperty() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio.set(precio);
    }

    public int getId_categoria() {
        return id_categoria.get();
    }

    public IntegerProperty id_categoriaProperty() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria.set(id_categoria);
    }
}
