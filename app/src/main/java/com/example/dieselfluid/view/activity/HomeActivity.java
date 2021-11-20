package com.example.dieselfluid.view.activity;

import static java.util.Locale.filter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dieselfluid.databinding.ActivityHomeBinding;
import com.example.dieselfluid.model.GasStationModel;
import com.example.dieselfluid.view.activity.recyclerview.DieselRecycleAdapter;
import com.example.dieselfluid.view.activity.recyclerview.OnItemClickListener;
import com.example.dieselfluid.viewmodel.DetailViewModel;
import com.example.dieselfluid.viewmodel.HomeViewModel;


public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private DieselRecycleAdapter adapter;
    private DetailViewModel detailViewModel;



    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initRecyclerView();
        initSearchView();
    }


    private void initSearchView() {
        binding.homeSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        binding.homeSearchView.setOnSearchClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "클릭", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRecyclerView() {
        adapter = new DieselRecycleAdapter(this);

        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getDieselData().observe(this, dieselData -> {
            binding.homeRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                    RecyclerView.VERTICAL, false)); // 상하 스크롤
            adapter.setListData(dieselData);
        });

        binding.homeRecyclerView.setAdapter(adapter);

        setOnItemClickListener(this);
    }

    private void setOnItemClickListener(HomeActivity homeActivity) {
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(DieselRecycleAdapter.DieselRecycleViewHolder holder,
                                    View view, int position) {
                GasStationModel gasModel = adapter.getItem(position);
                detailViewModel = new ViewModelProvider(homeActivity).get(DetailViewModel.class);
                detailViewModel.getGasData().setValue(gasModel);
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                startActivity(intent);
            }
        });

    }


}