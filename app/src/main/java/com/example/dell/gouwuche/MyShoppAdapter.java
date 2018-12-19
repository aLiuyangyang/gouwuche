package com.example.dell.gouwuche;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.dell.gouwuche.bean.JavaBean;

import java.util.ArrayList;
import java.util.List;

class MyShoppAdapter extends RecyclerView.Adapter<MyShoppAdapter.ViewHolder> {
      private List<JavaBean.DataBean> list;
      private Context context;

    public MyShoppAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_sjopp,viewGroup,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(list.get(i).getSellerName());
       final ProduAdapter produAdapter=new ProduAdapter(context,list.get(i).getList());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        viewHolder.recyclerView.setLayoutManager(linearLayoutManager);
        viewHolder.recyclerView.setAdapter(produAdapter);
        produAdapter.setList(list.get(i).getList());
        viewHolder.checkBox.setChecked(list.get(i).isCheck());

       produAdapter.setListener(new ProduAdapter.ShopCallBackListener() {
           @Override
           public void callBack() {
               if (mShopCallBackListener!=null){
                   mShopCallBackListener.callBack(list);
               }
               List<JavaBean.DataBean.ListBean> listBean=list.get(i).getList();
               boolean isAllCheck=true;
               for (JavaBean.DataBean.ListBean bean:listBean){
                   if (!bean.isCheck()){
                       isAllCheck=false;
                       break;
                   }
               }
               viewHolder.checkBox.setChecked(isAllCheck);
               list.get(i).setCheck(isAllCheck);
           }
       });
       viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               list.get(i).setCheck(viewHolder.checkBox.isChecked());
               produAdapter.seleremover(viewHolder.checkBox.isChecked());
           }
       });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<JavaBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView textView;
        private RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.item_check);
            textView=itemView.findViewById(R.id.item_text);
            recyclerView=itemView.findViewById(R.id.item_recy);
        }
    }
    private ShopCallBackListener mShopCallBackListener;

    public void setListener(ShopCallBackListener listener) {
        this.mShopCallBackListener = listener;
    }

    public interface ShopCallBackListener {
        void callBack(List<JavaBean.DataBean> list);
    }
}
