package com.lin.pet.application;

import android.app.Application;
import com.lin.baselib.arouter.IComponentApplication;
import com.lin.baselib.base.BaseApplication;


/**
 * Created by lin on 2018/9/25.
 */

public class MainApplication implements IComponentApplication {
    @Override
    public void onCreate(BaseApplication application) {

    }

    @Override
    public Application getApplication() {
        return BaseApplication.getInstance();
    }
}
