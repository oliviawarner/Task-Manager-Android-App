/*==========================================RUNNING IN ROOT==========================================*/

package com.example.taskmanager.ui.main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


//[6] http://muzikant-android.blogspot.com/2011/02/how-to-get-root-access-and-execute.html

public class rootCommands {

   public static String runAsRoot(String command) {

        try {

            //[6] https://stackoverflow.com/questions/20932102/execute-shell-%20%20%20command-from-android

            // Executes the command. each command will be different and are defined in (MainActivity.java)
            Process process = Runtime.getRuntime().exec(command);

            //[13] https://stackoverflow.com/questions/7449515/run-shell-commands-from-android-program
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            int read;
            char[] buffer = new char[24000];
            StringBuilder output = new StringBuilder();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            //closes the reader
            reader.close();

            // Waits for the command to finish.
            process.waitFor();

            //if it all works correctly this will return the output of the command just ran

            //returns the output
            return output.toString();
        }

        //exception catching
        catch (IOException | InterruptedException e) {
            try {
                throw new IOException("*** IO Exception was thrown ***");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                throw new InterruptedException("*** Interrupted Exception was thrown ***");
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

       //this prints if the try fails and any of the catch exceptions run
        return "Command didn't work";
    }

    //this method should be run before you attempt to run any super user or root commands
    //the method will evoke super user by writing su to the runtime exec command built in
    @SuppressWarnings("UnusedReturnValue")
    public static boolean canRunRootCommands()
    {
        //sets the return value to false
        boolean retVal;
        Process suProcess;

        //anytime you want to write to the os it must be put in a try catch or java will not let you do it
        try
        {

            //[6] https://stackoverflow.com/questions/33823794/running-root-commands-on-android-device
            //invoking the su command
            //the first time su is run the user will get a message to deny or grant the application super user access
            suProcess = Runtime.getRuntime().exec("su");

            //Allows for input and output to the command line that we opened
            DataOutputStream os = new DataOutputStream(suProcess.getOutputStream());

            //must user buffer reader because the readLine() method associated with DataInputStream class has depreciated
            BufferedReader osRes = new BufferedReader(new InputStreamReader(suProcess.getInputStream()));

            //command to get the id of the user
            os.writeBytes("id\n");
            //basically clearing the input buffer for next command
            os.flush();

            //reading the output form the command id above. this command should give the current user id for the application
            String userID = osRes.readLine();
            //sets the exit su to false
            boolean exitSu;

            //these if statements ensure that the user id was given back and that it is 0 which means that we are now acting as super user.
            //if the device is not rooted or the user of the phone denies granting super user access for the application then it will fail and the catch will execute
            if(userID == null)
            {
                retVal = true;
                exitSu = false;
            }
            else if(userID.contains("uid=0"))
            {
                retVal = true;
                exitSu = true;
            }
            else
            {
                retVal = false;
                exitSu = true;
            }
            if (exitSu)
            {
                os.writeBytes("exit\n");
                os.flush();
            }

            //catches the false return value
        } catch (Exception e) {
            retVal = false;
        }

        //returns the return value whether its true or false
        return retVal;
    }
}
