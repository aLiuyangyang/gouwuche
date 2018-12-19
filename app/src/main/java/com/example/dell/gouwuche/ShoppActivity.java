package com.example.dell.gouwuche;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.dell.gouwuche.bean.JavaBean;
import com.example.dell.gouwuche.presenter.Presneterimpl;
import com.example.dell.gouwuche.view.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppActivity extends AppCompatActivity implements View.OnClickListener,IView {
    private RecyclerView xrecy;
    private CheckBox check;
    private TextView price;
    private TextView total;
    private MyShoppAdapter adapter;
    private Presneterimpl presneterimpl;
    private String ShoppUrl = "http://www.zhaoapi.cn/product/getCarts";
    private List<JavaBean.DataBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopp);
        initView();
        adapter.setListener(new MyShoppAdapter.ShopCallBackListener() {
            @Override
            public void callBack(List<JavaBean.DataBean> list) {
                double totalprice = 0;
                int num = 0;
                int totalnum = 0;
                for (int i = 0; i < list.size(); i++) {
                    List<JavaBean.DataBean.ListBean> listBeans = list.get(i).getList();
                    for (int j = 0; j < listBeans.size(); j++) {
                        totalnum = totalnum + listBeans.get(j).getNum();
                        if (listBeans.get(j).isCheck()) {
                            totalprice = totalprice + (listBeans.get(j).getPrice());
                            listBeans.get(j).getNum();
                            num += num + listBeans.get(j).getNum();
                        }
                    }
                }
                if (num < totalnum) {
                    check.setChecked(false);
                } else {
                    check.setChecked(true);
                }
                price.setText("合计" + totalprice);
                total.setText("去结算(" + num + ")");

            }
        });
    }

    private void initView() {
        presneterimpl = new Presneterimpl(this);
        xrecy = findViewById(R.id.xrecy);
        check = (CheckBox) findViewById(R.id.check);
        check.setOnClickListener(this);
        price = (TextView) findViewById(R.id.price);
        total = (TextView) findViewById(R.id.total);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrecy.setLayoutManager(linearLayoutManager);
        adapter = new MyShoppAdapter(this);
        xrecy.setAdapter(adapter);

        Map<String, String> mar = new HashMap<>();
        mar.put("uid", "90");
        presneterimpl.setRequestData(ShoppUrl, mar, JavaBean.class);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.check:
                 checkSeller(check.isChecked());
                 adapter.notifyDataSetChanged();
                 break;
                 default:break;
         }
    }

    @Override
    public void setData(Object data) {
        if (data instanceof JavaBean) {
            JavaBean javaBean = (JavaBean) data;
            list = javaBean.getData();
            if (list != null) {
                list.remove(0);
                adapter.setList(list);
            }
        }
    }

    private void checkSeller(boolean bool) {
        double totalPrice = 0;
        int num = 0;
        for (int a = 0; a < list.size(); a++) {
            //遍历商家，改变状态
            JavaBean.DataBean dataBean = list.get(a);
            dataBean.setCheck(bool);

            List<JavaBean.DataBean.ListBean> listAll = list.get(a).getList();
            for (int i = 0; i < listAll.size(); i++) {
                //遍历商品，改变状态
                listAll.get(i).setCheck(bool);
                totalPrice = totalPrice + (listAll.get(i).getPrice() * listAll.get(i).getNum());
                num = num + listAll.get(i).getNum();
            }
        }

        if (bool) {
            price.setText("合计：" + totalPrice);
            total.setText("去结算(" + num + ")");
        } else {
            price.setText("合计：0.00");
            total.setText("去结算(0)");
        }

    }
}
