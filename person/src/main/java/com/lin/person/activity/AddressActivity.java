package com.lin.person.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lin.baselib.arouter.ArouterHelper;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.person.R;
import com.lin.person.adapter.AddressAdapter;
import com.lin.person.entity.Address;
import com.lin.person.presenter.AddressPresenter;
import com.lin.person.view.AddressView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2019/5/13.
 */
@Route(path = ArouterPath.ACTIVITY_ADDRESS)
public class AddressActivity extends BaseActivity<AddressPresenter> implements AddressView{

    private Button btnNewAddress;
    private RecyclerView rvAddress;
    private ImageButton back;
    private List<Address> addressList = new ArrayList<>();
    private AddressAdapter adapter;
    @Override
    protected AddressPresenter createPresenter() {
        return new AddressPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.person_activity_address;
    }

    @Override
    public void initData() {
        presenter.initData(addressList);
    }

    @Override
    public void initView() {
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnNewAddress = findViewById(R.id.btn_new_address);
        btnNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArouterHelper.gotoAddAddressActivity();
            }
        });
        rvAddress = findViewById(R.id.rv_address);
        adapter = new AddressAdapter(addressList,presenter);
        rvAddress.setLayoutManager(new LinearLayoutManager(this));
        rvAddress.setAdapter(adapter);
    }

    @Override
    protected void onRestart() {
        initData();
        super.onRestart();
    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }
}
