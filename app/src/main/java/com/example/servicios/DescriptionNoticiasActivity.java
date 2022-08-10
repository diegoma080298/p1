package com.example.servicios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.servicios.modelos.Noticias;

import org.json.JSONException;
import org.json.JSONObject;

public class DescriptionNoticiasActivity extends AppCompatActivity {

    private TextView titleDescriptionTextView2;
    private ImageView imageTextView2;
    private TextView descriptionTextView2;
    private String dato,url,descripcion,nombre;
    Noticias noticias;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_noticias);

        Bundle extras = getIntent().getExtras();
        requestQueue = Volley.newRequestQueue(this);
        if(extras!=null){
            dato = extras.getString("nombre");
        }

        variable();
        datosCliente();

    }
    private void variable(){
        imageTextView2 = (ImageView) findViewById(R.id.imageTextView2);
        titleDescriptionTextView2 = findViewById((R.id.titleDescriptionTextView2));
        descriptionTextView2 = findViewById(R.id.descriptionTextView2);
    }
    private void datosCliente(){
        String ip = getString(R.string.ip);
        String URL=ip+"/ws/Guardado/Noticias/data_noticia.php?id="+dato;
        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            nombre = response.getString("tituloNot");
                            descripcion = response.getString("descripNot");
                            url = response.getString("imgNot");

                            cargarImagen();
                            titleDescriptionTextView2.setText(nombre);
                            descriptionTextView2.setText(descripcion);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
    public void cargarImagen(){
        ImageRequest imageRequest = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageTextView2.setImageBitmap(response);
                    }
                }, 600, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication(),"Error al cargar imagen",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(imageRequest);
    }
    public void regresar(View view){
        Intent i = new Intent(getApplicationContext(), ActivityNoticias.class);
        startActivity(i);
    }

}