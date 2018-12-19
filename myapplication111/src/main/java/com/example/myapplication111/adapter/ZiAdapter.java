package com.example.myapplication111.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication111.R;
import com.example.myapplication111.bean.DetBean;

import java.util.List;

public class ZiAdapter extends RecyclerView.Adapter<ZiAdapter.ViewHolder> {
    private List<DetBean.DataBean.ListBean> listBeans;
    private Context context;

    public ZiAdapter(Context context) {
        this.context=context;
    }

    public void setListBeans(List<DetBean.DataBean.ListBean> listBeans) {
        this.listBeans = listBeans;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ZiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_right_zi,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZiAdapter.ViewHolder viewHolder, int i) {
          viewHolder.zi_text.setText(listBeans.get(i).getName());
        Glide.with(context).load(listBeans.get(i).getIcon()).into(viewHolder.zi_image);
    }


    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView zi_text;
        private ImageView zi_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            zi_text=itemView.findViewById(R.id.zi_text);
            zi_image=itemView.findViewById(R.id.zi_image);
        }
    }
}
