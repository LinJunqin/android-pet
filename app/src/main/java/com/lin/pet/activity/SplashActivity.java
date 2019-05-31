package com.lin.pet.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lin.baselib.arouter.ArouterHelper;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.baselib.db.SPManager;
import com.lin.baselib.entity.User;
import com.lin.pet.R;
import com.lin.pet.presenter.SplashPresenter;
import com.lin.pet.view.SplashView;


/**
 * Created by lin on 2018/12/27.
 */
@Route(path = ArouterPath.ACTIVITY_SPLASH)
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashView {
    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.app_activity_splash;
    }

    @Override
    public void initData() {
        if(!SPManager.getInstance().getBoolean("isLoginValid",false)||SPManager.getInstance().getString("phone","").equals("")){
            ArouterHelper.gotoMainActivity();
            finish();
        }else{
            if(User.getUser()==null){
                User.init(SPManager.getInstance().getLong("userId",0),SPManager.getInstance().getString("phone",""),SPManager.getInstance().getString("username",""),SPManager.getInstance().getBoolean("gender",false),SPManager.getInstance().getString("avatar",""));
            }
            presenter.checkLogin(SPManager.getInstance().getString("phone",""));
        }

    }

    @Override
    public void initView() {
//        Intent service = new Intent(SplashActivity.this,SplashService.class);
//        startService(service);
//        broadcastReceiver = new SplashBroadcastReceiver(this);
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(BROADCAST_ACTION);
//        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void finishActivity() {
        finish();
    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(broadcastReceiver);
//    }
}
