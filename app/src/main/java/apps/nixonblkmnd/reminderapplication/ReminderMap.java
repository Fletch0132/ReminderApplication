package apps.nixonblkmnd.reminderapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

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
import com.google.android.libraries.places.api.Places;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission;

public class ReminderMap extends FragmentActivity {

    private GoogleMap mMap;
    private Geocoder geocoder;
    FusedLocationProviderClient client;
    ArrayList<String> locNames = new ArrayList<>();
    //ListView mapUpcoming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_map);
        //INITIALIZE FUSED LOCATION
        client = LocationServices.getFusedLocationProviderClient(this);
        Places.initialize(this, BuildConfig.GMP_KEY);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        //GOOGLE API - CONVERT LOCATION ID TO LAT LNG AND NAME
        geocoder = new Geocoder(this);



        //METHOD FOR USER LOCATION
        getCurrentLocation(mapFragment);

    }


    //RETRIEVE USER LOCATION AFTER PERMISSION CHECK
    public void getCurrentLocation(SupportMapFragment mapFragment) {
        Dexter.withContext(this).withPermission(permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                if ((ActivityCompat.checkSelfPermission(ReminderMap.this, permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(ReminderMap.this, "Location Permission not Enabled. Enable via Settings", Toast.LENGTH_SHORT).show();
                    return;
                }
                Task<Location> task = client.getLastLocation();
                task.addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        //SUCCESS
                        if (location != null) {
                            //USER LOCATION
                            mapFragment.getMapAsync(new OnMapReadyCallback() {
                                @Override
                                public void onMapReady(GoogleMap googleMap) {
                                    //GET LOCATION
                                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                    //GET MARKER OPTIONS
                                    MarkerOptions marker = new MarkerOptions().position(latLng);
                                    //FIX ZOOM - OTHERWISE TOO FAR OUT
                                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                                    //SET MARKER LOCATION
                                    googleMap.addMarker(marker);

                                    //SET LOCATION MARKERS
                                    PlaceMarkers(googleMap, locNames);
                                }
                            });
                        }
                    }
                });
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), "");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    //VIEW REMINDER LOCATIONS ON MAP WITH MARKER
    public void PlaceMarkers(GoogleMap googleMap, ArrayList<String> locNames) {
        mMap = googleMap;

        try {
            //GET LOCATION NAMES AND ID FROM DB
            //LOOP THROUGH LIST TO RETRIEVE LOCATION NAME AND LOCATION ID
            for (int i = 0; i < locNames.size(); i++) {
                List<Address> addresses = geocoder.getFromLocationName(locNames.get(i), 1);
                Address address = addresses.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                MarkerOptions marker = new MarkerOptions().position(latLng).title(address.getLocality());
                mMap.addMarker(marker);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

            //NEW MARKER
        }
    }





    //GET LAT LNG FROM LOCATIONID

