package com.example.rikao20.adapter;

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
import com.example.rikao20.R;
import com.example.rikao20.bean.PerBean;
import com.example.rikao20.view.Mycustom;

import java.util.ArrayList;
import java.util.List;

public class ZiApapter extends RecyclerView.Adapter<ZiApapter.ViewHolder> {
     private List<PerBean.DataBean.ListBean> listBeans;
     private Context context;
  public ZiApapter(Context context, List<PerBean.DataBean.ListBean> list){
      this.context=context;
      listBeans=new ArrayList<>();
  }

    public void setListBeans(List<PerBean.DataBean.ListBean> listBeans) {
        this.listBeans = listBeans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ZiApapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View view=LayoutInflater.from(context).inflate(R.layout.item_zi,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZiApapter.ViewHolder viewHolder, final int i) {
        viewHolder.zi_che.setChecked(listBeans.get(i).isCheck());
        viewHolder.price.setText(listBeans.get(i).getPrice()+"");
        viewHolder.title.setText(listBeans.get(i).getTitle());
        String images = listBeans.get(i).getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(viewHolder.imageView);
       /* viewHolder.zi_che.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    listBeans.get(i).setCheck(isChecked);
                    if (ziCilck!=null){
                        ziCilck.mCilck();
                    }
            }
        });*/
        viewHolder.mycustom.setMyData(this,listBeans,i);
        viewHolder.mycustom.setMyCusCilck(new Mycustom.MyCusCilck() {
            @Override
            public void mMyCusCilck() {
                if (ziCilck!=null){
                    ziCilck.mCilck();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox zi_che;
        TextView title,price;
        ImageView imageView;
        Mycustom mycustom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            zi_che=itemView.findViewById(R.id.zi_che);
            title=itemView.findViewById(R.id.zi_name);
            price=itemView.findViewById(R.id.zi_price);
            imageView=itemView.findViewById(R.id.zi_image);
            mycustom=itemView.findViewById(R.id.cus);

        }
    }
   /* public void setseleck(boolean isAllcheck){
        for (PerBean.DataBean.ListBean bean: listBeans) {
            bean.setCheck(isAllcheck);
        }
        notifyDataSetChanged();
    }*/

    private ZiCilck ziCilck;

    public void setZiCilck(ZiCilck ziCilck) {
        this.ziCilck = ziCilck;
    }

    public interface ZiCilck{
       void mCilck();
    }
}
