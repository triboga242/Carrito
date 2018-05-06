package co.com.hgr.cestadelacompra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import modelos.Producto;
import utilesbbdd.AyudanteBBDD;

public class DatosProductoActivity extends AppCompatActivity {
    //Elementios de la vista
    private EditText nombreProducto;
    private EditText descripcionProducto;
    private EditText unidadDeVentaProducto;
    private EditText medidaDeVentaProducto;
    private EditText preciodeVentaProducto;
    private TextView textViewProductoDisponible;
    private Button btnProductoDisponible;
    private Button btnGuardarProducto;

    //Elementos para la bbdd
    AyudanteBBDD ayudanteBBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_producto);
        findElementosVista();
        setOnClickListeners();
        ayudanteBBDD= new AyudanteBBDD();
    }

    private void findElementosVista (){
        nombreProducto = (EditText) findViewById(R.id.et_nombre_producto);
        descripcionProducto = (EditText) findViewById(R.id.et_descripcion_producto);
        unidadDeVentaProducto = (EditText) findViewById(R.id.et_unidad_de_venta);
        medidaDeVentaProducto = (EditText) findViewById(R.id.et_medida_de_venta);
        preciodeVentaProducto = (EditText) findViewById(R.id.et_precio_de_venta);
        textViewProductoDisponible=(TextView) findViewById(R.id.tv_producto_disponible);
        textViewProductoDisponible.setText("Disponible");
        btnProductoDisponible=(Button) findViewById(R.id.btn_producto_disponible);
        btnGuardarProducto=(Button)findViewById(R.id.btn_guardar_producto);

    }

    private void setOnClickListeners(){

        btnProductoDisponible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textViewProductoDisponible.equals("Disponible")){
                    textViewProductoDisponible.setText("Agotado");
                }else{
                    textViewProductoDisponible.setText("Disponible");
                }
            }
        });

        btnGuardarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Producto producto = new Producto();
                producto.setNombreProducto(nombreProducto.getText().toString());
                producto.setDescripcionProducto(descripcionProducto.getText().toString());
                producto.setUnidadDeVenta(Integer.parseInt(unidadDeVentaProducto.getText().toString()));
                producto.setMedidaDeVenta(medidaDeVentaProducto.getText().toString());
                producto.setPrecioVenta(Float.parseFloat(preciodeVentaProducto.getText().toString()));
                if (textViewProductoDisponible.equals("Disponible")){
                    producto.setDisponible(true);
                }else {
                    producto.setDisponible(false);
                }
                ayudanteBBDD.aniadeUnProducto(producto);
            }
        });
    }
}


