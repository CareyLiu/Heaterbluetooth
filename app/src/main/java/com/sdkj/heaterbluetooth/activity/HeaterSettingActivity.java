package com.sdkj.heaterbluetooth.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttUnSubscribe;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.app.ConstanceValue;
import com.sdkj.heaterbluetooth.app.Notice;
import com.sdkj.heaterbluetooth.common.UIHelper;


import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.sdkj.heaterbluetooth.app.MyApplication.CAR_CTROL;


public class HeaterSettingActivity extends BaseActivity {


    @BindView(R.id.back)
    LinearLayout mBack;
    @BindView(R.id.item_ovner)
    LinearLayout mItemOvner;
    @BindView(R.id.item_parts)
    LinearLayout mItemParts;
    @BindView(R.id.item_ratio)
    LinearLayout mItemRatio;
    @BindView(R.id.item_host)
    LinearLayout mItemHost;
    @BindView(R.id.item_atmos)
    LinearLayout mItemAtmos;

    public String ctsl;//磁铁数量
    public String jrs;//加热塞
    public String jqgl;//机器功率
    public String dfdy;//大风大油
    public String cgq;//传感器
    public String dy;//电压
    public String version = "";

    public static void build(String message) {

//        version = message.substring(message.indexOf("i") + 7, message.indexOf("i") + 11);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heater_setting);
        ButterKnife.bind(this);

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_CAR_I) {
                    //接收到信息
                    Log.i("msg_car_i", message.content.toString());

                    String message1 = message.content.toString();
//                    ctsl = message1.substring(message1.indexOf("i") + 1, message1.indexOf("i") + 2);
//                    jrs = message1.substring(message1.indexOf("i") + 2, message1.indexOf("i") + 3);
//                    jqgl = message1.substring(message1.indexOf("i") + 3, message1.indexOf("i") + 4);
//                    dfdy = message1.substring(message1.indexOf("i") + 4, message1.indexOf("i") + 5);
//                    cgq = message1.substring(message1.indexOf("i") + 5, message1.indexOf("i") + 6);
//                    dy = message1.substring(message1.indexOf("i") + 6, message1.indexOf("i") + 7);
                    version = message1.substring(message1.indexOf("i") + 7, message1.indexOf("i") + 11);
                    Log.i("hostCanShu:", "磁铁数量:" + ctsl + " | 加热塞:" + jrs + " | 机器功率：" + jqgl + " | 大风大油：" + dfdy + " | 传感器:" + cgq + " | 电压:" + dy + " | 版本号：" + version);
                }
            }
        }));
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


    }

    @OnClick({R.id.back, R.id.item_ovner, R.id.item_parts, R.id.item_ratio, R.id.item_host, R.id.item_atmos})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.item_ovner:
                startActivity(new Intent(this, UserInfoActivity.class));
                break;
            case R.id.item_parts:
                // startActivity(new Intent(this, PartsInfoActivity.class));

                DriveinfoActivity.actionStart(this);
                break;
            case R.id.item_ratio://风油比参数
                ServerPassWordActivity.actionStart(this, "RatioActivity");
                //startActivity(new Intent(this, RatioActivity.class));
                break;
            case R.id.item_host:
                if (version.equals("2019")) {
                    ServerPassWordActivity.actionStart(this, "host");
                } else {
                    UIHelper.ToastMessage(HeaterSettingActivity.this,"当前设备不支持修改主机参数");
                }

                break;
            case R.id.item_atmos://大气压参数
                //    startActivity(new Intent(this, AtmosActivity.class));
                ServerPassWordActivity.actionStart(this, "AtmosActivity");
                break;
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
