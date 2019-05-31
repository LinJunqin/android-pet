package com.lin.person.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.lin.baselib.base.BasePresenter;
import com.lin.baselib.entity.User;
import com.lin.baselib.network.BaseObserver;
import com.lin.baselib.network.BaseResponse;
import com.lin.person.api.PersonHttpClient;
import com.lin.person.entity.Order;
import com.lin.person.view.OrderView;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * Created by lin on 2019/5/10.
 */

public class OrderPresenter extends BasePresenter<OrderView> {
    public OrderPresenter(OrderView baseView) {
        super(baseView);
    }

    public void initData() {
     addDisposable(PersonHttpClient.getOrders(User.getUser().getUserId()), new BaseObserver<BaseResponse>(baseView) {
         @Override
         public void onSuccess(BaseResponse response) {
             GsonBuilder builder = new GsonBuilder();
             builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                 public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                     return new Date(json.getAsJsonPrimitive().getAsLong());
                 }
             });
             Gson gson = builder.create();
             List<Order> orderList = gson.fromJson(gson.toJson(response.getData()), new TypeToken<List<Order>>(){}.getType());
             baseView.updateList(orderList);
         }
     });

    }

    public void sureOfGot(Long orderId) {
       addDisposable(PersonHttpClient.sureOfGot(orderId), new BaseObserver<BaseResponse>(baseView) {
           @Override
           public void onSuccess(BaseResponse response) {
               baseView.updateList();
           }
       });
    }

    public void delete(Long orderId, final int position) {
           addDisposable(PersonHttpClient.deleteOrder(orderId), new BaseObserver<BaseResponse>(baseView) {
               @Override
               public void onSuccess(BaseResponse response) {
                   baseView.updateList(position);
               }
           });
    }
}
