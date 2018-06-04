package com.wuhulala.common.dubbo.handler;

import com.alibaba.dubbo.rpc.RpcException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 功能说明: ${END}<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/5/28<br>
 */
public class InvokerInvocationHandlerInterceptor implements MethodInterceptor {


    ///////////////////////////// 方法区 ////////////////////////////////////

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            return invocation.proceed();
        }catch (RpcException e){
            System.out.println(e.getCode());
        }catch (Exception e){
            throw  e;
        }
        return null;
    }
}
