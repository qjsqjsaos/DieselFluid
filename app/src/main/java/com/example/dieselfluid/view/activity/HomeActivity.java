package com.example.dieselfluid.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import com.example.dieselfluid.R;
import com.example.dieselfluid.databinding.ActivityHomeBinding;
import com.example.dieselfluid.view.fragment.DetailFragment;
import com.example.dieselfluid.view.fragment.MainFragment;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;


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

