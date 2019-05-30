package com.bawei.moniyuekao0529.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawei.moniyuekao0529.R;
import com.bawei.moniyuekao0529.adapter.MyLeftAdapter;
import com.bawei.moniyuekao0529.adapter.MyRightAdapter;
import com.bawei.moniyuekao0529.bean.JsonBean;
import com.bawei.moniyuekao0529.mvp.IContract;
import com.bawei.moniyuekao0529.mvp.Presenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：边旭东
 * @E-mail： bxd313@vip.qq.com
 * @Date： 2019/5/29 10:23
 * @Description：描述信息
 */
public class AFragment extends Fragment implements IContract.IView {
    private String url1="http://172.17.8.100/small/commodity/v1/findFirstCategory";
    private String url2="http://172.17.8.100/small/commodity/v1/findSecondCategory?firstCategoryId=";
    private RecyclerView left_Recycle,right_Recycle;
    private List<JsonBean.ResultBean> lList=new ArrayList<>();
    private List<JsonBean.ResultBean> rList=new ArrayList<>();
    private Presenter presenter;
    private MyLeftAdapter myLeftAdapter;
    private MyRightAdapter myRightAdapter;
    private Boolean bl=true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.afragment, container, false);

        left_Recycle = view.findViewById(R.id.left_Recycle);
        right_Recycle = view.findViewById(R.id.right_Recycle);
        //左边线性布局
        LinearLayoutManager left_manager=new LinearLayoutManager(getActivity());
        left_Recycle.setLayoutManager(left_manager);
        //右边网格布局
        GridLayoutManager right_manager=new GridLayoutManager(getActivity(),3);
        right_Recycle.setLayoutManager(right_manager);
        initData();
        initListener();
        return view;

    }

    private void initData() {
        myLeftAdapter=new MyLeftAdapter(lList,getActivity());
        left_Recycle.setAdapter(myLeftAdapter);
        myRightAdapter=new MyRightAdapter(rList,getActivity());
        right_Recycle.setAdapter(myRightAdapter);
        presenter=new Presenter();
        presenter.attach(this);
        presenter.startRequest(url1,getActivity());
    }

    private void initListener() {
        myLeftAdapter.setItemClick(new MyLeftAdapter.OnItemClick() {
            @Override
            public void setOnItemClick(int position) {
                bl=false;
                myLeftAdapter.setTextColor(position);
                String id = lList.get(position).id;
                presenter.startRequest(url2+id,getContext());
            }
        });
        myRightAdapter.setItemClick(new MyRightAdapter.OnItemClick() {
            @Override
            public void setOnItemClick(int position) {
                //如何在Fragment里面操作Activity
               RadioButton radioButton=getActivity().findViewById(R.id.main_btn2);
               radioButton.setChecked(true);
            }
        });
    }

    @Override
    public void getData(String json) {
        if(bl){
            lList.clear();
            Gson gson=new Gson();
            JsonBean jsonBean = gson.fromJson(json, JsonBean.class);
            lList.addAll(jsonBean.result);
            myLeftAdapter.notifyDataSetChanged();
        }else{
            rList.clear();
            Gson gson=new Gson();
            JsonBean jsonBean = gson.fromJson(json, JsonBean.class);
             rList.addAll(jsonBean.result);
             myRightAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
