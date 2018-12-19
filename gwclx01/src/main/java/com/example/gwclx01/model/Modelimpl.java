package com.example.gwclx01.model;

import com.example.gwclx01.callback.MyCallback;
import com.example.gwclx01.util.ICallback;
import com.example.gwclx01.util.OkHttpUtil;

import java.util.Map;

public class Modelimpl implements Model{
    @Override
    public void setRequestData(String path, final Map<String, String> mar, Class clazz, final MyCallback myCallback) {
        OkHttpUtil.getmIntstance().setPost(path, mar, clazz, new ICallback() {
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
