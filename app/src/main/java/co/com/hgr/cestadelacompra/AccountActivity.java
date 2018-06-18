package co.com.hgr.cestadelacompra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import holders.UsuarioHolder;
import modelos.UsuarioPersona;
import utilesbbdd.AyudanteBBDD;

public class AccountActivity extends AppCompatActivity {

    Button logOutBtn;

    private TextView nombre;
    private TextView apellidos;
    private TextView email;
    private Button botonEliminarListener;
    private Button botonAniadir;

    private DatabaseReference dbUsuario;
    private ValueEventListener eventListener;

    //Autenticaion de usuario
    private FirebaseAuth mAuth;
    private FirebaseUser usuario;

    //Ayudante para la bbdd
    private AyudanteBBDD ayudanteBBDD;


    private FirebaseAuth.AuthStateListener mAuthListener;

    private FirebaseRecyclerAdapter mAdapter;

    private static final String TAGLOG="firebase-db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        nombre= (TextView) findViewById(R.id.lblNombre);
        apellidos= (TextView) findViewById(R.id.lblApellidos);
        email= (TextView) findViewById(R.id.lblEmail);
        botonEliminarListener=(Button) findViewById(R.id.botonEliminarListener);
        botonAniadir=(Button)findViewById(R.id.button2);
        dbUsuario= FirebaseDatabase.getInstance().getReference().child("personaUsuario");

        mAuth = FirebaseAuth.getInstance();
        usuario=mAuth.getCurrentUser();

        ayudanteBBDD=new AyudanteBBDD();
        ayudanteBBDD.modoPersonas();



      if (ayudanteBBDD.compruebaUsuarioRegistrado()){
          Intent intent = new Intent(AccountActivity.this, VendedorCompradorActivity.class);
           startActivity(intent);
      }

        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.lstUsuarios);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter=
        new FirebaseRecyclerAdapter<UsuarioPersona, UsuarioHolder>(
                UsuarioPersona.class, R.layout.lista_item, UsuarioHolder.class, dbUsuario){
            @Override
                    public void populateViewHolder(UsuarioHolder usarioHolder0, UsuarioPersona usuario0, int posicion){
                usarioHolder0.setUsuario(dbUsuario.child("personaUsuario").getKey().toString());
                usarioHolder0.setNombre(usuario0.getNombre());
                usarioHolder0.setApellidos(usuario0.getApellidos());
                usarioHolder0.setEmail(usuario.getEmail());


            }
        };

        botonAniadir.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UsuarioPersona persona=new UsuarioPersona("jeray", "juanez juanez", "juan@juanez.com", "66666666");
                UsuarioPersona persona2=new UsuarioPersona("moises", "juanez juanez", "juan@juanez.com", "111111111");
                UsuarioPersona persona3=new UsuarioPersona("abraham", "juanez juanez", "juan@juanez.com", "222222222");

                dbUsuario.push().setValue(persona);
                dbUsuario.push().setValue(persona2);
                dbUsuario.push().setValue(persona3);

            }
        });
        recyclerView.setAdapter(mAdapter);




        logOutBtn = (Button)findViewById(R.id.logOutBtn);
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseAuth.getInstance().signOut();


                Intent intent = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mAdapter.cleanup();
    }
}
