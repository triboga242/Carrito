package pedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.com.hgr.cestadelacompra.ListaCategoriasClienteActivity;
import co.com.hgr.cestadelacompra.MapsActivityTiendasExistentes;
import co.com.hgr.cestadelacompra.R;
import holders.ProductoCompradorHolder;
import modelos.Producto;
import utilesbbdd.AyudanteBBDD;
import utilesbbdd.Container;

public class DatosPedidoClienteActivity extends AppCompatActivity {


    //Elementos de la vista
    private TextView tvNombreTienda;
    private TextView tvDireccionTienda;
    private TextView tvPrecioTotalPedido;

    private Button btnGuardarPedido;
    private Button btnReiniciaPedido;
    private Button btnAniadeArticuloAPedido;

    private ListView listaArticulosEnCesta;


    //Elementos de la bbdd
    private AyudanteBBDD ayudanteBBDD;
    private FirebaseRecyclerAdapter mAdapter;
    private DatabaseReference dbArticulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_pedido_cliente);
        ayudanteBBDD = new AyudanteBBDD();
        findElementosVista();
        setReciclerView();
        setListenersBotones();
        setDatosTienda();
    }

    /**
     * Asigna elementos de la vista:
     *     TextView tvNombreTienda
     *     TextView tvDireccionTienda
     *     TextView tvPrecioTotalPedido
     *     Boton btnAniadeArticuloAPedido
     *     Boton btnReiniciaPedido
     *     Boton btnGuardarPedido
     *
     */
    private void findElementosVista(){

        tvNombreTienda=(TextView)findViewById(R.id.tv_nombre_tienda);
        tvDireccionTienda=(TextView)findViewById(R.id.tv_direccion_tienda);
        tvPrecioTotalPedido=(TextView)findViewById(R.id.tv_precio_total_pedido);

        btnAniadeArticuloAPedido=(Button)findViewById(R.id.btnAniadirProducto);
        btnReiniciaPedido =(Button)findViewById(R.id.btnReiniciarPedido);
        btnGuardarPedido=(Button)findViewById(R.id.bntConfirmarPedido);
        btnGuardarPedido.setText("Confirma");
        btnAniadeArticuloAPedido.setText("Categorias");

        btnReiniciaPedido.setVisibility(View.GONE);


        listaArticulosEnCesta=(ListView)findViewById(R.id.lista_productos_pedido);
    }

    /**
     * Pone texto a los Textview de los datos de la tienda
     */
    private void setDatosTienda(){
        ayudanteBBDD.buscaTiendaSeleccionada(Container.tiendaLogueada.getNombre());

        tvNombreTienda.setText(Container.tiendaLogueada.getNombre());
        tvDireccionTienda.setText(Container.tiendaLogueada.getDireccion());
        tvPrecioTotalPedido.setText(String.valueOf(Container.precioTotalPedidoEnCurso) + " €");
    }


    /**
     * Crea y asigna los listener de los botones
     */
    private void setListenersBotones(){
        btnAniadeArticuloAPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DatosPedidoClienteActivity.this, ListaCategoriasClienteActivity.class);
                startActivity(intent);
            }
        });

        btnGuardarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ayudanteBBDD.aniadeUnPedido(Container.pedidoEnCurso);
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
/*
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lista_productos_pedido);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new FirebaseRecyclerAdapter<Producto, ProductoCompradorHolder>(
                Producto.class, R.layout.lista_articulo, ProductoCompradorHolder.class, dbArticulo) {
            @Override
            public void populateViewHolder(final ProductoCompradorHolder productoHolder, Producto producto, int posicion) {
                productoHolder.setNombreArticulo(producto.getNombreProducto());
                productoHolder.setDescripcionArticulo(producto.getDescripcionProducto());
                productoHolder.setPrecioYUnidadDeVentaArticulo(
                        producto.getPrecioVenta() + " x " + producto.getUnidadDeVenta()
                );
                productoHolder.setBotonBorrarArticulo();
                productoHolder.setBotonEditarArticulo(DatosPedidoClienteActivity.this);
                productoHolder.setBotonProductoAgotado();
            }
        };
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
   */

        listaArticulosEnCesta.setAdapter(new AdaptadorListaArticulosEnLaCesta(this));




    }

    @Override
    protected void onResume() {
        super.onResume();
        setDatosTienda();
    }
}
