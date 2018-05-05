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
import holders.ProductoHolder;
import modelos.Producto;
import utilesbbdd.AyudanteBBDD;
import utilesbbdd.Container;

/**
 * Actividad para mostrar la lista y opciones de los articulos
 */
public class ListaAritculosTiendaActivity extends AppCompatActivity {

    //Elementos de la vista
    private Button crearNuevoArticulo;

    //Elementos de la bbdd
    private AyudanteBBDD ayudanteBBDD;
    private FirebaseRecyclerAdapter mAdapter;
    private DatabaseReference dbArticulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aritculos_tienda);
        ayudanteBBDD = new AyudanteBBDD();
        findElementosVista();
        setListener();
        setReciclerView();

    }

    /**
     * Asigna elementos de la vista:
     *      Boton crearNuevoArticulo
     */
    private void findElementosVista(){
        crearNuevoArticulo=(Button)findViewById(R.id.btn_nuevo_articulo_tienda);

    }

    /**
     * Setea los listeners de los botones:
     *      Boton crearNuevoArticulo
     */
    private void setListener(){
        crearNuevoArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /**
     * Lanza la lista de los articulos y asigna sus elementos
     */
    private void setReciclerView(){
        dbArticulo =
                FirebaseDatabase.getInstance().getReference("articulo").child(
                        Container.tiendaLogueada.getNombre()).child(Container.categoriaProductoAGuardar);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lista_categorias_tienda);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new FirebaseRecyclerAdapter<Producto, ProductoHolder>(
                Producto.class, R.layout.lista_articulo, ProductoHolder.class, dbArticulo) {
            @Override
            public void populateViewHolder(final ProductoHolder productoHolder, Producto producto, int posicion) {
                productoHolder.setNombreArticulo(producto.getNombreProducto());
                productoHolder.setDescripcionArticulo(producto.getDescripcionProducto());
                productoHolder.setPrecioYUnidadDeVentaArticulo(
                        producto.getPrecioVenta() + " x " + producto.getUnidadDeVenta()
                );
                productoHolder.setBotonBorrarArticulo();
                productoHolder.setBotonEditarArticulo();
                productoHolder.setBotonProductoAgotado();
            }
        };
        recyclerView.setAdapter(mAdapter);
    }
}
