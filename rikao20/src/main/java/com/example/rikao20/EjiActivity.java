package com.example.rikao20;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rikao20.adapter.MyErjiAdapter;
import com.example.rikao20.adapter.RightAdapter;
import com.example.rikao20.bean.Bean;
import com.example.rikao20.bean.DetBean;
import com.example.rikao20.bean.ShopBean;
import com.example.rikao20.presenter.Presenterlmpl;
import com.example.rikao20.view.IView;

import java.util.HashMap;
import java.util.Map;

public class EjiActivity extends AppCompatActivity implements View.OnClickListener,IView {

    private RecyclerView recy1;
    private RecyclerView recy2;
    private Presenterlmpl presenterlmpl;
    private MyErjiAdapter adapter;
    private String flpath = "http://www.zhaoapi.cn/product/getCatagory";
    private String detpath = "http://www.zhaoapi.cn/product/getProductCatagory";
    private RightAdapter rightAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eji);
        recy1 = (RecyclerView) findViewById(R.id.recy1);
        recy2 = (RecyclerView) findViewById(R.id.recy2);
        initView();
        initright();
    }



    private void initView() {

        presenterlmpl=new Presenterlmpl(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recy1.setLayoutManager(linearLayoutManager);
        Map<String,String> mar=new HashMap<>();
        presenterlmpl.setRequestData(flpath,mar,Bean.class);
        adapter=new MyErjiAdapter(this);
        recy1.setAdapter(adapter);
        adapter.setErCilck(new MyErjiAdapter.ErCilck() {
            @Override
            public void erCilcks(int cid) {
                Map<String,String> map=new HashMap<>();
                map.put("cid",String.valueOf(cid));
                presenterlmpl.setRequestData(detpath,map,DetBean.class);
            }
        });
    }
    private void initright() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recy2.setLayoutManager(linearLayoutManager);
        rightAdapter=new RightAdapter(this);
        recy2.setAdapter(rightAdapter);

    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void setData(Object data) {
       if (data instanceof Bean){
           Bean bean= (Bean) data;
           adapter.setList(bean.getData());
       }
       if (data instanceof DetBean){
           DetBean detBean= (DetBean) data;
           rightAdapter.setList(detBean.getData());
       }
    }
}
