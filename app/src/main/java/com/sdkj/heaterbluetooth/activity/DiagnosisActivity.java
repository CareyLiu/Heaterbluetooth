package com.sdkj.heaterbluetooth.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.rairmmd.andmqtt.MqttUnSubscribe;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.app.ConstanceValue;
import com.sdkj.heaterbluetooth.app.MyApplication;
import com.sdkj.heaterbluetooth.app.Notice;
import com.sdkj.heaterbluetooth.app.PreferenceHelper;
import com.sdkj.heaterbluetooth.callback.JsonCallback;
import com.sdkj.heaterbluetooth.common.UIHelper;
import com.sdkj.heaterbluetooth.config.AppResponse;
import com.sdkj.heaterbluetooth.config.UserManager;
import com.sdkj.heaterbluetooth.dialog.CustomBaseDialog;
import com.sdkj.heaterbluetooth.dialog.MyCarCaoZuoDialog_CaoZuoTIshi_Clear;
import com.sdkj.heaterbluetooth.dialog.MyCarCaoZuoDialog_Success;
import com.sdkj.heaterbluetooth.getnet.Urls;
import com.sdkj.heaterbluetooth.model.AlarmClass;
import com.sdkj.heaterbluetooth.model.HeaterDetails;
import com.sdkj.heaterbluetooth.model.ServiceModel;


import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.sdkj.heaterbluetooth.app.MyApplication.CARBOX_GETNOW;
import static com.sdkj.heaterbluetooth.app.MyApplication.CAR_CTROL;


/**
 * Created by Sl on 2019/6/25.
 * 故障诊断
 */

public class DiagnosisActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.tv_factory)
    TextView mTvFactory;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_voltage)
    TextView mTvVoltage;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.tv_addr)
    TextView mTvAddr;
    @BindView(R.id.tv_rate)
    TextView mTvRate;
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.layout_message)
    LinearLayout layoutMessage;
    @BindView(R.id.layout_info)
    ConstraintLayout layoutInfo;
    @BindView(R.id.btn_clean)
    Button btnClean;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.rl_consult)
    RelativeLayout rlConsult;
    @BindView(R.id.rl_title)
    RelativeLayout rl_title;
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private CustomBaseDialog dialog;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    private List<ServiceModel.DataBean> list = new ArrayList<>();
    AlarmClass alarmClass;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_diagnosis;
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, DiagnosisActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, AlarmClass alarmClass) {
        Intent intent = new Intent(context, DiagnosisActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("alarmClass", alarmClass);
        context.startActivity(intent);
    }

    @Override
    public void initImmersion() {
        super.initImmersion();
        //  mImmersionBar.with(this).titleBar(rl_title).transparentStatusBar().init();
        mImmersionBar.with(this).statusBarColor(R.color.blue_3a96e9).init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);


        if (!AndMqtt.getInstance().isConneect()) {
            mTvTitle.setText("设备已离线，请检查设备");
            return;
        }

        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(500); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); //
        mIvIcon.setAnimation(animation);
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        // requestData();
        requestData2();
        alarmClass = (AlarmClass) getIntent().getSerializableExtra("alarmClass");
        if (alarmClass != null) {
            Log.i("alarmClass", alarmClass.changjia_name + alarmClass.sell_phone);

            mTvTitle.setText("整机运转异常");
            layoutInfo.setVisibility(View.VISIBLE);
            layoutMessage.setVisibility(View.VISIBLE);
            btnClean.setVisibility(View.VISIBLE);
            mTvMessage.setText(alarmClass.failure_name);
            mTvAddr.setText(alarmClass.install_addr);
            mTvDate.setText(alarmClass.install_time);
            mTvFactory.setText(alarmClass.changjia_name);
            mTvPhone.setText(alarmClass.sell_phone);
            mTvType.setText(alarmClass.xinghao);

        } else {
            requestData();
        }

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_GUZHANG) {
                    Gson gson = new Gson();
                    try {
                        AlarmClass alarmClass = gson.fromJson(message.content.toString(), AlarmClass.class);
                        Log.i("alarmClass", alarmClass.changjia_name + alarmClass.sell_phone);
                        mTvTitle.setText("整机运转异常");
                        layoutInfo.setVisibility(View.VISIBLE);
                        layoutMessage.setVisibility(View.VISIBLE);
                        btnClean.setVisibility(View.VISIBLE);
                        mTvMessage.setText(alarmClass.failure_name);
                        mTvAddr.setText(alarmClass.install_addr);
                        mTvDate.setText(alarmClass.install_time);
                        mTvFactory.setText(alarmClass.changjia_name);
                        mTvPhone.setText(alarmClass.sell_phone);
                        mTvType.setText(alarmClass.xinghao);

                        //重新获取ccid
//                        CAR_CTROL = "wit/cbox/hardware/" + MyApplication.getServer_id() + alarmClass.ccid;
                    } catch (Exception e) {
                        System.out.println("警报异常" + e.getMessage());
                    }
//                    mTvVoltage.setText(alarmClass.);
//                    mTvRate.setText(alarmClass.);

                    // showDialog("是否清除故障");

//                    MyCarCaoZuoDialog_CaoZuoTIshi_Clear clear = new MyCarCaoZuoDialog_CaoZuoTIshi_Clear(DiagnosisActivity.this, new MyCarCaoZuoDialog_CaoZuoTIshi_Clear.OnDialogItemClickListener() {
//                        @Override
//                        public void clickLeft() {
//
//                        }
//
//                        @Override
//                        public void clickRight() {
//                            AndMqtt.getInstance().publish(new MqttPublish()
//                                    .setMsg("M691.").setRetained(false)
//                                    .setQos(2)
//                                    .setTopic(CAR_CTROL), new IMqttActionListener() {
//                                @Override
//                                public void onSuccess(IMqttToken asyncActionToken) {
//                                    Log.i("Rair", "(清除故障 --- 发布成功");
//                                    //      UIHelper.ToastMessage(DiagnosisActivity.this, "故障清除中，请稍候", Toast.LENGTH_SHORT);
//                                    // dialog.dismiss();
//                                    UIHelper.ToastMessage(DiagnosisActivity.this, "故障已清除", Toast.LENGTH_SHORT);
//                                    //获得车辆的实时数据和基本信息
//                                    finish();
//
//                                }
//
//                                @Override
//                                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                                    Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
//                                }
//                            });
//                        }
//                    });
//                    clear.show();
                } else if (message.type == ConstanceValue.MSG_CLEARGUZHANGSUCCESS) {
                    //清除故障
//                    dialog.setTitle("成功");
//                    dialog.setContent("清除故障成功");
//                    dialog.setPic(getResources().getDrawable(R.drawable.chenggong));
//                    dialog.setCancelClickListener(null);
//                    dialog.setConfirmClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            dialog.dismiss();
//                        }
//                    });
//
//                    dialog.setUiBeforShow();

                    MyCarCaoZuoDialog_Success dialog_success = new MyCarCaoZuoDialog_Success(DiagnosisActivity.this);
                    dialog_success.show();
                    layoutInfo.setVisibility(View.GONE);
                    layoutMessage.setVisibility(View.GONE);
                    btnClean.setVisibility(View.GONE);
                    mTvTitle.setText("整机运转正常");
                    UIHelper.ToastMessage(DiagnosisActivity.this, "故障已清除", Toast.LENGTH_LONG);

                }

            }
        }));
    }


    public void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03225");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());

        Log.i("it_token", UserManager.getManager(this).getAppToken());
        map.put("user_car_id", PreferenceHelper.getInstance(this).getString("of_user_id", ""));
        map.put("ccid", PreferenceHelper.getInstance(this).getString("ccid", ""));
        map.put("type", "1");
        map.put("type_msg", "2");
        Gson gson = new Gson();
        OkGo.<AppResponse<HeaterDetails.DataBean>>post(Urls.SERVER_URL + "wit/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<HeaterDetails.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<HeaterDetails.DataBean>> response) {
                        if (response.body().data.get(0).getZhu_car_stoppage_no() == null) {
                            response.body().data.get(0).setZhu_car_stoppage_no("");
                        }
                        if (response.body().data.get(0).getZhu_car_stoppage_no().equals("")) {
                            //UIHelper.ToastMessage(DiagnosisActivity.this, "故障清除成功", Toast.LENGTH_LONG);
                            mTvTitle.setText("整机运转正常");

                        } else {

                            //重新获取ccid
                            // CAR_CTROL = "wit/cbox/hardware/" + MyApplication.getServer_id() + response.body().data.get(0).getCcid();
                            CARBOX_GETNOW = "wit/cbox/app/" + MyApplication.getServer_id() + response.body().data.get(0).getCcid();

                            AndMqtt.getInstance().subscribe(new MqttSubscribe()
                                    .setTopic(CARBOX_GETNOW)
                                    .setQos(2), new IMqttActionListener() {
                                @Override
                                public void onSuccess(IMqttToken asyncActionToken) {
                                    Log.i("Rair", "(MainActivity.java:63)-onSuccess:-&gt;订阅成功" + CARBOX_GETNOW);
                                }

                                @Override
                                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                                    Log.i("Rair", "(MainActivity.java:68)-onFailure:-&gt;订阅失败");
                                }
                            });

                            mTvTitle.setText("整机运转异常");
                            layoutInfo.setVisibility(View.VISIBLE);
                            layoutMessage.setVisibility(View.VISIBLE);
                            btnClean.setVisibility(View.VISIBLE);
                            mTvMessage.setText(response.body().data.get(0).getFailure_name());
                            mTvAddr.setText(response.body().data.get(0).getInstall_addr());
                            mTvDate.setText(response.body().data.get(0).getInstall_time());
                            mTvFactory.setText(response.body().data.get(0).getChangjia_name());
                            mTvPhone.setText(response.body().data.get(0).getSell_phone());
                            mTvType.setText(response.body().data.get(0).getXinghao());
                            mTvVoltage.setText(response.body().data.get(0).getMachine_voltage());
                            mTvRate.setText(response.body().data.get(0).getFrequency());
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<HeaterDetails.DataBean>> response) {
                        UIHelper.ToastMessage(DiagnosisActivity.this, response.getException().getMessage());
                    }
                });
    }

    public void requestData2() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03311");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("user_car_id", PreferenceHelper.getInstance(this).getString("of_user_id", ""));
        map.put("ccid", PreferenceHelper.getInstance(this).getString("ccid", ""));
        map.put("type", "1");
        map.put("type_msg", "2");
        Gson gson = new Gson();
        OkGo.<AppResponse<ServiceModel.DataBean>>post(Urls.SERVER_URL + "wit/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ServiceModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<ServiceModel.DataBean>> response) {
                        list = response.body().data;
                        for (int i = 0; i < response.body().data.size(); i++) {
                            mMenuItems.add(new DialogMenuItem(response.body().data.get(i).getSub_user_name(), R.drawable.zixun_on));
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<ServiceModel.DataBean>> response) {
                        UIHelper.ToastMessage(DiagnosisActivity.this, response.getException().getMessage());
                    }
                });
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

//        AndMqtt.getInstance().unSubscribe(new MqttUnSubscribe().setTopic(CARBOX_GETNOW), new IMqttActionListener() {
//            @Override
//            public void onSuccess(IMqttToken asyncActionToken) {
//                Log.i("Rair", "(MainActivity.java:93)-onSuccess:-&gt;取消订阅成功");
//            }
//
//            @Override
//            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                Log.i("Rair", "(MainActivity.java:98)-onFailure:-&gt;取消订阅失败");
//            }
//        });
    }

    @OnClick({R.id.rl_back, R.id.rl_consult, R.id.btn_clean})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_consult:
                final NormalListDialog dialog = new NormalListDialog(this, mMenuItems);
                dialog.title("请选择")//
                        .showAnim(mBasIn)//
                        .dismissAnim(mBasOut)//
                        .show();
                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //此处title参数用来区分是车主端还是客服端
                        ServiceModel.DataBean dataBean = list.get(position);
                        Conversation.ConversationType conversationType = Conversation.ConversationType.PRIVATE;
                        String targetId = dataBean.getSub_accid();
                        String instName = dataBean.getSub_user_name();
                        Bundle bundle = new Bundle();
                        bundle.putString("dianpuming", instName);
                        bundle.putString("inst_accid", targetId);
                        bundle.putString("shoptype", "1");
                        RongIM.getInstance().startConversation(mContext, conversationType, targetId, instName, bundle);
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.btn_clean:
                MyCarCaoZuoDialog_CaoZuoTIshi_Clear clear = new MyCarCaoZuoDialog_CaoZuoTIshi_Clear(DiagnosisActivity.this, new MyCarCaoZuoDialog_CaoZuoTIshi_Clear.OnDialogItemClickListener() {
                    @Override
                    public void clickLeft() {

                    }

                    @Override
                    public void clickRight() {
                        AndMqtt.getInstance().publish(new MqttPublish()
                                .setMsg("M691.").setRetained(false)
                                .setQos(2)
                                .setTopic(CAR_CTROL), new IMqttActionListener() {
                            @Override
                            public void onSuccess(IMqttToken asyncActionToken) {
                                Log.i("Rair", "(清除故障 --- 发布成功");
                                //      UIHelper.ToastMessage(DiagnosisActivity.this, "故障清除中，请稍候", Toast.LENGTH_SHORT);
                                // dialog.dismiss();
                                UIHelper.ToastMessage(DiagnosisActivity.this, "故障已清除", Toast.LENGTH_SHORT);
                                mTvTitle.setText("整机运转正常");
                                layoutInfo.setVisibility(View.GONE);
                                layoutMessage.setVisibility(View.GONE);
                                btnClean.setVisibility(View.GONE);
                                //finish();
                            }

                            @Override
                            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                                Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                            }
                        });
                    }
                });
                clear.show();
        }
    }
}
