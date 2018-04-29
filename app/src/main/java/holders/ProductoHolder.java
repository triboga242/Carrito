package holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import co.com.hgr.cestadelacompra.R;

/**
 * Created by Triboga on 22/4/18.
 */

public class ProductoHolder extends RecyclerView.ViewHolder {

    private View view;

    public ProductoHolder(View itemView) {
        super(itemView);
        this.view=itemView;

    }
    public void setNombre(String cadena){
        TextView field= (TextView) view.findViewById(R.id.lblUsuario);
        field.setText(cadena);
    }

    public void setDescripcion(String cadena){
        TextView field= (TextView) view.findViewById(R.id.lblNombre);
        field.setText(cadena);
    }

    public void setUnidadDeVenta(String cadena){
        TextView field= (TextView) view.findViewById(R.id.lblApellidos);
        field.setText(cadena);
    }

    public void setPrecioVenta(String cadena){
        TextView field= (TextView) view.findViewById(R.id.lblEmail);
        field.setText(cadena);
    }
}
