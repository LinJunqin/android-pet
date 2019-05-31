package com.lin.goods.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lin.baselib.arouter.ArouterHelper;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.baselib.base.BaseApplication;
import com.lin.baselib.db.SPManager;
import com.lin.baselib.util.ImageLoaderUtil;
import com.lin.goods.R;
import com.lin.goods.entity.GoodsDetailDTO;
import com.lin.goods.presenter.GoodsDetailPresenter;
import com.lin.goods.view.GoodsDetailView;

/**
 * Created by lin on 2019/5/9.
 */
@Route(path = ArouterPath.ACTIVITY_GOODS_DETAIL)
public class GoodsDetailActivity extends BaseActivity<GoodsDetailPresenter> implements GoodsDetailView{
     ImageView ivGoodsAvatar;
     TextView  tvName;
    TextView   tvPrice;
    TextView   tvCount;
    ImageView  ivGoodsDetail;
    @Override
    protected GoodsDetailPresenter createPresenter() {
        return new GoodsDetailPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_activity_goods_detail;
    }

    @Override
    public void initData() {
        Long goodsId = getIntent().getLongExtra("goodsId",0L);
        presenter.initData(goodsId);
    }

    @Override
    public void initView() {
        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button btnBuy = findViewById(R.id.btn_buy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SPManager.getInstance().getBoolean("isLoginValid",false)){
                    ArouterHelper.gotoLoginActivity();
                    return;
                }
                ARouter.getInstance().build(ArouterPath.ACTIVITY_GOODS_ORDER).withSerializable("goodsDetailDTO",goodsDetailDTO).navigation();
            }
        });

        ivGoodsAvatar = findViewById(R.id.iv_goods_avatar);
        tvName = findViewById(R.id.tv_name);
        tvPrice = findViewById(R.id.tv_price);
        tvCount = findViewById(R.id.tv_count);
        ivGoodsDetail = findViewById(R.id.iv_goods_detail);
    }
    private GoodsDetailDTO goodsDetailDTO;
    @Override
    public void update(GoodsDetailDTO goodsDetailDTO) {
        this.goodsDetailDTO =goodsDetailDTO;
        ImageLoaderUtil.getInstance(BaseApplication.getInstance()).displayImage(ivGoodsAvatar,goodsDetailDTO.getAvatar().get(0));
        tvName.setText(goodsDetailDTO.getName());
        tvPrice.setText("价格：￥"+goodsDetailDTO.getPrice());
        tvCount.setText("库存："+goodsDetailDTO.getSum());
        ImageLoaderUtil.getInstance(BaseApplication.getInstance()).displayImage(ivGoodsDetail,goodsDetailDTO.getDetailImage());
    }
}
