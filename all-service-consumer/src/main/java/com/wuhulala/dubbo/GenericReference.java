package com.wuhulala.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;

/**
 * 功能
 *
 * @author xueah20964 2018/11/18 Create 1.0  <br>
 * @version 1.0
 */
public class GenericReference {

    public static void main(String[] args) throws InterruptedException {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("generic-reference-app");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper:2181");
        registry.setProtocol("zookeeper");
        registry.setClient("zkclient");

        // 引用远程服务
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        reference.setInterface("com.wuhulala.dubbo.user.service.MyGenericService");
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setCheck(false);
        //reference.setVersion("1.0.0");
        reference.setRetries(0);
        reference.setGeneric(true);
        reference.setTimeout(1000000);

        GenericService genericService = reference.get();

        Object result = genericService.$invoke("sayHello", new String[]{"java.lang.String"}, new Object[]{"world"});
        System.out.println("==========================");
        System.out.println(result);
        System.out.println("==========================");
        Thread.sleep(100000);
    }
}
