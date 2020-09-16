package com.sdkj.heaterbluetooth.app;

public class HardWareValue {
    public static String CLIENT_ID = "witcboxapp";//APP:witcboxapp+汽车id数字 --- §1.3	MQTT客户端client_id命名规则
    /**
     * 1.汽车盒子刚刚启动->接入互联网->订阅本身地址->自己给自己订阅的地址发送心跳,发送，自己接收到了g.说明自己和MQTT连接在线
     */
    public static String WIT_CBOX_HARDWARE = "wit/cbox/hardware/"; //硬件请求头

    public static String WIT_APP = "wit/app/";//警报头使用

    public static String WIT_SERVER = "wit/server/";//1.app通知后台APP用户全局主题上线


}
