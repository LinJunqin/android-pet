package com.lin.baselib.arouter;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by lin on 2018/9/28.
 */

public class ArouterHelper {
    //登录模块
    public static void gotoLoginActivity(){
        ARouter.getInstance().build(ArouterPath.ACTIVITY_LOGIN).navigation();
    }

    public static void gotoSearchActivity(){
        ARouter.getInstance().build(ArouterPath.ACTIVITY_SEARCH).navigation();
    }
    public static void gotoSearchResultActivity(){
        ARouter.getInstance().build(ArouterPath.ACTIVITY_SEARCH_RESULT).navigation();
    }
    public static void gotoStoreSearchActivity(){
        ARouter.getInstance().build(ArouterPath.ACTIVITY_STORE_SEARCH).navigation();
    }
    public static void gotoStoreSearchResultActivity(){
        ARouter.getInstance().build(ArouterPath.ACTIVITY_STORE_SEARCH_RESULT).navigation();
    }
    public static void gotoFeedbackActivity(){
        ARouter.getInstance().build(ArouterPath.ACTIVITY_FEEDBACK).navigation();
    }
    public static void gotoAddressActivity(){
        ARouter.getInstance().build(ArouterPath.ACTIVITY_ADDRESS).navigation();
    }
    public static void gotoAddAddressActivity(){
        ARouter.getInstance().build(ArouterPath.ACTIVITY_ADDRESS_ADD).navigation();
    }
    public static void gotoModifyAddressActivity(Long addressId){
        ARouter.getInstance().build(ArouterPath.ACTIVITY_ADDRESS_MODIFY).withLong("addressId",addressId).navigation();
    }
    public static void gotoInfoManageActivity(){
        ARouter.getInstance().build(ArouterPath.ACTIVITY_INFOMANAGE).navigation();
    }
    public static void gotoSettingActivity(){
        ARouter.getInstance().build(ArouterPath.ACTIVITY_SETTING).navigation();
    }
    public static void gotoOrderActivity(){
        ARouter.getInstance().build(ArouterPath.ACTIVITY_ORDER).navigation();
    }

    public static void gotoOrderDetailActivity(){
        ARouter.getInstance().build(ArouterPath.ACTIVITY_ORDER_DETAIL).navigation();
    }
    public static void gotoPreOrderActivity(){
        ARouter.getInstance().build(ArouterPath.ACTIVITY_PRE_ORDER).navigation();
    }
    public static void gotoPreOrderDetailActivity(){
        ARouter.getInstance().build(ArouterPath.ACTIVITY_PRE_ORDER_DETAIL).navigation();
    }
    public static void gotoCommentActivity(){
        ARouter.getInstance().build(ArouterPath.ACTIVITY_COMMENT).navigation();
    }

    public static void gotoStoreDetailActivity(Long hospitalId) {
        ARouter.getInstance().build(ArouterPath.ACTIVITY_STORE_DETAIL).withLong("hospitalId",hospitalId).navigation();
    }

    public static void gotoGoodsOrderActivity() {
        ARouter.getInstance().build(ArouterPath.ACTIVITY_GOODS_ORDER).navigation();
    }
    public static void gotoGoodsDetailActivity() {
        ARouter.getInstance().build(ArouterPath.ACTIVITY_GOODS_DETAIL).navigation();
    }
    public static void gotoPaymentActivity() {
        ARouter.getInstance().build(ArouterPath.ACTIVITY_PAYMENT).navigation();
    }
    public static void gotoPayResultActivity() {
        ARouter.getInstance().build(ArouterPath.ACTIVITY_PAY_RESULT).navigation();
    }
    public static void gotoMainActivity() {
        ARouter.getInstance().build(ArouterPath.ACTIVITY_MAIN).navigation();
    }
}
