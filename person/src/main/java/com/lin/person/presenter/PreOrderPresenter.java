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
import com.lin.person.entity.PreOrder;
import com.lin.person.view.PreOrderView;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * Created by lin on 2019/5/10.
 */

public class PreOrderPresenter extends BasePresenter<PreOrderView> {
    public PreOrderPresenter(PreOrderView baseView) {
        super(baseView);
    }

    public void initData(Long userId, final List<PreOrder>preOrderList) {
      addDisposable(PersonHttpClient.getPreorders(userId), new BaseObserver<BaseResponse>(baseView) {
          @Override
          public void onSuccess(BaseResponse response) {
              GsonBuilder builder = new GsonBuilder();
              builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                  public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                      return new Date(json.getAsJsonPrimitive().getAsLong());
                  }
              });
              Gson gson = builder.create();
              List<PreOrder> preOrders = gson.fromJson(gson.toJson(response.getData()), new TypeToken<List<PreOrder>>(){}.getType());
               preOrderList.clear();
               preOrderList.addAll(preOrders);
               baseView.updateList();
          }
      });
    }

    public void cancelPreOrder(Long preorderId) {
        addDisposable(PersonHttpClient.cancelPreorder(preorderId), new BaseObserver<BaseResponse>(baseView) {
            @Override
            public void onSuccess(BaseResponse response) {
                baseView.updateList();
            }
        });
    }

    public void delete(Long preorderId, final int position) {
        addDisposable(PersonHttpClient.deletePreorder(preorderId), new BaseObserver<BaseResponse>(baseView) {
            @Override
            public void onSuccess(BaseResponse response) {
                baseView.updateList(position);
            }
        });
    }
}
