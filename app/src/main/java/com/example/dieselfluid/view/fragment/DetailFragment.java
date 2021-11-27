package com.example.dieselfluid.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.dieselfluid.databinding.FragmentDetailBinding;
import com.example.dieselfluid.view.activity.HomeActivity;
import com.example.dieselfluid.viewmodel.HomeViewModel;

public class DetailFragment extends Fragment implements HomeActivity.OnKeyBackPressedListener {

    private FragmentDetailBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
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

        HomeViewModel homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        homeViewModel.initializationDetail(getContext() ,binding, requireActivity());
    }

    @Override
    public void onBackKey() {
        HomeActivity activity = (HomeActivity) getActivity();
        if (activity != null) {
            activity.setOnKeyBackPressedListener(null);
        }
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((HomeActivity) context).setOnKeyBackPressedListener(this);
    }
}