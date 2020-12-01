  /*=====================================SPLASH SCREEN @ BEGINNING====================================*/

package com.example.taskmanager.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.example.taskmanager.R;


//SPLASH SCREEN. THIS FUNCTION CREATES THE VIEW AND SETS THE SPLASH SCREEN FOR A SPECIFIED AMOUNT OF TIME BEFORE OTHER MAIN ACTIVITY VIEW
@SuppressWarnings("deprecation")
public class SplashScreen extends AppCompatActivity {

    //4000 = 4 sec
    final public static int splashTime = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sets the content view to the specified xml file
        setContentView(R.layout.activity_splash_screen);

        //this function lays out the content for a specific amount of time before the fragments
        //[2] https://youtu.be/Q0gRqbtFLcw
        new Handler().postDelayed(new Runnable() {
            @Override

            //INTENT creates a new activity.Which is why startActivity and finish are built in function
            //runs the splash screen and main activity class
            public void run() {
                //creates a new intent to start the activity
                Intent splashScreen = new Intent(SplashScreen.this, MainActivity.class);

                //built in intent content function to start the activity
                startActivity(splashScreen);

                //built in intent content function to end the activity
                finish();
            }
        }
        , splashTime); //runs for the specified time (4sec)
    }
}