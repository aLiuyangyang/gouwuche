package com.example.rikao20.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.rikao20.R;

public class MyTitleView extends LinearLayout {
    private ImageView imageView,image;
    private EditText editText;
    private Context context;


    public MyTitleView(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public MyTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    private void init() {
        View view=View.inflate(context, R.layout.mytitle,null);
        imageView=view.findViewById(R.id.title_image);
        image=view.findViewById(R.id.image);
        editText=view.findViewById(R.id.title_edit);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
              if (titleCilck!=null){
                  titleCilck.title(editText.getText().toString());
              }
            }
        });
        image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemCilck!=null){
                    itemCilck.item();
                }
            }
        });
        addView(view);

    }
    private TitleCilck titleCilck;

    public void setTitleCilck(TitleCilck titleCilck) {
        this.titleCilck = titleCilck;
    }

    public interface TitleCilck{
        void title(String s);
    }
    private ItemCilck itemCilck;

    public void setItemCilck(ItemCilck itemCilck) {
        this.itemCilck = itemCilck;
    }

    public interface  ItemCilck{
        void item();
    }
}
