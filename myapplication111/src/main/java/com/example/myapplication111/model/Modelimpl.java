package com.example.myapplication111.model;

import com.example.myapplication111.callback.MyCallback;
import com.example.myapplication111.util.ICallback;
import com.example.myapplication111.util.OkHttpUtils;

import java.util.Map;

public class Modelimpl implements Model{
    @Override
    public void setRequesrtData(String path, final Map<String, String> mar, Class clazz, final MyCallback myCallback) {
        OkHttpUtils.getInstance().setPostEnqueue(path, mar, clazz, new ICallback() {
            @Override
            public void setSuccess(Object obj) {
                myCallback.setData(obj);
            }

            @Override
            public void setFille(Exception ex) {
                myCallback.setData(ex);
            }
        });
    }
}
