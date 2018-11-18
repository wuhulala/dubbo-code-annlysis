package com.wuhulala.dubbo.myspi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Protocol;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.instanceOf;

/**
 * 功能说明: ${END}<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: wuhulala<br>
 * 开发时间: 2018/5/27<br>
 */
public class ExtensionLoaderTest {


    ///////////////////////////// 方法区 ////////////////////////////////////
    @Test
    public void testGetDefaultExtension() {
        ExtensionLoader<SimpleExt> loader = ExtensionLoader.getExtensionLoader(SimpleExt.class);
        SimpleExt ext = loader.getDefaultExtension();
        loader.getExtension("");
        Assert.assertThat(ext, instanceOf(SimplExtWrapper.class));
        ext.echo(new URL("dubbo", "host", 20080), "sssss");
        //String name = ExtensionLoader.getExtensionLoader(SimpleExt.class).getDefaultExtensionName();
        //Assert.assertEquals("ext1", name);
    }

    @Test
    public void testAdaptiveOfEcho(){
        ExtensionLoader<SimpleExt> loader = ExtensionLoader.getExtensionLoader(SimpleExt.class);
        SimpleExt ext = loader.getAdaptiveExtension();
        Map<String, String> map = new HashMap<>();
        map.put("simple.ext", "ext2");
        URL url = new URL("dubbo", "host", 20080, map);
        System.out.println(url.getParameter("simple.ext", "ext1"));
        String s = ext.echo(url, "asdasda");
        System.out.println(s);
    }

    @Test
    public void testAdaptiveOfYell(){
        ExtensionLoader<SimpleExt> loader = ExtensionLoader.getExtensionLoader(SimpleExt.class);
        SimpleExt ext = loader.getAdaptiveExtension();
        URL url = new URL("dubbo", "host", 20080);

        // 这是个坑
        url = url.addParameter("key1", "ext2");
        String extName = url.getParameter("key1", url.getParameter("key2", "ext1"));

        System.out.println(extName);
        String s = ext.yell(url, "asdasda");
        System.out.println(s);
    }

    @Test
    public void testProtocolAdaptive(){
        ExtensionLoader<Protocol> protocolExtensionLoader = ExtensionLoader.getExtensionLoader(Protocol.class);
        protocolExtensionLoader.getAdaptiveExtension();
    }
}
