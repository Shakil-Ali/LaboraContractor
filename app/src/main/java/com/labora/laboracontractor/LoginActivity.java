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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignUp;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);


        //Initalise firebase
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //profile activity

        }

        //Initalise the objects
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);

        progressDialog = new ProgressDialog(this);

        buttonSignIn.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);


    }

    private void userLogin(){
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

        progressDialog.setMessage("Signing in...");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        progressDialog.dismiss();

                        if(task.isSuccessful())
                        {
                            finish();

                        }

                    }
                });


    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignIn){
            userLogin();
        }

        if(view == buttonSignUp){
            finish();
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }
}
