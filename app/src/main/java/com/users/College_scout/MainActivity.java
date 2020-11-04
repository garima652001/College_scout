package com.users.College_scout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chipNavigationBar = findViewById(R.id.bottom_nav);
        chipNavigationBar.setItemSelected(R.id.page_1,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container1,new AddFragment()).commit();
        menu();
    }

    private void menu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment=null;
                /*switch(i){
                    case R.id.nav_home:
                        fragment= new Homefragment();
                        break;

                    case R.id.nav_profile:
                        fragment= new Profilefragment();
                        break;

                    case R.id.nav_create:
                        fragment= new Createfragment();
                        break;
                }*/
                getSupportFragmentManager().beginTransaction().replace(R.id.container1,fragment).addToBackStack(null).commit();
            }
        });
    }
}