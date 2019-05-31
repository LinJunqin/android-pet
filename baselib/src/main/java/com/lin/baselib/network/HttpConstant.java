package com.lin.baselib.network;

/**
 * Created by lin on 2018/9/18.
 */

public class HttpConstant {
    /**
     * 请求路径
     *
     */
    public static final String BASE_SERVER_URL = "http://53fcb6f1.ngrok.io/";
    /**
     * 请求超时时间 单位 秒
     */
    public static final int DEFAULT_TIMEOUT = 10;

    /**
     * 请求成功
     */
    public static final int SUCCESS = 10001;
    /**
     * 解析数据失败
     */
    public static final int PARSE_ERROR = 1001;
    public static final String PARSE_ERROR_MSG = "解析数据失败";

    /**
     * 网络问题
     */
    public static final int BAD_NETWORK = 1002;
    public static final String BAD_NETWORK_MSG = "网络出错";

    /**
     * 连接错误
     */
    public static final int CONNECT_ERROR = 1003;
    public static final String CONNECT_ERROR_MSG = "连接错误";
    /**
     * 连接超时
     */
    public static final int CONNECT_TIMEOUT = 1004;
    public static final String CONNECT_TIMEOUT_MSG = "连接超时";
    /*
    * 未知错误
    */
    public static final String UNKNOWN = "未知错误";
}
