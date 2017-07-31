package it.assini.test.androidtest3;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
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

        LatLng casaVirle = new LatLng(45.50622222, 10.33125);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mapPointCamera(casaVirle);
    }

    private void mapPointCamera(LatLng casaVirle) {
        //mMap.addMarker(new MarkerOptions().position(casaVirle).title("Marker in Casa Virle"));
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String test = getIntent().getStringExtra("POINTS");
            //mMap.addMarker(new MarkerOptions().position(casaVirle).title(test));

            String[] array=extras.getStringArray("ARRAY");
            Log.d("MapsActivity", ""+array.length);
            for (int i = 0; i<array.length; i++){
                Log.d("MapsActivity", "["+array[i]+"]");
                double lat = Double.parseDouble(array[i].split(",")[0]);
                double lng = Double.parseDouble(array[i].split(",")[1]);
                mMap.addMarker(new MarkerOptions().position(new LatLng(lat,lng)).title(test));
            }


        }



        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(casaVirle, 20.0f)); //12.0f città, 24.0f via

    }
}
