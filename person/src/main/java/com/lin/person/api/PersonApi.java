package com.lin.person.api;

import com.lin.baselib.network.BaseResponse;
import com.lin.person.entity.AddressDTO;
import com.lin.person.entity.CommentDTO;
import com.lin.person.entity.UserDTO;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by lin on 2018/9/18.
 */

public interface PersonApi {

    @POST("/person/avatar")
    @Multipart
    Observable<BaseResponse> changeAvatar(@Query("userId") Long userId,@Part MultipartBody.Part file);


    @POST("/person/info")
    Observable<BaseResponse> changeInfo(@Body UserDTO userDTO);

    @GET("/person/getAllAddress")
    Observable<BaseResponse> getAllAddress(@Query("userId") Long userId);

    @GET("/person/getAddress")
    Observable<BaseResponse> getAddress(@Query("userId") Long userId);

    @POST("/person/modifyAddress")
    Observable<BaseResponse> modifyAddress(@Body AddressDTO addressDTO);

    @POST("/person/deleteAddress")
    Observable<BaseResponse> deleteAddress(@Query("addressId") Long addressId);

    @POST("/person/addAddress")
    Observable<BaseResponse> addAddress(@Body AddressDTO addressDTO);

    @POST("/person/feedback")
    Observable<BaseResponse> summitFeedback(@Query("userId") Long userId,@Query("content") String content);

    @GET("/person/preorders")
    Observable<BaseResponse> getPreorders(@Query("userId")Long userId);

    @PUT("/person/preorder/cancel")
    Observable<BaseResponse> cancelPreorder(@Query("preorderId")Long preorderId);

    @POST("/person/preorder/comment")
    Observable<BaseResponse> commentPreorder(@Body CommentDTO commentDTO);

    @DELETE("/person/preorder")
    Observable<BaseResponse> deletePreorder(@Query("preorderId")Long preorderId);

    @GET("/person/orders")
    Observable<BaseResponse> getOrders(@Query("userId")Long userId);

    @PUT("/person/order/sure")
    Observable<BaseResponse> sureOfGot(@Query("orderId")Long orderId);

    @DELETE("/person/order")
    Observable<BaseResponse> deleteOrder(@Query("orderId")Long orderId);

    @GET("/person/order/detail")
    Observable<BaseResponse> getOrderDetail(@Query("orderId")Long orderId);
}
