package com.sdkj.heaterbluetooth.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.app.ConstanceValue;
import com.sdkj.heaterbluetooth.app.Notice;
import com.sdkj.heaterbluetooth.app.PreferenceHelper;
import com.sdkj.heaterbluetooth.common.UIHelper;


import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.sdkj.heaterbluetooth.app.MyApplication.CAR_CTROL;


public class DeviceStateActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_device_state)
    TextView tvDeviceState;
    @BindView(R.id.tv_device_voltage)
    TextView tvDeviceVoltage;
    @BindView(R.id.tv_speed)
    TextView tvSpeed;
    @BindView(R.id.tv_rate)
    TextView tvRate;
    @BindView(R.id.tv_in_temperature)
    TextView tvInTemperature;
    @BindView(R.id.tv_out_temperature)
    TextView tvOutTemperature;
    @BindView(R.id.tv_mode)
    TextView tvMode;
    @BindView(R.id.tv_power)
    TextView tvPower;
    @BindView(R.id.tv_duration)
    TextView tvDuration;
    public static Handler stateHandler;
    @BindView(R.id.tv_daqiya)
    TextView tvDaqiya;
    @BindView(R.id.tv_haibagaodu)
    TextView tvHaibagaodu;
    @BindView(R.id.tv_hanyangliang)
    TextView tvHanyangliang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heater_state);
        ButterKnife.bind(this);

        //查询时时数据
        //HeaterMqttService.mqttService.publish("N9.", HeaterMqttService.TOPIC_SERVER_ORDER, 2, false);
        //HeaterMqttService.handler = stateHandler;
        if (!AndMqtt.getInstance().isConneect()) {
            UIHelper.ToastMessage(DeviceStateActivity.this, "设备已离线，请检查设备后重新尝试");
            return;
        }

        String server_id = PreferenceHelper.getInstance(DeviceStateActivity.this).getString("server_id", "");
        String ccid = PreferenceHelper.getInstance(DeviceStateActivity.this).getString("ccid", "");


        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("N9.")
                .setQos(2)
                .setTopic(CAR_CTROL)
                .setRetained(false), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败" + exception.getMessage());

            }
        });

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_CAR_J_M) {
                    //接收到信息
                    Log.i("msg_car_j_m", message.content.toString());
                    String messageData = message.content.toString().substring(1);
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
                    // 驻车加热器:当前温度	3	是
                    String oper_wendu = messageData.substring(4, 6);
                    oper_wendu += "0".equals(messageData.substring(6, 7)) ? "" : "." + messageData.substring(6, 7);
                    // 水暖加热器:尾气温度 例如:-03	3	是
                    String zhu_shui_tail_gas = messageData.substring(7, 10);
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
                    // 驻车加热器故障码->01至18	2	 标准故障码
                    String zhu_car_stoppage_no = messageData.substring(35, 37);
                    zhu_car_stoppage_no = 0 <= zhu_car_stoppage_no.indexOf("a") ? "" : String.valueOf(Integer.parseInt(zhu_car_stoppage_no));
                    // 水暖加热器:开关机状态
                    String shui_open_close = messageData.substring(37, 38);

                    if (0 <= shui_open_close.indexOf("a")) {
                        shui_open_close = "5";
                    }
                    String worktime = "1";

                    try {
                        worktime = messageData.substring(38, 44);        // 风暖加热器:工作时长(小时)

                    } catch (Exception e) {
                    }


                    // 定制故障码
                    if (messageData.length() >= 46) {
                        // 驻车加热器故障码->01至18	2	其它公司用
                        String zhu_car_stoppage_no_o = messageData.substring(44, 46);
                        zhu_car_stoppage_no_o = 0 <= zhu_car_stoppage_no_o.indexOf("a") ? "" : String.valueOf(Integer.parseInt(zhu_car_stoppage_no_o));
                    }
                    // 大气压 3位
                    if (messageData.length() >= 49) {
                        String daQiYa = messageData.substring(46, 49);
                        tvDaqiya.setText(daQiYa);
                    }
                    // 海拔高度 4位
                    if (messageData.length() >= 53) {
                        String haiBaGaoDu = messageData.substring(49, 53);
                        tvHaibagaodu.setText(haiBaGaoDu);
                    }

                    // 含氧量 3位
                    if (messageData.length() >= 56) {
                        String hanYangLiang = messageData.substring(53, 56);
                        tvHanyangliang.setText(hanYangLiang);
                    }


                    //加载实时数据
                    tvDeviceVoltage.setText(noneZero(machine_voltage) + "V");
                    tvDuration.setText(noneZero(worktime) + "h");
                    tvInTemperature.setText(noneZero(in_temperature) + "°C");
                    tvOutTemperature.setText(noneZero(out_temperature) + "°C");
                    tvPower.setText(noneZero(power) + "W");
                    tvRate.setText(noneZero(frequency) + "Hz");
                    tvSpeed.setText(noneZero(revolution) + "rpm");
                    switch (oper_open_close) {

                        case "1":
                            tvMode.setText("档位开机");
                            tvDeviceState.setText("开机");
                            break;
                        case "2":
                            tvMode.setText("空调开机");
                            tvDeviceState.setText("开机");
                            break;
                        case "3":
                            tvDeviceState.setText("关机");
                            tvMode.setText("关机");
                            break;
                        case "4":
                            tvMode.setText("水泵开机");
                            tvDeviceState.setText("开机");
                            break;
                        case "9":
                            tvMode.setText("关机中 ");
                            break;
                        case "6":
                            tvMode.setText("预泵油");
                            break;
                        case "7":
                            tvMode.setText("预通风");
                            break;
                        default:
                            break;
                    }
                }
            }
        }));
    }

    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }

    public String noneZero(String str) {
        try {
            BigDecimal bigDecimal = new BigDecimal(str);
            if (bigDecimal.compareTo(BigDecimal.ZERO) == 0) {
                return "0";
            }

        } catch (Exception e) {

        }
        int len = str.length();// 取得字符串的长度
        int index = 0;// 预定义第一个非零字符串的位置

        char strs[] = str.toCharArray();// 将字符串转化成字符数组
        for (int i = 0; i < len; i++) {
            if ('0' != strs[i]) {
                index = i;// 找到非零字符串并跳出
                break;
            }
        }
        String strLast = str.substring(index, len);// 截取字符串
        return strLast;
    }
}
