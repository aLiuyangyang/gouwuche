package com.example.myapplication111.model;


import com.example.myapplication111.callback.MyCallback;

import java.util.Map;

public interface Model {
    void setRequesrtData(String path, Map<String, String> mar, Class clazz, MyCallback myCallback);
}
