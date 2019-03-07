package com.labora.laboracontractor;

import android.content.Context;
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


//    public GeoPoint getLocationFromAddress() {
//
//        DocumentReference strAddress = FirebaseFirestore.getInstance().collection("Summary-ServiceRequester").document("Post Code");
//
//        Geocoder coder = new Geocoder(this);
//        List<Address> address;
//        GeoPoint p1 = null;
//
//        try {
//            address = (List<Address>) coder.getFromLocationName(String.valueOf(strAddress),5);
//            if(address==null){
//                return null;
//            }
//
//            Address location =address.get(0);
//            location.getLatitude();
//            location.getLongitude();
//
//            System.out.println();
//            p1 = new GeoPoint((double) (location.getLatitude() * 1E6),(double) (location.getLongitude() * 1E6));
//
//            return p1;
//
//
//        }
//
//        catch (IOException e)
//        {
//            e.printStackTrace();
//            return null;
//        }
//
//    }

    public LatLng getLocationFromAddress(Context context, String inputtedAddress) {

        DocumentReference strAddress = FirebaseFirestore.getInstance().collection("Summary-ServiceRequester").document("Post Code");

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng resLatLng = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(String.valueOf(strAddress), 5);
            System.out.print(address);
            if (address == null) {
                return null;
            }

            if (address.size() == 0) {
                return null;
            }

            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            resLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (IOException ex) {

            ex.printStackTrace();
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        return resLatLng;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;


        LatLng coords = new LatLng(19.169257, 73.341601);
        map.addMarker(new MarkerOptions().position(coords).title("Client"));
        map.moveCamera(CameraUpdateFactory.newLatLng(coords));

    }



}
