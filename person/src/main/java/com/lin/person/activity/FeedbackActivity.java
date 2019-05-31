package com.lin.person.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.baselib.entity.User;
import com.lin.person.R;
import com.lin.person.presenter.FeedbackPresenter;
import com.lin.person.view.FeedbackView;

/**
 * Created by lin on 2019/5/10.
 */
@Route(path = ArouterPath.ACTIVITY_FEEDBACK)
public class FeedbackActivity extends BaseActivity<FeedbackPresenter> implements FeedbackView{


    private ImageButton back;
    private EditText settingHelpTxt;
    private Button settingSummit;
    @Override
    protected FeedbackPresenter createPresenter() {
        return new FeedbackPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.person_activity_feedback;
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
        settingHelpTxt = findViewById(R.id.setting_help_txt);
        settingSummit = findViewById(R.id.setting_summit);
        settingSummit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  String content = settingHelpTxt.getText().toString().trim();
                  if("".equals(content)){
                      showToast("反馈内容不能为空");

                  }else{
                      presenter.summit(User.getUser().getUserId(),content);
                  }
            }
        });

    }
}
