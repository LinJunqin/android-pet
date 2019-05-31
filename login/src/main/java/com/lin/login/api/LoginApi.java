package com.lin.login.api;

import com.lin.baselib.network.BaseResponse;
import com.lin.login.entity.UserDTO;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lin on 2018/9/18.
 */

public interface LoginApi  {

    @POST("/login/send_code/{phone}")
    Observable<BaseResponse> sendCode(@Path("phone")String phone);

    @POST("/login/login")
    Observable<BaseResponse<UserDTO>> login(@Query("phone") String phone, @Query("code") String code);

    @POST("/login/auto/{phone}")
    Observable<BaseResponse> checkLogin(@Path("phone")String phone);
}
