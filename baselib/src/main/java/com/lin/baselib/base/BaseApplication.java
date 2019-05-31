package com.lin.baselib.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lin.baselib.arouter.IComponentApplication;
import com.lin.baselib.config.DebugConfig;
import com.lin.baselib.config.ModuleConfig;


import me.jessyan.autosize.AutoSize;


/**
 * Created by lin on 2018/9/19.
 */

public class BaseApplication extends Application {
    public synchronized static BaseApplication getInstance() {
        return instance;
    }
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //路由初始化
        if (DebugConfig.IS_DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        // 初始化 屏幕适配框架
        AutoSize.initCompatMultiProcess(this);

        //Module类的APP初始化
        initModulesApplication();

    }

    private void initModulesApplication(){
        for (String moduleImpl : ModuleConfig.MODULES_LIST){
            try {
                Class<?> clazz = Class.forName(moduleImpl);
                Object obj = clazz.newInstance();
                if (obj instanceof IComponentApplication){
                    ((IComponentApplication) obj).onCreate(BaseApplication.getInstance());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
