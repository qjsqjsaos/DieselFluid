package com.example.dieselfluid.view.fragment;

import static android.content.Context.CLIPBOARD_SERVICE;

import static androidx.core.content.ContextCompat.getSystemService;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dieselfluid.R;
import com.example.dieselfluid.databinding.FragmentDetailBinding;
import com.example.dieselfluid.view.activity.HomeActivity;
import com.example.dieselfluid.viewmodel.HomeViewModel;

import java.util.Objects;

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

    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
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

        binding.addressLayout.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) { //눌렀을 때 동작
                copyBoard(binding.detailAddress.getText().toString(), true);
            }
            return true;
        });

        binding.phoneNumberLayout.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) { //눌렀을 때 동작
                copyBoard(binding.phoneNumber.getText().toString(), false);
            }
            return true;
        });

    }

    private void copyBoard(String copyValue, boolean isAddress) {
        ClipboardManager clipboard = (ClipboardManager) requireActivity().getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", copyValue);
        clipboard.setPrimaryClip(clip);
        if(isAddress){
            Toast.makeText(getActivity(), "주소가 복사되었습니다.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "연락처가 복사되었습니다.", Toast.LENGTH_SHORT).show();
        }
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