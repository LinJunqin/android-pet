package com.lin.goods.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.baselib.base.BaseApplication;
import com.lin.baselib.entity.User;
import com.lin.baselib.ui.AmountView;
import com.lin.baselib.util.ImageLoaderUtil;
import com.lin.goods.R;
import com.lin.goods.entity.Address;
import com.lin.goods.entity.GoodsDetailDTO;
import com.lin.goods.entity.OrderDTO;
import com.lin.goods.presenter.GoodsOrderPresenter;
import com.lin.goods.view.GoodsOrderView;

import java.math.BigDecimal;

/**
 * Created by lin on 2019/5/9.
 */
@Route(path = ArouterPath.ACTIVITY_GOODS_ORDER)
public class GoodsOrderActivity extends BaseActivity<GoodsOrderPresenter> implements GoodsOrderView{

    GoodsDetailDTO goodsDetailDTO;

    TextView tvName;
    TextView tvPhone;
    TextView tvAddress;

    ImageView  ivGoodsAvatar;
    TextView   tvGoodsName;
    AmountView avGoodsAmount;
    TextView   tvOrderAccount;

    @Override
    protected GoodsOrderPresenter createPresenter() {
        return new GoodsOrderPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_activity_goods_order;
    }

    @Override
    public void initData() {
         presenter.initData(User.getUser().getUserId());
    }
    private int amount = 1;
    private Address address;
    @Override
    public void initView() {
        goodsDetailDTO = (GoodsDetailDTO) getIntent().getSerializableExtra("goodsDetailDTO");
        Button btnBuy = findViewById(R.id.btn_buy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setGoodsId(goodsDetailDTO.getGoodsId());
                orderDTO.setAccount(new BigDecimal(amount*goodsDetailDTO.getPrice().doubleValue()));
                orderDTO.setStoreId(goodsDetailDTO.getStoreId());
                orderDTO.setSum((byte)amount);
                orderDTO.setUserId(User.getUser().getUserId());
                orderDTO.setProvince(address.getProvince());
                orderDTO.setCity(address.getCity());
                orderDTO.setCounty(address.getCounty());
                orderDTO.setDetail(address.getDetail());
                orderDTO.setPhone(address.getPhone());
                orderDTO.setRecevierName(address.getName());
                ARouter.getInstance().build(ArouterPath.ACTIVITY_PAYMENT).withSerializable("orderDTO",orderDTO).navigation();
            }
        });

        tvName = findViewById(R.id.tv_name);
        tvPhone = findViewById(R.id.tv_phone);
        tvAddress = findViewById(R.id.tv_address);
        ivGoodsAvatar = findViewById(R.id.iv_goods_avatar);
        tvGoodsName = findViewById(R.id.tv_goods_name);
        avGoodsAmount = findViewById(R.id.av_goods_amount);
        avGoodsAmount.setAmount(amount);
        tvOrderAccount = findViewById(R.id.tv_order_account);
        ImageLoaderUtil.getInstance(BaseApplication.getInstance()).displayImage(ivGoodsAvatar,goodsDetailDTO.getAvatar().get(0));

        tvGoodsName.setText(goodsDetailDTO.getName());
        avGoodsAmount.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount, int position) {
                GoodsOrderActivity.this.amount =amount;
                tvOrderAccount.setText("总价：￥"+amount*goodsDetailDTO.getPrice().doubleValue());
            }
        });
        tvOrderAccount.setText("总价：￥"+goodsDetailDTO.getPrice());

    }

    @Override
    public void update(Address address) {
        this.address =address;
        tvName.setText(address.getName());
        tvPhone.setText(address.getPhone());
        tvAddress.setText(address.getProvince()+address.getCity()+address.getCounty()+address.getDetail());
    }
}
