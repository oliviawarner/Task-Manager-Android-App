package com.example.taskmanager.ui.main;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/*===============================ADMIN PERMISSION===============================*/

//[5] https://medium.com/@isuru.2014033_41377/make-a-device-admin-application-android-emm-b08e6a134
//[5] http://blogs.quovantis.com/android-device-administration-apis/

//getting admin privileges by extended the device admin receiver
@SuppressWarnings("NullableProblems")

public class myAdmin extends DeviceAdminReceiver {
    @Override
    public void onEnabled(Context context, Intent intent) {
        super.onEnabled(context, intent);
        //toast message to show that the admin has been enabled
        Toast.makeText(context, "Device Admin is now Enabled", Toast.LENGTH_SHORT).show();
    }
}
