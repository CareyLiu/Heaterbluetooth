package com.sdkj.heaterbluetooth.model;

import java.io.Serializable;

public class AlarmClass implements Serializable {
    public String type; //1.风暖加热器 2.水暖加热器 3.汽车 4.货车防盗

    public String sound;//要播放音乐的名字
    public String zhu_car_stoppage_no;//故障/报警码
    public String failure_name;//故障描述
    public String xinghao;//型号
    public String changjia_name;//厂家
    public String sell_phone;//客服电话
    public String install_time;//安装时间
    public String install_addr;//安装地址
    public String ccid;//24位ccid
}
