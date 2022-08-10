package com.example.servicios.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.servicios.ActivityLogin;
import com.example.servicios.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActivityRegister extends AppCompatActivity implements View.OnClickListener {
    EditText txtCip,txtEmail,txtPassword;
    Button btnRegistrar;
    ImageView btnBack;
    RequestQueue requestQueue;
    String nroMatricula,Email,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        requestQueue = Volley.newRequestQueue(this);

        txtCip =  findViewById(R.id.txtCip);
        txtEmail = findViewById(R.id.txtEmail);

        txtPassword = findViewById(R.id.txtPassword);
        btnBack = findViewById(R.id.btnBack);
        btnRegistrar =findViewById(R.id.btnRegistrar);

        btnBack.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnBack) {
            Intent back = new Intent(ActivityRegister.this,ActivityLogin.class);
            startActivity(back);
        }else if(id == R.id.btnRegistrar){
            nroMatricula = txtCip.getText().toString().trim();
            Email = txtEmail.getText().toString().trim();
            Password = txtPassword.getText().toString().trim();
            registerUser(nroMatricula,Email,Password);
            txtCip.setText("");
            txtEmail.setText("");
            txtPassword.setText("");
            Intent back = new Intent(ActivityRegister.this,ActivityLogin.class);
            startActivity(back);
        }
    }


    public void registerUser(String txtCip, String txtEmail, String txtPassword){
        String ip = getString(R.string.ip);
        String URL=ip+"/ws/register.php";
        StringRequest respuesta = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ActivityRegister.this, "Cliente registrado correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActivityRegister.this, "Cliente no se pudo registrar correctamente", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("nroMatricula",txtCip);
                params.put("emailUsuario",txtEmail);
                params.put("contrasena",txtPassword);
                return params;
            }
        };

        requestQueue.add(respuesta);
    }



}