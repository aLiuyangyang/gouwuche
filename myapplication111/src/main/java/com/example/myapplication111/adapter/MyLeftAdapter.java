package com.example.myapplication111.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication111.R;
import com.example.myapplication111.bean.Bean;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyLeftAdapter extends RecyclerView.Adapter<MyLeftAdapter.ViewHolder> {
    private List<Bean.DataBean> list;
    private Context context;

    public MyLeftAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<Bean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyLeftAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_letf,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyLeftAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(list.get(i).getName());
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.itemCilck(list.get(i).getCid());
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
            textView=itemView.findViewById(R.id.left_text);
        }
    }
    private Click click;

    public void setClick(Click click) {
        this.click = click;
    }

    public interface Click{
        void itemCilck(int cid);
    }
}
