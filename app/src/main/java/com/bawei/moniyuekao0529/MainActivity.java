package com.bawei.moniyuekao0529;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.bawei.moniyuekao0529.fragment.AFragment;
import com.bawei.moniyuekao0529.fragment.BFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager main_pager;
    private RadioGroup main_group;
    private List<Fragment> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        list.add(new AFragment());
        list.add(new BFragment());
        main_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        main_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
               switch (i){
                   case 0:
                       main_group.check(R.id.main_btn1);
                       break;
                   case 1:
                       main_group.check(R.id.main_btn2);
                       break;
               }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        main_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_btn1:
                        main_pager.setCurrentItem(0);
                        break;
                    case R.id.main_btn2:
                        main_pager.setCurrentItem(1);
                        break;
                }
            }
        });
    }

    private void initView() {
        main_pager = findViewById(R.id.main_pager);
        main_group = findViewById(R.id.main_group);
    }

}
