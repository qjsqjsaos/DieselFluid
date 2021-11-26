package com.example.dieselfluid.view.activity.recyclerview;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dieselfluid.R;
import com.example.dieselfluid.databinding.RecycleListItemBinding;
import com.example.dieselfluid.model.GasStationModel;
import java.util.ArrayList;

public class DieselRecycleAdapter extends RecyclerView.Adapter<DieselRecycleAdapter.DieselRecycleViewHolder>
        implements Filterable, OnItemClickListener {
    private ArrayList<GasStationModel> gasStationList;
    private ArrayList<GasStationModel> gasStationListCopy;
    OnItemClickListener listener;

    public DieselRecycleAdapter () {
        gasStationList = new ArrayList<>();
        gasStationListCopy = new ArrayList<>();
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


    public void setListData(ArrayList<GasStationModel> list) {
        gasStationList = list;
        gasStationListCopy = new ArrayList<>(list);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(DieselRecycleViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder,view,position);
        }
    }

    @Override
    public Filter getFilter() {
        if(gasStationList != null){
            return searchedFilter;
        }
        return null;
    }

    private final Filter searchedFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<GasStationModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(gasStationListCopy);
            } else {

                String filterPattern = constraint.toString().toLowerCase().trim();
                for (GasStationModel item : gasStationListCopy) {
                    if (item.getDetailAddress().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @SuppressLint("NotifyDataSetChanged")
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            Log.d("gasStationList1", String.valueOf(gasStationList));
            gasStationList.clear();

            gasStationList.addAll((ArrayList<GasStationModel>) results.values);
            notifyDataSetChanged();
        }
    };

    public GasStationModel getItem(int position){
        return gasStationList.get(position);
    }

    public class DieselRecycleViewHolder extends RecyclerView.ViewHolder {

        private final RecycleListItemBinding binding;

        public DieselRecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RecycleListItemBinding.bind(itemView);
            binding.cardItem.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if(listener != null) {
                    listener.onItemClick(DieselRecycleViewHolder.this, v, position);
                }
            });
        }

        @SuppressLint("SetTextI18n")
        void bindItem(GasStationModel gasModel){
            binding.cardTitle.setText(" "  + gasModel.getDetailAddress()); //상세 주소 넣어주기
            binding.dieselStock.setText(gasModel.getDieselStock()); //요소수 재고 값 넣어주기
            binding.price.setText(gasModel.getPrice() + "원"); //가격 넣어주기
            binding.location.setText(gasModel.getLocation()); //위치 넣어주기
        }
    }
}




