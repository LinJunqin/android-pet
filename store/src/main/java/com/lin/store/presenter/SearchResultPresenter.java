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
import com.lin.store.entity.Store;
import com.lin.store.view.SearchResultView;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * Created by lin on 2019/5/9.
 */

public class SearchResultPresenter extends BasePresenter<SearchResultView> {
    public SearchResultPresenter(SearchResultView baseView) {
        super(baseView);
    }

    public void search(String key) {
        addDisposable(StoreHttpClient.getHospitalOfSearch(key), new BaseObserver<BaseResponse>(baseView) {
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
                baseView.updateList(stores);
            }
        });
    }
}
