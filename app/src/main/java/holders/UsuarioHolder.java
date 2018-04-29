package holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import co.com.hgr.cestadelacompra.R;

/**
 * Created by Triboga on 21/4/18.
 */

public class UsuarioHolder extends RecyclerView.ViewHolder {

    private View view;

    public UsuarioHolder(View itemView) {
        super(itemView);
        this.view=itemView;

    }
public void setUsuario(String cadena){
    TextView field= (TextView) view.findViewById(R.id.lblUsuario);
    field.setText(cadena);
}

    public void setNombre(String cadena){
        TextView field= (TextView) view.findViewById(R.id.lblNombre);
        field.setText(cadena);
    }

    public void setApellidos(String cadena){
        TextView field= (TextView) view.findViewById(R.id.lblApellidos);
        field.setText(cadena);
    }

    public void setEmail(String cadena){
        TextView field= (TextView) view.findViewById(R.id.lblEmail);
        field.setText(cadena);
    }
}
