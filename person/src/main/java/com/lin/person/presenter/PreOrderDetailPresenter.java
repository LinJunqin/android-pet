package com.lin.person.presenter;

import com.lin.baselib.base.BasePresenter;
import com.lin.baselib.network.BaseObserver;
import com.lin.baselib.network.BaseResponse;
import com.lin.person.api.PersonHttpClient;
import com.lin.person.view.PreOrderDetailView;

/**
 * Created by lin on 2019/5/10.
 */

public class PreOrderDetailPresenter extends BasePresenter<PreOrderDetailView> {
    public PreOrderDetailPresenter(PreOrderDetailView baseView) {
        super(baseView);
    }

    public void cancelPreOrder(Long preorderId) {
        addDisposable(PersonHttpClient.cancelPreorder(preorderId), new BaseObserver<BaseResponse>(baseView) {
            @Override
            public void onSuccess(BaseResponse response) {
                baseView.update();
            }
        });
    }
}
