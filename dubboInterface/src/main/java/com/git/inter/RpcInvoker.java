package com.git.inter;

import com.git.model.ApiResult;

public interface RpcInvoker {
    ApiResult invoke(ApiResult tingContext);

    ApiResult asyncInvoke(ApiResult tingContext);
}