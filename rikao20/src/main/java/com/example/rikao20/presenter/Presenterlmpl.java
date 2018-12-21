package com.example.rikao20.presenter;

import com.example.rikao20.callback.MyCallback;
import com.example.rikao20.model.Modellmpl;
import com.example.rikao20.view.IView;

import java.util.Map;

public class Presenterlmpl implements Presenter{
     private IView iView;
     private Modellmpl modellmpl;

    public Presenterlmpl(IView iView) {
        this.iView = iView;
        modellmpl=new Modellmpl();
    }

    @Override
    public void setRequestData(String path, Map<String, String> mar, Class clazz) {
        modellmpl.setRequest(path, mar, clazz, new MyCallback() {
            @Override
            public void setData(Object data) {
                iView.setData(data);
            }
        });
    }
}
