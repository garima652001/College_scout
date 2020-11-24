package com.users.College_scout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.users.College_scout.ViewModel.FoodViewModel;

import java.util.List;

public class AddFragment extends Fragment {

    ImageView add;
    LinearLayout isempty;
    FoodViewModel foodViewModel;
    Context context;
    RecyclerView recyclerView;
    FoodAdapter itemadapter;
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
        View view= inflater.inflate(R.layout.fragment_add, container, false);
        add=view.findViewById(R.id.img_add);
        recyclerView=view.findViewById(R.id.recycler_view);
        isempty= view.findViewById(R.id.empytycart);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),AdditemActivity.class));
                /*Fragment fragment = new AdditemFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container1, fragment).commit();*/

            }
        });
        loaddata();
        return view;
    }

    private void loaddata() {
        foodViewModel = ViewModelProviders.of((FragmentActivity) context).get(FoodViewModel.class);
        foodViewModel.getFeed(context).observe(getViewLifecycleOwner(), new Observer<List<FoodModel>>() {
            @Override
            public void onChanged(@Nullable List<FoodModel> foodList) {
                if (foodList.size() == 0) {
                      isempty.setVisibility(View.VISIBLE);
                } else {
                    itemadapter = new FoodAdapter(foodList, context);
                    recyclerView.setAdapter(itemadapter);
                    // Toast.makeText(context, "Food is: "+foodList.get(5).getPriceArray().get(0).getPrice(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}