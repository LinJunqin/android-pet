package com.lin.person.activity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.baselib.entity.User;
import com.lin.person.R;
import com.lin.person.entity.AddressDTO;
import com.lin.person.presenter.AddNewAddressPresenter;
import com.lin.person.utils.AddressHelper;
import com.lin.person.view.AddNewAddressView;

/**
 * Created by lin on 2019/5/13.
 */
@Route(path = ArouterPath.ACTIVITY_ADDRESS_ADD)
public class AddNewAddressActivity extends BaseActivity<AddNewAddressPresenter> implements AddNewAddressView{

    private EditText etName;
    private EditText etPhone;
    private TextView tvAddress;
    private EditText etAddressDetail;
    private CheckBox cbRemember;
    private Button   btnSummit;
    private ImageButton back;
    AddressDTO addressDTO  = new AddressDTO();;
    @Override
    protected AddNewAddressPresenter createPresenter() {
        return new AddNewAddressPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.person_activity_add_new_address;
    }

    @Override
    public void initData() {

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
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);

        etAddressDetail = findViewById(R.id.et_address_detail);
        cbRemember = findViewById(R.id.cb_remember);
        btnSummit = findViewById(R.id.btn_summit);
        btnSummit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String address = tvAddress.getText().toString().trim();
                String addressDetail = etAddressDetail.getText().toString().trim();
                if("".equals(name)||"".equals(phone)||"".equals(address)||"".equals(addressDetail)){
                    showToast("所填项有空");

                }else{
                    addressDTO.setUserId(User.getUser().getUserId());
                    addressDTO.setPhone(phone);
                    addressDTO.setName(name);
                    addressDTO.setDetail(addressDetail);
                    addressDTO.setIsMain((byte)(cbRemember.isChecked()?1:0));
                    presenter.addAddress(addressDTO);
                }
            }
        });
        tvAddress = findViewById(R.id.tv_address);
        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressHelper.getAddress(AddNewAddressActivity.this,addressDTO,tvAddress);
                 }
        });
    }
}
