package com.example.myapplication111;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.myapplication111.adapter.MyLeftAdapter;
import com.example.myapplication111.adapter.MyrigthAdapter;
import com.example.myapplication111.bean.Bean;
import com.example.myapplication111.bean.DetBean;
import com.example.myapplication111.presenter.Presneterimpl;
import com.example.myapplication111.view.IView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView {
    private String flpath = "http://www.zhaoapi.cn/product/getCatagory";
    private String detpath = "http://www.zhaoapi.cn/product/getProductCatagory";
    private Presneterimpl presneterimpl;
    private RecyclerView main_recy_left;
    private RecyclerView main_recy_right;
    private MyLeftAdapter adapter;
    private MyrigthAdapter rightadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_recy_left = (RecyclerView) findViewById(R.id.main_recy_left);
        main_recy_right = (RecyclerView) findViewById(R.id.main_recy_right);
        presneterimpl=new Presneterimpl(this);
        initView();
        initHight();
    }
    private void initView() {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        main_recy_left.setLayoutManager(linearLayoutManager);
        presneterimpl.setRequestData(flpath,new HashMap<String, String>(),Bean.class);
        adapter=new MyLeftAdapter(this);
        main_recy_left.setAdapter(adapter);
        adapter.setClick(new MyLeftAdapter.Click() {
            @Override
            public void itemCilck(int cid) {
                Map<String,String> map=new HashMap<>();
                map.put("cid",String.valueOf(cid));
                presneterimpl.setRequestData(detpath,map,DetBean.class);
            }
        });

    }

    private void initHight() {
       LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
       linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
       main_recy_right.setLayoutManager(linearLayoutManager);
       rightadapter=new MyrigthAdapter(this);
       main_recy_right.setAdapter(rightadapter);
    }

    @Override
    public void setData(Object data) {
       if (data instanceof Bean){
           Bean bean= (Bean) data;
           adapter.setList(bean.getData());
       }
       if (data instanceof DetBean){
           DetBean detBean= (DetBean) data;
            rightadapter.setList(detBean.getData());
       }
    }
}
