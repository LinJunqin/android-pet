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
import com.lin.person.entity.Address;
import com.lin.person.view.AddressView;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * Created by lin on 2019/5/13.
 */

public class AddressPresenter extends BasePresenter<AddressView>{
    public AddressPresenter(AddressView baseView) {
        super(baseView);
    }
    public void initData(final List<Address> addresses){
          addDisposable(PersonHttpClient.getAllAddress(User.getUser().getUserId()), new BaseObserver<BaseResponse>(baseView) {
              @Override
              public void onSuccess(BaseResponse response) {
                  //Gson gson = new Gson();
                  GsonBuilder builder = new GsonBuilder();
                  builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                      public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                          return new Date(json.getAsJsonPrimitive().getAsLong());
                      }
                  });
                  Gson gson = builder.create();
                  List<Address> addressList = gson.fromJson(gson.toJson(response.getData()), new TypeToken<List<Address>>(){}.getType());
                  addresses.clear();
                  addresses.addAll(addressList);
                  baseView.updateList();
              }
          });
    }

    public void deleteAddress(Long addressId, final int position, final List<Address> addressList) {
        addDisposable(PersonHttpClient.deleteAddress(addressId), new BaseObserver<BaseResponse>(baseView) {
            @Override
            public void onSuccess(BaseResponse response) {
                addressList.remove(position);
                baseView.updateList();
            }
        });
    }
}
