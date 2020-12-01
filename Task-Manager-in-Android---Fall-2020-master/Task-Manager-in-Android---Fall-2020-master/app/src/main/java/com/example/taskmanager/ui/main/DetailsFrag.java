/*==========================================DETAILS FRAGMENT==========================================*/

package com.example.taskmanager.ui.main;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import com.example.taskmanager.R;

public class DetailsFrag extends Fragment {



    //Details Fragment constructor
    public DetailsFrag() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //creates the view for this fragment
        View detailsView = inflater.inflate(R.layout.fragment_details, container, false);


        /*===============================CREATING LIST VIEW ADAPTER IN FRAGMENT==============================*/
        //declaring list view var
        final ListView detailsLIST;

        //sets the list to the object id from the (fragment_details.xml)
        detailsLIST = detailsView.findViewById(R.id.detailsList);

        //this gives us access to the global variables inside of main activity
        final MainActivity mainActivity = (MainActivity) getActivity();
        assert mainActivity != null;

        // //[10] https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView
        //creating array adapter to set at the servicesList adapter. this adapter should use the servicesInfo array from main
        final ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mainActivity.detailsInfo);

        //sets the list to the newly defined adapter
        detailsLIST.setAdapter(listViewAdapter);


        /*=====================================SEARCH VIEW====================================*/
        //[12] https://developer.android.com/reference/android/widget/SearchView

        //declaring search view var
        SearchView searchView;

        //setting the search view var to the appropriate object id in the (fragment_details.xml) file
        searchView = detailsView.findViewById(R.id.filterDetails);

        //android studio sets up the onQueryTextSubmit and change as a template for you when creating a search view
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }


            @Override
            public boolean onQueryTextChange(String filteredDetails) {
                listViewAdapter.getFilter().filter(filteredDetails);
                return false;
            }
        });

        /*==========================================THIS COMMENTED CODE WAS FOR THE KILL PROCESSES============================*/
        /*Onclick listener:
         * https://www.youtube.com/watch?v=kCJv5YWHRXQ
         *
        detailsLIST.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> listViewAdapter, final View detailsView, int i, long l) {
                AlertDialog.Builder killAlert = new AlertDialog.Builder(mainActivity);

                killAlert.setTitle("CONFIRM KILL: ");
                killAlert.setMessage("Are you sure you want to kill this process?");
                killAlert.setIcon(R.drawable.warning);

                killAlert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(mainActivity, "PROCESS CANCELLED", Toast.LENGTH_SHORT).show();
                    }
                });

                killAlert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       // try {
                          //  Runtime.getRuntime().exec("su -c taskkill /F /PID" + MainActivity.mySplit);
                      //  } catch (IOException e) {
                           // e.printStackTrace();
                    //   }

                        try {
                            //detailsLIST.remove(MainActivity.detailsLines[i]);
                            //listViewAdapter.notifyDataSetChanged();
                            //only getting the last process
                            Toast.makeText(mainActivity, "PROCESS " + MainActivity.mySplit + "KILLED", Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });

                killAlert.create();
                killAlert.show();
            }
        });
        =====================================================================================================*/

        //returns the entire details layout view
        return detailsView;
    }
}








