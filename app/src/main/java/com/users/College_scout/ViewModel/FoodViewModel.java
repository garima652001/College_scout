package com.users.College_scout.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.users.College_scout.FoodModel;
import com.users.College_scout.Repository.FoodRepo;

import java.util.List;

public class FoodViewModel extends ViewModel {

    public MutableLiveData<List<FoodModel>> fooddatalist;

    public LiveData<List<FoodModel>> getFeed(Context context) {

        if(fooddatalist==null)
        {
            fooddatalist = new MutableLiveData<List<FoodModel>>();
            FoodRepo repo=new FoodRepo(fooddatalist);
            //we will load it asynchronously from server in this method
            repo.loaddata();
        }
        return fooddatalist;
    }
}
