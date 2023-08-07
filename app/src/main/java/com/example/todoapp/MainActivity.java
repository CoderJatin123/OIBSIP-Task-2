package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;

import com.example.todoapp.Fragment.Home;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private Home home_fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        fragmentManager = getSupportFragmentManager();
        home_fragment=new Home();
        loadFragment(home_fragment);
    }

    private void loadFragment(Fragment x){

        FragmentTransaction ft= fragmentManager.beginTransaction();
        ft.replace(R.id.main_frame,x);
        ft.commit();
    }
}