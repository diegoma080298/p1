package com.example.servicios.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.servicios.PagosHistorial;
import com.example.servicios.R;
import com.example.servicios.adaptadores.AdaptadorHistorialCompras;
import com.example.servicios.modelos.ProductoHistorial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class FragmentHistorialCompras extends Fragment {

    List<ProductoHistorial> productoHistorialList;
    RecyclerView recyclerView;
    TextView txtFondo;
    String nroMatricula;

    public FragmentHistorialCompras() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_historial_compras, container, false);
        txtFondo=view.findViewById(R.id.textoAvisoNoHayCompras);
        recyclerView=view.findViewById(R.id.recyclerTabCompras);
        PagosHistorial pago = (PagosHistorial)getActivity();
        nroMatricula=pago.getNro();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productoHistorialList=new ArrayList<>();
        cargarHistorialCompras();
        return view;
    }

    private void cargarHistorialCompras() {
        String ip = getString(R.string.ip);
        String URL = ip+"/ws/consulta_historialcompra.php?id="+nroMatricula;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    if (array.length()==0){
                    }else{
                        for (int i=0;i<array.length();i++){
                            JSONObject producto = array.getJSONObject(i);
                            productoHistorialList.add(new ProductoHistorial(
                                    producto.getString("nombreProducto"),
                                    producto.getString("monto"),
                                    producto.getString("fechaPago"),
                                    producto.getString("fueRecogido")
                            ));
                            txtFondo.setVisibility(View.INVISIBLE);
                            recyclerView.setVisibility(View.VISIBLE);
                            AdaptadorHistorialCompras adaptador = new AdaptadorHistorialCompras(
                                    getContext(),productoHistorialList);
                            recyclerView.setAdapter(adaptador);
                        }
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }
}