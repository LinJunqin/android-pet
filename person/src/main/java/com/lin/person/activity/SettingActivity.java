package com.lin.person.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lin.baselib.arouter.ArouterHelper;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.baselib.db.SPManager;
import com.lin.person.R;
import com.lin.person.presenter.SettingPresenter;
import com.lin.person.view.SettingView;

/**
 * Created by lin on 2019/5/10.
 */
@Route(path = ArouterPath.ACTIVITY_SETTING)
public class SettingActivity extends BaseActivity<SettingPresenter> implements SettingView{

    private RelativeLayout settingHelp;
    private Button settingLogout;

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.person_activity_setting;
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
        settingHelp = findViewById(R.id.setting_help);
        settingHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArouterHelper.gotoFeedbackActivity();
            }
        });
        settingLogout = findViewById(R.id.setting_logout);
        settingLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPManager.getInstance().putBoolean("isLoginValid",false);
                showToast("退出成功");
                finish();
            }
        });
    }
}
