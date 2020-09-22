package com.sdkj.heaterbluetooth.app;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * 应用程序配置类：用于保存用户相关信息及设置
 */
@SuppressLint("NewApi")
public class AppConfig {


    public static String IS_FIRST_RUN = "0x1112";//是否首次进入app 十分重要 true 是首次 false 不是首次
    public static String DefaultCity = "哈尔滨市";
    public static boolean ADSTATE = true;
    public static String SCREEN = "-1";//检索预约
    public static String SORT = "-1"; //检索预约
    /**
     * 是否已授权 0.未授权 1.授权
     */
    public final static String AUTHORIZATIONFLAG = "authorizationFlag";
    /**
     * 治疗状态 1-治疗中 3-治疗结束
     */
    public final static String CURESTATE = "curestate";
    /**
     * 服务治疗名称
     */
    public final static String TREATMENTNAME = "treatmentname";
    /**
     * 服务店铺名称
     */
    public final static String COMPANYNAME = "companyname";
    /**
     * 到店时间
     */
    public final static String ARRIVETIME = "arrivetime";
    /**
     * 到店上午或者下午
     */
    public final static String AFTERNOONORMORNING = "afternoonOrMorning";
    /**
     * id
     */
    public final static String ID = "id";

    /**
     * companyId
     */
    public final static String COMPANYID = "companyid";

    private final static String APP_CONFIG = "config";
    public final static String CONF_FIRST_RUN = "perf_first_run";
    public final static String CONF_VERSION_LOOK = "version_look";
    public final static String WELCOME_PAGER = "welcome_pager";
    public final static String TEMP_TWEET_IMAGE = "temp_tweet_image";
    public final static String TEMP_MESSAGE = "temp_message";
    public final static String TEMP_COMMENT = "temp_comment";
    public final static String TEMP_POST_TITLE = "temp_post_title";
    public final static String TEMP_POST_CATALOG = "temp_post_catalog";
    public final static String TEMP_POST_CONTENT = "temp_post_content";
    public final static String CONF_APP_UNIQUEID = "APP_UNIQUEID";
    public final static String CONF_COOKIE = "cookie";
    public final static String CONF_ACCESSTOKEN = "accessToken";
    public final static String CONF_ACCESSSECRET = "accessSecret";
    public final static String CONF_EXPIRESIN = "expiresIn";
    public final static String CONF_LOAD_IMAGE = "perf_loadimage";
    public final static String CONF_SCROLL = "perf_scroll";
    public final static String CONF_HTTPS_LOGIN = "perf_httpslogin";
    public final static String CONF_VOICE = "perf_voice";
    public final static String CONF_CHECKUP = "perf_checkup";
    public final static String CONF_CITY = "perf_city";//本地手动选择存储的城市名
    public final static String CONF_LOCATION_CITY = "perf_location_city";//定位的城市名
    public final static String CONF_LOCATION_CITYADDRESS = "perf_location_city_address";//定位的城市地址
    public final static String CONF_LOCATION_LATITUDE = "perf_location_latitude";
    public final static String CONF_LOCATION_LONTITUDE = "perf_location_lotitude";
    public final static String CONF_LONGITUDE = "perf_longitude";//经度
    public final static String CONF_LATITUDE = "perf_latitude";//纬度
    public final static String CONF_CITYVERSION = "perf_city_version";//本地存储数据库版本
    public final static String SAVE_IMAGE_PATH = "save_image_path";
    public final static String CHANGETIME = "change_time";//首页提示切换时间
    public final static String JINGWEIDU = "jingweidu";//判断经纬度和选择城市是否相同  -- 0 不相同  1  相同
    public static boolean resumeFlag = true;//定位需求修改了-- 退出客户端  再进入才提示 切换城市  字段定义在系统级别
    public static String longClickAddress = "长按可设置您的位置";
    public static String ShopListOrmap = "shopList";//"shoplist" 是门店列表页面  "map" 是地图页面
    public static boolean ifFirstEnter = true;//true 第一进入  false
    // public static boolean ADVERTISEFLAG = true;
    public static boolean SHOPLISTRIGHTICONFLAG = true;//门店页面-右侧按钮是否可以点击  (需求修改不可点击)
    public static boolean isLocateFlag = true;//定位相关 ： 第一次进入才进行定位
    public static String chooseOrderState = null;//订单状态
    public static String CONF_ORDERID = "orderId";//正常订单id
    public static String CONF_MAILORDERID = "mailOrderId";//包邮的订单id
    public static String CONF_EXCENTVIDEO = "0";//0不是从浏览器进入，1 是从浏览器进入
    public final static String FIRSTINSTALL = "FIRSTINSTALL";
    public final static String FIRSTINSTALLTIME = "FIRSTINSTALLTIME";
    public static boolean showAppAccess = true;//是否展示评论
    public final static String showAppAccessthis = "showAppAccessthis";
    public final static String LOCATION_CITY_NAME = "LOCATION_CITY_NAME";
    public final static String PEIWANG_FAMILYID = "PEIWANGFAMILYID";//智能家居 家庭id
    public final static String PEIWANG_MIMA = "PEIWANG_MIMA";
    public final static String SMS_ID = "SMS_ID";//smsId;//临时使用
    public final static String SMS_CODE = "SMS_CODE";//临时使用
    public final static String RONGYUN_TOKEN = "RONGYUN_TOKEN";
    //福利功能  需要的字段
    //作废时间  --待使用
    public static String abolishDate;
    //取消时间  --待支付
    public static String cancelDate;

    public static String payCancellDate;
    //400电话
    public static String phone400;

    public static String EXTERNAL_DIR = "smart";
    public static String SOURCE = "consumer-app";
    public static String IMG_LINK = "@!";
    public static String IMG_SOURCE = IMG_LINK + "source";
    public static String IMG_W200 = IMG_LINK + "W200";
    public static String IMG_W200_AUADRATE = IMG_LINK + "W200_quadrate";
    public static String IMG_W400 = IMG_LINK + "W400";
    public static String IMG_W400_AUADRATE = IMG_LINK + "W400_quadrate";
    public static String IMG_W450_AUADRATE = IMG_LINK + "W450_quadrate";
    public static String IMG_W600 = IMG_LINK + "W600";
    public static String IMG_W800 = IMG_LINK + "W800";
    public static String IMG_W450 = IMG_LINK + "W450";
    public static String IMG_NO_STYLE = IMG_LINK + "no_style";
    public static int SETSELECTION = 0;
    @SuppressLint("NewApi")
    public final static String DEFAULT_SAVE_IMAGE_PATH = Environment.getExternalStorageDirectory() + File.separator + EXTERNAL_DIR + File.separator + "Image";

    /**
     * 工作台对应名字
     */
    public final static String COMPANY_APP = "company_app";
    public final static String UNDERLINE = "_";
    public final static String AGENT = "dls";//代理商
    public final static String SHOPOWNER = "dz";//店长
    public final static String STAFF = "yg";//员工
    public final static String AGENT_ROLEID = "1";//代理商
    public final static String SHOPOWNER_ROLEID = "2";//店长
    public final static String STAFF_ROLEID = "3";//员工
    public final static String PRODUCT = "1";//货品展架
    public final static String PURCHASEORDERS = "2";//进货订单
    public final static String REATMENTMANAGEMENT = "3";//治疗管理
    public final static String BOOKINGMANAGEMENT = "4";//预约管理
    public final static String GIFTMANAGERMENT = "5";//礼品活动
    public final static String LATESTACTIVITY = "6";//最新活动
    public final static String CUSTOMERMANAGEMEN = "7";//客户管理
    public final static String STOREMAINTENANCE = "8";//店铺维护
    public final static String STAFFMANAGEMENT = "9";//员工管理
    public final static String HELPTRAINING = "10";//帮助培训
    public final static String PROMOTION = "11";//宣传推广
    public final static String STAFFAPPRAISALRECORDS = "12";//员工考核记录
    public final static String INDEXMAINTENANCE = "13";//员工考核指标维护
    public final static String ADDRESS = "14";//收货地址列表
    private Context mContext;
    private volatile static AppConfig appConfig; //声明成 volatile

    private AppConfig() {
    }

    public static AppConfig getAppConfig(Context context) {
        if (appConfig == null) {
            synchronized (AppConfig.class) {
                if (appConfig == null) {
                    appConfig = new AppConfig();
                    appConfig.mContext = context;
                }
            }
        }
        return appConfig;
    }


    /**
     * 获取Preference设置
     */
    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * 是否加载显示文章图片
     */
    public static boolean isLoadImage(Context context) {
        return getSharedPreferences(context).getBoolean(CONF_LOAD_IMAGE, true);
    }

    public void setAccessToken(String accessToken) {
        set(CONF_ACCESSTOKEN, accessToken);
    }

    public String getAccessToken() {
        return get(CONF_ACCESSTOKEN);
    }

    public String get(String key) {
        Properties props = get();
        return (props != null) ? props.getProperty(key) : null;
    }

    public Properties get() {
        FileInputStream fis = null;
        Properties props = new Properties();
        try {
            // 读取files目录下的config
            // fis = activity.openFileInput(APP_CONFIG);

            // 读取app_config目录下的config
            File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
            fis = new FileInputStream(dirConf.getPath() + File.separator
                    + APP_CONFIG);

            props.load(fis);
        } catch (Exception e) {
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return props;
    }

    private void setProps(Properties p) {
        FileOutputStream fos = null;
        try {
            // 把config建在files目录下
            // fos = activity.openFileOutput(APP_CONFIG, Context.MODE_PRIVATE);

            // 把config建在(自定义)app_config的目录下
            File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
            File conf = new File(dirConf, APP_CONFIG);
            fos = new FileOutputStream(conf);

            p.store(fos, null);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    public void set(Properties ps) {
        Properties props = get();
        props.putAll(ps);
        setProps(props);
    }

    public void set(String key, String value) {
        Properties props = get();
        props.setProperty(key, value);
        setProps(props);
    }

    public void remove(String... key) {
        Properties props = get();
        for (String k : key)
            props.remove(k);
        setProps(props);
    }

    /**
     * 基本权限管理
     */
    public static final String[] BASIC_PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,

    };
    /**
     * 基本定位权限管理
     */
    public static final String[] BASIC_PERMISSIONS_LOCATION = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    /**
     * 基本存储权限管理
     */
    public static final String[] BASIC_PERMISSIONS_STORAGE = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };


    public static final String[] BASIC_PERMISSIONS_HEARTERBLUE = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };
}
