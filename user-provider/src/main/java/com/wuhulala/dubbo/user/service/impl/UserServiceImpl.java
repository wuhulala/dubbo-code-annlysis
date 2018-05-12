package com.wuhulala.dubbo.user.service.impl;

import com.wuhulala.dubbo.user.dto.req.UserReq;
import com.wuhulala.dubbo.user.dto.resp.UserResp;
import com.wuhulala.dubbo.user.pojo.User;
import com.wuhulala.dubbo.user.service.UserService;

/**
 * 功能说明: 用户服务提供者<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: wuhulala<br>
 * 开发时间: 2018/5/8<br>
 */

public class UserServiceImpl implements UserService {

    ///////////////////////////// 方法区 ////////////////////////////////////

    @Override
    public UserResp saveUser(UserReq userReq) {
        User newUser = userReq.getQuery();
        System.out.println(newUser);
        UserResp resp = new UserResp();
        resp.setItem(newUser);
        return resp;
    }
}
