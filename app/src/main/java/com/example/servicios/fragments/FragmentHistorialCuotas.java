package com.example.servicios.fragments;

import android.app.Activity;
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
import com.example.servicios.adaptadores.AdaptadorHistorialCuota;
import com.example.servicios.modelos.CuotasHistorial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentHistorialCuotas extends Fragment {
    List<CuotasHistorial> cuotasHistorialList;
    RecyclerView recyclerView;
    TextView txtFondo;
    String nroMatricula;
    int habilitado;


    public FragmentHistorialCuotas() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_historial_cuotas, container, false);
        txtFondo=view.findViewById(R.id.textoAvisoNoHayCuotas);
        recyclerView=view.findViewById(R.id.recyclerTabCuotas);
        PagosHistorial pago = (PagosHistorial)getActivity();
        nroMatricula=pago.getNro();
        cargarHistorialCuotas();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cuotasHistorialList = new ArrayList<>();
        return view;
    }

    private void cargarHistorialCuotas() {
        String ip = getString(R.string.ip);
        String URL = ip+"/ws/consulta_historialcuota.php?id="+nroMatricula;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array =new JSONArray(response);
                            if (array.length()==0){
                            }else{
                                for (int i=0;i<array.length();i++){
                                    JSONObject cuota = array.getJSONObject(i);
                                    cuotasHistorialList.add(new CuotasHistorial(
                                            cuota.getString("mes"),
                                            cuota.getString("periodo"),
                                            cuota.getString("fechaPago"),
                                            cuota.getString("monto")
                                    ));
                                }
                                txtFondo.setVisibility(View.INVISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                                AdaptadorHistorialCuota adaptadorHistorialCuota =new
                                        AdaptadorHistorialCuota(getContext(),cuotasHistorialList);
                                recyclerView.setAdapter(adaptadorHistorialCuota);
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