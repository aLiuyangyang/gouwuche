package com.example.gwclx01.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gwclx01.R;
import com.example.gwclx01.bean.Bean;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Proadapter extends RecyclerView.Adapter<Proadapter.ViewHolder> {
    private List<Bean.DataBean.ListBean> listBeans=new ArrayList<>();
private Context context;
    public Proadapter(Context context, List<Bean.DataBean.ListBean> list) {
        this.context=context;
        this.listBeans=list;
    }


    @NonNull
    @Override
    public Proadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_pro,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Proadapter.ViewHolder viewHolder, final int i) {
        String images = listBeans.get(i).getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(viewHolder.imageView);
        viewHolder.textprice.setText(listBeans.get(i).getPrice()+"");
        viewHolder.textname.setText(listBeans.get(i).getTitle());
        viewHolder.checkBox.setChecked(listBeans.get(i).isCheck());
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listBeans.get(i).setCheck(isChecked);
                if (proCilck!=null){
                    proCilck.backCilck();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private CheckBox checkBox;
        private TextView textname,textprice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            checkBox=itemView.findViewById(R.id.checkbox);
            textname=itemView.findViewById(R.id.name);
            textprice=itemView.findViewById(R.id.price);
        }
    }
    public void setremoverall(boolean isall) {
        for (Bean.DataBean.ListBean bean:listBeans) {
            bean.setCheck(isall);
        }
        notifyDataSetChanged();
    }
    private ProCilck proCilck;

    public void setProCilck(ProCilck proCilck) {
        this.proCilck = proCilck;
    }
    public interface ProCilck{
        void backCilck();
    }
}
