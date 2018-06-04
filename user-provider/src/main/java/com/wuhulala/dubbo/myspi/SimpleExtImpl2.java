package com.wuhulala.dubbo.myspi;

import com.alibaba.dubbo.common.URL;

/**
 * 功能说明: ${END}<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: wuhulala<br>
 * 开发时间: 2018/5/27<br>
 */
public class SimpleExtImpl2 implements SimpleExt {


    ///////////////////////////// 方法区 ////////////////////////////////////


    @Override
    public String echo(URL url, String s) {
        return "impl1-echo2";
    }

    @Override
    public String yell(URL url, String s) {
        return "impl1-yell2";
    }

    @Override
    public String bang(URL url, String s) {
        return "impl-bang2";
    }
}
