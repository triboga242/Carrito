package holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.com.hgr.cestadelacompra.R;
import modelos.Producto;
import utilesbbdd.AyudanteBBDD;

/**
 * Created by Triboga on 7/5/18.
 */

public class ProductoCompradorHolder extends RecyclerView.ViewHolder {

    private View view;

    //Elementos de la BBDD
    AyudanteBBDD ayudanteBBDD;

    public ProductoCompradorHolder(View itemView) {
        super(itemView);
        this.view = itemView;
        ayudanteBBDD=new AyudanteBBDD();
    }

    public void setNombreArticulo(String cadena) {
        TextView field = (TextView) view.findViewById(R.id.txtvNombreArticulo);
        field.setText(cadena);
    }

    public void setDescripcionArticulo(String cadena) {
        TextView field = (TextView) view.findViewById(R.id.txtvDescripcionArticulo);
        field.setText(cadena);
    }

    public void setCantidadArticulo(String cadena) {
        TextView field = (TextView) view.findViewById(R.id.txtvDescripcionArticulo);
        field.setText(cadena);
    }

    public String getCantidadArticulo(){
        TextView field = (TextView) view.findViewById(R.id.txtvDescripcionArticulo);
        return field.getText().toString();
    }

    public void setPrecioYUnidadDeVentaArticulo(String cadena) {
        TextView field = (TextView) view.findViewById(R.id.txtvPrecioYUnidadVentaArticulo);
        field.setText(cadena);
    }

    public String getPrecioYUnidadDeVentaArticulo(){
        TextView field = (TextView) view.findViewById(R.id.txtvPrecioYUnidadVentaArticulo);
        return field.getText().toString();
    }

    public String getNombreArticulo(){
        TextView field = (TextView) view.findViewById(R.id.txtvNombreArticulo);
        return field.getText().toString();
    }

    public String getDescripcionArticulo(){
        TextView field = (TextView) view.findViewById(R.id.txtvDescripcionArticulo);
        return field.getText().toString();
    }

    public void setBotonEditarArticulo(final Context context) {
        Button btnEditarArticulo = (Button) view.findViewById(R.id.btnEditarArticulo);
        btnEditarArticulo.setText("Añadir");
      //  btnEditarArticulo.setVisibility(View.GONE);
        btnEditarArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ayudanteBBDD.aniadeUnProductoAPedidoEnCurso(creaProducto(), context);
            }
        });
    }

    public void setBotonProductoAgotado() {
        Button botonProductoAgotado = (Button) view.findViewById(R.id.btnArticuloAgotado);
       // botonProductoAgotado.setVisibility(View.GONE);
        botonProductoAgotado.setText("Informacion");
    }

    public void setBotonBorrarArticulo() {
        Button botonBorrarArticulo = (Button) view.findViewById(R.id.btnBorrarArticulo);
        botonBorrarArticulo.setVisibility(View.GONE);
    }

    public Producto creaProducto(){
        Producto producto= new Producto();
        producto.setNombreProducto(getNombreArticulo());
        producto.setDescripcionProducto(getDescripcionArticulo());
        //producto.setPrecioVenta();


        return producto;
    }
}
