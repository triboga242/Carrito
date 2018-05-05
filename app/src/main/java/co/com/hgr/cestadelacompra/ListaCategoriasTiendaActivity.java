package co.com.hgr.cestadelacompra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import holders.CategoriaVendedorHolder;
import utilesbbdd.AyudanteBBDD;
import utilesbbdd.Container;

public class ListaCategoriasTiendaActivity extends AppCompatActivity {

    //Elementos de la vista
    private TextView nombreNuevaCategoria;
    private Button guardarNuevaCategoria;

    //Elementos de la bbdd
    private AyudanteBBDD ayudanteBBDD;
    private FirebaseRecyclerAdapter mAdapter;
    private DatabaseReference dbUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_categorias_tienda);
        findElementosVista();
        setListenerBotonGuardar();
        setRecyclerAdapter();
        ayudanteBBDD = new AyudanteBBDD();
    }

    private void setRecyclerAdapter() {
        dbUsuario =
                FirebaseDatabase.getInstance().getReference("articulo").child(
                        Container.tiendaLogueada.getNombre());

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lista_categorias_tienda);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new FirebaseRecyclerAdapter<String, CategoriaVendedorHolder>(
                String.class, R.layout.lista_categoria_vendedor, CategoriaVendedorHolder.class, dbUsuario) {
            @Override
            public void populateViewHolder(final CategoriaVendedorHolder categoriaVendedorHolder, String string, int posicion) {
                categoriaVendedorHolder.setNombreCategoria(string);

                categoriaVendedorHolder.setBorrarCategoria();
                categoriaVendedorHolder.setEditarCategoria();
                categoriaVendedorHolder.setArticulosCategoria();

            }
        };
        recyclerView.setAdapter(mAdapter);


    }

    private void findElementosVista() {
        this.nombreNuevaCategoria = (TextView) findViewById(R.id.txtv_nombre_categoria_nueva);
        this.guardarNuevaCategoria = (Button) findViewById(R.id.btn_nueva_categoria_tienda);
    }

    private void setListenerBotonGuardar() {

        guardarNuevaCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarCategoria();
            }
        });

    }

    private void guardarCategoria() {

        if (nombreNuevaCategoria.getText().toString().isEmpty() || nombreNuevaCategoria.getText().toString().equals("")) {

        } else {
            ayudanteBBDD.aniadeUnaCategoria(nombreNuevaCategoria.getText().toString());
        }
    }
}
