package com.example.rikao20.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rikao20.R;
import com.example.rikao20.bean.ShopBean;

import java.util.ArrayList;
import java.util.List;

public class MyShopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ShopBean.DataBean> list;
    private Context context;

    public MyShopAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<ShopBean.DataBean> lists) {
        list.clear();
        list.addAll(lists);
        notifyDataSetChanged();
    }
    public void addList(List<ShopBean.DataBean> lists) {
        list.addAll(lists);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_shopp,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
           ViewHolder holder= (ViewHolder) viewHolder;
           holder.price.setText(list.get(i).getPrice()+"");
           holder.name.setText(list.get(i).getTitle());
           String images = list.get(i).getImages();
           String[] split = images.split("\\|");
           Glide.with(context).load(split[0]).into(holder.imageView);
           holder.button.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   butCilck.bClick(list.get(i).getPid());

               }
           });
           viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   itemCilck.click();
               }
           });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
       private ImageView imageView;
       private TextView name,price;
       private Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.item_image);
            name=itemView.findViewById(R.id.item_text_name);
            price=itemView.findViewById(R.id.item_text_price);
            button=itemView.findViewById(R.id.item_but);
        }
    }
    private ButCilck butCilck;

    public void setButCilck(ButCilck butCilck) {
        this.butCilck = butCilck;
    }

    public interface ButCilck{
        void bClick(int pid);
    }


    private ItemCilck itemCilck;
    public void setItemCilck(ItemCilck itemCilck) {
        this.itemCilck = itemCilck;
    }
    public interface ItemCilck{
        void click();
    }
}
