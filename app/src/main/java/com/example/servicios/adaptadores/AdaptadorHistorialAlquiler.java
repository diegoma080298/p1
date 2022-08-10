package com.example.servicios.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.servicios.R;
import com.example.servicios.modelos.AlquilerHistorial;

import java.util.List;

public class AdaptadorHistorialAlquiler extends RecyclerView.Adapter<AdaptadorHistorialAlquiler.AlquilerViewHolder>{

    private Context mCtx;
    private List<AlquilerHistorial> alquilerHistorialList;

    public AdaptadorHistorialAlquiler(Context mCtx, List<AlquilerHistorial> alquilerHistorialList) {
        this.mCtx = mCtx;
        this.alquilerHistorialList = alquilerHistorialList;
    }

    @Override
    public AdaptadorHistorialAlquiler.AlquilerViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_historial_alquiler,null);
        return new AdaptadorHistorialAlquiler.AlquilerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorHistorialAlquiler.AlquilerViewHolder holder, int position){
        AlquilerHistorial alquiler = alquilerHistorialList.get(position);
        holder.txtFechaPago.setText(alquiler.getFechaPago());
        holder.txtNombreAlq.setText(alquiler.getNombreAmbiente());
        holder.txtFechaAlquiler.setText(alquiler.getFechaAlq());
        holder.txtHoraAlq.setText(alquiler.getHoraInicio());
        holder.txtMonto.setText("S/. "+alquiler.getMonto());
        holder.txtHoras.setText(alquiler.getHoras());
    }

    @Override
    public int getItemCount(){
        return alquilerHistorialList.size();
    }

    class AlquilerViewHolder extends RecyclerView.ViewHolder{
        TextView txtFechaPago,txtFechaAlquiler,txtNombreAlq,txtHoraAlq,txtMonto,txtHoras;
        public AlquilerViewHolder(View itemView){
            super(itemView);
            txtFechaPago=itemView.findViewById(R.id.idTextFechaPago);
            txtFechaAlquiler=itemView.findViewById(R.id.idTextFechaAlq);
            txtNombreAlq=itemView.findViewById(R.id.idTextNombreAlq);
            txtHoraAlq=itemView.findViewById(R.id.idTextHoraAlq);
            txtHoras=itemView.findViewById(R.id.idTextCantidadHoras);
            txtMonto=itemView.findViewById(R.id.idTextMontoPago);
        }

    }



}
