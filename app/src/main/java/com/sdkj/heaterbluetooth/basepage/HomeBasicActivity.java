package com.sdkj.heaterbluetooth.basepage;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationQualityReport;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.activity.DiagnosisActivity;
import com.sdkj.heaterbluetooth.app.App;
import com.sdkj.heaterbluetooth.app.AppConfig;
import com.sdkj.heaterbluetooth.app.AppManager;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.app.ConstanceValue;
import com.sdkj.heaterbluetooth.app.Notice;
import com.sdkj.heaterbluetooth.app.PreferenceHelper;
import com.sdkj.heaterbluetooth.basicmvp.BasicFragment;
import com.sdkj.heaterbluetooth.dialog.MyCarCaoZuoDialog_Notify;
import com.sdkj.heaterbluetooth.fragment.ShebeiFrament;
import com.sdkj.heaterbluetooth.fragment.ShuoMingFragment;
import com.sdkj.heaterbluetooth.fragment.WoDeFragment;
import com.sdkj.heaterbluetooth.model.AlarmClass;
import com.sdkj.heaterbluetooth.util.DoMqttValue;
import com.sdkj.heaterbluetooth.util.SoundPoolUtils;
import com.sdkj.heaterbluetooth.util.Utils;
import com.sdkj.heaterbluetooth.view.BottomBar;
import com.sdkj.heaterbluetooth.view.BottomBarTab;
import com.sdkj.heaterbluetooth.view.DoubleClickExitHelper;

import static com.sdkj.heaterbluetooth.app.App.JINGDU;
import static com.sdkj.heaterbluetooth.app.App.WEIDU;
import static com.sdkj.heaterbluetooth.app.MyApplication.getAppContext;


public class HomeBasicActivity extends BaseActivity {

    private static final String tag = HomeBasicActivity.class.getSimpleName();

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;

    private final int VISIBLESTATE = 1;//可见
    private final int INVISIBLESTATE = 0;//不可见

    public static BottomBar mBottomBar;
    private BasicFragment[] mFragments = new BasicFragment[5];
    DoubleClickExitHelper doubleClick;
    private App app;
    public static HomeBasicActivity ac;
    AlarmClass alarmClass;

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, HomeBasicActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_home_basic;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        overridePendingTransition(R.anim.anim_bottom_in, R.anim.anim_bottom_out);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.anim_bottom_in, R.anim.anim_bottom_out);
        ac = this;
        BasicFragment firstFragment = findFragment(ShebeiFrament.class);
        if (firstFragment == null) {
            mFragments[FIRST] = ShebeiFrament.newInstance();
            mFragments[SECOND] = ShuoMingFragment.newInstance();
            mFragments[THIRD] = WoDeFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container1, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD]);
        }

        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mBottomBar.show();
        mBottomBar
                .addItem(new BottomBarTab(this, R.mipmap.tab_icon_shebei_nor, R.mipmap.tab_icon_shebei_sel, "设备"))
                .addItem(new BottomBarTab(this, R.mipmap.tab_icon_shuomingshu_nor, R.mipmap.tab_icon_shuomingshu_sel, "说明"))
                .addItem(new BottomBarTab(this, R.mipmap.tab_icon_wode_nor, R.mipmap.tab_icon_wode_sel, "我的"));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                switch (position) {

                }
                // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
                // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                // EventBusActivityScope.getDefault(MainActivity.this).post(new TabSelectedEvent(position));
            }
        });
        app = App.getInstance();
        doubleClick = new DoubleClickExitHelper(this);

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice notice) {
                if (notice.type == ConstanceValue.MSG_GUZHANG_SHOUYE) {

                    String xiangmu = PreferenceHelper.getInstance(mContext).getString(App.CHOOSE_KONGZHI_XIANGMU, "");

                    if (!StringUtils.isEmpty(xiangmu)){
                        return;
                    }

                    String message = (String) notice.content;
                    Gson gson = new Gson();
                    alarmClass = gson.fromJson(message.toString(), AlarmClass.class);
                    Log.i("alarmClass", alarmClass.changjia_name + alarmClass.sell_phone);
//
//                    if (player != null) {
//                        player.stop();
//                        player.release();
//                        audioFocusManage.releaseTheAudioFocus();
//                        player = null;
//                    }


                    switch (alarmClass.sound) {

                        case "chSound1.mp3":
                            // SoundPoolUtils.soundPool(mContext,R.raw.ch_sound1);
                            playMusic(R.raw.ch_sound1);
                            break;
                        case "chSound2.mp3":
                            playMusic(R.raw.ch_sound2);
                            break;
                        case "chSound3.mp3":
                            playMusic(R.raw.ch_sound3);
                            break;
                        case "chSound4.mp3":
                            playMusic(R.raw.ch_sound4);
                            break;
                        case "chSound5.mp3":
                            playMusic(R.raw.ch_sound5);
                            break;
                        case "chSound6.mp3":
                            playMusic(R.raw.ch_sound6);
                            break;
                        case "chSound8.mp3":
                            playMusic(R.raw.ch_sound8);
                            break;
                        case "chSound9.mp3":
                            playMusic(R.raw.ch_sound9);
                            break;
                        case "chSound10.mp3":
                            playMusic(R.raw.ch_sound10);
                            break;
                        case "chSound11.mp3":
                            playMusic(R.raw.ch_sound11);
                            break;
                        case "chSound18.mp3":
                            playMusic(R.raw.ch_sound18);
                            break;
                    }
                } else if (notice.type == ConstanceValue.MSG_LOGIN) {
                    mBottomBar.setCurrentItem(2);
                }
            }
        }));

        //初始化定位
        initLocation();
        startLocation();

    }
    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;

    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(mContext);
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(gaodeDingWeiListener);
    }

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        //根据控件的选择，重新设置定位参数
        //resetOption();
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }
    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        app = App.getInstance();
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            /*
             * 注意在if判断中要加一个event.getAction() == KeyEvent.ACTION_DOWN判断，
             * 因为按键有两个事件ACTION_DOWN和ACTION_UP，也就是按下和松开，
             * 如果不加这个判断，代码会执行两遍
             */
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                //exitApp();
                doubleClick.onKeyDown(event.getKeyCode(), event);
            }
            //实现只在冷启动时显示启动页，即点击返回键与点击HOME键退出效果一致
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            startActivity(intent);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    public void playMusic(int res) {
        boolean flag = false;

        Activity currentActivity = AppManager.getAppManager().currentActivity();
        if (currentActivity != null) {
            if (!currentActivity.getClass().getSimpleName().equals(DiagnosisActivity.class.getSimpleName())) {
                MyCarCaoZuoDialog_Notify myCarCaoZuoDialog_notify = new MyCarCaoZuoDialog_Notify(getAppContext(), new MyCarCaoZuoDialog_Notify.OnDialogItemClickListener() {
                    @Override
                    public void clickLeft() {
                        // player.stop();
                        if (SoundPoolUtils.soundPool != null) {
                            SoundPoolUtils.soundPool.release();
                        }

                    }

                    @Override
                    public void clickRight() {
                        DiagnosisActivity.actionStart(HomeBasicActivity.this, alarmClass);
                        //SoundPoolUtils.soundPool.release();
                        if (SoundPoolUtils.soundPool != null) {
                            SoundPoolUtils.soundPool.release();
                        }

                    }
                }
                );

                myCarCaoZuoDialog_notify.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG);
                myCarCaoZuoDialog_notify.show();
                myCarCaoZuoDialog_notify.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (SoundPoolUtils.soundPool != null) {
                            SoundPoolUtils.soundPool.release();
                        }
                    }
                });

            } else {
                flag = true;
            }
        }

        if (flag) {
            return;
        }

        SoundPoolUtils.soundPool(mContext, res);

    }

    /**
     * 定位监听
     */
    AMapLocationListener gaodeDingWeiListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {

                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    sb.append("定位成功" + "\n");
                    sb.append("定位类型: " + location.getLocationType() + "\n");
                    sb.append("经    度    : " + location.getLongitude() + "\n");
                    sb.append("纬    度    : " + location.getLatitude() + "\n");
                    sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
                    sb.append("提供者    : " + location.getProvider() + "\n");

                    sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                    sb.append("角    度    : " + location.getBearing() + "\n");
                    // 获取当前提供定位服务的卫星个数
                    sb.append("星    数    : " + location.getSatellites() + "\n");
                    sb.append("国    家    : " + location.getCountry() + "\n");
                    sb.append("省            : " + location.getProvince() + "\n");
                    sb.append("市            : " + location.getCity() + "\n");
                    sb.append("城市编码 : " + location.getCityCode() + "\n");
                    sb.append("区            : " + location.getDistrict() + "\n");
                    sb.append("区域 码   : " + location.getAdCode() + "\n");
                    sb.append("地    址    : " + location.getAddress() + "\n");
                    sb.append("兴趣点    : " + location.getPoiName() + "\n");
                    //定位完成的时间
                    sb.append("定位时间: " + Utils.formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");

                    PreferenceHelper.getInstance(mContext).putString(JINGDU, String.valueOf(location.getLongitude()));
                    PreferenceHelper.getInstance(mContext).putString(WEIDU, String.valueOf(location.getLatitude()));
                    PreferenceHelper.getInstance(mContext).putString(AppConfig.LOCATION_CITY_NAME, location.getCity());

                    stopLocation();
                } else {

                    //"gps_x=45.666043" + "&" + "gps_y=126.605713";
                    // x 纬度 y 经度
                    PreferenceHelper.getInstance(mContext).putString(WEIDU, "45.666043");
                    PreferenceHelper.getInstance(mContext).putString(JINGDU, "126.605713");
                    PreferenceHelper.getInstance(mContext).putString(AppConfig.LOCATION_CITY_NAME, "哈尔滨");
                    //定位失败
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                }
                sb.append("***定位质量报告***").append("\n");
                sb.append("* WIFI开关：").append(location.getLocationQualityReport().isWifiAble() ? "开启" : "关闭").append("\n");
                sb.append("* GPS状态：").append(getGPSStatusString(location.getLocationQualityReport().getGPSStatus())).append("\n");
                sb.append("* GPS星数：").append(location.getLocationQualityReport().getGPSSatellites()).append("\n");
                sb.append("* 网络类型：" + location.getLocationQualityReport().getNetworkType()).append("\n");
                sb.append("* 网络耗时：" + location.getLocationQualityReport().getNetUseTime()).append("\n");
                sb.append("****************").append("\n");
                //定位之后的回调时间
                sb.append("回调时间: " + Utils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");

                //解析定位结果，
                String result = sb.toString();
                Log.i("Location_result", result);
            } else {
                PreferenceHelper.getInstance(mContext).putString(WEIDU, "45.666043");
                PreferenceHelper.getInstance(mContext).putString(JINGDU, "126.605713");
                PreferenceHelper.getInstance(mContext).putString(AppConfig.LOCATION_CITY_NAME, "哈尔滨");
                Log.i("Location_result", "定位失败");
            }
        }
    };

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }

    /**
     * 获取GPS状态的字符串
     *
     * @param statusCode GPS状态码
     * @return
     */
    private String getGPSStatusString(int statusCode) {
        String str = "";
        switch (statusCode) {
            case AMapLocationQualityReport.GPS_STATUS_OK:
                str = "GPS状态正常";
                break;
            case AMapLocationQualityReport.GPS_STATUS_NOGPSPROVIDER:
                str = "手机中没有GPS Provider，无法进行GPS定位";
                break;
            case AMapLocationQualityReport.GPS_STATUS_OFF:
                str = "GPS关闭，建议开启GPS，提高定位质量";
                break;
            case AMapLocationQualityReport.GPS_STATUS_MODE_SAVING:
                str = "选择的定位模式中不包含GPS定位，建议选择包含GPS定位的模式，提高定位质量";
                break;
            case AMapLocationQualityReport.GPS_STATUS_NOGPSPERMISSION:
                str = "没有GPS定位权限，建议开启gps定位权限";
                break;
        }
        return str;
    }

}
