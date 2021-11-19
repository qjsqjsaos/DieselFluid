package com.example.dieselfluid.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.util.Log;

import com.example.dieselfluid.databinding.ActivityHomeBinding;
import com.example.dieselfluid.viewmodel.HomeViewModel;


public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        HomeViewModel model = new ViewModelProvider(this).get(HomeViewModel.class);
        model.getDieselData().observe(this, dieselData -> {
            Log.d("dieselData", String.valueOf(dieselData));
        });
    }
}