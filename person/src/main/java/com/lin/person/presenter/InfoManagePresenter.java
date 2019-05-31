package com.lin.person.presenter;

import com.lin.baselib.base.BasePresenter;
import com.lin.baselib.network.BaseObserver;
import com.lin.baselib.network.BaseResponse;
import com.lin.person.api.PersonHttpClient;
import com.lin.person.entity.UserDTO;
import com.lin.person.view.InfoManageView;

import okhttp3.MultipartBody;

/**
 * Created by lin on 2019/5/10.
 */

public class InfoManagePresenter extends BasePresenter<InfoManageView> {
    public InfoManagePresenter(InfoManageView baseView) {
        super(baseView);
    }

    public void changeAvatar(Long userId, MultipartBody.Part file) {
       addDisposable(PersonHttpClient.changeAvatar(userId, file), new BaseObserver<BaseResponse<String>>(baseView) {
           @Override
           public void onSuccess(BaseResponse<String> response) {
               baseView.saveAvatar(response.getData());
           }
       });
    }

    public void save(UserDTO userDTO) {
        addDisposable(PersonHttpClient.changeInfo(userDTO), new BaseObserver<BaseResponse>(baseView) {
            @Override
            public void onSuccess(BaseResponse response) {
                baseView.showToast("保存成功");
                baseView.updateUser();
            }
        });
    }
}
