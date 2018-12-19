package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private WaterTitle title;
    private WaterFall myview1;
    private WaterFall myview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        title = findViewById(R.id.title);
        myview1 = findViewById(R.id.myview1);
        myview2 = findViewById(R.id.myview2);
        title.setCilck(new WaterTitle.Cilck() {
            @Override
            public void itemCilck(String str) {
                TextView tv=new TextView(MainActivity.this);
                tv.setText(str);
                tv.setTextColor(Color.BLACK);
                tv.setBackgroundResource(R.drawable.de);
                myview1.addView(tv);
            }
        });
    }
}
