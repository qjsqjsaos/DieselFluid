package com.example.dieselfluid.view.activity.recyclerview;

import android.content.Context;
import android.view.View;

public interface OnItemClickListener {
    public void onItemClick(DieselRecycleAdapter.DieselRecycleViewHolder holder, View view, int position);
}
