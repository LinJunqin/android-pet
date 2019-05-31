package com.lin.pet.api;

import com.lin.baselib.network.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by lin on 2018/9/18.
 */

public interface AppApi {

    @POST("/login/auto/{phone}")
    Observable<BaseResponse> checkLogin(@Path("phone") String phone);
}
