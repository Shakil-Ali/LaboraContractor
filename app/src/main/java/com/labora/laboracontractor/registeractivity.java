package com.labora.laboracontractor;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class registeractivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog ProgressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialise firebaseauth
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialise progressDialog object
        ProgressDialog = new ProgressDialog(this);

        // Initialise register button
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        // Set variable values
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        // Set onClick listeners
        buttonRegister.setOnClickListener(this);
    }

    private void registerUser() {
        // Create strings for email and password
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Check if email entered
        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;

        }

        // Check if password is entered
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_SHORT).show();
            return;

        }

        ProgressDialog.setMessage("Registering email and password...");
        ProgressDialog.show();

        // Registering user to database
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            // User succesfully registers and logged in
                            // will start profile activity here
                            // Temp toast
                            Toast.makeText(registeractivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(registeractivity.this, "Failed to register. Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    @Override
    public void onClick (View view){

        if(view == buttonRegister) {
            registerUser();
        }




    }



}
