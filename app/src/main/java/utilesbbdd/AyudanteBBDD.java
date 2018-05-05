package utilesbbdd;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import modelos.Tienda;
import modelos.UsuarioPersona;

/**
 * Created by Triboga on 22/4/18.
 * Clase para el CRUD y demas utiles de la BBDD
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
        final DatabaseReference dbTienda = FirebaseDatabase.getInstance().getReference().child("tienda").child(Container.personaLogueada.getEmailFB());

        dbTienda.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Tienda tienda = postSnapshot.getValue(Tienda.class);

                    if (tienda.getNombre().equals(nombreTienda)) {
                        Container.tiendaLogueada = tienda;
                        Container.keyTiendaAeditar = postSnapshot.getKey();
                        Log.d("bucleValue", "Tienda logueada---------------------------------------------------");

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
                FirebaseDatabase.getInstance().getReference("articulo").child(Container.tiendaLogueada.getNombre());
        referenceCategoria.push().setValue(nombreCategoria);

    }
}
