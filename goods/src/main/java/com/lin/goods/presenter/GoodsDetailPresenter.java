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
import com.lin.goods.entity.GoodsDetailDTO;
import com.lin.goods.view.GoodsDetailView;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by lin on 2019/5/10.
 */

public class GoodsDetailPresenter extends BasePresenter<GoodsDetailView> {
    public GoodsDetailPresenter(GoodsDetailView baseView) {
        super(baseView);
    }

    public void initData(Long goodsId) {
       addDisposable(GoodsHttpClient.getGoodsDetail(goodsId), new BaseObserver<BaseResponse>(baseView) {
           @Override
           public void onSuccess(BaseResponse response) {
               GsonBuilder builder = new GsonBuilder();
               builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                   public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                       return new Date(json.getAsJsonPrimitive().getAsLong());
                   }
               });
               Gson gson = builder.create();
               GoodsDetailDTO goodsDetailDTO = gson.fromJson(gson.toJson(response.getData()), new TypeToken<GoodsDetailDTO>(){}.getType());
               baseView.update(goodsDetailDTO);
           }
       });
    }
}
