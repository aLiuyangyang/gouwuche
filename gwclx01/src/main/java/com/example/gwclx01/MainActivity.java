package com.example.gwclx01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.gwclx01.adapter.MyShoppAadapter;
import com.example.gwclx01.bean.Bean;
import com.example.gwclx01.presenter.Presneterimpl;
import com.example.gwclx01.view.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView {
    private Presneterimpl presneterimpl;
    private String path = "https://www.zhaoapi.cn/product/getCarts";
    private List<Bean.DataBean> list = new ArrayList<>();
    private MyShoppAadapter adapter;
    private RecyclerView recy;
    private CheckBox qxall;
    private TextView p_price;
    private TextView m_num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initdata();

    }



    private void initView() {
        recy = (RecyclerView) findViewById(R.id.recy);
        qxall = (CheckBox) findViewById(R.id.qxall);
        p_price = (TextView) findViewById(R.id.p_price);
        m_num = (TextView) findViewById(R.id.m_num);
        presneterimpl=new Presneterimpl(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recy.setLayoutManager(linearLayoutManager);
        adapter=new MyShoppAadapter(this);
        recy.setAdapter(adapter);

        adapter.setShopitemCilck(new MyShoppAadapter.ShopitemCilck() {
            @Override
            public void itemCilck(List<Bean.DataBean> list) {
                double totalprice=0;
                int num=0;
                double totalnum = 0;
                for (int a = 0; a <list.size() ; a++) {
                    List<Bean.DataBean.ListBean> list1 = list.get(a).getList();
                    for (int i = 0; i < list1.size(); i++) {
                        totalnum+=list1.get(i).getNum();
                        if (list1.get(i).isCheck()){
                            totalprice+=(list1.get(i).getPrice()*list1.get(i).getNum());
                            num+=list1.get(i).getNum();
                        }
                    }
                }
                if (num<totalnum){
                    qxall.setChecked(false);
                }else {
                    qxall.setChecked(true);
                }
                m_num.setText("去结算"+num);
                p_price.setText("总计"+totalprice);
            }
        });

    }
    private void initdata() {
        Map<String,String> mar=new HashMap<>();
        mar.put("uid","99");
        presneterimpl.setRequestData(path,mar,Bean.class);
    }
    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.qxall:
                 checkall(qxall.isChecked());
                 adapter.notifyDataSetChanged();
                 break;
                 default:break;
         }
    }

    @Override
    public void setData(Object data) {
       if (data instanceof Bean){
           Bean bean= (Bean) data;
           list=bean.getData();
           if (list!=null){
               list.remove(0);
               adapter.setList(list);
           }
       }
    }

    public void checkall(boolean bool){
       double totloprice=0;
       int num=0;
        for (int a = 0; a <list.size() ; a++) {
            Bean.DataBean bean=list.get(a);
            bean.setCheck(bool);
            List<Bean.DataBean.ListBean> lists = this.list.get(a).getList();
            for (int i = 0; i < lists.size(); i++) {
                lists.get(i).setCheck(bool);
                totloprice+=(lists.get(i).getNum()*lists.get(i).getPrice());
                num+=lists.get(i).getNum();
            }

        }
        if (bool){
            m_num.setText("去结算"+num);
            p_price.setText("总计"+totloprice);
        }else {
            m_num.setText("去结算");
            p_price.setText("总计");
        }
    }
}
