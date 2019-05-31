package com.lin.baselib.arouter;

/**
 * Created by lin on 2018/9/28.
 */

public final class ArouterPath {

    //主组件
    public static final String GROUP_APP = "app";
    public static final String ACTIVITY_MAIN = "/app/MainActivity";
    public static final String ACTIVITY_SPLASH = "/app/SplashActivity";




    //个人中心组件
    public static final String GROUP_PERSON = "person";
    public static final String FRAGMENT_PERSON = "/person/PersonFragment";
    public static final String ACTIVITY_FEEDBACK = "/person/FeedbackActivity";
    public static final String ACTIVITY_INFOMANAGE = "/person/InfoManageActivity";
    public static final String ACTIVITY_ORDER = "/person/OrderActivity";
    public static final String ACTIVITY_ORDER_DETAIL = "/person/OrderDetailActivity";
    public static final String ACTIVITY_PRE_ORDER = "/person/PreOrderActivity";
    public static final String ACTIVITY_PRE_ORDER_DETAIL = "/person/PreOrderDetailActivity";
    public static final String ACTIVITY_SETTING= "/person/SettingActivity";
    public static final String ACTIVITY_ADDRESS = "/person/AddressActivity";
    public static final String ACTIVITY_ADDRESS_ADD = "/person/AddNewAddressActivity";
    public static final String ACTIVITY_ADDRESS_MODIFY= "/person/ModifyActivity";
    public static final String ACTIVITY_COMMENT= "/person/CommentActivity";

    // 登录组件
    public static final String GROUP_LOGIN = "person";
    public static final String ACTIVITY_LOGIN = "/login/LoginActivity";

    //商品订单组件
    public static final String GROUP_GOODS = "goods";
    public static final String FRAGMENT_HOME = "/goods/HomeFragment";
    public static final String ACTIVITY_GOODS_DETAIL = "/goods/GoodsDetailActivity";
    public static final String ACTIVITY_GOODS_ORDER = "/goods/GoodsOrderActivity";
    public static final String ACTIVITY_PAYMENT = "/goods/PaymentActivity";
    public static final String ACTIVITY_PAY_RESULT = "/goods/PayResultActivity";
    public static final String ACTIVITY_SEARCH = "/goods/SearchActivity";
    public static final String ACTIVITY_SEARCH_RESULT = "/goods/SearchResultActivity";

    //门店组件
    public static final String GROUP_STORE = "store";
    public static final String FRAGMENT_STORE = "/store/StoreFragment";
    public static final String ACTIVITY_STORE_SEARCH = "/store/SearchActivity";
    public static final String ACTIVITY_STORE_SEARCH_RESULT = "/store/SearchResultActivity";
    public static final String ACTIVITY_STORE_DETAIL = "/store/StoreDetailActivity";


}
