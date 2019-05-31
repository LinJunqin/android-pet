package com.lin.baselib.network;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.lin.baselib.base.BaseView;
import com.lin.baselib.util.LogUtils;


import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;



/**
 * Created by lin on 2018/9/18.
 */

/**
 * 订阅者
 */


public abstract class BaseObserver<T> extends DisposableObserver<T> {
    protected BaseView view;


    public BaseObserver(BaseView view) {
        this.view = view;
    }

    @Override
    protected void onStart() {
        if (view != null) {
            LogUtils.d("rerquest_start","yes");
            view.showLoading();
        }
    }

    @Override
    public void onNext(T o) {
        try {
            BaseResponse response = (BaseResponse) o;
            if (response.getStatus() ==10001) {
                onSuccess(o);
            } else {
                if (view != null) {
                    view.onError(response.getMsg());// 服务器返回码code = 200；但是数据验证不通过
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            onError(e.toString());// 解析出错,数据格式不符合规范
        }
    }

    @Override
    public void onError(Throwable e) {
        if (view != null) {
            view.hideLoading();
        }
        if (e instanceof HttpException) {
            //   HTTP错误
            onException(HttpConstant.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            //   连接错误
            onException(HttpConstant.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {
            //  连接超时
            onException(HttpConstant.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //  解析错误
            onException(HttpConstant.PARSE_ERROR);
        } else {
            if (e != null) {
                onError(e.toString());
            } else {
                onError(HttpConstant.UNKNOWN);
            }
        }

    }

    private void onException(int unknownError) {
        switch (unknownError) {
            case HttpConstant.CONNECT_ERROR:
                onError(HttpConstant.CONNECT_ERROR_MSG);
                break;

            case HttpConstant.CONNECT_TIMEOUT:
                onError(HttpConstant.CONNECT_ERROR_MSG);
                break;

            case HttpConstant.BAD_NETWORK:
                onError(HttpConstant.BAD_NETWORK_MSG);
                break;

            case HttpConstant.PARSE_ERROR:
                onError(HttpConstant.PARSE_ERROR_MSG);
                break;

            default:
                break;
        }
    }


    @Override
    public void onComplete() {
        if (view != null) {
            LogUtils.d("rerquest_finished","yes");
            view.hideLoading();
        }
    }

    public abstract void onSuccess(T response);

    public  void onError(String msg){
        LogUtils.d("网络出错",msg);
        view.showToast(msg);
    }
}

