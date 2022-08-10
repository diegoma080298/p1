package com.example.servicios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.example.servicios.fragments.FragmentHistorialAlquiler;
import com.example.servicios.fragments.FragmentHistorialCompras;
import com.example.servicios.fragments.FragmentHistorialCuotas;
import com.example.servicios.vistas.ActivityAlquilarAmbientes;
import com.example.servicios.vistas.ActivityISS;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class MenuServicio extends AppCompatActivity {
    /*Variables que se vincularan al XML: activity_servicio_menu*/
    CardView cardCuotas, cardEstado, cardIss, cardTienda, cardAlquiler;
    /*Strings que se usaran para recibir los datos del Login*/
    String nroMatricula,contraseña;
    int habilitado=1;
    BottomNavigationView bottomNavigationView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios_menu);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            nroMatricula = extras.getString("nro");
            contraseña = extras.getString("Password");
        }
        recibirIntent();
        vincularMenuServicio();
        /*Se pone los Listener para los CardView */
        bottomNavigationView1 = findViewById(R.id.bottomNavigationView);
        bottomNavigationView1.setSelectedItemId(R.id.Serv);
        bottomNavigationView1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.Ini:
                        Intent cliente = new Intent(getApplicationContext(), ActivityUsuario.class);
                        cliente.putExtra("nro",nroMatricula);
                        cliente.putExtra("habilitado",habilitado);
                        cliente.putExtra("Password",contraseña);
                        startActivity(cliente);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Info:
                        Intent informacion = new Intent(getApplicationContext(),ActivityInformacion.class);
                        informacion.putExtra("nro",nroMatricula);
                        informacion.putExtra("habilitado",habilitado);
                        informacion.putExtra("Password",contraseña);
                        startActivity(informacion);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Serv:
                        Intent servicio = new Intent(getApplicationContext(), MenuServicio.class);
                        servicio.putExtra("nro",nroMatricula);
                        servicio.putExtra("habilitado",habilitado);
                        servicio.putExtra("Password",contraseña);
                        return true;
                }
                return false;
            }
        });
        cardCuotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent para movernos a otra activity y envio del NroCip*/
                Intent i = new Intent(getApplicationContext(),PagoCuota.class);
                i.putExtra("nro",nroMatricula);
                i.putExtra("habilitado",habilitado);
                startActivity(i);
            }
        });
        cardEstado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent para movernos a otra activity y envio del NroCip*/
                Intent i = new Intent(getApplicationContext(),PagosHistorial.class);
                i.putExtra("nro",nroMatricula);
                i.putExtra("habilitado",habilitado);
                startActivity(i);
            }
        });
        cardIss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent para movernos a otra activity y envio del NroCip*/
                Intent i = new Intent(getApplicationContext(), ActivityISS.class);
                i.putExtra("nro",nroMatricula);
                i.putExtra("habilitado",habilitado);
                startActivity(i);
            }
        });
        cardAlquiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent para movernos a otra activity y envio del NroCip*/
                Intent i = new Intent(getApplicationContext(), ActivityAlquilarAmbientes.class);
                i.putExtra("nro",nroMatricula);
                i.putExtra("habilitado",habilitado);
                startActivity(i);
            }
        });

        cardTienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent para movernos a otra activity y envio del NroCip*/
                Intent i = new Intent(getApplicationContext(),TiendaVirtual.class);
                i.putExtra("nro",nroMatricula);
                i.putExtra("habilitado",habilitado);
                startActivity(i);
            }
        });

    }

    /*METODO: recibirIntent
    * Recibir los datos del login
    * */
    private void recibirIntent(){
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            nroMatricula = extras.getString("nro");
            habilitado=extras.getInt("habilitado");
        }
    }

    /*METODO: vincularMenuServicio
     * Vincular la vista
     * */
        private void vincularMenuServicio(){
        cardCuotas=findViewById(R.id.idCartaCuotas);
        cardEstado=findViewById(R.id.idCartaEstado);
        cardAlquiler=findViewById(R.id.idCartaAlquiler);
        cardIss=findViewById(R.id.idCartaIss);
        cardTienda=findViewById(R.id.idCartaTienda);

    }

}