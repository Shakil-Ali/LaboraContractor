package com.labora.laboracontractor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SummaryActivity extends AppCompatActivity implements View.OnClickListener {

     // Initialise variables
    private Button buttonAccept;
    private Button buttonReject;

    // On create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // Buttons on page
        buttonAccept = (Button) findViewById(R.id.acceptBtn);
        buttonReject = (Button) findViewById(R.id.rejectBtn);

        // Setting onclick listeners for buttons on page
        buttonAccept.setOnClickListener(this);
        buttonReject.setOnClickListener(this);

    }

    public void onClick(View view){

        // Conditional to check if accept button clicked
        if(view == buttonAccept){

            // Initialise and starts the new activity - goes to the main menu
            startActivity(new Intent(getApplicationContext(),  DoneActivity.class));


        }

        // Conditional to check if reject button clicked
        if(view == buttonReject){

            // Initialise and starts the new activity - goes to the main menu
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));


        }
    }

}
