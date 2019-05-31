package com.lin.person.presenter;

import com.lin.baselib.base.BasePresenter;
import com.lin.baselib.network.BaseObserver;
import com.lin.baselib.network.BaseResponse;
import com.lin.person.api.PersonHttpClient;
import com.lin.person.entity.CommentDTO;
import com.lin.person.view.CommentView;

/**
 * Created by lin on 2019/5/14.
 */

public class CommentPresenter extends BasePresenter<CommentView>{
    public CommentPresenter(CommentView baseView) {
        super(baseView);
    }

    public void comment(CommentDTO commentDTO) {
      addDisposable(PersonHttpClient.commentPreorder(commentDTO), new BaseObserver<BaseResponse>(baseView) {
          @Override
          public void onSuccess(BaseResponse response) {
              baseView.showToast("评论成功");
              baseView.finishActivity();
          }
      });
    }
}
