package com.lin.baselib.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.lin.baselib.R;
import com.lin.baselib.ui.LoadingDialog;


import me.jessyan.autosize.internal.CustomAdapt;

/**
 * Created by lin on 2018/9/18.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView, CustomAdapt {

    private LoadingDialog dialog;
    public Toast toast;
    protected P presenter;

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //StatusBarCompat.setStatusBarColor(this, getBaseContext().getResources().getColorStateList(R.color.theme_color).getDefaultColor());
        ImmersionBar.with(this)
                .fitsSystemWindows(true, R.color.theme_color)
               .statusBarColor(R.color.theme_color)
                .init();
        presenter = createPresenter();
        initView();
        initData();
    }

    public abstract void initData();


    public abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    /**
     * @param s
     */
    @Override
    public void showToast(String s) {
           Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    private void closeLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    private void showLoadingDialog() {

        if (dialog == null) {
            dialog = new LoadingDialog(this);
        }
        dialog.show();
    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }


    @Override
    public void hideLoading() {
        closeLoadingDialog();
    }


    @Override
    public void onError(String msg) {
        showToast(msg);
    }


    // 屏幕适配相关
    @Override
    public boolean isBaseOnWidth() {
        return true;
    }

    @Override
    public float getSizeInDp() {
        return 0;
    }
}
