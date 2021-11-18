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
    Fragment gas_fragment, diesel_fragment, map_fragment;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        gas_fragment = new GasStationFragment();
        diesel_fragment = new DieselStatusFragment();
        map_fragment = new MapFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.home_frame_layout, gas_fragment)
                .commitAllowingStateLoss();

        binding.homeBottomNavi.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nearest_gas_station:
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_frame_layout, gas_fragment)
                            .commitAllowingStateLoss();
                    return true;
                case R.id.diesel_status:
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_frame_layout, diesel_fragment)
                            .commitAllowingStateLoss();
                    return true;
                case R.id.map:
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_frame_layout, map_fragment)
                            .commitAllowingStateLoss();
                    return true;
            }
            return true;
        });

    }
}