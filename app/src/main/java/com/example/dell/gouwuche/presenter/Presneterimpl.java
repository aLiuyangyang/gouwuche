package com.example.dell.gouwuche.presenter;

import com.example.dell.gouwuche.callback.MyCallback;
import com.example.dell.gouwuche.model.Modelimpl;
import com.example.dell.gouwuche.view.IView;

import java.util.Map;

public class Presneterimpl implements Presenter{
    private IView iView;
    private Modelimpl modelimpl;

    public Presneterimpl(IView iView) {
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
