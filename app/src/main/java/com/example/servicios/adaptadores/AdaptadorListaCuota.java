package com.example.servicios.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.servicios.R;
import com.example.servicios.modelos.Cuotas;

import java.util.List;

public class AdaptadorListaCuota extends RecyclerView.Adapter<AdaptadorListaCuota.CuotaViewHolder>{
    private Context mCtx;
    private List<Cuotas> cuotasList;
    final AdaptadorListaCuota.onItemClickListener listener;

    public interface onItemClickListener{
        void onItemClick(Cuotas cuota);
    }

        public AdaptadorListaCuota(Context mCtx, List<Cuotas> cuotasList,AdaptadorListaCuota.onItemClickListener listener) {
        this.mCtx = mCtx;
        this.cuotasList = cuotasList;
        this.listener=listener;
    }

    @Override
    public CuotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_cuota,null);
        return new CuotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CuotaViewHolder holder, int position) {
        holder.bindData(cuotasList.get(position));
    }

    @Override
    public int getItemCount() {
        return cuotasList.size();
    }


    class CuotaViewHolder extends  RecyclerView.ViewHolder{
        TextView txtMes,txtAño,txtPago;

        public CuotaViewHolder( View itemView) {
            super(itemView);
            txtMes=itemView.findViewById(R.id.txtMes);
            txtAño=itemView.findViewById(R.id.txtAño);
            txtPago=itemView.findViewById(R.id.txtCuota);
        }
        void bindData(final Cuotas cuota){
            txtMes.setText(cuota.getMes());
            txtAño.setText(String.valueOf(cuota.getAño()));
            txtPago.setText(cuota.getCuota());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(cuota);
                }
            });
        }


    }

}
