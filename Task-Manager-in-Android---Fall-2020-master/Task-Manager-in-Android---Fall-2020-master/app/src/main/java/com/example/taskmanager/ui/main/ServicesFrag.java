/*==========================================SERVICES FRAGMENT==========================================*/

package com.example.taskmanager.ui.main;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import com.example.taskmanager.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;


public class ServicesFrag extends Fragment {

    //Services fragment constructor
    public ServicesFrag() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //sets the view of the layout in the (fragment_services.xml)
        View servicesView = inflater.inflate(R.layout.fragment_services, container, false);

        //this gives us access to the global variables inside of main activity
        final MainActivity mainActivity = (MainActivity) getActivity();

        //sets the services list to the appropriate object id in the (fragment_services.xml)
        final ListView servicesLIST = servicesView.findViewById(R.id.servicesList);
        assert mainActivity != null;

        //creating array adapter to set at the servicesList adapter. this adapter should use the servicesInfo array from main
        final ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_list_item_1, mainActivity.servicesInfo);

        //sets the servicesList to the newly created adapter
        servicesLIST.setAdapter(listViewAdapter);

        //sets the running list to the appropriate object id in the (fragment_services.xml)
        final ListView runningLIST = servicesView.findViewById(R.id.runningList);

        //[10] https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView
        //creating array adapter to set at the servicesList adapter. this adapter should use the servicesInfo array from main
        final ArrayAdapter<String> runningListViewAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mainActivity.runningServicesInfo);

        //sets the running list to the newly created adapter
        runningLIST.setAdapter(runningListViewAdapter);

        //setting the running list to invisible when the view is loaded so only the other list is visible
        runningLIST.setVisibility(View.INVISIBLE);


        /*=====================================SEARCH VIEW====================================*/
        //[12] https://developer.android.com/reference/android/widget/SearchView

        //declared searchView var
        SearchView searchView;

        //sets the search view to the appropriate object id in the (fragment_services.xml)
        searchView = servicesView.findViewById(R.id.filterServices);

        //android studio sets up the onQueryTextSubmit and change as a template for you when creating the search view
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String filteredServices) {
                listViewAdapter.getFilter().filter(filteredServices);
                runningListViewAdapter.getFilter().filter(filteredServices);
                return false;
            }
        });

        /*=============================FLOATING ACTION BTN====================================*/

        //declaring running btn var to the appropriate object id in (fragment_services.xml)
        final FloatingActionButton getRunning = servicesView.findViewById(R.id.runningS);
        //sets the btn to immediately gone at the start
        getRunning.setVisibility(View.INVISIBLE);

        //declares text view var to the object id in (fragment_services.xml)
        final TextView running_caption = servicesView.findViewById(R.id.running_btn_txt);
        //running caption next to btn set to invisible immediately
        running_caption.setVisibility(View.INVISIBLE);


        //gets the running services and hides the services list
        getRunning.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                servicesLIST.setVisibility(View.INVISIBLE);
                //running list visible
                runningLIST.setVisibility(View.VISIBLE);
                //complete service list invisible
                getRunning.setVisibility(View.INVISIBLE);
                //running btn caption invisible
                running_caption.setVisibility(View.INVISIBLE);
            }
        });


        //declaring floating action btn to the appropriate object id in (fragment_services.xml)
        FloatingActionButton goBack = servicesView.findViewById(R.id.regS);

        //on click it hides the running list and shows the complete services list list
        goBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //complete service list visible
                servicesLIST.setVisibility(View.VISIBLE);
                //running list invisible
                runningLIST.setVisibility(View.INVISIBLE);
                //get running btn visible
                getRunning.setVisibility(View.VISIBLE);
                //running btn caption visible
                running_caption.setVisibility(View.VISIBLE);
            }
        });

        //returns the entire view
        return servicesView;
    }
}
