package com.wuhulala.dubbo.user;

import com.wuhulala.dubbo.user.dto.req.UserReq;
import com.wuhulala.dubbo.user.dto.resp.UserResp;
import com.wuhulala.dubbo.user.service.UserService;

/**
 * 功能说明: ${END}<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/5/28<br>
 */
public class UserServiceMock implements UserService{
    public UserServiceMock() {
    }

    ///////////////////////////// 方法区 ////////////////////////////////////

    @Override
    public UserResp saveUser(UserReq userReq) {
        return new UserResp("-1", "调用服务端失败");
    }
}
