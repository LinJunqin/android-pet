package com.lin.person.fragment;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lin.baselib.arouter.ArouterHelper;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseFragment;
import com.lin.baselib.entity.User;
import com.lin.baselib.util.ImageLoaderUtil;
import com.lin.person.R;
import com.lin.person.presenter.PersonPresenter;
import com.lin.person.view.PersonView;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by lin on 2018/12/25.
 */
@Route(path = ArouterPath.FRAGMENT_PERSON)
public class PersonFragment extends BaseFragment<PersonPresenter> implements PersonView {

    private RelativeLayout rlPersonInfo;
    private CircleImageView civUserUrl;
    private TextView tvUsername;
    private RelativeLayout rlOrder;
    private RelativeLayout rlAppointment;
    private RelativeLayout rlSetting;

    @Override
    protected PersonPresenter createPresenter() {
        return new PersonPresenter(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(View root) {
        civUserUrl = root.findViewById(R.id.civ_user_url);
        tvUsername = root.findViewById(R.id.tv_username);
        if(User.getUser()!=null){
            ImageLoaderUtil.getInstance(getContext()).displayImage(civUserUrl, User.getUser().getAvatar());
            tvUsername.setText(User.getUser().getUsername());
        }
        rlPersonInfo = root.findViewById(R.id.rl_person_info);
        rlPersonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArouterHelper.gotoInfoManageActivity();
            }
        });
        rlOrder = root.findViewById(R.id.rl_order);
        rlOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArouterHelper.gotoOrderActivity();
            }
        });
        rlAppointment = root.findViewById(R.id.rl_appointment);
        rlAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArouterHelper.gotoPreOrderActivity();
            }
        });
        rlSetting = root.findViewById(R.id.rl_setting);
        rlSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArouterHelper.gotoSettingActivity();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.person_fragment_person;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(User.getUser()!=null){
            ImageLoaderUtil.getInstance(getContext()).displayImage(civUserUrl, User.getUser().getAvatar());
            tvUsername.setText(User.getUser().getUsername());
        }

    }
}
