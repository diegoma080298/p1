package com.example.servicios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.servicios.adaptadores.AdaptadorListaProducto;
import com.example.servicios.modelos.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

public class TiendaVirtual extends AppCompatActivity {
    List<Producto> productosList;
    RecyclerView recyclerView;
    String nroMatricula,fechaHoy;
    int habilitado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda_virtual);
        recyclerView=(RecyclerView)findViewById(R.id.idRecyclerListaProductos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productosList=new ArrayList<>();
        recibirIntent();
        cargarProductos();
    }

    private void cargarProductos(){
        String ip = getString(R.string.ip);
        String URL_productos=ip+"/ws/consulta_tienda.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_productos,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject producto = array.getJSONObject(i);
                                productosList.add(new Producto(
                                        producto.getInt("id"),
                                        producto.getString("nombre"),
                                        producto.getInt("costo"),
                                        producto.getInt("stock"),
                                        producto.getString("img")
                                ));
                            }
                            AdaptadorListaProducto adapter = new AdaptadorListaProducto(TiendaVirtual.this,
                                    productosList, new AdaptadorListaProducto.onItemClickListener() {
                                @Override
                                public void onItemClick(Producto producto) {
                                    mostrarItem(producto);
                                }
                            });
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void mostrarItem(Producto producto){
       obtenerFecha();
        Intent i = new Intent(getApplicationContext(),Pagar.class);
        i.putExtra("tipo",2);
        i.putExtra("nro",nroMatricula);
        i.putExtra("idProducto",producto.getId());
        i.putExtra("nombreProducto",producto.getNombre());
        i.putExtra("montoProducto",String.valueOf(producto.getCosto()));
        i.putExtra("fechaPago",fechaHoy);
        i.putExtra("fueRecogido","0");
       startActivity(i);
    }

    private void mostrarToast(String mensaje){
        Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();
    }

    private void recibirIntent(){
        nroMatricula=getIntent().getStringExtra("nro");
        habilitado=getIntent().getIntExtra("habilitado",0);
    }

    public void regresar(View view){
        Intent i = new Intent(getApplicationContext(), MenuServicio.class);
        i.putExtra("nro",nroMatricula);
        i.putExtra("habilitado",habilitado);
        startActivity(i);
    }

    /*obtenerFecha
     * Funcion para obtener la fecha del dia
     * para enviarla a la siguiente activity
     * */
    private void obtenerFecha(){
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/M/yyyy");
        fechaHoy= formatoFecha.format(fecha);
    }


}