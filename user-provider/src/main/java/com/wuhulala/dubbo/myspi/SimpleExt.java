package com.wuhulala.dubbo.myspi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

/**
 * 功能说明: ${END}<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: wuhulala<br>
 * 开发时间: 2018/5/27<br>
 */
@SPI("ext1")
public interface SimpleExt {


    ///////////////////////////// 方法区 ////////////////////////////////////

    @Adaptive
    String echo(URL url, String s);

    @Adaptive({"key1", "key2"})
    String yell(URL url, String s);

    /**
     * 无Adaptive注解
     * @param url
     * @param s
     * @return
     */
    String bang(URL url, String s);
}
