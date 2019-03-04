package com.labora.laboracontractor;

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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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

    GoogleMap map;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore mFirestore;
    private static final String TAG = "MapActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        
        //Initalise firebaseAuth and firestore
        firebaseAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();


        if(firebaseAuth.getCurrentUser() != null){
            //profile activity

        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

//    public void getUserInformation(View v) {
//
//        noteRef.get()
//                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        if (documentSnapshot.exists()) {
//                            String postcode = documentSnapshot.getString(KEY_TITLE);
//                            String description = documentSnapshot.getString(KEY_DESCRIPTION);
//
//                            //Map<String, Object> note = documentSnapshot.getData();
//
////                            textViewData.setText("Title: " + postcode + "\n" + "Description: " + description);
//                        } else {
//                            Toast.makeText(MapActivity.this, "Document does not exist", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(MapActivity.this, "Error!", Toast.LENGTH_SHORT).show();
//                        Log.d(TAG, e.toString());
//                    }
//                });
//
//    }


    public GeoPoint getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        GeoPoint p1 = null;

        try {
            address = coder.getFromLocationName(strAddress,5);
            if(address==null){
                return null;
            }

            Address location =address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new GeoPoint((double) (location.getLatitude() * 1E6),(double) (location.getLongitude() * 1E6));

            return p1;


        }

        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;


        LatLng coords = new LatLng(19.169257, 73.341601);
        map.addMarker(new MarkerOptions().position(coords).title("Client"));
        map.moveCamera(CameraUpdateFactory.newLatLng(coords));

    }



}
