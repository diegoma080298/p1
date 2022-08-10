package com.example.servicios;

import android.content.Intent;
import android.os.Bundle;

import com.example.servicios.fragments.FragmentHistorialAlquiler;
import com.example.servicios.fragments.FragmentHistorialCompras;
import com.example.servicios.fragments.FragmentHistorialCuotas;
import com.example.servicios.ui.main.PlaceholderFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.servicios.ui.main.SectionsPagerAdapter;

public class PagosHistorial extends AppCompatActivity {
    public String NRO_FRAGMENT;
    String nroMatricula;
    int habilitado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagos_historial);
       recibirIntent();
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);

        Bundle args = new Bundle();
        args.putString("nro",nroMatricula);
        tabs.setupWithViewPager(viewPager);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Bundle args = new Bundle();
                args.putString("nro",nroMatricula);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }

    public String getNro(){
        return nroMatricula;
    }

    private void recibirIntent(){
        nroMatricula=getIntent().getStringExtra("nro");
        habilitado=getIntent().getIntExtra("habilitado",0);
        NRO_FRAGMENT=nroMatricula;
    }

    public void regresar(View view){
        Intent i = new Intent(getApplicationContext(), MenuServicio.class);
        i.putExtra("nro",nroMatricula);
        i.putExtra("habilitado",habilitado);
        startActivity(i);
    }
}