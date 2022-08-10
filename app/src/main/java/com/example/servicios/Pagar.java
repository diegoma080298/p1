package com.example.servicios;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vinaygaba.creditcardview.CardType;
import com.vinaygaba.creditcardview.CreditCardView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Pagar extends AppCompatActivity {
    /*Variables en comun*/
    String nroMatricula, fechaPago;
    int tipoPago,habilitado;
    /*Variables para el pago de Cuotas*/
    String idPersona, mesE,montoCuota,periodoC,periodoE;
    int mesC;
    /*Variables para el pago de Alquiler*/
    String nombreAmbiente,fechaAlq,montoAlquiler,horaInicio, horasAlquiler;
    /*Variables para el pago de Productos*/
    String nombreProducto,
            montoProductos, fueRecogido;
    int idProducto,stock, stockNuevo;
    /*Componentes de la vista*/
    Button botonPagar;
    CreditCardView tarjeta;
    EditText editNroTarjeta,editMes,editAnno,editCvv,editCorreo;
    String tarjetaEnviar,tarjetaMesEnviar,tarjetaAnnoEnviar,
            tarjetaCvvEnviar,tarjetaCorreoEnviar,montoPago;
    AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagar);
        recibirIntent();
        vincularActivityPagar();
        cargarDatosTarjeta();
        editNroTarjeta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tarjetaEnviar = editNroTarjeta.getText().toString();
                tarjeta.setCardNumber(tarjetaEnviar);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        editMes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tarjetaMesEnviar = editMes.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editAnno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tarjetaAnnoEnviar = editAnno.getText().toString();
                tarjeta.setExpiryDate(tarjetaMesEnviar+"/"+tarjetaAnnoEnviar);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        botonPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (camposLlenos()){
                    identificarTipoPago(tipoPago);
                }else{
                    mostrarToast("Llenar campos");
                }
            }
        });
    }

    private void guardarDatosTarjeta(){
        tarjetaEnviar=editNroTarjeta.getText().toString();
        tarjetaMesEnviar=editMes.getText().toString();
        tarjetaAnnoEnviar=editAnno.getText().toString();
        tarjetaCvvEnviar=editCvv.getText().toString();
        tarjetaCorreoEnviar=editCorreo.getText().toString();
    }

    private void cargarDatosTarjeta(){
        editNroTarjeta.setText("414141414141");
        editMes.setText("2");
        editAnno.setText("22");
        editCvv.setText("123");
        editCorreo.setText("diego@gmail.com");
    }

    private void mostrarAlert(String msj,int tipo){
        AlertDialog.Builder builder = new AlertDialog.Builder(Pagar.this);
        builder.setMessage(msj)
                .setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (tipo==1){
                            pagarCuota();
                            actualizarCuota();
                            mostrarToast("Pago CUOTA realizado!");

                        }else if (tipo==2){
                            pagarProducto();
                            actualizarStock();
                            mostrarToast("Pago PRODUCTO realizado!");

                        }else if (tipo ==3){
                            pagarAlquiler();
                            mostrarToast("Pago ALQUILER realizado!");

                        }
                        dialogInterface.cancel();
                        volverMenu();

                    }
                }).setNegativeButton("Volver", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alerta= builder.create();
        alerta.setTitle("Datos del Pago:");
        alerta.show();
    }


    /*
     * METODO: identificarTipoPago
     * Se usara para identificar el pago que se realizara, los parametros
     * pueden ser:
     * 1: Pago de cuota
     * 2: Compra de productos
     * 3: Alquiler Ambiente
     * El parametro "tipo" se igualara al dato que recibiremos de la Activity anterior
     * -Revisar metodo recibirDato()-
     * Finalmente dependiendo el tipo de pago se realizara los metodos correspondientes
     * para el envio de datos por POST
     * */
    private void identificarTipoPago(int tipo){
        guardarDatosTarjeta();
        if (tipo==1){
            cambiarMes(mesE);
            String msjAlerta = "EL VALOR ES: "+tipo+" SE HARA PAGO CUOTA"+"\n"+
                    "Al Nro Matricula: "+nroMatricula+"\n"+
                    "Nro: "+tarjetaEnviar+"\n"+
                    " Mes: "+tarjetaMesEnviar+"\n"+
                    " Año: "+ tarjetaAnnoEnviar+"\n"+
                    " CVV: "+tarjetaCvvEnviar+"\n"+
                    " Correo: "+tarjetaCorreoEnviar+"\n"+
                    " Por un monto de: "+montoPago+"\n"+
                    " ID PERSONA: "+idPersona+"\n"+
                    " bajo concepto de: CUOTA "+mesE+" "+periodoE+"\n"+
                    " bajo concepto de: CUOTA "+mesC+" "+periodoC;
            mostrarAlert(msjAlerta,tipo);
            //pagarCuota();
            //actualizarCuota();

        }else{
            if (tipo==2){
                String msjAlerta ="EL VALOR ES: "+tipo+" SE HARA PAGO TIENDA"+"\n"+
                        "Al Nro Matricula: "+nroMatricula+"\n"+
                        "Nro: "+tarjetaEnviar+"\n"+
                        " Mes: "+tarjetaMesEnviar+"\n"+
                        " Año: "+ tarjetaAnnoEnviar+"\n"+
                        " CVV: "+tarjetaCvvEnviar+"\n"+
                        " Correo: "+tarjetaCorreoEnviar+"\n"+
                        " Por un monto de: "+montoPago+"\n"+
                        " Id producto: "+idProducto+"\n"+
                        " bajo concepto de: "+nombreProducto+"\n"+
                        " stock viejo de: "+stock+"\n"+
                        " stock nuevo de: "+stockNuevo;
                mostrarAlert(msjAlerta,tipo);

            }else{
                String msjAlerta= "EL VALOR ES: "+tipo+" SE HARA PAGO DE ALQUILER"+"\n"+
                        "Al Nro Matricula: "+nroMatricula+"\n"+
                        "Nro: "+tarjetaEnviar+"\n"+
                        " Mes: "+tarjetaMesEnviar+"\n"+
                        " Año: "+ tarjetaAnnoEnviar+"\n"+
                        " CVV: "+tarjetaCvvEnviar+"\n"+
                        " Correo: "+tarjetaCorreoEnviar+"\n"+
                        " Por un monto de: "+montoPago+"\n"+
                        " bajo concepto de: "+nombreAmbiente;
                mostrarAlert(msjAlerta,tipo);
                //pagarAlquiler();

            }
        }
    }

    private void volverMenu(){
        Intent i = new Intent(getApplicationContext(),MenuServicio.class);
        i.putExtra("nro",nroMatricula);
        i.putExtra("habilitado",habilitado);
        startActivity(i);
    }

    private void actualizarStock(){
        String ip= getString(R.string.ip);
        String URL1=ip+"/ws/actualizar_stock.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("idProducto",String.valueOf(idProducto));
                parametros.put("stock",String.valueOf(stockNuevo));
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void actualizarCuota(){
        String ip = getString(R.string.ip);
        String URL_actualizar=ip+"/ws/actualizar_deuda.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_actualizar,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mostrarToast("Se actualizo la cuota");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mostrarToast(error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("idPersona",idPersona);
                parametros.put("mes",String.valueOf(mesC));
                parametros.put("periodo",periodoC);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void pagarCuota(){
        String ip = getString(R.string.ip);
        String URL_pagoCuota=ip+"/ws/insertar_pagocuota.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_pagoCuota,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mostrarToast("Se inserto correctamente");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mostrarToast(error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("nroMatricula",nroMatricula);
                parametros.put("mes",mesE);
                parametros.put("periodo",String.valueOf(periodoE));
                parametros.put("fechaPago",fechaPago);
                parametros.put("monto",String.valueOf(montoCuota));
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void pagarAlquiler(){
        String ip = getString(R.string.ip);
        String URL_pagaralq=ip+"/ws/insertar_pagoalquiler.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_pagaralq, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mostrarToast("Se inserto correctamente el pago alquiler");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mostrarToast(error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("nroMatricula",nroMatricula);
                parametros.put("nombre",nombreAmbiente);
                parametros.put("fechaPago",fechaPago);
                parametros.put("fechaAlq",fechaAlq);
                parametros.put("horaInicio",horaInicio);
                parametros.put("horas",horasAlquiler);
                parametros.put("monto",montoAlquiler);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void pagarProducto(){
        String ip = getString(R.string.ip);
        String URL_pagoProducto=ip+"/ws/insertar_pagocompra.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_pagoProducto, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mostrarToast("Se inserto correctamente: ");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mostrarToast(error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("nroMatricula",nroMatricula);
                parametros.put("idProducto",String.valueOf(idProducto));
                parametros.put("nombre",nombreProducto);
                parametros.put("monto",montoProductos);
                parametros.put("fechaPago",fechaPago);
                parametros.put("fueRecogido",fueRecogido);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void obtenerIDPeriodo(String p){
        String ip = getString(R.string.ip);
        String URL_periodo=ip+"/ws/consulta_periodo.php?anno="+p;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL_periodo,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject=null;
                        for (int i=0;i<response.length();i++){
                            try {
                                jsonObject=response.getJSONObject(i);
                                periodoC=jsonObject.getString("anno");
                            }catch (JSONException e){
                                mostrarToast(e.toString());
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mostrarToast(error.toString());
            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }



    private void consultarStock(int id){
        String ip=getString(R.string.ip);
        String URL1= ip+"/ws/consulta_stock.php?id="+id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL1,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonArray= new JSONArray(response);
                    for (int i =0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        stock=jsonObject.getInt("stock");
                        stockNuevo=stock-1;
                    }
                }catch (JSONException e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(stringRequest);

    }

    private void recibirIntent(){
        tipoPago=getIntent().getIntExtra("tipo",0);
        nroMatricula=getIntent().getStringExtra("nro");
        habilitado=getIntent().getIntExtra("habilitado",0);
        if (tipoPago==1){
            idPersona=getIntent().getStringExtra("idPersona");
            fechaPago=getIntent().getStringExtra("fechaPago");
            mesE=getIntent().getStringExtra("mes");
            periodoE=getIntent().getStringExtra("periodo");
            montoCuota=getIntent().getStringExtra("montoCuota");
            montoPago=montoCuota;
            obtenerIDPeriodo(periodoE);
        }else{
            if (tipoPago==2){
                idProducto=getIntent().getIntExtra("idProducto",0);
                nombreProducto=getIntent().getStringExtra("nombreProducto");
                montoProductos=getIntent().getStringExtra("montoProducto");
                fechaPago=getIntent().getStringExtra("fechaPago");
                fueRecogido=getIntent().getStringExtra("fueRecogido");
                montoPago=montoProductos;
                consultarStock(idProducto);
            }else{
                nombreAmbiente=getIntent().getStringExtra("nombreAmbiente");
                fechaPago=getIntent().getStringExtra("fechaPago");
                fechaAlq=getIntent().getStringExtra("fechaAlq");
                horaInicio=getIntent().getStringExtra("horaInicio");
                montoAlquiler=getIntent().getStringExtra("montoAlq");
                horasAlquiler=getIntent().getStringExtra("horas");
                montoPago=montoAlquiler;
            }
        }
    }

    private void mostrarToast(String mensaje){
        Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();
    }

    private boolean camposLlenos(){
        if (editNroTarjeta.getText().toString().isEmpty() ||
                editMes.getText().toString().isEmpty() ||
                editAnno.getText().toString().isEmpty()||
                editCvv.getText().toString().isEmpty()||
                editCorreo.getText().toString().isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    public void regresar(View view){
        Intent i = new Intent(getApplicationContext(), MenuServicio.class);
        i.putExtra("nro",nroMatricula);
        i.putExtra("habilitado",habilitado);
        startActivity(i);
    }

    private void vincularActivityPagar(){
        botonPagar=(Button)findViewById(R.id.idBotonPagar);
        tarjeta=(CreditCardView)findViewById(R.id.idTarjetaCredito);
        editNroTarjeta=(EditText)findViewById(R.id.txt_card);
        editMes=(EditText)findViewById(R.id.txt_mes);
        editAnno=(EditText)findViewById(R.id.txt_anno);
        editCorreo=(EditText)findViewById(R.id.txt_email);
        editCvv=(EditText)findViewById(R.id.txt_cvv);
        botonPagar.setText("Pagar S/. "+montoPago);
    }

    private int cambiarMes(String mes){
        switch (mes){
            case "ENERO":
                mesC=1;
                break;
            case "FEBRERO":
                mesC=2;
                break;
            case "MARZO":
                mesC=3;
                break;
            case "ABRIL":
                mesC=4;
                break;
            case "MAYO":
                mesC=5;
                break;
            case "JUNIO":
                mesC=6;
                break;
            case "JULIO":
                mesC=7;
                break;
            case "AGOSTO":
                mesC=8;
                break;
            case "SEPTIEMBRE":
                mesC=9;
                break;
            case "OCTUBRE":
                mesC=10;
                break;
            case "NOVIEMBRE":
                mesC=11;
                break;
            case "DICIEMBRE":
                mesC=12;
                break;
            default:
                mesC=1;
        }
        return mesC;
    }
}