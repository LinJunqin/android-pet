package com.lin.login.presenter;


import com.lin.baselib.base.BasePresenter;
import com.lin.baselib.db.SPManager;
import com.lin.baselib.entity.User;
import com.lin.baselib.network.BaseObserver;
import com.lin.baselib.network.BaseResponse;
import com.lin.baselib.util.string.StringUtil;
import com.lin.login.api.LoginHttpClient;
import com.lin.login.entity.UserDTO;
import com.lin.login.view.LoginView;

/**
 * Created by lin on 2018/9/18.
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(LoginView baseView) {
        super(baseView);
    }

    public void initData() {

    }
    public void getCode(String phone){
        if(!StringUtil.isMobile(phone)){
            baseView.showToast("手机号码格式不正确");
            return;
        }
        baseView.refreshCodeBtn();
      addDisposable(LoginHttpClient.sendCode(phone), new BaseObserver<BaseResponse>(baseView) {
          @Override
          public void onSuccess(BaseResponse response) {
              baseView.refreshLoginBtn();
              baseView.showToast("发送成功");
          }
      });

    }
    /**
     * 登录
     * @param phone
     * @param code
     */
    public void login(String phone, String code) {
        addDisposable(LoginHttpClient.login(phone, code), new BaseObserver<BaseResponse<UserDTO>>(baseView) {
            @Override
            public void onSuccess(BaseResponse<UserDTO> response) {
                UserDTO user = response.getData();
                User.init(user.getUserId(),user.getPhone(),user.getUsername(),user.getGender(),user.getAvatar());
                SPManager.getInstance().putLong("userId",user.getUserId());
                SPManager.getInstance().putString("phone",user.getPhone());
                SPManager.getInstance().putString("username",user.getUsername());
                SPManager.getInstance().putBoolean("gender",user.getGender());
                SPManager.getInstance().putString("avatar",user.getAvatar());
                SPManager.getInstance().putBoolean("isLoginValid",true);
                baseView.showToast("登陆成功");
                baseView.finishActivity();
            }
        });
    }
}
