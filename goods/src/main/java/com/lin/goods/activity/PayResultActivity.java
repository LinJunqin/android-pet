package com.lin.goods.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.goods.R;
import com.lin.goods.presenter.PayResultPresenter;
import com.lin.goods.view.PayResultView;

/**
 * Created by lin on 2019/5/9.
 */
@Route(path = ArouterPath.ACTIVITY_PAY_RESULT)
public class PayResultActivity extends BaseActivity<PayResultPresenter> implements PayResultView{
    @Override
    protected PayResultPresenter createPresenter() {
        return new PayResultPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_activity_pay_result;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }
}
