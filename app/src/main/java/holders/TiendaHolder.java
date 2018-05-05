package holders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import co.com.hgr.cestadelacompra.DatosTiendaActivity;
import co.com.hgr.cestadelacompra.EleccionTiendaVendedorActivity;
import co.com.hgr.cestadelacompra.ListaCategoriasTiendaActivity;
import co.com.hgr.cestadelacompra.R;
import utilesbbdd.AyudanteBBDD;
import utilesbbdd.Container;

/**
 * Created by Triboga on 22/4/18.
 *
 * Holder para mostrar los elementos de la lista de tiendas propias
 */

public class TiendaHolder extends RecyclerView.ViewHolder {

    private View view;
    public Button botonEditarTienda;
    public Button botonArticulosTienda;
    private AyudanteBBDD ayudanteBBDD;


    public TiendaHolder(View itemView) {
        super(itemView);
        this.view=itemView;
        ayudanteBBDD = new AyudanteBBDD();


    }
    public void setHorario(String cadena){
        TextView field= (TextView) view.findViewById(R.id.txtvHorario);
        field.setText(cadena);
    }

    public void setNombre(String cadena){
        TextView field= (TextView) view.findViewById(R.id.txtvNombreTienda);
        field.setText(cadena);
    }

    public String getNombre(){
        TextView field= (TextView) view.findViewById(R.id.txtvNombreTienda);

        return field.getText().toString();
    }

    public void setDireccion(String cadena){
        TextView field= (TextView) view.findViewById(R.id.xtvDireccion);
        field.setText(cadena);
    }

    /**
     * Asigna y activa el listener del boton editar tienda
     * Setea en container la tiendaLogueada para mostrar los edittexts de DatosTiendaActivity
     * activa el modo editar
     * llama a la actividad editar tienda
     */
    public void setBotonEditar(){
        botonEditarTienda = (Button) view.findViewById(R.id.btnEditar);
        botonEditarTienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ayudanteBBDD.buscaTiendaSeleccionada(getNombre());
                Container.modoEditar=true;
                Intent intent = new Intent(view.getContext(), DatosTiendaActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    /**
     * Asigna y activa el listener del boton editar tienda
     * Setea en container la tiendaLogueada para mostrar las categorias registradas
     * llama a la actividad de las categorias de la tienda seleccionada
     */
    public void setBotonArticulosTienda(){
        botonArticulosTienda = (Button) view.findViewById(R.id.btnArticulos);
        botonArticulosTienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ayudanteBBDD.buscaTiendaSeleccionada(getNombre());
                Intent intent = new Intent(view.getContext(), ListaCategoriasTiendaActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    public void setBotonPedidosTienda(){
       Button botonPedidosTienda = (Button) view.findViewById(R.id.btnPedidosTienda);

    }
    public void setBotonBorrarTienda(){
        Button botonBorrarTienda = (Button) view.findViewById(R.id.btnBorrarTienda);

    }
}
