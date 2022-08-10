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
import com.example.servicios.modelos.Convenio;

import java.util.List;

public class AdaptadorListaConvenio extends RecyclerView.Adapter<AdaptadorListaConvenio.ViewHolder> {
    private List<Convenio> listConvenio;
    private Context context;


    public AdaptadorListaConvenio(Context context, List<Convenio> listConvenio) {

        this.context = context;
        this.listConvenio = listConvenio;


    }

    @NonNull
    @Override
    public AdaptadorListaConvenio.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_convenio, null);
        return new AdaptadorListaConvenio.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorListaConvenio.ViewHolder holder, int position) {
        Convenio convenio = listConvenio.get(position);

        Glide.with(context)
                .load(convenio.getImg())
                .into(holder.img);
        holder.empresa.setText(convenio.getEmpresa());
        holder.fecha.setText(convenio.getFecha());
        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri Pdf = Uri.parse(convenio.getLink());
                Intent pdf = new Intent(Intent.ACTION_VIEW,Pdf);
                holder.link.getContext().startActivity(pdf);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listConvenio.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView empresa,fecha;
        Button link;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            empresa = itemView.findViewById(R.id.empresaTxt);
            fecha = itemView.findViewById(R.id.fechaTxt);
            link = itemView.findViewById(R.id.PDF);
            img = itemView.findViewById(R.id.imageView);
        }
    }
}
