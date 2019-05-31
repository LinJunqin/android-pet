package com.lin.baselib.arouter;

import android.app.Application;

import com.lin.baselib.base.BaseApplication;


/**
 * 组件 Application 接口
 *
 * @author linjunqin
 * @date 2018/9/25
 */
public interface IComponentApplication {

    void onCreate(BaseApplication application);

    Application getApplication();

}
