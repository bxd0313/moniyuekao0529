package com.bawei.moniyuekao0529.mvp;

import android.content.Context;

import com.bawei.moniyuekao0529.util.VolleyUtil;

/**
 * @Author：边旭东
 * @E-mail： bxd313@vip.qq.com
 * @Date： 2019/5/29 14:04
 * @Description：描述信息
 */
public class Model implements IContract.IModel {

    @Override
    public void getData(String url, Context context, final IContract.Callback callback) {
        VolleyUtil.getInstance().getUrl(url, context, new VolleyUtil.CallBack() {
            @Override
            public void setRequest(String jsonStr) {
                callback.saveData(jsonStr);
            }
        });
    }
}
