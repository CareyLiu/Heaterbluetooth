package com.sdkj.heaterbluetooth.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceBottomEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttUnSubscribe;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.app.ConstanceValue;
import com.sdkj.heaterbluetooth.app.Notice;
import com.sdkj.heaterbluetooth.common.UIHelper;
import com.sdkj.heaterbluetooth.dialog.MyCarCaoZuoDialog_CaoZuo_Base;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.sdkj.heaterbluetooth.app.MyApplication.CAR_CTROL;


/**
 * Created by Sl on 2019/6/25.
 * 主机参数
 */

public class HostActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.rb_single)
    androidx.appcompat.widget.AppCompatRadioButton mRbSingle;
    @BindView(R.id.rb_double)
    androidx.appcompat.widget.AppCompatRadioButton mRbDouble;
    @BindView(R.id.rg_magnet)
    RadioGroup mRgMagnet;
    @BindView(R.id.rb_jc)
    androidx.appcompat.widget.AppCompatRadioButton mRbJc;
    @BindView(R.id.rb_lm)
    androidx.appcompat.widget.AppCompatRadioButton mRbLm;
    @BindView(R.id.rg_heat)
    RadioGroup mRgHeat;
    @BindView(R.id.rb_two)
    androidx.appcompat.widget.AppCompatRadioButton mRbTwo;
    @BindView(R.id.rb_five)
    androidx.appcompat.widget.AppCompatRadioButton mRbFive;
    @BindView(R.id.rg_power)
    RadioGroup mRgPower;
    @BindView(R.id.rb_disable)
    androidx.appcompat.widget.AppCompatRadioButton mRbDisable;
    @BindView(R.id.rb_enable)
    androidx.appcompat.widget.AppCompatRadioButton mRbEnable;
    @BindView(R.id.rg_fy)
    RadioGroup mRgFy;
    @BindView(R.id.rb_ntc)
    androidx.appcompat.widget.AppCompatRadioButton mRbNtc;
    @BindView(R.id.rb_ptc)
    androidx.appcompat.widget.AppCompatRadioButton mRbPtc;
    @BindView(R.id.rb_sensor_auto)
    androidx.appcompat.widget.AppCompatRadioButton mRbSensorAuto;
    @BindView(R.id.rg_sensor)
    RadioGroup mRgSensor;
    @BindView(R.id.rb_12v)
    androidx.appcompat.widget.AppCompatRadioButton mRb12v;
    @BindView(R.id.rb_14v)
    androidx.appcompat.widget.AppCompatRadioButton mRb14v;
    @BindView(R.id.rb_voltage_auto)
    androidx.appcompat.widget.AppCompatRadioButton mRbVoltageAuto;
    @BindView(R.id.rg_voltage)
    RadioGroup mRgVoltage;
    @BindView(R.id.bt_cancel)
    Button mBtCancel;
    @BindView(R.id.bt_sure)
    Button mBtSure;
    @BindView(R.id.bt_recovery)
    Button mBtRecovery;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;


    public String ctsl;//磁铁数量
    public String jrs;//加热塞
    public String jqgl;//机器功率
    public String dfdy;//大风大油
    public String cgq;//传感器
    public String dy;//电压
    public String version;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_host;
    }

    @Override
    public void initImmersion() {
        //   super.initImmersion();
        mImmersionBar.with(this).titleBar(rlTitle).init();
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {

        Intent intent = new Intent(context, HostActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_host);
        ButterKnife.bind(this);
        mBasIn = new BounceBottomEnter();
        mBasOut = new SlideBottomExit();
        //查询主机参数
        // HeaterMqttService.subscribe();
        // HeaterMqttService.mqttService.publish("M512.", HeaterMqttService.TOPIC_SERVER_ORDER, 2, false);
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("M512.")
                .setQos(2).setRetained(false)
                .setTopic(CAR_CTROL), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(CAR_NOTIFY.java:79)-onSuccess:-&gt;发布成功");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
            }
        });

        // HeaterMqttService.handler = mHandler;


        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_CAR_I) {
                    //接收到信息
                    Log.i("msg_car_i", message.content.toString());

                    String message1 = message.content.toString();
                    ctsl = message1.substring(message1.indexOf("i") + 1, message1.indexOf("i") + 2);
                    jrs = message1.substring(message1.indexOf("i") + 2, message1.indexOf("i") + 3);
                    jqgl = message1.substring(message1.indexOf("i") + 3, message1.indexOf("i") + 4);
                    dfdy = message1.substring(message1.indexOf("i") + 4, message1.indexOf("i") + 5);
                    cgq = message1.substring(message1.indexOf("i") + 5, message1.indexOf("i") + 6);
                    dy = message1.substring(message1.indexOf("i") + 6, message1.indexOf("i") + 7);
                    version = message1.substring(message1.indexOf("i") + 7, message1.indexOf("i") + 11);
                    Log.i("hostCanShu:", "磁铁数量:" + ctsl + " | 加热塞:" + jrs + " | 机器功率：" + jqgl + " | 大风大油：" + dfdy + " | 传感器:" + cgq + " | 电压:" + dy + " | 版本号：" + version);

                    initView();
                } else if (message.type == ConstanceValue.MSG_CAR_HUI_FU_CHU_CHAGN) {

                    UIHelper.ToastMessage(HostActivity.this, "恢复出厂设置成功", Toast.LENGTH_SHORT);
                }
            }
        }));

        //  initHandler();

    }

    public void initView() {
        if (ctsl.equals("0"))
            mRbSingle.setChecked(true);
        if (ctsl.equals("1"))
            mRbDouble.setChecked(true);
        if (cgq.equals("0"))
            mRbNtc.setChecked(true);
        if (cgq.equals("1"))
            mRbPtc.setChecked(true);
        if (cgq.equals("2"))
            mRbSensorAuto.setChecked(true);
        if (dfdy.equals("0"))
            mRbDisable.setChecked(true);
        if (dfdy.equals("1"))
            mRbEnable.setChecked(true);
        if (dy.equals("0"))
            mRb12v.setChecked(true);
        if (dy.equals("1"))
            mRb14v.setChecked(true);
        if (dy.equals("2"))
            mRbVoltageAuto.setChecked(true);
        if (jqgl.equals("0"))
            mRbTwo.setChecked(true);
        if (jqgl.equals("1"))
            mRbFive.setChecked(true);
        if (jrs.equals("0"))
            mRbJc.setChecked(true);
        if (jrs.equals("1"))
            mRbLm.setChecked(true);

    }

//    @SuppressLint("HandlerLeak")
//    private void initHandler() {
//        mHandler = new Handler() {
//            public void handleMessage(Message msg) {
//                switch (msg.what) {
//                    case ConstantUtil.MSG_HEATER_HOST_RECOVERY:
//                        //主机参数恢复出厂设置
//                        //showToast("恢复出厂设置成功");
//                        UIHelper.ToastMessage(HostActivity.this, "恢复出厂设置成功", Toast.LENGTH_SHORT);
//                        break;
//                    case ConstantUtil.MSG_HEATER_HOST_DATA:
//                        initView();
//                        break;
//                }
//                super.handleMessage(msg);
//            }
//        };
//    }

    @OnClick({R.id.iv_back, R.id.bt_cancel, R.id.bt_sure, R.id.bt_recovery})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_cancel:
                initView();
                break;
            case R.id.bt_sure:
                if (mRbSingle.isChecked()) {
                    ctsl = "0";
                }

                if (mRbDouble.isChecked()) {
                    ctsl = "1";
                }

                if (mRbNtc.isChecked()) {
                    cgq = "0";
                }

                if (mRbPtc.isChecked()) {
                    cgq = "1";
                }

                if (mRbSensorAuto.isChecked()) {
                    cgq = "2";
                }

                if (mRbDisable.isChecked()) {
                    dfdy = "0";
                }

                if (mRbEnable.isChecked()) {
                    dfdy = "1";
                }

                if (mRb12v.isChecked()) {
                    dy = "0";
                }

                if (mRb14v.isChecked()) {
                    dy = "1";
                }

                if (mRbVoltageAuto.isChecked()) {
                    dy = "2";
                }

                if (mRbTwo.isChecked()) {
                    jqgl = "0";
                }

                if (mRbFive.isChecked()) {
                    jqgl = "1";
                }

                if (mRbJc.isChecked()) {
                    jrs = "0";
                }

                if (mRbLm.isChecked()) {
                    jrs = "1";
                }


                String sendCode = ctsl + jrs + jqgl + dfdy + cgq + dy + version;
                //  HeaterMqttService.mqttService.publish("M54" + sendCode + ".", HeaterMqttService.TOPIC_SERVER_ORDER, 2, false);

                AndMqtt.getInstance().publish(new MqttPublish()
                        .setMsg("M54" + sendCode + ".")
                        .setQos(2).setRetained(false)
                        .setTopic(CAR_CTROL), new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i("Rair", "(CAR_NOTIFY.java:79)-onSuccess:-&gt;发布成功");
                        UIHelper.ToastMessage(HostActivity.this, "修改完毕", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                    }
                });

                break;
            case R.id.bt_recovery:
                final MyCarCaoZuoDialog_CaoZuo_Base base = new MyCarCaoZuoDialog_CaoZuo_Base(this, "恢复出厂", "是否执行恢复出厂", new MyCarCaoZuoDialog_CaoZuo_Base.OnDialogItemClickListener() {
                    @Override
                    public void clickLeft() {
                        // base.dismiss();

                    }

                    @Override
                    public void clickRight() {
                        AndMqtt.getInstance().publish(new MqttPublish()
                                .setMsg("M501.")
                                .setQos(2).setRetained(false)
                                .setTopic(CAR_CTROL), new IMqttActionListener() {
                            @Override
                            public void onSuccess(IMqttToken asyncActionToken) {
                                Log.i("Rair", "(CAR_NOTIFY.java:79)-onSuccess:-&gt;发布成功");
                                //    UIHelper.ToastMessage(HostActivity.this, "修改完毕", Toast.LENGTH_SHORT);

                            }

                            @Override
                            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                                Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                            }
                        });


                    }
                });
//                final NormalDialog normalDialog = new NormalDialog(this);
//                normalDialog.content("确定要恢复出厂设置吗?").showAnim(mBasIn).dismissAnim(mBasOut).show();
//                normalDialog.setOnBtnClickL(
//                        new OnBtnClickL() {
//                            @Override
//                            public void onBtnClick() {
//
//                                normalDialog.dismiss();
//                            }
//                        },
//                        new OnBtnClickL() {
//                            @Override
//                            public void onBtnClick() {
//
//                            }
//                        });
                base.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

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

    }
}
