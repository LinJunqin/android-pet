package com.lin.person.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.lin.baselib.base.BasePresenter;
import com.lin.baselib.network.BaseObserver;
import com.lin.baselib.network.BaseResponse;
import com.lin.person.api.PersonHttpClient;
import com.lin.person.entity.OrderDetailDTO;
import com.lin.person.view.OrderDetailView;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by lin on 2019/5/10.
 */

public class OrderDetailPresenter extends BasePresenter<OrderDetailView> {
    public OrderDetailPresenter(OrderDetailView baseView) {
        super(baseView);
    }

    public void sureOfGot(Long orderId) {
            addDisposable(PersonHttpClient.sureOfGot(orderId), new BaseObserver<BaseResponse>(baseView) {
                @Override
                public void onSuccess(BaseResponse response) {
                    baseView.update();
                }
            });

    }

    public void initData(Long orderId) {
        addDisposable(PersonHttpClient.getOrderDetail(orderId), new BaseObserver<BaseResponse>(baseView) {
            @Override
            public void onSuccess(BaseResponse response) {
                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                });
                Gson gson = builder.create();
                OrderDetailDTO orderDetailDTO = gson.fromJson(gson.toJson(response.getData()), new TypeToken<OrderDetailDTO>(){}.getType());
                 baseView.updateData(orderDetailDTO);
            }
        });
    }
}
