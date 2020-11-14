package ca.georgebrown.comp3074.restaurants;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
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
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);

        // mMap.getUiSettings().setCompassEnabled(true);
        float zoomLevel = (float) 16.0;

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        double[] location = extras.getDoubleArray("coordinates");

        LatLng latLng= null;

        if (location != null) {
            // Add a marker in Sydney and move the camera
            latLng = new LatLng(location[0], location[1]);

        } else {

//            LatLngBounds AUSTRALIA = new LatLngBounds(
//                    new LatLng(-44, 113), new LatLng(-10, 154));

            // Set the camera to the greatest possible zoom level that includes the
            // bounds
            // mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(AUSTRALIA, 10));

            latLng = new LatLng(43.641804, -79.386491);
        }

        mMap.addMarker(new MarkerOptions().position(latLng).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

    }
}
