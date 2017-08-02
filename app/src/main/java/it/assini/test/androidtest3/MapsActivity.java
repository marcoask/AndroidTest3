package it.assini.test.androidtest3;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //LatLng casaVirle = new LatLng(45.50622222, 10.33125);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mapPointCamera();
    }

    private void mapPointCamera() {

        Bundle extras = getIntent().getExtras();
        Log.d("MapsActivity", "extras.toString():"+extras.toString());
        if (extras != null) {

            String risultato = getIntent().getStringExtra("RISULTATO");
            LatLng latLngRisultato = string2Latlng(risultato);
            mMap.addMarker(new MarkerOptions().position(latLngRisultato).title("Risultato").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngRisultato, 16.0f)); //12.0f città, 24.0f via

            String[] latlngArray=extras.getStringArray("LATLNGARRAY");
            Log.d("MapsActivity", "array.length:"+latlngArray.length);
            String[] etichetteArray=extras.getStringArray("ETICHETTEARRAY");
            for (int i = 0; i<latlngArray.length; i++){
                Log.d("MapsActivity", "array:["+latlngArray[i]+"]");
                mMap.addMarker(new MarkerOptions().position(string2Latlng(latlngArray[i])).title(etichetteArray[i]));

            }

        }

    }

    private LatLng string2Latlng(String stringa) {
        LatLng latLng;
        double lat = Double.parseDouble(stringa.split(",")[0]);
        double lng = Double.parseDouble(stringa.split(",")[1]);
        return (latLng = new LatLng(lat,lng));
    }
}

//TODO: indicazioni stradali tra punti e risultato