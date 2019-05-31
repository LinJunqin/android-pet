package com.lin.login.application;

import android.app.Application;

import com.lin.baselib.arouter.IComponentApplication;
import com.lin.baselib.base.BaseApplication;


/**
 * Created by lin on 2018/9/25.
 */

public class LoginApplication implements IComponentApplication {
    @Override
    public void onCreate(BaseApplication application) {
       // 各组件application的初始化

    }

    @Override
    public Application getApplication() {
        return BaseApplication.getInstance();
    }
}
