package com.lin.pet.api;



import com.lin.baselib.network.ApiCreater;
import com.lin.baselib.network.BaseHttpClient;
import com.lin.baselib.network.BaseResponse;

import io.reactivex.Observable;

/**
 * Created by lin on 2018/9/18.
 */

public class AppHttpClient extends BaseHttpClient {
    private static class AppApiHolder {
        private static final AppApi appApi = ApiCreater.getInstance().create(AppApi.class);
    }


    public static Observable<BaseResponse> checkLogin(String phone){

        return AppApiHolder.appApi.checkLogin(phone);
    }
}
