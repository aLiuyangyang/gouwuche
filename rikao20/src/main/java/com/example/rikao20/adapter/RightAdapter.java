package com.example.rikao20.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rikao20.R;
import com.example.rikao20.bean.DetBean;

import java.util.ArrayList;
import java.util.List;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {
       private List<DetBean.DataBean> mlist;
       private Context mContext;

    public RightAdapter(Context context) {
        this.mContext = context;
        mlist=new ArrayList<>();
    }

    public void setList(List<DetBean.DataBean> list) {
        this.mlist = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_right,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RightAdapter.ViewHolder viewHolder, int i) {
        viewHolder.text1.setText(mlist.get(i).getName());
        RightziAdapter adapter1=new RightziAdapter(mContext);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        viewHolder.recy1.setLayoutManager(linearLayoutManager);
        adapter1= new RightziAdapter(mContext);
        viewHolder.recy1.setAdapter(adapter1);
        adapter1.setListBean(mlist.get(i).getList());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text1;
        private RecyclerView recy1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text1=itemView.findViewById(R.id.rigthtext);
            recy1=itemView.findViewById(R.id.zirecy);
        }
    }
}
