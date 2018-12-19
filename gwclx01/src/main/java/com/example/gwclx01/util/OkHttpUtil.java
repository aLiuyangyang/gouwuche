package com.example.gwclx01.util;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtil {
    private Handler handler=new Handler(Looper.getMainLooper());
    private OkHttpClient mClient;
    private static volatile OkHttpUtil mIntstance;
    public static OkHttpUtil getmIntstance(){
        if (mIntstance==null){
            synchronized (OkHttpUtil.class){
                mIntstance=new OkHttpUtil();
            }
        }
        return mIntstance;
    }

    public OkHttpUtil(){
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        mClient=new OkHttpClient.Builder()
                .writeTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }
    public void setPost(String path, Map<String,String>mar, final Class clazz, final ICallback iCallback){
        FormBody.Builder builder=new FormBody.Builder();
        for (Map.Entry<String,String> entry:mar.entrySet()) {
            builder.add(entry.getKey(),entry.getValue());
        }

        RequestBody body=builder.build();
        Request request=new Request.Builder()
                .post(body)
                .url(path)
                .build();

        Call call=mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                      iCallback.setfill(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson=new Gson();
                final Object o = gson.fromJson(json, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                     iCallback.setSuccess(o);
                    }
                });
            }
        });
    }
}
