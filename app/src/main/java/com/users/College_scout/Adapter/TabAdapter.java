package com.users.College_scout.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.users.College_scout.TabFragments.CompletedFrag;
import com.users.College_scout.TabFragments.PendingFragment;

public class TabAdapter extends FragmentPagerAdapter {
    int tabcount;
    public TabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new PendingFragment();
            case 1:
                return new CompletedFrag();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
