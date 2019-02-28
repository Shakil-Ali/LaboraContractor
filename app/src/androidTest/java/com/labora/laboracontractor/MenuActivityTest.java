package com.labora.laboracontractor;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MenuActivityTest {


    // Rule created, which will be required for this test
    @Rule
    public ActivityTestRule<MenuActivity> mActivityTestRule = new ActivityTestRule<MenuActivity>(MenuActivity.class);

    //Private variable of LoginActivity
    private MenuActivity menuActivity = null;

    // Monitor the Register activity
    Instrumentation.ActivityMonitor monitorActivity = getInstrumentation().addMonitor(LoginActivity.class.getName(), null, false);

    // Monitor the Register activity
    Instrumentation.ActivityMonitor monitorActivity2 = getInstrumentation().addMonitor(MapActivity.class.getName(), null, false);

    // Set up the function when launch starting
    @Before
    public void setUp() throws Exception
    {

        // Creating and initialising a private variable of type LoginActivity for use later in the tests
        menuActivity = mActivityTestRule.getActivity();


    }


    @Test
    public  void testLaunch()
    {
        // Attempt to launch the app and find the id of one of the components in menu Activity
        View view = menuActivity.findViewById(R.id.buttonGoOnline);
        // Check if launch is not null
        assertNotNull(view);

    }


    @Test
    public  void testOpenMap()
    {
        // Check if it does not return null
        assertNotNull(menuActivity.findViewById(R.id.buttonGoOnline));

        // Take the view with go online button id
        onView(withId(R.id.buttonGoOnline)).perform(click());

        // Wait for monitor to be hit and then expires in 50000000 ms
        Activity MapActivity = getInstrumentation().waitForMonitorWithTimeout(monitorActivity2, 50000000);

        // Check that the mapActivity  is not null
        assertNotNull(MapActivity);

        // Finish the opened activity
        MapActivity.finish();


    }

    @Test
    public  void testOpenLogOut()
    {
        // Check if it does not return null
        assertNotNull(menuActivity.findViewById(R.id.buttonLogout));

        // Take the view with buttonLogout button id
        onView(withId(R.id.buttonLogout)).perform(click());

        // Wait for monitor to be hit and then expires in 50000000 ms
        Activity LoginActivity = getInstrumentation().waitForMonitorWithTimeout(monitorActivity, 50000000);

        // Check that the login activity is not null
        assertNotNull(LoginActivity);

        // Finish the opened activity
        LoginActivity.finish();

    }


    @After
    public void tearDown() throws Exception
    {
        // Nullify the activity after it has/has not launched
        mActivityTestRule = null;

    }
}