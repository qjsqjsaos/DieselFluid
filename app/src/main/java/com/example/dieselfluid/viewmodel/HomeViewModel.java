package com.example.dieselfluid.viewmodel;

import static android.content.Context.CLIPBOARD_SERVICE;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dieselfluid.databinding.FragmentDetailBinding;
import com.example.dieselfluid.databinding.FragmentMainBinding;
import com.example.dieselfluid.model.GasStationModel;
import com.example.dieselfluid.view.activity.HomeActivity;
import com.example.dieselfluid.view.activity.recyclerview.DieselRecycleAdapter;
import com.example.dieselfluid.viewmodel.repository.api.RetrofitAPI;
import com.example.dieselfluid.viewmodel.repository.api.RetrofitClient;
import com.example.dieselfluid.viewmodel.repository.api.dto.Data;
import com.example.dieselfluid.viewmodel.repository.api.dto.DieselData;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeViewModel extends ViewModel {
    private MutableLiveData<ArrayList<GasStationModel>> gasModelList;
    private MutableLiveData<GasStationModel> oneGasModel = new MutableLiveData<>();
    private final static String SECRET_KEY
            = "+VSUnLfNB7xrQXw57u4L/6IDgIBuw4DIYKm6lax8dyEwZ5WkNzj2tQR5U9a1A3J6RSW/KD40199fXwI7RLhQGg==";
    @SuppressLint("StaticFieldLeak")
    private Context context;
    private DieselRecycleAdapter adapter;
    @SuppressLint("StaticFieldLeak")
    private FragmentActivity fragmentActivity;
    Handler handler = new Handler();
    private int adCount = 0;
    private InterstitialAd mInterstitialAd;
    private FragmentMainBinding mainBinding;
    private FragmentDetailBinding detailBinding;

    private void setContext(
            Context context,
            FragmentActivity fragmentActivity) {
        this.context = context;
        this.fragmentActivity = fragmentActivity;
    }

    private void setFragmentMainBinding(FragmentMainBinding binding) {
        mainBinding = binding;
    }

    private void setFragmentDetailBinding(FragmentDetailBinding binding) {
        detailBinding = binding;
    }

    private MutableLiveData<ArrayList<GasStationModel>> getGasModelList(boolean isRefresh) {
        if(isRefresh) {
            initAndLoadData();
        }else {
            if (gasModelList == null) {
                initAndLoadData();
            }
        }
        return gasModelList;
    }

    private void initAndLoadData(){
        gasModelList = new MutableLiveData<>();
        loadDieselData();
    }

    private void loadDieselData() {
        RetrofitClient retrofitClient = RetrofitClient.getInstance();

        if (retrofitClient != null) {
            RetrofitAPI retrofitApi = RetrofitClient.getRetrofitAPI();
            retrofitApi.getDieselData(SECRET_KEY, 1000).enqueue(new Callback<DieselData>() {
                @Override
                public void onResponse(@Nullable Call<DieselData> call, @Nullable Response<DieselData> response) {
                    DieselData dieselData = response != null ? response.body() : null;
                    Data[] data = dieselData != null
                            ? dieselData.getData()
                            : new Data[0];

                    ArrayList<GasStationModel> insertData = new ArrayList<>();

                    for (Data datum : data) {
                        String address = datum.getAddr();
                        String operatingTime = datum.getOpenTime();
                        String price = datum.getPrice();
                        String getDetailAddress = datum.getName();
                        String dieselStock = datum.getInventory();
                        String phoneNumber = datum.getTel();
                        String updateDate = datum.getRegDt();

                        insertData.add(new GasStationModel(
                                getDetailAddress,
                                operatingTime,
                                price,
                                address,
                                dieselStock,
                                phoneNumber,
                                updateDate
                        ));
                    }
                    gasModelList.setValue(insertData);
                }

                @Override
                public void onFailure(@Nullable Call<DieselData> call, @Nullable Throwable t) {
                    Toast.makeText(fragmentActivity,
                            "서버 통신이 원할하지 않습니다. 잠시 후 다시 접속해주세요." + t,
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private MutableLiveData<GasStationModel> getOneGasData() {
        if (oneGasModel == null) {
            oneGasModel = new MutableLiveData<>();
        }
        return oneGasModel;
    }

    private void setOneGasData(GasStationModel model) {
        oneGasModel.setValue(model);
    }

    //첫 세팅
    public void initializationMain(
            Context context,
            FragmentMainBinding binding,
            FragmentActivity fragmentActivity) {
        //컨텍스트와 액티비티 넣어주기
        setContext(context, fragmentActivity);
        //바인딩 넣어주기
        setFragmentMainBinding(binding);
        //광고 초기화
        initAd();
        //전면광고
        frontAdLoad();
        //배너광고
        bannerAdLoad();
        //리사이클러뷰 초기화
        initRecyclerView(false);
        //서치뷰 초기화
        initSearchView();
        //새로고침 리스너
        refreshListener();
    }

    private void bannerAdLoad() {
        if(mainBinding != null) {
            mainBinding.adView.loadAd(getAdRequest());
        }
        if(detailBinding != null) {
            detailBinding.adView.loadAd(getAdRequest());
        }
    }

    private void initAd() {
        MobileAds.initialize(context, initializationStatus -> {});
    }

    @SuppressLint("SetTextI18n")
    private void initRecyclerView(boolean isRefresh) {
        adapter = new DieselRecycleAdapter();

        getGasModelList(isRefresh).observe(fragmentActivity, dieselData -> {
            mainBinding.mainRecyclerView.setLayoutManager(new LinearLayoutManager(context,
                    RecyclerView.VERTICAL, false)); // 상하 스크롤
            adapter.setListData(dieselData);
        });

        mainBinding.mainRecyclerView.setAdapter(adapter);
        setOnItemClickListener();
    }


    private void setOnItemClickListener() {
        adapter.setOnItemClickListener((holder, view, position) -> {
            setOneGasData(adapter.getItem(position));
            ((HomeActivity)fragmentActivity).replaceFragment();
            ///4번에 한번 전면 광고 실행
            frontAdStart();
        });
    }

    private void initSearchView() {
        mainBinding.mainSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

    //리프레쉬 할때 동작할 코드
    private void refreshListener() {
        mainBinding.mainRefreshLayout.setOnRefreshListener(() -> {
            initRecyclerView(true);
            handler.postDelayed(() -> mainBinding.mainRefreshLayout.setRefreshing(false), 2000);
        });
    }

    private void frontAdStart() {
        if(adCount == 4) {
            adCount = 0;
        }
        if(adCount == 0){
            frontAdLoad();
        }
        if (mInterstitialAd != null && adCount == 0) {
            mInterstitialAd.show(fragmentActivity);
        }
        adCount++;
    }

    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    //전면 광고
    private void frontAdLoad() {
        InterstitialAd.load(context,"ca-app-pub-7522479314167243/6937741655", getAdRequest(),
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                mInterstitialAd = null;
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@Nullable AdError adError) {
                                mInterstitialAd = null;
                            }
                        });
                    }
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                });
    }

    //첫세팅
    public void initializationDetail(Context context,
                                     FragmentDetailBinding binding,
                                     FragmentActivity fragmentActivity) {
        //컨텍스트 넣어주기
        setContext(context, fragmentActivity);
        //바인딩 넣어주기
        setFragmentDetailBinding(binding);
        //배너 광고 로드하기
        bannerAdLoad();
        //가스 데이터 setText 해주기
        setGasData();
        //터치 시 저장 (주소)
        setOnTouchListener(true);
        //터치 시 저장 (휴대폰 번호)
        setOnTouchListener(false);
    }

    /*
    * 여기서는 대부분 DetailFragment **/
    @SuppressLint("SetTextI18n")
    private void setGasData() {
        getOneGasData().observe(fragmentActivity, gasData -> {
            detailBinding.detailAddress.setText(" " + gasData.getDetailAddress());
            detailBinding.operatingTime.setText(" " + gasData.getOperatingTime());
            detailBinding.phoneNumber.setText(" " + gasData.getPhoneNumber());
            detailBinding.updateDate.setText(" " + gasData.getUpdateDate());
            detailBinding.dieselStock.setText(gasData.getDieselStock());
            detailBinding.price.setText(gasData.getPrice());
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setOnTouchListener(boolean isAddress) {
        TextView bindingValue = isAddress
                ? detailBinding.detailAddress
                : detailBinding.phoneNumber;
                bindingValue.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_DOWN) { //눌렀을 때 동작
                    copyBoard(bindingValue.getText().toString(), isAddress);
                }
                return true;
            });
    }

    private void copyBoard(String copyValue, boolean isAddress) {
        ClipboardManager clipboard = (ClipboardManager) fragmentActivity.getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", copyValue);
        clipboard.setPrimaryClip(clip);
        if(isAddress){
            Toast.makeText(fragmentActivity, "주소가 복사되었습니다.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(fragmentActivity, "연락처가 복사되었습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
