package com.wuhulala.dubbo.user.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 功能说明: 用户<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: wuhulala<br>
 * 开发时间: 2018/5/8<br>
 */
@ToString
public class User implements Serializable{

    @Getter @Setter
    private String name;

    @Getter @Setter
    private int age;

    @Getter @Setter
    private String phone;

    @Getter @Setter
    private String email;


    ///////////////////////////// 方法区 ////////////////////////////////////
}
