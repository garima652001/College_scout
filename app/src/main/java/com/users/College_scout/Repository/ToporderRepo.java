package com.users.College_scout.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.pixplicity.easyprefs.library.Prefs;
import com.users.College_scout.Request.GettopRequest;
import com.users.College_scout.TodaysModel;
import com.users.College_scout.Interface.Retroclient;
import com.users.College_scout.Request.FoodRequest;
import com.users.College_scout.TodaysModel;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToporderRepo {
    private MutableLiveData<List<TodaysModel>> toplist;

    public ToporderRepo(MutableLiveData<List<TodaysModel>> toplist) {
        this.toplist = toplist;
        loaddata();
    }
    public MutableLiveData<List<TodaysModel>> loaddata() {
        GettopRequest gettopRequest= new GettopRequest("17/11/20");
        Call<List<TodaysModel>> call = Retroclient.getInstance().getapi().gettop(gettopRequest);


        call.enqueue(new Callback<List<TodaysModel>>() {
            @Override
            public void onResponse(Call<List<TodaysModel>> call, Response<List<TodaysModel>> response) {
                //finally we are setting the list to our MutableLiveData
                if (response.isSuccessful()) {
                    toplist.setValue(response.body());
                    // Log.d("rlog",response.body().toString());
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
            public void onFailure(Call<List<TodaysModel>> call, Throwable t) {
                Log.d("failure",t.getMessage());
            }
        });
        return  toplist;
    }
}
