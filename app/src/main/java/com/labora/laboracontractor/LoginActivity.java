//With help of https://www.youtube.com/watch?v=tJVBXCNtUuk&t=2s - Simplified Coding with the code

package com.labora.laboracontractor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


// Public class login activity
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    // Create all the UI features we will be using
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignUp;


    // Create the database variables
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    // On create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);


        //Initalise firebase
        firebaseAuth = FirebaseAuth.getInstance();

        // Conditional to check if current user exists for this activity
        if(firebaseAuth.getCurrentUser() != null){
            //profile activity

        }

        //Initalise the objects
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);

        progressDialog = new ProgressDialog(this);

        // Set on click listeners for both buttons
        buttonSignIn.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);


    }

    private void userLogin(){

        // Create strings to store username and password
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

        // Dialog to tell user they are being signed in
        progressDialog.setMessage("Signing in...");
        progressDialog.show();

        // Checking username & password against database
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        progressDialog.dismiss();

                        // If their account details exits, log them in
                        if(task.isSuccessful())
                        {
                            // Finish current activity
                            finish();
                            //Start the next activity
                            startActivity(new Intent(getApplicationContext(),  MenuActivity.class));

                        }

                        // Else they dont have an account
                        else {
                            // Toast to inform the user
                            Toast.makeText(LoginActivity.this, "Please enter valid login credentials or register an account", Toast.LENGTH_SHORT).show();
                        }


                    }
                });


    }

    // On click method
    @Override
    public void onClick(View view) {
        // Conditional to check if sign in button clicked
        if(view == buttonSignIn){
            //call login function
            userLogin();
        }

        // Conditional to check if sign up button clicked
        if(view == buttonSignUp){
            // Finish the current activity
            finish();
            // Start and open the Register Activity
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }
}
