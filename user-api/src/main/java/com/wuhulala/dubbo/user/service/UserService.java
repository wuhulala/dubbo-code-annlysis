package com.wuhulala.dubbo.user.service;

import com.wuhulala.dubbo.user.dto.req.UserReq;
import com.wuhulala.dubbo.user.dto.resp.UserResp;

/**
 * 功能说明: ${END}<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: wuhulala<br>
 * 开发时间: 2018/5/8<br>
 */
public interface UserService {

    ///////////////////////////// 方法区 ////////////////////////////////////

    /**
     * 保存用户
     *
     * @param userReq 用户请求
     * @return 保存结果
     */
    UserResp saveUser(UserReq userReq);
}
