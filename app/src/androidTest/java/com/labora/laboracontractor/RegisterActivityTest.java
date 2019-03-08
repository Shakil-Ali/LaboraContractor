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
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class RegisterActivityTest {

    // Rule created, which will be required for this test
    @Rule
    public ActivityTestRule<RegisterActivity> mActivityTestRule = new ActivityTestRule<RegisterActivity>(RegisterActivity.class);

    //Private variable of RegisterActivity

    private RegisterActivity registerActivity = null;

    // Monitor the Register activity
    Instrumentation.ActivityMonitor monitorActivity = getInstrumentation().addMonitor(RegisterActivity2.class.getName(), null, false);

    //email and password to be entered
    private String nEmail = "test@labora.org";
    private String nPassword = "123456";

    // Set up the function when launch starting
    @Before
    public void setUp() throws Exception {
        // Creating and initialising a private variable of type LoginActivity for use later in the tests
        registerActivity = mActivityTestRule.getActivity();
    }

    @Test
    public  void testLaunch()
    {
        // Attempt to launch the app and find the id of one of the components in LoginActivity
        View view = registerActivity.findViewById(R.id.buttonRegister);
        // Check if launch is not null
        assertNotNull(view);

    }


    @Test
    public  void testOpenRegister2()
    {
        // Check if it does not return null
        assertNotNull(registerActivity.findViewById(R.id.buttonRegister));

        // Take the view with signup button id
        onView(withId(R.id.buttonRegister)).perform(click());

        // Wait for monitor to be hit and then expires in 50000000 ms
        Activity RegisterActivity = getInstrumentation().waitForMonitorWithTimeout(monitorActivity, 50000000);

        // Check that the RegisterActivity  is not null
        assertNotNull(RegisterActivity);

        // Finish the opened activity
        RegisterActivity.finish();


    }

    @Test
    // Testing username field with empty password
    public void testUsernameEmptyPassword()
    {
        // Input some text in edit text
        Espresso.onView(withId(R.id.editTextEmail)).perform(typeText(nEmail));
        Espresso.onView(withId(R.id.editTextPassword)).perform(typeText(""));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click
        Espresso.onView(withId(R.id.buttonSignIn)).perform(click());
    }

    @Test
    // Testing password field with empty username
    public void testPasswordEmptyUsername()
    {
        // Input some text in edit text
        Espresso.onView(withId(R.id.editTextEmail)).perform(typeText(""));
        Espresso.onView(withId(R.id.editTextPassword)).perform(typeText(nPassword));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click
        Espresso.onView(withId(R.id.buttonSignIn)).perform(click());
    }

    @Test
    // Testing user email
    public void testUserInputScenario()
    {
        // Input some text in edit text
        Espresso.onView(withId(R.id.editTextEmail)).perform(typeText(nEmail));
        Espresso.onView(withId(R.id.editTextPassword)).perform(typeText(nPassword));
        // Close soft keyboard
        Espresso.closeSoftKeyboard();
        // Perform button click
        Espresso.onView(withId(R.id.buttonSignIn)).perform(click());
    }



    @After
    public void tearDown() throws Exception {
        // Nullify the activity after it has/has not launched
        mActivityTestRule = null;

    }
}