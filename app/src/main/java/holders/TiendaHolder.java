package holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.com.hgr.cestadelacompra.R;

/**
 * Created by Triboga on 22/4/18.
 *
 *  TODO
 * cambiar los findviewbyid, estan los de usuario!!
 */

public class TiendaHolder extends RecyclerView.ViewHolder {

    private View view;
    public Button botonEditarTienda;
    public Button botonArticulosTienda;

    public TiendaHolder(View itemView) {
        super(itemView);
        this.view=itemView;
        botonEditarTienda = (Button) view.findViewById(R.id.btnEditar);
        botonArticulosTienda = (Button) view.findViewById(R.id.btnArticulos);


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

    public void setBotonEditar(){
        botonEditarTienda = (Button) view.findViewById(R.id.btnEditar);
    }

    public void setBotonArticulosTienda(){
        botonArticulosTienda = (Button) view.findViewById(R.id.btnArticulos);

    }
}
