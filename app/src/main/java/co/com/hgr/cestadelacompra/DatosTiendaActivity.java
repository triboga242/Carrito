package co.com.hgr.cestadelacompra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import utilesbbdd.AyudanteBBDD;
import utilesbbdd.Container;

/**
 * Actividad para recger y guardar los datos de la tienda
 * TODO recoger y guardar en bbdd la tienda
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

    }

    private void setListeners(){
        setListenerBtnLocalizar();
        setListenerBtnGuardar();
    }


    private void setListenerBtnGuardar(){
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Container.tiendaLogueada.setNombre(String.valueOf(edNombreTienda.getText()));
                Container.tiendaLogueada.setEmailPedidos(String.valueOf(edEmailPedidos.getText()));
                Container.tiendaLogueada.setHorario(String.valueOf(edIdDiasApertura.getText() + " -- " + String.valueOf(edIdHorasApertura.getText())));
                Container.tiendaLogueada.setTelefono(String.valueOf(edTelefonoTienda.getText()));

                ayudanteBBDD=new AyudanteBBDD();
                ayudanteBBDD.modoTiendas();
                ayudanteBBDD.aniadeUnaTienda(Container.tiendaLogueada);
            }
        });
    }

    private void setListenerBtnLocalizar(){

        btnLocalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DatosTiendaActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
