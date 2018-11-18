package com.wuhulala.dubbo.user;

import com.alibaba.fastjson.JSON;
import com.wuhulala.dubbo.user.dto.req.UserReq;
import com.wuhulala.dubbo.user.dto.resp.UserResp;
import com.wuhulala.dubbo.user.pojo.User;
import com.wuhulala.dubbo.user.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 功能说明: ${END}<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: wuhulala<br>
 * 开发时间: 2018/5/8<br>
 */
public class UserConsumer {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath:META-INF/spring/spring-context.xml"});
        context.start();
        System.out.println("==============================初始化成功=================================================");
        UserService service = context.getBean(UserService.class);
        System.out.println("==============================reference 初始化成功=================================================");

        User user = new User();
        user.setName("wuhulala");
        user.setAge(12);
        user.setPhone("15858123123");
        user.setEmail("xxx@qq.com");
        UserReq userReq = new UserReq();
        userReq.setQuery(user);
        UserResp resp = service.saveUser(userReq);
        System.out.println(JSON.toJSONString(resp));
        System.in.read();

    }
}
