package com.sdkj.heaterbluetooth.activity;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.percentlayout.widget.PercentRelativeLayout;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.rairmmd.andmqtt.MqttUnSubscribe;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.App;
import com.sdkj.heaterbluetooth.app.AppManager;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.app.ConfigValue;
import com.sdkj.heaterbluetooth.app.ConstanceValue;
import com.sdkj.heaterbluetooth.app.MyApplication;
import com.sdkj.heaterbluetooth.app.Notice;
import com.sdkj.heaterbluetooth.app.PreferenceHelper;
import com.sdkj.heaterbluetooth.callback.JsonCallback;
import com.sdkj.heaterbluetooth.common.UIHelper;
import com.sdkj.heaterbluetooth.config.AppResponse;
import com.sdkj.heaterbluetooth.config.UserManager;
import com.sdkj.heaterbluetooth.dialog.LordingDialog;
import com.sdkj.heaterbluetooth.dialog.MyCarCaoZuoDialog_Notify;
import com.sdkj.heaterbluetooth.getnet.Urls;
import com.sdkj.heaterbluetooth.model.CarDetails;
import com.sdkj.heaterbluetooth.util.ConstantUtil;
import com.sdkj.heaterbluetooth.util.DoMqttValue;
import com.sdkj.heaterbluetooth.util.SoundPoolUtils;
import com.sdkj.heaterbluetooth.view.ArcProgressBar;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.sdkj.heaterbluetooth.app.ConfigValue.STARTSHELVES;
import static com.sdkj.heaterbluetooth.app.ConstanceValue.MSG_MQTT_CONNECTARRIVE;
import static com.sdkj.heaterbluetooth.app.ConstanceValue.MSG_MQTT_CONNECTCOMPLETE;
import static com.sdkj.heaterbluetooth.app.ConstanceValue.MSG_MQTT_CONNECTLOST;
import static com.sdkj.heaterbluetooth.app.ConstanceValue.MSG_MQTT_CONNECT_CHONGLIAN_ONFAILE;
import static com.sdkj.heaterbluetooth.app.ConstanceValue.MSG_MQTT_CONNECT_CHONGLIAN_ONSUCCESS;
import static com.sdkj.heaterbluetooth.app.MyApplication.CARBOX_GETNOW;
import static com.sdkj.heaterbluetooth.app.MyApplication.CAR_CTROL;
import static com.sdkj.heaterbluetooth.app.MyApplication.CAR_NOTIFY;
import static com.sdkj.heaterbluetooth.app.MyApplication.getAppContext;


public class WindHeaterActivity extends BaseActivity implements View.OnLongClickListener, NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "WindHeaterActivity";
    public Handler mHandler;
    public static Handler stateHandler;
    @BindView(R.id.iv_heater_host)
    RelativeLayout ivHeaterHost;


    @BindView(R.id.arcProgressBar)
    ArcProgressBar arcProgressBar;
    @BindView(R.id.btn_heater_close)
    Button btnHeaterClose;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tv_wd)
    TextView mTvWd;
    @BindView(R.id.iv_guanji)
    ImageView ivGuanji;
    @BindView(R.id.iv_kaiji)
    ImageView ivKaiji;
    @BindView(R.id.pl_temperature)
    PercentRelativeLayout plTemperature;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private int progressValue;
    DrawerLayout drawer;

    ValueAnimator valueAnimator;
    //当前档位
    int gear = 0;
    //默认档位模式
    int openMode = 1;
    ImageView ivBrandPic;
    TextView tvBrandName, tvCarNumber;
    List<RadioButton> btns;
    @BindView(R.id.rb_heater_air_mode)
    RadioButton rbHeaterAirMode;
    @BindView(R.id.rb_heater_gear_mode)
    RadioButton rbHeaterGearMode;
    @BindView(R.id.rb_heater_pump_mode)
    RadioButton rbHeaterPumpMode;
    @BindView(R.id.rb_heater_yby_mode)
    RadioButton rbHeaterYbyMode;
    @BindView(R.id.rb_heater_ytf_mode)
    RadioButton rbHeaterYtfMode;
    @BindView(R.id.rg_magnet)
    RadioGroup rgMagnet;
    @BindView(R.id.tv_yushe_wendu)
    TextView tvYuShe_WenDu;
    RadioButton button;
    public String car_server_id;
    public String ccid;
    public String of_user_id;
    Toolbar toolbar;
    String version;
    boolean flag = true;//是否有水泵模式

    private LordingDialog lordingDialog;
    MyCarCaoZuoDialog_Notify myCarCaoZuoDialog_notify;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_wind_heater;
    }

    ProgressDialog waitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceHelper.getInstance(mContext).putString(App.CHOOSE_KONGZHI_XIANGMU, DoMqttValue.FENGNUAN);

        ButterKnife.bind(this);
        waitDialog = ProgressDialog.show(mContext, null, "网络状态不稳定,连接中···", true, true);
        lordingDialog = new LordingDialog(mContext);
        initView();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        toolbar.setNavigationIcon(R.drawable.back_icon);
        @SuppressLint("ResourceType") ColorStateList csl = getResources().getColorStateList(R.drawable.menu_item_color);
        navigationView.setItemTextColor(csl);
        ivBrandPic = navigationView.getHeaderView(0).findViewById(R.id.iv_brand_pic);
        tvBrandName = navigationView.getHeaderView(0).findViewById(R.id.tv_brand_name);
        tvCarNumber = navigationView.getHeaderView(0).findViewById(R.id.tv_car_number);
        mToolbarTitle.setText("风暖加热器");

        initHandler();
        arcProgressBar.setHandler(mHandler);
        btnHeaterClose.setOnLongClickListener(this);
        rbHeaterAirMode.setOnLongClickListener(this);
        rbHeaterGearMode.setOnLongClickListener(this);
        rbHeaterPumpMode.setOnLongClickListener(this);
        rbHeaterYbyMode.setOnLongClickListener(this);
        rbHeaterYtfMode.setOnLongClickListener(this);

        car_server_id = PreferenceHelper.getInstance(mContext).getString("car_server_id", "");
        ccid = PreferenceHelper.getInstance(mContext).getString("ccid", "");
        of_user_id = PreferenceHelper.getInstance(mContext).getString("of_user_id", "");
        setMqttZhiLing();

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                Log.i("message.type", String.valueOf(message.type));
                if (message.type == ConstanceValue.MSG_CAR_J_G) {

                    //接收到信息
                    Log.i("msg_car_j_", message.content.toString());
                    String messageData = message.content.toString().substring(1, message.content.toString().length() - 1);
                    if (0 <= messageData.substring(0, 6).indexOf("a")) {
                        // 纬度x 045.666666=045666666 9
                        BigDecimal dec = new BigDecimal(messageData.substring(0, 3) + "." + messageData.substring(3, 9));
                        String gps_x = dec.toString();
                        // 经度y 126.666666=126666666 9
                        dec = new BigDecimal(messageData.substring(9, 12) + "." + messageData.substring(12, 18));
                        String gps_y = dec.toString();
                        // 车头朝向方向:1东2南3西4北5西北 6西南7东南 8东北
                        String car_orientations = messageData.substring(18, 19);
                        if (!"a".equals(car_orientations)) {

                            if (car_orientations.equals("1")) {

                            } else if (car_orientations.equals("2")) {

                            } else if (car_orientations.equals("3")) {

                            } else if (car_orientations.equals("4")) {

                            } else if (car_orientations.equals("5")) {

                            } else if (car_orientations.equals("6")) {

                            } else if (car_orientations.equals("7")) {

                            } else if (car_orientations.equals("8")) {

                            }
                        }
                    }
                } else if (message.type == ConstanceValue.MSG_CAR_J_M) {

                    //接收到信息
                    Log.i("msg_car_j_m", message.content.toString());

                    String messageData = message.content.toString().substring(1, message.content.toString().length() - 1);
                    Log.i("msg_car_j_m_data", messageData);
                    // 驻车加热器:当前档位1至5档	1	是
                    String oper_dang = messageData.substring(0, 1);
                    if (0 <= oper_dang.indexOf("a")) {
                        oper_dang = "";
                    }
                    // 预设温度1至32度	2	是
                    String oper_wendu_now = messageData.substring(1, 3);
                    oper_wendu_now = 0 <= oper_wendu_now.indexOf("a") ? "" : new BigDecimal(oper_wendu_now).toString();

                    // 驻车预热器:1.档位开机2.空调开机3.风暖关机4.水暖:开机5.水暖:关机	1	是
                    String oper_open_close = messageData.substring(3, 4);
                    /**
                     * 驻车预热器:1.档位开机2.空调开机3.关机 4.水泵开机9.关机中
                     * 6.预泵油7.预通风
                     */
                    switch (oper_open_close) {
                        case "1":
                            tvYuShe_WenDu.setText("当前的挡位为：" + oper_dang + "挡");
                            PreferenceHelper.getInstance(mContext).putString(STARTSHELVES, "1");
                            openMode(Integer.parseInt(oper_dang));
                            switchModel(rbHeaterGearMode, 1);
                            button = rbHeaterGearMode;

                            if (oper_dang != null) {
                                openMode(Integer.parseInt(oper_dang));
                            }

                            break;
                        case "2":

                            if (oper_wendu_now != null) {
                                tvYuShe_WenDu.setText("当前的预设温度为：" + oper_wendu_now + "℃");
                            }

                            PreferenceHelper.getInstance(mContext).putString(STARTSHELVES, "2");
                            if (null != oper_wendu_now) {
                                openKongTiaoMode(Integer.parseInt(oper_wendu_now));
                            } else {
                                openMode(0);
                            }

                            switchModel(rbHeaterAirMode, 2);
                            button = rbHeaterAirMode;
                            break;
                        case "3":

                            closeMode(0);
                            //  AlertUtil.t(WindHeaterActivity.this, "当前设备已关机");
                            tvYuShe_WenDu.setText("长按选择开机模式");
                            break;
                        case "4"://水泵开机
                            //showLoadSuccess();
//
//                            if (version != null) {
//                                if (version.equals("2019")) {
//                                    flag = false;
//
//                                }
//                            }
//
//                            if (flag) {
//                                tvYuShe_WenDu.setText("当前的挡位为：" + oper_dang + "挡");
//                                PreferenceHelper.getInstance(mContext).putString(STARTSHELVES, "4");
//                                button = rbHeaterPumpMode;
//
//                                switchModel(rbHeaterPumpMode, 4);
//                                //档位开机
//                                if (oper_dang != null) {
//                                    openMode(Integer.parseInt(oper_dang));
//                                }
//                            } else {
//                                UIHelper.ToastMessage(WindHeaterActivity.this, "不支持水泵模式", Toast.LENGTH_SHORT);
//                            }


                            break;
                        case "6":

                            tvYuShe_WenDu.setText("当前的挡位为：" + oper_dang + "挡");
                            PreferenceHelper.getInstance(mContext).putString(STARTSHELVES, "6");
                            button = rbHeaterYbyMode;
                            switchModel(rbHeaterYbyMode, 6);
                            //档位开机
                            if (oper_dang != null) {
                                openMode(Integer.parseInt(oper_dang));
                            }

                            break;
                        case "7":

                            tvYuShe_WenDu.setText("当前的挡位为：" + oper_dang + "挡");
                            button = rbHeaterYtfMode;
                            PreferenceHelper.getInstance(mContext).putString(STARTSHELVES, "7");

                            switchModel(rbHeaterYtfMode, 7);
                            //档位开机
                            if (oper_dang != null) {
                                openMode(Integer.parseInt(oper_dang));
                            }

                            break;
                        case "9":
                            mTvWd.setText("00");
                            // showLoadSuccess();
                            //  AlertUtil.t(WindHeaterActivity.this, "正在关机，请稍候");
                            break;
                    }
                    // 驻车加热器:当前温度	3	是
                    String oper_wendu = messageData.substring(4, 6);
                    oper_wendu += "0".equals(messageData.substring(6, 7)) ? "" : "." + messageData.substring(6, 7);

                    // 水暖加热器:尾气温度 例如:-03	3	是
                    String xinhaoQiangDu = messageData.substring(7, 9);
                    String jiGeXinHao = "没有接到信号";
                    if (!StringUtils.isEmpty(xinhaoQiangDu)) {
                        int xinhao = Integer.valueOf(xinhaoQiangDu);

                        if (xinhao < 15) {
                            jiGeXinHao = "无信号";
                        } else if (xinhao >= 15 && xinhao < -19) {
                            jiGeXinHao = "一格信号";
                        } else if (xinhao >= 20 && xinhao <= 25) {
                            jiGeXinHao = "两格信号";
                        } else if (xinhao >= 26 && xinhao <= 30) {
                            jiGeXinHao = "三格信号";
                        } else if (xinhao >= 30 && xinhao <= 35) {
                            jiGeXinHao = "四格信号";
                        }

                        //UIHelper.ToastMessage(mContext, jiGeXinHao);
                    }


                    // 驻车加热器:电压->0253 = 25.3	4	是
                    String machine_voltage = messageData.substring(10, 13) + "." + messageData.substring(13, 14);
                    // 驻车加热器:风机转速->13245	5	是
                    String revolution = messageData.substring(14, 19);
                    // 驻车加热器:加热塞功率->0264=26.4	4	是
                    String power = messageData.substring(19, 23);
                    // 驻车加热器:油泵频率->0265=26.5	4	是
                    String frequency = messageData.substring(23, 27);
                    frequency = frequency.substring(0, 3) + "." + frequency.substring(3);
                    // 驻车加热器:入风口温度->例如:-026	4	是
                    String in_temperature = messageData.substring(27, 31);
                    // 驻车加热器:出风口温度->0128	4	是
                    String out_temperature = messageData.substring(31, 35);

                    String wendu = out_temperature.substring(2, 4);
                    mTvWd.setText(wendu);

                    // 驻车加热器故障码->01至18	2	 标准故障码
                    String zhu_car_stoppage_no = messageData.substring(35, 37);
                    zhu_car_stoppage_no = 0 <= zhu_car_stoppage_no.indexOf("a") ? "" : String.valueOf(Integer.parseInt(zhu_car_stoppage_no));

                    if (!StringUtils.isEmpty(zhu_car_stoppage_no)) {
                        Activity currentActivity = AppManager.getAppManager().currentActivity();
                        if (currentActivity != null) {
                            if (!currentActivity.getClass().getSimpleName().equals(DiagnosisActivity.class.getSimpleName())) {

                                if (!myCarCaoZuoDialog_notify.isShowing()) {
                                    myCarCaoZuoDialog_notify.show();
                                }

                            }
                        }

                    }
                    if (messageData.length() >= 38) {
                        // 水泵状态   0 关  1 开 a 无水泵
                        String shuiBengZhuangTai = messageData.substring(37, 38);

                        switch (shuiBengZhuangTai) {
                            case "0"://0关
                                rbHeaterPumpMode.setChecked(false);
                                rbHeaterPumpMode.setVisibility(View.VISIBLE);
                                break;
                            case "1"://1开
                                rbHeaterPumpMode.setChecked(true);
                                rbHeaterPumpMode.setVisibility(View.VISIBLE);
                                break;
                            case "a"://无
                                flag = false;
                                rbHeaterPumpMode.setVisibility(View.GONE);
                                break;
                        }
                    }


                    String worktime = "1";

                    if (messageData.length() >= 44) {
                        worktime = messageData.substring(38, 44);        // 风暖加热器:工作时长(小时)
                    }

                    if (messageData.length() >= 46) {
                        // 驻车加热器故障码->01至18	2	其它公司用
                        String zhu_car_stoppage_no_o = messageData.substring(44, 46);
                        zhu_car_stoppage_no_o = 0 <= zhu_car_stoppage_no_o.indexOf("a") ? "" : String.valueOf(Integer.parseInt(zhu_car_stoppage_no_o));


                    }


                } else if (message.type == ConstanceValue.MSG_CAR_K) {

                } else if (message.type == ConstanceValue.MSG_CAR_I) {

                    //接收到信息
                    Log.i("msg_car_i", message.content.toString());
                    String message1 = message.content.toString();
                    version = message1.substring(message1.indexOf("i") + 7, message1.indexOf("i") + 11);


                    /**
                     *     int MSG_MQTT_CONNECTCOMPLETE = 0x10045;//连接完成
                     *     int MSG_MQTT_CONNECTLOST = 0x10046;//连接丢失
                     *     int MSG_MQTT_CONNECTARRIVE = 0x10047;//收到连接
                     *     int MSG_MQTT_CONNECT_CHONGLIAN_ONSUCCESS = 0x10048;//重连成功
                     *     int MSG_MQTT_CONNECT_CHONGLIAN_ONFAILE = 0x10049;//重连失败
                     */
                } else if (message.type == MSG_MQTT_CONNECTCOMPLETE) {

                    if (waitDialog.isShowing()) {
                        waitDialog.dismiss();
                        //  setMqttZhiLing();
                    }
                    Log.i("rair", "complete");

                } else if (message.type == MSG_MQTT_CONNECTLOST) {

                    if (!waitDialog.isShowing()) {
                        waitDialog.show();
                    }
                    Log.i("rair", "complete_lost");
                } else if (message.type == MSG_MQTT_CONNECTARRIVE) {

                    if (waitDialog.isShowing()) {
                        waitDialog.dismiss();
                    }
                    Log.i("rair", "arrive");
                } else if (message.type == MSG_MQTT_CONNECT_CHONGLIAN_ONSUCCESS) {

                    if (waitDialog.isShowing()) {
                        waitDialog.dismiss();
                    }

                    Log.i("rair", "chonglian_success");

                } else if (message.type == MSG_MQTT_CONNECT_CHONGLIAN_ONFAILE) {

                    if (!waitDialog.isShowing()) {
                        waitDialog.show();
                    }
                    Log.i("rair", "chonglian_failer");
                } else if (message.type == ConstanceValue.MSG_K6111) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            lordingDialog.dismiss();
                            SoundPoolUtils.soundPool(mContext, R.raw.yikaiji);
                        }
                    }, ConfigValue.YANCHI);//3秒后执行Runnable中的run方法

                } else if (message.type == ConstanceValue.MSG_K6131) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SoundPoolUtils.soundPool(mContext, R.raw.yiguanji);
                            lordingDialog.dismiss();
                        }
                    }, ConfigValue.YANCHI);//3秒后执行Runnable中的run方法

                } else if (message.type == ConstanceValue.MSG_K6121) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SoundPoolUtils.soundPool(mContext, R.raw.yikaiji);
                            lordingDialog.dismiss();
                        }
                    }, ConfigValue.YANCHI);//3秒后执行Runnable中的run方法
                } else if (message.type == ConstanceValue.MSG_K6141) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SoundPoolUtils.soundPool(mContext, R.raw.yikaiji);
                            lordingDialog.dismiss();
                        }
                    }, ConfigValue.YANCHI);//3秒后执行Runnable中的run方法
                } else if (message.type == ConstanceValue.MSG_K6161) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SoundPoolUtils.soundPool(mContext, R.raw.yikaiji);
                            lordingDialog.dismiss();
                        }
                    }, ConfigValue.YANCHI);//3秒后执行Runnable中的run方法
                } else if (message.type == ConstanceValue.MSG_K6171) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SoundPoolUtils.soundPool(mContext, R.raw.yikaiji);
                            lordingDialog.dismiss();
                        }
                    }, ConfigValue.YANCHI);//3秒后执行Runnable中的run方法
                } else if (message.type == ConstanceValue.MSG_ZHILINGMA) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            lordingDialog.dismiss();
                        }
                    }, ConfigValue.YANCHI);//3秒后执行Runnable中的run方法

                }
            }
        }));
        Glide.with(mContext).asGif().load(R.drawable.fengnuan_kaiji).into(ivKaiji);

        ivKaiji.setVisibility(View.GONE);
        ivGuanji.setVisibility(View.VISIBLE);


        myCarCaoZuoDialog_notify = new MyCarCaoZuoDialog_Notify(getAppContext(), new MyCarCaoZuoDialog_Notify.OnDialogItemClickListener() {
            @Override
            public void clickLeft() {
                // player.stop();

            }

            @Override
            public void clickRight() {
                DiagnosisActivity.actionStart(mContext);
                //SoundPoolUtils.soundPool.release();
                myCarCaoZuoDialog_notify.dismiss();

            }
        }
        );

        myCarCaoZuoDialog_notify.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG);
    }

    @Override
    public void initImmersion() {
        mImmersionBar.with(this).titleBar(toolbar).init();
    }

    public void initView() {
        btns = new ArrayList<>();
        btns.add(rbHeaterAirMode);
        btns.add(rbHeaterGearMode);
        btns.add(rbHeaterPumpMode);
        btns.add(rbHeaterYbyMode);
        btns.add(rbHeaterYtfMode);
        for (int i = 0; i < btns.size(); i++) {
            btns.get(i).setOnLongClickListener(this);
        }

    }

    private int gearToValue(int gear) {

        switch (gear) {
            case 1:
                if (progressValue > 20 || progressValue < 5)
                    progressValue = 10;
                break;
            case 2:
                if (progressValue < 20 || progressValue > 40)
                    progressValue = 30;
                break;
            case 3:
                if (progressValue < 40 || progressValue > 60)
                    progressValue = 50;
                break;
            case 4:
                if (progressValue < 60 || progressValue > 80)
                    progressValue = 70;
                break;
            case 5:
                if (progressValue < 80)
                    progressValue = 100;
                break;
            case 0:
                progressValue = 0;
                break;
        }
        return progressValue;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        AndMqtt.getInstance().unSubscribe(new MqttUnSubscribe().setTopic(CAR_NOTIFY), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(MainActivity.java:93)-onSuccess:-&gt;取消订阅成功");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:98)-onFailure:-&gt;取消订阅失败");
            }
        });

        AndMqtt.getInstance().unSubscribe(new MqttUnSubscribe().setTopic(CARBOX_GETNOW), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(MainActivity.java:93)-onSuccess:-&gt;取消订阅成功");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:98)-onFailure:-&gt;取消订阅失败");
            }
        });

        AndMqtt.getInstance().unSubscribe(new MqttUnSubscribe().setTopic(CAR_CTROL), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(MainActivity.java:93)-onSuccess:-&gt;取消订阅成功");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:98)-onFailure:-&gt;取消订阅失败");
            }
        });


        for (int i = 0; i < MyApplication.mqttDingyue.size(); i++) {
            if (MyApplication.mqttDingyue.get(i).equals(CAR_NOTIFY)) {
                MyApplication.mqttDingyue.remove(i);
            }
        }

        for (int i = 0; i < MyApplication.mqttDingyue.size(); i++) {
            if (MyApplication.mqttDingyue.get(i).equals(CARBOX_GETNOW)) {
                MyApplication.mqttDingyue.remove(i);
            }
        }

        for (int i = 0; i < MyApplication.mqttDingyue.size(); i++) {
            if (MyApplication.mqttDingyue.get(i).equals(CAR_CTROL)) {
                MyApplication.mqttDingyue.remove(i);
            }
        }
    }

    /**
     * 向汽车盒子发送命令开启或关闭驻车加热器
     *
     * @param button 用户当前按下的按钮
     * @param model  用户选择的开机模式常量
     **/
    public void heaterSwitch(RadioButton button, int model) {
        if (button != null && button.isChecked()) {

            if (model == 4) {

                AndMqtt.getInstance().publish(new MqttPublish()
                        .setMsg("M712.")
                        .setQos(2).setRetained(false)
                        .setTopic(CAR_CTROL), new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
                        //UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送成功,等待服务器响应", Toast.LENGTH_SHORT);
                        PreferenceHelper.getInstance(mContext).putString(ConfigValue.ZHILINGMA, "k71" + "2" + "1.");
                        lordingDialog.setTextMsg("正在关闭水泵循环，请稍后");
                        lordingDialog.show();
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                        UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送失败", Toast.LENGTH_SHORT);
                    }
                });

                return;
            }

            AndMqtt.getInstance().publish(new MqttPublish()
                    .setMsg("M613.")
                    .setQos(2).setRetained(false)
                    .setTopic(CAR_CTROL), new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
                    //UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送成功,等待服务器响应", Toast.LENGTH_SHORT);


                    switch (model) {
                        //档位模式
                        case 1:
                        case 7:
                        case 6:
                        case 2:
                            SoundPoolUtils.soundPool(mContext, R.raw.guanjizhong);
                            lordingDialog.setTextMsg("正在关机，请稍后");
                            lordingDialog.show();
                            break;
                    }
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                    UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送失败", Toast.LENGTH_SHORT);
                }
            });
        } else {
            if (!arcProgressBar.getIsOpen()) {

                if (model == 4) {

                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("M711.")
                            .setQos(2).setRetained(false)
                            .setTopic(CAR_CTROL), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
                            //UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送成功,等待服务器响应", Toast.LENGTH_SHORT);
                            // SoundPoolUtils.soundPool(mContext, R.raw.guanjizhong);
                            PreferenceHelper.getInstance(mContext).putString(ConfigValue.ZHILINGMA, "k71" + "1" + "1.");
                            lordingDialog.setTextMsg("正在开启水泵循环，请稍后");
                            lordingDialog.show();
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                            UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送失败", Toast.LENGTH_SHORT);
                        }
                    });
                    return;
                }

                AndMqtt.getInstance().publish(new MqttPublish()
                        .setMsg("M61" + model + ".")
                        .setQos(2).setRetained(false)
                        .setTopic(CAR_CTROL), new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");

                        switch (model) {
                            //档位模式
                            case 1:
                                SoundPoolUtils.soundPool(mContext, R.raw.dangwei);
                                lordingDialog.setTextMsg("正在以档位模式开机，请稍后");
                                lordingDialog.show();
                                break;
                            case 2:
                                SoundPoolUtils.soundPool(mContext, R.raw.kongtiao);
                                lordingDialog.setTextMsg("正在以空调模式开机，请稍后");
                                lordingDialog.show();
                                break;
                            case 4:
                                SoundPoolUtils.soundPool(mContext, R.raw.shuibeng);
                                lordingDialog.setTextMsg("正在以水泵模式开机，请稍后");
                                lordingDialog.show();
                                break;
                            case 6:
                                SoundPoolUtils.soundPool(mContext, R.raw.yubengyou);
                                lordingDialog.setTextMsg("正在以预泵油模式开机，请稍后");
                                lordingDialog.show();
                                break;
                            case 7:
                                SoundPoolUtils.soundPool(mContext, R.raw.yutongfeng);
                                lordingDialog.setTextMsg("正在以预通风模式开机，请稍后");
                                lordingDialog.show();
                                break;
                        }
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                        UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送失败", Toast.LENGTH_SHORT);
                    }
                });

            } else {
                //AlertUtil.t(WindHeaterActivity.this, "请先关机，再切换模式");
                lordingDialog.setTextMsg("检测到加热器运行中,请关机后切换模式重新尝试");
                lordingDialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lordingDialog.dismiss();
                    }
                }, 2500);//3秒后执行Runnable中的run方法
            }
        }
    }

    /**
     * 根据实时数据控制RadioButton的背景
     *
     * @param button   当前需要开启的RadioButton
     * @param openMode 用户选择的开机模式
     **/
    public void switchModel(RadioButton button, int openMode) {
        rgMagnet.clearCheck();
        if (button != null) {
            rgMagnet.check(button.getId());
        }
        this.openMode = openMode;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @SuppressLint("HandlerLeak")
    private void initHandler() {
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case ConstantUtil.MSG_HEATER_ROGRESS_VALUE_CHANGE:
                        handleProgressMsg(msg);
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }

    Integer myValue;

    private void handleProgressMsg(Message msg) {
        Bundle b = msg.getData();
        progressValue = b.getInt("progress_value");
        //先判断到底是什么种类的，开机模式

        //空调 档位 水泵 预泵油  预通风
        /**
         * 驻车预热器:1.档位开机2.空调开机3.关机 4.水泵开机9.关机中
         * 6.预泵油7.预通风
         */

        String dangWeiValue = PreferenceHelper.getInstance(mContext).getString(STARTSHELVES, "1");
        if (dangWeiValue.equals("1")) {//档位开机
            Log.i("MyProgressValue", String.valueOf(progressValue));
            dangWeiMode(progressValue);
        } else if (dangWeiValue.equals("2")) {//空调开机
            Log.i("MyProgressValue", String.valueOf(progressValue));
            myValue = 14 + progressValue;
            Log.i("myValue", String.valueOf(myValue));
            if (myValue > 0 && myValue <= 9) {
            } else if (myValue > 9 && myValue < 20) {
                String value = myValue.toString().substring(1, 2);
                Log.i("myValue", value);
                AndMqtt.getInstance().publish(new MqttPublish()
                        .setMsg("M66" + value + ".")
                        .setQos(2)
                        .setTopic(CAR_CTROL).setRetained(false), new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
                        PreferenceHelper.getInstance(mContext).putString(ConfigValue.ZHILINGMA, "k66" + value + "1.");
                        lordingDialog.setTextMsg("温度调节中,请稍后");
                        lordingDialog.show();
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败" + exception.getMessage());
                    }
                });

            } else if (myValue > 20 && myValue < 30) {
                String value = myValue.toString().substring(1, 2);
                Log.i("myValue", value);
                AndMqtt.getInstance().publish(new MqttPublish()
                        .setMsg("M67" + value + ".")
                        .setQos(2)
                        .setTopic(CAR_CTROL).setRetained(false), new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
                        PreferenceHelper.getInstance(mContext).putString(ConfigValue.ZHILINGMA, "k67" + value + "1.");
                        lordingDialog.setTextMsg("温度调节中,请稍后");
                        lordingDialog.show();
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败" + exception.getMessage());
                    }
                });
            } else if (myValue > 20 && myValue <= 32) {
                String value = myValue.toString().substring(1, 2);
                Log.i("myValue", value);
                AndMqtt.getInstance().publish(new MqttPublish()
                        .setMsg("M68" + value + ".")
                        .setQos(2)
                        .setTopic(CAR_CTROL).setRetained(false), new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
                        PreferenceHelper.getInstance(mContext).putString(ConfigValue.ZHILINGMA, "k68" + value + "1.");
                        lordingDialog.setTextMsg("温度调节中,请稍后");
                        lordingDialog.show();
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败" + exception.getMessage());
                    }
                });
            }
        } else if (dangWeiValue.equals("3")) {//关机
        } else if (dangWeiValue.equals("4")) {//水泵模式
            dangWeiMode(progressValue);
        } else if (dangWeiValue.equals("6")) {//预泵油
            dangWeiMode(progressValue);
        } else if (dangWeiValue.equals("7")) {//预通风
            dangWeiMode(progressValue);
        }
    }

    private void dangWeiMode(int progressValue) {
        progressValue = progressValue + 14;
        if (progressValue >= 0 && progressValue <= 18) {
            gear = 1;
        } else if (progressValue > 18 && progressValue <= 21) {
            gear = 2;
        } else if (progressValue > 21 && progressValue <= 25) {
            gear = 3;
        } else if (progressValue > 25 && progressValue <= 28) {
            gear = 4;
        } else if (progressValue > 28 && progressValue <= 32) {
            gear = 5;
        } else {
            gear = 3;
        }
        //    HeaterMqttService.mqttService.publish("M62" + gear + ".", HeaterMqttService.TOPIC_SERVER_ORDER, 2, false);
        //String TOPIC_SERVER_ORDER = "wit/cbox/hardware/" + car_server_id + ccid;
        //同步更新 进度条进度，选中进度条之后 更新进度条

        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("M62" + gear + ".")
                .setQos(2)
                .setTopic(CAR_CTROL).setRetained(false), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
                lordingDialog.setTextMsg("档位切换中,请稍后...");
                lordingDialog.show();
                PreferenceHelper.getInstance(mContext).putString(ConfigValue.ZHILINGMA, "k62" + gear + "1.");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                UIHelper.ToastMessage(WindHeaterActivity.this, "切换档位失败:" + exception.getMessage(), Toast.LENGTH_SHORT);

            }
        });


        int dangValue = 3;

        if (gear == 1) {
            dangValue = 4;

        } else if (gear == 2) {

            dangValue = 7;
        } else if (gear == 3) {

            dangValue = 11;
        } else if (gear == 4) {
            dangValue = 14;

        } else if (gear == 5) {
            dangValue = 18;
        }


        valueAnimator = ValueAnimator.ofInt(arcProgressBar.getCurrentProgerss(), dangValue);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                arcProgressBar.setCurrentProgress((int) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.start();
    }

    String value = "";

    @Override
    public boolean onLongClick(View view) {
        if (!AndMqtt.getInstance().isConneect()) {
            UIHelper.ToastMessage(mContext, "设备已离线,请检查设备后重新尝试");
            return true;
        }
        switch (view.getId()) {
            case R.id.btn_heater_close:
//                Log.d("isSub", "isSub==" + isSub);
                if (arcProgressBar.getIsOpen()) {
                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("M613.")
                            .setQos(2).setRetained(false)
                            .setTopic(CAR_CTROL), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
                            //  UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送成功,等待服务器响应", Toast.LENGTH_SHORT);
                            lordingDialog.setTextMsg("正在关机请稍后...");
                            lordingDialog.show();

                            SoundPoolUtils.soundPool(mContext, R.raw.guanjizhong);
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                            UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送失败，请检查是否连接网络", Toast.LENGTH_SHORT);
                        }
                    });
                } else {
                    //开机
                    //根据用户选择的模式开机
                    //HeaterMqttService.mqttService.publish("M61" + openMode + PreferenceHelper.getInstance(WindHeaterActivity.this).getString("atmos", "") + ".", HeaterMqttService.TOPIC_SERVER_ORDER, 2, false);
                    //关机
                    //  HeaterMqttService.mqttService.publish("M61", CAR_CTROL, 2, false);
                    Log.i("Rair", "主题：" + CAR_CTROL + "  " + "M61" + PreferenceHelper.getInstance(this).getString(STARTSHELVES, "1") + ".");

                    String str = PreferenceHelper.getInstance(this).getString(STARTSHELVES, "1");

                    //   1.风暖档位开机 2.风暖空调开机 3.关机
                    //   4.风暖水泵开机 6.预泵油7.预通风


                    switch (str) {
                        case "1":
                            value = "档位模式";
                            break;
                        case "2":
                            value = "空调模式";
                            break;
                        case "4":
                            value = "水泵模式";
                            break;
                        case "6":
                            value = "预泵油模式";
                            break;
                        case "7":
                            value = "预通风模式";
                            break;

                    }

                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("M61" + PreferenceHelper.getInstance(this).getString(STARTSHELVES, "1") + ".")
                            .setQos(2).setRetained(false)
                            .setTopic(CAR_CTROL), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
                            //UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送成功,等待服务器响应", Toast.LENGTH_SHORT);
                            lordingDialog.setTextMsg("正在以" + value + "开机请稍后...");
                            lordingDialog.show();
                            SoundPoolUtils.soundPool(mContext, R.raw.kaijizhong);
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                            UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送失败", Toast.LENGTH_SHORT);
                        }
                    });

                }
                // heaterSwitch(button, openMode);
                break;
            case R.id.rb_heater_air_mode: //空调模式
                heaterSwitch(rbHeaterAirMode, 2);
                break;
            case R.id.rb_heater_gear_mode://档位模式
                heaterSwitch(rbHeaterGearMode, 1);
                break;
            case R.id.rb_heater_pump_mode://水泵模式
                //heaterSwitch(rbHeaterPumpMode, 4);
                setShuiBeng(rbHeaterPumpMode, 4);
                break;
            case R.id.rb_heater_yby_mode://预泵油
                heaterSwitch(rbHeaterYbyMode, 6);
                break;
            case R.id.rb_heater_ytf_mode://预通风
                heaterSwitch(rbHeaterYtfMode, 7);
                break;
        }

        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.heater_menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_settings:
                if (!AndMqtt.getInstance().isConneect()) {
                    UIHelper.ToastMessage(mContext, "设备已离线,暂不支持菜单功能，请检查后重新尝试");
                    break;
                }
                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawers();
                } else {
                    drawer.openDrawer(GravityCompat.END);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_bluetooth:
                //切换蓝牙模式
                UIHelper.ToastMessage(this, "暂不支持");
                break;
            case R.id.nav_trajectory:
                //历史轨迹
                //startActivity(new Intent(this, HistoryLocusActivity.class));
                break;
            case R.id.nav_location:
                //定位
                //startActivity(new Intent(this, LocationActivity.class));
                break;
            case R.id.nav_timing:
                //定时
                //startActivity(new Intent(this, AppointmentActivity.class));
                AppointmentActivity.actionStart(mContext, ccid);
                break;
            case R.id.nav_record:
                //维修记录
                //startActivity(new Intent(this, RepairOrderAcitivity.class));
                break;
            case R.id.nav_state:
                //加热器状态
                startActivity(new Intent(this, DeviceStateActivity.class));
                break;
            case R.id.nav_report:
                //故障报警
                // startActivity(new Intent(this, DiagnosisActivity.class));
                DiagnosisActivity.actionStart(this);
                break;
            case R.id.nav_corral:
                //地理围栏
//                startActivity(new Intent(this, WebViewActivity.class).
//                        putExtra("url", PreferenceHelper.getInstance(this).getString("fence_url", "")).putExtra("title", "地理围栏"));
                break;

            case R.id.fengnuan_jiebang:
                FengNuanJieBangActivity.actionStart(mContext);
                break;
            case R.id.nav_setting:
                startActivity(new Intent(this, HeaterSettingActivity.class));
                //设置
                break;
        }


        return false;
    }

    public void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03107");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("user_car_id", PreferenceHelper.getInstance(this).getString("car_id", ""));
        Gson gson = new Gson();
        OkGo.<AppResponse<CarDetails.DataBean>>post(Urls.SERVER_URL + "wit/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<CarDetails.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<CarDetails.DataBean>> response) {
                        PreferenceHelper.getInstance(WindHeaterActivity.this).putString("fence_url", response.body().data.get(0).getWeilan_url());
                        Glide.with(WindHeaterActivity.this).load(response.body().data.get(0).getCar_brand_url_one()).into(ivBrandPic);
                        tvBrandName.setText(response.body().data.get(0).getCar_brand_name_one());
                        tvCarNumber.setText(response.body().data.get(0).getPlate_number());

                    }

                    @Override
                    public void onError(Response<AppResponse<CarDetails.DataBean>> response) {
                        UIHelper.ToastMessage(WindHeaterActivity.this, response.getException().getMessage());
                    }
                });


    }

    public void openKongTiaoMode(int value) {
        arcProgressBar.setOpen(true);

        //加载gif图 -- 开火了

        ivKaiji.setVisibility(View.VISIBLE);
        ivGuanji.setVisibility(View.GONE);
        //    btnHeaterClose.setBackground(getResources().getDrawable(R.drawable.bg_heater_close_btn_on));
        btnHeaterClose.setSelected(true);

        if (value > 32) {
            value = 32;
        }

        if (value > 15) {
            value = value - 14;
        }
        Log.i("arcProgressBar", value + "");
        valueAnimator = ValueAnimator.ofInt(arcProgressBar.getCurrentProgerss(), value);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                arcProgressBar.setCurrentProgress((int) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.start();


    }

    public void openMode(int value) {
        arcProgressBar.setOpen(true);

        //加载gif  -- 开关可见


        ivKaiji.setVisibility(View.VISIBLE);
        ivGuanji.setVisibility(View.GONE);

        //    btnHeaterClose.setBackground(getResources().getDrawable(R.drawable.bg_heater_close_btn_on));
        btnHeaterClose.setSelected(true);


        int dangValue = 3;

        if (value == 1) {
            dangValue = 4;

        } else if (value == 2) {

            dangValue = 7;
        } else if (value == 3) {

            dangValue = 11;
        } else if (value == 4) {
            dangValue = 14;

        } else if (value == 5) {
            dangValue = 18;
        }


        valueAnimator = ValueAnimator.ofInt(arcProgressBar.getCurrentProgerss(), dangValue);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                arcProgressBar.setCurrentProgress((int) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.start();


    }

    public void closeMode(int value) {
        arcProgressBar.setOpen(false);

        // 展示图片 不是gif


        ivKaiji.setVisibility(View.GONE);
        ivGuanji.setVisibility(View.VISIBLE);
        //   btnHeaterClose.setBackground(getResources().getDrawable(R.drawable.bg_heater_close_btn_off));
        btnHeaterClose.setSelected(false);
        valueAnimator = ValueAnimator.ofInt(arcProgressBar.getCurrentProgerss(), value);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                arcProgressBar.setCurrentProgress((int) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.start();
        switchModel(null, openMode);
    }

    @OnClick({R.id.rb_heater_air_mode, R.id.rb_heater_gear_mode, R.id.rb_heater_pump_mode, R.id.rb_heater_yby_mode, R.id.rb_heater_ytf_mode})
    public void onViewClicked(View view) {
        rgMagnet.clearCheck();
        if (button != null && arcProgressBar.getIsOpen())
            rgMagnet.check(button.getId());

    }

    public void setMqttZhiLing() {

        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(CAR_NOTIFY)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "notify:  " + CAR_NOTIFY + "CAR_NOTIFY 我是在类里面订阅的");


                MyApplication.mqttDingyue.add(CAR_NOTIFY);
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("CAR_NOTIFY", "(MainActivity.java:68)-onFailure:-&gt;订阅失败");
            }
        });


        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("k001.")
                .setQos(2).setRetained(false)
                .setTopic(CAR_NOTIFY), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(CAR_NOTIFY.java:79)-onSuccess:-&gt;发布成功" + "k001 我是在类里面订阅的");

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
            }
        });

        //获得车辆的实时数据和基本信息
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(CARBOX_GETNOW)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "订阅成功 carbox_getnow:  " + CARBOX_GETNOW + " CARBOX_GETNOW 我是在类里面订阅的");
                MyApplication.mqttDingyue.add(CARBOX_GETNOW);
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:68)-onFailure:-&gt;订阅失败");
            }
        });


        //控制车辆
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(CAR_CTROL)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", " Rair用户订阅成功" + "wit/app/" + of_user_id + " CAR_CTROL 我是在类里面订阅的");
                MyApplication.mqttDingyue.add(CAR_CTROL);
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:68)-onFailure:-&gt;订阅失败");
            }


        });


        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("N9.")
                .setQos(2)
                .setTopic(CAR_CTROL)
                .setRetained(false), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功" + " N9 我是在类里面订阅的");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
            }
        });

        //查询车辆详情数据
        // requestData();

        // HeaterMqttService.mqttService.publish("M512.", HeaterMqttService.TOPIC_SERVER_ORDER, 2, false);
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("M512.")
                .setQos(2).setRetained(false)
                .setTopic(CAR_CTROL), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(CAR_NOTIFY.java:79)-onSuccess:-&gt;发布成功" + "M512 我是在类里面订阅的");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
            }
        });
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
                        DiagnosisActivity.actionStart(mContext);
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
     * 向汽车盒子发送命令开启或关闭驻车加热器
     *
     * @param button 用户当前按下的按钮
     * @param model  用户选择的开机模式常量
     **/
    public void setShuiBeng(RadioButton button, int model) {
        if (button != null && button.isChecked()) {

            if (model == 4) {
                AndMqtt.getInstance().publish(new MqttPublish()
                        .setMsg("M712.")
                        .setQos(2).setRetained(false)
                        .setTopic(CAR_CTROL), new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
                        //UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送成功,等待服务器响应", Toast.LENGTH_SHORT);
                        PreferenceHelper.getInstance(mContext).putString(ConfigValue.ZHILINGMA, "k71" + "2" + "1.");
                        lordingDialog.setTextMsg("正在关闭水泵循环，请稍后");
                        lordingDialog.show();
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                        UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送失败", Toast.LENGTH_SHORT);
                    }
                });

                return;
            }
        } else {
            if (model == 4) {
                AndMqtt.getInstance().publish(new MqttPublish()
                        .setMsg("M711.")
                        .setQos(2).setRetained(false)
                        .setTopic(CAR_CTROL), new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
                        //UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送成功,等待服务器响应", Toast.LENGTH_SHORT);
                        PreferenceHelper.getInstance(mContext).putString(ConfigValue.ZHILINGMA, "k71" + "1" + "1.");
                        lordingDialog.setTextMsg("正在开启水泵循环，请稍后");
                        lordingDialog.show();
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                        UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送失败", Toast.LENGTH_SHORT);
                    }
                });
                return;
            }

        }
    }

}
