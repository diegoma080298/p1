package com.example.servicios.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import com.example.servicios.adaptadores.AdaptadorHistorialAlquiler;
import com.example.servicios.modelos.AlquilerHistorial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class FragmentHistorialAlquiler extends Fragment {

    List<AlquilerHistorial> alquilerHistorialList;
    RecyclerView recyclerView;
    TextView txtFondo;
    String nroMatricula;
    int habilitado;


    public FragmentHistorialAlquiler() {
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
        View view = inflater.inflate(R.layout.fragment_historial_alquiler, container, false);
        txtFondo=view.findViewById(R.id.textoAvisoNoHayAlquiler);
        recyclerView=view.findViewById(R.id.recyclerTabAlquiler);
        PagosHistorial pago = (PagosHistorial)getActivity();
        nroMatricula=pago.getNro();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        alquilerHistorialList=new ArrayList<>();
        cargarHistorialAlquiler();
        return view;
    }

    private void cargarHistorialAlquiler() {
        String ip = getString(R.string.ip);
        String URL = ip+"/ws/consulta_historialalquiler.php?id="+nroMatricula;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array =new JSONArray(response);
                    if (array.length()==0){
                    }else{
                        for (int i=0;i<array.length();i++){
                            JSONObject alquiler = array.getJSONObject(i);
                            alquilerHistorialList.add(new AlquilerHistorial(
                                    alquiler.getString("nombreAmbiente"),
                                    alquiler.getString("fechaPago"),
                                    alquiler.getString("fechaAlq"),
                                    alquiler.getString("horaInicio"),
                                    alquiler.getString("monto"),
                                    alquiler.getString("horas")
                            ));
                        }
                        txtFondo.setVisibility(View.INVISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);
                        AdaptadorHistorialAlquiler adaptador = new AdaptadorHistorialAlquiler(
                                getContext(),alquilerHistorialList);
                        recyclerView.setAdapter(adaptador);
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