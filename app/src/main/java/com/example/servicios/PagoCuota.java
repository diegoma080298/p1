package com.example.servicios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.servicios.adaptadores.AdaptadorListaCuota;
import com.example.servicios.modelos.Cuotas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PagoCuota extends AppCompatActivity {
    List<Cuotas> cuotasList;
    RecyclerView recyclerView;
    TextView txtDeuda,txt1,txt2;
    RelativeLayout relative,relative2;
    CardView card;
    String nroMatricula,fechaHoy,idPersona,deuda;
    int habilitado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_cuota);
        recibirIntent();
        vincularActivityPagoCuota();
        recyclerView = (RecyclerView) findViewById(R.id.idRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cuotasList = new ArrayList<>();
    }

    private void mensajeNoDeuda(){
        relative.setVisibility(View.VISIBLE);
        relative2.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        card.setVisibility(View.INVISIBLE);
    }

    private void mensajeDeuda(){
        relative.setVisibility(View.INVISIBLE);
        relative2.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        card.setVisibility(View.VISIBLE);
    }

    private void recibirIntent(){
        nroMatricula=getIntent().getStringExtra("nro");
        habilitado=getIntent().getIntExtra("habilitado",0);
        obtenerIDPersona();
    }


    private void obtenerDeudaTotal(String id){
        String ip = getString(R.string.ip);
        String URL_deuda=ip+"/ws/consulta_deuda.php?id="+id;
        StringRequest stringRequest = new StringRequest(URL_deuda,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray jsonArray= new JSONArray(response);
                            for (int i =0;i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                txtDeuda.setText(jsonObject.getString("total"));
                                deuda=jsonObject.getString("total");
                            }
                        }catch (JSONException e){
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                   }
       });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void obtenerIDPersona(){
        String ip=getString(R.string.ip);
        String URL_ID=ip+"/ws/consulta_idpersona.php?nro="+nroMatricula;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL_ID,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length()==1){
                    try {
                        JSONObject objeto = new JSONObject(response.getJSONObject(0).toString());
                        idPersona=objeto.getString("idPersona");
                        obtenerDeudaTotal(idPersona);
                        cargarCuotas(idPersona);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }

    private void cargarCuotas(String id){
        String ip = getString(R.string.ip);
        String URL_cuotas=ip+"/ws/consulta_listacuotas.php?id="+id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_cuotas,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            if (array.length()==0){
                                Toast.makeText(getApplicationContext(),"No hay deuda",Toast.LENGTH_LONG).show();
                                mensajeNoDeuda();
                            }else{
                                mensajeDeuda();
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject cuota = array.getJSONObject(i);
                                    cuotasList.add(new Cuotas(
                                            cuota.getString("mes"),
                                            cuota.getInt("anno"),
                                            cuota.getString("cuota")
                                    ));
                                }
                                AdaptadorListaCuota adapter = new AdaptadorListaCuota(PagoCuota.this,
                                        cuotasList, new AdaptadorListaCuota.onItemClickListener() {
                                    @Override
                                    public void onItemClick(Cuotas cuota) {
                                        mostrarItem(cuota);
                                    }
                                });
                                recyclerView.setAdapter(adapter);
                            }

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
        public void mostrarItem(Cuotas cuotas){
            obtenerFecha();
            Intent i = new Intent(getApplicationContext(),Pagar.class);
            i.putExtra("tipo",1);
            i.putExtra("nro",String.valueOf(nroMatricula));
            i.putExtra("idPersona",idPersona);
            i.putExtra("mes",cuotas.getMes());
            i.putExtra("periodo",String.valueOf(cuotas.getAño()));
            i.putExtra("fechaPago",fechaHoy);
            i.putExtra("montoCuota",cuotas.getCuota());
            i.putExtra("habilitado",habilitado);
            startActivity(i);
        }

        private void obtenerFecha(){
            Date fecha = new Date();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/M/yyyy");
            fechaHoy= formatoFecha.format(fecha);
        }

    public void regresar(View view){
        Intent i = new Intent(getApplicationContext(), MenuServicio.class);
        i.putExtra("nro",nroMatricula);
        i.putExtra("habilitado",habilitado);
        startActivity(i);
    }

    private void vincularActivityPagoCuota(){
        txt1=findViewById(R.id.textMensajeDeuda);
        txt2=findViewById(R.id.idText);
        txtDeuda=(TextView)findViewById(R.id.idTextDeudaTotal);
        card=(CardView)findViewById(R.id.cardView1);
        relative=(RelativeLayout)findViewById(R.id.relativeDeuda);
        relative2=(RelativeLayout)findViewById(R.id.relativeImg);
    }

}