package com.example.servicios.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.servicios.DescriptionConferenciasActivity;
import com.example.servicios.R;
import com.example.servicios.modelos.Conferencia;

import java.util.List;

public class AdaptadorListaConferencia extends RecyclerView.Adapter<AdaptadorListaConferencia.ViewHolder> {
    private List<Conferencia> listConference;
    private Context context;


    public AdaptadorListaConferencia(Context context, List<Conferencia> listConference) {
        this.context = context;
        this.listConference = listConference;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_conferencia, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorListaConferencia.ViewHolder holder, int position) {
        Conferencia conference = listConference.get(position);
        Glide.with(context)
                .load(conference.getUrl())
                .into(holder.url);
        holder.name.setText(conference.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent descripcion = new Intent(holder.itemView.getContext(), DescriptionConferenciasActivity.class);
                descripcion.putExtra("idConf", String.valueOf(conference.getId()));
                holder.itemView.getContext().startActivity(descripcion);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listConference.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView url;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTextView);
            url = itemView.findViewById(R.id.iconImageView);
        }
    }
}
