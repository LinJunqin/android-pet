package com.lin.baselib.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.lin.baselib.ui.LoadingDialog;

import me.jessyan.autosize.AutoSizeConfig;

/**
 * Created by lin on 2018/9/18.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView{
    private LoadingDialog dialog;
    public Toast toast;
    protected P presenter;
    private Context context;


    protected abstract P createPresenter();
    public abstract void initData();
    public abstract void initView(View root);
    protected abstract int getLayoutId();
    private View root;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AutoSizeConfig.getInstance().setCustomFragment(true);
        presenter = createPresenter();
        initData();
        if (root == null) {
            root = inflater.inflate(getLayoutId(), container, false);
            initView(root);
        }

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    /**
     * @param s
     */
    @Override
    public void showToast(String s) {

             Toast.makeText(context, s, Toast.LENGTH_LONG).show();

    }

    private void closeLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    private void showLoadingDialog() {

        if (dialog == null) {
            dialog = new LoadingDialog(context);
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
    public  void onError(String msg){
        showToast(msg);
    }
}
