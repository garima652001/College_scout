package com.users.College_scout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        findViewById(R.id.btnsignup).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnsignup:
                startActivity(new Intent(this, SignupActivity.class));
                break;

            case R.id.button2:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}