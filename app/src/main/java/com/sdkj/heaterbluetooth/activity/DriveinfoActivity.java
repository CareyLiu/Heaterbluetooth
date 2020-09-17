package com.sdkj.heaterbluetooth.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
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
import com.sdkj.heaterbluetooth.dialog.DriverDialog;
import com.sdkj.heaterbluetooth.getnet.Urls;
import com.sdkj.heaterbluetooth.model.DriverData;
import com.sdkj.heaterbluetooth.model.OwnerInfo;
import com.sdkj.heaterbluetooth.model.UserinfoData;
import com.sdkj.heaterbluetooth.util.GsonUtil;
import com.sdkj.heaterbluetooth.util.SharedPreferenceutils;
import com.sdkj.heaterbluetooth.util.WeiboDialogUtils;
import com.sdkj.heaterbluetooth.view.ItemTitleInfo;
import com.sdkj.heaterbluetooth.view.inter.OnDriverOnClickListener;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriveinfoActivity extends BaseActivity implements View.OnClickListener {

    private Context context = DriveinfoActivity.this;
    private SharedPreferenceutils sharedPreferenceutils;
    private ImageButton ibtnTitleBack;
    private TextView titleText, tv_title_save;
    private ItemTitleInfo itfIgnitionPplug, itfOutSensor, itfOilPump, itfFan, itfCombustionChamber, itfControlBoard;
    private ItemTitleInfo itfControlSwitch, itfRadiator, itfHarness, itfShell, itfRubberParts;
    private Dialog mWeiboDialog;
    private List<OwnerInfo.DataBean> dataBean;
    private List<DriverData.DataBean> dataBeans;
    private List<DriverData.DataBean.ClBeanXX> beanXList;
    private DriverDialog driverDialog;
    private String ignition_all_name = "";
    private String ignition_id_one = "";
    private String ignition_name_one = "";
    private String ignition_id_two = "";
    private String ignition_name_two = "";
    private String ignition_id_three = "";
    private String ignition_name_three = "";
    private String outlet_sensor_all_name = "";
    private String outlet_sensor_id_one = "";
    private String outlet_sensor_name_one = "";
    private String outlet_sensor_id_two = "";
    private String outlet_sensor_name_two = "";
    private String outlet_sensor_id_three = "";
    private String outlet_sensor_name_three = "";
    private String oil_pump_all_name = "";
    private String oil_pump_id_one = "";
    private String oil_pump_name_one = "";
    private String oil_pump_id_two = "";
    private String oil_pump_name_two = "";
    private String oil_pump_id_three = "";
    private String oil_pump_name_three = "";
    private String oil_pump_id_four = "";
    private String oil_pump_name_four = "";
    private String draught_fan_all_name = "";
    private String draught_fan_id_one = "";
    private String draught_fan_name_one = "";
    private String draught_fan_id_two = "";
    private String draught_fan_name_two = "";
    private String draught_fan_id_three = "";
    private String draught_fan_name_three = "";
    private String draught_fan_id_four = "";
    private String draught_fan_name_four = "";
    private String firebox_all_name = "";
    private String firebox_id_one = "";
    private String firebox_name_one = "";
    private String firebox_id_two = "";
    private String firebox_name_two = "";
    private String firebox_id_three = "";
    private String firebox_name_three = "";
    private String dash_board_all_name = "";
    private String dash_board_id_one = "";
    private String dash_board_name_one = "";
    private String dash_board_id_two = "";
    private String dash_board_name_two = "";
    private String control_switch_all_name = "";
    private String control_switch_id_one = "";
    private String control_switch_name_one = "";
    private String control_switch_id_two = "";
    private String control_switch_name_two = "";
    private String radiator_all_name = "";
    private String radiator_id_one = "";
    private String radiator_name_one = "";
    private String radiator_id_two = "";
    private String radiator_name_two = "";
    private String radiator_id_three = "";
    private String radiator_name_three = "";
    private String wiring_harness_all_name = "";
    private String wiring_harness_id_one = "";
    private String wiring_harness_name_one = "";
    private String wiring_harness_id_two = "";
    private String wiring_harness_name_two = "";
    private String wiring_harness_id_three = "";
    private String wiring_harness_name_three = "";
    private String outermost_shell_all_name = "";
    private String outermost_shell_id_one = "";
    private String outermost_shell_name_one = "";
    private String outermost_shell_id_two = "";
    private String outermost_shell_name_two = "";
    private String vulcanizate_all_name = "";
    private String vulcanizate_id_one = "";
    private String vulcanizate_name_one = "";
    private String vulcanizate_id_two = "";
    private String vulcanizate_name_two = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //    AtyContainer.getInstance().addActivity(this);
        sharedPreferenceutils = new SharedPreferenceutils(context, ACCOUNT_SERVICE);
        initTitleBar();
        initview();
        getData();
    }

    private void initTitleBar() {
        titleText = findViewById(R.id.title_view_page);
        titleText.setText("零配件信息");
        ibtnTitleBack = findViewById(R.id.ibtn_title_back);
        tv_title_save = findViewById(R.id.tv_title_save);
        tv_title_save.setVisibility(View.VISIBLE);
        tv_title_save.setText("完成");
        tv_title_save.setOnClickListener(this);
        ibtnTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_driveinfo;
    }

    @Override
    public void initImmersion() {
        mImmersionBar.with(this).titleBar(R.id.header).init();

    }

    private void initview() {
        itfIgnitionPplug = findViewById(R.id.itf_driver_ignition_plug);
        itfOutSensor = findViewById(R.id.itf_driver_out_sensor);
        itfOilPump = findViewById(R.id.itf_driver_oil_pump);
        itfFan = findViewById(R.id.itf_driver_fan);
        itfCombustionChamber = findViewById(R.id.itf_driver_combustion_chamber);
        itfControlBoard = findViewById(R.id.itf_driver_control_board);
        itfControlSwitch = findViewById(R.id.itf_driver_control_switch);
        itfRadiator = findViewById(R.id.itf_driver_radiator);
        itfHarness = findViewById(R.id.itf_driver_harness);
        itfShell = findViewById(R.id.itf_driver_shell);
        itfRubberParts = findViewById(R.id.itf_driver_rubber_parts);

        itfIgnitionPplug.setOnClickListener(this);
        itfOutSensor.setOnClickListener(this);
        itfOilPump.setOnClickListener(this);
        itfFan.setOnClickListener(this);
        itfCombustionChamber.setOnClickListener(this);
        itfControlBoard.setOnClickListener(this);
        itfControlSwitch.setOnClickListener(this);
        itfRadiator.setOnClickListener(this);
        itfHarness.setOnClickListener(this);
        itfShell.setOnClickListener(this);
        itfRubberParts.setOnClickListener(this);
    }

    private void getData() {
        mWeiboDialog = WeiboDialogUtils.createLoadingDialog(context, "加载中...");
        //   Api.getUserinfo(context, sharedPreferenceutils.getUserCarId(), handler);
        //  map.put("code", "02005");
        //  map.put("zhu_user_car_id", zhu_user_car_id);
        //  Api.getDriver(context, "zhu_parts_factory", datahandler);
        requestData11();//请求零配件基本信息
        requestData();
    }

    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    String data = (String) msg.obj;
                    UserinfoData userinfoData = GsonUtil.parseJsonWithGson(data, UserinfoData.class);
                    if (userinfoData.getMsg_code().equals("0000")) {
                        if (userinfoData.getData().size() > 0) {
                            //  dataBean = userinfoData.getData().get(0);
                            //  viewSetData(dataBean);
                        }
                    } else {
                        Toast.makeText(context, userinfoData.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    String error = (String) msg.obj;
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @SuppressLint("HandlerLeak")
    public Handler datahandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    String data = (String) msg.obj;
                    DriverData driverData = GsonUtil.parseJsonWithGson(data, DriverData.class);
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    if (driverData.getMsg_code().equals("0000")) {
                        dataBeans = driverData.getData();
                    } else {
                        Toast.makeText(context, driverData.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    String error = (String) msg.obj;
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @SuppressLint("HandlerLeak")
    public Handler submithandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    String data = (String) msg.obj;
                    try {
                        JSONObject jsonObject = new JSONObject(data);
                        WeiboDialogUtils.closeDialog(mWeiboDialog);
                        if (jsonObject.getString("msg_code").equals("0000")) {
                            Toast.makeText(context, "提交成功", Toast.LENGTH_SHORT).show();
                            //UserinfoActivity.instance.finish();
                            finish();
                        } else {
                            Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    String error = (String) msg.obj;
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    @Override
    public void onClick(View v) {
        driverDialog = new DriverDialog(context);
        switch (v.getId()) {
            case R.id.itf_driver_ignition_plug:
                driverDialog.show(dataBeans, 0);
                driverDialogOnclick(0);
                break;
            case R.id.itf_driver_out_sensor:
                driverDialog.show(dataBeans, 1);
                driverDialogOnclick(1);
                break;
            case R.id.itf_driver_oil_pump:
                driverDialog.show(dataBeans, 2);
                driverDialogOnclick(2);
                break;
            case R.id.itf_driver_fan:
                driverDialog.show(dataBeans, 3);
                driverDialogOnclick(3);
                break;
            case R.id.itf_driver_combustion_chamber:
                driverDialog.show(dataBeans, 4);
                driverDialogOnclick(4);
                break;
            case R.id.itf_driver_control_board:
                driverDialog.show(dataBeans, 5);
                driverDialogOnclick(5);
                break;
            case R.id.itf_driver_control_switch:
                driverDialog.show(dataBeans, 6);
                driverDialogOnclick(6);
                break;
            case R.id.itf_driver_radiator:
                driverDialog.show(dataBeans, 7);
                driverDialogOnclick(7);
                break;
            case R.id.itf_driver_harness:
                driverDialog.show(dataBeans, 8);
                driverDialogOnclick(8);
                break;
            case R.id.itf_driver_shell:
                driverDialog.show(dataBeans, 9);
                driverDialogOnclick(9);
                break;
            case R.id.itf_driver_rubber_parts:
                driverDialog.show(dataBeans, 10);
                driverDialogOnclick(10);
                break;
            case R.id.tv_title_save:
                submitData();
                break;
        }
    }

    private void driverDialogOnclick(final int type) {
        driverDialog.setOnClickLitener(new OnDriverOnClickListener() {
            @Override
            public void onClick(View view, int fristIndex, int secondIndex, int thirdIndex) {
                switch (type) {
                    case 0:
                        //点火塞
                        beanXList = dataBeans.get(0).getCl();
                        ignition_id_one = dataBeans.get(0).getZhu_part_id();
                        ignition_name_one = dataBeans.get(0).getPar_name();
                        ignition_id_two = beanXList.get(fristIndex).getZhu_part_id();
                        ignition_name_two = beanXList.get(fristIndex).getPar_name();
                        ignition_id_three = beanXList.get(fristIndex).getCl().get(secondIndex).getZhu_part_id();
                        ignition_name_three = beanXList.get(fristIndex).getCl().get(secondIndex).getPar_name();
                        ignition_all_name = ignition_name_one + "-" + ignition_name_two + "-" + ignition_name_three;
                        itfIgnitionPplug.setInfo(ignition_name_two + "-" + ignition_name_three);
                        break;
                    case 1:
                        //出风口传感器
                        beanXList = dataBeans.get(1).getCl();
                        outlet_sensor_id_one = dataBeans.get(1).getZhu_part_id();
                        outlet_sensor_name_one = dataBeans.get(1).getPar_name();
                        outlet_sensor_id_two = beanXList.get(fristIndex).getZhu_part_id();
                        outlet_sensor_name_two = beanXList.get(fristIndex).getPar_name();
                        outlet_sensor_id_three = beanXList.get(fristIndex).getCl().get(secondIndex).getZhu_part_id();
                        outlet_sensor_name_three = beanXList.get(fristIndex).getCl().get(secondIndex).getPar_name();
                        outlet_sensor_all_name = outlet_sensor_name_one + "-" + outlet_sensor_name_two + "-" + outlet_sensor_name_three;
                        itfOutSensor.setInfo(outlet_sensor_name_two + "-" + outlet_sensor_name_three);
                        break;
                    case 2:
                        //油泵
                        beanXList = dataBeans.get(2).getCl();
                        oil_pump_id_one = dataBeans.get(2).getZhu_part_id();
                        oil_pump_name_one = dataBeans.get(2).getPar_name();
                        oil_pump_id_two = beanXList.get(fristIndex).getZhu_part_id();
                        oil_pump_name_two = beanXList.get(fristIndex).getPar_name();
                        oil_pump_id_three = beanXList.get(fristIndex).getCl().get(secondIndex).getZhu_part_id();
                        oil_pump_name_three = beanXList.get(fristIndex).getCl().get(secondIndex).getPar_name();
                        oil_pump_id_four = beanXList.get(fristIndex).getCl().get(secondIndex).getCl().get(thirdIndex).getZhu_part_id();
                        oil_pump_name_four = beanXList.get(fristIndex).getCl().get(secondIndex).getCl().get(thirdIndex).getPar_name();
                        oil_pump_all_name = oil_pump_name_one + "-" + oil_pump_name_two + "-" + oil_pump_name_three + "-" + oil_pump_name_four;
                        itfOilPump.setInfo(oil_pump_name_two + "-" + oil_pump_name_three + "-" + oil_pump_name_four);
                        break;
                    case 3:
                        //风机
                        beanXList = dataBeans.get(3).getCl();
                        draught_fan_id_one = dataBeans.get(3).getZhu_part_id();
                        draught_fan_name_one = dataBeans.get(3).getPar_name();
                        draught_fan_id_two = beanXList.get(fristIndex).getZhu_part_id();
                        draught_fan_name_two = beanXList.get(fristIndex).getPar_name();
                        draught_fan_id_three = beanXList.get(fristIndex).getCl().get(secondIndex).getZhu_part_id();
                        draught_fan_name_three = beanXList.get(fristIndex).getCl().get(secondIndex).getPar_name();
                        draught_fan_id_four = beanXList.get(fristIndex).getCl().get(secondIndex).getCl().get(thirdIndex).getZhu_part_id();
                        draught_fan_name_four = beanXList.get(fristIndex).getCl().get(secondIndex).getCl().get(thirdIndex).getPar_name();
                        draught_fan_all_name = draught_fan_name_one + "-" + draught_fan_name_two + "-" + draught_fan_name_three + "-" + draught_fan_name_four;
                        itfFan.setInfo(draught_fan_name_two + "-" + draught_fan_name_three + "-" + draught_fan_name_four);
                        break;
                    case 4:
                        //燃烧室
                        beanXList = dataBeans.get(4).getCl();
                        firebox_id_one = dataBeans.get(4).getZhu_part_id();
                        firebox_name_one = dataBeans.get(4).getPar_name();
                        firebox_id_two = beanXList.get(fristIndex).getZhu_part_id();
                        firebox_name_two = beanXList.get(fristIndex).getPar_name();
                        firebox_id_three = beanXList.get(fristIndex).getCl().get(secondIndex).getZhu_part_id();
                        firebox_name_three = beanXList.get(fristIndex).getCl().get(secondIndex).getPar_name();
                        firebox_all_name = firebox_name_one + "-" + firebox_name_two + "-" + firebox_name_three;
                        itfCombustionChamber.setInfo(firebox_name_two + "-" + firebox_name_three);
                        break;
                    case 5:
                        //控制板
                        beanXList = dataBeans.get(5).getCl();
                        dash_board_id_one = dataBeans.get(5).getZhu_part_id();
                        dash_board_name_one = dataBeans.get(5).getPar_name();
                        dash_board_id_two = beanXList.get(fristIndex).getZhu_part_id();
                        dash_board_name_two = beanXList.get(fristIndex).getPar_name();
                        dash_board_all_name = dash_board_name_one + "-" + dash_board_name_two;
                        itfControlBoard.setInfo(dash_board_name_two);
                        break;
                    case 6:
                        //控制开关
                        beanXList = dataBeans.get(6).getCl();
                        control_switch_id_one = dataBeans.get(6).getZhu_part_id();
                        control_switch_name_one = dataBeans.get(6).getPar_name();
                        control_switch_id_two = beanXList.get(fristIndex).getZhu_part_id();
                        control_switch_name_two = beanXList.get(fristIndex).getPar_name();
                        control_switch_all_name = control_switch_name_one + "-" + control_switch_name_two;
                        itfControlSwitch.setInfo(control_switch_name_two);
                        break;
                    case 7:
                        //散热体
                        beanXList = dataBeans.get(7).getCl();
                        radiator_id_one = dataBeans.get(7).getZhu_part_id();
                        radiator_name_one = dataBeans.get(7).getPar_name();
                        radiator_id_two = beanXList.get(fristIndex).getZhu_part_id();
                        radiator_name_two = beanXList.get(fristIndex).getPar_name();
                        radiator_id_three = beanXList.get(fristIndex).getCl().get(secondIndex).getZhu_part_id();
                        radiator_name_three = beanXList.get(fristIndex).getCl().get(secondIndex).getPar_name();
                        radiator_all_name = radiator_name_one + "-" + radiator_name_two + "-" + radiator_name_three;
                        itfRadiator.setInfo(radiator_name_two + "-" + radiator_name_three);
                        break;
                    case 8:
                        //线束
                        beanXList = dataBeans.get(8).getCl();
                        wiring_harness_id_one = dataBeans.get(8).getZhu_part_id();
                        wiring_harness_name_one = dataBeans.get(8).getPar_name();
                        wiring_harness_id_two = beanXList.get(fristIndex).getZhu_part_id();
                        wiring_harness_name_two = beanXList.get(fristIndex).getPar_name();
                        wiring_harness_id_three = beanXList.get(fristIndex).getCl().get(secondIndex).getZhu_part_id();
                        wiring_harness_name_three = beanXList.get(fristIndex).getCl().get(secondIndex).getPar_name();
                        wiring_harness_all_name = wiring_harness_name_one + "-" + wiring_harness_name_two + "-" + wiring_harness_name_three;
                        itfHarness.setInfo(wiring_harness_name_two + "-" + wiring_harness_name_three);
                        break;
                    case 9:
                        //塑料外壳(铝)
                        beanXList = dataBeans.get(9).getCl();
                        outermost_shell_id_one = dataBeans.get(9).getZhu_part_id();
                        outermost_shell_name_one = dataBeans.get(9).getPar_name();
                        outermost_shell_id_two = beanXList.get(fristIndex).getZhu_part_id();
                        outermost_shell_name_two = beanXList.get(fristIndex).getPar_name();
                        outermost_shell_all_name = outermost_shell_name_one + "-" + outermost_shell_name_two;
                        itfShell.setInfo(outermost_shell_name_two);
                        break;
                    case 10:
                        //橡胶件
                        beanXList = dataBeans.get(10).getCl();
                        vulcanizate_id_one = dataBeans.get(10).getZhu_part_id();
                        vulcanizate_name_one = dataBeans.get(10).getPar_name();
                        vulcanizate_id_two = beanXList.get(fristIndex).getZhu_part_id();
                        vulcanizate_name_two = beanXList.get(fristIndex).getPar_name();
                        vulcanizate_all_name = vulcanizate_name_one + "-" + vulcanizate_name_two;
                        itfRubberParts.setInfo(vulcanizate_name_two);
                        break;
                }
            }
        });
    }

    private void viewSetData(OwnerInfo.DataBean dataBean) {

        itfIgnitionPplug.setInfo(dataBean.getIgnition_all_name());//控制板 getIgnition_all_name
        itfOutSensor.setInfo(dataBean.getOutlet_sensor_all_name());//燃烧室总名称 getOutlet_sensor_all_name
        itfOilPump.setInfo(dataBean.getOil_pump_all_name());//风机总名称 getOil_pump_all_name
        itfFan.setInfo(dataBean.getDraught_fan_all_name());//散热体总名称  getDraught_fan_all_name
        itfCombustionChamber.setInfo(dataBean.getFirebox_all_name());//线束总名称  getFirebox_all_name
        itfControlBoard.setInfo(dataBean.getDash_board_all_name());//点火塞总名称  getDash_board_all_name
        itfControlSwitch.setInfo(dataBean.getControl_switch_all_name());//油泵总名称  getControl_switch_all_name
        itfRadiator.setInfo(dataBean.getRadiator_all_name());//橡胶件总名称  getRadiator_all_name
        itfHarness.setInfo(dataBean.getWiring_harness_all_name());//出风口传感器总名称  getWiring_harness_all_name
        itfShell.setInfo(dataBean.getOutermost_shell_all_name());//外壳总名称
        itfRubberParts.setInfo(dataBean.getVulcanizate_all_name());//控制开关总名称  getVulcanizate_all_name
    }

    private void submitData() {
        mWeiboDialog = WeiboDialogUtils.createLoadingDialog(context, "加载中...");
        String user_car_id = PreferenceHelper.getInstance(DriveinfoActivity.this).getString("car_id", "");
        Gson gson = new Gson();
        HashMap<String, String> map = new HashMap<>();
        map.put("code", "03206");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(DriveinfoActivity.this).getAppToken());
        map.put("user_car_id", user_car_id);
        if (!StringUtils.isEmpty(ignition_id_one)){
            map.put("ignition_id_one", ignition_id_one);
            map.put("ignition_name_one", ignition_name_one);
            map.put("ignition_id_two", ignition_id_two);
            map.put("ignition_name_two", ignition_name_two);
            map.put("ignition_id_three", ignition_id_three);
            map.put("ignition_name_three", ignition_name_three);
            map.put("ignition_all_name", ignition_all_name);
        }


        if (!StringUtils.isEmpty(outlet_sensor_id_one)){
            map.put("outlet_sensor_id_one", outlet_sensor_id_one);
            map.put("outlet_sensor_name_one", outlet_sensor_name_one);
            map.put("outlet_sensor_id_two", outlet_sensor_id_two);
            map.put("outlet_sensor_name_two", outlet_sensor_name_two);
            map.put("outlet_sensor_id_three", outlet_sensor_id_three);
            map.put("outlet_sensor_name_three", outlet_sensor_name_three);
            map.put("outlet_sensor_all_name", outlet_sensor_all_name);
        }


        if (!StringUtils.isEmpty(oil_pump_id_one)){
            map.put("oil_pump_id_one", oil_pump_id_one);
            map.put("oil_pump_name_one", oil_pump_name_one);
            map.put("oil_pump_id_two", oil_pump_id_two);
            map.put("oil_pump_name_two", oil_pump_name_two);
            map.put("oil_pump_id_three", oil_pump_id_three);
            map.put("oil_pump_name_three", oil_pump_name_three);
            map.put("oil_pump_id_four", oil_pump_id_four);
            map.put("oil_pump_name_four", oil_pump_name_four);
            map.put("oil_pump_all_name", oil_pump_all_name);
        }


        if (!StringUtils.isEmpty(draught_fan_id_one)){
            map.put("draught_fan_id_one", draught_fan_id_one);
            map.put("draught_fan_name_one", draught_fan_name_one);
            map.put("draught_fan_id_two", draught_fan_id_two);
            map.put("draught_fan_name_two", draught_fan_name_two);
            map.put("draught_fan_id_three", draught_fan_id_three);
            map.put("draught_fan_name_three", draught_fan_name_three);
            map.put("draught_fan_id_four", draught_fan_id_four);
            map.put("draught_fan_name_four", draught_fan_name_four);
            map.put("draught_fan_all_name", draught_fan_all_name);
        }


        if (!StringUtils.isEmpty(firebox_all_name)){
            map.put("firebox_all_name", firebox_all_name);
            map.put("firebox_id_one", firebox_id_one);
            map.put("firebox_name_one", firebox_name_one);
            map.put("firebox_id_two", firebox_id_two);
            map.put("firebox_name_two", firebox_name_two);
            map.put("firebox_id_three", firebox_id_three);
            map.put("firebox_name_three", firebox_name_three);
        }


        if (!StringUtils.isEmpty(dash_board_id_one)){
            map.put("dash_board_id_one", dash_board_id_one);
            map.put("dash_board_name_one", dash_board_name_one);
            map.put("dash_board_id_two", dash_board_id_two);
            map.put("dash_board_name_two", dash_board_name_two);
            map.put("dash_board_all_name", dash_board_all_name);
        }


        if (!StringUtils.isEmpty(control_switch_id_one)){
            map.put("control_switch_id_one", control_switch_id_one);
            map.put("control_switch_name_one", control_switch_name_one);
            map.put("control_switch_id_two", control_switch_id_two);
            map.put("control_switch_name_two", control_switch_name_two);
            map.put("control_switch_all_name", control_switch_all_name);
        }


        if (!StringUtils.isEmpty(radiator_id_one)){
            map.put("radiator_id_one", radiator_id_one);
            map.put("radiator_name_one", radiator_name_one);
            map.put("radiator_id_two", radiator_id_two);
            map.put("radiator_name_two", radiator_name_two);
            map.put("radiator_id_three", radiator_id_three);
            map.put("radiator_name_three", radiator_name_three);
            map.put("radiator_all_name", radiator_all_name);
        }



        if (!StringUtils.isEmpty(wiring_harness_id_one)){
            map.put("wiring_harness_id_one", wiring_harness_id_one);
            map.put("wiring_harness_name_one", wiring_harness_name_one);
            map.put("wiring_harness_id_two", wiring_harness_id_two);
            map.put("wiring_harness_name_two", wiring_harness_name_two);
            map.put("wiring_harness_id_three", wiring_harness_id_three);
            map.put("wiring_harness_name_three", wiring_harness_name_three);
            map.put("wiring_harness_all_name", wiring_harness_all_name);
        }

        if (!StringUtils.isEmpty(outermost_shell_id_one)){
            map.put("outermost_shell_id_one", outermost_shell_id_one);
            map.put("outermost_shell_name_one", outermost_shell_name_one);
            map.put("outermost_shell_id_two", outermost_shell_id_two);
            map.put("outermost_shell_name_two", outermost_shell_name_two);
            map.put("outermost_shell_all_name", outermost_shell_all_name);
        }

        if (!StringUtils.isEmpty(vulcanizate_id_one)){
            map.put("vulcanizate_id_one", vulcanizate_id_one);
            map.put("vulcanizate_name_one", vulcanizate_name_one);
            map.put("vulcanizate_id_two", vulcanizate_id_two);
            map.put("vulcanizate_name_two", vulcanizate_name_two);
            map.put("vulcanizate_all_name", vulcanizate_all_name);
        }



        OkGo.<AppResponse>post(Urls.SERVER_URL + "wit/app/zhu")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse>() {
                    @Override
                    public void onSuccess(final Response<AppResponse> response) {
                        WeiboDialogUtils.closeDialog(mWeiboDialog);
                        if (response.body().msg_code.equals("0000")) {
                            UIHelper.ToastMessage(DriveinfoActivity.this, "保存成功", Toast.LENGTH_SHORT);
                        } else if (response.body().msg_code.equals("0001")) {
                            UIHelper.ToastMessage(DriveinfoActivity.this, response.body().msg, Toast.LENGTH_SHORT);
                        }

                    }

                    @Override
                    public void onError(Response<AppResponse> response) {
                        //   AlertUtil.t(MyShowDataBottomActivity.this, response.getException().getMessage());
                    }
                });


    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, DriveinfoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    public void requestData11() {//请求的参数
        Map<String, String> map = new HashMap<>();
        map.put("code", "00005");
        map.put("key", Urls.key);
        //map.put("token", UserManager.getManager(this).getAppToken());
        map.put("type_id", "zhu_parts_factory");
        Gson gson = new Gson();
        OkGo.<AppResponse<DriverData.DataBean>>post(Urls.SERVER_URL + "msg")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<DriverData.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<DriverData.DataBean>> response) {
                        //  String data = response.body().toString();
                        //   DriverData driverData = GsonUtil.parseJsonWithGson(data, DriverData.class);
                        WeiboDialogUtils.closeDialog(mWeiboDialog);

                        dataBeans = response.body().data;
//                        if (driverData.getMsg_code().equals("0000")) {
//                            dataBeans = driverData.getData();
//                        } else {
//                            Toast.makeText(context, driverData.getMsg(), Toast.LENGTH_SHORT).show();
//                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<DriverData.DataBean>> response) {

                    }
                });
    }

    public void requestData() {

        Map<String, String> map = new HashMap<>();
        map.put("code", "03205");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("user_car_id", PreferenceHelper.getInstance(this).getString("car_id", ""));
        Gson gson = new Gson();
        OkGo.<AppResponse<OwnerInfo.DataBean>>post(Urls.SERVER_URL + "wit/app/zhu")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<OwnerInfo.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<OwnerInfo.DataBean>> response) {
                        dataBean = response.body().data;
                        viewSetData(dataBean.get(0));
                    }

                    @Override
                    public void onError(Response<AppResponse<OwnerInfo.DataBean>> response) {
                        // AlertUtil.t(PartsInfoActivity.this, response.getException().getMessage());
                    }
                });
    }

    private void getCarInfo() {

    }
}
