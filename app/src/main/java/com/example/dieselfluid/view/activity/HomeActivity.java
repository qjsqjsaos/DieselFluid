package com.example.dieselfluid.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.dieselfluid.R;
import com.example.dieselfluid.databinding.ActivityHomeBinding;
import com.example.dieselfluid.view.fragment.DieselStatusFragment;
import com.example.dieselfluid.view.fragment.GasStationFragment;
import com.example.dieselfluid.view.fragment.MapFragment;


public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}