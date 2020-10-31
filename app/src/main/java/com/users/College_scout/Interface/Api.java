package com.users.College_scout.Interface;

import com.users.College_scout.Request.LoginRequest;
import com.users.College_scout.Request.Otpverify;
import com.users.College_scout.Request.Resendotp;
import com.users.College_scout.Request.ResetRequest;
import com.users.College_scout.Request.Signup;
import com.users.College_scout.Respose.LoginResponse;
import com.users.College_scout.Respose.OtpResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Api {

    @POST("signup")
    Call<ResponseBody> createuser(@Body Signup create);

    @POST("signup/otp-check")
    Call<OtpResponse> verifyuser(@Body Otpverify verify);

    @POST("resendOtp")
    Call<ResponseBody> resend_otp(@Body Resendotp resend);

    @POST("login")
    Call<LoginResponse> loginuser(@Body LoginRequest login);

    @POST("signup/resetOtp")
    Call<ResponseBody> resetotpemail(@Body ResetRequest reset);


}
