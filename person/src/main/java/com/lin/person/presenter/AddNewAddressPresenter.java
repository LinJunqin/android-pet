package com.lin.person.presenter;

import com.lin.baselib.base.BasePresenter;
import com.lin.baselib.network.BaseObserver;
import com.lin.baselib.network.BaseResponse;
import com.lin.person.api.PersonHttpClient;
import com.lin.person.entity.AddressDTO;
import com.lin.person.view.AddNewAddressView;

/**
 * Created by lin on 2019/5/13.
 */

public class AddNewAddressPresenter extends BasePresenter<AddNewAddressView> {
    public AddNewAddressPresenter(AddNewAddressView baseView) {
        super(baseView);
    }

    public void addAddress(AddressDTO addressDTO) {
        addDisposable(PersonHttpClient.addAddress(addressDTO), new BaseObserver<BaseResponse>(baseView) {
            @Override
            public void onSuccess(BaseResponse response) {
                baseView.showToast("添加成功");
            }
        });
    }
}
