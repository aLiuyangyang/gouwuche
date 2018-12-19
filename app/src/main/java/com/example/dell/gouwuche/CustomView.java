package com.example.dell.gouwuche;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dell.gouwuche.bean.JavaBean;

import java.util.ArrayList;
import java.util.List;

public class CustomView extends RelativeLayout implements View.OnClickListener {
    private Context context;
    private EditText editText;
    private List<JavaBean.DataBean.ListBean> listBeans=new ArrayList<>();
    private int position;
    private ProduAdapter produAdapter;

    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        this.context=context;
        View view=View.inflate(context,R.layout.show_car_price,null);
        Button jia=view.findViewById(R.id.jia);
        Button jian=view.findViewById(R.id.jian);
        editText = view.findViewById(R.id.edit);
        jia.setOnClickListener(this);
        jian.setOnClickListener(this);
        addView(view);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
     private  int num;
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.jia:
               num++;
               editText.setText(num+"");
                listBeans.get(position).setNum(num);
                callbacklistener.callback();
                produAdapter.notifyItemChanged(position);
               break;
           case R.id.jian:
               if (num>1){
                   num--;
               }else {
                   tosat("数量不能小于1");
               }
               editText.setText(num+"");
               listBeans.get(position).setNum(num);
               callbacklistener.callback();
               produAdapter.notifyItemChanged(position);
               break;
               default:break;
       }

    }
    private void tosat(String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }

    public void setDAta(ProduAdapter produAdapter,List<JavaBean.DataBean.ListBean> list,int i){
        listBeans=list;
        this.produAdapter=produAdapter;
        position=i;
        num=list.get(i).getNum();
        editText.setText(num+"");
    }
    private Callbacklistener callbacklistener;
    public void setCallbacklistener(Callbacklistener callbacklistener){
        this.callbacklistener=callbacklistener;
    }
    public interface Callbacklistener{
        void callback();
    }
}
