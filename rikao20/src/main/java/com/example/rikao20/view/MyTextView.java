package com.example.rikao20.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.rikao20.R;

@SuppressLint("AppCompatCustomView")
public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        int color = typedArray.getColor(R.styleable.MyTextView_Textcolor, Color.RED);
        float aFloat = typedArray.getFloat(R.styleable.MyTextView_Textname, 20);
        setTextColor(color);
        setTextSize(aFloat);
        typedArray.recycle();
    }
}
