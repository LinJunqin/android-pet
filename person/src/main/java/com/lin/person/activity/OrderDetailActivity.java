package com.lin.person.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.baselib.base.BaseApplication;
import com.lin.baselib.util.ImageLoaderUtil;
import com.lin.person.R;
import com.lin.person.entity.Order;
import com.lin.person.entity.OrderDetailDTO;
import com.lin.person.presenter.OrderDetailPresenter;
import com.lin.person.view.OrderDetailView;

/**
 * Created by lin on 2019/5/10.
 */
@Route(path = ArouterPath.ACTIVITY_ORDER_DETAIL)
public class OrderDetailActivity extends BaseActivity<OrderDetailPresenter> implements OrderDetailView{
    Order order;
     Button    btnGoodsGot;
     TextView  tvOrderStatus;
     ImageView ivGoodsAvatar;
     TextView  tvGoodsName;
     TextView  tvGoodsAmount;
     TextView  tvOrderAccount;

     TextView tvName;
     TextView tvPhone;
     TextView tvAddress;
     TextView tvLogisticsCompany;
     TextView tvLogisticsNum;
    @Override
    protected OrderDetailPresenter createPresenter() {
        return new OrderDetailPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.person_activity_order_detail;
    }

    @Override
    public void initData() {
          presenter.initData(order.getOrderId());
    }

    @Override
    public void initView() {
        order = (Order) getIntent().getSerializableExtra("order");
        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnGoodsGot = findViewById(R.id.btn_goods_got);
        tvOrderStatus = findViewById(R.id.tv_order_status);
        ivGoodsAvatar = findViewById(R.id.iv_goods_avatar);
        tvGoodsName = findViewById(R.id.tv_goods_name);
        tvGoodsAmount = findViewById(R.id.tv_goods_amount);
        tvOrderAccount = findViewById(R.id.tv_order_account);
        btnGoodsGot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.sureOfGot(order.getOrderId());
            }
        });
        ImageLoaderUtil.getInstance(BaseApplication.getInstance()).displayImage(ivGoodsAvatar,order.getAvatar());

       tvGoodsName.setText(order.getName());
       tvGoodsAmount.setText("购买数量："+order.getSum());
       tvOrderAccount.setText("总价："+order.getAccount().intValue());
        if(order.getStatus().byteValue()==0){
            tvOrderStatus.setText("进行中");
        }else if(order.getStatus().byteValue()==2){
            tvOrderStatus.setText("订单已结束");
            tvOrderStatus.setTextColor(Color.rgb(193, 193, 193));
            btnGoodsGot.setVisibility(View.INVISIBLE);
        }
        tvName = findViewById(R.id.tv_name);
        tvPhone = findViewById(R.id.tv_phone);
        tvAddress = findViewById(R.id.tv_address);
        tvLogisticsCompany = findViewById(R.id.tv_logistics_company);
        tvLogisticsNum = findViewById(R.id.tv_logistics_num);
    }

    @Override
    public void update() {
        tvOrderStatus.setText("订单已结束");
        tvOrderStatus.setTextColor(Color.rgb(193, 193, 193));
        btnGoodsGot.setVisibility(View.INVISIBLE);
    }

    @Override
    public void updateData(OrderDetailDTO orderDetailDTO) {
        tvName.setText(orderDetailDTO.getRecevierName());
        tvPhone.setText(orderDetailDTO.getPhone());
        tvAddress.setText(orderDetailDTO.getProvince()+orderDetailDTO.getCity()+orderDetailDTO.getCounty()+orderDetailDTO.getDetail());
        tvLogisticsCompany.setText(orderDetailDTO.getLogisticsCompany());
        tvLogisticsNum.setText(orderDetailDTO.getLogisticsNum());
    }
}
