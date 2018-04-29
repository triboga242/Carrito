package co.com.hgr.cestadelacompra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class DatosTiendaActivity extends AppCompatActivity implements OnMapReadyCallback {


    private Button btnLocalizar;

//    private FusedLocationProviderClient mFusedLocationClient;



    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_tienda);

        //MapFragment mapFragment = (MapFragment) getFragmentManager()
        //        .findFragmentById(R.id.mapa);
        //mapFragment.getMapAsync(this);

        setListenerBtnLocalizar();
    }

    private void setListenerBtnLocalizar(){
        btnLocalizar=(Button)findViewById(R.id.localizar);

        btnLocalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DatosTiendaActivity.this, MapsActivity.class);
                startActivity(intent);


            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
