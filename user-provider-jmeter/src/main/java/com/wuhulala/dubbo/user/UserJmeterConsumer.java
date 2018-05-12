package com.wuhulala.dubbo.user;

import com.wuhulala.dubbo.user.dto.req.UserReq;
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
public class UserJmeterConsumer {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath:spring/spring-context.xml"});
        context.start();
        UserService service = context.getBean(UserService.class);
        User user = new User();
        user.setName("wuhulala");
        user.setAge(12);
        user.setPhone("15858123123");
        user.setEmail("xxx@qq.com");
        UserReq userReq = new UserReq();
        userReq.setQuery(user);
        service.saveUser(userReq);
    }
}
