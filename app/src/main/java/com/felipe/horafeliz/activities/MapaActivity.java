package com.felipe.horafeliz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.felipe.horafeliz.R;
import com.felipe.horafeliz.model.Bar;
import com.felipe.horafeliz.model.BarDao;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private MapView mapaDoGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        mapaDoGoogle = findViewById(R.id.mapa_locais);
        mapaDoGoogle.onCreate(savedInstanceState);
        mapaDoGoogle.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setOnMarkerClickListener(this);

        LatLngBounds.Builder limiteLatLong = new LatLngBounds.Builder();

        ArrayList<Bar> listaCompromisso = BarDao.obterInstancia().listarBares();
        for(Bar bar : listaCompromisso){
            if(bar.getLatitude() == null || bar.getLongitude() == null){
                continue;
            }
            MarkerOptions marcador = new MarkerOptions();
            LatLng posicaoDoMarcador = new LatLng(bar.getLatitude(), bar.getLongitude());
            marcador.position(posicaoDoMarcador);
            marcador.title( bar.getNome() );
            googleMap.addMarker( marcador );
            limiteLatLong.include(posicaoDoMarcador);
        }
        LatLngBounds limiteDoMapa = limiteLatLong.build();
        CameraUpdate cameraDoMapa = CameraUpdateFactory.newLatLngBounds(limiteDoMapa, 200);
        googleMap.moveCamera( cameraDoMapa );

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapaDoGoogle.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapaDoGoogle.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapaDoGoogle.onPause();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this, marker.getTitle(), Toast.LENGTH_LONG).show();
        return false;
    }
}
