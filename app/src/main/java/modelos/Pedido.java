package modelos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Triboga on 22/4/18.
 */

public class Pedido {

    private String pedidoDe;
    private String pedidoA;
    private String fechaPedido;
    private List<Item> items;
    private String fechaEntregaPedido;
    private Boolean entregado;


    public Pedido(){
        items = new ArrayList<>(); {
        }
    }

    public Pedido(String pedidoDe, String pedidoA, String fechaPedido, String fechaEntregaPedido, Boolean entregado) {
        this.pedidoDe = pedidoDe;
        this.pedidoA = pedidoA;
        this.fechaPedido = fechaPedido;
        this.items = new ArrayList<>();
        this.fechaEntregaPedido = fechaEntregaPedido;
        this.entregado = entregado;
    }

    public String getPedidoDe() {
        return pedidoDe;
    }

    public void setPedidoDe(String pedidoDe) {
        this.pedidoDe = pedidoDe;
    }

    public String getPedidoA() {
        return pedidoA;
    }

    public void setPedidoA(String pedidoA) {
        this.pedidoA = pedidoA;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Boolean getEntregado() {
        return entregado;
    }

    public void setEntregado(Boolean entregado) {
        this.entregado = entregado;
    }

    public String getFechaEntregaPedido() {
        return fechaEntregaPedido;
    }

    public void setFechaEntregaPedido(String fechaEntregaPedido) {
        this.fechaEntregaPedido = fechaEntregaPedido;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "pedidoDe='" + pedidoDe + '\'' +
                ", pedidoA='" + pedidoA + '\'' +
                ", fechaPedido='" + fechaPedido + '\'' +
                ", items=" + items +
                ", fechaEntregaPedido='" + fechaEntregaPedido + '\'' +
                '}';
    }

    public List<Producto> getProductos() {

        ArrayList<Producto>productos= new ArrayList<>();
        if (items.size()>0) {
            for (Item item : items) {
                productos.add(item.getProducto());
            }
        }
        return productos;
    }
}
