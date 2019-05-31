package com.lin.store.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.baselib.base.BaseApplication;
import com.lin.baselib.ui.RecyclerViewDivider;
import com.lin.baselib.util.ImageLoaderUtil;
import com.lin.store.R;
import com.lin.store.adapter.CommentAdapter;
import com.lin.store.adapter.ProjectAdapter;
import com.lin.store.entity.CommentDTO;
import com.lin.store.entity.ProjectDTO;
import com.lin.store.entity.StoreDetailDTO;
import com.lin.store.presenter.StoreDetailPresenter;
import com.lin.store.view.StoreDetailView;
import com.mylhyl.circledialog.CircleDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2019/5/10.
 */
@Route(path = ArouterPath.ACTIVITY_STORE_DETAIL)
public class StoreDetailActivity extends BaseActivity<StoreDetailPresenter> implements StoreDetailView{
    private RecyclerView rvComment;
    private List<CommentDTO> commentList = new ArrayList<>();
    private CommentAdapter adapter;

    private RecyclerView rvProject;
    private List<ProjectDTO> projectList = new ArrayList<>();
    private ProjectAdapter projectAdapter;

    private ImageView ivPhone;
    ImageView ivStoreFace;
    TextView  tvStoreName;
    TextView  tvStoreAddress;

    Long hospitalId;

    private StoreDetailDTO storeDetailDTO = new StoreDetailDTO();
    @Override
    protected StoreDetailPresenter createPresenter() {
        return new StoreDetailPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.store_activity_store_detail;
    }

    @Override
    public void initData() {
        hospitalId = getIntent().getLongExtra("hospitalId",0L);
        presenter.initData(hospitalId);

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
        rvComment = findViewById(R.id.rv_comment);
        adapter = new CommentAdapter(commentList);
        rvComment.setLayoutManager(new LinearLayoutManager(this));
        rvComment.addItemDecoration(new RecyclerViewDivider(getBaseContext(), LinearLayoutManager.VERTICAL, 1, ContextCompat.getColor(getBaseContext(), R.color.gray)));
        rvComment.setAdapter(adapter);

        rvProject = findViewById(R.id.rv_project);
        projectAdapter = new ProjectAdapter(projectList,StoreDetailActivity.this,presenter);
        rvProject.setLayoutManager(new LinearLayoutManager(this));
        rvProject.addItemDecoration(new RecyclerViewDivider(getBaseContext(), LinearLayoutManager.VERTICAL, 1, ContextCompat.getColor(getBaseContext(), R.color.gray)));
        rvProject.setAdapter(projectAdapter);

        ivPhone = findViewById(R.id.iv_phone);
        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CircleDialog.Builder(StoreDetailActivity.this)
                        .setCancelable(false)
                        .setTitle("温馨提示")
                        .setText("将打电话给" + storeDetailDTO.getHospitalPhone())
                        .setWidth(0.6f)
                        .setNegative("取消", null)
                        .setPositive("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String num = "13632462201";
                                Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + num));
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });
        ivStoreFace = findViewById(R.id.iv_store_face);
        tvStoreName = findViewById(R.id.tv_store_name);
        tvStoreAddress = findViewById(R.id.tv_store_address);
    }

    @Override
    public void updateData(StoreDetailDTO data) {
        storeDetailDTO = data;
        commentList.addAll(storeDetailDTO.getCommentDTOList());
        projectList.addAll(storeDetailDTO.getProjectDTOList());
        for(ProjectDTO projectDTO:projectList){
            projectDTO.setHospitalId(hospitalId);
        }
        ImageLoaderUtil.getInstance(BaseApplication.getInstance()).displayImage(ivStoreFace,storeDetailDTO.getAvatar());
        tvStoreName.setText(storeDetailDTO.getHospitalName());
        tvStoreAddress.setText(storeDetailDTO.getAddress());
        adapter.notifyDataSetChanged();
        projectAdapter.notifyDataSetChanged();
    }
}
