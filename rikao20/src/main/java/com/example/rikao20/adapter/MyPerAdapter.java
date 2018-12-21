package com.example.rikao20.adapter;

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

import com.example.rikao20.R;
import com.example.rikao20.bean.PerBean;

import java.util.ArrayList;
import java.util.List;

public class MyPerAdapter extends RecyclerView.Adapter<MyPerAdapter.ViewHolder> {
    private List<PerBean.DataBean> list;
    private Context context;

    public MyPerAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<PerBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyPerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_per,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyPerAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.per_text.setText(list.get(i).getSellerName());
        viewHolder.per_check.setChecked(list.get(i).isCheck());

        final ZiApapter adapter=new ZiApapter(context,list.get(i).getList());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        viewHolder.zi_recy.setLayoutManager(linearLayoutManager);
        viewHolder.zi_recy.setAdapter(adapter);
        adapter.setListBeans(list.get(i).getList());
       adapter.setZiCilck(new ZiApapter.ZiCilck() {
            @Override
            public void mCilck() {
               if (perCilck!=null){
                   perCilck.mPercilck(list);
               }

//               List<PerBean.DataBean.ListBean> mlist = MyPerAdapter.this.list.get(i).getList();
//               boolean isAllcheck=true;
//                for (PerBean.DataBean.ListBean listBean:mlist) {
//                    if (!listBean.isCheck()){
//                        isAllcheck=false;
//                        break;
//                    }
//                }
//                viewHolder.per_check.setChecked(isAllcheck);
//                list.get(i).setCheck(isAllcheck);
            }
        });
//        viewHolder.per_check.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                list.get(i).setCheck(viewHolder.per_check.isChecked());
//                adapter.setseleck(viewHolder.per_check.isChecked());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox per_check;
        private TextView per_text;
        RecyclerView zi_recy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            per_check=itemView.findViewById(R.id.per_check);
            per_text=itemView.findViewById(R.id.per_text);
            zi_recy=itemView.findViewById(R.id.zi_recy);
        }
    }
    private PerCilck perCilck;

    public void setPerCilck(PerCilck perCilck) {
        this.perCilck = perCilck;
    }

    public interface PerCilck{
        void mPercilck(List<PerBean.DataBean> list);
    }
}
