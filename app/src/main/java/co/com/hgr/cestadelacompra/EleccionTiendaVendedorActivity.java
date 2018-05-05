package co.com.hgr.cestadelacompra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import holders.TiendaHolder;
import modelos.Tienda;
import utilesbbdd.AyudanteBBDD;
import utilesbbdd.Container;

public class EleccionTiendaVendedorActivity extends AppCompatActivity {

    private FirebaseRecyclerAdapter mAdapter;
    private DatabaseReference dbUsuario;
    private AyudanteBBDD ayudanteBBDD;

    private Button tiendaNueva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_tienda_vendedor);
        dbUsuario = FirebaseDatabase.getInstance().getReference().child("tienda").child(Container.personaLogueada.getEmailFB());
        ayudanteBBDD = new AyudanteBBDD();
        botonTiendaNueva();

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lista_tiendas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mAdapter =
                new FirebaseRecyclerAdapter<Tienda, TiendaHolder>(
                        Tienda.class, R.layout.lista_tienda, TiendaHolder.class, dbUsuario) {
                    @Override
                    public void populateViewHolder(final TiendaHolder tiendaHolder0, Tienda tienda0, int posicion) {
                        tiendaHolder0.setNombre(tienda0.getNombre());
                        tiendaHolder0.setHorario(tienda0.getHorario());
                        tiendaHolder0.setDireccion(tienda0.getDireccion());
                        tiendaHolder0.setBotonEditar();
                        tiendaHolder0.setBotonArticulosTienda();
                        tiendaHolder0.setBotonPedidosTienda();
                        tiendaHolder0.setBotonBorrarTienda();
                    }
                };
        recyclerView.setAdapter(mAdapter);
    }

    /**
     * Asigna el boton tienda nueva y su listener que llama a la actividad tienda nueva
     */
    public void botonTiendaNueva() {

        tiendaNueva = (Button) findViewById(R.id.tienda_nueva);
        tiendaNueva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llamaDatosTiendaActivity();
                Container.modoEditar=false;
            }
        });

    }

    /**
     * Llama a la actividad DatosTienda para meter una tienda nueva
     * o editar una tienda existente
     *
     */
    private void llamaDatosTiendaActivity() {
        Intent intent = new Intent(EleccionTiendaVendedorActivity.this, DatosTiendaActivity.class);
        startActivity(intent);
    }
}
