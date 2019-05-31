package com.lin.goods.api;

import com.lin.baselib.network.BaseResponse;
import com.lin.goods.entity.OrderDTO;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lin on 2018/9/18.
 */

public interface GoodsApi {




    @GET("/goods/list")
    Observable<BaseResponse> getGoods();

    @POST("/goods/goods/detail")
    Observable<BaseResponse> getGoodsDetail(@Query("goodsId") Long goodsId);

    @GET("/person/getAddress")
    Observable<BaseResponse> getAddress(@Query("userId") Long userId);

    @POST("/goods/order")
    Observable<BaseResponse> generateOrder(@Body OrderDTO orderDTO);

    @POST("/goods/search")
    Observable<BaseResponse> getGoodsOfSearch(@Query("key") String key);

}
