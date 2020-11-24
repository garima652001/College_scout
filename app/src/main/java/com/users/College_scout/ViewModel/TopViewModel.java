package com.users.College_scout.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.users.College_scout.FoodModel;
import com.users.College_scout.Repository.FoodRepo;
import com.users.College_scout.Repository.ToporderRepo;
import com.users.College_scout.TodaysModel;

import java.util.List;

public class TopViewModel extends ViewModel {
    public MutableLiveData<List<TodaysModel>> topdatalist;

    public LiveData<List<TodaysModel>> getFeed(Context context) {

        if(topdatalist==null)
        {
            topdatalist = new MutableLiveData<List<TodaysModel>>();
            ToporderRepo repo=new ToporderRepo(topdatalist);
            //we will load it asynchronously from server in this method
            repo.loaddata();
        }
        return topdatalist;
    }
}
