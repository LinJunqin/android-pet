package com.lin.person.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.person.R;
import com.lin.person.entity.Address;
import com.lin.person.presenter.AddressPresenter;

import java.util.List;

/**
 * Created by lin on 2019/5/13.
 */

public class AddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Address> addressList;
    private AddressPresenter presenter;
    public AddressAdapter(List<Address> addressList,AddressPresenter presenter){
        this.addressList = addressList;
        this.presenter = presenter;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root;
        root = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item_address, parent, false);
        return new AddressHolder(root);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        AddressHolder addressHolder =  (AddressHolder) holder;
        final Address address = addressList.get(position);
        addressHolder.tvName.setText(address.getName());
        addressHolder.tvPhone.setText(address.getPhone());
        addressHolder.tvLabel.setText(address.getIsMain()==1?"常用地址":"");
        addressHolder.tvAddress.setText(address.getProvince()+address.getCity()+address.getCounty()+address.getDetail());
        addressHolder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ArouterPath.ACTIVITY_ADDRESS_MODIFY).withSerializable("address",address).navigation();
            }
        });
        addressHolder.ivDelete.setVisibility(address.getIsMain()==1?View.INVISIBLE:View.VISIBLE);
        addressHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.deleteAddress(address.getAddressId(),position,addressList);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(addressList!=null){
            return addressList.size();
        }
        return 0;
    }
    private static class AddressHolder extends RecyclerView.ViewHolder{
        public TextView tvName;
        public TextView tvPhone;
        public TextView tvLabel;
        public TextView tvAddress;
        public ImageView ivEdit;
        public ImageView ivDelete;

        public AddressHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhone = itemView.findViewById(R.id.tv_phone);
            tvLabel = itemView.findViewById(R.id.tv_label);
            tvAddress = itemView.findViewById(R.id.tv_address);
            ivEdit = itemView.findViewById(R.id.iv_edit);
            ivDelete = itemView.findViewById(R.id.iv_delete);

        }
    }
}
