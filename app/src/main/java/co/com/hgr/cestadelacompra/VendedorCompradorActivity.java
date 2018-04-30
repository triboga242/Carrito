package co.com.hgr.cestadelacompra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Elegir entre modo vendedor y modo comprador
 * TODO activar boton comprador
 * TODO Del boton comprador ha de ir a una ventana donde muestre lista de las tiendas que tiene registradas y un boton de registrar nueva tienda y ahi a registrar tiendas o a lista de alimentos de la tienda seleccionada
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
    }

    private void setListeners() {
        btnVendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VendedorCompradorActivity.this, DatosTiendaActivity.class);
                startActivity(intent);
            }
        });
    }
}
