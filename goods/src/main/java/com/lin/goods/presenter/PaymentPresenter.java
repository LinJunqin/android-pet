package com.lin.goods.presenter;

import com.lin.baselib.base.BasePresenter;
import com.lin.baselib.network.BaseObserver;
import com.lin.baselib.network.BaseResponse;
import com.lin.goods.api.GoodsHttpClient;
import com.lin.goods.entity.OrderDTO;
import com.lin.goods.view.PaymentView;

/**
 * Created by lin on 2019/5/9.
 */

public class PaymentPresenter extends BasePresenter<PaymentView> {
    public PaymentPresenter(PaymentView baseView) {
        super(baseView);
    }

    public void generateOrder(OrderDTO orderDTO) {
        addDisposable(GoodsHttpClient.generateOrder(orderDTO), new BaseObserver<BaseResponse>(baseView) {
            @Override
            public void onSuccess(BaseResponse response) {
                baseView.update();
            }
        });
    }
}
