package com.lin.baselib.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.lin.baselib.R;


/**
 * 自定义加载进度对话框
 * Created by linjunqin on 25/9/2018.
 */

public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context);
        /**设置对话框背景透明*/
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.baselib_dialog_loading_layout);
    }
}
