package com.labora.laboracontractor;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.List;

import static android.support.customtabs.CustomTabsIntent.KEY_DESCRIPTION;
import static androidx.browser.browseractions.BrowserActionsIntent.KEY_TITLE;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    // Create user interface features we will be using
    GoogleMap map;

    // Creating database variables
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore mFirestore;
    private static final String TAG = "MapActivity";

    // On create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        
        //Initalise firebaseAuth and firestore
        firebaseAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        // Conditional if statement - this gets the current users information and whether it exists.
        if(firebaseAuth.getCurrentUser() != null){
            //profile activity

        }
        //This gets the maps on to display on the activity
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    //OnMapReady method created
    @Override
    public void onMapReady(GoogleMap googleMap) {

        //Instalising the object
        map = googleMap;

        //Coordinates hard coded as example for the map function working
        LatLng coords = new LatLng(51.474144, -0.035401);
        LatLng coords2 = new LatLng(51.476557, -0.036270);
        LatLng coords3 = new LatLng(51.472204, -0.034126);


        //Adding the coordinates to the map
        map.addMarker(new MarkerOptions().position(coords).title("Jane Doe, SE14 6NW").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        //The camera is focused on the points when the user clicks on the 'go online' button from the previous activity
        map.moveCamera(CameraUpdateFactory.newLatLng(coords));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(coords,14));

        //Adding the coordinates to the map
        map.addMarker(new MarkerOptions().position(coords2).title("Job has been accepted").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //The camera is focused on the points when the user clicks on the 'go online' button from the previous activity
        map.moveCamera(CameraUpdateFactory.newLatLng(coords2));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(coords2,14));

        //Adding the coordinates to the map
        map.addMarker(new MarkerOptions().position(coords3).title("Job has been rejected").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        //The camera is focused on the points when the user clicks on the 'go online' button from the previous activity
        map.moveCamera(CameraUpdateFactory.newLatLng(coords3));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(coords3,14));


        //This finds the title and starts the next activity
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.getTitle().equals("Jane Doe, SE14 6NW"))

                {

                    startActivity(new Intent(getApplicationContext(),  SummaryActivity.class));

                }



                return false;
            }
        });



    }


}
