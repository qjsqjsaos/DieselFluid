package com.example.dieselfluid.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.example.dieselfluid.databinding.ActivityHomeBinding;
import com.example.dieselfluid.view.activity.recyclerview.DieselRecycleAdapter;
import com.example.dieselfluid.viewmodel.HomeViewModel;


public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private DieselRecycleAdapter adapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        HomeViewModel model = new ViewModelProvider(this).get(HomeViewModel.class);
        model.getDieselData().observe(this, dieselData -> {
            Log.d("dieselData2", dieselData.get(5).getLocation());
            binding.homeRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                    RecyclerView.VERTICAL, false)); // 상하 스크롤
            adapter = new DieselRecycleAdapter(dieselData);
        });


        binding.homeRecyclerView.setAdapter(adapter);
    }
}