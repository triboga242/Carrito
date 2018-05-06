package co.com.hgr.cestadelacompra;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import modelos.LocationData;
import utilesbbdd.Container;

public class MapsActivityTiendasExistentes extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener, LocationListener {

    //Mapa
    private GoogleMap mMap;

    //Marcador posicion actual
    private Marker markerAhora;
    private MarkerOptions markerOptions;
    private Address direccionAmostrarAdress;

    //Utiles para guardar y mostrar la direccion
    private ArrayList<Address> direccionCompleta;

    //Latitud y longitud actual
    private LatLng latlongAhora;

    //Base de datos
    private DatabaseReference mDatabase;

    //Elementos de la vista
    private TextView direccion;
    private EditText buscarDireccion;
    private Button btnBuscarDireccion;
    private Button btnGuardarDireccion;
    private String direccionAmostrar;

    //Geolocalodaor para las direcciones
    private Geocoder geocoder;

    //Listener para el arrastre del marcador
    private GoogleMap.OnMarkerDragListener onMarkerDragListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Cambiar el layout o al menos los listeners
         */
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setElementosVista();

        startGettingLocations();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        getMarkers();


        geocoder = new Geocoder(this);

    }

    /***
     * busca y asigna los elementos de la vista
     */
    public void setElementosVista(){

        direccion = (TextView) findViewById(R.id.textView);
        buscarDireccion = (EditText) findViewById(R.id.editText);
        btnBuscarDireccion=(Button) findViewById(R.id.button4);
        btnGuardarDireccion=(Button)findViewById(R.id.button5);

    }

    /**
     * Inicializa y asigna los listeners
     */
    public void setListeners(){

        btnBuscarDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarLaDireccion();
            }
        });


    }

    /**
     * Busca el codigo postal introducido
     *
     * TODO Aceptar solo numeros, validar codigo postal poner que solo sea españa
     *
     *
     */
    private void buscarLaDireccion(){

        direccionCompleta=null;
        String direccionAbuscar= buscarDireccion.getText().toString();

        if (direccionAbuscar!=null || !direccionAbuscar.equals("")){
            try {
                direccionCompleta= (ArrayList<Address>) geocoder.getFromLocationName(direccionAbuscar, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        direccionAmostrarAdress = direccionCompleta.get(0);
        LatLng localizacionAmostrar = new LatLng(direccionAmostrarAdress.getLatitude(), direccionAmostrarAdress.getLongitude());
        markerAhora.remove();
        markerOptions=new MarkerOptions();
        markerOptions.position(localizacionAmostrar);
        markerOptions.draggable(true);
        markerAhora.setTitle(direccionCompleta.get(0).getAddressLine(0));


        markerAhora = mMap.addMarker(markerOptions);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(localizacionAmostrar));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        setListeners();


        mMap.setMyLocationEnabled(true);

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setAllGesturesEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMapToolbarEnabled(true);

    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onLocationChanged(Location location) {

/*
        if (markerAhora != null) {
            markerAhora.remove();
        }
        latlongAhora = new LatLng(location.getLatitude(), location.getLongitude());
        markerOptions = new MarkerOptions();
        markerOptions.position(latlongAhora);
        markerOptions.title("Ahora");
        markerOptions.draggable(true);
        markerAhora = mMap.addMarker(markerOptions);

        CameraPosition cameraPosition = new CameraPosition.Builder().zoom(15).target(latlongAhora).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        direccion.setText(String.valueOf(location.getLatitude()));
        */
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    private ArrayList findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList result = new ArrayList();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canAskPermission()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }


    private void startGettingLocations() {

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        boolean isGPS = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetwork = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean canGetLocation = true;
        int ALL_PERMISSIONS_RESULT = 101;
        long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;// Distance in meters
        long MIN_TIME_BW_UPDATES = 1000 * 10;// Time in milliseconds

        ArrayList<String> permissions = new ArrayList<>();
        ArrayList<String> permissionsToRequest;

        permissions.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionsToRequest = findUnAskedPermissions(permissions);

        //Check if GPS and Network are on, if not asks the user to turn on
        if (!isGPS && !isNetwork) {
            //showSettingsAlert();
        } else {
            // check permissions

            // check permissions for later versions
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (permissionsToRequest.size() > 0) {
                    requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]),
                            ALL_PERMISSIONS_RESULT);
                    canGetLocation = false;
                }
            }
        }
        //Checks if FINE LOCATION and COARSE Location were granted
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            return;
        }

        //Starts requesting location updates
        if (canGetLocation) {
            if (isGPS) {
                lm.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

            } else if (isNetwork) {
                // from Network Provider

                lm.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

            }
        } else {
            Toast.makeText(this, "No es posible obtener la localizacion", Toast.LENGTH_SHORT).show();
        }
    }

    private void getMarkers() {

        mDatabase.child("location").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        if (dataSnapshot.getValue() != null) {
                        }
                        getAllLocations((Map<String,Object>) dataSnapshot.getValue());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
    }

    private void getAllLocations(Map<String, Object> locations) {

        for (Map.Entry<String, Object> entry : locations.entrySet()) {
            Map singleLocation = (Map) entry.getValue();
            LatLng latLng = new LatLng((Double) singleLocation.get("latitud"), (Double) singleLocation.get("longitud"));
            addGreenMarker(singleLocation.get("nombreTienda").toString(), latLng);
        }
    }

    private void addGreenMarker(String newDate, LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(newDate);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(markerOptions);
    }

}
