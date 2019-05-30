package com.bawei.moniyuekao0529.util;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * @Author：边旭东
 * @E-mail： bxd313@vip.qq.com
 * @Date： 2019/5/29 11:03
 * @Description：描述信息
 */
public class VolleyUtil {
    private static final VolleyUtil ourInstance = new VolleyUtil();

    public static VolleyUtil getInstance() {
        return ourInstance;
    }

    private VolleyUtil() {
    }

    //GET
    public void getUrl(String url, Context context, final CallBack callBack){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.setRequest(response);
            }
        },null);
        requestQueue.add(stringRequest);
    }
    //POST
    public void postUrl(String url, Context context, final Map<String,String> map, final CallBack callBack){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.setRequest(response);
            }
        },null){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
    public interface CallBack {
         void setRequest(String jsonStr);
    }
}
