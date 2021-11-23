package com.example.dieselfluid.view.activity;

import static java.util.Locale.filter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.dieselfluid.R;
import com.example.dieselfluid.databinding.ActivityHomeBinding;
import com.example.dieselfluid.model.GasStationModel;
import com.example.dieselfluid.view.activity.recyclerview.DieselRecycleAdapter;
import com.example.dieselfluid.view.activity.recyclerview.OnItemClickListener;
import com.example.dieselfluid.view.fragment.DetailFragment;
import com.example.dieselfluid.view.fragment.MainFragment;
import com.example.dieselfluid.viewmodel.HomeViewModel;
import com.google.android.ads.mediationtestsuite.viewmodels.ViewModelFactory;

import java.util.Objects;


public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private Fragment mainFrag, detailFrag;
    private FragmentManager fragmentManager;
    long pressedTime = 0; //'뒤로가기' 버튼 클릭했을 때의 시간

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();

        mainFrag = new MainFragment();
        fragmentManager.beginTransaction().replace(R.id.home_frame_layout, mainFrag)
                .commitAllowingStateLoss();
    }

    public void replaceFragment() {
        pressedTime = 0;
        showDetailFrag();
    }


    public interface OnKeyBackPressedListener {
        void onBackKey();
    }

    private OnKeyBackPressedListener mOnKeyBackPressedListener;

    public void setOnKeyBackPressedListener(OnKeyBackPressedListener listener) {
        mOnKeyBackPressedListener = listener;
    }

    @Override
    public void onBackPressed() {
        if (mOnKeyBackPressedListener != null) {
            mOnKeyBackPressedListener.onBackKey();
        } else {
            if(System.currentTimeMillis() > pressedTime + 2000){
                pressedTime = System.currentTimeMillis();
                showMainFrag();
            } else{
                super.onBackPressed();
            }
        }
    }

    public void showMainFrag() {
        if(mainFrag == null) {
            mainFrag = new MainFragment();
            fragmentManager.beginTransaction().add(R.id.home_frame_layout, mainFrag).commit();
        }

        if(mainFrag != null) fragmentManager.beginTransaction().show(mainFrag).commit();
        if(detailFrag != null) fragmentManager.beginTransaction().hide(detailFrag).commit();
    }

    public void showDetailFrag() {
        if(detailFrag == null) {
            detailFrag = new DetailFragment();
            fragmentManager.beginTransaction().add(R.id.home_frame_layout, detailFrag).commit();
        }

        if(mainFrag != null) fragmentManager.beginTransaction().hide(mainFrag).commit();
        if(detailFrag != null) fragmentManager.beginTransaction().show(detailFrag).commit();
    }
}

