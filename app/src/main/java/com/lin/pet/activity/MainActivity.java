package com.lin.pet.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lin.baselib.arouter.ArouterHelper;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.baselib.db.SPManager;
import com.lin.baselib.entity.User;
import com.lin.goods.fragment.HomeFragment;
import com.lin.person.fragment.PersonFragment;
import com.lin.pet.R;
import com.lin.pet.presenter.MainPresenter;
import com.lin.pet.view.MainView;
import com.lin.store.fragment.StoreFragment;


@Route(path = ArouterPath.ACTIVITY_MAIN)
public class MainActivity extends BaseActivity<MainPresenter> implements MainView {
    private Fragment[] fragments;
    private ImageView[] imageViews;
    private TextView[] textViews;
    private int index;
    private int currentTabIndex;
    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.app_activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initView() {
        fragments = new Fragment[3];
        fragments[0] = (HomeFragment) ARouter.getInstance().build(ArouterPath.FRAGMENT_HOME).navigation();
        fragments[1] = (StoreFragment)ARouter.getInstance().build(ArouterPath.FRAGMENT_STORE).navigation();;
        fragments[2] = (PersonFragment)ARouter.getInstance().build(ArouterPath.FRAGMENT_PERSON).navigation();;

        imageViews = new ImageView[3];
        imageViews[0] =  findViewById(R.id.iv_home);
        imageViews[1] =  findViewById(R.id.iv_hospital);
        imageViews[2] =  findViewById(R.id.iv_mine);
        imageViews[0].setImageResource(R.drawable.app_home_selected);
        textViews = new TextView[3];
        textViews[0] =  findViewById(R.id.tv_home);
        textViews[1] =  findViewById(R.id.tv_hospital);
        textViews[2] =  findViewById(R.id.tv_mine);
        textViews[0].setTextColor(getBaseContext().getResources().getColorStateList(R.color.word_theme_color));
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragments[0])
                .add(R.id.fragment_container, fragments[1])
                .add(R.id.fragment_container,fragments[2])
                .hide(fragments[1]).hide(fragments[2])
                .show(fragments[0]).commit();

        RelativeLayout reHome = findViewById(R.id.re_home);
        reHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchTab(0);
                switchFragment();
            }
        });

        RelativeLayout reDiscovery = findViewById(R.id.re_hospital);
        reDiscovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchTab(1);
                switchFragment();
            }
        });
        RelativeLayout reMine = findViewById(R.id.re_mine);
        reMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(User.getUser()!=null&& SPManager.getInstance().getBoolean("isLoginValid",false)){
                    switchTab(2);
                    switchFragment();
                }else{
                    ArouterHelper.gotoLoginActivity();
                }

            }
        });

    }
    public void switchTab(int index){
        this.index = index;
        for(int i=0;i<3;i++){
            // 切换图标
            if(i==0){
                if(i==index){
                    imageViews[i].setImageResource(R.drawable.app_home_selected);
                }else{
                    imageViews[i].setImageResource(R.drawable.app_home_unselected);
                }
            }else if(i==1){
                if(i==index){
                    imageViews[i].setImageResource(R.drawable.app_hospital_selected);
                }else{
                    imageViews[i].setImageResource(R.drawable.app_hospital_unselected);
                }
            }else{
                if(i==index){
                    imageViews[i].setImageResource(R.drawable.app_mine_selected);
                }else{
                    imageViews[i].setImageResource(R.drawable.app_mine_unselected);
                }
            }
            // 更换字体颜色
            if(i==index){
                textViews[i].setTextColor(getBaseContext().getResources().getColorStateList(R.color.blue));
            }else{
                textViews[i].setTextColor(getBaseContext().getResources().getColorStateList(R.color.word_theme_color));

            }
        }
    }
    public void switchFragment(){
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commitAllowingStateLoss();
        }
        currentTabIndex = index;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(!SPManager.getInstance().getBoolean("isLoginValid",false)&&currentTabIndex==2){
            switchTab(0);
            switchFragment();
        }
    }
}
