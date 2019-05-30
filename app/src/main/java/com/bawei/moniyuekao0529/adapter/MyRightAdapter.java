package com.bawei.moniyuekao0529.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.moniyuekao0529.R;
import com.bawei.moniyuekao0529.bean.JsonBean;

import java.util.List;

/**
 * @Author：边旭东
 * @E-mail： bxd313@vip.qq.com
 * @Date： 2019/5/29 16:55
 * @Description：描述信息
 */
public class MyRightAdapter extends RecyclerView.Adapter<MyRightAdapter.MyHolder> {
     private List<JsonBean.ResultBean> rightList;
     private Context context;
    public MyRightAdapter(List<JsonBean.ResultBean> rightList, Context context) {
        this.rightList = rightList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View  view=View.inflate(context, R.layout.right_item,null);
        MyHolder myHolder=new MyHolder(view);
       return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
          myHolder.textView.setText(rightList.get(i).name);
          myHolder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(itemClick!=null){
                      itemClick.setOnItemClick(i);
                  }
              }
          });
    }
    @Override
    public int getItemCount() {
        return rightList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
         private TextView textView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView);
        }
    }
    public interface OnItemClick{
        void setOnItemClick(int position);
    }
    private OnItemClick itemClick;

    public void setItemClick(OnItemClick itemClick){
        this.itemClick=itemClick;
    }
}
