package com.lin.pet.presenter;


import com.lin.baselib.arouter.ArouterHelper;
import com.lin.baselib.base.BasePresenter;
import com.lin.baselib.db.SPManager;
import com.lin.baselib.network.BaseObserver;
import com.lin.baselib.network.BaseResponse;
import com.lin.pet.api.AppHttpClient;
import com.lin.pet.view.SplashView;

/**
 * Created by lin on 2018/12/13.
 */

public class SplashPresenter extends BasePresenter<SplashView> {
    public SplashPresenter(SplashView baseView) {
        super(baseView);
    }

    public void checkLogin(String phone){
        addDisposable(AppHttpClient.checkLogin(phone), new BaseObserver<BaseResponse>(baseView) {
            @Override
            public void onSuccess(BaseResponse response) {

            }

            @Override
            public void onNext(BaseResponse response) {
                super.onNext(response);
                if(response.getStatus()==10001){
                    SPManager.getInstance().putBoolean("isLoginValid",true);
                }else{
                    SPManager.getInstance().putBoolean("isLoginValid",false);
                }
                ArouterHelper.gotoMainActivity();
                baseView.finishActivity();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ArouterHelper.gotoMainActivity();
                baseView.finishActivity();
            }
        });
    }
}
