package com.example.rikao20;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class Main3Activity extends AppCompatActivity {
   private TextView chuan;
   private ImageView imageView;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        chuan=findViewById(R.id.chuan);
        imageView=findViewById(R.id.reweima);
        Intent intent1=getIntent();
        name = intent1.getStringExtra("name");
        creatQRCode();
    }
    private void creatQRCode() {

        QRTask qrtask=new  QRTask(this ,imageView,name);
        qrtask.execute(name);
    }
    static class QRTask extends AsyncTask<String,Void,Bitmap>{
        private WeakReference<Context> mCount;
        private WeakReference<ImageView> mimageView;

       public QRTask(Context context, ImageView imageView, String name) {
             mCount=new WeakReference<>(context);
             mimageView=new WeakReference<>(imageView);
       }

       @Override
        protected Bitmap doInBackground(String... strings) {
           String str = strings[0];
           if (TextUtils.isEmpty(str)) {
               return null;
           }
           int dimensionPixelOffset = mCount.get().getResources().getDimensionPixelOffset(R.dimen.ssd);
           return QRCodeEncoder.syncEncodeQRCode(str,dimensionPixelOffset);

       }

       @Override
       protected void onPostExecute(Bitmap bitmap) {
           super.onPostExecute(bitmap);
           if (bitmap!=null){
               mimageView.get().setImageBitmap(bitmap);
           }
       }
   }
}
