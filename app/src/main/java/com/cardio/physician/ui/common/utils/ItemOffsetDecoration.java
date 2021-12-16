package com.cardio.physician.ui.common.utils;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

    private int mItemOffset;

    public ItemOffsetDecoration(int itemOffset) {
        mItemOffset = itemOffset;
    }

    public ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
        this(context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //  outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
        int position = parent.getChildLayoutPosition(view);
        //set right margin to all
        outRect.top = mItemOffset;
        //set bottom margin to all
        outRect.bottom = mItemOffset;
        //we only add top margin to the first row
        //add left margin only to the first column
        if (position % 2 != 0) {
            outRect.left = mItemOffset;
        } else {
            outRect.right = mItemOffset;
        }
    }
}