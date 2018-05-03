package utilesbbdd;

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
}
