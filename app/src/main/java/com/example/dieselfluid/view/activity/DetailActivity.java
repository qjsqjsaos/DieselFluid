package com.example.dieselfluid.view.activity;
import android.os.Bundle;
import android.util.Log;
import com.example.dieselfluid.databinding.ActivityDetailBinding;
import com.example.dieselfluid.model.GasStationModel;
import com.example.dieselfluid.viewmodel.DetailViewModel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    private DetailViewModel detailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getViewModelData();
    }

    private void getViewModelData() {
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        detailViewModel.getGasData().observe(this, gasData -> {
            Log.d("result21", String.valueOf(gasData.getDetailAddress()));
            binding.detailAddress.setText(gasData.getDetailAddress());
            binding.operatingTime.setText(gasData.getOperatingTime());
            binding.phoneNumber.setText(gasData.getPhoneNumber());
            binding.updateDate.setText(gasData.getUpdateDate());
            binding.dieselStock.setText(gasData.getDieselStock());
            binding.price.setText(gasData.getPrice());
        });
    }
}