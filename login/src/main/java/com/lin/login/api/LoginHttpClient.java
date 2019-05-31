package com.lin.login.api;



import com.lin.baselib.network.ApiCreater;
import com.lin.baselib.network.BaseHttpClient;
import com.lin.baselib.network.BaseResponse;
import com.lin.login.entity.UserDTO;

import io.reactivex.Observable;

/**
 * Created by lin on 2018/9/18.
 */

public class LoginHttpClient extends BaseHttpClient {
    private static class LoginApiHolder {
        private static final LoginApi loginApi = ApiCreater.getInstance().create(LoginApi.class);
    }

    /**
     * 外界调用登录接口
     * @param phone
     * @param code
     * @return
     */
    public static Observable<BaseResponse<UserDTO>> login(String phone, String code){

        return LoginApiHolder.loginApi.login(phone,code);
    }

    public static Observable<BaseResponse> sendCode(String phone){

        return LoginApiHolder.loginApi.sendCode(phone);
    }
}
