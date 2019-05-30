package com.bawei.moniyuekao0529.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.moniyuekao0529.R;
import com.bawei.moniyuekao0529.bean.JsonBean;

import java.util.List;

/**
 * @Author：边旭东
 * @E-mail： bxd313@vip.qq.com
 * @Date： 2019/5/29 14:17
 * @Description：描述信息
 */
public class MyLeftAdapter extends RecyclerView.Adapter<MyLeftAdapter.MyHolder> {
      private List<JsonBean.ResultBean> leftList;
      private Context context;
      private int mIndex=-1;

    public MyLeftAdapter(List<JsonBean.ResultBean> leftList, Context context) {
        this.leftList = leftList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view=View.inflate(context,R.layout.left_item,null);
       MyHolder myHolder=new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int i) {
       myHolder.textView.setText(leftList.get(i).name);
       myHolder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(itemClick!=null){
                  itemClick.setOnItemClick(i);
               }
           }
       });
       if(i==mIndex){
           myHolder.textView.setTextColor(Color.RED);
       }else{
           myHolder.textView.setTextColor(Color.BLACK);
       }
    }

    @Override
    public int getItemCount() {
        return leftList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
          private TextView textView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView);
        }
    }
    public void setTextColor(int position){
        this.mIndex=position;
        notifyDataSetChanged();
    }

   public interface OnItemClick{
        void setOnItemClick(int position);
   }
   private OnItemClick itemClick;

    public void setItemClick(OnItemClick itemClick) {
        this.itemClick = itemClick;
    }
}
