package com.bawei.moniyuekao0529.mvp;

import android.content.Context;

/**
 * @Author：边旭东
 * @E-mail： bxd313@vip.qq.com
 * @Date： 2019/5/29 11:22
 * @Description：描述信息
 */
public interface IContract {
    interface IView{
        void getData(String json);
    }
    interface IModel{
        void getData(String url, Context context,Callback callback);
    }

    interface IPresenter{
        void attach(IView view);
        void startRequest(String url,Context context);
        void detach();
    }
    interface Callback {
        void saveData(String json);
    }
}
