/*==========================================CPU FRAGMENT==========================================*/

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


public class CpuFrag extends Fragment {

    //cpu constructor
    public CpuFrag() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View cpuView = inflater.inflate(R.layout.fragment_cpu, container, false);

        //declaring cpu list view var
        final ListView cpuLIST;

        //setting the list view to its object id in (fragment_cpu.xml)
        cpuLIST = cpuView.findViewById(R.id.cpuList);

        //this gives us access to the global variables inside of main activity
        final MainActivity mainActivity = (MainActivity) getActivity();
        assert mainActivity != null;

        // [10] https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView
        //creating array adapter to set at the servicesList adapter. this adapter should use the cpuInfo array from main
        final ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mainActivity.cpuInfo);

        //sets the cpu list to the new adapter
        cpuLIST.setAdapter(listViewAdapter);


        /*=====================================SEARCH VIEW====================================*/
        //[12] https://developer.android.com/reference/android/widget/SearchView

        //declaring the search view var
        SearchView searchView;

        //setting the  search view to its appropriate object id in (fragment_cpu.xml)
        searchView = cpuView.findViewById(R.id.filterCPU);

        //android studio sets up the onQueryTextSubmit and change as a template for you
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

        //returns the cpu view
        return cpuView;
        }
    }
