package com.wuhulala.dubbo.user;

import com.alibaba.fastjson.JSON;
import com.wuhulala.dubbo.user.dto.req.UserReq;
import com.wuhulala.dubbo.user.dto.resp.UserResp;
import com.wuhulala.dubbo.user.pojo.User;
import com.wuhulala.dubbo.user.service.UserService;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 功能说明: ${END}<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: wuhulala<br>
 * 开发时间: 2018/5/8<br>
 */
public class UserJmeterAction extends AbstractJavaSamplerClient {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[]{"META-INF/spring/spring-context.xml"});

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sr = new SampleResult();
        sr.sampleStart();
        context.start();

        UserResp resp = callCreateUser();
        sr.setResponseData(JSON.toJSONString(resp), null);
        sr.setDataType(SampleResult.TEXT);
        sr.setSuccessful(true);
        sr.sampleEnd();
        return sr;
    }

    private UserResp callCreateUser() {
        UserService service = context.getBean(UserService.class);
        User user = new User();
        user.setName("wuhulala");
        user.setAge(12);
        user.setPhone("15858123123");
        user.setEmail("xxx@qq.com");
        UserReq userReq = new UserReq();
        userReq.setQuery(user);
        return service.saveUser(userReq);
    }
}
