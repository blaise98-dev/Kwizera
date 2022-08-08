package com.example.group16.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.group16.Frag.computerLocalListFragment;
import com.example.group16.Frag.computerRemoteListFragment;

public class MyTabAdapters extends FragmentStatePagerAdapter {
    int NumOfTabs;
    public MyTabAdapters(FragmentManager fm, int tabNumber) {
        super(fm);
        this.NumOfTabs=tabNumber;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 1) {
            return new computerRemoteListFragment();
        }
        return new computerLocalListFragment();

    }

    @Override
    public int getCount() {
        return NumOfTabs;
    }
}
