package com.sdkj.heaterbluetooth.fragment;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.AppConfig;
import com.sdkj.heaterbluetooth.app.ConstanceValue;
import com.sdkj.heaterbluetooth.app.Notice;
import com.sdkj.heaterbluetooth.app.PreferenceHelper;
import com.sdkj.heaterbluetooth.basepage.HomeBasicActivity;
import com.sdkj.heaterbluetooth.basicmvp.BaseFragment;
import com.sdkj.heaterbluetooth.callback.DialogCallback;
import com.sdkj.heaterbluetooth.callback.JsonCallback;
import com.sdkj.heaterbluetooth.config.AppResponse;
import com.sdkj.heaterbluetooth.config.UserManager;
import com.sdkj.heaterbluetooth.dialog.TishiDialog;
import com.sdkj.heaterbluetooth.getnet.Urls;
import com.sdkj.heaterbluetooth.model.LoginUser;
import com.sdkj.heaterbluetooth.model.Message;
import com.sdkj.heaterbluetooth.model.newmodel.UserModel;
import com.sdkj.heaterbluetooth.util.RxBus;
import com.sdkj.heaterbluetooth.util.TimeCount;
import com.sdkj.heaterbluetooth.util.Y;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

import static com.sdkj.heaterbluetooth.getnet.Urls.SERVER_URL;
import static com.sdkj.heaterbluetooth.getnet.Urls.USER;

public class WoDeFragment extends BaseTwoFragment {
    @BindView(R.id.tv_title_view)
    TextView tv_title_view;
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.ed_pwd)
    EditText ed_pwd;
    @BindView(R.id.tv_send_code)
    TextView tv_send_code;
    @BindView(R.id.tv_qiehuan)
    TextView tv_qiehuan;
    @BindView(R.id.tv_zhaohui)
    TextView tv_zhaohui;
    @BindView(R.id.tv_login)
    TextView tv_login;
    @BindView(R.id.ll_login)
    LinearLayout ll_login;
    @BindView(R.id.iv_wode_head)
    ImageView iv_wode_head;
    @BindView(R.id.tv_wode_name)
    TextView tv_wode_name;
    @BindView(R.id.tv_wode_phone)
    TextView tv_wode_phone;
    @BindView(R.id.ll_jiaofeijilu)
    LinearLayout ll_jiaofeijilu;
    @BindView(R.id.tv_login_out)
    TextView tv_login_out;
    @BindView(R.id.sm_wode)
    SmartRefreshLayout sm_wode;

    private TimeCount timeCount;
    private String smsId;
    private String req_type;
    private UserModel.DataBean user;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main_wode;
    }


    public static WoDeFragment newInstance() {
        Bundle args = new Bundle();
        WoDeFragment fragment = new WoDeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initSm();
        return rootView;
    }

    private void initSm() {
        sm_wode.setEnableLoadMore(false);
        sm_wode.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getUserInfo();
            }
        });
    }

    @OnClick({R.id.tv_send_code, R.id.tv_qiehuan, R.id.tv_zhaohui, R.id.tv_login, R.id.ll_jiaofeijilu, R.id.tv_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send_code:
                get_code();
                break;
            case R.id.tv_qiehuan:
                clickQiehuan();
                break;
            case R.id.tv_zhaohui:
                break;
            case R.id.tv_login:
                login();
                break;
            case R.id.ll_jiaofeijilu:
                getUserInfo();
                break;
            case R.id.tv_login_out:
                clickLoginOut();
                break;
        }
    }

    /**
     * 切换登录方式
     */
    private void clickQiehuan() {
        String items[] = {getString(R.string.sms_login), getString(R.string.pwd_login)};
        final ActionSheetDialog dialog = new ActionSheetDialog(getContext(), items, null);
        dialog.isTitleShow(false).show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        tv_send_code.setVisibility(View.VISIBLE);
                        ed_pwd.setHint("请输入验证码");
                        ed_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        req_type = "2";
                        break;
                    case 1:
                        tv_send_code.setVisibility(View.GONE);
                        ed_pwd.setHint("请输入登录密码");
                        ed_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        req_type = "1";
                        break;
                }
                dialog.dismiss();

            }
        });
    }

    /**
     * 获取短信验证码
     */
    private void get_code() {
        String phone = ed_phone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            Y.t("手机号码不能为空");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("code", "00001");
        map.put("key", Urls.key);
        map.put("user_phone", phone);
        map.put("mod_id", "0333");
        Gson gson = new Gson();
        OkGo.<AppResponse<Message.DataBean>>post(SERVER_URL + "msg")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new DialogCallback<AppResponse<Message.DataBean>>(getActivity()) {
                    @Override
                    public void onSuccess(Response<AppResponse<Message.DataBean>> response) {
                        Y.t("验证码获取成功");
                        timeCount.start();
                        if (response.body().data.size() > 0) {
                            smsId = response.body().data.get(0).getSms_id();
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<Message.DataBean>> response) {
                        Y.tError(response);
                        timeCount.cancel();
                        timeCount.onFinish();
                    }
                });
    }


    /**
     * 登录
     */
    private void login() {
        String phone = ed_phone.getText().toString();
        String pwd = ed_pwd.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            Y.t("手机号码不能为空");
            return;
        }

        if (TextUtils.isEmpty(pwd)) {

            if (req_type.equals("1")) {
                Y.t("请输入密码");
            } else {
                Y.t("请输入验证码");
            }
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("code", "00057");
        map.put("key", Urls.key);
        map.put("req_type", req_type);
        map.put("user_phone", phone);
        switch (req_type) {
            case "1"://密码登录
                map.put("user_pwd", pwd);
                break;
            case "2"://手机验证码登录
                map.put("sms_id", smsId);
                map.put("sms_code", pwd);
                break;
        }

        Gson gson = new Gson();
        OkGo.<AppResponse<LoginUser.DataBean>>post(SERVER_URL + "index/login")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new DialogCallback<AppResponse<LoginUser.DataBean>>(getActivity()) {
                    @Override
                    public void onSuccess(Response<AppResponse<LoginUser.DataBean>> response) {
                        PreferenceHelper.getInstance(getContext()).putString("user_phone", phone);
                        LoginUser.DataBean dataBean = response.body().data.get(0);
                        UserManager.getManager(getContext()).saveUser(dataBean);
                        HomeBasicActivity.actionStart(getContext());

                        initState();

                        //重连mqtt
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_CONNET_MQTT;
                        RxBus.getDefault().sendRx(n);

                        String rongYunTouken = UserManager.getManager(getContext()).getRongYun();
                        if (!StringUtils.isEmpty(rongYunTouken)) {
                            connectRongYun(response.body().data.get(0).getToken_rong());
                        }

                        timeCount.cancel();
                        timeCount.onFinish();
                    }

                    @Override
                    public void onError(Response<AppResponse<LoginUser.DataBean>> response) {
                        Y.tError(response);
                    }
                });
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
                Log.i("rongYun", "融云连接成功");
                PreferenceHelper.getInstance(getContext()).putString(AppConfig.RONGYUN_TOKEN, token);
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

    private void clickLoginOut() {
        TishiDialog dialog = new TishiDialog(getContext(), TishiDialog.TYPE_CAOZUO, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {
                loginOut();
            }

            @Override
            public void onDismiss(TishiDialog dialog) {

            }
        });
        dialog.setTextContent("是否退出登录?");
        dialog.show();
    }

    private void loginOut() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03002");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getContext()).getAppToken());
        map.put("of_user_id", UserManager.getManager(getContext()).getOfUserId());
        Gson gson = new Gson();
        OkGo.<AppResponse<Message.DataBean>>post(USER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new DialogCallback<AppResponse<Message.DataBean>>(getActivity()) {
                    @Override
                    public void onSuccess(Response<AppResponse<Message.DataBean>> response) {
                        UserManager.getManager(getContext()).removeUser();
                        RongIM.getInstance().logout();
                        HomeBasicActivity.actionStart(getContext());
                        initState();
                    }

                    @Override
                    public void onError(Response<AppResponse<Message.DataBean>> response) {
                        Y.tError(response);
                    }
                });
    }

    private void initState() {
        if (UserManager.getManager(getActivity()).isLogin()) {
            ll_login.setVisibility(View.GONE);
            sm_wode.setVisibility(View.VISIBLE);
            tv_title_view.setText("我的");
            getUserInfo();
        } else {
            ll_login.setVisibility(View.VISIBLE);
            sm_wode.setVisibility(View.GONE);
            tv_title_view.setText("登录");
            initLoginSet();
        }
    }

    private void initLoginSet() {
        timeCount = new TimeCount(60000, 1000, tv_send_code);
        ed_phone.setText(PreferenceHelper.getInstance(getContext()).getString("user_phone", ""));
        tv_send_code.setVisibility(View.VISIBLE);
        ed_pwd.setHint("请输入验证码");
        ed_pwd.setInputType(InputType.TYPE_CLASS_NUMBER);
        req_type = "2";
    }

    private void getUserInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03003");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getContext()).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<UserModel.DataBean>>post(USER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<UserModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<UserModel.DataBean>> response) {
                        user = response.body().data.get(0);
                        tv_wode_name.setText("昵称：" + user.getUser_name());
                        tv_wode_phone.setText("手机号：" + user.getUser_phone());
                        Glide.with(getContext()).load(user.getUser_img_url()).into(iv_wode_head);
                    }

                    @Override
                    public void onError(Response<AppResponse<UserModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        sm_wode.finishRefresh();
                    }
                });
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        initState();
    }
}
