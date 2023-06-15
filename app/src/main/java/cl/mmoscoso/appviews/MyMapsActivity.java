package cl.mmoscoso.appviews;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;

import java.util.ArrayList;

import cl.mmoscoso.appviews.databinding.ActivityMiMapsBinding;


public class MyMapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {


    private GoogleMap mMap;
    private float zoomLevel = 15.0f;

    private Marker currentLocation;

    private ActivityMiMapsBinding binding;


    private LocationManager locationManager;
    private LatLng lastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Location Marker
        currentLocation = null;

        //LocationManager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        startLocationUpdates();
//        // Check if the ACCESS_FINE_LOCATION permission is granted
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // Request the permission if not granted
//            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//        } else {
//            // Permission already granted, start requesting location updates
//            startLocationUpdates();
//        }

        binding = ActivityMiMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private void startLocationUpdates() {
        // Check if GPS provider is enabled
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // Request location updates with a minimum time interval of 15 seconds and minimum distance change of 15 meters
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15, 15, this);
            // Get the last known location
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation != null) {
                // Do something with the last known location
                double latitude = lastKnownLocation.getLatitude();
                double longitude = lastKnownLocation.getLongitude();
                if (mMap != null) {
                    LatLng latLng = new LatLng(latitude, longitude);
                    if (currentLocation == null) {
                        MarkerOptions markerOptions = new MarkerOptions()
                                .position(latLng)
                                .title("Current Location");
                        currentLocation = mMap.addMarker(markerOptions);

                    }

                }
            }
        }
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
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);

        currentLocation = null;


//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        ArrayList<PointOfInterest> poiLocations = getIntent().getParcelableArrayListExtra("poiList");
        int iconFirst = 0;
        // Use the retrieved locations as needed in your activity
        for (PointOfInterest location : poiLocations) {
            MarkerOptions markerOptions = new MarkerOptions().position(location.latLng);
            if (iconFirst == 0) {
                BitmapDescriptor bitmapExample = BitmapDescriptorFactory.fromResource(R.drawable.market);
                iconFirst++;
                markerOptions.icon(bitmapExample);
            }
            markerOptions.title(location.name);
            mMap.addMarker(markerOptions);
        }

        mMap.moveCamera(CameraUpdateFactory.zoomTo(13));
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        // Add a marker at the current location
        if (mMap != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            if (currentLocation == null) {
                MarkerOptions markerOptions = new MarkerOptions()
                        .position(latLng)
                        .title("Current Location");

                currentLocation = mMap.addMarker(markerOptions);
            } else {
                currentLocation.setPosition(latLng);
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Handle status changes
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}