package com.users.College_scout.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.pixplicity.easyprefs.library.Prefs;
import com.users.College_scout.FoodModel;
import com.users.College_scout.Interface.Retroclient;
import com.users.College_scout.OrdersModel;
import com.users.College_scout.Request.FoodRequest;
import com.users.College_scout.Request.OrderRequest;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRepo {
    private MutableLiveData<OrdersModel> orderlist;

    public OrderRepo(MutableLiveData<OrdersModel> orderlist) {
        this.orderlist = orderlist;
        loaddata();
    }

    public MutableLiveData<OrdersModel> loaddata() {
        OrderRequest orderRequest=new OrderRequest("5fa04596a25d55337c640eb1");
        Call<OrdersModel> call = Retroclient.getInstance().getapi().getorderstatus(orderRequest);
        call.enqueue(new Callback<OrdersModel>() {
            @Override
            public void onResponse(Call<OrdersModel> call, Response<OrdersModel> response) {
                //finally we are setting the list to our MutableLiveData
                if (response.isSuccessful()) {
                    orderlist.setValue(response.body());
                    Log.d("rlog",response.body().toString());
                    String s= response.body().toString();
                }

                else
                    {
                    try {
                        Log.d("rlogerror",response.errorBody().string());
                    } catch (IOException e) {
                        Log.d("rlogerror",e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<OrdersModel> call, Throwable t) {
                Log.d("failure",t.getMessage());
            }
        });
        return orderlist;
    }
}

