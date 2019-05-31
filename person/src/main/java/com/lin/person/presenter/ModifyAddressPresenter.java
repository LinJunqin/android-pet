package com.lin.person.presenter;

import com.lin.baselib.base.BasePresenter;
import com.lin.baselib.network.BaseObserver;
import com.lin.baselib.network.BaseResponse;
import com.lin.person.api.PersonHttpClient;
import com.lin.person.entity.AddressDTO;
import com.lin.person.view.ModifyAddressView;

/**
 * Created by lin on 2019/5/13.
 */

public class ModifyAddressPresenter extends BasePresenter<ModifyAddressView>{
    public ModifyAddressPresenter(ModifyAddressView baseView) {
        super(baseView);
    }


    public void modifyAddress(AddressDTO addressDTO) {
        addDisposable(PersonHttpClient.modifyAddress(addressDTO), new BaseObserver<BaseResponse>(baseView) {
            @Override
            public void onSuccess(BaseResponse response) {
                baseView.showToast("修改成功");
            }
        });
    }
}
