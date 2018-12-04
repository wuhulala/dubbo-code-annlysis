package com.wuhulala.dubbo;

import com.alibaba.dubbo.common.utils.ReflectUtils;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2018/11/22<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class HessionTest {

    public static void main(String[] args) {
        // 字符串长度 + 7个分隔符
        String s = "2.6.1com.wuhulala.dubbo.user.service.MyGenericService0.0.0sayHello"+ ReflectUtils.getDesc(new Class[]{String.class})+"world{}";

        System.out.println(s);
        System.out.println(s.getBytes().length+7);

    }

}
