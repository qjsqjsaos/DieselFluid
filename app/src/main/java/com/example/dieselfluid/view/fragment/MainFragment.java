package com.example.dieselfluid.view.fragment;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.dieselfluid.databinding.FragmentMainBinding;
import com.example.dieselfluid.model.GasStationModel;
import com.example.dieselfluid.view.activity.HomeActivity;
import com.example.dieselfluid.view.activity.recyclerview.DieselRecycleAdapter;
import com.example.dieselfluid.view.activity.recyclerview.OnItemClickListener;
import com.example.dieselfluid.viewmodel.HomeViewModel;

public class MainFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentMainBinding binding;
    private DieselRecycleAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        initRecyclerView();
        initSearchView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
    }

    private void initRecyclerView() {
        adapter = new DieselRecycleAdapter(getContext());

        homeViewModel.getDieselData().observe(requireActivity(), dieselData -> {
            binding.homeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                    RecyclerView.VERTICAL, false)); // 상하 스크롤
            adapter.setListData(dieselData);
        });

        binding.homeRecyclerView.setAdapter(adapter);

        setOnItemClickListener();
    }

    private void setOnItemClickListener() {
        homeViewModel.getOneGasData();
        adapter.setOnItemClickListener((holder, view, position) -> {
            homeViewModel.setOneGasData(adapter.getItem(position));
            ((HomeActivity)getActivity()).replaceFragment();
        });

    }
}