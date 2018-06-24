package co.com.hgr.cestadelacompra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import holders.ProductoCompradorHolder;
import holders.ProductoHolder;
import modelos.Producto;
import pedidos.DatosPedidoClienteActivity;
import utilesbbdd.AyudanteBBDD;
import utilesbbdd.Container;

/**
 * Created by Triboga on 7/5/18.
 */

public class ListaArticulosCompradorActivity extends AppCompatActivity {

    //Elementos de la vista
    private Button btnDatosPedido;

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
        setReciclerView();

    }

    /**
     * Asigna elementos de la vista:
     *      Boton crearNuevoArticulo
     */
    private void findElementosVista(){
        btnDatosPedido=(Button)findViewById(R.id.btn_nuevo_articulo_tienda);
        btnDatosPedido.setText("Volver al carrito");
        btnDatosPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListaArticulosCompradorActivity.this, DatosPedidoClienteActivity.class);
                startActivity(intent);
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

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lista_articulos_tienda);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new FirebaseRecyclerAdapter<Producto, ProductoCompradorHolder>(
                Producto.class, R.layout.lista_articulo, ProductoCompradorHolder.class, dbArticulo) {
            @Override
            public void populateViewHolder(final ProductoCompradorHolder productoHolder, Producto producto, int posicion) {
                productoHolder.setNombreArticulo(producto.getNombreProducto());
                productoHolder.setDescripcionArticulo(producto.getDescripcionProducto());
                productoHolder.setPrecioYUnidadDeVentaArticulo(
                        producto.getPrecioVenta() + " € x " + producto.getUnidadDeVenta() + " " + producto.getMedidaDeVenta()
                );
                productoHolder.setBotonBorrarArticulo();
                productoHolder.setBotonEditarArticulo(ListaArticulosCompradorActivity.this);
                productoHolder.setBotonProductoAgotado();
            }
        };
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }
}
