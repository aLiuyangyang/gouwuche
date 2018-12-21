
package com.example.rikao20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rikao20.adapter.MyShopAdapter;
import com.example.rikao20.adapter.PerActivity;
import com.example.rikao20.bean.JiaBean;
import com.example.rikao20.bean.PerBean;
import com.example.rikao20.bean.ShopBean;
import com.example.rikao20.presenter.Presenterlmpl;
import com.example.rikao20.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener,IView {
      private Presenterlmpl presenterlmpl;
      private XRecyclerView xRecyclerView;
      private MyShopAdapter adapter;
      private int page;

      private Button xl,px,jg;
      private String ShopUrl="http://www.zhaoapi.cn/product/searchProducts";
      private String JaoUrl="http://www.zhaoapi.cn/product/addCart";
      private int sort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        page=1;
        xl=findViewById(R.id.xl);
        xl.setOnClickListener(this);
        px=findViewById(R.id.px);
        px.setOnClickListener(this);
        jg=findViewById(R.id.jg);
        jg.setOnClickListener(this);
        xRecyclerView=findViewById(R.id.xrecy);
        presenterlmpl=new Presenterlmpl(this);
        adapter=new MyShopAdapter(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.setAdapter(adapter);

        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                 page=1;
                 load(page,sort);
            }

            @Override
            public void onLoadMore() {
                load(page,sort);
            }
        });
        load(page,sort);
      adapter.setButCilck(new MyShopAdapter.ButCilck() {
          @Override
          public void bClick(int pid) {

              Map<String,String> map=new HashMap<>();
              map.put("uid",23755+"");
              map.put("pid",pid+"");
              presenterlmpl.setRequestData(JaoUrl,map,JiaBean.class);

          }
      });
      adapter.setItemCilck(new MyShopAdapter.ItemCilck() {
          @Override
          public void click() {
              Intent intent1=new Intent(ShopActivity.this,PerActivity.class);
              startActivity(intent1);
          }
      });
    }

    private void load(int page,int sort) {
        Map<String,String> mar=new HashMap<>();
        mar.put("keywords","手机");
        mar.put("page",page+"");
        mar.put("sort",sort+"");
       presenterlmpl.setRequestData(ShopUrl,mar,ShopBean.class);
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.px:
              sort=0;
              Map<String,String> mar1=new HashMap<>();
              mar1.put("keywords","手机");
              mar1.put("page",page+"");
              mar1.put("sort",sort+"");
              presenterlmpl.setRequestData(ShopUrl,mar1,ShopBean.class);
              break;
          case R.id.xl:
              sort=1;
              Map<String,String> mar2=new HashMap<>();
              mar2.put("keywords","手机");
              mar2.put("page",page+"");
              mar2.put("sort",sort+"");
              presenterlmpl.setRequestData(ShopUrl,mar2,ShopBean.class);
              break;
          case R.id.jg:
              sort=2;
              Map<String,String> mar3=new HashMap<>();
              mar3.put("keywords","手机");
              mar3.put("page",page+"");
              mar3.put("sort",sort+"");
              presenterlmpl.setRequestData(ShopUrl,mar3,ShopBean.class);
              break;
              default:break;
      }
    }

    @Override
    public void setData(Object data) {
        if (data instanceof ShopBean) {
            ShopBean bean = (ShopBean) data;
//            String images = bean.getData().get(1).getImages();
//            String[] split = images.split("\\|");
//            List<String> list = Arrays.asList(split);

            if (page == 1) {
                adapter.setList(bean.getData());
            } else {
                adapter.addList(bean.getData());
            }
            page++;
            xRecyclerView.loadMoreComplete();
            xRecyclerView.refreshComplete();
        }
    }
}
