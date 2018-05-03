package fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import co.com.hgr.cestadelacompra.R;
import modelos.Tienda;
import utilesbbdd.AyudanteBBDD;

/**
 * Created by Triboga on 1/5/18.
 */

public class TiendasPropiasFragment extends android.support.v4.app.ListFragment implements AdapterView.OnItemClickListener{


    //Lista de tiendas propias
    ArrayList<Tienda>tiendasPropias;
    ArrayList<String>tiendasPropiasString;

    //Para la bbdd
    AyudanteBBDD ayudanteBBDD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lista_tiendas_propias_fragment, container, false);
        traerTiendaPropias();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter <String> adapter = new ArrayAdapter(getActivity(),
                R.layout.tienda_lista, R.id.nombre, tiendasPropiasString);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }

    /**
     * Guarda en la lista las tiendas propias para mostrarlas
     */
    public void traerTiendaPropias(){
        tiendasPropias = new ArrayList<>();
        tiendasPropias = ayudanteBBDD.traerTiendasPropias();

        for (Tienda t:tiendasPropias){

            tiendasPropiasString.add(t.getNombre());
        }

    }
}
