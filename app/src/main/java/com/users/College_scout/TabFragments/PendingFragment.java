package com.users.College_scout.TabFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.users.College_scout.FoodAdapter;
import com.users.College_scout.FoodModel;
import com.users.College_scout.OrdersModel;
import com.users.College_scout.R;
import com.users.College_scout.ViewModel.FoodViewModel;
import com.users.College_scout.ViewModel.OrderViewModel;

import java.util.List;


public class PendingFragment extends Fragment {
    Context context;
    OrderViewModel orderViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pending, container, false);
        loaddata();
        return view;
    }

    private void loaddata() {
        Toast.makeText(context, "pending frag", Toast.LENGTH_SHORT).show();
       orderViewModel =ViewModelProviders.of((FragmentActivity) context).get(OrderViewModel.class);
        orderViewModel.getFeed(context).observe(getViewLifecycleOwner(), new Observer<OrdersModel>() {
            @Override
            public void onChanged(@Nullable OrdersModel orderList) {
                Toast.makeText(context, "Food is: "+orderList.getOrders(), Toast.LENGTH_LONG).show();
            }
        });

    }
}