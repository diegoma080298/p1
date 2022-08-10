package com.example.servicios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class ActivityInformacion extends AppCompatActivity {
    BottomNavigationView bottomNavigationView1;
    String nCip,contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            nCip = extras.getString("nro");
            contraseña = extras.getString("Password");
        }
        bottomNavigationView1 = findViewById(R.id.bottomNavigationView);
        bottomNavigationView1.setSelectedItemId(R.id.Info);
        bottomNavigationView1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.Ini:
                        Intent cliente = new Intent(getApplicationContext(), ActivityUsuario.class);
                        cliente.putExtra("nro",nCip);
                        cliente.putExtra("Password",contraseña);
                        startActivity(cliente);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Info:
                        Intent informacion = new Intent(getApplicationContext(),ActivityInformacion.class);
                        informacion.putExtra("nro",nCip);
                        informacion.putExtra("Password",contraseña);
                        return true;
                    case R.id.Serv:
                        Intent servicio = new Intent(getApplicationContext(), MenuServicio.class);
                        servicio.putExtra("nro",nCip);
                        servicio.putExtra("Password",contraseña);
                        startActivity(servicio);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    public void noticiasV(View view){
        Intent noticias = new Intent(getApplicationContext(),ActivityNoticias.class);
        noticias.putExtra("nro",nCip);
        startActivity(noticias);
    }
    public void conferenciaV(View view){
        Intent conferencia = new Intent(getApplicationContext(),ActivityConferencia.class);
        conferencia.putExtra("nro",nCip);
        startActivity(conferencia);
    }
    public void convocatoriaV(View view){
        Intent convocatoria = new Intent(getApplicationContext(),ActivityConvocatoria.class);
        convocatoria.putExtra("nro",nCip);
        startActivity(convocatoria);
    }
    public void comunicadoV(View view){
        Intent comunicado = new Intent(getApplicationContext(),ActivityComunicado.class);
        comunicado.putExtra("nro",nCip);
        startActivity(comunicado);
    }
    public void convenioV(View view){
        Intent convenio = new Intent(getApplicationContext(),ActivityConvenio.class);
        convenio.putExtra("nro",nCip);
        startActivity(convenio);
    }
}