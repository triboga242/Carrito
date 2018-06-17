package co.com.hgr.cestadelacompra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;

import utilesbbdd.Container;

/**
 * Elegir entre modo vendedor y modo comprador
 * TODO activar botonEditarTienda comprador
 * TODO Del botonEditarTienda vendedor ha de ir a una ventana donde muestre lista de las tiendas que tiene registradas y un botonEditarTienda de registrar nueva tienda y ahi a registrar tiendas o a lista de alimentos de la tienda seleccionada
 */
public class VendedorCompradorActivity extends AppCompatActivity {


    Button btnComprador;
    Button btnVendedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendedor_comprador);

        btnComprador = (Button) findViewById(R.id.Comprador);
        btnVendedor = (Button) findViewById(R.id.Vendedor);

        setListeners();
        Container.currentTocken= FirebaseInstanceId.getInstance().getToken();

        Log.d("**************+MYTOKEN", "+cBY1WoC-0BE:APA91bFk3cjNj9AgLNDi_4KO-FqSFcqPnp7thuyjhn9d4mc6RxCTb7wUHpFrMC0FKmWeW2lmwpXFpvuDww0lR8qiJbc-x0uqOl6QTmV_cDghxdqzDmgrwlZSVNGat7tHltjNc_Uam-Wh" + Container.currentTocken);

    }

    private void setListeners() {
        btnVendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VendedorCompradorActivity.this, EleccionTiendaVendedorActivity.class);
                startActivity(intent);
            }
        });

        btnComprador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VendedorCompradorActivity.this, MapsActivityTiendasExistentes.class);
                startActivity(intent);
            }
        });
    }
}
