package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class WaterText extends TextView {
    public WaterText(Context context) {
        super(context);
    }

    public WaterText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WaterText);
        int color = typedArray.getColor(R.styleable.WaterText_Textcolor, Color.RED);
        setTextColor(color);
        typedArray.recycle();

    }
}
