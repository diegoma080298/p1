package com.example.servicios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.servicios.adaptadores.AdaptadorListaNoticias;
import com.example.servicios.modelos.Noticias;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityNoticias extends AppCompatActivity {

    String nroMatricula;
    int habilitado;
    List<Noticias> listNoticias ;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        init();
    }
    public void init(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listNoticias = new ArrayList<>();
        recibirIntent();
        loaddata();

    }

    private void recibirIntent(){
        nroMatricula=getIntent().getStringExtra("nro");
        habilitado=getIntent().getIntExtra("habilitado",0);
    }

    public void regresar(View view){
        Intent i = new Intent(getApplicationContext(), ActivityInformacion.class);
        i.putExtra("nro",nroMatricula);
        i.putExtra("habilitado",habilitado);
        startActivity(i);
    }

    private void loaddata(){
        String ip = getString(R.string.ip);
        String URL1= ip+"/ws/Guardado/Noticias/data.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject imagenes = array.getJSONObject(i);


                                listNoticias.add(new Noticias(
                                        imagenes.getInt("idNot"),
                                        imagenes.getString("tituloNot"),
                                        imagenes.getString("descripNot"),
                                        imagenes.getString("imgNot")

                                ));

                            }
                            AdaptadorListaNoticias adapter = new AdaptadorListaNoticias(ActivityNoticias.this
                                    , listNoticias);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}