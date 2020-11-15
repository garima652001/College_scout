package com.users.College_scout.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.users.College_scout.FoodModel;
import com.users.College_scout.OrdersModel;
import com.users.College_scout.Repository.FoodRepo;
import com.users.College_scout.Repository.OrderRepo;

import java.util.List;

public class OrderViewModel extends ViewModel {
    public MutableLiveData<OrdersModel> orderdatalist;

    public LiveData<OrdersModel> getFeed(Context context) {

        if(orderdatalist==null)
        {
            orderdatalist = new MutableLiveData<OrdersModel>();
            OrderRepo repo=new OrderRepo(orderdatalist);
            //we will load it asynchronously from server in this method
            repo.loaddata();
        }
        return orderdatalist;
    }
}
