package com.lin.store.api;

import com.lin.baselib.network.BaseResponse;
import com.lin.store.entity.ConditionDTO;
import com.lin.store.entity.PreorderDTO;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lin on 2018/9/18.
 */

public interface StoreApi {


    @POST("/hospital/list/condition")
    Observable<BaseResponse> getHospitalsOfCondition(@Body ConditionDTO conditionDTO);

    @POST("/hospital/detail")
    Observable<BaseResponse> getHospitalDetail(@Query("hospitalId") Long hospitalId);

    @POST("/hospital/preorder")
    Observable<BaseResponse> generatePreorder(@Body PreorderDTO preorderDTO);

    @POST("/hospital/search")
    Observable<BaseResponse> getHospitalOfSearch(@Query("key") String key);

}
