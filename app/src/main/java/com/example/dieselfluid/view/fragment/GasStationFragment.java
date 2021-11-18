package com.example.dieselfluid.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dieselfluid.R;
import com.example.dieselfluid.databinding.FragmentGasStationBinding;
import com.example.dieselfluid.model.UserModel;
import com.example.dieselfluid.viewmodel.DieselViewModel;

public class GasStationFragment extends Fragment {

    private FragmentGasStationBinding binding;
    private DieselViewModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGasStationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(DieselViewModel.class);
        model.getUsers().observe(getViewLifecycleOwner(), userModel -> {
            //위도와 경도를 받아온다.

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}