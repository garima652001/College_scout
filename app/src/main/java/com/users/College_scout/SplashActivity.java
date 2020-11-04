package com.users.College_scout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.pixplicity.easyprefs.library.Prefs;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkuser();
            }
        }, 2500);
    }

        private void checkuser () {
           boolean user= Prefs.getBoolean("registered",false);
            if(user)
            {
                //startActivity(new Intent(this,DetailActivity.class));
               startActivity(new Intent(this,MainActivity.class));
            }
            else{
                startActivity(new Intent(this,IntroActivity.class));
            }
        }
    }