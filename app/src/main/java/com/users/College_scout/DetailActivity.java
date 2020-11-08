package com.users.College_scout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;
import com.users.College_scout.Interface.Retroclient;
import com.users.College_scout.Request.DetailRequest;
import com.users.College_scout.Request.Otpverify;
import com.users.College_scout.Respose.OtpResponse;

import java.io.IOException;

import es.dmoral.toasty.Toasty;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    EditText et_name,et_shopname,et_college;
    boolean incollege,selected;
    Button btn;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        et_name=findViewById(R.id.etname);
        et_shopname= findViewById(R.id.ets_name);
        et_college=findViewById(R.id.etcollege);
        radioGroup=findViewById(R.id.radiogrp);
        btn=findViewById(R.id.btn_nxt2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toasty.normal(DetailActivity.this, "clicked").show();
                add_details();
            }
        });
    }


    private void add_details() {
        String name = et_name.getText().toString();
        String shop_name = et_shopname.getText().toString();
        String college = et_college.getText().toString();
        String email = Prefs.getString("email", "");
       String token ="Bearer "+Prefs.getString("access_token","");
        if (name.isEmpty()) {
            et_name.setError("Name is required");
            et_name.requestFocus();
            return;
        }
        if (shop_name.isEmpty()) {
            et_shopname.setError("Shopname is required");
            et_shopname.requestFocus();
            return;
        }
        if (college.isEmpty()) {
            et_college.setError("College is required");
            et_college.requestFocus();
            return;
        }
        if (radioGroup.getCheckedRadioButtonId()==-1) {
            Toasty.normal(DetailActivity.this, "Select shop location").show();
        }

        else {
            DetailRequest details = new DetailRequest(email, name, shop_name, college, incollege);
            Call<ResponseBody> call = Retroclient
                    .getInstance()
                    .getapi()
                    .savedetail(details,token);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Toasty.success(DetailActivity.this, "Details successfully added", Toasty.LENGTH_LONG, true).show();
                        startActivity(new Intent(DetailActivity.this, MainActivity.class));
                    }
                    else if (response.code() == 401) {
                        Toasty.normal(DetailActivity.this,"Expired").show();
                        String token1= Prefs.getString("refresh_token","");
                         PrefsApplication prefsApplication= new PrefsApplication();
                         prefsApplication.refreshToken(token1);
                         add_details();
                    }
                    else {
                        try {
                            String msg = response.errorBody().string();
                            Toasty.normal(DetailActivity.this, msg).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toasty.error(DetailActivity.this, t.getMessage(), Toast.LENGTH_LONG, true).show();
                }
            });
        }
    }

    public void checkbtn(View view) {
        //Toasty.normal(DetailActivity.this,"id").show();
        int radioid = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioid);
       //Toasty.error(DetailActivity.this,"id: "+radioid, Toast.LENGTH_LONG, true).show();
      if(radioid == 2131296431){
          incollege=true;
          selected=true;
      }
    }
}