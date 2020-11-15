package com.users.College_scout.NavFragments;

import android.graphics.Color;
import android.hardware.camera2.params.ColorSpaceTransform;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.users.College_scout.R;

import java.util.ArrayList;

public class StatsFragment extends Fragment {

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barentries;
    String[] days={"M","T","W","TH","F","SAT","SUN"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_stats, container, false);
        barChart=view.findViewById(R.id.graph);
        getentries();
        barDataSet= new BarDataSet(barentries,"Data set");
        barData=new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.rgb("#fed8b1"));
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(12f);
        barDataSet.setBarBorderColor(Color.rgb(251,133,0));
        barDataSet.setBarBorderWidth(0.5f);
        barDataSet.setStackLabels(days);
        return view;
    }

    private void getentries() {
        barentries= new ArrayList<>();
        barentries.add(new BarEntry(1f,50));
        barentries.add(new BarEntry(2f,100));
        barentries.add(new BarEntry(3f,60));
        barentries.add(new BarEntry(4f,140));
        barentries.add(new BarEntry(5f,70));
        barentries.add(new BarEntry(6f,90));
        barentries.add(new BarEntry(7f,110));

    }

}