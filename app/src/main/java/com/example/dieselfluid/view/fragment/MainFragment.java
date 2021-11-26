package com.example.dieselfluid.view.fragment;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.dieselfluid.databinding.FragmentMainBinding;
import com.example.dieselfluid.view.activity.HomeActivity;
import com.example.dieselfluid.view.activity.recyclerview.DieselRecycleAdapter;
import com.example.dieselfluid.viewmodel.HomeViewModel;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentMainBinding binding;
    private DieselRecycleAdapter adapter;
    private int adCount = 0;
    Handler handler = new Handler();

    // 광고
    private InterstitialAd mInterstitialAd;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //광고 초기화
        initAd();
        //전면광고
        frontAdLoad();
        //배너광고
        binding.adView.loadAd(getAdRequest());

        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        initRecyclerView();
        initSearchView();
        refreshListener();
    }

    private void refreshListener() {
        binding.mainRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //리프레쉬 할때 동작할 코드
                initRecyclerView();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.mainRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initSearchView() {
        binding.mainSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

    @SuppressLint("SetTextI18n")
    private void initRecyclerView() {
        adapter = new DieselRecycleAdapter(getContext());

        homeViewModel.getDieselData().observe(requireActivity(), dieselData -> {
            //로딩 구현 할것 추후에
            binding.updateDate.setText("마지막 갱신 날짜 : " + dieselData.get(0).getUpdateDate());
            binding.mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                    RecyclerView.VERTICAL, false)); // 상하 스크롤
            adapter.setListData(dieselData);
        });

        binding.mainRecyclerView.setAdapter(adapter);

        setOnItemClickListener();
    }

    private void setOnItemClickListener() {
        homeViewModel.getOneGasData();
        adapter.setOnItemClickListener((holder, view, position) -> {
            homeViewModel.setOneGasData(adapter.getItem(position));
            ((HomeActivity)getActivity()).replaceFragment();
            if(adCount == 4) { ///4번에 한번 전면 광고 실행
                adCount = 0;
            }
            frontAdStart();
        });

    }

    private void frontAdStart() {
        if (mInterstitialAd != null && adCount == 0) {
            mInterstitialAd.show(getActivity());
            adCount++;
        } else {
            frontAdLoad();
            adCount++;
        }
    }
    private void initAd() {
        //광고 초기화
        MobileAds.initialize(requireActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
    }

    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    //전면 광고
    private void frontAdLoad() {


        InterstitialAd.load(getContext(),"ca-app-pub-7522479314167243/6937741655", getAdRequest(),
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                mInterstitialAd = null;
                                Log.d("TAG", "The ad was dismissed.");
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                mInterstitialAd = null;
                                Log.d("TAG", "The ad failed to show.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                Log.d("TAG", "The ad was shown.");
                            }
                        });

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });
    }
}