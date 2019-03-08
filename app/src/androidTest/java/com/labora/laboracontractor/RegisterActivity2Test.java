package com.labora.laboracontractor;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class RegisterActivity2Test {


    // Rule created, which will be required for this test
    @Rule
    public ActivityTestRule<RegisterActivity2> mActivityTestRule = new ActivityTestRule<RegisterActivity2>(RegisterActivity2.class);

    //Private variable of registerActivity2
    private RegisterActivity2 registerActivity2 = null;

    // Monitor the Register activity
//    Instrumentation.ActivityMonitor monitorActivity = getInstrumentation().addMonitor(RegisterActivity.class.getName(), null, false);

    // Monitor the menu activity
    Instrumentation.ActivityMonitor monitorActivity2 = getInstrumentation().addMonitor(MenuActivity.class.getName(), null, false);

    private String nName = "Thomas";
    private String nNumber = "0208528173818";
    private String nOccupation = "Plumber";
    private String nAd = "50 Billet Road, Romford";


    // Set up the function when launch starting
    @Before
    public void setUp() throws Exception
    {

        // Creating and initialising a private variable of type registerActivity2 for use later in the tests
        registerActivity2 = mActivityTestRule.getActivity();


    }


    @Test
    public  void testLaunch()
    {
        // Attempt to launch the app and find the id of one of the components in registerActivity2
        View view = registerActivity2.findViewById(R.id.buttonRegister);
        // Check if launch is not null
        assertNotNull(view);

    }


    @Test
    public  void testOpenMenu()
    {

        // Input some text in edit text
        Espresso.onView(withId(R.id.editTextFullName)).perform(typeText(nName));
        Espresso.onView(withId(R.id.editTextPhoneNumber)).perform(typeText(nNumber));
        Espresso.onView(withId(R.id.editTextOccupancy)).perform(typeText(nOccupation));
        Espresso.onView(withId(R.id.editTextAd)).perform(typeText(nAd));

        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click
        Espresso.onView(withId(R.id.buttonRegister)).perform(click());
        // Check if it does not return null
        assertNotNull(registerActivity2.findViewById(R.id.buttonRegister));

        // Take the view with signup button id
        onView(withId(R.id.buttonRegister)).perform(click());

        // Wait for monitor to be hit and then expires in 50000000 ms
        Activity menuActivity = getInstrumentation().waitForMonitorWithTimeout(monitorActivity2, 50000000);

        // Check that the menuActivity  is not null
        assertNotNull(menuActivity);

        // Finish the opened activity
        menuActivity.finish();


    }

    @Test
    // Testing address field with empty post address
    public void testAddressField()
    {
        // Input some text in edit text
        Espresso.onView(withId(R.id.editTextFullName)).perform(typeText(nName));
        Espresso.onView(withId(R.id.editTextPhoneNumber)).perform(typeText(nNumber));
        Espresso.onView(withId(R.id.editTextOccupancy)).perform(typeText(nOccupation));
        Espresso.onView(withId(R.id.editTextAd)).perform(typeText(""));


        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click
        Espresso.onView(withId(R.id.buttonRegister)).perform(click());
    }

    @Test
    // Testing occupancy field with empty occupancy
    public void testOccupancyField()
    {

        // Input some text in edit text
        Espresso.onView(withId(R.id.editTextFullName)).perform(typeText(nName));
        Espresso.onView(withId(R.id.editTextPhoneNumber)).perform(typeText(nNumber));
        Espresso.onView(withId(R.id.editTextOccupancy)).perform(typeText(""));
        Espresso.onView(withId(R.id.editTextAd)).perform(typeText(nAd));

        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click
        Espresso.onView(withId(R.id.buttonRegister)).perform(click());
    }

    @Test
    // Testing phone number field with empty number
    public void testPhoneField()
    {
        // Input some text in edit text
        Espresso.onView(withId(R.id.editTextFullName)).perform(typeText(nName));
        Espresso.onView(withId(R.id.editTextPhoneNumber)).perform(typeText(""));
        Espresso.onView(withId(R.id.editTextOccupancy)).perform(typeText(nOccupation));
        Espresso.onView(withId(R.id.editTextAd)).perform(typeText(nAd));

        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click
        Espresso.onView(withId(R.id.buttonRegister)).perform(click());
    }

    @Test
    // Testing name field with empty name
    public void testNameField()
    {
        // Input some text in edit text
        Espresso.onView(withId(R.id.editTextFullName)).perform(typeText(""));
        Espresso.onView(withId(R.id.editTextPhoneNumber)).perform(typeText(nNumber));
        Espresso.onView(withId(R.id.editTextOccupancy)).perform(typeText(nOccupation));
        Espresso.onView(withId(R.id.editTextAd)).perform(typeText(nAd));

        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click
        Espresso.onView(withId(R.id.buttonRegister)).perform(click());

    }

    @Test
    // Testing user email
    public void testUserInputScenario()
    {
        // Input some text in edit text
        Espresso.onView(withId(R.id.editTextFullName)).perform(typeText(nName));
        Espresso.onView(withId(R.id.editTextPhoneNumber)).perform(typeText(nNumber));
        Espresso.onView(withId(R.id.editTextOccupancy)).perform(typeText(nOccupation));
        Espresso.onView(withId(R.id.editTextAd)).perform(typeText(nAd));

        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click
        Espresso.onView(withId(R.id.buttonRegister)).perform(click());

    }


//    @Test
//    public  void testOpenMenu()
//    {
//        // Check if it does not return null
//        assertNotNull(loginActivity.findViewById(R.id.buttonSignIn));
//
//        // Take the view with signin button id
//        onView(withId(R.id.buttonSignIn)).perform(click());
//
//        // Wait for monitor to be hit and then expires in 50000000 ms
//        Activity MenuActivity = getInstrumentation().waitForMonitorWithTimeout(monitorActivity2, 50000000);
//
//        // Check that the menu activity is not null
//        assertNotNull(MenuActivity);
//
//        // Finish the opened activity
//        MenuActivity.finish();
//
//    }


    @After
    public void tearDown() throws Exception
    {
        // Nullify the activity after it has/has not launched
        mActivityTestRule = null;

    }
}