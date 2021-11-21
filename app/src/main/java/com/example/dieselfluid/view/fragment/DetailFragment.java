package com.example.dieselfluid.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dieselfluid.R;
import com.example.dieselfluid.databinding.FragmentDetailBinding;
import com.example.dieselfluid.view.activity.HomeActivity;
import com.example.dieselfluid.viewmodel.HomeViewModel;

public class DetailFragment extends Fragment implements HomeActivity.OnKeyBackPressedListener {

    private HomeViewModel homeViewModel;
    private FragmentDetailBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        homeViewModel.getOneGasData().observe(requireActivity(), gasData -> {
            Log.d("result21", String.valueOf(gasData.getDetailAddress()));
            binding.detailAddress.setText(" " + gasData.getDetailAddress());
            binding.operatingTime.setText(" " + gasData.getOperatingTime());
            binding.phoneNumber.setText(" " + gasData.getPhoneNumber());
            binding.updateDate.setText(" " + gasData.getUpdateDate());
            binding.dieselStock.setText(gasData.getDieselStock());
            binding.price.setText(gasData.getPrice());
        });
    }

    @Override
    public void onBackKey() {
        HomeActivity activity = (HomeActivity) getActivity();
        activity.setOnKeyBackPressedListener(null);
        activity.onBackPressed();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((HomeActivity) context).setOnKeyBackPressedListener(this);
    }


}