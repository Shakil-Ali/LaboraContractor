package com.labora.laboracontractor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// Done activity
public class DoneActivity extends AppCompatActivity implements View.OnClickListener {

    private Button backtoMM;

    //On create function added
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        //Initalise the button object
        backtoMM = (Button) findViewById(R.id.backtoMM);

        //Adding setOnClick on the button
        backtoMM.setOnClickListener(this);


    }

    // On click method
    @Override
    public void onClick(View view) {

        // Conditional to check if sign up button clicked
        if(view == backtoMM){
            // Finish the current activity
            finish();
            // Start and open the Register Activity
            startActivity(new Intent(this, MenuActivity.class));
        }
    }

}
