package co.com.hgr.cestadelacompra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import utilesbbdd.AyudanteBBDD;
import utilesbbdd.Container;

/**
 * Actividad para recger y guardar los datos de la tienda
 */
public class DatosTiendaActivity extends AppCompatActivity {


    //Boton para localizar el negocio en el mapa
    private Button btnLocalizar;
    //Boton para guardar el negocio
    private Button btnGuardar;
    //Campos datos tienda
    private EditText edNombreTienda;
    private EditText edIdDiasApertura;
    private EditText edIdHorasApertura;
    private EditText edEmailPedidos;
    private EditText edTelefonoTienda;
    private TextView edLocalizacion;
    //Para la BBDD
    AyudanteBBDD ayudanteBBDD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_tienda);
        finders();
        setListeners();

    }


    private void finders() {
        btnLocalizar = (Button) findViewById(R.id.localizar);
        btnGuardar = (Button) findViewById(R.id.guardar);

        edNombreTienda = (EditText) findViewById(R.id.nombre);
        edIdDiasApertura = (EditText) findViewById(R.id.dias_apertura);
        edIdHorasApertura = (EditText) findViewById(R.id.horario_apertura);
        edEmailPedidos = (EditText) findViewById(R.id.email_pedidos);
        edTelefonoTienda = (EditText) findViewById(R.id.telefono);
        edLocalizacion = (TextView) findViewById(R.id.localizacion);

    }

    private void setListeners(){
        setListenerBtnLocalizar();
        setListenerBtnGuardar();
    }


    private void setListenerBtnGuardar(){
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTiendaLogueada();

                ayudanteBBDD=new AyudanteBBDD();
                ayudanteBBDD.modoTiendas();
                ayudanteBBDD.aniadeUnaTienda(Container.tiendaLogueada);

                Intent intent = new Intent(DatosTiendaActivity.this, EleccionTiendaVendedorActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setListenerBtnLocalizar(){

        btnLocalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTiendaLogueada();
                Intent intent = new Intent(DatosTiendaActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTextoDireccion();
        setEditTexts();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setTextoDireccion();
    }

    private void setTextoDireccion(){
        edLocalizacion.setText(Container.tiendaLogueada.getDireccion());
    }

    /**
     * Metodo para poner los datos de la tienda en los EditText
     */
    private void setEditTexts() {
        edLocalizacion.setText(Container.tiendaLogueada.getDireccion());
        edNombreTienda.setText(Container.tiendaLogueada.getNombre());
        edTelefonoTienda.setText(Container.tiendaLogueada.getTelefono());
        edEmailPedidos.setText(Container.tiendaLogueada.getEmailPedidos());

        if (Container.tiendaLogueada.getHorario().isEmpty() || Container.tiendaLogueada.getHorario() != null) {
            String[] horarioYdias = Container.tiendaLogueada.getHorario().split("--");

            edIdDiasApertura.setText(horarioYdias[0]);
            edIdHorasApertura.setText(horarioYdias[1]);
        }
    }

    /**
     * Metodo para almacenar los datos de la tienda logueada
     */
    private void setTiendaLogueada(){
        Container.tiendaLogueada.setNombre(String.valueOf(edNombreTienda.getText()));
        Container.tiendaLogueada.setEmailPedidos(String.valueOf(edEmailPedidos.getText()));
        Container.tiendaLogueada.setHorario(String.valueOf(edIdDiasApertura.getText() + " -- " + String.valueOf(edIdHorasApertura.getText())));
        Container.tiendaLogueada.setTelefono(String.valueOf(edTelefonoTienda.getText()));
        Container.tiendaLogueada.setEmailDuenio(Container.personaLogueada.getEmail());
    }

}
