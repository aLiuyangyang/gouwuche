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
import com.example.rikao20.bean.Bean;

import java.util.ArrayList;
import java.util.List;

public class MyErjiAdapter extends RecyclerView.Adapter<MyErjiAdapter.ViewHolder> {
    private List<Bean.DataBean> list;
    private Context mContext;

    public MyErjiAdapter(Context context) {
        this.mContext = context;
        list=new ArrayList<>();
    }

    public void setList(List<Bean.DataBean> lists) {
        this.list = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyErjiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.erji,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyErjiAdapter.ViewHolder viewHolder, final int i) {
            viewHolder.textView.setText(list.get(i).getName());
            viewHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    erCilck.erCilcks(list.get(i).getCid());
                }
            });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_erji);
        }
    }
    private ErCilck erCilck;

    public void setErCilck(ErCilck erCilck) {
        this.erCilck = erCilck;
    }

    public  interface ErCilck{
        void erCilcks(int cid);
    }
}
