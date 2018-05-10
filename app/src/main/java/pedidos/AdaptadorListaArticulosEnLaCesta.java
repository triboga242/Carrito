package pedidos;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.com.hgr.cestadelacompra.R;
import modelos.Producto;
import utilesbbdd.Container;

/**
 * Created by Triboga on 8/5/18.
 */

public class AdaptadorListaArticulosEnLaCesta extends BaseAdapter {

    protected Activity activity;
    protected List<Producto> items;

    public AdaptadorListaArticulosEnLaCesta (Activity activity) {
        this.activity = activity;
       // this.items = items;
        this.items= Container.pedidoEnCurso.getProductos();
    }

    public void addAll(ArrayList<Producto> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View v = view;

        if (view == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.lista_articulo, null);
        }

        Producto dir = items.get(position);
        TextView tvNombreArticulo = (TextView) v.findViewById(R.id.txtvNombreArticulo);
        tvNombreArticulo.setText(dir.getNombreProducto());
        TextView tvDescripcionArticulo = (TextView) v.findViewById(R.id.txtvDescripcionArticulo);
        TextView tvPrecioYcantidadDeVentaArticulo = (TextView) v.findViewById(R.id.txtvPrecioYUnidadVentaArticulo);

        tvDescripcionArticulo.setText(dir.getDescripcionProducto());
        tvPrecioYcantidadDeVentaArticulo.setText(String.valueOf(dir.getPrecioVenta()));


        return v;
    }
}
