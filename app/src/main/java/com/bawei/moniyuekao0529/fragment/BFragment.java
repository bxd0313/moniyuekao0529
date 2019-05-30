package com.bawei.moniyuekao0529.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.moniyuekao0529.R;

/**
 * @Author：边旭东
 * @E-mail： bxd313@vip.qq.com
 * @Date： 2019/5/29 10:23
 * @Description：描述信息
 */
public class BFragment extends Fragment {
    private Button change_Color,jing_Xi;
    private WebView webView;
    private TextView textView;
    private String mUrl = "https://abnerming8.github.io/abnerming.html";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.bfragment, container, false);
        change_Color=view.findViewById(R.id.change_color);
        jing_Xi=view.findViewById(R.id.jing_xi);
        webView=view.findViewById(R.id.webView);
        textView=view.findViewById(R.id.textView);
        initData();
         return view;
    }

    private void initData() {
        webView.loadUrl(mUrl);
        //获取设置管理器
        WebSettings settings = webView.getSettings();
        //开启权限可以跟js交互的权限
        settings.setJavaScriptEnabled(true);
        //Alert弹窗需要我们开权限
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //添加你和html5要交互的方法  参数1:是定义两边需要交互的方法  参数2:就是html那边的调用者
        webView.addJavascriptInterface(new Jiang(),"android");
        webView.setWebViewClient(new WebViewClient()/*{
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        }*/);
        webView.setWebChromeClient(new WebChromeClient()/*{
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        }*/);
        change_Color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("javascript:changeColor('#d43c3c')");
                    }
                });
            }
        });
        jing_Xi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("javascript:toast()");
                    }
                });
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radioButton = getActivity().findViewById(R.id.main_btn1);
                radioButton.setChecked(true);
            }
        });
    }


     class Jiang {
        @JavascriptInterface
        public void show(){
            Toast.makeText(getContext(),"边旭东",Toast.LENGTH_SHORT).show();
            /*getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("边旭东");
                }
            });*/
        }

    }
}
