package com.wuhulala.dubbo.myspi;

import com.alibaba.dubbo.common.URL;

/**
 * 功能说明: ${END}<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: wuhulala<br>
 * 开发时间: 2018/5/27<br>
 */
public class SimplExtWrapper implements SimpleExt{

    private SimpleExt instance;

    public SimplExtWrapper(SimpleExt instance) {
        this.instance = instance;
    }

    ///////////////////////////// 方法区 ////////////////////////////////////


    @Override
    public String echo(URL url, String s) {
        return "Wrapper-" + instance.echo(url, s);
    }

    @Override
    public String yell(URL url, String s) {
        return "Wrapper-" + instance.yell(url, s);
    }

    @Override
    public String bang(URL url, String s) {
        return "Wrapper-" + instance.bang(url, s);
    }
}
