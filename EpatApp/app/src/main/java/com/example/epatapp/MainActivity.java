package com.example.epatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.example.epatapp.fragment.HomeFragment;
import com.example.epatapp.fragment.PatientFragment;
import com.example.epatapp.fragment.PersonFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

/**
 * Activity của trang chủ
 */
public class MainActivity extends AppCompatActivity implements HomeFragment.ReplaceFragment
{
    private BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        setComponents();

    }

    /**
     * Set sự kiện cho các component trong màn hình
     */
    private void setComponents(){
        nav = findViewById(R.id.bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commitNow();
        nav.setOnItemSelectedListener((NavigationBarView.OnItemSelectedListener) navListener);
    }

    /**
     * Xử lý sự kiện navigation bottom
     */
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

    @Override
    public void replace(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commitNow();
        nav.setSelectedItemId(R.id.nav_pat);
    }
}