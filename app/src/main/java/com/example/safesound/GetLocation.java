package com.example.safesound;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class GetLocation extends AppCompatActivity {

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);


        supportMapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.google_map);

        client= LocationServices.getFusedLocationProviderClient(this);

        //check permission
        if(ActivityCompat.checkSelfPermission(GetLocation.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            //granted
            //call method
            if(enableLocation()){
                getCurrentLocation();
            }
            else{
                Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(settingsIntent);
            }

        }else{
            //Request For Permission if denied
            ActivityCompat.requestPermissions(GetLocation.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);

        }

    }

    private boolean enableLocation() {
        LocationManager locationManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return gpsEnabled;

    }

    private void getCurrentLocation() {
        //initializing Task Location
        if(ActivityCompat.checkSelfPermission(GetLocation.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            //enableLocation();
        }

        Task<Location> task=client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //success
                if (location != null){
                    //sync map
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            //initialise lat lng
                            LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
                            //creating marker options
                            MarkerOptions options=new MarkerOptions().position(latLng).title("You Are Here");
                            //Zoom Map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                            //Adding Marker on Map
                            googleMap.addMarker(options);
                        }
                    });
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Call Method if granted
                if(enableLocation()){
                    getCurrentLocation();
                }
                else{
                    Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(settingsIntent);
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
