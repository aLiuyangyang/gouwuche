package com.example.dell.gouwuche.model;

import com.example.dell.gouwuche.callback.MyCallback;
import com.example.dell.gouwuche.util.ICallback;
import com.example.dell.gouwuche.util.OkHttpUtils;

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
