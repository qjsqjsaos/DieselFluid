package com.example.dieselfluid.view.activity.recyclerview;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dieselfluid.R;
import com.example.dieselfluid.databinding.RecycleListItemBinding;
import com.example.dieselfluid.model.GasStationModel;

import java.util.ArrayList;

public class DieselRecycleAdapter extends RecyclerView.Adapter<DieselRecycleAdapter.DieselRecycleViewHolder>{
    private ArrayList<GasStationModel> gasStationList;

    public DieselRecycleAdapter (ArrayList<GasStationModel> gasStationList) {
        this.gasStationList = gasStationList;
    }


    @NonNull
    @Override
    public DieselRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_list_item, parent, false);
        return new DieselRecycleViewHolder(view);
    }

    //모델에 있는 각 항목에 값 넣어주기
    @Override
    public void onBindViewHolder(@NonNull DieselRecycleViewHolder holder, int position) {
        holder.bindItem(gasStationList.get(position));
    }

    @Override
    public int getItemCount() {
        return gasStationList.size();
    }

    class DieselRecycleViewHolder extends RecyclerView.ViewHolder {

        private RecycleListItemBinding binding;

        public DieselRecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RecycleListItemBinding.bind(itemView);
            binding.cardItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "되는지?", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @SuppressLint("SetTextI18n")
        void bindItem(GasStationModel gasModel){
            binding.cardTitle.setText(gasModel.getDetailAddress()); //상세 주소 넣어주기
            binding.dieselStock.setText(gasModel.getDieselStock()); //요소수 재고 값 넣어주기
            binding.price.setText(gasModel.getPrice() + "원"); //가격 넣어주기
            binding.location.setText(gasModel.getLocation()); //위치 넣어주기
        }
    }
}


