package com.users.College_scout.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.pixplicity.easyprefs.library.Prefs;
import com.users.College_scout.FoodModel;
import com.users.College_scout.Interface.Retroclient;
import com.users.College_scout.Request.FoodRequest;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodRepo {
    private MutableLiveData<List<FoodModel>> foodlist;

    public FoodRepo(MutableLiveData<List<FoodModel>> foodlist) {
        this.foodlist = foodlist;
        loaddata();
    }

    public MutableLiveData<List<FoodModel>> loaddata() {
        FoodRequest foodRequest= new FoodRequest("garima652001@gmail.com");
        Call<List<FoodModel>> call = Retroclient.getInstance().getapi().getitems(foodRequest);


        call.enqueue(new Callback<List<FoodModel>>() {
            @Override
            public void onResponse(Call<List<FoodModel>> call, Response<List<FoodModel>> response) {
                //finally we are setting the list to our MutableLiveData
                if (response.isSuccessful()) {
                    foodlist.setValue(response.body());
                    Log.d("rlog",response.body().toString());
                }

                else{
                    try {
                        Log.d("rlogerror",response.errorBody().string());
                    } catch (IOException e) {
                        Log.d("rlogerror",e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<FoodModel>> call, Throwable t) {
                Log.d("failure",t.getMessage());
            }
        });
        return  foodlist;
    }
}
