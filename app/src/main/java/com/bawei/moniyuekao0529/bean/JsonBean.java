package com.bawei.moniyuekao0529.bean;

import java.util.List;

/**
 * @Author：边旭东
 * @E-mail： bxd313@vip.qq.com
 * @Date： 2019/5/29 14:12
 * @Description：描述信息
 */
public class JsonBean {
    public List<ResultBean> result;

    public static class ResultBean {
        public String id;
        public String name;

    }
}
