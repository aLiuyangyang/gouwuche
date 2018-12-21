package com.example.rikao20.model;

import com.example.rikao20.callback.MyCallback;
import com.example.rikao20.okutil.ICallback;
import com.example.rikao20.okutil.OkHttpUtils;

import java.util.Map;

public class Modellmpl implements Model{
    @Override
    public void setRequest(String path, final Map<String, String> mar, Class clazz, final MyCallback myCallback) {
        OkHttpUtils.getmInstance().setPost(path, mar, clazz, new ICallback() {
            @Override
            public void setSuccess(Object obj) {
                myCallback.setData(obj);
            }

            @Override
            public void setfill(Exception ex) {
                 myCallback.setData(ex);
            }
        });
    }
}
