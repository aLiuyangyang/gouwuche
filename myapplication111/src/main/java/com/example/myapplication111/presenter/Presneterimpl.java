package com.example.myapplication111.presenter;


import com.example.myapplication111.MainActivity;
import com.example.myapplication111.callback.MyCallback;
import com.example.myapplication111.model.Modelimpl;
import com.example.myapplication111.view.IView;

import java.util.Map;

public class Presneterimpl implements Presenter{
    private IView iView;
    private Modelimpl modelimpl;

    public Presneterimpl(MainActivity iView) {
        this.iView = iView;
        modelimpl=new Modelimpl();
    }

    @Override
    public void setRequestData(String path, Map<String, String> mar, Class clazz) {
        modelimpl.setRequesrtData(path, mar, clazz, new MyCallback() {
            @Override
            public void setData(Object data) {
                iView.setData(data);
            }
        });
    }
}
