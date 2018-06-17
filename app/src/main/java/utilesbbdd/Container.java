package utilesbbdd;

import android.content.Intent;

import modelos.Pedido;
import modelos.Tienda;
import modelos.UsuarioPersona;

/**
 * Created by Triboga on 30/4/18.
 */

public class Container {

    //Usuario logueado
    public static UsuarioPersona personaLogueada= new UsuarioPersona();
    //Tienda seleccionada/logueada
    public static Tienda tiendaLogueada = new Tienda();
    //Modo editar
    public static Boolean modoEditar = false;
    //Key de la tienda a editar
    public static String keyTiendaAeditar ="";
    //Categoria del producto a guardar
    public static String categoriaProductoAGuardar="";
    //Key categoria seleccionada
    public static String keyCategoriaSeleccionada;
    //Pedido en curso
    public static Pedido pedidoEnCurso= new Pedido();
    //Precio total pedido en curso
    public static float precioTotalPedidoEnCurso=0f;
    //Current tocken
    public static String currentTocken="";
}
