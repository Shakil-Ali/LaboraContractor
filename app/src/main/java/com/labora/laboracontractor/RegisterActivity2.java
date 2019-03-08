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

    private EditText editTextFullName;
    private EditText editTextPhoneNumber;
    private EditText editTextOccupancy;
    private EditText editTextAddress;
    private Button buttonRegister;
    private static final String TAG = "RegisterActivity2";

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity2);

        mFirestore = FirebaseFirestore.getInstance();

        editTextFullName = (EditText) findViewById(R.id.editTextFullName);
        editTextOccupancy = (EditText) findViewById(R.id.editTextOccupancy);
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        editTextAddress = (EditText) findViewById(R.id.editTextAd);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        //Initalise firebase
        firebaseAuth = FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser() != null){
            //profile activity

        }
        buttonRegister.setOnClickListener(this);

    }

    public void registerUser() {


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


        Map<String, Object> user_contractor = new HashMap<>();
        user_contractor.put("fullname", name);
        user_contractor.put("address", address);
        user_contractor.put("phone", phone);
        user_contractor.put("occupation", occupation);
        user_contractor.put("userid", userId);

        mFirestore.collection("Users-Contractor")
                .add(user_contractor)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        startActivity(new Intent(getApplicationContext(),  MenuActivity.class));

                    }
                })


                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void onClick(View view){
        if(view == buttonRegister){

            registerUser();


        }
    }



}