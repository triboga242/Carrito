package utilesbbdd;

import android.content.ClipData;
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
import java.util.List;

import co.com.hgr.cestadelacompra.DatosCompradorActivity;
import co.com.hgr.cestadelacompra.VendedorCompradorActivity;
import modelos.Tienda;
import modelos.UsuarioPersona;

/**
 * Created by Triboga on 22/4/18.
 */

public class AyudanteBBDD {

    private DatabaseReference dbUsuario;
    private FirebaseUser usuario;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    private ArrayList<UsuarioPersona> usuarios;

    public AyudanteBBDD() {

        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();

        usuarios = new ArrayList<>();
    }

    public void modoPersonas() {
        dbUsuario = FirebaseDatabase.getInstance().getReference().child("personaUsuario");
    }

    public void aniadeUnaPersona(UsuarioPersona usuarioPersona) {
        dbUsuario = FirebaseDatabase.getInstance().getReference().child("personaUsuario").child(usuario.getPhoneNumber());
        dbUsuario.push().setValue(usuarioPersona);
    }

    public void modoTiendas() {
        dbUsuario = FirebaseDatabase.getInstance().getReference().child("tienda");
    }

    public void aniadeUnaTienda(Tienda tienda) {
        String usuarioSinPuntos = usuario.getEmail().replaceAll("\\.", "_");
        dbUsuario = FirebaseDatabase.getInstance().getReference().child("tienda").child(usuarioSinPuntos);

        dbUsuario.push().setValue(tienda);
    }

    public void compruebaUsuarioLogueado(final Context context, final Intent intent) {

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

    public Boolean compruebaTiendaRegistrada() {

        final Boolean[] existe = {false};
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("tienda").child(usuario.getEmail());
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

    public ArrayList<Tienda> traerTiendasPropias(){
        final List[] tiendasPropias = new List[]{new ArrayList<>()};

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("tienda").child(usuario.getEmail());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Tienda>> t = new GenericTypeIndicator<List<Tienda>>() {};

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                        tiendasPropias[0] =dataSnapshot.getValue(t);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return (ArrayList<Tienda>) tiendasPropias[0];
    }
}
