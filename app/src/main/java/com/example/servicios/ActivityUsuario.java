package com.example.servicios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityUsuario extends AppCompatActivity implements View.OnClickListener{

    ImageView btnLogOut,tvImagen;
    int data;
    TextView tvName,tvEstado,tvPago,tvVitalicio,tvFecha;
    EditText tvEditName,tvEditDni,tvEditSurName,tvEditnCip,tvEditCondicion;
    String nroMatricula,contraseña;
    String nombres,dni,nombre,apellidoP,apellidoM,estado,codigo,mespago,annopago,tipo,activo,mespago1;
    RequestQueue requestQueue;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        Bundle extras = getIntent().getExtras();
        requestQueue = Volley.newRequestQueue(this);
        if(extras!=null){
            nroMatricula = extras.getString("nro");
            contraseña = extras.getString("Password");
        }
        variable();
        datosCliente();
        btnLogOut.setOnClickListener(this);
        bottomNavigationView.setSelectedItemId(R.id.Ini);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.Ini:
                        Intent cliente = new Intent(getApplicationContext(), ActivityUsuario.class);
                        cliente.putExtra("nro",nroMatricula);
                        cliente.putExtra("Password",contraseña);
                        return true;
                    case R.id.Info:
                        Intent informacion = new Intent(getApplicationContext(),ActivityInformacion.class);
                        informacion.putExtra("nro",nroMatricula);
                        informacion.putExtra("Password",contraseña);
                        startActivity(informacion);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Serv:
                        Intent servicio = new Intent(getApplicationContext(), MenuServicio.class);
                        servicio.putExtra("nro",nroMatricula);
                        servicio.putExtra("Password",contraseña);
                        startActivity(servicio);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnLogOut) {
            Intent register = new Intent(ActivityUsuario.this,ActivityLogin.class);
            startActivity(register);
            Toast.makeText(ActivityUsuario.this,"Volviendo al Login", Toast.LENGTH_SHORT).show();
        }
    }
    private void variable(){
        tvFecha = findViewById(R.id.tvFecha);
        tvName = findViewById(R.id.tvName);
        tvImagen =findViewById(R.id.tvImagen);
        tvEstado = findViewById(R.id.tvEstado);
        tvEditCondicion = findViewById(R.id.tvEditCondicion);
        tvPago = findViewById(R.id.tvPago1);
        tvVitalicio = findViewById(R.id.tvVitalicio);
        tvEditDni = findViewById(R.id.tvEditDNI);
        tvEditnCip = findViewById(R.id.tvEditnCip);
        tvEditName = findViewById(R.id.tvEditName);
        tvEditSurName = findViewById(R.id.tvEditSurName);
        btnLogOut = findViewById(R.id.btnLogOut);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }
    private void datosCliente(){
        String ip = getString(R.string.ip);
        String URL=ip+"/ws/dato.php?nro_matricula="+nroMatricula;
        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            nombres = response.getString("NOMBRE");
                            dni = response.getString("DNI");
                            estado = response.getString("estado");
                            nombre = response.getString("NOMBRES");
                            apellidoM = response.getString("APELLIDO_MATERNO");
                            apellidoP = response.getString("APELLIDO_PATERNO");
                            activo = response.getString("activo");
                            codigo = response.getString("nro_matricula");
                            tipo=response.getString("tipo");
                            mespago = response.getString("MES");
                            annopago = response.getString("ANNO");
                            data = Integer.parseInt(annopago)+1;

                            tvName.setText(nombres);
                            tvEstado.setText(estado);
                            if(estado.equals("HABILITADO")){
                                tvImagen.setImageResource(R.drawable.habilitados);
                            }
                            if(estado.equals("INHABILITADO")) {
                                tvImagen.setImageResource(R.drawable.inhabilitado);
                            }
                            tvVitalicio.setText("Tipo: " + tipo);
                            tvEditDni.setText(dni);
                            tvEditName.setText(nombre);
                            tvEditnCip.setText(codigo);
                            tvEditCondicion.setText(activo);
                            tvEditSurName.setText(apellidoP + " " +  apellidoM);

                            switch (mespago){
                                case "1":
                                    tvPago.setText("Ultimo Pago: ENERO" + " " + annopago);
                                    tvFecha.setText("Habilitado hasta : ABRIL" + " " + annopago);
                                    break;
                                case "2":
                                    tvPago.setText("Ultimo Pago: FEBRERO" + " " + annopago);
                                    tvFecha.setText("Habilitado hasta : MAYO" + " " + annopago);
                                    break;
                                case "3":
                                    tvPago.setText("Ultimo Pago: MARZO" + " " + annopago);
                                    tvFecha.setText("Habilitado hasta : JUNIO" + " " + annopago);
                                    break;
                                case "4":
                                    tvPago.setText("Ultimo Pago: ABRIL" + " " + annopago);
                                    tvFecha.setText("Habilitado hasta : JULIO" + " " + annopago);
                                    break;
                                case "5":
                                    tvPago.setText("Ultimo Pago: MAYO" + " " + annopago);
                                    tvFecha.setText("Habilitado hasta : AGOSTO" + " " + annopago);
                                    break;
                                case "6":
                                    tvPago.setText("Ultimo Pago: JUNIO" + " " + annopago);
                                    tvFecha.setText("Habilitado hasta : SEPTIEMBRE" + " " + annopago);
                                    break;
                                case "7":
                                    tvPago.setText("Ultimo Pago: JULIO" + " " + annopago);
                                    tvFecha.setText("Habilitado hasta : OCTUBRE" + " " + annopago);
                                    break;
                                case "8":
                                    tvPago.setText("Ultimo Pago: AGOSTO" + " " + annopago);
                                    tvFecha.setText("Habilitado hasta : NOVIEMBRE" + " " + annopago);
                                    break;
                                case "9":
                                    tvPago.setText("Ultimo Pago: SEPTIEMBRE" + " " + annopago);
                                    tvFecha.setText("Habilitado hasta : DICIEMBRE" + " " + annopago);
                                    break;
                                case "10":
                                    tvPago.setText("Ultimo Pago: OCTUBRE" + " " + annopago);
                                    tvFecha.setText("Habilitado hasta : ENERO" + " " + (data));
                                    break;
                                case "11":
                                    tvPago.setText("Ultimo Pago: NOVIEMBRE" + " " + annopago);
                                    tvFecha.setText("Habilitado hasta : FEBRERO" + " " + (data));
                                    break;
                                case "12":
                                    tvPago.setText("Ultimo Pago: DICIEMBRE" + " " + annopago);
                                    tvFecha.setText("Habilitado hasta : MARZO" + " " + (data));
                                    break;
                                default:
                                    throw new IllegalStateException("Unexpected value: " + mespago);
                            }

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ActivityUsuario.this, ActivityLogin.class));
        Toast.makeText(ActivityUsuario.this,"Volviendo al Login", Toast.LENGTH_SHORT).show();
    }
}