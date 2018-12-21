package com.example.rikao20.mysqilt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import com.example.rikao20.bean.PuBean;

import java.util.ArrayList;
import java.util.List;

public class Dao {
    private MySqilt mySqilt;
    private SQLiteDatabase database;
    public Dao(Context context){
        mySqilt=new MySqilt(context);
        database=mySqilt.getReadableDatabase();
    }

    public void add(String name){
        ContentValues values=new ContentValues();
        values.put("name",name);
        database.insert("user",null,values);
    }
    public List<PuBean> select(){
        List<PuBean> list=new ArrayList<>();
        Cursor query = database.query("user", null, null, null, null, null, null);
        while (query.moveToNext()){
            String name = query.getString(query.getColumnIndex("name"));
            PuBean bean=new PuBean(name);
            list.add(bean);
        }
        return list;
    }

}
