package com.example.policeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Map extends AppCompatActivity implements OnMapReadyCallback {
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "ready to use", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Map is ready to go");
        mMap = googleMap;
        if (mLocationPermissionGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
            !=PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED) {

                return;
            }
            mMap.setMyLocationEnabled(true);

        }

    }
    private static final String TAG="Map";

    private static final String FINE_LOCATION=Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION=Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final  int LOCATION_PERMISSION_REQUEST_CODE=1234;
    private static final float DEFAULT_ZOOM=15f;
    //vars
    private Boolean mLocationPermissionGranted=false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getLocationPermisssion();

    }
    private void getDeviceLocation(){
        Log.d(TAG,"getDeviceLocation: current location");
        mFusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        try{
              if(mLocationPermissionGranted){
                  Task location =mFusedLocationProviderClient.getLastLocation();
                  location.addOnCompleteListener(new OnCompleteListener() {
                      @Override
                      public void onComplete(@NonNull Task task) {
                          if(task.isSuccessful()){
                              Log.d(TAG,"OnComplte: Location found.");
                              Location currentLocation=(Location)task.getResult();
                              moveCamera(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()),
                                      DEFAULT_ZOOM);

                          }else{
                              Log.d(TAG,"OnComplte: Location is null.");
                              Toast.makeText(Map.this,"unable to find current location",Toast.LENGTH_SHORT).show();
                          }

                      }
                  });
              }
        }catch (SecurityException e){
            Log.d(TAG,"getDeviceLocation:securityException:"+ e.getMessage());
        }

    }
    private void moveCamera(LatLng latLng,float zoom){
        Log.d(TAG,"moveCamera: moving camera to: lat:" + latLng.latitude + ",lng:" + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
    }

    private void initMap(){
        Log.d(TAG,"initMap:Map Initialised");
        SupportMapFragment mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(Map.this);

    }
    // check the method permission
    private void getLocationPermisssion(){
        Log.d(TAG,"getLocationPermission:location permited");
        String[]permission={Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION};
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION)==PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted=true;
                initMap();
            }
            else{
                ActivityCompat.requestPermissions(this,permission,LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,permission,LOCATION_PERMISSION_REQUEST_CODE);
        }

        }

        public void onRequestPermissionResult(int requestCode, @NonNull String[]permission, @NonNull int[]grantResults){
            Log.d(TAG,"onRequestPermissionResult: called");
        mLocationPermissionGranted=false;
        switch (requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length >0){
                    for(int i=0; i<grantResults.length;i++){
                        if(grantResults[i] !=PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionGranted=false;
                            Log.d(TAG,"onRequestPermissionResult: permission failed");
                            return;
                        }

                    }
                    Log.d(TAG,"onRequestPermissionResult: permission Granted");
                    mLocationPermissionGranted=true;
                    //initialise the map
                    initMap();
                }
            }
        }
        }


}

