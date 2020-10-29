package com.users.College_scout.Interface;

import com.users.College_scout.Request.Signup;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Api {

    @POST("signup")
    Call<ResponseBody> createuser(@Body Signup create);
}
