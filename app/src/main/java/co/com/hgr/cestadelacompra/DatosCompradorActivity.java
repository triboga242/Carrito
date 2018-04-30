package co.com.hgr.cestadelacompra;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import modelos.UsuarioPersona;
import utilesbbdd.AyudanteBBDD;
import utilesbbdd.Container;

/**
 * TODO
 * Preguntar si el email del usuario ya está en la bbdd, si es así, pasar directamente a la
 * actividad de lista de tiendas.
 *
 */
public class DatosCompradorActivity extends AppCompatActivity {


    private EditText nombre;
    private EditText apellidos;
    private EditText telefono;
    private Button guardar;

    private FirebaseUser usuario;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private AyudanteBBDD ayudanteBBDD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_comprador);

        nombre = (EditText) findViewById(R.id.campoNombre);
        apellidos = (EditText) findViewById(R.id.campoApellidos);
        telefono = (EditText) findViewById(R.id.campoTelefono);
        guardar = (Button) findViewById(R.id.Guardar);

        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();

        ayudanteBBDD = new AyudanteBBDD();

        ayudanteBBDD.modoPersonas();
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuarioPersona persona = new UsuarioPersona(
                        nombre.getText().toString(),
                        apellidos.getText().toString(),
                        usuario.getEmail(),
                        telefono.getText().toString());
                ayudanteBBDD.aniadeUnaPersona(persona);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


        //Comentar para que no la salte automaticamente
        ayudanteBBDD.compruebaUsuarioLogueado(this,
                new Intent(DatosCompradorActivity.this,
                        VendedorCompradorActivity.class));

    }
}
