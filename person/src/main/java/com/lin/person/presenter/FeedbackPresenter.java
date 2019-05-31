package com.lin.person.presenter;

import com.lin.baselib.base.BasePresenter;
import com.lin.baselib.network.BaseObserver;
import com.lin.baselib.network.BaseResponse;
import com.lin.person.api.PersonHttpClient;
import com.lin.person.view.FeedbackView;

/**
 * Created by lin on 2019/5/10.
 */

public class FeedbackPresenter extends BasePresenter<FeedbackView> {
    public FeedbackPresenter(FeedbackView baseView) {
        super(baseView);
    }

    public void summit(Long userId, String content) {
        addDisposable(PersonHttpClient.summitFeedback(userId, content), new BaseObserver<BaseResponse>(baseView) {
            @Override
            public void onSuccess(BaseResponse response) {
                baseView.showToast("谢谢您的反馈");
            }
        });
    }
}
