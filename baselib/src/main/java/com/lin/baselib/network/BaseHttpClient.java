package com.lin.baselib.network;

/**
 * Created by lin on 2018/9/18.
 */

public class BaseHttpClient {
    // 公共api
    protected static class BaseApiHolder {
        public static final BaseApi baseApi = ApiCreater.getInstance().create(BaseApi.class);
    }
}
