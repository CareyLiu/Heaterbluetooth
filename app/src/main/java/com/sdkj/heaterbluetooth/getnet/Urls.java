package com.sdkj.heaterbluetooth.getnet;

//网络接口地址
public class Urls {
    public static String key = "20180305124455yu";//全局请求key
     //public static String SERVER_URL = "https://shop.hljsdkj.com/";//基本地址
    //public static String SERVER_URL = "http://192.168.1.122:8080/";//大志本地
    //public static String SERVER_URL = "http://192.168.1.127:8080/";//大ge本地
    public static String SERVER_URL = "https://test.hljsdkj.com/";//测试地址

    public final static String code_00001 = "00001";//发送验证码
    public final static String code_04310 = "04310";//登录接口
    public static String APP = SERVER_URL + "shop_new/app/";//APP端
    public static String WORKER = APP + "worker/";//卖家端
    public static String LOGIN = WORKER + "login";//卖家端登录接口
    public static String MESSAGE_URL = SERVER_URL + "wit/app/user";
    public static String HOME_PICTURE = SERVER_URL + "shop_new/app";
    public static String LIBAOLIST = SERVER_URL + "xc/app/us";//获取大礼包接口
    public static String TAOKELIST = SERVER_URL + "shop_taobaoke/app/user ";
    public static String MSG = SERVER_URL + "msg";
    public static String DALIBAO_PAY = SERVER_URL + "msg/pay/create";//大礼包支付
    public static String ZIJIANHOME = SERVER_URL + "shop_new/app";//自建商城
    public static String ZIYINGFENLEI = SERVER_URL + "shop_new/app";//自建分类
    public static String JDWEB = "https://store.ixiaocong.net/#/index/Z66Q3A";//京东的连接
    public static String PDDWEB = "https://u.jd.com/SgYbff";//京东的连接
    public static String HOME_PICTURE_HOME = SERVER_URL + "shop_new/app/user";
    public static String WIT_APP = SERVER_URL + "wit/app";
    public static String ZHINENGJIAJU = SERVER_URL + "znjj/app/user";//智能家居
    public static String BAZIAPP = SERVER_URL + "bz/app";//八紫APP接口
    public static String BAZIAPPUSER = SERVER_URL + "bz/app/user";//八紫APP接口an jiareqi
    public static String BAZIPAYOK = SERVER_URL + "msg/pay/createbz/ok";//大礼包支付
    public static String ARGEMENTS = SERVER_URL + "shop_new/user_agreement";// ARGEMENTS
    public static String PAYSUCCESS = SERVER_URL + "msg/pay/create/ok";//支付成功
    public static String DINGSHI = SERVER_URL + "fn/common";//定时


    public static String USER = SERVER_URL + "manange/cd_user";

}
