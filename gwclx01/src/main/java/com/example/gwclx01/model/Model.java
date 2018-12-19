package com.example.gwclx01.model;

import com.example.gwclx01.callback.MyCallback;

import java.util.Map;

public interface Model {
    void setRequestData(String path, Map<String,String> mar, Class clazz, MyCallback myCallback);
}
