package com.sdkj.heaterbluetooth.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.app.ConstanceValue;
import com.sdkj.heaterbluetooth.app.Notice;
import com.sdkj.heaterbluetooth.common.UIHelper;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.sdkj.heaterbluetooth.app.MyApplication.CAR_CTROL;


public class RatioActivity extends BaseActivity implements View.OnTouchListener {

    @BindView(R.id.back)
    LinearLayout mBack;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.rl_outer_one)
    RelativeLayout mRlOuterOne;
    @BindView(R.id.item_one)
    LinearLayout mItemOne;
    @BindView(R.id.rl_outer_two)
    RelativeLayout mRlOuterTwo;
    @BindView(R.id.item_two)
    LinearLayout mItemTwo;
    @BindView(R.id.rl_outer_three)
    RelativeLayout mRlOuterThree;
    @BindView(R.id.item_three)
    LinearLayout mItemThree;
    @BindView(R.id.rl_outer_four)
    RelativeLayout mRlOuterFour;
    @BindView(R.id.item_four)
    LinearLayout mItemFour;
    @BindView(R.id.rl_outer_five)
    RelativeLayout mRlOuterFive;
    @BindView(R.id.item_five)
    LinearLayout mItemFive;
    @BindView(R.id.rl_outer_stop)
    RelativeLayout mRlOuterStop;
    @BindView(R.id.item_stop)
    LinearLayout mItemStop;
    @BindView(R.id.tv_two)
    TextView mTvTwo;
    @BindView(R.id.tv_three)
    TextView mTvThree;
    @BindView(R.id.tv_five)
    TextView mTvFive;
    @BindView(R.id.tv_recovery)
    TextView mTvRecovery;

    Animation rotate;
    @BindView(R.id.et_speed_one)
    EditText mEtSpeedOne;
    @BindView(R.id.et_frequency_one)
    EditText mEtFrequencyOne;
    @BindView(R.id.et_speed_two)
    EditText mEtSpeedTwo;
    @BindView(R.id.et_frequency_two)
    EditText mEtFrequencyTwo;
    @BindView(R.id.et_speed_three)
    EditText mEtSpeedThree;
    @BindView(R.id.et_frequency_three)
    EditText mEtFrequencyThree;
    @BindView(R.id.et_speed_four)
    EditText mEtSpeedFour;
    @BindView(R.id.et_frequency_four)
    EditText mEtFrequencyFour;
    @BindView(R.id.et_speed_five)
    EditText mEtSpeedFive;
    @BindView(R.id.et_frequency_five)
    EditText mEtFrequencyFive;
    List<RelativeLayout> list = new ArrayList<>();
    @BindView(R.id.et_heat)
    EditText mEtHeat;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle1;
    String version;//版本号
    String GongLv12;
    String GongLv24;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_ratio;
    }

    @Override
    public void initImmersion() {
        super.initImmersion();
        mImmersionBar.with(this).statusBarColor(R.color.blue_3a96e9).init();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        mEtFrequencyOne.setOnTouchListener(this);
        mEtSpeedOne.setOnTouchListener(this);
        mEtFrequencyTwo.setOnTouchListener(this);
        mEtSpeedTwo.setOnTouchListener(this);
        mEtSpeedThree.setOnTouchListener(this);
        mEtFrequencyThree.setOnTouchListener(this);
        mEtSpeedFour.setOnTouchListener(this);
        mEtSpeedFour.setOnTouchListener(this);
        mEtSpeedFive.setOnTouchListener(this);
        mEtFrequencyFive.setOnTouchListener(this);
        mEtHeat.setOnTouchListener(this);
        list.add(0, mRlOuterOne);
        list.add(1, mRlOuterTwo);
        list.add(2, mRlOuterThree);
        list.add(3, mRlOuterFour);
        list.add(4, mRlOuterFive);
        list.add(5, mRlOuterStop);

        setMySpeed(mEtSpeedOne, "1100", "2400");
        setMySpeed(mEtSpeedTwo, "1800", "3000");
        setMySpeed(mEtSpeedThree, "2800", "4000");
        setMySpeed(mEtSpeedFour, "3200", "4200");
        setMySpeed(mEtSpeedFive, "3400", "4600");
        setYouBengSpeed(mEtFrequencyOne, "0.60", "2.20");
        setYouBengSpeed(mEtFrequencyTwo, "1.00", "3.20");
        setYouBengSpeed(mEtFrequencyThree, "1.80", "4.20");
        setYouBengSpeed(mEtFrequencyFour, "2.70", "5.20");
        setYouBengSpeed(mEtFrequencyFive, "3.40", "6.00");

        //订阅和请求 mqtt  查询风油比参数

        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("M511.")
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

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_CAR_FEGNYOUBI) {//获得风油比 数据
                    //接收到信息
                    Log.i("fengyoubi", message.content.toString());


                    //获得数据后填充到页面中

                    String zhuJiCanShu = message.content.toString().substring(1, 2);//第一个数字

                    if (zhuJiCanShu != null) {
                        if (zhuJiCanShu.equals("0")) {//2k
                            mTvTwo.setBackground(getResources().getDrawable(R.drawable.btn_heater_check));
                            mTvFive.setBackground(getResources().getDrawable(R.drawable.btn_heater_normal));
                        } else if (zhuJiCanShu.equals("1")) {//5k
                            mTvFive.setBackground(getResources().getDrawable(R.drawable.btn_heater_check));
                            mTvTwo.setBackground(getResources().getDrawable(R.drawable.btn_heater_normal));
                        }
                    }


                    GongLv12 = message.content.toString().substring(2, 4);//后两个

                    GongLv24 = message.content.toString().substring(4, 6);//后两个

                    Log.i("fengyoubi", "第一个参数:" + zhuJiCanShu + "    " + "第二个参数:" + GongLv12 + "   " + "第三个参数 :" + GongLv24);

                    String fengJiZhuanSu1Dang = message.content.toString().substring(6, 10);//风机转速1挡

                    String youBengPinLv = message.content.toString().substring(10, 13);//油泵频率

                    String fengJiZhuanSu2Dang = message.content.toString().substring(13, 17);//风机转速2挡


                    String youBengPinLv2Dang = message.content.toString().substring(17, 20);//2档：油泵频率


                    String fengJiZhuanSu3Dang = message.content.toString().substring(20, 24);//3档：风机转速

                    String youBengPinLv3Dang = message.content.toString().substring(24, 27);//3档：油泵频率

                    String fengJiZhuanSu4Dang = message.content.toString().substring(27, 31);//4档：风机转速

                    String youBengPinLv4Dang = message.content.toString().substring(31, 34);//4档：油泵频率	(1.55=155)取值范围：0.50-6.00	3	是

                    String fengJiZhuanSu5Dang = message.content.toString().substring(34, 38);//5档：风机转速	取值范围：600-5500	4	是

                    String youBengpinLv5Dang = message.content.toString().substring(38, 41);//5档：油泵频率	(1.55=155)取值范围：0.50-6.00	3	是

                    version = message.content.toString().substring(41, 45);//版本号4位	2018  2019 版本号	4	是

                    Log.i("fengyoubi", "版本号：" + version);


                    //展示数据
                    if (fengJiZhuanSu1Dang != null) {

                        for (int i = 0; i < fengJiZhuanSu1Dang.length() - 1; i++) {
                            if (fengJiZhuanSu1Dang.charAt(0) == '0') {
                                fengJiZhuanSu1Dang = fengJiZhuanSu1Dang.substring(1);
                                break;
                            }
                        }
                        mEtSpeedOne.setText(fengJiZhuanSu1Dang);
                    }

                    //展示数据
                    if (fengJiZhuanSu2Dang != null) {

                        for (int i = 0; i < fengJiZhuanSu2Dang.length() - 1; i++) {
                            if (fengJiZhuanSu2Dang.charAt(0) == '0') {
                                fengJiZhuanSu2Dang = fengJiZhuanSu2Dang.substring(1);
                                break;
                            }
                        }
                        mEtSpeedTwo.setText(fengJiZhuanSu2Dang);
                    }


                    //展示数据
                    if (fengJiZhuanSu3Dang != null) {

                        for (int i = 0; i < fengJiZhuanSu3Dang.length() - 1; i++) {
                            if (fengJiZhuanSu3Dang.charAt(0) == '0') {
                                fengJiZhuanSu3Dang = fengJiZhuanSu3Dang.substring(1);
                                break;
                            }
                        }
                        mEtSpeedThree.setText(fengJiZhuanSu3Dang);
                    }

                    //展示数据
                    if (fengJiZhuanSu4Dang != null) {
                        for (int i = 0; i < fengJiZhuanSu4Dang.length() - 1; i++) {
                            if (fengJiZhuanSu4Dang.charAt(0) == '0') {
                                fengJiZhuanSu4Dang = fengJiZhuanSu4Dang.substring(1);
                                break;
                            }
                        }
                        mEtSpeedFour.setText(fengJiZhuanSu4Dang);
                    }

                    //展示数据
                    if (fengJiZhuanSu5Dang != null) {
                        for (int i = 0; i < fengJiZhuanSu5Dang.length(); i++) {
                            if (fengJiZhuanSu5Dang.charAt(0) == '0') {
                                fengJiZhuanSu5Dang = fengJiZhuanSu5Dang.substring(1);
                                break;
                            }
                        }
                        mEtSpeedFive.setText(fengJiZhuanSu5Dang);
                    }


                    //展示数据
                    if (youBengPinLv != null) {

                        for (int i = 0; i < youBengPinLv.length(); i++) {
                            if (youBengPinLv.charAt(0) == '0') {//首字母等于0
                                youBengPinLv = youBengPinLv.substring(1);
                                break;
                            }
                        }

                        String str1 = youBengPinLv.substring(0, 1);
                        String str2 = youBengPinLv.substring(1);

                        String myValue = str1 + "." + str2;

                        // UIHelper.ToastMessage(RatioActivity.this, myValue, Toast.LENGTH_SHORT);
                        mEtFrequencyOne.setText(myValue);
                    }

                    //展示数据
                    if (youBengPinLv2Dang != null) {

                        for (int i = 0; i < youBengPinLv2Dang.length(); i++) {
                            if (youBengPinLv2Dang.charAt(0) == '0') {//首字母等于0
                                youBengPinLv2Dang = youBengPinLv2Dang.substring(1);
                                break;
                            }
                        }

                        String str1 = youBengPinLv2Dang.substring(0, 1);
                        String str2 = youBengPinLv2Dang.substring(1);

                        String myValue = str1 + "." + str2;
                        mEtFrequencyTwo.setText(myValue);
                    }

                    //展示数据
                    if (youBengPinLv3Dang != null) {

                        for (int i = 0; i < youBengPinLv3Dang.length(); i++) {
                            if (youBengPinLv3Dang.charAt(0) == '0') {//首字母等于0
                                youBengPinLv3Dang = youBengPinLv3Dang.substring(1);
                                break;
                            }
                        }

                        String str1 = youBengPinLv3Dang.substring(0, 1);
                        String str2 = youBengPinLv3Dang.substring(1);

                        String myValue = str1 + "." + str2;
                        mEtFrequencyThree.setText(myValue);
                    }

                    //展示数据
                    if (youBengPinLv4Dang != null) {

                        for (int i = 0; i < youBengPinLv4Dang.length(); i++) {
                            if (youBengPinLv4Dang.charAt(0) == '0') {//首字母等于0
                                youBengPinLv4Dang = youBengPinLv4Dang.substring(1);
                                break;
                            }
                        }

                        String str1 = youBengPinLv4Dang.substring(0, 1);
                        String str2 = youBengPinLv4Dang.substring(1);

                        String myValue = str1 + "." + str2;

                        mEtFrequencyFour.setText(myValue);
                    }

                    //展示数据
                    if (youBengpinLv5Dang != null) {

                        for (int i = 0; i < youBengpinLv5Dang.length(); i++) {
                            if (youBengpinLv5Dang.charAt(0) == '0') {//首字母等于0
                                youBengpinLv5Dang = youBengpinLv5Dang.substring(1);
                                break;
                            }
                        }

                        String str1 = youBengpinLv5Dang.substring(0, 1);
                        String str2 = youBengpinLv5Dang.substring(1);

                        String myValue = str1 + "." + str2;

                        mEtFrequencyFive.setText(myValue);
                    }

                    if (version != null) {
                        if (version.equals("2019")) {

                            if (GongLv12 != null) {
                                mEtHeat.setText(GongLv12);
                            }

                        } else {
                            if (GongLv24 != null) {
                                mEtHeat.setText(GongLv24);
                            }
                        }
                    } else {
                        if (GongLv24 != null) {
                            mEtHeat.setText(GongLv24);
                        }
                    }
                }
            }
        }));

        setMySpeed(mEtHeat, "80", "93");
    }

    public void setYouBengSpeed(final EditText mEditText, final String min, final String max) {
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null) {
                    if (!StringUtils.isEmpty(s.toString())) {
                        if (Double.valueOf(s.toString()) > Double.valueOf(max)) {
                            mEditText.setText(max);
                        }
                    }
                }
            }


        });

        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {//焦点在

                } else {

                    if (!StringUtils.isEmpty(mEditText.getText().toString())) {


                        if (Double.valueOf(mEditText.getText().toString()) < Double.valueOf(min)) {
                            mEditText.setText(min);
                            UIHelper.ToastMessage(RatioActivity.this, "您输入的最小值不能少于" + min + "哦", Toast.LENGTH_SHORT);
                        }
                    } else {
                        mEditText.setText(min);
                        UIHelper.ToastMessage(RatioActivity.this, "您输入的最小值不能少于" + min + "哦", Toast.LENGTH_SHORT);
                    }

                    if (mEditText.getText().toString().length() == 3) {
                        String str = mEditText.getText().toString();
                        mEditText.setText(str + "0");
                    }

                }
            }
        });
    }


    public void setMySpeed(final EditText mEditText, final String min, final String max) {
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null) {
                    if (!StringUtils.isEmpty(s.toString())) {
                        if (Integer.parseInt(s.toString()) > Integer.parseInt(max)) {
                            mEditText.setText(max);
                        }
                    }
                }
            }


        });

        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {//焦点在

                } else {

                    if (!StringUtils.isEmpty(mEditText.getText().toString())) {

                        if (Integer.parseInt(mEditText.getText().toString()) < Integer.parseInt(min)) {
                            mEditText.setText(min);
                            UIHelper.ToastMessage(RatioActivity.this, "您输入的最小值不能少于600哦", Toast.LENGTH_SHORT);
                        }
                    } else {
                        mEditText.setText(min);
                        UIHelper.ToastMessage(RatioActivity.this, "您输入的最小值不能少于600哦", Toast.LENGTH_SHORT);
                    }

                }
            }
        });
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RatioActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @OnClick({R.id.back, R.id.tv_save, R.id.item_one, R.id.item_two, R.id.item_three, R.id.item_four, R.id.item_five, R.id.item_stop, R.id.tv_recovery, R.id.tv_two, R.id.tv_three, R.id.tv_five})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.tv_save:

                //获得输入的指令代码 发送给硬件
                String str = "";
//                if (version.equals("2019")) {//判断是否是2019款，12v加热还是24v加热
//                    str = mEtHeat.getText().toString();//加热功率
//                }
                str = str + GongLv12;
                str = str + GongLv24;

                //1挡 4位 风机转速
                String value = "";
                if (mEtSpeedOne.getText().toString().length() == 3) {
                    value = "0" + mEtSpeedOne.getText().toString();
                    str = str + value;
                } else {
                    value = mEtSpeedOne.getText().toString();
                    str = str + value;
                }
                //1挡油泵频率 获得第一位 第三位第四位

                value = mEtFrequencyOne.getText().toString();
                String value1 = value.substring(0, 1);
                String value2 = value.substring(2);
                value = value1 + value2;
                //  UIHelper.ToastMessage(this, value);
                str = str + value;
                //处理位字符串


                //2挡 4位 风机转速

                if (mEtSpeedTwo.getText().toString().length() == 3) {
                    value = "0" + mEtSpeedTwo.getText().toString();
                    str = str + value;
                } else {
                    value = mEtSpeedTwo.getText().toString();
                    str = str + value;
                }
                //1挡油泵频率 获得第一位 第三位第四位

                value = mEtFrequencyTwo.getText().toString();
                value1 = value.substring(0, 1);
                value2 = value.substring(2);
                value = value1 + value2;
                //   UIHelper.ToastMessage(this, value);
                str = str + value;
                //处理位字符串


                //3挡 4位 风机转速

                if (mEtSpeedThree.getText().toString().length() == 3) {
                    value = "0" + mEtSpeedThree.getText().toString();
                    str = str + value;
                } else {
                    value = mEtSpeedThree.getText().toString();
                    str = str + value;
                }
                //1挡油泵频率 获得第一位 第三位第四位

                value = mEtFrequencyThree.getText().toString();
                value1 = value.substring(0, 1);
                value2 = value.substring(2);
                value = value1 + value2;
                UIHelper.ToastMessage(this, value);
                str = str + value;
                //处理位字符串


                //1挡 4位 风机转速

                if (mEtSpeedFour.getText().toString().length() == 3) {
                    value = "0" + mEtSpeedFour.getText().toString();
                    str = str + value;
                } else {
                    value = mEtSpeedFour.getText().toString();
                    str = str + value;
                }
                //1挡油泵频率 获得第一位 第三位第四位

                value = mEtFrequencyFive.getText().toString();
                value1 = value.substring(0, 1);
                value2 = value.substring(2);
                value = value1 + value2;
                UIHelper.ToastMessage(this, value);
                str = str + value;
                //处理位字符串


                //5挡 位 风机转速

                if (mEtSpeedFive.getText().toString().length() == 3) {
                    value = "0" + mEtSpeedFive.getText().toString();
                    str = str + value;
                } else {
                    value = mEtSpeedFive.getText().toString();
                    str = str + value;
                }
                //1挡油泵频率 获得第一位 第三位第四位

                value = mEtFrequencyFive.getText().toString();
                value1 = value.substring(0, 1);
                value2 = value.substring(2);
                value = value1 + value2;
                UIHelper.ToastMessage(this, value);
                str = str + value;
                //处理位字符串


                AndMqtt.getInstance().publish(new MqttPublish()
                        .setMsg("M53" + str + ".")
                        .setQos(2).setRetained(false)
                        .setTopic(CAR_CTROL), new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i("Rair", "(CAR_NOTIFY.java:79)-onSuccess:-&gt;发布成功");
                        UIHelper.ToastMessage(RatioActivity.this, "风油比设置成功", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                    }
                });


                break;
            case R.id.item_one:
                start(0);
                break;
            case R.id.item_two:
                start(1);
                break;
            case R.id.item_three:
                start(2);
                break;
            case R.id.item_four:
                start(3);
                break;
            case R.id.item_five:
                start(4);
                break;
            case R.id.item_stop:
                start(5);
                break;
            case R.id.tv_recovery:
                break;
            case R.id.tv_two:
                mTvTwo.setBackground(null);
                mTvFive.setBackground(null);

                mTvTwo.setBackground(getResources().getDrawable(R.drawable.btn_heater_check));
                mTvFive.setBackground(getResources().getDrawable(R.drawable.btn_heater_normal));
                //  mTvThree.setBackground(getResources().getDrawable(R.drawable.btn_heater_normal));

                //跳转主机页面

                HostActivity.actionStart(this);
                break;
            case R.id.tv_three:
                //   mTvThree.setBackground(getResources().getDrawable(R.drawable.btn_heater_check));
                //  mTvFive.setBackground(getResources().getDrawable(R.drawable.btn_heater_normal));
                //  mTvTwo.setBackground(getResources().getDrawable(R.drawable.btn_heater_normal));
                break;
            case R.id.tv_five:
                mTvTwo.setBackground(null);
                mTvFive.setBackground(null);
                mTvFive.setBackground(getResources().getDrawable(R.drawable.btn_heater_check));
                mTvTwo.setBackground(getResources().getDrawable(R.drawable.btn_heater_normal));
                //    mTvThree.setBackground(getResources().getDrawable(R.drawable.btn_heater_normal));
                HostActivity.actionStart(this);
                break;
        }
    }

    public void start(int index) {
        for (int i = 0; i < list.size(); i++) {
            if (i != index) {
                list.get(i).clearAnimation();
            } else {
                if (list.get(i).getAnimation() == null) {
                    list.get(i).setAnimation(rotate);
                    list.get(i).startAnimation(rotate);
                }

            }

        }
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_UP:
                switch (view.getId()) {
                    case R.id.et_speed_one:
                    case R.id.et_frequency_one:
                        start(0);
                        break;
                    case R.id.et_speed_two:
                    case R.id.et_frequency_two:
                        start(1);
                        break;
                    case R.id.et_speed_three:
                    case R.id.et_frequency_three:
                        start(2);
                        break;
                    case R.id.et_speed_four:
                    case R.id.et_frequency_four:
                        start(3);
                        break;
                    case R.id.et_speed_five:
                    case R.id.et_frequency_five:
                        start(4);
                        break;
                    case R.id.et_heat:
                        start(5);
                        break;
                }
                break;
            case MotionEvent.ACTION_DOWN:

                break;
        }
        return view.performClick();
    }
}
