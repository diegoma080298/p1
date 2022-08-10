package com.example.servicios.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.servicios.R;
import com.example.servicios.modelos.CuotasHistorial;

import java.util.List;

public class AdaptadorHistorialCuota extends RecyclerView.Adapter<AdaptadorHistorialCuota.CuotaViewHolder>{

    private Context mCtx;
    private List<CuotasHistorial> cuotasHistorialList;

    public AdaptadorHistorialCuota(Context mCtx, List<CuotasHistorial> cuotasHistorialList) {
        this.mCtx = mCtx;
        this.cuotasHistorialList = cuotasHistorialList;
    }

    @Override
    public AdaptadorHistorialCuota.CuotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_cuotapagadas,null);
        return new AdaptadorHistorialCuota.CuotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorHistorialCuota.CuotaViewHolder holder, int position) {
        CuotasHistorial cuota = cuotasHistorialList.get(position);
        holder.txtFechaPago.setText(cuota.getFechaPago());
        holder.txtMes.setText(cuota.getMes());
        holder.txtAño.setText(cuota.getPeriodo());
        holder.txtMonto.setText("S/. "+cuota.getMonto());
    }

    @Override
    public int getItemCount() {
        return cuotasHistorialList.size();
    }

    class CuotaViewHolder extends  RecyclerView.ViewHolder{
        TextView txtFechaPago,txtMes,txtAño,txtMonto;


        public CuotaViewHolder( View itemView) {
            super(itemView);
            txtFechaPago=itemView.findViewById(R.id.idTextFechaPago);
            txtMes=itemView.findViewById(R.id.idTextMesPago);
            txtAño=itemView.findViewById(R.id.idTextAnnoPago);
            txtMonto=itemView.findViewById(R.id.idTextMontoPago);

        }
    }
}
