package com.example.dell.gouwuche;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.gouwuche.bean.JavaBean;

import java.util.ArrayList;
import java.util.List;

public class ProduAdapter extends RecyclerView.Adapter<ProduAdapter.ViewHolder> {
    private List<JavaBean.DataBean.ListBean> list;
    private Context context;

    public ProduAdapter(Context context, List<JavaBean.DataBean.ListBean> list) {
        this.context = context;
        this.list =new ArrayList<>();
    }

    public void setList(List<JavaBean.DataBean.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProduAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context,R.layout.item_pro,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProduAdapter.ViewHolder viewHolder, final int i) {
        String images = list.get(i).getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(viewHolder.mImage);
        viewHolder.mPrice.setText( list.get(i).getPrice()+"");
        viewHolder.mTitle.setText(list.get(i).getTitle());
        viewHolder.mCheckBox.setChecked(list.get(i).isCheck());
        viewHolder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(i).setCheck(isChecked);
                if (mShopCallBackListener!=null){
                    mShopCallBackListener.callBack();
                }
            }
        });
        viewHolder.scustom.setDAta(this,list,i);
        viewHolder.scustom.setCallbacklistener(new CustomView.Callbacklistener() {
            @Override
            public void callback() {
                if (mShopCallBackListener!=null){
                    mShopCallBackListener.callBack();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle, mPrice;
        ImageView mImage;
        CheckBox mCheckBox;
        CustomView scustom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            scustom=itemView.findViewById(R.id.cus_view);
            mImage = (ImageView) itemView.findViewById(R.id.iv_product);
            mTitle = (TextView) itemView.findViewById(R.id.tv_product_title);
            mPrice = (TextView) itemView.findViewById(R.id.tv_product_price);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.check_product);
        }
    }
    public void seleremover(boolean isSeleAll){
        for (JavaBean.DataBean.ListBean listBean:list){
            listBean.setCheck(isSeleAll);
        }
        notifyDataSetChanged();
    }

    private ShopCallBackListener mShopCallBackListener;

    public void setListener(ShopCallBackListener listener) {
        this.mShopCallBackListener = listener;
    }

    public interface ShopCallBackListener {
        void callBack();
    }
}
