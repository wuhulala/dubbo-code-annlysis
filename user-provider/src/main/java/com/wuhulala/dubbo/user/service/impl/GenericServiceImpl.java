package com.wuhulala.dubbo.user.service.impl;

import com.wuhulala.dubbo.user.service.MyGenericService;

/**
 * 功能
 *
 * @author xueah20964 2018/11/18 Create 1.0  <br>
 * @version 1.0
 */
public class GenericServiceImpl implements MyGenericService {

    @Override
    public String sayHello(String name) {
        return "hello " + name + " !";
    }
}
