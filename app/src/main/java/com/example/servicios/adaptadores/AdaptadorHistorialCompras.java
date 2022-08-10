package com.example.servicios.adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.servicios.R;
import com.example.servicios.modelos.ProductoHistorial;

import java.util.List;

public class AdaptadorHistorialCompras  extends RecyclerView.Adapter<AdaptadorHistorialCompras.ComprasViewHolder> {
    private Context mCtx;
    private List<ProductoHistorial> productoHistorialList;

    public AdaptadorHistorialCompras(Context mCtx, List<ProductoHistorial> productoHistorialList) {
        this.mCtx = mCtx;
        this.productoHistorialList = productoHistorialList;
    }

     @Override
    public AdaptadorHistorialCompras.ComprasViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_compras,null);
        return new AdaptadorHistorialCompras.ComprasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorHistorialCompras.ComprasViewHolder holder, int position){
        ProductoHistorial producto = productoHistorialList.get(position);
        int estado = Integer.parseInt(productoHistorialList.get(position).getFueRecogido());
        if (estado==1){
            holder.txtRecogido.setText("Recogido");
            holder.linearFondo.setBackgroundColor(Color.parseColor("#8BC34A"));
        }else{
            holder.txtRecogido.setText("Por Recoger");
            holder.linearFondo.setBackgroundColor(Color.parseColor("#FF6636"));

        }
        holder.txtFechaPago.setText(producto.getFechaPago());
        holder.txtNombreProducto.setText(producto.getNombre());
        holder.txtMonto.setText("S/. "+producto.getMonto());

    }

    @Override
    public int getItemCount(){
        return productoHistorialList.size();
    }

    class ComprasViewHolder extends RecyclerView.ViewHolder {
        TextView txtFechaPago,txtRecogido, txtNombreProducto, txtMonto;
        LinearLayout linearFondo;
        public ComprasViewHolder(View itemView) {
            super(itemView);
            txtFechaPago=itemView.findViewById(R.id.idTextFechaCompra);
            txtRecogido=itemView.findViewById(R.id.idTextEstado);
            txtNombreProducto=itemView.findViewById(R.id.idTextProducto);
            txtMonto=itemView.findViewById(R.id.idTextMontoPago);
            linearFondo=itemView.findViewById(R.id.linearColorFondo);
        }
    }
}
