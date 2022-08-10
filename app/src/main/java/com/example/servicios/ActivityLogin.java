package com.example.servicios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.servicios.vistas.ActivityRegister;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener{
    EditText txtCip,txtPassword;
    Button btnIC,btnRC,btnUC;
    String nroMatricula,Password;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        requestQueue = Volley.newRequestQueue(this);
        variable();
        btnIC.setOnClickListener(this);
        btnRC.setOnClickListener(this);
        btnUC.setOnClickListener(this);
    }

    private void variable(){
        txtCip = findViewById(R.id.txtCip);
        txtPassword = findViewById(R.id.txtPassword);
        btnIC = findViewById(R.id.btnIngresar);
        btnRC = findViewById(R.id.btnRegistrar);
        btnUC = findViewById(R.id.btnUC);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnIngresar) {
            nroMatricula = txtCip.getText().toString().trim();
            Password = txtPassword.getText().toString().trim();
            login();
        } else if (id == R.id.btnRegistrar){
            Intent register = new Intent(ActivityLogin.this, ActivityRegister.class);
            startActivity(register);
        }
    }

    private void login(){
        if(nroMatricula.equals("") || Password.equals("")){
            Toast.makeText(this,"Ingrese Correo electronico y/o Contraseña", Toast.LENGTH_SHORT).show();
        } else {
            String ip = getString(R.string.ip);
            String URL = ip+"/ws/login.php?nroMatricula="+nroMatricula+"&contrasena="+Password;
            JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(
                    Request.Method.GET,
                    URL,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                nroMatricula = response.getString("nro_matricula");
                                txtCip.setText(nroMatricula);
                                txtPassword.setText(Password);
                                Intent cliente = new Intent(ActivityLogin.this, ActivityUsuario.class);
                                cliente.putExtra("nro",nroMatricula);
                                cliente.putExtra("Password",Password);
                                startActivity(cliente);
                                txtCip.setText("");
                                txtPassword.setText("");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ActivityLogin.this,"No existe usuario con ID " + nroMatricula, Toast.LENGTH_SHORT).show();
                        }
                    }
            );
            requestQueue.add(jsonObjectRequest);
        }
    }


    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ActivityLogin.this, ActivityLogin.class));
    }

}