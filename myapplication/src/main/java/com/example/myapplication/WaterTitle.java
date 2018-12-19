package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class WaterTitle extends LinearLayout {
    private Button button;
    private EditText editText;
    private Context context;
    public WaterTitle(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public WaterTitle(Context context,AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    private void init() {
        View view=View.inflate(context,R.layout.title,null);
        button=view.findViewById(R.id.but);
        editText=view.findViewById(R.id.edit);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cilck != null) {
                    cilck.itemCilck(editText.getText().toString());
                }
            }
        });
        addView(view);

    }
    private Cilck cilck;

    public void setCilck(Cilck cilck) {
        this.cilck = cilck;
    }

    public interface Cilck{
        void itemCilck(String str);
    }
}
