package com.example.rikao20.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rikao20.R;
import com.example.rikao20.bean.DetBean;

import java.util.ArrayList;
import java.util.List;

public class RightziAdapter extends RecyclerView.Adapter<RightziAdapter.ViewHolder> {
     private List<DetBean.DataBean.ListBean> listBean=new ArrayList<>();
     private Context context;

    public RightziAdapter(Context myContext) {
        this.context = myContext;
    }

    public void setListBean(List<DetBean.DataBean.ListBean> listBean) {
        this.listBean = listBean;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RightziAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.rigth_zi,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RightziAdapter.ViewHolder viewHolder, int i) {
         viewHolder.zitextview.setText(listBean.get(i).getName());
        Glide.with(context).load(listBean.get(i).getIcon()).into(viewHolder.ziimageview);
    }

    @Override
    public int getItemCount() {
        return listBean.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ziimageview;
        private TextView zitextview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            zitextview=itemView.findViewById(R.id.zitextview);
            ziimageview=itemView.findViewById(R.id.ziimageview);
        }
    }
}
