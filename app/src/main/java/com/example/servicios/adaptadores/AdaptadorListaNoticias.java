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
import com.example.servicios.DescriptionNoticiasActivity;
import com.example.servicios.R;
import com.example.servicios.modelos.Noticias;

import java.util.List;

public class AdaptadorListaNoticias extends RecyclerView.Adapter<AdaptadorListaNoticias.ViewHolder> {
    private List<Noticias> listNoticias;
    private Context context;


    public AdaptadorListaNoticias(Context context, List<Noticias> listNoticias) {
        this.context = context;
        this.listNoticias = listNoticias;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_noticias, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorListaNoticias.ViewHolder holder, int position) {
        Noticias noticias = listNoticias.get(position);
        Glide.with(context)
                .load(noticias.getImagef())
                .into(holder.url);
        holder.name.setText(noticias.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent descripcion = new Intent(holder.itemView.getContext(), DescriptionNoticiasActivity.class);
                descripcion.putExtra("nombre", String.valueOf(noticias.getId()));
                holder.itemView.getContext().startActivity(descripcion);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listNoticias.size();
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
