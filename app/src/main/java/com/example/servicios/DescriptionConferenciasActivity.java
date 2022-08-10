package com.example.servicios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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

import org.json.JSONException;
import org.json.JSONObject;

public class DescriptionConferenciasActivity extends AppCompatActivity {

    private TextView titleDescriptionTextView,fechaTextView,linkTextView,idZoomTxt,codigoAccesoTxt;
    private ImageView imageTextView2;
    private String dato,url,fecha,titulo,link,idZoom,codigoAcceso,nroMatricula;
    private int habilitado;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_conferencias);
        recibirIntent();
        Bundle extras = getIntent().getExtras();
        requestQueue = Volley.newRequestQueue(this);
        if(extras!=null){
            dato = extras.getString("idConf");
        }

        variable();
        datosCliente();

    }
    private void variable(){
        imageTextView2 = (ImageView) findViewById(R.id.imageTextView2);
        titleDescriptionTextView = findViewById((R.id.titleDescriptionTextView));
        fechaTextView = findViewById(R.id.fechaTextView);
        linkTextView = findViewById(R.id.linkTextView);
        idZoomTxt = findViewById(R.id.idZoomtxt);
        codigoAccesoTxt = findViewById(R.id.codigoAccesotxt);
    }
    private void datosCliente(){
        String ip = getString(R.string.ip);
        String URL1=ip+"/ws/Guardado/Conferencias/data_conferencia.php?id="+dato;
        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(
                Request.Method.GET,
                URL1,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            titulo = response.getString("tituloConf");
                            fecha = response.getString("fechaConf");
                            link = response.getString("linkZoom");
                            url = response.getString("imgConf");
                            idZoom = response.getString("idZoom");
                            codigoAcceso = response.getString("codigoAcceso");



                            cargarImagen();

                            titleDescriptionTextView.setText(titulo);
                            idZoomTxt.setText("ID Zoom: " + idZoom);
                            fechaTextView.setText("Fecha de la Reunion: " + fecha);
                            if (codigoAcceso.equals("0")){
                                codigoAccesoTxt.setText("Ingreso sin Codigo");
                            } else {
                                codigoAccesoTxt.setText("Codigo de Acceso: " +codigoAcceso);
                            }
                            linkTextView.setText(link);



                            linkTextView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Uri url = Uri.parse(link);
                                    Intent view = new Intent(Intent.ACTION_VIEW,url);
                                    linkTextView.getContext().startActivity(view);
                                }
                            });


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
                }, 1000, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication(),"Error al cargar imagen",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(imageRequest);
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
}