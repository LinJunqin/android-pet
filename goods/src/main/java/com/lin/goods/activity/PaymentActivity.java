package com.lin.goods.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lin.baselib.arouter.ArouterHelper;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.goods.R;
import com.lin.goods.entity.OrderDTO;
import com.lin.goods.presenter.PaymentPresenter;
import com.lin.goods.view.PaymentView;

/**
 * Created by lin on 2019/5/9.
 */
@Route(path = ArouterPath.ACTIVITY_PAYMENT)
public class PaymentActivity extends BaseActivity<PaymentPresenter> implements PaymentView{

    OrderDTO orderDTO;
    TextView tvPaymentSum;
    @Override
    protected PaymentPresenter createPresenter() {
        return new PaymentPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_activity_payment;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        orderDTO = (OrderDTO) getIntent().getSerializableExtra("orderDTO");
        Button btnBuy = findViewById(R.id.btn_buy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               presenter.generateOrder(orderDTO);
            }
        });
        tvPaymentSum = findViewById(R.id.tv_payment_sum);
        tvPaymentSum.setText("需支付：￥"+orderDTO.getAccount());

    }

    @Override
    public void update() {
        ArouterHelper.gotoPayResultActivity();
    }
}
