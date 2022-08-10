package com.example.servicios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.servicios.R;
import com.example.servicios.databinding.ActivityUbicacionBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ActivityUbicacion extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityUbicacionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUbicacionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        Siguiente(googleMap);




        // Add a marker in Sydney and move the camera
    }

    public void Siguiente(GoogleMap googleMap){
        mMap = googleMap;
        LatLng CIP = new LatLng(-9.12831445401715, -78.5211649704482);
        mMap.addMarker(new MarkerOptions().position(CIP).title("COLEGIO DE INGENIEROS DEL PERÚ C.D. ANCASH – CHIMBOTE, Av. Pacífico, Mz. C1 Lt. 11 Urb. Casuarinas 2da. Etapa” a “COLEGIO DE INGENIEROS DEL PERÚ C.D. ANCASH – CHIMBOTE, Av. Pacífico, Mz. C1 Lt. 11 Urb. Casuarinas 2da. Etapa"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(CIP));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(CIP)
                .zoom(18)
                .bearing(70)
                .tilt(45)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}