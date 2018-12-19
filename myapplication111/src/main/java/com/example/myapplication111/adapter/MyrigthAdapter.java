package com.example.myapplication111.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication111.MainActivity;
import com.example.myapplication111.R;
import com.example.myapplication111.bean.DetBean;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyrigthAdapter extends RecyclerView.Adapter<MyrigthAdapter.ViewHolder> {
    private List<DetBean.DataBean> list;
    private Context context;

    public MyrigthAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<DetBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyrigthAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_right,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyrigthAdapter.ViewHolder viewHolder, int i) {
       viewHolder.right_text.setText(list.get(i).getName());

        GridLayoutManager linearLayoutManager=new GridLayoutManager(context,2);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        viewHolder.right_recy.setLayoutManager(linearLayoutManager);
        ZiAdapter ziAdapter=new ZiAdapter(context);
        viewHolder.right_recy.setAdapter(ziAdapter);
        ziAdapter.setListBeans(list.get(i).getList());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       private TextView right_text;
       private RecyclerView right_recy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            right_text=itemView.findViewById(R.id.right_text);
            right_recy=itemView.findViewById(R.id.right_recy);
        }
    }

}
