package com.lin.person.application;

import android.app.Application;

import com.lin.baselib.arouter.IComponentApplication;
import com.lin.baselib.base.BaseApplication;
import com.lin.person.utils.AddressUtils;


/**
 * Created by lin on 2018/9/25.
 */

public class PersonApplication implements IComponentApplication {
    @Override
    public void onCreate(BaseApplication application) {
        AddressUtils.getInstance();
    }

    @Override
    public Application getApplication() {
        return BaseApplication.getInstance();
    }
}
