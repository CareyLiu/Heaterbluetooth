package com.sdkj.heaterbluetooth.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.app.PreferenceHelper;
import com.sdkj.heaterbluetooth.callback.JsonCallback;
import com.sdkj.heaterbluetooth.common.UIHelper;
import com.sdkj.heaterbluetooth.config.AppResponse;
import com.sdkj.heaterbluetooth.config.UserManager;
import com.sdkj.heaterbluetooth.getnet.Urls;
import com.sdkj.heaterbluetooth.model.AtmosBean;


import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sl on 2019/6/25.
 */

public class AtmosActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.et_atmos)
    EditText mEtAtmos;
    @BindView(R.id.bt_sure)
    Button mBtSure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atmos);
        ButterKnife.bind(this);

        //请求大气压参数
        requestGetData();
        mEtAtmos.addTextChangedListener(new TextWatcher() {
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

                        if (Integer.parseInt(s.toString()) > 100) {
                            mEtAtmos.setText("100");
                            UIHelper.ToastMessage(AtmosActivity.this, "可以输入的最大值为100", Toast.LENGTH_SHORT);

                        }
                    }
                }
            }


        });

        mEtAtmos.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {//焦点在

                } else {

                    if (!StringUtils.isEmpty(mEtAtmos.getText().toString())) {


                        if (Integer.parseInt(mEtAtmos.getText().toString()) < Integer.parseInt("50")) {
                            mEtAtmos.setText("50");
                            UIHelper.ToastMessage(AtmosActivity.this, "您输入的最小值不能少于" + "50" + "哦", Toast.LENGTH_SHORT);
                        }
                    } else {
                        mEtAtmos.setText("50");
                        UIHelper.ToastMessage(AtmosActivity.this, "您输入的最小值不能少于" + "50" + "哦", Toast.LENGTH_SHORT);
                    }


                }
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.bt_sure})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.bt_sure:
                requestData();
                break;
        }
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AtmosActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void requestData() {

        Map<String, String> map = new HashMap<>();
        map.put("code", "03112");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("user_car_id", PreferenceHelper.getInstance(AtmosActivity.this).getString("car_id", ""));
        map.put("zhu_apc", "0" + mEtAtmos.getText().toString());
        Gson gson = new Gson();
        OkGo.<AppResponse>post(Urls.SERVER_URL + "wit/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse>() {
                    @Override
                    public void onSuccess(final Response<AppResponse> response) {
                        if (response.body().msg_code.equals("0000")) {
                            UIHelper.ToastMessage(AtmosActivity.this, "大气压参数输入成功", Toast.LENGTH_SHORT);
                            PreferenceHelper.getInstance(AtmosActivity.this).putString("atmos", mEtAtmos.getText().toString());
                            InputMethodManager imm = (InputMethodManager) AtmosActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                            // 隐藏软键盘
                            imm.hideSoftInputFromWindow(AtmosActivity.this.getWindow().getDecorView().getWindowToken(), 0);

                            finish();
                        } else if (response.body().msg_code.equals("0001")) {
                            UIHelper.ToastMessage(AtmosActivity.this, response.body().msg, Toast.LENGTH_SHORT);
                        }

                    }

                    @Override
                    public void onError(Response<AppResponse> response) {
                        UIHelper.ToastMessage(AtmosActivity.this, response.getException().getMessage());
                    }
                });
    }


    public void requestGetData() {

        Map<String, String> map = new HashMap<>();
        map.put("code", "03111");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("user_car_id", PreferenceHelper.getInstance(AtmosActivity.this).getString("car_id", ""));

        Gson gson = new Gson();

        OkGo.<AppResponse<AtmosBean.DataBean>>post(Urls.SERVER_URL + "wit/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<AtmosBean.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<AtmosBean.DataBean>> response) {
                        AtmosBean.DataBean atmosBean = response.body().data.get(0);
                        System.out.println("大气压" + atmosBean.getZhu_apc());

                        if (atmosBean.getZhu_apc() != null) {

                            if (atmosBean.getZhu_apc().equals("aaa")) {
                                mEtAtmos.setText("50");
                            } else if (atmosBean.getZhu_apc().indexOf('0') == 0) {
                                mEtAtmos.setText(atmosBean.getZhu_apc().substring(1));
                            } else {
                                mEtAtmos.setText(atmosBean.getZhu_apc());
                            }

                        }

                    }

                    @Override
                    public void onError(Response<AppResponse<AtmosBean.DataBean>> response) {
                        //  AlertUtil.t(CarListActivity.this, response.getException().getMessage());
                    }
                });

    }


}
