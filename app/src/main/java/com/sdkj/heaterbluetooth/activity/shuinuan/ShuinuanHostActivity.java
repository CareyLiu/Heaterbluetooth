package com.sdkj.heaterbluetooth.activity.shuinuan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.ConstanceValue;
import com.sdkj.heaterbluetooth.app.Notice;
import com.sdkj.heaterbluetooth.dialog.TishiDialog;
import com.sdkj.heaterbluetooth.util.Y;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ShuinuanHostActivity extends ShuinuanBaseActivity {


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_cixianquan0)
    TextView tvCixianquan0;
    @BindView(R.id.tv_cixianquan1)
    TextView tvCixianquan1;
    @BindView(R.id.tv_cixianquan2)
    TextView tvCixianquan2;
    @BindView(R.id.tv_cixianquan3)
    TextView tvCixianquan3;
    @BindView(R.id.tv_cixianquan4)
    TextView tvCixianquan4;
    @BindView(R.id.tv_jiaresai_jingci)
    TextView tvJiaresaiJingci;
    @BindView(R.id.tv_jiaresai_limai)
    TextView tvJiaresaiLimai;
    @BindView(R.id.tv_jiqigonglv)
    TextView tvJiqigonglv;
    @BindView(R.id.tv_dianya_12v)
    TextView tvDianya12v;
    @BindView(R.id.tv_dianya_24v)
    TextView tvDianya24v;
    @BindView(R.id.tv_dianya_zidong)
    TextView tvDianyaZidong;
    @BindView(R.id.tv_rongjizhi)
    TextView tvRongjizhi;
    @BindView(R.id.tv_guoyazhi)
    TextView tvGuoyazhi;
    @BindView(R.id.tv_guoya_time)
    TextView tvGuoyaTime;
    @BindView(R.id.tv_qianyazhi)
    TextView tvQianyazhi;
    @BindView(R.id.tv_qianya_time)
    TextView tvQianyaTime;

    @Override
    public void initImmersion() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_shuinuan_canshu;
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShuinuanHostActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initHuidiao();
        registerKtMqtt();
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_SN_DATA) {
                    String msg = message.content.toString();
                    getData(msg);
                }
            }
        }));
    }

    private void getData(String msg) {
        Y.e("获取的数据是" + msg);
        if (msg.contains("i_s")) {
            dismissProgressDialog();
            handlerStart.removeMessages(1);

            String substring = msg.substring(3, msg.length() - 2);
            if (substring.contains("a")) {
                showNodata();
            } else {
                String citienum = msg.substring(3, 4);//磁铁数量  0：无1：单磁铁2：双磁铁3：三磁铁4：四磁铁
                String jiaresai = msg.substring(4, 5);//加热塞  0：京瓷 1：利麦
                String jiqigonglv = msg.substring(5, 8);//机器功率  020=2kw   037=3.7kw  170=17kw
                String dianya = msg.substring(8, 9);//电压  0：12V  1：24V  9：自动
                String rongjizhi = msg.substring(9, 11);//油泵容积值  16-70
                String guoyazhi = msg.substring(11, 14);//过压值  135=13.5V
                String guoyatime = msg.substring(14, 16);//过压报警时间  10=10秒
                String qianyazhi = msg.substring(16, 19);//欠压值  135=13.5V
                String qianyatime = msg.substring(19, 21);//欠压报警时间  10=10秒

                clickCixianquan(citienum);
                clickJiaresai(jiaresai);
                tvJiqigonglv.setText(Y.formatNum(Y.getFloat(jiqigonglv) / 10));
                clickDianya(dianya);
                tvRongjizhi.setText(rongjizhi + "L");
                tvGuoyazhi.setText(Y.formatNum(Y.getFloat(guoyazhi) / 10) + "v");
                tvGuoyaTime.setText(guoyatime + "s");
                tvQianyazhi.setText(Y.formatNum(Y.getFloat(qianyazhi) / 10) + "v");
                tvQianyaTime.setText(qianyatime + "s");
            }
        }
    }

    private void showNodata() {
        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_FAILED, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {

            }

            @Override
            public void onDismiss(TishiDialog dialog) {

            }
        });
        dialog.setTextTitle("提示");
        dialog.setTextContent("暂无主机参数信息");
        dialog.setTextConfirm("关闭");
        dialog.show();
    }

    /**
     * 注册订阅Mqtt
     */
    private void registerKtMqtt() {
        showProgressDialog("获取数据中...");
        initHandlerStart();
        getHost();
    }

    private void initHandlerStart() {
        Message message = handlerStart.obtainMessage(1);
        handlerStart.sendMessageDelayed(message, 250);
    }

    private void getHost() {
        //注册水暖加热器订阅
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(SN_Send)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Y.i("我订阅了" + SN_Send);
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });

        //模拟水暖加热器订阅app
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(SN_Accept)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Y.i("我订阅了" + SN_Accept);
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });

        //向水暖加热器发送获取实时数据
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("M_s112.")
                .setQos(2).setRetained(false)
                .setTopic(SN_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("app端向水暖加热器请求主机参数", "");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    private int time = 0;

    private Handler handlerStart = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    time++;
                    if (time >= 60) {
                        showTishiDialog();
                    } else {
                        if (time == 4 || time == 24 || time == 44) {
                            getHost();
                        }
                        initHandlerStart();
                    }
                    Y.i("计时是多少啊啊啊" + time);
                    break;

            }
            super.handleMessage(msg);
        }
    };

    private void showTishiDialog() {
        time = 0;
        dismissProgressDialog();
        showNodata();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerStart.removeMessages(1);
    }


    private void clickCixianquan(String type) {
        tvCixianquan0.setTextColor(Color.parseColor("#999999"));
        tvCixianquan1.setTextColor(Color.parseColor("#999999"));
        tvCixianquan2.setTextColor(Color.parseColor("#999999"));
        tvCixianquan3.setTextColor(Color.parseColor("#999999"));
        tvCixianquan4.setTextColor(Color.parseColor("#999999"));
        switch (type) {
            case "0":
                tvCixianquan0.setTextColor(Y.getColor(R.color.text_color_blue));
                break;
            case "1":
                tvCixianquan1.setTextColor(Y.getColor(R.color.text_color_blue));
                break;
            case "2":
                tvCixianquan2.setTextColor(Y.getColor(R.color.text_color_blue));
                break;
            case "3":
                tvCixianquan3.setTextColor(Y.getColor(R.color.text_color_blue));
                break;
            case "4":
                tvCixianquan4.setTextColor(Color.parseColor("#0F85FF"));
                break;
        }
    }

    private void clickJiaresai(String type) {
        tvJiaresaiJingci.setTextColor(Color.parseColor("#999999"));
        tvJiaresaiLimai.setTextColor(Color.parseColor("#999999"));
        switch (type) {
            case "0":
                tvJiaresaiJingci.setTextColor(Y.getColor(R.color.text_color_blue));
                break;
            case "1":
                tvJiaresaiLimai.setTextColor(Y.getColor(R.color.text_color_blue));
                break;
        }
    }

    private void clickDianya(String type) {
        tvDianya12v.setTextColor(Color.parseColor("#999999"));
        tvDianya24v.setTextColor(Color.parseColor("#999999"));
        tvDianyaZidong.setTextColor(Color.parseColor("#999999"));
        switch (type) {
            case "0":
                tvDianya12v.setTextColor(Y.getColor(R.color.text_color_blue));
                break;
            case "1":
                tvDianya24v.setTextColor(Y.getColor(R.color.text_color_blue));
                break;
            case "9":
                tvDianyaZidong.setTextColor(Y.getColor(R.color.text_color_blue));
                break;
        }
    }
}
