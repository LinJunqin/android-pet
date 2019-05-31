package com.lin.person.utils;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.lin.person.R;
import com.lin.person.entity.AddressDTO;
import com.lin.person.entity.ProvinceBean;

import java.util.ArrayList;

/**
 * Created by lin on 2019/5/23.
 */

public class AddressHelper {
    private static AddressUtils mAddressSelector = AddressUtils.getInstance();
    private static OptionsPickerView pvOptions;
    public static void getAddress(Context context, final AddressDTO addressDTO, final TextView tvAddress) {
        final ArrayList<ProvinceBean> options1Items = mAddressSelector.getProvinces();
        final ArrayList<ArrayList<String>> options2Items = mAddressSelector.getCitys();
        final ArrayList<ArrayList<ArrayList<String> > >options3Items = mAddressSelector.getAreas();
        Log.i("size",options1Items.size()+"");
        pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                //返回的分别是三个级别的选中位置
                addressDTO.setProvince(options1Items.get(options1).getPickerViewText());
                addressDTO.setCity(options2Items.get(options1).get(option2));
                addressDTO.setCounty(options3Items.get(options1).get(option2).get(options3));
                tvAddress.setText(addressDTO.getProvince()+addressDTO.getCity()+addressDTO.getCounty());
                return;

            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setBackgroundId(R.drawable.list_group_item_background)
                .setDividerColor(Color.parseColor("#499BF7"))
                .setSubCalSize(18)//确定和取消文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleColor(Color.parseColor("#499BF7"))//标题文字颜色
                .setSubmitColor(Color.parseColor("#499BF7"))//确定按钮文字颜色
                .setCancelColor(Color.parseColor("#499BF7"))//取消按钮文字颜色
                .setContentTextSize(18)//滚轮文字大小
                //.setLinkage(true)//设置是否联动，默认true
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(0, 0, 0)  //设置默认选中项
                .setOutSideCancelable(true)//点击外部dismiss default true
                .build();
        pvOptions.setPicker(options1Items,options2Items,options3Items);//添加数据源
        pvOptions.show(true);
    }
}
