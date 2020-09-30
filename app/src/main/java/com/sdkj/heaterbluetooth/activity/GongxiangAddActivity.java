package com.sdkj.heaterbluetooth.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.callback.JsonCallback;
import com.sdkj.heaterbluetooth.config.AppResponse;
import com.sdkj.heaterbluetooth.config.UserManager;
import com.sdkj.heaterbluetooth.dialog.TishiDialog;
import com.sdkj.heaterbluetooth.getnet.Urls;
import com.sdkj.heaterbluetooth.model.Message;
import com.sdkj.heaterbluetooth.model.ServiceModel;
import com.sdkj.heaterbluetooth.util.TimeCount;
import com.sdkj.heaterbluetooth.util.Y;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

public class GongxiangAddActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.ed_pwd)
    EditText ed_pwd;
    @BindView(R.id.tv_send_code)
    TextView tv_send_code;
    @BindView(R.id.ll_jiebang)
    LinearLayout ll_jiebang;

    private String ccidMa;
    private TimeCount timeCount;
    private String smsId;

    @Override
    public void initImmersion() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_gongxiang_add;
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String ccid) {
        Intent intent = new Intent(context, GongxiangAddActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ccid", ccid);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ccidMa = getIntent().getStringExtra("ccid");
        timeCount = new TimeCount(60000, 1000, tv_send_code);
    }


    @OnClick({R.id.rl_back, R.id.tv_send_code, R.id.ll_jiebang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_send_code:
                get_code();
                break;
            case R.id.ll_jiebang:
                add();
                break;
        }
    }

    private void add() {
        String smm_code = ed_pwd.getText().toString();
        if (TextUtils.isEmpty(smsId)) {
            Y.t("请发送验证码");
            return;
        }

        if (TextUtils.isEmpty(smm_code)) {
            Y.t("请输入验证码");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("code", "03512");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("ccid", ccidMa);
        map.put("sms_id", smsId);
        map.put("sms_code", smm_code);
        Gson gson = new Gson();
        OkGo.<AppResponse<ServiceModel.DataBean>>post(Urls.SERVER_URL + "wit/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ServiceModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<ServiceModel.DataBean>> response) {
                        Y.t("添加成功");
                        finish();
                    }

                    @Override
                    public void onError(Response<AppResponse<ServiceModel.DataBean>> response) {
                        Y.tError(response);
                    }
                });
    }

    private void get_code() {
        String user_phone = ed_phone.getText().toString();
        if (TextUtils.isEmpty(user_phone)) {
            Y.t("请输入共享成员手机号");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("code", "00001");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("user_phone", user_phone);
        map.put("mod_id", "0335");
        Gson gson = new Gson();
        OkGo.<AppResponse<Message.DataBean>>post(Urls.SERVER_URL + "msg")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Message.DataBean>>() {
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
                        timeCount.cancel();
                        timeCount.onFinish();
                        Y.tError(response);
                    }
                });
    }
}
