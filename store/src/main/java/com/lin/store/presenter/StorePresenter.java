package com.lin.store.presenter;


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
import com.lin.store.api.StoreHttpClient;
import com.lin.store.entity.ConditionDTO;
import com.lin.store.entity.Store;
import com.lin.store.view.StoreView;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * Created by lin on 2018/12/14.
 */

public class StorePresenter extends BasePresenter<StoreView> {
    public StorePresenter(StoreView baseView) {
        super(baseView);
    }

    public void initData(final List<Store> storeList) {
        ConditionDTO conditionDTO = new ConditionDTO();
        conditionDTO.setDistance(0.00);
        conditionDTO.setScore(2.50);
        conditionDTO.setType((byte)2);
          addDisposable(StoreHttpClient.getHospitalsOfCondition(conditionDTO), new BaseObserver<BaseResponse>(baseView) {
              @Override
              public void onSuccess(BaseResponse response) {
                  GsonBuilder builder = new GsonBuilder();
                  builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                      public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                          return new Date(json.getAsJsonPrimitive().getAsLong());
                      }
                  });
                  Gson gson = builder.create();
                  List<Store> stores = gson.fromJson(gson.toJson(response.getData()), new TypeToken<List<Store>>(){}.getType());
//                  for(Store store:stores){
//                      Log.i("storeData",store.getProjectNames().get(0));
//                      List<String> projectNames = gson.fromJson(gson.toJson(store.getProjectNames()), new TypeToken<List<String>>(){}.getType());
//                      store.setProjectNames(projectNames);
//                  }
                  storeList.clear();
                  storeList.addAll(stores);
                  baseView.updateList();



              }
          });
    }
}
