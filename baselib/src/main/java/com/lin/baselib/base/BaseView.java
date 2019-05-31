package com.lin.baselib.base;


/**
 * Created by lin on 2018/9/18.
 */

public interface BaseView {
    /**
     * 每个activity 和 fragment中的需要更新的UI的基本事件
     */
    void showLoading();

    void hideLoading();

    void showToast(String msg);

    void onError(String msg);
}
