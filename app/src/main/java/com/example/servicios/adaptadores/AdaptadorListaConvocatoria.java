package com.example.servicios.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.servicios.R;
import com.example.servicios.modelos.Convocatoria;

import java.util.List;

public class AdaptadorListaConvocatoria extends RecyclerView.Adapter<AdaptadorListaConvocatoria.ViewHolder> {
    private List<Convocatoria> listConvocatoria;
    private Context context;


    public AdaptadorListaConvocatoria(Context context, List<Convocatoria> listConvocatoria) {

        this.context = context;
        this.listConvocatoria = listConvocatoria;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_convocatoria, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorListaConvocatoria.ViewHolder holder, int position) {
        Convocatoria convocatoria = listConvocatoria.get(position);

        Glide.with(context)
                .load(convocatoria.getImg())
                .into(holder.img);
        holder.asunto.setText(convocatoria.getAsunto());
        holder.fecha.setText(convocatoria.getVigencia());
        holder.institucion.setText(convocatoria.getInstitucion());
        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri Pdf = Uri.parse(convocatoria.getLink());
                Intent pdf = new Intent(Intent.ACTION_VIEW,Pdf);
                holder.link.getContext().startActivity(pdf);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listConvocatoria.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView asunto,fecha,institucion;
        Button link;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            asunto = itemView.findViewById(R.id.asuntoTxt);
            fecha = itemView.findViewById(R.id.fechaTxt);
            link = itemView.findViewById(R.id.PDF);
            institucion = itemView.findViewById(R.id.institucionTxt);
            img = itemView.findViewById(R.id.imageView);
        }
    }
}
