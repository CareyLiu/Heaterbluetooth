package com.sdkj.heaterbluetooth.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.app.PreferenceHelper;
import com.sdkj.heaterbluetooth.callback.JsonCallback;
import com.sdkj.heaterbluetooth.config.AppResponse;
import com.sdkj.heaterbluetooth.config.UserManager;
import com.sdkj.heaterbluetooth.getnet.Urls;
import com.sdkj.heaterbluetooth.model.DingShiResultModel;
import com.sdkj.heaterbluetooth.util.Y;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FengnuandishiActivity extends BaseActivity {


    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.tv_queren)
    TextView tv_queren;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_shijian)
    TextView tv_shijian;
    @BindView(R.id.ll_select_time)
    LinearLayout ll_select_time;

    @BindView(R.id.cb_sunday)
    CheckBox cbSunday;
    @BindView(R.id.cb_monday)
    CheckBox cbMonday;
    @BindView(R.id.cb_tuesday)
    CheckBox cbTuesday;
    @BindView(R.id.cb_wednesday)
    CheckBox cbWednesday;
    @BindView(R.id.cb_thursday)
    CheckBox cbThursday;
    @BindView(R.id.cb_friday)
    CheckBox cbFriday;
    @BindView(R.id.cb_saturday)
    CheckBox cbSaturday;

    private String ccid;
    private String chooseHour = "00";//小时
    private String chooseMin = "00";//分钟

    private String weekTimes;
    private String jinriShijian;
    private TimePickerView timePicker;


    @Override
    public int getContentViewResId() {
        return R.layout.activity_fengnuan_dingshi;
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
        Intent intent = new Intent(context, FengnuandishiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        ccid = PreferenceHelper.getInstance(this).getString("ccid", "");
        chaXunDingShi();
    }

    //设置定时
    public void setDingShi() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03200");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("ccid", ccid);
        map.put("type", "1");

        String str;

        if (cbSunday.isChecked()) {
            str = "1";
        } else {
            str = "0";
        }

        if (cbMonday.isChecked()) {
            str = str + "1";
        } else {
            str = str + "0";
        }

        if (cbTuesday.isChecked()) {
            str = str + "1";
        } else {
            str = str + "0";
        }

        if (cbWednesday.isChecked()) {
            str = str + "1";
        } else {
            str = str + "0";
        }
        if (cbThursday.isChecked()) {
            str = str + "1";
        } else {
            str = str + "0";
        }
        if (cbFriday.isChecked()) {
            str = str + "1";
        } else {
            str = str + "0";
        }
        if (cbSaturday.isChecked()) {
            str = str + "1";
        } else {
            str = str + "0";
        }

        str = str + chooseHour + chooseMin;
        map.put("time", str);

        Gson gson = new Gson();
        OkGo.<AppResponse>post(Urls.DINGSHI)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse>() {
                    @Override
                    public void onSuccess(final Response<AppResponse> response) {
//                        Y.t(response.body().msg);
                        Y.t("定时成功");
                    }

                    @Override
                    public void onError(Response<AppResponse> response) {
                        Y.tError(response);
                    }
                });
    }

    @Override
    public boolean showToolBar() {
        return false;
    }


    public void chaXunDingShi() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03201");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("ccid", ccid);
        Gson gson = new Gson();
        OkGo.<AppResponse<DingShiResultModel.DataBean>>post(Urls.DINGSHI)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<DingShiResultModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<DingShiResultModel.DataBean>> response) {
                        weekTimes = response.body().data.get(0).getWeeks_time();
                        jinriShijian = response.body().data.get(0).getShifen_time();

                        if (weekTimes.length() == 7) {
                            String zhoutian = String.valueOf(weekTimes.charAt(0));
                            String zhouyi = String.valueOf(weekTimes.charAt(1));
                            String zhouer = String.valueOf(weekTimes.charAt(2));
                            String zhousan = String.valueOf(weekTimes.charAt(3));
                            String zhousi = String.valueOf(weekTimes.charAt(4));
                            String zhouwu = String.valueOf(weekTimes.charAt(5));
                            String zhouliu = String.valueOf(weekTimes.charAt(6));

                            if (zhoutian.equals("1")) {
                                cbSunday.setChecked(true);
                            } else {
                                cbSunday.setChecked(false);
                            }

                            if (zhouyi.equals("1")) {
                                cbMonday.setChecked(true);
                            } else {
                                cbMonday.setChecked(false);
                            }

                            if (zhouer.equals("1")) {
                                cbTuesday.setChecked(true);
                            } else {
                                cbTuesday.setChecked(false);
                            }

                            if (zhousan.equals("1")) {
                                cbWednesday.setChecked(true);
                            } else {
                                cbWednesday.setChecked(false);
                            }

                            if (zhousi.equals("1")) {
                                cbThursday.setChecked(true);
                            } else {
                                cbThursday.setChecked(false);
                            }

                            if (zhouwu.equals("1")) {
                                cbFriday.setChecked(true);
                            } else {
                                cbFriday.setChecked(false);
                            }

                            if (zhouliu.equals("1")) {
                                cbSaturday.setChecked(true);
                            } else {
                                cbSaturday.setChecked(false);
                            }

                        }

                        if (!TextUtils.isEmpty(jinriShijian)) {
                            String[] shijian = jinriShijian.split(":");
                            if (shijian.length >= 2) {
                                chooseHour = shijian[0];
                                chooseMin = shijian[1];
                            }
                            tv_time.setText(jinriShijian);
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<DingShiResultModel.DataBean>> response) {
                        Y.tError(response);
                    }
                });

    }


    private void selectData() {
        if (timePicker == null) {
            //时间选择器
            boolean[] select = {false, false, false, true, true, false};
            timePicker = new TimePickerBuilder(this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    int hours = date.getHours();
                    if (hours < 10) {
                        chooseHour = "0" + hours;
                    } else {
                        chooseHour = "" + hours;
                    }

                    int minutes = date.getMinutes();
                    if (minutes < 10) {
                        chooseMin = "0" + minutes;
                    } else {
                        chooseMin = "" + minutes;
                    }

                    tv_time.setText(chooseHour + ":" + chooseMin);
                }
            }).setType(select).build();
        }
        timePicker.show();
    }

    @OnClick({R.id.rl_back, R.id.tv_queren, R.id.tv_time, R.id.ll_select_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_queren:
                setDingShi();
                break;
            case R.id.tv_time:
                selectData();
                break;
            case R.id.ll_select_time:
                break;
        }
    }
}
