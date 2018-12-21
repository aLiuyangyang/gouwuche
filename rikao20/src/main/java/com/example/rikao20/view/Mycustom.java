package com.example.rikao20.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.rikao20.R;
import com.example.rikao20.adapter.ZiApapter;
import com.example.rikao20.bean.PerBean;

import java.util.ArrayList;
import java.util.List;

public class Mycustom extends RelativeLayout implements View.OnClickListener {
    private EditText editText;
    private Button jia,jian;
    private Context context;
    private List<PerBean.DataBean.ListBean> listBeans=new ArrayList<>();
    private int position;
    private ZiApapter ziApapter;
    public Mycustom(Context context) {
        super(context);
        init(context);
    }

    public Mycustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Mycustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context=context;
        View view=View.inflate(context, R.layout.mycus,null);
        editText=view.findViewById(R.id.edit_num);
        jia=view.findViewById(R.id.jia);
        jian=view.findViewById(R.id.jian);
        jia.setOnClickListener(this);
        jian.setOnClickListener(this);
        addView(view);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText.getText().toString()==null|editText.getText().equals("")){
                    Log.i("TAG","PPP");
                    return;
                }
                String string = editText.getText().toString();
                listBeans.get(position).setNum(Integer.parseInt(string));
                if (myCusCilck!=null){
                    myCusCilck.mMyCusCilck();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
  private int num;
    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.jia:
                num++;
                editText.setText(num+"");
                listBeans.get(position).setNum(num);
                myCusCilck.mMyCusCilck();
                ziApapter.notifyItemChanged(position);
              break;
          case R.id.jian:
              if (num>1){
                  num--;
              }else {
                  Toast.makeText(context,"数量不能小于1",Toast.LENGTH_SHORT).show();
              }
              editText.setText(num+"");
              listBeans.get(position).setNum(num);
              myCusCilck.mMyCusCilck();
              ziApapter.notifyItemChanged(position);
              break;
              default:break;
      }
    }
    public void setMyData(ZiApapter ziApapter, List<PerBean.DataBean.ListBean> listBeans, int i){
        this.listBeans=listBeans;
        this.ziApapter=ziApapter;
        position=i;
        num = listBeans.get(i).getNum();
        editText.setText(num+"");
    }
    private MyCusCilck myCusCilck;

    public void setMyCusCilck(MyCusCilck myCusCilck) {
        this.myCusCilck = myCusCilck;
    }

    public interface MyCusCilck{
        void mMyCusCilck();
    }

}
