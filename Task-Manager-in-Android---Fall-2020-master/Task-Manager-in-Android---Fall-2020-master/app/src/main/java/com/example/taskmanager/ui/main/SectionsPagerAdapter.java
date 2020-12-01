package com.example.taskmanager.ui.main;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.taskmanager.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
@SuppressWarnings("deprecation")
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;



    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    //gets fragment item at a position
    @SuppressWarnings("NullableProblems")
    @Override
    public Fragment getItem(int position) {
        Fragment tabFrag;

        //switch statement to go between the 3 different fragments. Case 0 starts with the first tab position
        //[1] https://youtu.be/HHd-Fa3DCng
        switch(position) {
            case 0:
                tabFrag = new DetailsFrag();
                break;

            case 1:
                tabFrag = new ServicesFrag();
                break;

            case 2:
                tabFrag = new CpuFrag();
                break;

            default:
                throw new IllegalStateException("Unexpected tab value: " + position);
        }
        return tabFrag;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);

    }

    @Override
    public int getCount() {
        // Showing 3 total page count tabs
        return 3;
    }
}