/*==========================================MAIN ACTIVITY==========================================*/

package com.example.taskmanager.ui.main;

import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.example.taskmanager.R;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    //public static String mySplit;

    //admin service vars
    private DevicePolicyManager DevicePolicyManager;
    private ActivityManager activityManager;
    private ComponentName compName;

    //Declaration of display ArrayLists for services & details
    ArrayList<String> servicesInfo;
    ArrayList<String> runningServicesInfo;
    ArrayList<String> detailsInfo;
    ArrayList<String> cpuInfo;

    //root var
    rootCommands root;

    //kill background process package string
    private String KillPackage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creates the new saved instance
        super.onCreate(savedInstanceState);

        //sets the content view to the layout declared in (activity_main.xml)
        setContentView(R.layout.activity_main);

        //creates a new section page adapter
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        //creates the view page by setting it to the object id in (activity_main.xml)
        ViewPager viewPager = findViewById(R.id.view_pager);

        //sets the new page adapter
        viewPager.setAdapter(sectionsPagerAdapter);

        //setting the tab layout to the object id in (activity_main.xml)
        TabLayout tabs = findViewById(R.id.tabs);

        //sets the tab up with the view pager
        tabs.setupWithViewPager(viewPager);

        /*==========================================ADMIN STUFF W/ ACTIVITY MANAGER========================================*/

        //[5] https://medium.com/@isuru.2014033_41377/make-a-device-admin-application-android-emm-b08e6a134
        //[5] http://blogs.quovantis.com/android-device-administration-apis/

        //the device policy manager get the device policy services
        DevicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        //activity manager get the activity services
        activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        //sets the component to the class created in the (myAdmin.java) file
        compName = new ComponentName(this, myAdmin.class);

        /*==========================================SERVICES AND DETAILS ARRAYS========================================*/


        //sets the array lists of info for different tabs
        servicesInfo = new ArrayList<String>();
        runningServicesInfo = new ArrayList<String>();
        detailsInfo = new ArrayList<String>();
        cpuInfo = new ArrayList<String>();

        //allows to run the root command class to run the different commands in main
        root = new rootCommands();
        root.canRunRootCommands();


        //ALL services available on the device
        //[8] https://askubuntu.com/questions/903354/difference-between-systemctl-and-service-commands
        String rootCommandInfoServices = root.runAsRoot("su -c service list");


        //running services command
        //[8] https://www.geeksforgeeks.org/java-program-open-command-prompt-insert-commands/
        String rootCommandInfoRunServices = root.runAsRoot("su -c cmd -l");


        //shows all processes by all users
        //[7] https://www.cyberciti.biz/faq/show-all-running-processes-in-linux/
        String rootCommandInfoDetails = root.runAsRoot("su -c ps");


        //shows cpu usage and the different processes and what cpu usage they are taking up
        //[9] https://phoenixnap.com/kb/check-cpu-usage-load-linux
        //[9] https://www.geeksforgeeks.org/top-command-in-linux-with-examples/
        String rootCommandInfoCPU = root.runAsRoot("su -c uptime && su -c top -n 1");


        //[11] https://stackoverflow.com/questions/454908/split-java-string-by-new-line
        //the command output is split by line
        String servicesLines[] = rootCommandInfoServices.split("\\r?\\n");
        String runningServicesLines[] = rootCommandInfoRunServices.split("\\r?\\n");
        String detailsLines[] = rootCommandInfoDetails.split("\\r?\\n");
        String cpuLines[] = rootCommandInfoCPU.split("\\r?\\n");


        //adds services into array list
        for (int i = 0; i < servicesLines.length; i++) {
            if (servicesLines[i] != null && !(servicesLines[i].equals("")))
                servicesInfo.add(servicesLines[i]);
        }

        //adds running services into array list
        for (int i = 0; i < runningServicesLines.length; i++) {
            if (runningServicesLines[i] != null && !(runningServicesLines[i].equals("")))
                runningServicesInfo.add(runningServicesLines[i]);
        }

        //adds details/processes into array list
        for (int i = 0; i < detailsLines.length; i++) {
            if (detailsLines[i] != null && !(detailsLines[i].equals("")))
                detailsInfo.add(detailsLines[i]);

            /*This commented code is from trying to kill the processes >>>>
                //this adds the pid onto a line below it. for testing purposes
                //mySplit = detailsLines[i].substring(10,15);
                //mySplit = mySplit.replaceAll("//s","");
                //detailsInfo.add(mySplit);
             */
        }
        //adds cpu info into array list
        for (int i = 0; i < cpuLines.length; i++) {
            if (cpuLines[i] != null && !(cpuLines[i].equals("")))
                cpuInfo.add(cpuLines[i]);
        }


            /*=================================KILL BACKGROUND PROCESS PERMISSION USING ACTIVITY MANAGER==========================*/

            //[4] http://androidpermissions.org/android-permission-KILL_BACKGROUND_PROCESSES/

            //activity manager gets the activity services
            ActivityManager killB = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            //set the package to be able to kill background processes
            killB.killBackgroundProcesses(KillPackage);
            /*===================================================================================================================*/


            /*===========================================APP DETAILS BTN=====================================*/


            //button declared with the appropriate id in (activity_main.xml)
            Button detailsBtn = findViewById(R.id.appDetails);

            //the app details btn sets an action to take you to the current application details and settings
            detailsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //creates new intent
                    //[3] https://developer.android.com/guide/components/intents-filters
                    Intent appInfo = new Intent();

                    //sets the action
                    //[3] https://developer.android.com/reference/android/provider/Settings
                    appInfo.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);

                    //gets package to get into the action
                    Uri uri = Uri.fromParts("package", getPackageName(), null);

                    //sets the data to the intent
                    appInfo.setData(uri);

                    //starts the app info activity
                    //[3] https://developer.android.com/guide/components/activities/intro-activities
                    startActivity(appInfo);
                }
            });
        }
    }


