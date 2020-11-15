package com.users.College_scout.NavFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.users.College_scout.Adapter.TabAdapter;
import com.users.College_scout.R;


public class OrderFragment extends Fragment {

    TabLayout tabLayout;
    TabItem tabItem1,tabItem2;
    ViewPager viewPager;
    TabAdapter tabAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout=view.findViewById(R.id.tablayout);
        tabItem1=view.findViewById(R.id.tab1);
        tabItem2=view.findViewById(R.id.tab2);
        viewPager=view.findViewById(R.id.viewpager);
        tabAdapter=new TabAdapter(getActivity().getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(tabAdapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            if(tab.getPosition()==0 || tab.getPosition()==1)
            tabAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.bringToFront();
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}