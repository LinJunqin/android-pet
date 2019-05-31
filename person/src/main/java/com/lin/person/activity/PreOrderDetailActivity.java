package com.lin.person.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.baselib.base.BaseApplication;
import com.lin.baselib.entity.User;
import com.lin.baselib.util.ImageLoaderUtil;
import com.lin.person.R;
import com.lin.person.entity.CommentDTO;
import com.lin.person.entity.PreOrder;
import com.lin.person.presenter.PreOrderDetailPresenter;
import com.lin.person.view.PreOrderDetailView;

/**
 * Created by lin on 2019/5/10.
 */
@Route(path = ArouterPath.ACTIVITY_PRE_ORDER_DETAIL)
public class PreOrderDetailActivity extends BaseActivity<PreOrderDetailPresenter> implements PreOrderDetailView{
    PreOrder preOrder;

    Button btnComment;
    Button btnCancel;
    TextView tvOrderStatus;
    ImageView ivStoreAvatar;
    TextView tvStoreName;
    TextView tvStoreAddress;
    TextView tvPreOrderTime;
    TextView tvProjectName;
    TextView tvPreStorePhone;
    @Override
    protected PreOrderDetailPresenter createPresenter() {
        return new PreOrderDetailPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.person_activity_pre_order_detail;
    }

    @Override
    public void initData() {

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
        preOrder = (PreOrder) getIntent().getSerializableExtra("preOrder");
        tvOrderStatus = findViewById(R.id.tv_order_status);
        ivStoreAvatar = findViewById(R.id.iv_store_avatar);
        tvStoreName = findViewById(R.id.tv_store_name);
        tvStoreAddress = findViewById(R.id.tv_store_address);
        tvPreOrderTime = findViewById(R.id.tv_pre_order_time);
        btnComment = findViewById(R.id.btn_comment);
        btnCancel = findViewById(R.id.btn_cancel);
        tvProjectName = findViewById(R.id.tv_project_name);
        tvPreStorePhone = findViewById(R.id.tv_pre_store_phone);

        tvProjectName.setText(preOrder.getTitle());
        tvPreStorePhone.setText("服务热线:"+preOrder.getHospitalPhone());
        ImageLoaderUtil.getInstance(BaseApplication.getInstance()).displayImage(ivStoreAvatar,preOrder.getAvatar());
       tvStoreName.setText(preOrder.getHospitalName());
       tvStoreAddress.setText(preOrder.getAddress());
       tvPreOrderTime.setText(preOrder.getArriveTime());
        if(preOrder.getStatus().byteValue()==0){
           tvOrderStatus.setText("预约成功");
        }else if(preOrder.getStatus().byteValue()==1){
           btnComment.setVisibility(View.INVISIBLE);
           btnCancel.setVisibility(View.INVISIBLE);
           tvOrderStatus.setText("已取消");
           tvOrderStatus.setTextColor(Color.rgb(193, 193, 193));
        }else if(preOrder.getStatus().byteValue()==2){
           tvOrderStatus.setText("已评价");
           btnComment.setVisibility(View.INVISIBLE);
           btnCancel.setVisibility(View.INVISIBLE);
           tvOrderStatus.setTextColor(Color.rgb(193, 193, 193));
        }

       btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setPreorderId(preOrder.getPreorderId());
                commentDTO.setHospitalId(preOrder.getHospitalId());
                commentDTO.setUserId(User.getUser().getUserId());
                ARouter.getInstance().build(ArouterPath.ACTIVITY_COMMENT).withSerializable("commentDTO",commentDTO).navigation(PreOrderDetailActivity.this,2);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.cancelPreOrder(preOrder.getPreorderId());
            }
        });


    }

    @Override
    public void update() {
        btnComment.setVisibility(View.INVISIBLE);
        btnCancel.setVisibility(View.INVISIBLE);
        tvOrderStatus.setText("已取消");
        tvOrderStatus.setTextColor(Color.rgb(193, 193, 193));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==2){
                tvOrderStatus.setText("已评价");
                btnComment.setVisibility(View.INVISIBLE);
                btnCancel.setVisibility(View.INVISIBLE);
                tvOrderStatus.setTextColor(Color.rgb(193, 193, 193));
            }
        }
    }
}
