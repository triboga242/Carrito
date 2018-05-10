package modelos;

import android.content.Intent;

/**
 * Created by Triboga on 8/5/18.
 */

public class Item {

    private Integer cantidad;
    private Producto producto;

    public Item(){}

    public Item(Integer cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Item{" +
                "cantidad=" + cantidad +
                ", producto=" + producto.getNombreProducto() +
                '}';
    }
}
