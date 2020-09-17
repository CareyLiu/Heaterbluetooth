package com.sdkj.heaterbluetooth.util;

/**
 * Created by Android on 2018/5/26.
 *
 */

public class ConstantUtil {
    //

    public static boolean isOpen = false;
    //消息类型
    public static final int MSG_HEATER_MANUAL_ORDER = 0x001;//开机
    public static final int MSG_HEATER_SHUTDOWN_ORDER = 0x002;//关机
    public static final int MSG_UPDATE_GEAR_ORDER = 0x003;//档位更新
    public static final int MSG_HEATER_ROGRESS_VALUE_CHANGE = 0x004;//弧形进度条更新
    public static final int MSG_HEATER_HOST_RECOVERY = 0x005;//主机参数恢复出厂设置
    public static final int MSG_HEATER_ACTUAL_DATA = 0x006;//驻车加热器实时数据
    public static final int MSG_HEATER_HOST_DATA = 0x007;//主机参数实时数据
    public static final int MSG_HEATER_CLEAN_FAULT = 0x008;//清除故障

}
