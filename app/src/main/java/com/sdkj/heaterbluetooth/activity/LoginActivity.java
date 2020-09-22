package com.sdkj.heaterbluetooth.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.google.gson.Gson;

import com.jakewharton.rxbinding.view.RxView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.AppConfig;
import com.sdkj.heaterbluetooth.app.AppManager;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.app.ConstanceValue;
import com.sdkj.heaterbluetooth.app.Notice;
import com.sdkj.heaterbluetooth.app.PreferenceHelper;
import com.sdkj.heaterbluetooth.basepage.HomeBasicActivity;
import com.sdkj.heaterbluetooth.callback.DialogCallback;
import com.sdkj.heaterbluetooth.callback.JsonCallback;
import com.sdkj.heaterbluetooth.common.UIHelper;
import com.sdkj.heaterbluetooth.config.AppResponse;
import com.sdkj.heaterbluetooth.config.UserManager;
import com.sdkj.heaterbluetooth.getnet.Urls;
import com.sdkj.heaterbluetooth.model.LoginUser;
import com.sdkj.heaterbluetooth.model.Message;
import com.sdkj.heaterbluetooth.util.RxBus;
import com.sdkj.heaterbluetooth.util.TimeCount;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import rx.functions.Action1;

import static com.sdkj.heaterbluetooth.getnet.Urls.SERVER_URL;


public class LoginActivity extends BaseActivity {
    ProgressDialog progressDialog;
    private static final String TAG = "LoginActivity";
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_pwd_code)
    EditText mEtPwdCode;
    @BindView(R.id.get_code)
    LinearLayout mGetCode;
    @BindView(R.id.tv_switch)
    TextView mTvSwitch;
    @BindView(R.id.bt_login)
    Button mBtLogin;
    @BindView(R.id.tv_get_code)
    TextView mTvGetCode;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.view)
    ImageView view;
    @BindView(R.id.imageView7)
    ImageView imageView7;
    @BindView(R.id.imageView10)
    ImageView imageView10;
    @BindView(R.id.tv_yinsi)
    TextView tvYinsi;
    @BindView(R.id.tv_yonghushiyong)
    TextView tvYonghushiyong;
    @BindView(R.id.imageView9)
    ImageView imageView9;
    private boolean isExit;
    private TimeCount timeCount;
    private String req_type = "2";
    private String smsId;

    public static List<LoginUser.DataBean> userlist = new ArrayList<>();
    Response<AppResponse<LoginUser.DataBean>> response;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_login;
    }

    @Override
    public void initImmersion() {
        mImmersionBar.with(this).statusBarColor(R.color.white).init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        timeCount = new TimeCount(60000, 1000, mTvGetCode);
        mEtPhone.setText(PreferenceHelper.getInstance(this).getString("user_phone", ""));
        RxView.clicks(mTvGetCode)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {


                        get_code();
                    }
                });


        tvYonghushiyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DefaultX5WebViewActivity.actionStart(LoginActivity.this, "https://shop.hljsdkj.com/shop_new/sgfn_user_agreements");
            }
        });
        tvYinsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DefaultX5WebViewActivity.actionStart(LoginActivity.this, "https://shop.hljsdkj.com/shop_new/sgfn_privacy_clause");
            }
        });


    }


    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                // showToast("再按一次返回键退出");
                UIHelper.ToastMessage(this, "再按一次返回键退出");
                isExit = true;
                new Thread() {
                    public void run() {
                        SystemClock.sleep(3000);
                        isExit = false;
                    }

                }.start();
                return true;
            }
            // finish();
            AppManager.getAppManager().AppExit(this);
        }
        return super.onKeyDown(keyCode, event);

    }

    @OnClick({R.id.tv_get_code, R.id.tv_switch, R.id.bt_login})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_get_code:

                break;
            case R.id.tv_switch:
                String items[] = {getString(R.string.sms_login), getString(R.string.pwd_login)};
                final ActionSheetDialog dialog = new ActionSheetDialog(this, items, null);
                dialog.isTitleShow(false).show();
                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                mGetCode.setVisibility(View.VISIBLE);
                                mEtPwdCode.setHint("请输入验证码");
                                mEtPwdCode.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                req_type = "2";
                                break;
                            case 1:
                                mGetCode.setVisibility(View.GONE);
                                mEtPwdCode.setHint("请输入登录密码");
                                mEtPwdCode.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                req_type = "1";
                                break;
                        }
                        dialog.dismiss();

                    }
                });

                break;
            case R.id.bt_login:
//                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                beforehand_login();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userlist.clear();
    }

    /**
     * 获取短信验证码
     */
    private void get_code() {
        if (mEtPhone.getText().equals("") || mEtPhone.getText() == null) {
            UIHelper.ToastMessage(this, "手机号码不能为空");
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("code", "00001");
            map.put("key", Urls.key);
            map.put("user_phone", mEtPhone.getText().toString());
            map.put("mod_id", "0315");
            Gson gson = new Gson();
            OkGo.<AppResponse<Message.DataBean>>post(SERVER_URL + "msg")
                    .tag(this)//
                    .upJson(gson.toJson(map))
                    .execute(new JsonCallback<AppResponse<Message.DataBean>>() {
                        @Override
                        public void onSuccess(Response<AppResponse<Message.DataBean>> response) {
                            //           UIHelper.ToastMessage(LoginActivity.this, response.body().msg);
                            UIHelper.ToastMessage(LoginActivity.this, "验证码获取成功");
                            timeCount.start();
                            if (response.body().data.size() > 0)
                                smsId = response.body().data.get(0).getSms_id();
                        }

                        @Override
                        public void onError(Response<AppResponse<Message.DataBean>> response) {
                            UIHelper.ToastMessage(LoginActivity.this, response.getException().getMessage());
                            timeCount.cancel();
                            timeCount.onFinish();
                        }
                    });
        }

    }

    /**
     * 预登陆
     */
    private void beforehand_login() {
        if (mEtPhone.getText().equals("")) {
            UIHelper.ToastMessage(this, "手机号码不能为空");
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("code", "00050");
            map.put("key", Urls.key);
            map.put("req_type", req_type);

            //map.put("phone_model", SystemUtils.getSystemModel());
            map.put("user_phone", mEtPhone.getText().toString());
            // map.put("user_pwd", mEtPwdCode.getText().toString());
            map.put("log_type", "1");
            switch (req_type) {
                case "1"://密码登录
                    map.put("user_phone", mEtPhone.getText().toString());
                    map.put("user_pwd", mEtPwdCode.getText().toString());
                    break;
                case "2"://手机验证码登录
                    map.put("sms_id", smsId);
                    map.put("sms_code", mEtPwdCode.getText().toString());
                    break;
            }

            Gson gson = new Gson();
            OkGo.<AppResponse<LoginUser.DataBean>>post(SERVER_URL + "index/login")
                    .tag(this)//
                    .upJson(gson.toJson(map))
                    .execute(new DialogCallback<AppResponse<LoginUser.DataBean>>(this) {
                        @Override
                        public void onSuccess(Response<AppResponse<LoginUser.DataBean>> response) {
                            userlist.clear();

                            LoginActivity.this.response = response;
                            //保存用户手机号码
                            PreferenceHelper.getInstance(LoginActivity.this).putString("user_phone", mEtPhone.getText().toString() + "");
                            if (response.body().data.size() == 1) {
                                //如果登录角色数量<=1则直接登录
                                // response.body().data.get(0).invitation_code_state = "2";
                                UserManager.getManager(LoginActivity.this).saveUser(LoginActivity.this.response.body().data.get(0));
                                if (response.body().data.get(0).getPower_state().equals("1")) {

                                        UserManager.getManager(LoginActivity.this).saveUser(response.body().data.get(0));
                                        startActivity(new Intent(LoginActivity.this, HomeBasicActivity.class));

                                        //重连mqtt
                                        Notice n = new Notice();
                                        n.type = ConstanceValue.MSG_CONNET_MQTT;
                                        RxBus.getDefault().sendRx(n);
                                        finish();


                                } else {
                                    UserManager.getManager(LoginActivity.this).saveUser(response.body().data.get(0));
                                    startActivity(new Intent(LoginActivity.this, HomeBasicActivity.class));

                                    //重连mqtt
                                    Notice n = new Notice();
                                    n.type = ConstanceValue.MSG_CONNET_MQTT;
                                    RxBus.getDefault().sendRx(n);
                                    finish();
                                }

                            } else {
                                //登录角色 >1 时，让用户选择要登录的角色
                                userlist.addAll(response.body().data);
                                startActivity(new Intent(LoginActivity.this, SelectLoginActivity.class));
                            }

                            String rongYunTouken = UserManager.getManager(mContext).getRongYun();
                            if (!StringUtils.isEmpty(rongYunTouken)) {
                                connectRongYun(response.body().data.get(0).getToken_rong());
                            }
                        }

                        @Override
                        public void onError(Response<AppResponse<LoginUser.DataBean>> response) {
                            UIHelper.ToastMessage(LoginActivity.this, response.getException().getMessage());
                        }
                    });
        }

    }


    public void connectRongYun(String token) {

        RongIM.connect(token, new RongIMClient.ConnectCallbackEx() {
            /**
             * 数据库回调.
             * @param code 数据库打开状态. DATABASE_OPEN_SUCCESS 数据库打开成功; DATABASE_OPEN_ERROR 数据库打开失败
             */
            @Override
            public void OnDatabaseOpened(RongIMClient.DatabaseOpenStatus code) {
                Log.i("rongYun", "数据库打开失败");
            }

            /**
             * token 无效
             */
            @Override
            public void onTokenIncorrect() {
                Log.i("rongYun", "token 无效");
            }

            /**
             * 成功回调
             * @param userId 当前用户 ID
             */
            @Override
            public void onSuccess(String userId) {
                //UIHelper.ToastMessage(mContext, "融云连接成功");
                Log.i("rongYun", "融云连接成功");
                PreferenceHelper.getInstance(mContext).putString(AppConfig.RONGYUN_TOKEN, token);
            }

            /**
             * 错误回调
             * @param errorCode 错误码
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.i("rongYun", "融云连接失败");
            }
        });

    }

    /**
     * §6.5	车主端，代理商端，维修端，商家端四端合一登陆
     */
    private void login() {

        Map<String, String> map = new HashMap<>();
        map.put("code", "00051");
        map.put("key", Urls.key);
        map.put("subsystem_id", "");
        map.put("user_id_key", UserManager.getManager(this).getUserIdKey());
        map.put("power_state", UserManager.getManager(this).getPowerState());
        Gson gson = new Gson();
        OkGo.<AppResponse<LoginUser.DataBean>>post(SERVER_URL + "index/login")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<LoginUser.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<LoginUser.DataBean>> response) {
                        if (response.body().data.size() > 0)
                            UserManager.getManager(LoginActivity.this).saveUser(response.body().data.get(0));

//                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                        //重连mqtt
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_CONNET_MQTT;
                        RxBus.getDefault().sendRx(n);
                        startActivity(new Intent(LoginActivity.this, HomeBasicActivity.class));

                    }

                    @Override
                    public void onError(Response<AppResponse<LoginUser.DataBean>> response) {
                        UIHelper.ToastMessage(LoginActivity.this, response.getException().getMessage());
                    }
                });


    }

    private void getNet_butian(String et) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04343");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        // map.put("shop_product_id", productId);
        //map.put("wares_id", warseId);
        map.put("invitation_code", et);

        Log.i("taoken_gg", UserManager.getManager(mContext).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<Object>>post(SERVER_URL + "shop_new/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Object>>() {
                    @Override

                    public void onSuccess(final Response<AppResponse<Object>> response) {

                        startActivity(new Intent(LoginActivity.this, HomeBasicActivity.class));

                        //重连mqtt
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_CONNET_MQTT;
                        RxBus.getDefault().sendRx(n);
                        finish();

                    }

                    @Override
                    public void onError(Response<AppResponse<Object>> response) {
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());

                        UserManager.getManager(LoginActivity.this).saveUser(LoginActivity.this.response.body().data.get(0));
                        startActivity(new Intent(LoginActivity.this, HomeBasicActivity.class));

                        //重连mqtt
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_CONNET_MQTT;
                        RxBus.getDefault().sendRx(n);
                        finish();
                    }
                });
    }

    /**
     * @param context 上下文
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
