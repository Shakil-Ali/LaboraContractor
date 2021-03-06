package com.labora.laboracontractor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity2 extends AppCompatActivity implements View.OnClickListener {

    // Create and initialising ui features
    private EditText editTextFullName;
    private EditText editTextPhoneNumber;
    private EditText editTextOccupancy;
    private EditText editTextAddress;
    private Button buttonRegister;
    private static final String TAG = "RegisterActivity2";

    // Creating database variables
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore mFirestore;


    // On create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity2);

        //Getting firestore instance
        mFirestore = FirebaseFirestore.getInstance();

        // Variables to store the data
        editTextFullName = (EditText) findViewById(R.id.editTextFullName);
        editTextOccupancy = (EditText) findViewById(R.id.editTextOccupancy);
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        editTextAddress = (EditText) findViewById(R.id.editTextAd);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        //Initalise firebase
        firebaseAuth = FirebaseAuth.getInstance();


        // Conditional if statement - this gets the current users.
        if(firebaseAuth.getCurrentUser() != null){
            //profile activity

        }

        // Check if register button clicked
        buttonRegister.setOnClickListener(this);

    }


    // Register user function
    public void registerUser() {

        // Variables initialisation and assignment
        String name = editTextFullName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String phone = editTextPhoneNumber.getText().toString().trim();
        String occupation = editTextOccupancy.getText().toString().trim();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();


        // Check if name entered
        if(TextUtils.isEmpty(name)){
            //name is empty
            Toast.makeText(this, "Please enter an name", Toast.LENGTH_SHORT).show();
            return;

        }

        // Check if address is entered
        if(TextUtils.isEmpty(address)){
            //address is empty
            Toast.makeText(this, "Please enter a valid address", Toast.LENGTH_SHORT).show();
            return;

        }

        // Check if phone number is entered
        if(TextUtils.isEmpty(phone)){
            //phone number is empty
            Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
            return;

        }


        // Check if occupation is entered
        if(TextUtils.isEmpty(occupation)){
            //occupation is empty
            Toast.makeText(this, "Please enter occupation", Toast.LENGTH_SHORT).show();
            return;

        }


        // Create a map and store the details of the users
        Map<String, Object> user_contractor = new HashMap<>();
        user_contractor.put("fullname", name);
        user_contractor.put("address", address);
        user_contractor.put("phone", phone);
        user_contractor.put("occupation", occupation);
        user_contractor.put("userid", userId);

        // Store the map values in the database
        mFirestore.collection("Users-Contractor")
                .add(user_contractor)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        //Starts the menu activity
                        startActivity(new Intent(getApplicationContext(),  MenuActivity.class));

                    }
                })

                // will spit out an error if not there is an unexpected issue
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    // On click method
    public void onClick(View view){

        // Conditional if statement to check if register button is working
        if(view == buttonRegister){

            // Call register user
            registerUser();


        }
    }



}