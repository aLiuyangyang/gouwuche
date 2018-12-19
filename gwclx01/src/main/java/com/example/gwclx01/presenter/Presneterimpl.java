package com.example.gwclx01.presenter;

import com.example.gwclx01.callback.MyCallback;
import com.example.gwclx01.model.Modelimpl;
import com.example.gwclx01.view.IView;

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
        modelimpl.setRequestData(path, mar, clazz, new MyCallback() {
            @Override
            public void setData(Object data) {
                iView.setData(data);
            }
        });
    }
    public void setDel(){
        if (iView!=null){
            iView=null;
        }
        if (modelimpl!=null){
            modelimpl=null;
        }
    }
}
