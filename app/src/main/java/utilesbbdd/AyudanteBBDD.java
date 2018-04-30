package utilesbbdd;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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


    public AyudanteBBDD(){


        mAuth = FirebaseAuth.getInstance();
        usuario=mAuth.getCurrentUser();


    }

    public void modoPersonas(){
        dbUsuario= FirebaseDatabase.getInstance().getReference().child("personaUsuario");
    }
    public void aniadeUnaPersona(UsuarioPersona usuarioPersona){
        dbUsuario.push().setValue(usuarioPersona);
    }

    public void modoTiendas(){
        dbUsuario=FirebaseDatabase.getInstance().getReference().child("tienda");
    }
    public void aniadeUnaTienda(Tienda tienda){
        dbUsuario.push().setValue(tienda);
    }

    public void compruebaUsuarioLogueado(final Context context, final Intent intent){

        mAuthListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (firebaseAuth.getCurrentUser()!=null){
                    context.startActivity(intent);
                    Container.personaLogueada.setEmail(user.getEmail());
                    Container.personaLogueada.setTelefono(String.valueOf(user.getPhoneNumber().toString()));
                }
            }
        };
        mAuth.addAuthStateListener(mAuthListener);
    }


}
