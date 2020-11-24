package com.users.College_scout.TabFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Predicate;
import com.users.College_scout.Adapter.OrderAdapter;
import com.users.College_scout.FoodAdapter;
import com.users.College_scout.FoodModel;
import com.users.College_scout.Order;
import com.users.College_scout.OrdersModel;
import com.users.College_scout.R;
import com.users.College_scout.ViewModel.FoodViewModel;
import com.users.College_scout.ViewModel.OrderViewModel;

import java.util.List;

public class PendingFragment extends Fragment {
    Context context;
    OrderViewModel orderViewModel;
    OrderAdapter orderAdapter;
    RecyclerView recyclerView;
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
        recyclerView=view.findViewById(R.id.recycler_view1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        loaddata();
        return view;
    }

    private void loaddata() {
        Toast.makeText(context, "pending frag", Toast.LENGTH_SHORT).show();
       orderViewModel =ViewModelProviders.of((FragmentActivity) context).get(OrderViewModel.class);
        orderViewModel.getFeed(context).observe(getViewLifecycleOwner(), new Observer<OrdersModel>() {
            @Override
            public void onChanged(@Nullable OrdersModel orderList) {

                List<Order> filteredList = Stream.of(orderList.getOrders()).filter(new Predicate<Order>() {
                    @Override
                    public boolean test(Order item) {
                        return item.getOrderStatus().equalsIgnoreCase("pending");
                    }
                }).collect(Collectors.toList());

                orderAdapter = new OrderAdapter(filteredList,context);
                recyclerView.setAdapter(orderAdapter);
               // Toast.makeText(context, "Food is: "+orderList.getOrders().get(0).getItemName(), Toast.LENGTH_LONG).show();
            }
        });

    }
}