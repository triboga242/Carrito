package holders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.com.hgr.cestadelacompra.ListaAritculosTiendaActivity;
import co.com.hgr.cestadelacompra.R;
import utilesbbdd.AyudanteBBDD;
import utilesbbdd.Container;

/**
 * Created by Triboga on 7/5/18.
 */

public class CategoriaCompradrorHolder extends RecyclerView.ViewHolder {
    private View view;
    //Elementos de la vista
    private TextView nombreCategoria;
    private Button editarCategoria;
    private Button borrarCategoria;
    private Button articulosCategoria;
    //Elementos para la BBDD
    AyudanteBBDD ayudanteBBDD;



    public CategoriaCompradrorHolder(View view) {
        super(view);
        this.view=view;
        ayudanteBBDD=new AyudanteBBDD();
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
        editarCategoria.setVisibility(View.GONE);

    }

    public void setBorrarCategoria() {

        this.borrarCategoria = (Button) view.findViewById(R.id.btnBorrarCategoria);
        borrarCategoria.setVisibility(View.GONE);

    }
    public void setArticulosCategoria(final String categoriaSeleccionada) {

        this.articulosCategoria = (Button) view.findViewById(R.id.btnArticulosCategoria);

        articulosCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Container.categoriaProductoAGuardar=categoriaSeleccionada;
                ayudanteBBDD.buscaCategoriaSeleccionada(categoriaSeleccionada);
                Intent intent = new Intent(view.getContext(), ListaAritculosTiendaActivity.class);
                view.getContext().startActivity(intent);
            }
        });


    }
}
