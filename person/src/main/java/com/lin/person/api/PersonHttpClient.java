package com.lin.person.api;


import com.lin.baselib.network.ApiCreater;
import com.lin.baselib.network.BaseHttpClient;
import com.lin.baselib.network.BaseResponse;
import com.lin.person.entity.AddressDTO;
import com.lin.person.entity.CommentDTO;
import com.lin.person.entity.UserDTO;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * Created by lin on 2018/9/18.
 */

public class PersonHttpClient extends BaseHttpClient {
    private static class PersonApiHolder {
        private static final PersonApi personApi = ApiCreater.getInstance().create(PersonApi.class);
    }

    /**
     * 外界调用登录接口
     * @param userId
     * @param file
     * @return
     */

    public static Observable<BaseResponse> changeAvatar(Long userId, MultipartBody.Part file) {

        return PersonApiHolder.personApi.changeAvatar(userId,file);
    }

    public static Observable<BaseResponse> changeInfo(UserDTO userDTO) {

        return PersonApiHolder.personApi.changeInfo(userDTO);
    }


    public static Observable<BaseResponse> getAllAddress( Long userId){
        return PersonApiHolder.personApi.getAllAddress(userId);
    }


    public static Observable<BaseResponse> getAddress( Long userId){
        return PersonApiHolder.personApi.getAddress(userId);
    }


    public static Observable<BaseResponse> modifyAddress( AddressDTO addressDTO){
        return PersonApiHolder.personApi.modifyAddress(addressDTO);
    }


    public static  Observable<BaseResponse> deleteAddress( Long addressId){
        return PersonApiHolder.personApi.deleteAddress(addressId);
    }


    public static Observable<BaseResponse> addAddress( AddressDTO addressDTO){
        return PersonApiHolder.personApi.addAddress(addressDTO);
    }

    public static Observable<BaseResponse> summitFeedback( Long userId,String content){
        return PersonApiHolder.personApi.summitFeedback(userId,content);
    }

    public static Observable<BaseResponse> getPreorders(Long userId){
        return PersonApiHolder.personApi.getPreorders(userId);
    }

    public static Observable<BaseResponse> cancelPreorder(Long preorderId){
        return PersonApiHolder.personApi.cancelPreorder(preorderId);
    }
    public static Observable<BaseResponse> commentPreorder(CommentDTO commentDTO){
        return PersonApiHolder.personApi.commentPreorder(commentDTO);
    }
    public static Observable<BaseResponse> deletePreorder(Long preorderId){
        return PersonApiHolder.personApi.deletePreorder(preorderId);
    }
    public static Observable<BaseResponse> getOrders(Long userId){
        return PersonApiHolder.personApi.getOrders(userId);
    }

    public static Observable<BaseResponse> sureOfGot(Long orderId){
        return PersonApiHolder.personApi.sureOfGot(orderId);
    }

    public static Observable<BaseResponse> deleteOrder(Long orderId){
        return PersonApiHolder.personApi.deleteOrder(orderId);
    }

    public static Observable<BaseResponse> getOrderDetail(Long orderId){
        return PersonApiHolder.personApi.getOrderDetail(orderId);
    }
}
