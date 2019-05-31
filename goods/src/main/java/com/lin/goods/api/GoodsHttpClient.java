package com.lin.goods.api;


import com.lin.baselib.network.ApiCreater;
import com.lin.baselib.network.BaseHttpClient;
import com.lin.baselib.network.BaseResponse;
import com.lin.goods.entity.OrderDTO;

import io.reactivex.Observable;

/**
 * Created by lin on 2018/9/18.
 */

public class GoodsHttpClient extends BaseHttpClient {
    private static class GoodsApiHolder {
        private static final GoodsApi goodsApi = ApiCreater.getInstance().create(GoodsApi.class);
    }


    public static Observable<BaseResponse> getGoods(){
        return GoodsApiHolder.goodsApi.getGoods();
    }
    public static Observable<BaseResponse> getGoodsDetail(Long goodsId){
        return GoodsApiHolder.goodsApi.getGoodsDetail(goodsId);
    }
    public static Observable<BaseResponse> getAddress(Long userId){
        return GoodsApiHolder.goodsApi.getAddress(userId);
    }
    public static Observable<BaseResponse> generateOrder(OrderDTO orderDTO){
        return GoodsApiHolder.goodsApi.generateOrder(orderDTO);
    }

    public static Observable<BaseResponse> getGoodsOfSearch(String key){
        return GoodsApiHolder.goodsApi.getGoodsOfSearch(key);
    }
}
