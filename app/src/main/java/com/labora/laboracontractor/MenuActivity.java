package com.labora.laboracontractor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    // Initialise variables
    private FirebaseAuth firebaseAuth;
    private Button buttonLogout;
    private Button buttonGoOnline;

    //oncreate method
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuactivity);

        // Assignment operations
        firebaseAuth = FirebaseAuth.getInstance();

        // Check if current user exists
        if(firebaseAuth.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        // Get current user
        FirebaseUser user = firebaseAuth.getCurrentUser();



        // Buttons on page
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonGoOnline = (Button) findViewById(R.id.buttonGoOnline);

        // Setting onclick listeners for buttons on page
        buttonLogout.setOnClickListener(this);
        buttonGoOnline.setOnClickListener(this);

    }

    //Onclick method
    @Override
    public void onClick(View view)
    {
        if(view == buttonLogout)
        {
            // Sign out method from fire base database
            firebaseAuth.signOut();

            //Ends current activity
            finish();

            //Starts new activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        if(view == buttonGoOnline)
        {
            //Ends current activity
            finish();

            //Starts new activity
            startActivity(new Intent(this, MapActivity.class));
        }

    }

}
