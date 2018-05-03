package adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.com.hgr.cestadelacompra.R;
import interfaces.TiendaRecicleViewOnClickListener;
import modelos.Tienda;

/**
 * Created by Triboga on 1/5/18.
 */

public class TiendasReciclerViewAdapter extends RecyclerView.Adapter<TiendasReciclerViewAdapter.MyViewHolder>{

    private List<Tienda> data;
    private TiendaRecicleViewOnClickListener recyclerViewOnItemClickListener;

    public TiendasReciclerViewAdapter(@NonNull List<Tienda> data,
                                 @NonNull TiendaRecicleViewOnClickListener
                                         recyclerViewOnItemClickListener) {
        this.data = data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @Override
    public TiendasReciclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TiendasReciclerViewAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {
        private TextView nombreTienda;
        private TextView direccionTienda;
        private TextView horarioTienda;
        private Button articulos;
        private Button editar;


        public MyViewHolder(View itemView) {
            super(itemView);
            nombreTienda = (TextView) itemView.findViewById(R.id.txtvNombreTienda);
            direccionTienda = (TextView) itemView.findViewById(R.id.xtvDireccion);
            horarioTienda = (TextView) itemView.findViewById(R.id.txtvHorario);

            articulos = (Button)itemView.findViewById(R.id.btnArticulos);
            editar = (Button)itemView.findViewById(R.id.btnEditar);


            itemView.setOnClickListener(this);
        }

        public TextView getNombreTienda() {
            return nombreTienda;
        }

        public TextView getDireccionTienda() {
            return direccionTienda;
        }

        public TextView getHorarioTienda() {
            return horarioTienda;
        }

        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v, getAdapterPosition());
        }
    }
}
