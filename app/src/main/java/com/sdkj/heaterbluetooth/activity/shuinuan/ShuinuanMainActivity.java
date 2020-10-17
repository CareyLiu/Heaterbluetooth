package com.sdkj.heaterbluetooth.activity.shuinuan;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.health.SystemHealthManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.rairmmd.andmqtt.MqttUnSubscribe;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.activity.SheBeiSetActivity;
import com.sdkj.heaterbluetooth.activity.shuinuan.dialog.GuzhangDialog;
import com.sdkj.heaterbluetooth.app.App;
import com.sdkj.heaterbluetooth.app.ConstanceValue;
import com.sdkj.heaterbluetooth.app.MyApplication;
import com.sdkj.heaterbluetooth.app.Notice;
import com.sdkj.heaterbluetooth.app.PreferenceHelper;
import com.sdkj.heaterbluetooth.dialog.TishiDialog;
import com.sdkj.heaterbluetooth.util.DoMqttValue;
import com.sdkj.heaterbluetooth.util.SoundPoolUtils;
import com.sdkj.heaterbluetooth.util.TimeCount;
import com.sdkj.heaterbluetooth.util.Y;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ShuinuanMainActivity extends ShuinuanBaseActivity implements View.OnLongClickListener {

    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.rl_set)
    RelativeLayout rl_set;
    @BindView(R.id.iv_shebeima)
    ImageView iv_shebeima;
    @BindView(R.id.tv_dingshi)
    TextView tv_dingshi;
    @BindView(R.id.iv_xinhao)
    ImageView iv_xinhao;
    @BindView(R.id.tv_zaixian)
    TextView tv_zaixian;
    @BindView(R.id.tv_fuwutianshushengyu)
    TextView tv_fuwutianshushengyu;
    @BindView(R.id.tv_shebei_state)
    TextView tv_shebei_state;
    @BindView(R.id.iv_shuinuan_kaijie)
    ImageView iv_shuinuan_kaijie;
    @BindView(R.id.tv_shuinuan_kaiji)
    TextView tv_shuinuan_kaiji;
    @BindView(R.id.rv_shuinuan_kaiji)
    RelativeLayout rv_shuinuan_kaiji;
    @BindView(R.id.iv_shuinuan_guanji)
    ImageView iv_shuinuan_guanji;
    @BindView(R.id.tv_shuinuan_guanji)
    TextView tv_shuinuan_guanji;
    @BindView(R.id.rv_shuinuan_guanji)
    RelativeLayout rv_shuinuan_guanji;
    @BindView(R.id.tv_shuinuan_shuibeng)
    TextView tv_shuinuan_shuibeng;
    @BindView(R.id.tv_shuinuan_youbeng)
    TextView tv_shuinuan_youbeng;
    @BindView(R.id.tv_wendu_yushe)
    TextView tv_wendu_yushe;
    @BindView(R.id.tv_wendu_dangqian)
    TextView tv_wendu_dangqian;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.tv_dianya)
    TextView tv_dianya;
    @BindView(R.id.tv_jinshuikou_wendu)
    TextView tv_jinshuikou_wendu;
    @BindView(R.id.tv_chushuikou_wendu)
    TextView tv_chushuikou_wendu;
    @BindView(R.id.tv_haibagaodu)
    TextView tv_haibagaodu;
    @BindView(R.id.tv_hanyangliang)
    TextView tv_hanyangliang;
    @BindView(R.id.tv_daqiya)
    TextView tv_daqiya;
    @BindView(R.id.iv_heater_host)
    ImageView iv_heater_host;
    @BindView(R.id.ll_content)
    LinearLayout ll_content;

    private String sn_state;     //水暖状态
    private String yushewendu;      //预设温度
    private boolean isKaiji;
    private boolean iskaijiDianhou;
    private GuzhangDialog guzhangDialog;
    private AnimationDrawable animationDrawable;
    private String shuibeng_state;
    private String youbeng_state;
    private boolean youbengIson;
    private boolean youbengIsdianhou;
    private boolean shubengIson;
    private boolean shoubengisdianhou;
    private boolean isZaixian = false;
    private int zhilingma;
    private final int zhiling_kaiji = 1;
    private final int zhiling_guanji = 2;
    private final int zhiling_shuibeng = 3;
    private final int zhiling_youbeng = 4;
    private String xinhaoStr;
    private boolean isFirst;
    private boolean isOnActivity;

    @Override
    protected void onResume() {
        super.onResume();
        isOnActivity = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isOnActivity = false;
    }

    private Handler handlerTime10 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    if (isOnActivity) {
                        getNsData();
                        Y.e("我执行了一次查询实时数据啦");
                    }
                    initHandlerNS();
                    break;
            }
            return false;
        }
    });


    @Override
    public int getContentViewResId() {
        return R.layout.activity_shuinuan_main_new;
    }

    @Override
    public void initImmersion() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        mImmersionBar.statusBarDarkFont(true);
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String ccid, String car_server_id) {
        Intent intent = new Intent(context, ShuinuanMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ccid", ccid);
        intent.putExtra("car_server_id", car_server_id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
        initCcid();
        initHuidiao();
        registerKtMqtt();
    }

    private void init() {
        rv_shuinuan_guanji.setSelected(true);
        isKaiji = false;

        rv_shuinuan_kaiji.setOnLongClickListener(this);
        rv_shuinuan_guanji.setOnLongClickListener(this);
        tv_shuinuan_youbeng.setOnLongClickListener(this);
        tv_shuinuan_shuibeng.setOnLongClickListener(this);

        PreferenceHelper.getInstance(mContext).putString(App.CHOOSE_KONGZHI_XIANGMU, DoMqttValue.SHUINUAN);
    }

    /**
     * 初始化Ccid
     */
    private void initCcid() {
        String car_server_id = getIntent().getStringExtra("car_server_id");
        ccid = getIntent().getStringExtra("ccid");
        SN_Send = "wh/hardware/" + car_server_id + ccid;
        SN_Accept = "wh/app/" + car_server_id + ccid;

        MyApplication.mqttDingyue.add(SN_Send);
        MyApplication.mqttDingyue.add(SN_Accept);

        isFirst = true;
    }

    /**
     * 初始化回调数据
     */
    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_SN_DATA) {
                    String msg = message.content.toString();
                    getData(msg);
                } else if (message.type == ConstanceValue.MSG_JIEBANG) {
                    finish();
                }
            }
        }));
    }

    private void getData(String msg) {
        Log.e("水暖加热器返回的数据是", msg);
        if (msg.contains("j_s")) {
            sn_state = msg.substring(3, 4);//水暖状态
            String syscTime = msg.substring(4, 7);//加热剩余时长
            //水泵状态  1.工作中2.待机中
            shuibeng_state = msg.substring(7, 8);
            //油泵状态  1.工作中2.待机中
            youbeng_state = msg.substring(8, 9);
            String fengji_state = msg.substring(9, 10);//风机状态  1.工作中2.待机中
            String dianyan = (Y.getInt(msg.substring(10, 14)) / 10.0f) + "";//电压  0253 = 25.3
            String fengjizhuansu = msg.substring(14, 19);//风机转速   13245
            String jairesaigonglv = (Y.getInt(msg.substring(19, 23)) / 10.0f) + "";// 加热塞功率  0264=26.4
            String youbenggonglv = (Y.getInt(msg.substring(23, 27)) / 10.0f) + "";// 油泵频率  0264=26.4
            String rushukowendu = (Y.getInt(msg.substring(27, 30)) - 50) + "";// 入水口温度（℃）  -50至150（000 = -50，100 = 50）
            String chushuikowendu = (Y.getInt(msg.substring(30, 33)) - 50) + "";// 出水口温度（℃）  -50至150（000 = -50，100 = 50）
            String weiqiwendu = (Y.getInt(msg.substring(33, 37)) - 50) + "";// 尾气温度（℃）  -50至2000（000 = -50，100 = 50）
            String danqiandangwei = msg.substring(37, 38);// 1.一档2.二档（注：用*占位）
            yushewendu = msg.substring(38, 40);//预设温度（℃） 预设温度（℃）
            String zongTime = msg.substring(40, 45);//总时长 （小时）
            String daqiya = msg.substring(45, 48);//大气压
            String haibagaodu = msg.substring(48, 52);//海拔高度
            String hanyangliang = msg.substring(52, 55);//含氧量

            boolean isTTT = true;

            if (isTTT) {
                firstCaozuo();
            }


            if (sn_state.equals("0") || sn_state.equals("3")) {
                if (shuibeng_state.equals("1") && youbeng_state.equals("2")) {
                    shubengIson = true;
                } else {
                    shubengIson = false;
                }

                if (youbeng_state.equals("1") && shuibeng_state.equals("2")) {
                    youbengIson = true;
                } else {
                    youbengIson = false;
                }
            }

            xinhaoStr = msg.substring(55, 57);
            if (xinhaoStr.equals("aa")) {
                iv_xinhao.setImageResource(R.mipmap.fengnuan_icon_signal2);
                tv_zaixian.setText("在线");
            } else {
                int xinhao = Y.getInt(xinhaoStr);//信号强度
                if (xinhao >= 15 && xinhao <= 19) {
                    iv_xinhao.setImageResource(R.mipmap.fengnuan_icon_signal2);
                    tv_zaixian.setText("在线");
                } else if (xinhao >= 20 && xinhao <= 25) {
                    iv_xinhao.setImageResource(R.mipmap.fengnuan_icon_signal3);
                    tv_zaixian.setText("在线");
                } else if (xinhao >= 26 && xinhao <= 35) {
                    iv_xinhao.setImageResource(R.mipmap.fengnuan_icon_signal4);
                    tv_zaixian.setText("在线");
                } else {
                    iv_xinhao.setImageResource(R.mipmap.fengnuan_icon_signal1);
                    tv_zaixian.setText("在线");
                }
            }

            String num = "水暖状态" + sn_state + "  加热剩余时长" + syscTime + "  水泵状态" + shuibeng_state + "  油泵状态" + youbeng_state
                    + "  风机状态" + fengji_state
                    + "  电压" + dianyan
                    + "  风机转速" + fengjizhuansu
                    + "  加热塞功率" + jairesaigonglv
                    + "  油泵频率" + youbenggonglv
                    + "    入水口温度" + rushukowendu
                    + "    出水口温度" + chushuikowendu
                    + "    尾气温度" + weiqiwendu
                    + "    尾气温度" + weiqiwendu
                    + "  一档二挡" + danqiandangwei
                    + "  总时长" + zongTime + "   大气压" + daqiya + "    海拔高度" + haibagaodu + "  含氧量" + hanyangliang
                    + "  信号强度" + xinhaoStr;
            Y.e(num);
            tv_dianya.setText(dianyan + "v");
            tv_jinshuikou_wendu.setText(rushukowendu + "℃");
            tv_chushuikou_wendu.setText(chushuikowendu + "℃");
            tv_haibagaodu.setText(haibagaodu + "m");
            tv_hanyangliang.setText(hanyangliang + "kg/cm3");
            tv_daqiya.setText(daqiya + "kpa");
            tv_wendu_yushe.setText("设定温度:" + yushewendu + "℃");
            tv_wendu_dangqian.setText("当前温度:" + chushuikowendu + "℃");
            isZaixian = true;

            switch (sn_state) {
                case "1"://开机中
                    iv_shuinuan_kaijie.setVisibility(View.VISIBLE);
                    iv_shuinuan_guanji.setVisibility(View.GONE);
                    tv_shuinuan_kaiji.setTextColor(Y.getColor(R.color.text_color_blue));
                    tv_shuinuan_guanji.setTextColor(Y.getColor(R.color.white));
                    rv_shuinuan_kaiji.setSelected(true);
                    rv_shuinuan_guanji.setSelected(false);
                    isKaiji = true;
                    tv_shebei_state.setText("加热器状态：开机");
                    iv_heater_host.setBackgroundResource(R.drawable.shuinuan_kaiji);
                    animationDrawable = (AnimationDrawable) iv_heater_host.getBackground();
                    animationDrawable.start();
                    break;
                case "2"://加热中
                    iv_shuinuan_kaijie.setVisibility(View.VISIBLE);
                    iv_shuinuan_guanji.setVisibility(View.GONE);
                    tv_shuinuan_kaiji.setTextColor(Y.getColor(R.color.text_color_blue));
                    tv_shuinuan_guanji.setTextColor(Y.getColor(R.color.white));
                    rv_shuinuan_kaiji.setSelected(true);
                    rv_shuinuan_guanji.setSelected(false);
                    isKaiji = true;
                    tv_shebei_state.setText("加热器状态：加热中");
                    iv_heater_host.setBackgroundResource(R.drawable.shuinuan_kaiji);
                    animationDrawable = (AnimationDrawable) iv_heater_host.getBackground();
                    animationDrawable.start();
                    break;
                case "4"://循环水
                    iv_shuinuan_kaijie.setVisibility(View.VISIBLE);
                    iv_shuinuan_guanji.setVisibility(View.GONE);
                    tv_shuinuan_kaiji.setTextColor(Y.getColor(R.color.text_color_blue));
                    tv_shuinuan_guanji.setTextColor(Y.getColor(R.color.white));
                    rv_shuinuan_kaiji.setSelected(true);
                    rv_shuinuan_guanji.setSelected(false);
                    isKaiji = true;
                    tv_shebei_state.setText("加热器状态：循环水");
                    iv_heater_host.setBackgroundResource(R.drawable.shuinuan_kaiji);
                    animationDrawable = (AnimationDrawable) iv_heater_host.getBackground();
                    animationDrawable.start();
                    break;
                case "0"://关机中
                case "3"://待机中
                    iv_shuinuan_kaijie.setVisibility(View.GONE);
                    iv_shuinuan_guanji.setVisibility(View.VISIBLE);
                    tv_shuinuan_kaiji.setTextColor(Y.getColor(R.color.white));
                    tv_shuinuan_guanji.setTextColor(Y.getColor(R.color.text_color_blue));
                    rv_shuinuan_kaiji.setSelected(false);
                    rv_shuinuan_guanji.setSelected(true);
                    isKaiji = false;
                    tv_shebei_state.setText("加热器状态：关机");
                    iv_heater_host.setBackgroundResource(R.drawable.shuinuan_guanji);
                    break;
            }

            switch (shuibeng_state) {
                case "1":
                    tv_shuinuan_shuibeng.setText("水泵已开启");
                    tv_shuinuan_shuibeng.setTextColor(Y.getColor(R.color.text_color_blue));
                    break;
                case "2":
                    tv_shuinuan_shuibeng.setText("水泵已关闭");
                    tv_shuinuan_shuibeng.setTextColor(Y.getColor(R.color.text_color_9));
                    break;
            }

            switch (youbeng_state) {
                case "1":
                    tv_shuinuan_youbeng.setText("油泵已开启");
                    tv_shuinuan_youbeng.setTextColor(Y.getColor(R.color.text_color_blue));
                    break;
                case "2":
                    tv_shuinuan_youbeng.setText("油泵已关闭");
                    tv_shuinuan_youbeng.setTextColor(Y.getColor(R.color.text_color_9));
                    break;
            }
        } else if (msg.contains("k_s")) {
            String code = msg.substring(3, 5);
            if (code.equals("01")) {
                String zhuantai = msg.substring(5, 6);
                if (zhuantai.equals("1")) {
                    Log.i("操作成功", "");
                } else {
                    Log.i("操作失败", "");
                }
            } else {

            }
        } else if (msg.contains("M_s")) {
            String code = msg.substring(3, 5);
            if (code.equals("01")) {

            } else if (code.equals("02")) {

            }
        } else if (msg.contains("g_s.")) {
            if (!isZaixian) {
                isZaixian = true;
                iv_xinhao.setImageResource(R.mipmap.fengnuan_icon_signal1);
                tv_zaixian.setText("在线");
            }
        } else if (msg.contains("r_s")) {
            String dianya = msg.substring(3, 4);//电压	0.正常1.过高2.过低3.故障
            String youbeng = msg.substring(4, 5);//油泵	0.正常1.开路2.短路3.故障
            String shuibeng = msg.substring(5, 6);//水泵	0.正常1.开路2.短路3.过流4.堵转5.故障
            String chushuiko = msg.substring(6, 7);//出水口	0.正常1.开路2.短路3.高温4.故障
            String rushuiko = msg.substring(7, 8);//入水口	0.正常1.开路2.短路3.高温4.故障
            String wensheng = msg.substring(8, 9);//温升	0.正常1.异常
            String fengji = msg.substring(9, 10);//风机	0.正常1.开路2.短路3.过流4.堵转5.故障
            String chufengko = msg.substring(10, 11);//出风口	0.正常1.开路2.短路3.高温4.故障
            String dianhuosai = msg.substring(11, 12);//点火塞	0.正常1.开路2.短路3.故障
            String houyan = msg.substring(12, 13);//火焰	0.正常1.熄火
            String dianhuo = msg.substring(13, 14);//点火	0.正常1.失败

            List<String> guzhangs = new ArrayList<>();

            if (dianya.equals("1")) {
                guzhangs.add("故障报警：电压过高");
            } else if (dianya.equals("2")) {
                guzhangs.add("故障报警：电压过低");
            } else if (dianya.equals("3")) {
                guzhangs.add("故障报警：电压故障");
            }

            if (youbeng.equals("1")) {
                guzhangs.add("故障报警：油泵开路");
            } else if (youbeng.equals("2")) {
                guzhangs.add("故障报警：油泵短路");
            } else if (youbeng.equals("3")) {
                guzhangs.add("故障报警：油泵故障");
            }

            if (shuibeng.equals("1")) {
                guzhangs.add("故障报警：水泵开路");
            } else if (shuibeng.equals("2")) {
                guzhangs.add("故障报警：水泵短路");
            } else if (shuibeng.equals("3")) {
                guzhangs.add("故障报警：水泵过流");
            } else if (shuibeng.equals("4")) {
                guzhangs.add("故障报警：水泵堵转");
            } else if (shuibeng.equals("5")) {
                guzhangs.add("故障报警：水泵故障");
            }

            if (chushuiko.equals("1")) {
                guzhangs.add("故障报警：出水口开路");
            } else if (chushuiko.equals("2")) {
                guzhangs.add("故障报警：出水口短路");
            } else if (chushuiko.equals("3")) {
                guzhangs.add("故障报警：出水口高温");
            } else if (chushuiko.equals("4")) {
                guzhangs.add("故障报警：出水口故障");
            }

            if (rushuiko.equals("1")) {
                guzhangs.add("故障报警：入水口开路");
            } else if (rushuiko.equals("2")) {
                guzhangs.add("故障报警：入水口短路");
            } else if (rushuiko.equals("3")) {
                guzhangs.add("故障报警：入水口高温");
            } else if (rushuiko.equals("4")) {
                guzhangs.add("故障报警：入水口故障");
            }

            if (wensheng.equals("1")) {
                guzhangs.add("故障报警：升温异常");
            }

            if (fengji.equals("1")) {
                guzhangs.add("故障报警：风机开路");
            } else if (fengji.equals("2")) {
                guzhangs.add("故障报警：风机短路");
            } else if (fengji.equals("3")) {
                guzhangs.add("故障报警：风机过流");
            } else if (fengji.equals("4")) {
                guzhangs.add("故障报警：风机堵转");
            } else if (fengji.equals("5")) {
                guzhangs.add("故障报警：风机故障");
            }

            if (chufengko.equals("1")) {
                guzhangs.add("故障报警：出风口开路");
            } else if (chufengko.equals("2")) {
                guzhangs.add("故障报警：出风口短路");
            } else if (chufengko.equals("3")) {
                guzhangs.add("故障报警：出风口高温");
            } else if (chufengko.equals("4")) {
                guzhangs.add("故障报警：出风口故障");
            }

            if (dianhuosai.equals("1")) {
                guzhangs.add("故障报警：点火塞开路");
            } else if (dianhuosai.equals("2")) {
                guzhangs.add("故障报警：点火塞短路");
            } else if (dianhuosai.equals("3")) {
                guzhangs.add("故障报警：点火塞故障");
            }

            if (houyan.equals("1")) {
                guzhangs.add("故障报警：火焰熄火");
            }

            if (dianhuo.equals("1")) {
                guzhangs.add("故障报警  ：点火失败");
            }

            if (guzhangs.size() > 0) {
                showguzhangla(guzhangs);
            }
        }
    }

    private void firstCaozuo() {
        if (isFirst) {
            //向水暖加热器发送获取实时数据
            AndMqtt.getInstance().publish(new MqttPublish()
                    .setMsg("X_s.")
                    .setQos(2).setRetained(false)
                    .setTopic(SN_Send), new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Y.i("app端向水暖加热器请求实时数据");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });

            AndMqtt.getInstance().publish(new MqttPublish()
                    .setMsg("Y_s.")
                    .setQos(2).setRetained(false)
                    .setTopic(SN_Send), new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Y.i("查询一次经纬度");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });

            AndMqtt.getInstance().publish(new MqttPublish()
                    .setMsg("Z_s.")
                    .setQos(2).setRetained(false)
                    .setTopic(SN_Send), new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Y.i("查询故障/报警");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });

            initHandlerNS();
            isFirst = false;
        }

    }

    private void initHandlerNS() {
        Message message = handlerTime10.obtainMessage(1);
        handlerTime10.sendMessageDelayed(message, 10000);
    }

    private void showguzhangla(List<String> strings) {
        if (guzhangDialog == null) {
            guzhangDialog = new GuzhangDialog(mContext, new GuzhangDialog.Guzhang() {
                @Override
                public void onClickConfirm(View v, GuzhangDialog dialog) {
                    dealGuzhang();
                }

                @Override
                public void onDismiss(GuzhangDialog dialog) {

                }
            });
        }
        guzhangDialog.showDD(strings);
    }

    private void dealGuzhang() {
        String data = "M_s071";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
                .setQos(2).setRetained(false)
                .setTopic(SN_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    /**
     * 注册订阅Mqtt
     */
    private void registerKtMqtt() {
        showProgressDialog("设备连接中...");
        initHandlerStart();
        getNs();
    }

    private void getNs() {
        //注册水暖加热器订阅
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(SN_Send)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

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

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
        getNsData();
    }

    private void getNsData() {
        //向水暖加热器发送获取实时数据
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("N_s.")
                .setQos(2).setRetained(false)
                .setTopic(SN_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("app端向水暖加热器请求实时数据", "");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    private int time = 0;

    private void initHandlerStart() {
        Message message = handlerStart.obtainMessage(1);
        handlerStart.sendMessageDelayed(message, 250);
    }

    private void initHandlerClick() {
        Message message = handlerStart.obtainMessage(2);
        handlerStart.sendMessageDelayed(message, 250);
    }

    private void initHandlerShuibeng() {
        Message message = handlerStart.obtainMessage(3);
        handlerStart.sendMessageDelayed(message, 250);
    }

    private void initHandlerYoubeng() {
        Message message = handlerStart.obtainMessage(4);
        handlerStart.sendMessageDelayed(message, 250);
    }


    private Handler handlerStart = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    time++;
                    if (!isZaixian) {
                        if (time >= 60) {
                            showTishiDialog();
                        } else {
                            if (time == 4 || time == 24 || time == 44) {
                                getNs();
                            }
                            initHandlerStart();
                        }
                    } else {
                        dismissProgressDialog();
                        time = 0;
                    }
                    Y.i("计时是多少啊啊啊" + time);
                    break;
                case 2:
                    time++;
                    if (iskaijiDianhou != isKaiji) {
                        if (time >= 60) {
                            iskaijiDianhou = !iskaijiDianhou;
                            showZhiling();
                        } else {
                            if (time == 4 || time == 24 || time == 44) {
                                getNs();
                            }
                            initHandlerClick();
                        }
                    } else {
                        if (iskaijiDianhou) {
                            SoundPoolUtils.soundPool(mContext, R.raw.shuinuan_end_on);
                        } else {
                            SoundPoolUtils.soundPool(mContext, R.raw.shuinuan_end_off);
                        }

                        dismissProgressDialog();
                        time = 0;
                    }
                    Y.i("计时是多少啊啊啊" + time);
                    break;
                case 3:
                    time++;
//                    if (shubengIson) {
//                        if (shuibeng_state.equals("1")) {
//                            dismissProgressDialog();
//                            time = 0;
//                        } else {
//                            if (time >= 60) {
//                                shubengIson = false;
//                                showZhiling();
//                            } else {
//                                if (time == 4 || time == 24 || time == 44) {
//                                    getNs();
//                                }
//                                initHandlerShuibeng();
//                            }
//                        }
//                    } else {
//                        if (shuibeng_state.equals("2")) {
//                            dismissProgressDialog();
//                            time = 0;
//                        } else {
//                            if (time >= 60) {
//                                shubengIson = true;
//                                showZhiling();
//                            } else {
//                                if (time == 4 || time == 24 || time == 44) {
//                                    getNs();
//                                }
//                                initHandlerShuibeng();
//                            }
//                        }
//                    }

                    if (shoubengisdianhou != shubengIson) {
                        if (time >= 60) {
                            shoubengisdianhou = !shoubengisdianhou;
                            showZhiling();
                        } else {
                            if (time == 4 || time == 24 || time == 44) {
                                getNs();
                            }
                            initHandlerShuibeng();
                        }
                    } else {
                        if (shoubengisdianhou) {
                            SoundPoolUtils.soundPool(mContext, R.raw.shuinuan_shuibeng_on_a);
                        } else {
                            SoundPoolUtils.soundPool(mContext, R.raw.shuinuan_shuibeng_off_a);
                        }

                        dismissProgressDialog();
                        time = 0;
                    }
                    Y.i("水泵的计时是多少啊啊啊" + time + "   " + shubengIson);
                    break;
                case 4:
                    time++;
//                    if (youbengIson) {
//                        if (youbeng_state.equals("1")) {
//                            dismissProgressDialog();
//                            time = 0;
//                        } else {
//                            if (time >= 60) {
//                                youbengIson = false;
//                                showZhiling();
//                            } else {
//                                if (time == 4 || time == 24 || time == 44) {
//                                    getNs();
//                                }
//                                initHandlerYoubeng();
//                            }
//                        }
//                    } else {
//                        if (youbeng_state.equals("2")) {
//                            dismissProgressDialog();
//                            time = 0;
//                        } else {
//                            if (time >= 60) {
//                                youbengIson = true;
//                                showZhiling();
//                            } else {
//                                if (time == 4 || time == 24 || time == 44) {
//                                    getNs();
//                                }
//                                initHandlerYoubeng();
//                            }
//                        }
//                    }

                    if (youbengIsdianhou != youbengIson) {
                        if (time >= 60) {
                            youbengIsdianhou = !youbengIsdianhou;
                            showZhiling();
                        } else {
                            if (time == 4 || time == 24 || time == 44) {
                                getNs();
                            }
                            initHandlerYoubeng();
                        }
                    } else {
                        if (youbengIsdianhou) {
                            SoundPoolUtils.soundPool(mContext, R.raw.shuinuan_youbeng_on_a);
                        } else {
                            SoundPoolUtils.soundPool(mContext, R.raw.shuinuan_youbeng_off_a);
                        }

                        dismissProgressDialog();
                        time = 0;
                    }
                    Y.i("油泵的计时是多少啊啊啊" + time);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void showTishiDialog() {
        tv_zaixian.setText("离线");
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
        tishiDialog.setTextCancel("关闭");
        tishiDialog.show();
    }

    private void showZhiling() {
        showTishiDialog();
//        tv_zaixian.setText("离线");
//        time = 0;
//        dismissProgressDialog();
//        isZaixian = false;
//        TishiDialog tishiDialog = new TishiDialog(mContext, TishiDialog.TYPE_CAOZUO, new TishiDialog.TishiDialogListener() {
//            @Override
//            public void onClickCancel(View v, TishiDialog dialog) {
//
//            }
//
//            @Override
//            public void onClickConfirm(View v, TishiDialog dialog) {
//                getNs();
//
//                if (zhilingma == zhiling_kaiji) {
//                    kaiji();
//                } else if (zhilingma == zhiling_guanji) {
//                    guanji();
//                } else if (zhilingma == zhiling_shuibeng) {
//                    shuibeng();
//                } else if (zhilingma == zhiling_youbeng) {
//                    youbeng();
//                }
//            }
//
//            @Override
//            public void onDismiss(TishiDialog dialog) {
//
//            }
//        });
//
//        tishiDialog.setTextTitle("提示");
//        tishiDialog.setTextContent("指令发送失败，是否重新发送？");
//        tishiDialog.setTextConfirm("重试");
//        tishiDialog.setTextCancel("关闭");
//        tishiDialog.show();
    }


    @OnClick({R.id.rl_back, R.id.rl_set, R.id.iv_shebeima})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_set:
                SheBeiSetActivity.actionStart(mContext, SheBeiSetActivity.TYPE_SHUINUAN);
                break;
            case R.id.iv_shebeima:
                showShebeima();
                break;
        }
    }

    private void showShebeima() {
        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_CAOZUO, new TishiDialog.TishiDialogListener() {
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
        dialog.setTextTitle("设备码");
        dialog.setTextContent(ccid);
        dialog.setTextCancel("");
        dialog.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerStart.removeMessages(1);
        handlerStart.removeMessages(2);
        handlerStart.removeMessages(3);
        handlerStart.removeMessages(4);
        handlerTime10.removeMessages(1);

        AndMqtt.getInstance().unSubscribe(new MqttUnSubscribe().setTopic(SN_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });

        AndMqtt.getInstance().unSubscribe(new MqttUnSubscribe().setTopic(SN_Accept), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });

        for (int i = 0; i < MyApplication.mqttDingyue.size(); i++) {
            if (MyApplication.mqttDingyue.get(i).equals(SN_Send)) {
                MyApplication.mqttDingyue.remove(i);
            }
        }

        for (int i = 0; i < MyApplication.mqttDingyue.size(); i++) {
            if (MyApplication.mqttDingyue.get(i).equals(SN_Accept)) {
                MyApplication.mqttDingyue.remove(i);
            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (!isZaixian) {
            showTishiDialog();
            return false;
        } else {
            switch (v.getId()) {
                case R.id.rv_shuinuan_kaiji:
                    kaiji();
                    break;
                case R.id.rv_shuinuan_guanji:
                    guanji();
                    break;
                case R.id.tv_shuinuan_shuibeng:
                    shuibeng();
                    break;
                case R.id.tv_shuinuan_youbeng:
                    youbeng();
                    break;
            }
            return false;
        }
    }

    private void youbeng() {
        if (isKaiji) {
            return;
        }

        if (shuibeng_state.equals("1")) {
            return;
        }

        showProgressDialog("发送指令中...");
        initHandlerYoubeng();
        zhilingma = zhiling_youbeng;
        if (youbeng_state.equals("1")) {
            youbengIsdianhou = false;
            String data = "M_s052.";
            SoundPoolUtils.soundPool(mContext, R.raw.shuinuan_youbeng_off);
            AndMqtt.getInstance().publish(new MqttPublish()
                    .setMsg(data)
                    .setQos(2).setRetained(false)
                    .setTopic(SN_Send), new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        } else {
            youbengIsdianhou = true;
            String data = "M_s051.";
            SoundPoolUtils.soundPool(mContext, R.raw.shuinuan_youbeng_on);
            AndMqtt.getInstance().publish(new MqttPublish()
                    .setMsg(data)
                    .setQos(2).setRetained(false)
                    .setTopic(SN_Send), new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        }
    }

    private void shuibeng() {
        if (isKaiji) {
            return;
        }

        if (youbeng_state.equals("1")) {
            return;
        }

        showProgressDialog("发送指令中...");
        initHandlerShuibeng();
        zhilingma = zhiling_shuibeng;
        if (shuibeng_state.equals("1")) {
            shoubengisdianhou = false;
            String data = "M_s022.";
            SoundPoolUtils.soundPool(mContext, R.raw.shuinuan_shuibeng_off);
            AndMqtt.getInstance().publish(new MqttPublish()
                    .setMsg(data)
                    .setQos(2).setRetained(false)
                    .setTopic(SN_Send), new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        } else {
            shoubengisdianhou = true;
            String data = "M_s021.";
            SoundPoolUtils.soundPool(mContext, R.raw.shuinuan_shuibeng_on);
            AndMqtt.getInstance().publish(new MqttPublish()
                    .setMsg(data)
                    .setQos(2).setRetained(false)
                    .setTopic(SN_Send), new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        }
    }

    private void guanji() {
        if (!isKaiji) {
            return;
        }

        showProgressDialog("发送指令中...");
        initHandlerClick();

        iskaijiDianhou = false;
        zhilingma = zhiling_guanji;
        SoundPoolUtils.soundPool(mContext, R.raw.shuinuan_start_off);
        String data = "M_s012.";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
                .setQos(2).setRetained(false)
                .setTopic(SN_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    private void kaiji() {
        if (isKaiji) {
            return;
        }


        if (shubengIson) {
            Y.t("水泵模式下无法开机");
            return;
        }

        if (youbengIson) {
            Y.t("油泵模式下无法开机");
            return;
        }

        showProgressDialog("发送指令中...");
        initHandlerClick();

        iskaijiDianhou = true;
        zhilingma = zhiling_kaiji;

        String data = "M_s011000080.";
        SoundPoolUtils.soundPool(mContext, R.raw.shuinuan_start_on);
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
                .setQos(2).setRetained(false)
                .setTopic(SN_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }


    public static String getTime(long seconds) {
        String format = "HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(seconds));
    }
}
