package com.wuhulala.common.dubbo.filter;

import com.alibaba.dubbo.rpc.*;
import com.wuhulala.dubbo.common.dto.resp.BaseResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CallExceptionFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CallExceptionFilter.class);

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = null;
        Object resp = null;

        try {
            result = invoker.invoke(invocation);
            if (result.hasException() && result.getValue() != null && result instanceof RpcResult) {

            }
        }catch (RpcException e){
            String methodName = invocation.getMethodName();
            Class<?>[] parameterTypes = invocation.getParameterTypes();
            try {
                Method method = invoker.getInterface().getMethod(methodName, parameterTypes);
                Class<?> returnType = method.getReturnType();
                if (returnType != null && returnType.isAssignableFrom(BaseResp.class)) {
                    Constructor constructor = returnType.getDeclaredConstructor();
                    if (constructor != null) {
                        resp = constructor.newInstance();
                        ((BaseResp) resp).setResultInfo("-1", e.getMessage());
                        ((BaseResp) resp).setCause(result.getException().toString());
                    }
                }
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException oe) {
                logger.error("dubbo服务返回值包装异常！", oe);
            }
            if(resp != null){
                ((RpcResult) result).setValue(resp);
            }
            logger.error("asasdasd",e);
        }
        // after filter ...


        return result;
    }
}