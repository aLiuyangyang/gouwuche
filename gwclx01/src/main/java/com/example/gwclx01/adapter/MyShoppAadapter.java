package com.example.gwclx01.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.gwclx01.R;
import com.example.gwclx01.bean.Bean;

import java.util.ArrayList;
import java.util.List;

public class MyShoppAadapter extends RecyclerView.Adapter<MyShoppAadapter.ViewHolder> {
    private List<Bean.DataBean> list=new ArrayList<>();
    private Context context;
    public MyShoppAadapter(Context context){
        this.context=context;
    }

    public void setList(List<Bean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyShoppAadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_shop,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyShoppAadapter.ViewHolder viewHolder, final int i) {
        //商家姓名
          viewHolder.textView.setText(list.get(i).getSellerName());
          //选中状态
          viewHolder.checkBox.setChecked(list.get(i).isCheck());
          //得到商品
        final Proadapter proadapter=new Proadapter(context,list.get(i).getList());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        viewHolder.sp_recy.setLayoutManager(linearLayoutManager);
        viewHolder.sp_recy.setAdapter(proadapter);
        proadapter.setProCilck(new Proadapter.ProCilck() {
            @Override
            public void backCilck() {
                if (shopitemCilck!=null){
                    shopitemCilck.itemCilck(list);
                }
                List<Bean.DataBean.ListBean> mlist = MyShoppAadapter.this.list.get(i).getList();
                boolean isall=true;
                for (Bean.DataBean.ListBean bean:mlist) {
                    if (!bean.isCheck()){
                         isall=false;
                         break;
                    }
                }
                viewHolder.checkBox.setChecked(isall);
                mlist.get(i).setCheck(isall);
            }
        });

        viewHolder.sp_recy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(i).setCheck(viewHolder.checkBox.isChecked());
                proadapter.setremoverall(viewHolder.checkBox.isChecked());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
          private CheckBox checkBox;
          private TextView textView;
          private RecyclerView sp_recy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sp_recy=itemView.findViewById(R.id.sp_crey);
            checkBox=itemView.findViewById(R.id.sj_check);
            textView=itemView.findViewById(R.id.sj_text);
        }
    }
    private ShopitemCilck shopitemCilck;

    public void setShopitemCilck(ShopitemCilck shopitemCilck) {
        this.shopitemCilck = shopitemCilck;
    }

    public interface ShopitemCilck{
        void itemCilck(List<Bean.DataBean> list);
    }
}
