package com.example.servicios.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.servicios.MenuServicio;
import com.example.servicios.Pagar;
import com.example.servicios.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActivityAlquilarAmbientes extends AppCompatActivity {
    /*Variables que hacen referencia a los componentes del activity*/
    //Variables de los auditorios
    TextView txtCosto2,txtLimp2,txtSonido2,txtMulti2, txtIncluye2,txtCapac2,
            txtCosto4,txtLimp4,txtSonido4, txtMulti4, txtIncluye4,txtCapac4;
    int costo2,costo4;
    Button botonAlq2,botonAlq4;
    EditText editFecha1,editFecha2;

    //Variables del centro recreacional
    TextView txtHora,txtHoras;
    EditText editFecha3,editHora;
    Button botonAlqCentro,botonSumar,botonRestar;
    int id1,id2,id3, costoPequePG,costoPequeH,
            costoMediaP,costoMediaH,costoLosaP,costoLosaH,
            minutosReloj,horasReloj, horasAlq =1;
    RadioButton radioPeque, radioMedia,radioLosa;
    RadioGroup radioGroup;

    //Variables para enviar
    String fechaHoy,fechaAlquiler;
    String nroMatricula;
    int habilitado,monto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alquilar_ambientes);
        vincularTienda();
        recibirIntent();
        cargarAuditorios();
        cargarCentro();
        obtenerFecha();
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        botonAlq2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alquilarAuditorio(2);
            }
        });
        botonAlq4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alquilarAuditorio(4);
            }
        });

        editFecha1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityAlquilarAmbientes.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month=month+1;
                                String date = day+"/"+month+"/"+year;
                                editFecha1.setText(date);
                                fechaAlquiler=editFecha1.getText().toString();
                            }
                        }, year,month,day);
                datePickerDialog.show();
            }
        });
        editFecha2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityAlquilarAmbientes.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month=month+1;
                                String date = day+"/"+month+"/"+year;
                                editFecha2.setText(date);
                                fechaAlquiler=editFecha2.getText().toString();

                            }
                        }, year,month,day);
                datePickerDialog.show();
            }
        });
        editFecha3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityAlquilarAmbientes.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month=month+1;
                                String date = day+"/"+month+"/"+year;
                                editFecha3.setText(date);
                                fechaAlquiler=editFecha3.getText().toString();
                            }
                        }, year,month,day);
                datePickerDialog.show();
            }
        });
        editHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c= Calendar.getInstance();
                horasReloj=c.get(Calendar.HOUR_OF_DAY);
                minutosReloj=c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog= new TimePickerDialog(ActivityAlquilarAmbientes.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        editHora.setText(i+":"+i1);
                    }
                },horasReloj,minutosReloj,false);
                timePickerDialog.show();
            }
        });

    }


    /*alquilarAuditorio
    * Funcion para identificar el auditorio y enviar
    * sus datos a la siguiente activity
    * */
    private void alquilarAuditorio(int audi){
        Intent i = new Intent(getApplicationContext(), Pagar.class);

        if (audi==2){
            if (editFecha1.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Seleccione una fecha",
                        Toast.LENGTH_LONG).show();
            }else{
                monto=costo2;
                i.putExtra("nombreAmbiente","Auditorio 2");
                i.putExtra("montoAlq",String.valueOf(monto));
                i.putExtra("tipo",3);
                i.putExtra("nro",nroMatricula);
                i.putExtra("fechaPago",fechaHoy);
                i.putExtra("fechaAlq",fechaAlquiler);
                i.putExtra("horaInicio","18:00");
                i.putExtra("horas", "5");
                i.putExtra("habilitado",habilitado);
                startActivity(i);
            }
        }
        if (audi==4){
            if (editFecha2.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Seleccione una fecha",
                        Toast.LENGTH_LONG).show();
            }else{
                monto=costo4;
                i.putExtra("nombreAmbiente","Auditorio 4");
                i.putExtra("montoAlq",String.valueOf(monto));
                i.putExtra("tipo",3);
                i.putExtra("nro",nroMatricula);
                i.putExtra("fechaPago",fechaHoy);
                i.putExtra("fechaAlq",fechaAlquiler);
                i.putExtra("horaInicio","18:00");
                i.putExtra("horas", "5");
                i.putExtra("habilitado",habilitado);
                startActivity(i);
            }
        }

    }

    /*alquilarCentro
    * Funcion que se usara para calcular el monto de alquiler
    * segun si el usuario esta habilitado o no. La funcion
    * recibira dos parametros:
    * c1: costo para publico general
    * c2: costo especial para ing. habilitados
    * Se envia los datos necesarios por Intent para realizar
    * el cobro y guardado de datos.
    * */
    private void alquilarCentro(int c1,int c2,String lugar){
        horasAlq =Integer.parseInt(txtHora.getText().toString());
        if (habilitado==0){
            monto = c1 * horasAlq;
        }else {
            monto = c2 * horasAlq;
        }
        Intent i = new Intent(getApplicationContext(),Pagar.class);
        i.putExtra("tipo",3);
        i.putExtra("nro",String.valueOf(nroMatricula));
        i.putExtra("nombreAmbiente",lugar);
        i.putExtra("fechaPago",fechaHoy);
        i.putExtra("fechaAlq",fechaAlquiler);
        i.putExtra("horaInicio",editHora.getText().toString());
        i.putExtra("horas", String.valueOf(horasAlq));
        i.putExtra("montoAlq",String.valueOf(monto));
        i.putExtra("habilitado",habilitado);
        startActivity(i);
    }

    /*obtenerRadioOpcion
    * Funcion para saber cual RadioButton fue escogido
    * dependiendo la seleccionada se le pasara los parametros
    * a la funcion alquilarCentro()
    * */
    public void obtenerRadioOpcion(View view){
        boolean r1 = radioPeque.isChecked();
        boolean r2 = radioMedia.isChecked();
        boolean r3 = radioLosa.isChecked();
        if (editFecha3.getText().toString().isEmpty()||editHora.getText().toString().isEmpty()){
            mostrarToast("Escoger una hora");
        }else{
            if (r1){
                alquilarCentro(costoPequePG,costoPequeH,"Cancha Pequeña");
            }else{
                if (r2) {
                    alquilarCentro(costoMediaP,costoMediaH,"Cancha Mediana");
                }else{
                    if (r3){
                        alquilarCentro(costoLosaP,costoLosaH,"Losa Multiusos");
                    }
                }
            }
        }
    }

    /*cargarCentro
    * Funcion para cargar los datos de la BD en
    * los componentes de la vista
    * */
    private void cargarCentro(){
        String ip=getString(R.string.ip);
        String URL_centro=ip+"/ws/consulta_centro.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_centro,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject centro = array.getJSONObject(i);
                                if (i==0){
                                    id1=centro.getInt("idCancha");
                                    costoPequePG=centro.getInt("centroPG");
                                    costoPequeH=centro.getInt("centroH");
                                }if (i==1){
                                    id2=centro.getInt("idCancha");
                                    costoMediaP=centro.getInt("centroPG");
                                    costoMediaH=centro.getInt("centroH");
                                }
                                else {
                                    id3=centro.getInt("idCancha");
                                    costoLosaP=centro.getInt("centroPG");
                                    costoLosaH=centro.getInt("centroH");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mostrarToast("Ha ocurrido un error en la solicitud: "+error.toString());
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    /*
    * cargarAuditorios
     * Funcion para cargar los datos de la BD en
     * los componentes de la vista
    * */
    private void cargarAuditorios(){
        String ip = getString(R.string.ip);
        String URL_ambientes=ip+"/ws/consulta_auditorios.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_ambientes,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject ambiente = array.getJSONObject(i);
                                if (i==0){
                                    txtCosto2.setText(ambiente.getString("costo"));
                                    costo2=ambiente.getInt("costo");
                                    txtLimp2.setText(ambiente.getString("limp"));
                                    txtSonido2.setText(ambiente.getString("sonido"));
                                    txtMulti2.setText(ambiente.getString("multi"));
                                    txtIncluye2.setText( ambiente.getString("incluye"));
                                    txtCapac2.setText(ambiente.getString("capac"));
                                }else {
                                    txtCosto4.setText(ambiente.getString("costo"));
                                    costo4=ambiente.getInt("costo");
                                    txtLimp4.setText(ambiente.getString("limp"));
                                    txtSonido4.setText(ambiente.getString("sonido"));
                                    txtMulti4.setText(ambiente.getString("multi"));
                                    txtIncluye4.setText( ambiente.getString("incluye"));
                                    txtCapac4.setText(ambiente.getString("capac"));
                                }
                               }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mostrarToast("Ha ocurrido un error en la solicitud: "+error.toString());
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void mostrarToast(String mensaje){
        Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();
    }

    /*sumarHora
    * Funcion para aumentar el valor de la cantidad
    * de horas para alquilar
    * */
    public void sumarHora(View view){
        horasAlq = horasAlq +1;
        txtHora.setText(String.valueOf(horasAlq));
        txtHoras.setText("Horas");
    }

    /*restarrHora
     * Funcion para disminuir el valor de la cantidad
     * de horas para alquilar
     * */
    public void restarHora(View view){
        if (horasAlq ==1){
            txtHora.setText(String.valueOf(horasAlq));
        }else {
            horasAlq = horasAlq -1;
            txtHora.setText(String.valueOf(horasAlq));
            if (horasAlq ==1){
                txtHoras.setText("Hora");
            }
        }
    }

    /*regresar
     * Funcion usada para regresar a la activity anterior,
     * se enviara tambien el nroMatricula por el Intent.
     * */
    public void regresar(View view){
        Intent i = new Intent(getApplicationContext(), MenuServicio.class);
        i.putExtra("nro",nroMatricula);
        i.putExtra("habilitado",habilitado);
        startActivity(i);
    }

    /*obtenerFecha
    * Funcion para obtener la fecha del dia
    * para enviarla a la siguiente activity
    * */
    private void obtenerFecha(){
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/M/yyyy");
        fechaHoy= formatoFecha.format(fecha);
    }

    /*recibirIntent
    * Funcion para asignar las variables creadas
    * con los datos enviados por el Intent
    * */
    private void recibirIntent(){
        nroMatricula=getIntent().getStringExtra("nro");
        habilitado=getIntent().getIntExtra("habilitado",0);
    }

    /*vincularTienda
     * Funcion comun para vincular las variables creadas con los
     * componentes del activity_alquilar_ambientes
     * */
    private void vincularTienda(){
        txtCosto2=(TextView)findViewById(R.id.idTextCosto2);
        txtCosto4=(TextView)findViewById(R.id.idTextCosto4);
        txtLimp2=(TextView)findViewById(R.id.idTextLimp2);
        txtLimp4=(TextView)findViewById(R.id.idTextLimp4);
        txtSonido2=(TextView) findViewById(R.id.idTextSonido2);
        txtSonido4=(TextView) findViewById(R.id.idTextSonido4);
        txtMulti2=(TextView)findViewById(R.id.idTextMulti2);
        txtMulti4=(TextView)findViewById(R.id.idTextMulti4);
        txtIncluye2=(TextView)findViewById(R.id.idTextIncluye2);
        txtIncluye4=(TextView)findViewById(R.id.idTextIncluye4);
        txtCapac2=(TextView)findViewById(R.id.idTextCap2);
        txtCapac4=(TextView)findViewById(R.id.idTextCap4);
        txtHora=(TextView)findViewById(R.id.idTextHorasCentro);
        txtHoras=(TextView)findViewById(R.id.textoHoras);
        botonAlq2=(Button) findViewById(R.id.idBotonAlq2);
        botonAlq4=(Button)findViewById(R.id.idBotonAlq4);
        botonSumar=(Button)findViewById(R.id.idBotonSumarHoras);
        botonRestar=(Button)findViewById(R.id.idBotonRestarHoras);
        botonAlqCentro=(Button)findViewById(R.id.idBotonAlqCentro);
        radioGroup=(RadioGroup)findViewById(R.id.idRadioGrupoCentro);
        radioPeque=(RadioButton)findViewById(R.id.idRadioPeque);
        radioMedia=(RadioButton)findViewById(R.id.idRadioMedia);
        radioLosa=(RadioButton)findViewById(R.id.idRadioLosa);
        editFecha1=(EditText)findViewById(R.id.idEditFecha1);
        editFecha2=(EditText)findViewById(R.id.idEditFecha2);
        editFecha3=(EditText)findViewById(R.id.idEditFecha3);
        editHora=(EditText)findViewById(R.id.idEditHora);
    }
}