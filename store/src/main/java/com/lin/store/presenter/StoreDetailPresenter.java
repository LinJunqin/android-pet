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
import com.lin.store.entity.PreorderDTO;
import com.lin.store.entity.StoreDetailDTO;
import com.lin.store.view.StoreDetailView;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by lin on 2019/5/10.
 */

public class StoreDetailPresenter extends BasePresenter<StoreDetailView>{
    public StoreDetailPresenter(StoreDetailView baseView) {
        super(baseView);
    }

    public void initData(Long hospitalId) {
        addDisposable(StoreHttpClient.getHospitalDetail(hospitalId), new BaseObserver<BaseResponse>(baseView) {
            @Override
            public void onSuccess(BaseResponse response) {
                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                });
                Gson gson = builder.create();
                StoreDetailDTO storeDetailDTO = gson.fromJson(gson.toJson(response.getData()), new TypeToken<StoreDetailDTO>(){}.getType());
                baseView.updateData(storeDetailDTO);

            }
        });
    }

    public void generatePreorder(PreorderDTO preorderDTO) {
        addDisposable(StoreHttpClient.generatePreorder(preorderDTO), new BaseObserver<BaseResponse>(baseView) {
            @Override
            public void onSuccess(BaseResponse response) {
                baseView.showToast("预约成功");
            }
        });
    }
}
