package com.example.dieselfluid.view.activity.recyclerview;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

//리사이클러뷰 간격 조절

public class RecyclerViewDecoration extends RecyclerView.ItemDecoration {

    private final int divHeight;

    public RecyclerViewDecoration(int divHeight)
    {
        this.divHeight = divHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = divHeight;
    }
}