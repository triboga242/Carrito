package modelos;

import java.util.Map;

/**
 * Created by Triboga on 22/4/18.
 */

public class Pedido {

    private String pedidoDe;
    private String pedidoA;
    private String fechaPedido;
    private Map<String, Producto>items;
    private String fechaEntregaPedido;


    public Pedido(){}

    public Pedido(String pedidoDe, String pedidoA, String fechaPedido, Map<String, Producto> items, String fechaEntregaPedido) {
        this.pedidoDe = pedidoDe;
        this.pedidoA = pedidoA;
        this.fechaPedido = fechaPedido;
        this.items = items;
        this.fechaEntregaPedido = fechaEntregaPedido;
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

    public Map<String, Producto> getItems() {
        return items;
    }

    public void setItems(Map<String, Producto> items) {
        this.items = items;
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
}
