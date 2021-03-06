package modelos;

/**
 * Created by Triboga on 22/4/18.
 */

public class Producto {

    private String categoriaProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private Integer unidadDeVenta;
    private String medidaDeVenta;
    private double precioVenta;
    private boolean disponible;

    public Producto(){}

    public Producto(String categoriaProducto, String nombreProducto, String descripcionProducto, Integer unidadDeVenta, double precioVenta) {
        this.categoriaProducto=categoriaProducto;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.unidadDeVenta = unidadDeVenta;
        this.precioVenta = precioVenta;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public Integer getUnidadDeVenta() {
        return unidadDeVenta;
    }

    public void setUnidadDeVenta(Integer unidadDeVenta) {
        this.unidadDeVenta = unidadDeVenta;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getMedidaDeVenta() {
        return medidaDeVenta;
    }

    public void setMedidaDeVenta(String medidaDeVenta) {
        this.medidaDeVenta = medidaDeVenta;
    }

    public String getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(String categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "categoriaProducto='" + categoriaProducto + '\'' +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", descripcionProducto='" + descripcionProducto + '\'' +
                ", unidadDeVenta=" + unidadDeVenta +
                ", medidaDeVenta='" + medidaDeVenta + '\'' +
                ", precioVenta=" + precioVenta +
                ", disponible=" + disponible +
                '}';
    }
}
