package com.lin.baselib.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lin.baselib.util.LogUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by lin on 2018/9/18.
 */

public class ApiCreater {

    private static ApiCreater apiCreater;
    private Retrofit retrofit;
    private OkHttpClient client;

    private String TAG = "ApiCreater";

    /**
     * 网络拦截器
     *
     */
    private Interceptor requestLog = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.currentTimeMillis();
            Response response = chain.proceed(chain.request());
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            LogUtils.e(TAG, "----------Request Start----------------");
            LogUtils.e(TAG, "| " + request.toString() + request.headers().toString());
            LogUtils.e(TAG, "| Response:" + content);
            LogUtils.e(TAG, "----------Request End:" + duration + "毫秒----------");
            return response.newBuilder()
                    .body(ResponseBody.create(mediaType, content))
                    .build();
        }
    };


    public ApiCreater() {
        client = new OkHttpClient.Builder()
                //添加log拦截器
                .addInterceptor(requestLog)
                .connectTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)//连接超时时间
                .readTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(HttpConstant.DEFAULT_TIMEOUT,TimeUnit.SECONDS)
                .build();
         final Gson gson  = new GsonBuilder()
                .setLenient()  // 设置GSON的非严格模式setLenient()
                .create();
       //默认参数
        retrofit = new Retrofit.Builder()
                .baseUrl(HttpConstant.BASE_SERVER_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                //支持RxJava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

    }

    public static ApiCreater getInstance() {
        if (apiCreater == null) {
            synchronized (ApiCreater.class) {
                if (apiCreater == null) {
                    apiCreater = new ApiCreater();
                }
            }
        }
        return apiCreater;
    }
    public <T> T create(Class<T> t) {
        return retrofit.create(t);
    }
}

