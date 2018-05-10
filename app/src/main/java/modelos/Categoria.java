package modelos;

import java.util.HashMap;

/**
 * Created by Triboga on 8/5/18.
 */

public class Categoria {
    String nombreCategoria;
    HashMap<String, Producto> productos;

    public Categoria(){}

    public Categoria(String nombreCategoria, HashMap<String, Producto> productos) {
        this.nombreCategoria = nombreCategoria;
        this.productos = productos;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}
