package com.example.servicios.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.servicios.MenuServicio;
import com.example.servicios.R;

public class ActivityISS extends AppCompatActivity {
    /*Variables que hacen referencia a los componentes del activity*/
    ImageView word1,word2,word3,word4,word5,word6;
    /*Variables del Intent*/
    String nroMatricula;
    int habilitado;
    /*Enlaces a los Word del ActivityISS*/
    private String url1 ="https://www.cip.org.pe/publicaciones/2021/abril/portal/carta-declaratoria-de-beneficiarios-iss-cip-aprobado-en-cnccdd-24-04-2020.pdf";
    private String url2 ="https://www.cip.org.pe/publicaciones/2022/enero/portal/dec-jur-solic-de-asig-por-fallec-de-titular-27012022.docx";
    private String url3 ="https://www.cip.org.pe/publicaciones/2022/enero/portal/dec-jur-solic-de-asig-por-fallec-de-conyuge-27012022.docx";
    private String url4 ="https://www.cip.org.pe/publicaciones/2022/enero/portal/dec-jur-solic-de-asig-por-fallec-hijos-men-18-anos-27012022.docx";
    private String url5 ="https://www.cip.org.pe/publicaciones/2022/enero/portal/dec-jur-solic-de-asig-por-inval-pte-de-titular-27012022.docx";
    private String url6 ="https://www.cip.org.pe/publicaciones/2022/enero/portal/dec-jur-solic-de-apoyo-solid-de-titular-27012022.docx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iss);
        vincularIss();
        nroMatricula=getIntent().getStringExtra("nro");
        habilitado=getIntent().getIntExtra("habilitado",0);
        /*Ponemos a escuchar las ImageView cuando reciban un click*/
        word1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(url1);
                Intent i = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        });
        word2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(url2);
                Intent i = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        });
        word3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(url3);
                Intent i = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        });
        word4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(url4);
                Intent i = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        });
        word5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(url5);
                Intent i = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        });
        word6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(url6);
                Intent i = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        });
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

    /*mostrarToast
    * Funcion comun para mostrar algun mensaje.
    * */
    private void mostrarToast(String mensaje){
        Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();
    }

    /*vincularIss
    * Funcion comun para vincular las variables creadas con los
    * componentes del activity_iss.xml
    * */
    private void vincularIss(){
        word1=(ImageView)findViewById(R.id.idWord1);
        word2=(ImageView)findViewById(R.id.idWord2);
        word3=(ImageView)findViewById(R.id.idWord3);
        word4=(ImageView)findViewById(R.id.idWord4);
        word5=(ImageView)findViewById(R.id.idWord5);
        word6=(ImageView)findViewById(R.id.idWord6);
    }
}