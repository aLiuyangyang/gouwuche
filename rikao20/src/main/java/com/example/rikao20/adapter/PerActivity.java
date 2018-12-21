package com.example.rikao20.adapter;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rikao20.R;
import com.example.rikao20.bean.PerBean;
import com.example.rikao20.presenter.Presenterlmpl;
import com.example.rikao20.view.IView;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerActivity extends AppCompatActivity implements View.OnClickListener,IView {
    private String PerUrl="http://www.zhaoapi.cn/product/getCarts";
    private Presenterlmpl presenterlmpl;
    private RecyclerView per_recy;
    private MyPerAdapter adapter;
    private CheckBox checkBox;
    private TextView mytotal,mynum;
    private List<PerBean.DataBean> list=new ArrayList<>();
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per);
        checkBox=findViewById(R.id.mycheck);
        checkBox.setOnClickListener(this);
        mynum=findViewById(R.id.mynum);
        mytotal=findViewById(R.id.mytotal);
        per_recy=findViewById(R.id.per_recy);
        presenterlmpl=new Presenterlmpl(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        per_recy.setLayoutManager(linearLayoutManager);
        adapter=new MyPerAdapter(this);
        per_recy.setAdapter(adapter);
        Map<String,String> mar=new HashMap<>();
        mar.put("uid",23755+"");
        presenterlmpl.setRequestData(PerUrl,mar,PerBean.class);
        adapter.setPerCilck(new MyPerAdapter.PerCilck() {
            @Override
            public void mPercilck(List<PerBean.DataBean> list) {
                double totalprice=0;
                int num=0;
                int totalnum=0;
                for (int i = 0; i < list.size(); i++) {
                    List<PerBean.DataBean.ListBean> list1 = list.get(i).getList();
                    for (int j = 0; j < list1.size(); j++) {
                        totalnum+=list1.get(j).getNum();
                        if (list1.get(j).isCheck()){
                            totalprice+=(list1.get(j).getNum()*list1.get(j).getPrice());
                            num+=list1.get(j).getNum();
                        }
                    }
                }
                if (num<totalnum){
                    checkBox.setChecked(false);
                }else {
                    checkBox.setChecked(true);
                }

                mytotal.setText("总计:"+totalprice);
                mynum.setText("数量"+num);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mycheck:
                 setCheck(checkBox.isChecked());
                  adapter.notifyDataSetChanged();
                break;
                default:break;
        }
    }

    @Override
    public void setData(Object data) {

        if (data instanceof PerBean) {
            if (data instanceof PerBean) {
                PerBean perBean = (PerBean) data;
                list=perBean.getData();
                adapter.setList(list);
            }

        }
    }
    public void setCheck(boolean bool){
           double totalprice=0;
           int num=0;
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setCheck(bool);
            List<PerBean.DataBean.ListBean> lists = this.list.get(i).getList();
            for (int j = 0; j < lists.size(); j++) {
                lists.get(j).setCheck(bool);
                num+=lists.get(j).getNum();
                totalprice+=(lists.get(j).getNum()*lists.get(j).getPrice());
            }
        }
        if (bool){
            mytotal.setText("总计:"+totalprice);
            mynum.setText("数量"+num);
        }else {
            mytotal.setText("总计:");
            mynum.setText("数量");
        }
    }
}
