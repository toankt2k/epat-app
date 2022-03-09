package com.example.epatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        setComponents();

    }

    private void setComponents(){
        BottomNavigationView nav = findViewById(R.id.bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commitNow();
        nav.setOnItemSelectedListener((NavigationBarView.OnItemSelectedListener) navListener);
    }
    private  NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFrag = null;
            switch (item.getItemId()){
                case R.id.nav_home:
                    selectedFrag = new HomeFragment();
                    break;
                case R.id.nav_pat:
                    selectedFrag = new PatientFragment();
                    break;
                case R.id.nav_person:
                    selectedFrag = new PersonFragment();
                    break;
                default:
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFrag).commitNow();

            return true;
        }
    };
}