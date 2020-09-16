package com.sdkj.heaterbluetooth.app;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public interface ConstanceValue {
    String HIDE_LOADING_STATUS_MSG = "hide_loading_status_msg";
    int MSG_CAR_J_G = 0x10000;//g汽车GPS经纬度
    int MSG_CAR_J_M = 0x10001;//M风暖加热器时时数据
    int MSG_CAR_K = 0x0002;//k车门/车窗/车锁
    int MSG_CAR_h = 0x0003;//胎压
    int MSG_CAR_l = 0x0004;//开关
    int MSG_CAR_m = 0x0005;//m汽车其它数据
    int MSG_CAR_n = 0x0006;//硬件监控报警

    int MSG_CAR_p = 0x0007;//硬件日志
    int MSG_CAR_r = 0x0008;//数据直接存储到数据库，一条一条的存好 维护时候可以导出数据库数据   常看数据分析问题，内部信号量状态等观察区数据
    int MSG_CAR_s = 0x0009;//掉电不消失数据 设备出厂时候是0  一直累加 到产品生命周期终止
    int MSG_CAR_Z = 0x00010;//状态
    int MSG_GUZHANG = 0X00011;//故障状态
    int MSG_CLEARGUZHANGSUCCESS = 0X00012;//清除故障成功
    int MSG_UNSUB_MQTT = 0X00013;//清除application mqtt订阅的信息
    int MSG_CONNET_MQTT = 0X00014;//重新登录的时候，重连mqtt
    int MSG_CAR_I = 0x10015;//获取主机的时时信息
    int MSG_CAR_HUI_FU_CHU_CHAGN = 0x10016;//恢复出厂设置成功
    int MSG_CAR_FEGNYOUBI = 0x10017;//获得风油比参数
    int MSG_WETCHSUCCESS = 0x10018;//微信支付成功
    int MSG_DALIBAO_SUCCESS = 0X10019;

    int MSG_SETFZONGHE = 0x10020;//点击综合显示的项目

    int MSG_TUANGOUPAY = 0x10021;//团购支付
    int MSG_DIYONGQUAN = 0x10022;//返回
    int MSG_SAOMASUCCESS = 0x10023;//支付成功
    int MSG_SAOMAFAILE = 0x10024;//支付失败


    int MSG_XINTUANYOU_PAY = 0x10025;//新团油支付成功
    int MSG_XINTUANYOU_PAY_FAIL = 0x10026;//新团友支付失败


    int MSG_ZIYING_PAY = 0x10027;//自营支付
    int MSG_ZIYING_PAY_FAIL = 0x10028;//自营支付失败
    int MSG_GETADDRESS = 0x10029;//获得收货地址
    int MSG_NONEADDRESS = 0x10030;//没有收货地址
    int MSG_APK_DOWNLOADSUCCESS = 0x10031;//apk更新完毕执行安装
    int MSG_DINGDAN_PAY = 0x10032;//执行订单支付操作

    int MSG_PAY_SUCCESS_REFRESH_WODE = 0x10033;//支付成功刷新接口

    int MSG_KT_DATA = 0x10034;//水暖加热器数据
    int MSG_SN_DATA = 0x10035;//水暖加热器数据
    int MSG_DAILISHANG_TIXIAN = 0x10036;//提现
    int MSG_ADD_CHELIANG_SUCCESS = 0x10037;//添加车辆成功


    /**
     * 智能家居部分
     */
    int MSG_FAMILY_MANAGE_ADD = 0x10038;//家庭管理，创建家庭
    int MSG_FAMILY_MANAGE_CHANGENAME = 0x10039;//家庭管理，更改家庭名字
    int MSG_PEIWANG_SUCCESS = 0x10041;//配网成功
    int MSG_ROOM_MANAGE_ADD = 0x10042;//家庭管理，新建家庭名字
    int MSG_ROOM_MANAGE_CHANGENAME = 0x10043;//家庭管理，更改家庭名字
    int MSG_ROOM_DEVICE_CHANGENAME = 0x10044;//家庭管理，更改家庭名字

    /**
     * MQTT 连接
     */
    int MSG_MQTT_CONNECTCOMPLETE = 0x10045;//连接完成
    int MSG_MQTT_CONNECTLOST = 0x10046;//连接丢失
    int MSG_MQTT_CONNECTARRIVE = 0x10047;//收到连接
    int MSG_MQTT_CONNECT_CHONGLIAN_ONSUCCESS = 0x10048;//重连成功
    int MSG_MQTT_CONNECT_CHONGLIAN_ONFAILE = 0x10049;//重连失败


    int MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN = 0x10050;//重连失败
    int MSG_RONGYUN_STATE = 0x10051;//连接状态


    int MSG_BAZI_FSBJ1 = 0x10052;//风水摆件绑定成功
    int MSG_BAZI_FSBJ2 = 0x10053;//风水摆件绑定成功2
    int MSG_SERVICE_CHAT = 0x10054;//服务跳转

    int MSG_RONGYUN_REVICE = 0x10055;//接收聊天消息
    int MSG_GUZHANG_SHOUYE = 0x10056;//故障到首页
    int MSG_GOTOXIAOXI = 0x10057;//跳转到消息页
    int MSG_P = 0x10058;//接收到了p.
    int MSG_ZHINENGJIAJU = 0x10059;//跳转到智能家居


    int MSG_K6111 = 0x10060;//接收到了档位开机指令
    int MSG_K6131 = 0x10061;//接收到关机指令
    int MSG_K6121 = 0x10062;//接收到了空调开机
    int MSG_K6141 = 0x10063;//接收到了水泵模式
    int MSG_K6161 = 0x10064;//接收到了预泵油模式
    int MSG_K6171 = 0x10065;//接收到了预通风模式

    int MSG_ZHILINGMA = 0x10066;//指令码

}
