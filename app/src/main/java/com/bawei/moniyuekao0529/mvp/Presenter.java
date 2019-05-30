package com.bawei.moniyuekao0529.mvp;

import android.content.Context;

import java.lang.ref.SoftReference;

/**
 * @Author：边旭东
 * @E-mail： bxd313@vip.qq.com
 * @Date： 2019/5/29 14:05
 * @Description：描述信息
 */
public class Presenter implements IContract.IPresenter {
      private Model model;
      private IContract.IView view;
      private SoftReference<IContract.IView> soft;
    @Override
    public void attach(IContract.IView view) {
        this.view=view;
        model=new Model();
        soft= new SoftReference<>(view);
    }

    @Override
    public void startRequest(String url, Context context) {
        model.getData(url, context, new IContract.Callback() {
            @Override
            public void saveData(String json) {
                view.getData(json);
            }
        });
    }


    @Override
    public void detach() {
        if(soft!=null){
            soft.clear();
        }
        if(model!=null){
            model=null;
        }
    }
}
