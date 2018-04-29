package holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
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

    public TiendaHolder(View itemView) {
        super(itemView);
        this.view=itemView;

    }
    public void setHorario(String cadena){
        TextView field= (TextView) view.findViewById(R.id.lblUsuario);
        field.setText(cadena);
    }

    public void setNombre(String cadena){
        TextView field= (TextView) view.findViewById(R.id.lblNombre);
        field.setText(cadena);
    }

    public void setDireccion(String cadena){
        TextView field= (TextView) view.findViewById(R.id.lblApellidos);
        field.setText(cadena);
    }

    public void setEmailPedidos(String cadena){
        TextView field= (TextView) view.findViewById(R.id.lblEmail);
        field.setText(cadena);
    }
}
