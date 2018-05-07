package holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.com.hgr.cestadelacompra.R;

/**
 * Created by Triboga on 7/5/18.
 */

public class ProductoCompradorHolder extends RecyclerView.ViewHolder {

    private View view;

    public ProductoCompradorHolder(View itemView) {
        super(itemView);
        this.view = itemView;
    }

    public void setNombreArticulo(String cadena) {
        TextView field = (TextView) view.findViewById(R.id.txtvNombreArticulo);
        field.setText(cadena);
    }

    public void setDescripcionArticulo(String cadena) {
        TextView field = (TextView) view.findViewById(R.id.txtvDescripcionArticulo);
        field.setText(cadena);
    }

    public void setPrecioYUnidadDeVentaArticulo(String cadena) {
        TextView field = (TextView) view.findViewById(R.id.txtvPrecioYUnidadVentaArticulo);
        field.setText(cadena);
    }

    public void setBotonEditarArticulo() {
        Button btnEditarArticulo = (Button) view.findViewById(R.id.btnEditarArticulo);
        btnEditarArticulo.setVisibility(View.GONE);
    }

    public void setBotonProductoAgotado() {
        Button botonProductoAgotado = (Button) view.findViewById(R.id.btnArticuloAgotado);
        botonProductoAgotado.setVisibility(View.GONE);
    }

    public void setBotonBorrarArticulo() {
        Button botonBorrarArticulo = (Button) view.findViewById(R.id.btnBorrarArticulo);
        botonBorrarArticulo.setVisibility(View.GONE);
    }
}
