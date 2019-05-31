package com.lin.goods.presenter;

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
import com.lin.goods.api.GoodsHttpClient;
import com.lin.goods.entity.Address;
import com.lin.goods.entity.GoodsDetailDTO;
import com.lin.goods.view.GoodsOrderView;

import java.lang.reflect.Type;
import java.util.Date;


/**
 * Created by lin on 2019/5/9.
 */

public class GoodsOrderPresenter extends BasePresenter<GoodsOrderView> {
    public GoodsOrderPresenter(GoodsOrderView baseView) {
        super(baseView);
    }

    public void initData(Long userId) {
        addDisposable(GoodsHttpClient.getAddress(userId), new BaseObserver<BaseResponse>(baseView) {
            @Override
            public void onSuccess(BaseResponse response) {
                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                });
                Gson gson = builder.create();
                Address address = gson.fromJson(gson.toJson(response.getData()), new TypeToken<Address>(){}.getType());
                baseView.update(address);
            }
        });
    }
}
