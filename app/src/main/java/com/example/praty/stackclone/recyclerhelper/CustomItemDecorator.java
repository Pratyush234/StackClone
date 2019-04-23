package com.example.praty.stackclone.recyclerhelper;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

//Recyclerview item decorator for setting up spaces between rows of recyclerview
public class CustomItemDecorator extends RecyclerView.ItemDecoration {

    public final int verticalSpaceHeight;


    public CustomItemDecorator(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom=verticalSpaceHeight;
    }
}
