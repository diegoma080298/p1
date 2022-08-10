package com.example.servicios.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.servicios.R;
import com.example.servicios.modelos.Producto;

import java.util.List;

public class AdaptadorListaProducto extends RecyclerView.Adapter<AdaptadorListaProducto.ProductoViewHolder> {
    private Context mCtx;
    private List<Producto> productoList;
    final AdaptadorListaProducto.onItemClickListener listener;

    public interface onItemClickListener{
        void onItemClick(Producto producto);
    }

    public AdaptadorListaProducto(Context mCtx, List<Producto> productoList, AdaptadorListaProducto.onItemClickListener listener){
        this.mCtx=mCtx;
        this.productoList=productoList;
        this.listener = listener;
    }

    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_tienda,null);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductoViewHolder holder,int position){
        holder.bindData(productoList.get(position));
    }

    @Override
    public int getItemCount(){return productoList.size();}


    class ProductoViewHolder extends RecyclerView.ViewHolder{
        TextView txtStock, txtNombre;
        Button botonCompra;
        ImageView img;
        public  ProductoViewHolder(View itemView){
            super(itemView);
            txtStock=itemView.findViewById(R.id.idTextStock);
            img=itemView.findViewById(R.id.idImagenTienda);
            txtNombre=itemView.findViewById(R.id.idTxtNombreProducto);
            botonCompra=itemView.findViewById(R.id.idBotonComprar);
        }

        void bindData(Producto productos){
            txtNombre.setText(productos.getNombre());
            txtStock.setText(String.valueOf(productos.getStock()));
            Glide.with(itemView).load(productos.getImg()).into(img);
            botonCompra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(productos);
                }
            });
        }
    }



}
