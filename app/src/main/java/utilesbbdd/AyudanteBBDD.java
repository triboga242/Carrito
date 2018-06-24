package utilesbbdd;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import co.com.hgr.cestadelacompra.ListaArticulosCompradorActivity;
import modelos.Categoria;
import modelos.Item;
import modelos.LocationData;
import modelos.Pedido;
import modelos.Producto;
import modelos.Tienda;
import modelos.UsuarioPersona;

/**
 * Created by Triboga on 22/4/18.
 * Clase para los CRUD y demas utiles de la BBDD
 */

public class AyudanteBBDD {

    //Referencia de la bbdd
    private DatabaseReference dbUsuario;
    //Usuario logueado
    private FirebaseUser usuario;
    //usuario autenticado
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    /**
     * Constructor
     */
    public AyudanteBBDD() {

        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();
    }

    public void modoPersonas() {
        dbUsuario = FirebaseDatabase.getInstance().getReference().child("personaUsuario");
    }

    /**
     *
     * @param usuarioPersona
     */
    public void aniadeUnaPersona(UsuarioPersona usuarioPersona) {
        dbUsuario = FirebaseDatabase.getInstance().getReference().child("personaUsuario").child(usuario.getPhoneNumber());
        dbUsuario.push().setValue(usuarioPersona);
    }

    public void modoTiendas() {
        dbUsuario = FirebaseDatabase.getInstance().getReference().child("tienda");
    }

    /**
     * Aniade una tienda en la bbdd bajo un hijo de su dueño por si tiene mas de una tienda
     *
     * @param tienda a aniadir en la bbdd
     */
    public void aniadeUnaTienda(Tienda tienda) {
        dbUsuario = FirebaseDatabase.getInstance().getReference().child("tienda").child(Container.personaLogueada.getEmailFB());
        DatabaseReference dbLocalizacion= FirebaseDatabase.getInstance().getReference().child("location");

        LocationData locationData = new LocationData(Double.parseDouble(tienda.getLatitud()),Double.parseDouble(tienda.getLongitud()), tienda.getNombre(), tienda.getEmailDuenio());

        dbLocalizacion.push().setValue(locationData);
        dbUsuario.push().setValue(tienda);
    }

    /**
     * Guarda cambios en la tienda a editar
     *
     * @param tiendaAeditar tienda a editar
     */
    public void guardacambiosEnTienda(final Tienda tiendaAeditar) {
        final DatabaseReference dbTienda = FirebaseDatabase.getInstance().getReference().child("tienda").child(Container.personaLogueada.getEmailFB());

        dbTienda.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dbTienda.child(Container.keyTiendaAeditar).setValue(tiendaAeditar);
                Log.d("bucleValue", Container.keyTiendaAeditar + "key tienda---------------------------------------------------");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Buscar la tienda seleccionada a editar
     * setea en el container la tienda seleccionada
     *
     * @param nombreTienda nombre de la tienda a buscar
     */
    public void buscaTiendaSeleccionada(final String nombreTienda) {
        final DatabaseReference dbTienda = FirebaseDatabase.getInstance().getReference()
                .child("tienda")
                .child(Container.personaLogueada.getEmailFB());

        dbTienda.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Tienda tienda = postSnapshot.getValue(Tienda.class);

                    if (tienda.getNombre().equals(nombreTienda)) {
                        Container.tiendaLogueada = tienda;
                        Container.keyTiendaAeditar = postSnapshot.getKey();
                        Log.d("bucleValue", "Tienda logueada---------------------------------------------------" + Container.tiendaLogueada.toString());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    /**
     * Comprueba si el usuario esta atutenticado y autorizado y pasa a la siguiente actividad
     *
     * @param context contexto
     * @param intent  intento
     */
    public void compruebaUsuarioLogueadoYpasaAotraActividad(final Context context, final Intent intent) {

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (firebaseAuth.getCurrentUser() != null) {
                    context.startActivity(intent);
                    Container.personaLogueada.setEmail(user.getEmail());
                    Container.personaLogueada.setTelefono(String.valueOf(user.getPhoneNumber().toString()));
                }
            }
        };
        mAuth.addAuthStateListener(mAuthListener);
    }


    /**
     * Comprueba si el usuario esta logueado y autorizado
     *
     * @return true si esta autorizado, false si no lo esta
     */
    public Boolean compruebaUsuarioRegistrado() {

        final Boolean[] existe = {false};
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("personaUsuario").child(usuario.getPhoneNumber());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot == null) {
                    existe[0] = false;
                } else {
                    existe[0] = true;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return existe[0];
    }


    /**
     * Consulta en bbdd las tienda bajo el child de tu email de usuario logueado
     *
     * @return la lista de tiendas bajo el nodo del email del usuario logueado
     */
    public ArrayList<Tienda> traerTiendasPropias() {
        final List[] tiendasPropias = new List[]{new ArrayList<>()};

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("tienda").child(usuario.getEmail());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Tienda>> t = new GenericTypeIndicator<List<Tienda>>() {
                };

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    tiendasPropias[0] = dataSnapshot.getValue(t);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return (ArrayList<Tienda>) tiendasPropias[0];
    }


    /**
     * Aniade una categoria en la bbdd a la lista de categorias de la tienda seleccionada
     * @param nombreCategoria el nombre de la categoria a crear
     */
    public void aniadeUnaCategoria(String nombreCategoria){

        DatabaseReference referenceCategoria =
                FirebaseDatabase.getInstance().getReference("categoria").child(Container.tiendaLogueada.getNombre());
        referenceCategoria.push().setValue(nombreCategoria);

    }


    /**
     * Buscar la Categoria seleccionada a editar
     * setea en el container la categoria seleccionada
     *
     * @param nombreCategoria nombre de la categoria a buscar
     */
    public void buscaCategoriaSeleccionada(final String nombreCategoria) {
        final DatabaseReference dbProducto = FirebaseDatabase.getInstance().getReference().child("categoria")
                .child(Container.tiendaLogueada.getNombre())
                ;

        dbProducto.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String categoria = postSnapshot.getValue(String.class);

                    if (categoria.equals(nombreCategoria)) {
                        Container.keyCategoriaSeleccionada = postSnapshot.getKey();
                        Log.d("bucleValue", "Categoria seleccionada---------------------------------------------------");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Aniade un producto a la bbdd bajo la categoria y tienda seleccionada
     * @param producto a guardar
     */
    public void aniadeUnProducto(Producto producto) {
        final DatabaseReference dbProducto = FirebaseDatabase.getInstance().getReference()
                .child("articulo")
                .child(Container.tiendaLogueada.getNombre())
                .child(Container.categoriaProductoAGuardar);

        dbProducto.push().setValue(producto);

        final DatabaseReference dbProductoRaw = FirebaseDatabase.getInstance().getReference()
                .child("articuloraw")
                .child(Container.tiendaLogueada.getNombre());
        dbProductoRaw.push().setValue(producto);

    }


    /**
     * Aniade al pedido en curso el producto seleccionado en BBDD
     *
     * @param producto  producto
     */
    public void aniadeUnProductoAPedidoEnCurso(Producto producto, Context context) {

        buscaProductoSeleccionadoYaniade(producto, context);

    }

    /**
     *Busca el producto seleccionado en BBDD
     *
     *
     */
    public void buscaProductoSeleccionadoYaniade(final Producto productoABuscar, final Context context) {

        final DatabaseReference dbProductoRaw = FirebaseDatabase.getInstance().getReference()
                .child("articuloraw")
                .child(Container.tiendaLogueada.getNombre())
                ;

        dbProductoRaw.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean existe=false;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Producto producto = postSnapshot.getValue(Producto.class);
                    if (producto.getNombreProducto().equals(productoABuscar.getNombreProducto())
                            && producto.getDescripcionProducto().equals(productoABuscar.getDescripcionProducto())) {
                        Log.d("bucleValue", "Producto seleccionado---------------------------------------------------" + producto.toString());
                        for (Item item : Container.pedidoEnCurso.getItems()) {
                            if (item.getProducto().getNombreProducto().equals(productoABuscar.getNombreProducto())
                                    && item.getProducto().getDescripcionProducto().equals(productoABuscar.getDescripcionProducto())) {
                                existe = true;
                                item.setCantidad(item.getCantidad() + 1);
                                Log.d("bucleValue", "Cantidad aumentada" +
                                        "---------------------------------------------------" + item.toString());
                                Toast.makeText(context, "Aumentado" + item.toString(), Toast.LENGTH_SHORT ).show();
                            }
                        }
                    }
                }
                if (!existe){
                    Container.pedidoEnCurso.getItems().add(new Item(1, productoABuscar));
                    Log.d("bucleValue", "Producto aniadido a pedido en curso" +
                            "---------------------------------------------------" + productoABuscar.toString());
                    Toast.makeText(context, "Aniadido" + productoABuscar.getNombreProducto(), Toast.LENGTH_SHORT ).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void recargaProductosPedido(){

        Container.pedidoEnCurso.getItems();


    }

    /**
     * Aniade un pedido a la bbdd
     * @param pedidoEnCurso
     */
    public void aniadeUnPedido(Pedido pedidoEnCurso) {

        final DatabaseReference dbPedido=FirebaseDatabase.getInstance().getReference()
                .child("pedido")
                .child(Container.personaLogueada.getEmailFB() + " -- " + Container.tiendaLogueada.getEmailPedidosFB());
        Container.precioTotalPedidoEnCurso=0f;
        for (Item item:Container.pedidoEnCurso.getItems()){
            Container.precioTotalPedidoEnCurso+=item.getCantidad()*item.getProducto().getPrecioVenta();
        }

        Date ahora = Calendar.getInstance().getTime();
        pedidoEnCurso.setFechaPedido(ahora.toString());
        pedidoEnCurso.setPedidoA(Container.tiendaLogueada.getEmailPedidosFB());


        dbPedido.push().setValue(pedidoEnCurso);
    }
}
