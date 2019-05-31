package com.lin.login.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.login.R;
import com.lin.login.presenter.LoginPresenter;
import com.lin.login.view.LoginView;

import java.util.Timer;
import java.util.TimerTask;


@Route(path = ArouterPath.ACTIVITY_LOGIN)
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {
    private EditText etPhone;
    private EditText etCode;
    private Button btCode;
    private Button btLogin;
    private ImageView ivBack;

    private int TIME = 60;//倒计时
    private static final int CODE_REPEAT = 1;
    private TimerTask tt;
    private Timer tm;
     Handler hd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == CODE_REPEAT) {
                btCode.setEnabled(true);
                btLogin.setEnabled(true);
                tm.cancel();
                tt.cancel();
                TIME = 60;//时间重置
                btCode.setText("重新发送");
            }else {
                btCode.setText(TIME + "重新发送");
            }
        }
    };
    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        presenter.initData();

    }

    @Override
    public void initView() {
        etPhone = findViewById(R.id.et_phone);
        etCode = findViewById(R.id.et_code);

        btCode = findViewById(R.id.bt_code);
        btCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getCode(etPhone.getText().toString());
            }
        });

        btLogin = findViewById(R.id.bt_login);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 presenter.login(etPhone.getText().toString(),etCode.getText().toString());
            }
        });

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  finish();
            }
        });

    }

    @Override
    public void refreshCodeBtn() {
        btCode.setEnabled(false);
        tm = new Timer();
        tt = new TimerTask() {
            @Override
            public void run() {
                hd.sendEmptyMessage(TIME--);
            }
        };
        tm.schedule(tt,0,1000);
    }

    @Override
    public void refreshLoginBtn() {
        btLogin.setEnabled(true);
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
