package com.example.servicios.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servicios.R;
import com.example.servicios.modelos.Comunicado;

import java.util.List;

public class AdaptadorListaComunicado extends RecyclerView.Adapter<AdaptadorListaComunicado.ViewHolder>{
    private List<Comunicado> listComunicado;
    private Context context;


    public AdaptadorListaComunicado(Context context, List<Comunicado> listComunicado) {
        this.context = context;
        this.listComunicado = listComunicado;
    }

    @NonNull
    @Override
    public AdaptadorListaComunicado.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(com.example.servicios.R.layout.list_comunicado, null);
        return new AdaptadorListaComunicado.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorListaComunicado.ViewHolder holder, int position) {
        Comunicado comunicado = listComunicado.get(position);

        holder.asunto.setText(comunicado.getAsunto());
        holder.fecha.setText(comunicado.getFecha());
        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri Pdf = Uri.parse(comunicado.getLink());
                Intent pdf = new Intent(Intent.ACTION_VIEW,Pdf);
                holder.link.getContext().startActivity(pdf);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listComunicado.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView asunto,fecha;
        Button link;

        public ViewHolder(View itemView) {
            super(itemView);
            asunto = itemView.findViewById(R.id.asuntoTxt);
            fecha = itemView.findViewById(R.id.fechaTxt);
            link = itemView.findViewById(R.id.PDF);
        }
    }
}
