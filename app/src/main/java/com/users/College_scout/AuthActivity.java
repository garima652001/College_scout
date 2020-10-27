package com.users.College_scout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        String msg = getIntent().getStringExtra("authkeyword");
        Fragment fragment=null;
        if(msg.equals("login"))
            fragment= new LoginFragment();
        else
            fragment= new SignupFragment();
        FragmentManager fragmentManager= getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container,fragment).addToBackStack(null).commit();
    }
}