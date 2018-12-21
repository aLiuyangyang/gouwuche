package com.example.rikao20.model;

import com.example.rikao20.callback.MyCallback;

import java.util.Map;

public interface Model {
    void setRequest(String path, Map<String,String>mar, Class clazz, MyCallback myCallback);
}
