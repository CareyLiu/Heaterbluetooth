package com.sdkj.heaterbluetooth.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/9/30.
 */
public class SharedPreferenceutils {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SharedPreferenceutils(Context context, String file) {
        sp = context.getSharedPreferences(file, context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public boolean getIsFirstType() {
        return sp.getBoolean("isFirst", true);
    }


    public void setIsFirstType(boolean isFirstnew) {
        editor.putBoolean("isFirst", isFirstnew);
        editor.commit();
    }

    public String getUserPhone() {
        return sp.getString("user_phone", "");
    }

    public void setUserPhone(String user_phone) {
        editor.putString("user_phone", user_phone);
        editor.commit();
    }

    public String getPowerState() {
        return sp.getString("power_state", "");
    }

    public void setPowerState(String power_state) {
        editor.putString("power_state", power_state);
        editor.commit();
    }

    //记录上一次登录的身份
    public String getIdentity() {
        return sp.getString("identity", "");
    }

    public void setIdentity(String identity) {
        editor.putString("identity", identity);
        editor.commit();
    }


    //记录上一次档位
    public String getGear() {
        return sp.getString("gear", "");
    }

    public void setGear(String gear) {
        editor.putString("gear", gear);
        editor.commit();
    }

    public String getInst_code() {
        return sp.getString("inst_code", "");
    }

    public void setInst_code(String inst_code) {
        editor.putString("inst_code", inst_code);
        editor.commit();
    }

    public String getInst_name() {
        return sp.getString("inst_name", "");
    }

    public void setInst_name(String inst_name) {
        editor.putString("inst_name", inst_name);
        editor.commit();
    }


    public String getPwd() {
        return sp.getString("pwd", "");
    }

    public void setPwd(String pwd) {
        editor.putString("pwd", pwd);
        editor.commit();
    }

    public String getSubsystem_id() {
        return sp.getString("subsystem_id", "");
    }

    public void setSubsystem_id(String subsystem_id) {
        editor.putString("subsystem_id", subsystem_id);
        editor.commit();
    }

    public String getUserId() {
        return sp.getString("of_user_id", "");
    }

    public void setUserId(String of_user_id) {
        editor.putString("of_user_id", of_user_id);
        editor.commit();
    }

    public String getUserName() {
        return sp.getString("user_name", "");
    }

    public void setUserName(String user_name) {
        editor.putString("user_name", user_name);
        editor.commit();
    }

    public String getToken() {
        return sp.getString("token", "");
    }

    public void setToken(String token) {
        editor.putString("token", token);
        editor.commit();
    }

    public String getToken_Rong() {
        return sp.getString("token_rong", "");
    }

    public void setToken_Rong(String token_rong) {
        editor.putString("token_rong", token_rong);
        editor.commit();
    }

    public String getUserCarId() {
        return sp.getString("zhu_user_car_id", "");
    }

    public void setUserCarId(String zhu_user_car_id) {
        editor.putString("zhu_user_car_id", zhu_user_car_id);
        editor.commit();
    }

    public String getJpushState() {
        return sp.getString("jpushState", "");
    }

    public void setJpushState(String jpushState) {
        editor.putString("jpushState", jpushState);
        editor.commit();
    }

    public String getInstallData() {
        return sp.getString("install_data", "");
    }

    public void setInstallData(String install_data) {
        editor.putString("install_data", install_data);
        editor.commit();
    }

    public String getAccid() {
        return sp.getString("accid", "");
    }

    public void setAccid(String accid) {
        editor.putString("accid", accid);
        editor.commit();
    }

    public String getPlateNumber() {
        return sp.getString("plate_number", "");
    }

    public void setPlateNumber(String plate_number) {
        editor.putString("plate_number", plate_number);
        editor.commit();
    }

    public String getFault() {
        return sp.getString("fault", "");
    }

    public void setFault(String fault) {
        editor.putString("fault", fault);
        editor.commit();
    }

    public void setDigitMachineCode(String digit_machine_code) {
        editor.putString("digit_machine_code", digit_machine_code);
        editor.commit();
    }

    public String getDigitMachineCode() {
        return sp.getString("digit_machine_code", "0000");
    }

    public boolean getHeaterStatus() {
        return sp.getBoolean("heater_status", false);
    }

    public void setHeaterStatus(boolean heater_status) {
        editor.putBoolean("heater_status", heater_status);
        editor.commit();
    }

    public boolean getHeaterLastStatus() {
        return sp.getBoolean("heater_last_status", false);
    }

    public void setHeaterLastStatus(boolean heater_last_status) {
        editor.putBoolean("heater_last_status", heater_last_status);
        editor.commit();
    }

    public String getCurrentTemperature() {
        return sp.getString("current_temperature", "000");
    }

    public void setCurrentTemperature(String current_temperature) {
        editor.putString("current_temperature", current_temperature);
        editor.commit();
    }

    public String getDiagnosisBrokenCode() {
        return sp.getString("diagnosis_broken_code", "00");
    }

    public void setDiagnosisBrokenCode(String diagnosis_broken_code) {
        editor.putString("diagnosis_broken_code", diagnosis_broken_code);
        editor.commit();
    }

    public Long getOpenTime() {
        return sp.getLong("open_time", System.currentTimeMillis());
    }

    public void setOpenTime(Long open_time) {
        editor.putLong("open_time", open_time);
        editor.commit();
    }

    public Long getCloseTime() {
        return sp.getLong("close_time", System.currentTimeMillis());
    }

    public void setCloseTime(Long close_time) {
        editor.putLong("close_time", close_time);
        editor.commit();
    }

    public String getStartState() {
        return sp.getString("start_state", "");
    }

    public void setStartState(String start_state) {
        editor.putString("start_state", start_state);
        editor.commit();
    }

    public String getHostStatus() {
        return sp.getString("host_status", "00");
    }

    public void setHostStatus(String host_status) {
        editor.putString("host_status", host_status);
        editor.commit();
    }

    public String getVoltageData() {
        return sp.getString("voltage_data", "000");
    }

    public void setVoltageData(String voltage_data) {
        editor.putString("voltage_data", voltage_data);
        editor.commit();
    }

    public String getFanSpeed() {
        return sp.getString("fan_speed", "0000");
    }

    public void setFanSpeed(String fan_speed) {
        editor.putString("fan_speed", fan_speed);
        editor.commit();
    }

    public String getHeatPower() {
        return sp.getString("heat_power", "000");
    }

    public void setHeatPower(String heat_power) {
        editor.putString("heat_power", heat_power);
        editor.commit();
    }

    public String getOilFerquency() {
        return sp.getString("oil_ferquency", "000");
    }

    public void setOilFerquency(String oil_ferquency) {
        editor.putString("oil_ferquency", oil_ferquency);
        editor.commit();
    }

    public String getEntryData() {
        return sp.getString("entry_data", "000");
    }

    public void setEntryData(String entry_data) {
        editor.putString("entry_data", entry_data);
        editor.commit();
    }

    public String getEffluentData() {
        return sp.getString("effluent_data", "000");
    }

    public void setEffluentData(String effluent_data) {
        editor.putString("effluent_data", effluent_data);
        editor.commit();
    }

    public String getHostMode() {
        return sp.getString("host_mode", "000");
    }

    public void setHostMode(String host_mode) {
        editor.putString("host_mode", host_mode);
        editor.commit();
    }


    //保存设置参数
    public int getParamsFanspeedOne() {
        return sp.getInt("params_fanspeed_one_manual", 0);
    }

    public void setParamsFanspeedOne(int params_fanspeed_one_manual) {
        editor.putInt("params_fanspeed_one_manual", params_fanspeed_one_manual);
        editor.commit();
    }

    public float getParamsOilfrequencyOne() {
        return sp.getFloat("params_oilfrequency_one_manual", 0);
    }

    public void setParamsOilfrequencyOne(float params_oilfrequency_one_manual) {
        editor.putFloat("params_oilfrequency_one_manual", params_oilfrequency_one_manual);
        editor.commit();
    }

    public int getParamsFanspeedTwo() {
        return sp.getInt("params_fanspeed_two_manual", 0);
    }

    public void setParamsFanspeedTwo(int params_fanspeed_two_manual) {
        editor.putInt("params_fanspeed_two_manual", params_fanspeed_two_manual);
        editor.commit();
    }

    public float getParamsOilfrequencyTwo() {
        return sp.getFloat("params_oilfrequency_two_manual", 0);
    }

    public void setParamsOilfrequencyTwo(float params_oilfrequency_two_manual) {
        editor.putFloat("params_oilfrequency_two_manual", params_oilfrequency_two_manual);
        editor.commit();
    }

    public int getParamsFanspeedThree() {
        return sp.getInt("params_fanspeed_three_manual", 0);
    }

    public void setParamsFanspeedThree(int params_fanspeed_three_manual) {
        editor.putInt("params_fanspeed_three_manual", params_fanspeed_three_manual);
        editor.commit();
    }

    public float getParamsOilfrequencyThree() {
        return sp.getFloat("params_oilfrequency_three_manual", 0);
    }

    public void setParamsOilfrequencyThree(float params_oilfrequency_three_manual) {
        editor.putFloat("params_oilfrequency_three_manual", params_oilfrequency_three_manual);
        editor.commit();
    }

    public int getParamsFanspeedFour() {
        return sp.getInt("params_fanspeed_four_manual", 0);
    }

    public void setParamsFanspeedFour(int params_fanspeed_four_manual) {
        editor.putInt("params_fanspeed_four_manual", params_fanspeed_four_manual);
        editor.commit();
    }

    public float getParamsOilfrequencyFour() {
        return sp.getFloat("params_oilfrequency_four_manual", 0);
    }

    public void setParamsOilfrequencyFour(float params_oilfrequency_four_manual) {
        editor.putFloat("params_oilfrequency_four_manual", params_oilfrequency_four_manual);
        editor.commit();
    }

    public int getParamsFanspeedFive() {
        return sp.getInt("params_fanspeed_five_manual", 0);
    }

    public void setParamsFanspeedFive(int params_fanspeed_five_manual) {
        editor.putInt("params_fanspeed_five_manual", params_fanspeed_five_manual);
        editor.commit();
    }

    public float getParamsOilfrequencyFive() {
        return sp.getFloat("params_oilfrequency_five_manual", 0);
    }

    public void setParamsOilfrequencyFive(float params_oilfrequency_five_manual) {
        editor.putFloat("params_oilfrequency_five_manual", params_oilfrequency_five_manual);
        editor.commit();
    }

    public int getParamsHeatPower12() {
        return sp.getInt("params_heater_power_12", 0);
    }

    public void setParamsHeatPower12(int params_heater_power_12) {
        editor.putInt("params_heater_power_12", params_heater_power_12);
        editor.commit();
    }

    public int getParamsHeatPower24() {
        return sp.getInt("params_heater_power_24", 0);
    }

    public void setParamsHeatPower24(int params_heater_power_24) {
        editor.putInt("params_heater_power_24", params_heater_power_24);
        editor.commit();
    }


    public String getgetToken_rong() {
        return sp.getString("token_rong", "");
    }

    public void setgetToken_rong(String token_rong) {
        editor.putString("token_rong", token_rong);
        editor.commit();
    }


    public String getCurrentData() {
        return sp.getString("current_temperature", "000");
    }

    public void setCurrentData(String current_temperature) {
        editor.putString("current_temperature", current_temperature);
        editor.commit();
    }
}
