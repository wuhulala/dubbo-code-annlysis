package com.wuhulala.dubbo.user.dto.resp;

import com.wuhulala.dubbo.common.dto.resp.BaseResp;
import com.wuhulala.dubbo.user.pojo.User;

/**
 * 功能说明: ${END}<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: wuhulala<br>
 * 开发时间: 2018/5/8<br>
 */
public class UserResp extends BaseResp<User> {
    public UserResp() {
    }

    public UserResp(String resultCode, String resultMsg) {
        super(resultCode, resultMsg);
    }

///////////////////////////// 方法区 ////////////////////////////////////
}
