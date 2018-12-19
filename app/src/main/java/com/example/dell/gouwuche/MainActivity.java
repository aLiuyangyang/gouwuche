package com.example.dell.gouwuche;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.gouwuche.view.IView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IView {

    private MyShoppAdapter adapter;
    private EditText edit_search;
    private ImageView iv_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }
    private void initView() {
        edit_search = (EditText) findViewById(R.id.edit_search);
        iv_search = (ImageView) findViewById(R.id.iv_search);
        iv_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.iv_search:
                  Intent intent1=new Intent(MainActivity.this,ShoppActivity.class);
                  startActivity(intent1);
                  break;
          }
    }

    @Override
    public void setData(Object data) {

    }



    private void submit() {
        // validate
        String search = edit_search.getText().toString().trim();
        if (TextUtils.isEmpty(search)) {
            Toast.makeText(this, "search不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
