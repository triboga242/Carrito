package holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import co.com.hgr.cestadelacompra.R;

/**
 * Created by Triboga on 5/5/18.
 * Holder para mostrar las categorias en un ReclyclerView
 */

public class CategoriaVendedorHolder extends RecyclerView.ViewHolder {

    private View view;
    //Elementos de la vista
    private TextView nombreCategoria;
    private Button editarCategoria;
    private Button borrarCategoria;
    private Button articulosCategoria;



    public CategoriaVendedorHolder(View view) {
        super(view);
        this.view=view;
    }

    public TextView getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String categoria) {
        this.nombreCategoria = (TextView) view.findViewById(R.id.txtvNombreCategoria);
        nombreCategoria.setText(categoria.toString());
    }

    public void setEditarCategoria() {

        this.editarCategoria = (Button) view.findViewById(R.id.btnEditarCategoria);
        editarCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void setBorrarCategoria() {

        this.borrarCategoria = (Button) view.findViewById(R.id.btnBorrarCategoria);

    }
    public void setArticulosCategoria() {

        this.articulosCategoria = (Button) view.findViewById(R.id.btnArticulosCategoria);

    }
}
