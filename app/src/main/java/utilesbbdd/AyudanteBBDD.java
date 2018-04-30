package utilesbbdd;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import modelos.Tienda;
import modelos.UsuarioPersona;

/**
 * Created by Triboga on 22/4/18.
 */

public class AyudanteBBDD {

    private DatabaseReference dbUsuario;

    public AyudanteBBDD(){

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
}
