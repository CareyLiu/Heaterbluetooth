package com.sdkj.heaterbluetooth.activity.zckt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.app.ConstanceValue;
import com.sdkj.heaterbluetooth.app.Notice;
import com.sdkj.heaterbluetooth.app.PreferenceHelper;
import com.sdkj.heaterbluetooth.dialog.TishiDialog;
import com.sdkj.heaterbluetooth.util.Y;


import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ZcktStateActivity extends BaseActivity {


    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.switch_button)
    ImageView switch_button;
    @BindView(R.id.tv_yinliang)
    TextView tv_yinliang;
    @BindView(R.id.seek_yinliang)
    SeekBar seek_yinliang;
    @BindView(R.id.tv_qianyazhi)
    TextView tv_qianyazhi;
    @BindView(R.id.seek_qianya)
    SeekBar seek_qianya;

    private String KT_Send = "zckt/cbox/hardware/11111111111111111111111";
    private String KT_Accept = "zckt/cbox/app/11111111111111111111111";
    private String KT_ccid;
    private boolean isZaixian;
    private String kt_mode;
    private String nowMingling;
    private String kt_lamp_dianhou;
    private String fengmenMode;
    private String kt_yinliang_dianhou;
    private String yinliang;
    private String kt_qianya_dianhou;
    private String qianyazhi;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_air_state;
    }

    @Override
    public void initImmersion() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ZcktStateActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
        initHuidiao();
        registerKtMqtt();
    }

    private void init() {
        KT_ccid = PreferenceHelper.getInstance(this).getString("ccid", "");
        KT_Send = "zckt/cbox/hardware/" + KT_ccid;
        KT_Accept = "zckt/cbox/app/" + KT_ccid;

        switch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fengmenMode.equals("0")) {
                    lampOn();
                } else {
                    lampOff();
                }
            }
        });


        seek_yinliang.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                Y.e("音量是多少啊啊" + progress);
                if (progress < 10) {
                    setYiling("0" + progress);
                } else {
                    setYiling("" + progress);
                }
            }
        });

        seek_qianya.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress() + 190;
                Y.e("欠压值是多少啊" + progress);
                setQianya("0" + progress);
            }
        });
    }

    private void registerKtMqtt() {
        showProgressDialog("设备连接中...");
        initHandlerStart();

        //注册向空调发送数据的主题
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(KT_Send)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
        //注册空调向app回调数据的主题
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(KT_Accept)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });

        getNs();
    }

    /**
     * 界面打开时向空调发送查询实时数据指令
     */
    private void getNs() {
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("M001.")
                .setQos(2).setRetained(false)
                .setTopic(KT_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    /**
     * 获取通知返回的数据
     */
    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ZCKT) {
                    String msg = message.content.toString();
                    getData(msg);
                }
            }
        }));
    }

    private void getData(String msg) {
        if (msg.contains("j_g")) {
            isZaixian = true;
            //接收到信息
            Y.e("我接收到了数据啦" + "    " + msg.toString());
            //手机终端版本号
            String banbenhao = msg.substring(3, 6);
            //空调模式     0.关机 1.经济模式2.标准模式3.强力模式4.固定模式
            kt_mode = msg.substring(6, 7);
            //风量   0 1 2 3 4 5 6
            String kt_fengsu = msg.substring(7, 8);
            //设定温度   05-35
            String kt_temperature = msg.substring(8, 10);
            //风门状态   4：开启扫风   0：关闭扫风
            fengmenMode = msg.substring(10, 11);
            //当前欠压阀值190-230
            qianyazhi = msg.substring(11, 14);
            //当前音量00-15
            yinliang = msg.substring(14, 16);
            //当前电压00-50V
            String dianya = msg.substring(16, 18);
            //进风温度-025=-25度   0026=26度
            String wendu_jinfeng = msg.substring(18, 22);
            //出风温度-025=-25度   0026=26度
            String wendu_chufeng = msg.substring(22, 26);
            //压缩机故障码
            String compress_code = msg.substring(26, 28);
            //面板故障码
            String panel_code = msg.substring(28, 30);
            //照明状态
            String kt_lamp = msg.substring(30, 31);

            if (fengmenMode.equals("4")) {
                switch_button.setImageResource(R.mipmap.switch_open);
            } else {
                switch_button.setImageResource(R.mipmap.switch_close);
            }
        }
    }

    private void initHandlerStart() {
        Message message = handlerStart.obtainMessage(1);
        handlerStart.sendMessageDelayed(message, 1000);
    }

    private void initDengClick() {
        Message message = handlerStart.obtainMessage(2);
        handlerStart.sendMessageDelayed(message, 1000);
    }

    private void initYinliangClick() {
        Message message = handlerStart.obtainMessage(3);
        handlerStart.sendMessageDelayed(message, 1000);
    }

    private void initQianyaClick() {
        Message message = handlerStart.obtainMessage(4);
        handlerStart.sendMessageDelayed(message, 1000);
    }

    private long time;

    private Handler handlerStart = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            time++;
            switch (msg.what) {
                case 1:
                    if (!isZaixian) {
                        if (time >= 30) {
                            showTishiDialog();
                        } else {
                            if (time == 5 || time == 10 || time == 15 || time == 20 || time == 25) {
                                getNs();
                            }
                            initHandlerStart();
                        }
                    } else {
                        dismissProgressDialog();
                        time = 0;
                    }
                    break;
                case 2:
                    if (!fengmenMode.equals(kt_lamp_dianhou)) {
                        if (time >= 30) {
                            showTishiDialog();
                            if (fengmenMode.equals("4")) {
                                switch_button.setImageResource(R.mipmap.switch_open);
                            } else {
                                switch_button.setImageResource(R.mipmap.switch_close);
                            }
                        } else {
                            if (time == 5 || time == 10 || time == 15 || time == 20 || time == 25) {
                                sendDeng(nowMingling);
                            }
                            initDengClick();
                        }
                    } else {
                        dismissProgressDialog();
                        time = 0;
                    }
                    break;
                case 3:
                    if (!kt_yinliang_dianhou.equals(yinliang)) {
                        if (time >= 30) {
                            showTishiDialog();
                            tv_yinliang.setText("音量设定:" + yinliang);
                        } else {
                            if (time == 5 || time == 10 || time == 15 || time == 20 || time == 25) {
                                sendYinliang(nowMingling);
                            }
                            initYinliangClick();
                        }
                    } else {
                        dismissProgressDialog();
                        time = 0;
                    }
                    break;
                case 4:
                    if (!kt_qianya_dianhou.equals(qianyazhi)) {
                        if (time >= 30) {
                            showTishiDialog();
                            tv_qianyazhi.setText("当前欠压阈值:" + qianyazhi);
                        } else {
                            if (time == 5 || time == 10 || time == 15 || time == 20 || time == 25) {
                                sendQianya(nowMingling);
                            }
                            initQianyaClick();
                        }
                    } else {
                        dismissProgressDialog();
                        time = 0;
                    }
                    break;
            }
            Y.e(msg.what + "  的时间时多少啊  " + time);
            return false;
        }
    });

    private void showTishiDialog() {
        isZaixian = false;
        time = 0;
        dismissProgressDialog();
        TishiDialog tishiDialog = new TishiDialog(mContext, TishiDialog.TYPE_CAOZUO, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {
                registerKtMqtt();
            }

            @Override
            public void onDismiss(TishiDialog dialog) {

            }
        });
        tishiDialog.setTextTitle("提示：网络信号异常");
        tishiDialog.setTextContent("请检查设备情况。1:设备是否接通电源 2:设备信号灯是否闪烁 3:设备是否有损坏 4:手机是否开启网络，如已确认以上问题，请重新尝试。");
        tishiDialog.setTextConfirm("重试");
        tishiDialog.setTextCancel("忽略");
        tishiDialog.show();
    }

    /**
     * 开启扫风
     */
    private void lampOn() {
        if (!isZaixian) {
            return;
        }

        if (kt_mode.equals("0")) {
            Y.t("空调已关机，请打开空调发送指令");
            return;
        }

        switch_button.setImageResource(R.mipmap.switch_open);
        showProgressDialog("正在开启扫风...");
        nowMingling = "M054.";
        kt_lamp_dianhou = "4";
        initDengClick();
        sendDeng(nowMingling);
    }

    /**
     * 关闭扫风
     */
    private void lampOff() {
        if (!isZaixian) {
            return;
        }

        if (kt_mode.equals("0")) {
            Y.t("空调已关机，请打开空调发送指令");
            return;
        }

        switch_button.setImageResource(R.mipmap.switch_close);
        showProgressDialog("正在关灯...");
        nowMingling = "M050.";
        kt_lamp_dianhou = "0";
        initDengClick();
        sendDeng(nowMingling);
    }

    private void sendDeng(String nowMingling) {
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(nowMingling)
                .setQos(2).setRetained(false)
                .setTopic(KT_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    /**
     * 设置音量
     */
    private void setYiling(String pos) {
        if (!isZaixian) {
            return;
        }

        if (kt_mode.equals("0")) {
            Y.t("空调已关机，请打开空调发送指令");
            return;
        }

        showProgressDialog("设置中...");
        nowMingling = "M07" + pos + ".";
        kt_yinliang_dianhou = pos;
        tv_yinliang.setText("音量设定:" + kt_yinliang_dianhou);
        initYinliangClick();
        sendYinliang(nowMingling);
    }

    private void sendYinliang(String nowMingling) {
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(nowMingling)
                .setQos(2).setRetained(false)
                .setTopic(KT_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    /**
     * 设置欠压值
     */
    private void setQianya(String qianya) {
        if (!isZaixian) {
            return;
        }

        if (kt_mode.equals("0")) {
            Y.t("空调已关机，请打开空调发送指令");
            return;
        }

        showProgressDialog("设置中...");
        nowMingling = "M06" + qianya + ".";
        kt_qianya_dianhou = qianya;
        tv_qianyazhi.setText("当前欠压阈值:" + kt_qianya_dianhou);
        initQianyaClick();
        sendQianya(nowMingling);
    }

    private void sendQianya(String nowMingling) {
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(nowMingling)
                .setQos(2).setRetained(false)
                .setTopic(KT_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }
}
