package com.git.dubboconsumer.util;

import com.git.inter.RpcInvoker;
import com.git.model.ApiResult;

/**
 * 调用者
 */
public class RpcInvokeStrategy {
    public static ApiResult  startInvoker(ApiResult apiResult) {
        return getRpcInvoker().invoke(apiResult);
    }

    public static RpcInvoker getRpcInvoker(){
        RpcInvoker rpcInvoker = (RpcInvoker) MySpringContext.getBean("rpcInvoker");
        return rpcInvoker;
    }

}
