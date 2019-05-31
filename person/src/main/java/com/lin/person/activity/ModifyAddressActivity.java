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
import com.lin.person.R;
import com.lin.person.entity.Address;
import com.lin.person.entity.AddressDTO;
import com.lin.person.presenter.ModifyAddressPresenter;
import com.lin.person.utils.AddressHelper;
import com.lin.person.view.ModifyAddressView;

/**
 * Created by lin on 2019/5/13.
 */
@Route(path = ArouterPath.ACTIVITY_ADDRESS_MODIFY)
public class ModifyAddressActivity extends BaseActivity<ModifyAddressPresenter> implements ModifyAddressView{


    Address address;

    AddressDTO addressDTO;
    private EditText etName;
    private EditText etPhone;
    private TextView tvAddress;
    private EditText etAddressDetail;
    private CheckBox cbRemember;
    private Button btnSave;
    private ImageButton back;

    @Override
    protected ModifyAddressPresenter createPresenter() {
        return new ModifyAddressPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.person_activity_modify_address;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        address = (Address) getIntent().getSerializableExtra("address");
        addressDTO = new AddressDTO();
        addressDTO.setProvince(address.getProvince());
        addressDTO.setCity(address.getCity());
        addressDTO.setCounty(address.getCounty());
        addressDTO.setAddressId(address.getAddressId());
        addressDTO.setDetail(address.getDetail());
        addressDTO.setUserId(address.getUserId());
        addressDTO.setIsMain(address.getIsMain());
        addressDTO.setName(address.getName());
        addressDTO.setPhone(address.getPhone());

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        etName = findViewById(R.id.et_name);
        etName.setText(addressDTO.getName());
        etPhone = findViewById(R.id.et_phone);
        etPhone.setText(addressDTO.getPhone());
        tvAddress = findViewById(R.id.tv_address);
        tvAddress.setText(addressDTO.getProvince()+addressDTO.getCity()+addressDTO.getCounty());
        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressHelper.getAddress(ModifyAddressActivity.this,addressDTO,tvAddress);
            }
        });
        etAddressDetail = findViewById(R.id.et_address_detail);
        etAddressDetail.setText(address.getDetail());
        cbRemember = findViewById(R.id.cb_remember);
        cbRemember.setChecked(address.getIsMain()==1);

        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String address = tvAddress.getText().toString().trim();
                String addressDetail = etAddressDetail.getText().toString().trim();
                if("".equals(name)||"".equals(phone)||"".equals(address)||"".equals(addressDetail)){
                    showToast("所填项有空");

                }else{
                    addressDTO.setPhone(phone);
                    addressDTO.setName(name);
                    addressDTO.setDetail(addressDetail);
                    addressDTO.setIsMain((byte)(cbRemember.isChecked()?1:0));

                    presenter.modifyAddress(addressDTO);
                }
            }
        });
    }
}
