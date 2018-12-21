package com.example.rikao20;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rikao20.bean.PuBean;
import com.example.rikao20.mysqilt.Dao;
import com.example.rikao20.view.MyTitleView;
import com.example.rikao20.view.MyView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private MyTitleView tltle;
    private MyView Myview1;
    private MyView Myview2;
    private Button butt,buttt,but_ds;
    private Dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        butt=findViewById(R.id.butt);
        buttt=findViewById(R.id.buttt);
        but_ds=findViewById(R.id.but_ds);
        Myview1 = (MyView) findViewById(R.id.Myview1);
        Myview2 = (MyView) findViewById(R.id.Myview2);
        tltle = findViewById(R.id.tltle);
        dao=new Dao(this);
        List<PuBean> select = dao.select();
        for (int i = 0; i < select.size(); i++) {
            TextView tv=new TextView(this);
            String name = select.get(i).getName();
            tv.setText(name);
            tv.setTextColor(Color.RED);
            tv.setBackgroundResource(R.drawable.sss);
            Myview1.addView(tv);
        }

        but_ds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           UMShareAPI umShareAPI=UMShareAPI.get(MainActivity.this);
            umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                @Override
                public void onStart(SHARE_MEDIA share_media) {

                }

                @Override
                public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                    Log.i("aa",map.toString()+"");
                    String iconurl = map.get("iconurl");
                    String name = map.get("name");
                    Intent intent3=new Intent(MainActivity.this,Main3Activity.class);
                    intent3.putExtra("iconurl",iconurl);
                    intent3.putExtra("name",name);
                   startActivity(intent3);
                   finish();
                }

                @Override
                public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                }

                @Override
                public void onCancel(SHARE_MEDIA share_media, int i) {

                }
            });
            }
        });
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity.this,EjiActivity.class);
                startActivity(intent1);
            }
        });
        buttt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent2);
            }
        });
        tltle.setTitleCilck(new MyTitleView.TitleCilck() {
            @Override
            public void title(String s) {
                final TextView textView=new TextView(MainActivity.this);
                textView.setText(s);
                textView.setTextColor(Color.YELLOW);
                textView.setBackgroundResource(R.drawable.sss);
                Myview1.addView(textView);
                dao.add(s);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       Toast.makeText(MainActivity.this,textView.getText(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        tltle.setItemCilck(new MyTitleView.ItemCilck() {
            @Override
            public void item() {
                Intent intent1=new Intent(MainActivity.this,ShopActivity.class);
                startActivity(intent1);
            }
        });
      final String[] name=new String[]{"上衣","裤子","裙子"};
        for (int i = 0; i <name.length; i++) {
            final TextView textView1=new TextView(MainActivity.this);
            textView1.setTextColor(Color.BLACK);
            textView1.setText(name[i]);
            textView1.setBackgroundResource(R.drawable.sss);
            Myview2.addView(textView1);
             textView1.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Toast.makeText(MainActivity.this,textView1.getText(),Toast.LENGTH_SHORT).show();
                 }
             });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }
}
