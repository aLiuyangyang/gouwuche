package com.example.rikao20.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class MyView extends LinearLayout {
    private int mMax=20;
    private int mJj=20;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        finH();
        int left=0;
        int top=0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (left!=0){
                if ((left+view.getMeasuredWidth())>w){
                    top+=mMax+mJj;
                    left=0;
                }
            }
            left+=view.getMeasuredWidth()+mJj;
        }
        setMeasuredDimension(w,(mMax+mMax)>h?h:top+mMax);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        finH();
        int left=0; int top=0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (left!=0){
                if ((left+view.getMeasuredWidth())>getWidth()){
                    top+=mMax+mJj;
                    left=0;
                }
            }
            view.layout(left,top,left+view.getMeasuredWidth(),top+mMax);
            left+=view.getMeasuredWidth()+mJj;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void finH() {
        mMax=0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if(view.getMeasuredHeight()>mMax){
                mMax=view.getMeasuredHeight();
            }
        }
    }
}
