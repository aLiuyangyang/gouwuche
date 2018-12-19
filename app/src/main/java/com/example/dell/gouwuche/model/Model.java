package com.example.dell.gouwuche.model;

import com.example.dell.gouwuche.callback.MyCallback;

import java.util.Map;

public interface Model {
    void setRequesrtData(String path, Map<String,String> mar, Class clazz, MyCallback myCallback);
}
