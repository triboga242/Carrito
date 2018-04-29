package holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import co.com.hgr.cestadelacompra.R;

/**
 * Created by Triboga on 22/4/18.
 *
 * TODO
 * cambiar los findviewbyid, estan los de usuario!!
 */

public class PedidoHolder extends RecyclerView.ViewHolder {

    private View view;

    public PedidoHolder(View itemView) {
        super(itemView);
        this.view = itemView;

    }

    public void setPedidoDe(String cadena) {
        TextView field = (TextView) view.findViewById(R.id.lblUsuario);
        field.setText(cadena);
    }

    public void setPedidoA(String cadena) {
        TextView field = (TextView) view.findViewById(R.id.lblNombre);
        field.setText(cadena);
    }

    public void setFechaPedido(String cadena) {
        TextView field = (TextView) view.findViewById(R.id.lblApellidos);
        field.setText(cadena);
    }

    public void SetFechaEntrega(String cadena) {
        TextView field = (TextView) view.findViewById(R.id.lblEmail);
        field.setText(cadena);
    }

    public void SetEntregado(String cadena) {
        TextView field = (TextView) view.findViewById(R.id.lblEmail);
        field.setText(cadena);
    }

}
