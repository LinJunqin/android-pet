package com.lin.store.api;


import com.lin.baselib.network.ApiCreater;
import com.lin.baselib.network.BaseHttpClient;
import com.lin.baselib.network.BaseResponse;
import com.lin.store.entity.ConditionDTO;
import com.lin.store.entity.PreorderDTO;

import io.reactivex.Observable;

/**
 * Created by lin on 2018/9/18.
 */

public class StoreHttpClient extends BaseHttpClient {
    private static class StoreApiHolder {
        private static final StoreApi storeApi = ApiCreater.getInstance().create(StoreApi.class);
    }

    public static Observable<BaseResponse> getHospitalsOfCondition( ConditionDTO conditionDTO){
        return StoreApiHolder.storeApi.getHospitalsOfCondition(conditionDTO);
    }

    public static Observable<BaseResponse> getHospitalDetail(Long hospitalId){
        return StoreApiHolder.storeApi.getHospitalDetail(hospitalId);
    }

    public static Observable<BaseResponse> generatePreorder(PreorderDTO preorderDTO){
        return StoreApiHolder.storeApi.generatePreorder(preorderDTO);
    }

    public static Observable<BaseResponse> getHospitalOfSearch(String key){
        return StoreApiHolder.storeApi.getHospitalOfSearch(key);
    }

}
