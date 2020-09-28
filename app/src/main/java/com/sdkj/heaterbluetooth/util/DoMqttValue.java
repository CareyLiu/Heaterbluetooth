package com.sdkj.heaterbluetooth.util;

import android.content.Context;
import android.util.Log;

import com.sdkj.heaterbluetooth.app.App;
import com.sdkj.heaterbluetooth.app.ConfigValue;
import com.sdkj.heaterbluetooth.app.ConstanceValue;
import com.sdkj.heaterbluetooth.app.Notice;
import com.sdkj.heaterbluetooth.app.PreferenceHelper;

public class DoMqttValue {

    public Context context;//上下文
    public String message;//消息


    public static final String ZHINENGJIAJU = "ZHINENGJIAJU";
    public static final String FENGNUAN = "FENGNUAN";
    public static final String SHUINUAN = "SHUINUAN";
    public static final String KONGTIAO = "KONGTIAO";
    public static final String KONGCHE = "KONGCHE";


    public void doValue(Context context, String topic, String message) {
        //控制硬件项目  1. 智能家居 2.风暖加热器 3.水暖加热器 4.驻车空调 5.神灯控车
        String chooseXiangMu = PreferenceHelper.getInstance(context).getString(App.CHOOSE_KONGZHI_XIANGMU, "0");
        Log.i("chooseXiangMu", chooseXiangMu);
        String zhiLingMa = PreferenceHelper.getInstance(context).getString(ConfigValue.ZHILINGMA, "");
        Log.i("zhiLingMa", zhiLingMa);

        switch (chooseXiangMu) {
            case ZHINENGJIAJU:
                break;
            case FENGNUAN:
                if (message.contains("_")) {
                    String messageData = message.substring(2, message.length() - 1);
                    String[] arr = messageData.split("_");

                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i].contains("g")) {
                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_J_G;
                            n.content = arr[i];
                            Log.i("g--n.content", n.content.toString());
                            RxBus.getDefault().sendRx(n);

                        } else if (arr[i].contains("M")) {
                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_J_M;
                            n.content = arr[i];
                            RxBus.getDefault().sendRx(n);
                            Log.i("MSG_CAR_J_M", n.content.toString());

                        } else if (arr[i].contains("h")) {
                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_h;
                            n.content = arr[i];
                            RxBus.getDefault().sendRx(n);
                            Log.i("MSG_CAR_J_M", n.content.toString());

                        } else if (arr[i].contains("l")) {

                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_l;
                            n.content = arr[i];
                            RxBus.getDefault().sendRx(n);
                            Log.i("MSG_CAR_l", n.content.toString());

                        } else if (arr[i].contains("m")) {

                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_l;
                            n.content = arr[i];
                            RxBus.getDefault().sendRx(n);
                            Log.i("MSG_CAR_l", n.content.toString());
                        } else if (arr[i].contains("n")) {

                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_n;
                            n.content = arr[i];
                            RxBus.getDefault().sendRx(n);
                            Log.i("MSG_CAR_n", n.content.toString());
                        } else if (arr[i].contains("p")) {

                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_p;
                            n.content = arr[i];
                            RxBus.getDefault().sendRx(n);
                            Log.i("MSG_CAR_p", n.content.toString());
                        } else if (arr[i].contains("r")) {

                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_r;
                            n.content = arr[i];
                            RxBus.getDefault().sendRx(n);
                            Log.i("MSG_CAR_r", n.content.toString());

                        } else if (arr[i].contains("s")) {

                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_s;
                            n.content = arr[i];
                            RxBus.getDefault().sendRx(n);
                            Log.i("MSG_CAR_s", n.content.toString());

                        } else if (arr[i].contains("Z")) {

//                            Notice n = new Notice();
//                            n.type = ConstanceValue.MSG_CAR_Z;
//                            n.content = arr[i];
//                            RxBus.getDefault().sendRx(n);
//                            Log.i("MSG_CAR_Z", n.content.toString());

                        } else if (message.toString().contains("k")) {
                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_K;
                            n.content = message.toString();
                            RxBus.getDefault().sendRx(n);

                        } else if (message.toString().contains("i")) {

                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_CAR_I;
                            n.content = message.toString();
                            RxBus.getDefault().sendRx(n);
                        }
                    }

                } else if (message.toString().equals("M691.")) { //清除故障成功
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CLEARGUZHANGSUCCESS;
                    n.content = message.toString();
                    RxBus.getDefault().sendRx(n);
                } else if (message.toString().contains("i")) {

                } else if (message.toString().equals("k5011.")) {
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CAR_HUI_FU_CHU_CHAGN;
                    RxBus.getDefault().sendRx(n);

                } else if (message.toString().contains("h")) {//h是风油比
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CAR_FEGNYOUBI;
                    n.content = message.toString();
                    RxBus.getDefault().sendRx(n);
                } else if (message.toString().equals("M001.")) {
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_CAR_J_G;
//                            n.content = message.toString();
                    n.content = "g0011108122015500026-02500041";
                    RxBus.getDefault().sendRx(n);
                } else if (message.equals("k6111.")) {//档位模式
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_K6111;
                    RxBus.getDefault().sendRx(n);
                } else if (message.equals("k6131.")) {//关机
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_K6131;
                    RxBus.getDefault().sendRx(n);
                } else if (message.equals("k6121.")) {//空调模式
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_K6121;
                    RxBus.getDefault().sendRx(n);
                } else if (message.equals("k6141.")) {
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_K6141;
                    RxBus.getDefault().sendRx(n);
                } else if (message.equals("k6161.")) {
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_K6161;
                    RxBus.getDefault().sendRx(n);
                } else if (message.equals("k6171.")) {
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_K6171;
                    RxBus.getDefault().sendRx(n);
                } else if (message.equals(zhiLingMa)) {
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_ZHILINGMA;
                    RxBus.getDefault().sendRx(n);
                } else if (message.substring(0, 3).equals("c_X")) {

                    String str = message.substring(3, 20);
                    Notice n = new Notice();
                    n.type = ConstanceValue.MSG_C_P;
                    n.content = str;
                    RxBus.getDefault().sendRx(n);
                }
                break;
            case SHUINUAN:
                shuiNuan(topic, message);
                break;
            case KONGTIAO:
                break;
            case KONGCHE:
                break;
        }
        if (topic.contains("zn/")) {//智能家居 主题
            String messageData = message.toString().substring(2, message.toString().length() - 1);
        } else if (message.toString().equals("j_s")) {
            Notice n = new Notice();
            n.type = ConstanceValue.MSG_SN_DATA;
//                            n.content = message.toString();
            n.content = "j_s345611166666661102640265050070600017002310.";
            RxBus.getDefault().sendRx(n);
        } else if (message.toString().contains("j_s")) {
            Notice n = new Notice();
            n.type = ConstanceValue.MSG_SN_DATA;
            n.content = message.toString();
            RxBus.getDefault().sendRx(n);
        }
    }


    //水暖相关代码
    private void shuiNuan(String topic, String message) {
        if (topic.contains("wh/app") || topic.contains("wh/hardware/")) {
            Notice n = new Notice();
            n.type = ConstanceValue.MSG_SN_DATA;
            n.content = message.toString();
            RxBus.getDefault().sendRx(n);
        }
    }
}
