package br.com.alura.agenda;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.modelo.Aluno;

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng posicaoCentral = pegaCoordenadaDoEndereco("Westminster, Londres SW1A 0AA, Reino Unido");
        if(posicaoCentral != null) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoCentral, 3);
            googleMap.moveCamera(update);
        }

        AlunoDAO characterDAO = new AlunoDAO(getContext());
        for(Aluno character : characterDAO.buscaCharacters()){
            LatLng coordenada = pegaCoordenadaDoEndereco(character.getPais());
            if(coordenada != null){
                MarkerOptions marcador = new MarkerOptions();
                marcador.position(coordenada);
                marcador.title(character.getNome());
                marcador.snippet(String.valueOf(character.getPower()));
                googleMap.addMarker(marcador);
            }
        }
        characterDAO.close();
    }

    private LatLng pegaCoordenadaDoEndereco(String endereco){
        Geocoder geocoder = new Geocoder(getContext());
        try {
            List<Address> resultados = geocoder.getFromLocationName(endereco, 1);
            if(!resultados.isEmpty()){
               LatLng posicao = new LatLng(resultados.get(0).getLatitude(), resultados.get(0).getLongitude());
               return posicao;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
