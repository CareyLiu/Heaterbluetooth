package com.sdkj.heaterbluetooth.model;

import java.util.List;

/**
 * Created by Android on 2018/6/1.
 */

public class DriverData {

    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"par_name":"点火塞","zhu_part_config":"1","zhu_part_id":"1","cl":[{"zhu_part_id":"203","vender_id":"3","par_name":"京瓷","zhu_part_id_up":"1","cl":[{"zhu_part_id":"206","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"203","cl":[]},{"zhu_part_id":"207","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"203","cl":[]}]},{"zhu_part_id":"204","vender_id":"4","par_name":"利迈","zhu_part_id_up":"1","cl":[{"zhu_part_id":"208","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"204","cl":[]},{"zhu_part_id":"209","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"204","cl":[]}]},{"zhu_part_id":"205","vender_id":"5","par_name":"其他","zhu_part_id_up":"1","cl":[{"zhu_part_id":"210","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"205","cl":[]},{"zhu_part_id":"211","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"205","cl":[]}]}]},{"par_name":"出风口传感器","zhu_part_config":"2","zhu_part_id":"2","cl":[{"zhu_part_id":"212","vender_id":"6","par_name":"邱小波","zhu_part_id_up":"2","cl":[{"zhu_part_id":"216","par_id":"zhu_part_2_NTC50K","par_name":"NTC50K","zhu_part_id_up":"212","cl":[]},{"zhu_part_id":"217","par_id":"zhu_part_2_PT1000","par_name":"PT1000","zhu_part_id_up":"212","cl":[]},{"zhu_part_id":"218","par_id":"zhu_part_2_99","par_name":"其他","zhu_part_id_up":"212","cl":[]}]},{"zhu_part_id":"213","vender_id":"7","par_name":"王怀素","zhu_part_id_up":"2","cl":[{"zhu_part_id":"219","par_id":"zhu_part_2_NTC50K","par_name":"NTC50K","zhu_part_id_up":"213","cl":[]},{"zhu_part_id":"220","par_id":"zhu_part_2_PT1000","par_name":"PT1000","zhu_part_id_up":"213","cl":[]},{"zhu_part_id":"221","par_id":"zhu_part_2_99","par_name":"其他","zhu_part_id_up":"213","cl":[]}]},{"zhu_part_id":"214","vender_id":"8","par_name":"段德双","zhu_part_id_up":"2","cl":[{"zhu_part_id":"222","par_id":"zhu_part_2_NTC50K","par_name":"NTC50K","zhu_part_id_up":"214","cl":[]},{"zhu_part_id":"223","par_id":"zhu_part_2_PT1000","par_name":"PT1000","zhu_part_id_up":"214","cl":[]},{"zhu_part_id":"224","par_id":"zhu_part_2_99","par_name":"其他","zhu_part_id_up":"214","cl":[]}]},{"zhu_part_id":"215","vender_id":"5","par_name":"其他","zhu_part_id_up":"2","cl":[{"zhu_part_id":"225","par_id":"zhu_part_2_NTC50K","par_name":"NTC50K","zhu_part_id_up":"215","cl":[]},{"zhu_part_id":"226","par_id":"zhu_part_2_PT1000","par_name":"PT1000","zhu_part_id_up":"215","cl":[]},{"zhu_part_id":"227","par_id":"zhu_part_2_99","par_name":"其他","zhu_part_id_up":"215","cl":[]}]}]},{"par_name":"油泵","zhu_part_config":"3","zhu_part_id":"3","cl":[{"zhu_part_id":"228","vender_id":"9","par_name":"托马斯","zhu_part_id_up":"3","cl":[{"zhu_part_id":"286","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"228","cl":[{"zhu_part_id":"294","par_id":"zhu_part_3_32","par_name":"32","zhu_part_id_up":"286"},{"zhu_part_id":"295","par_id":"zhu_part_3_28","par_name":"28","zhu_part_id_up":"286"},{"zhu_part_id":"296","par_id":"zhu_part_3_22","par_name":"22","zhu_part_id_up":"286"},{"zhu_part_id":"297","par_id":"zhu_part_3_16","par_name":"16","zhu_part_id_up":"286"}]},{"zhu_part_id":"287","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"228","cl":[{"zhu_part_id":"298","par_id":"zhu_part_3_32","par_name":"32","zhu_part_id_up":"287"},{"zhu_part_id":"299","par_id":"zhu_part_3_28","par_name":"28","zhu_part_id_up":"287"},{"zhu_part_id":"300","par_id":"zhu_part_3_22","par_name":"22","zhu_part_id_up":"287"},{"zhu_part_id":"301","par_id":"zhu_part_3_16","par_name":"16","zhu_part_id_up":"287"}]}]},{"zhu_part_id":"229","vender_id":"10","par_name":"刘云","zhu_part_id_up":"3","cl":[{"zhu_part_id":"288","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"229","cl":[{"zhu_part_id":"302","par_id":"zhu_part_3_32","par_name":"32","zhu_part_id_up":"288"},{"zhu_part_id":"303","par_id":"zhu_part_3_28","par_name":"28","zhu_part_id_up":"288"},{"zhu_part_id":"304","par_id":"zhu_part_3_22","par_name":"22","zhu_part_id_up":"288"},{"zhu_part_id":"305","par_id":"zhu_part_3_16","par_name":"16","zhu_part_id_up":"288"}]},{"zhu_part_id":"289","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"229","cl":[{"zhu_part_id":"306","par_id":"zhu_part_3_32","par_name":"32","zhu_part_id_up":"289"},{"zhu_part_id":"307","par_id":"zhu_part_3_28","par_name":"28","zhu_part_id_up":"289"},{"zhu_part_id":"308","par_id":"zhu_part_3_22","par_name":"22","zhu_part_id_up":"289"},{"zhu_part_id":"309","par_id":"zhu_part_3_16","par_name":"16","zhu_part_id_up":"289"}]}]},{"zhu_part_id":"230","vender_id":"11","par_name":"那忠纫","zhu_part_id_up":"3","cl":[{"zhu_part_id":"290","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"230","cl":[{"zhu_part_id":"310","par_id":"zhu_part_3_32","par_name":"32","zhu_part_id_up":"290"},{"zhu_part_id":"311","par_id":"zhu_part_3_28","par_name":"28","zhu_part_id_up":"290"},{"zhu_part_id":"312","par_id":"zhu_part_3_22","par_name":"22","zhu_part_id_up":"290"},{"zhu_part_id":"313","par_id":"zhu_part_3_16","par_name":"16","zhu_part_id_up":"290"}]},{"zhu_part_id":"291","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"230","cl":[{"zhu_part_id":"314","par_id":"zhu_part_3_32","par_name":"32","zhu_part_id_up":"291"},{"zhu_part_id":"315","par_id":"zhu_part_3_28","par_name":"28","zhu_part_id_up":"291"},{"zhu_part_id":"316","par_id":"zhu_part_3_22","par_name":"22","zhu_part_id_up":"291"},{"zhu_part_id":"317","par_id":"zhu_part_3_16","par_name":"16","zhu_part_id_up":"291"}]}]},{"zhu_part_id":"231","vender_id":"5","par_name":"其他","zhu_part_id_up":"3","cl":[{"zhu_part_id":"292","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"231","cl":[{"zhu_part_id":"318","par_id":"zhu_part_3_32","par_name":"32","zhu_part_id_up":"292"},{"zhu_part_id":"319","par_id":"zhu_part_3_28","par_name":"28","zhu_part_id_up":"292"},{"zhu_part_id":"320","par_id":"zhu_part_3_22","par_name":"22","zhu_part_id_up":"292"},{"zhu_part_id":"321","par_id":"zhu_part_3_16","par_name":"16","zhu_part_id_up":"292"}]},{"zhu_part_id":"293","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"231","cl":[{"zhu_part_id":"322","par_id":"zhu_part_3_32","par_name":"32","zhu_part_id_up":"293"},{"zhu_part_id":"323","par_id":"zhu_part_3_28","par_name":"28","zhu_part_id_up":"293"},{"zhu_part_id":"324","par_id":"zhu_part_3_22","par_name":"22","zhu_part_id_up":"293"},{"zhu_part_id":"325","par_id":"zhu_part_3_16","par_name":"16","zhu_part_id_up":"293"}]}]}]},{"par_name":"风机","zhu_part_config":"4","zhu_part_id":"4","cl":[{"zhu_part_id":"232","vender_id":"12","par_name":"杨启涛","zhu_part_id_up":"4","cl":[{"zhu_part_id":"326","par_id":"zhu_magnet_N","par_name":"N极","zhu_part_id_up":"232","cl":[{"zhu_part_id":"342","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"326"},{"zhu_part_id":"343","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"326"}]},{"zhu_part_id":"327","par_id":"zhu_magnet_S","par_name":"S极","zhu_part_id_up":"232","cl":[{"zhu_part_id":"344","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"327"},{"zhu_part_id":"345","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"327"}]},{"zhu_part_id":"328","par_id":"zhu_magnet_NN","par_name":"NN","zhu_part_id_up":"232","cl":[{"zhu_part_id":"346","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"328"},{"zhu_part_id":"347","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"328"}]},{"zhu_part_id":"329","par_id":"zhu_magnet_99","par_name":"其他","zhu_part_id_up":"232","cl":[{"zhu_part_id":"348","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"329"},{"zhu_part_id":"349","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"329"}]}]},{"zhu_part_id":"233","vender_id":"13","par_name":"王兴土","zhu_part_id_up":"4","cl":[{"zhu_part_id":"330","par_id":"zhu_magnet_N","par_name":"N极","zhu_part_id_up":"233","cl":[{"zhu_part_id":"350","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"330"},{"zhu_part_id":"351","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"330"}]},{"zhu_part_id":"331","par_id":"zhu_magnet_S","par_name":"S极","zhu_part_id_up":"233","cl":[{"zhu_part_id":"352","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"331"},{"zhu_part_id":"353","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"331"}]},{"zhu_part_id":"332","par_id":"zhu_magnet_NN","par_name":"NN","zhu_part_id_up":"233","cl":[{"zhu_part_id":"354","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"332"},{"zhu_part_id":"355","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"332"}]},{"zhu_part_id":"333","par_id":"zhu_magnet_99","par_name":"其他","zhu_part_id_up":"233","cl":[{"zhu_part_id":"356","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"333"},{"zhu_part_id":"357","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"333"}]}]},{"zhu_part_id":"234","vender_id":"14","par_name":"宗孔","zhu_part_id_up":"4","cl":[{"zhu_part_id":"334","par_id":"zhu_magnet_N","par_name":"N极","zhu_part_id_up":"234","cl":[{"zhu_part_id":"358","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"334"},{"zhu_part_id":"359","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"334"}]},{"zhu_part_id":"335","par_id":"zhu_magnet_S","par_name":"S极","zhu_part_id_up":"234","cl":[{"zhu_part_id":"360","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"335"},{"zhu_part_id":"361","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"335"}]},{"zhu_part_id":"336","par_id":"zhu_magnet_NN","par_name":"NN","zhu_part_id_up":"234","cl":[{"zhu_part_id":"362","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"336"},{"zhu_part_id":"363","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"336"}]},{"zhu_part_id":"337","par_id":"zhu_magnet_99","par_name":"其他","zhu_part_id_up":"234","cl":[{"zhu_part_id":"364","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"337"},{"zhu_part_id":"365","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"337"}]}]},{"zhu_part_id":"235","vender_id":"5","par_name":"其他","zhu_part_id_up":"4","cl":[{"zhu_part_id":"338","par_id":"zhu_magnet_N","par_name":"N极","zhu_part_id_up":"235","cl":[{"zhu_part_id":"366","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"338"},{"zhu_part_id":"367","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"338"}]},{"zhu_part_id":"339","par_id":"zhu_magnet_S","par_name":"S极","zhu_part_id_up":"235","cl":[{"zhu_part_id":"368","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"339"},{"zhu_part_id":"369","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"339"}]},{"zhu_part_id":"340","par_id":"zhu_magnet_NN","par_name":"NN","zhu_part_id_up":"235","cl":[{"zhu_part_id":"370","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"340"},{"zhu_part_id":"371","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"340"}]},{"zhu_part_id":"341","par_id":"zhu_magnet_99","par_name":"其他","zhu_part_id_up":"235","cl":[{"zhu_part_id":"372","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"341"},{"zhu_part_id":"373","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"341"}]}]}]},{"par_name":"燃烧室","zhu_part_config":"5","zhu_part_id":"5","cl":[{"zhu_part_id":"236","vender_id":"15","par_name":"辛凤茂","zhu_part_id_up":"5","cl":[{"zhu_part_id":"283","par_id":"zhu_part_5_99","par_name":"暂无","zhu_part_id_up":"236","cl":[]}]},{"zhu_part_id":"237","vender_id":"16","par_name":"孙浩然","zhu_part_id_up":"5","cl":[{"zhu_part_id":"284","par_id":"zhu_part_5_99","par_name":"暂无","zhu_part_id_up":"237","cl":[]}]},{"zhu_part_id":"238","vender_id":"5","par_name":"其他","zhu_part_id_up":"5","cl":[{"zhu_part_id":"285","par_id":"zhu_part_5_99","par_name":"暂无","zhu_part_id_up":"238","cl":[]}]}]},{"par_name":"控制板","zhu_part_config":"6","zhu_part_id":"6","cl":[{"zhu_part_id":"239","vender_id":"5","par_name":"其他","zhu_part_id_up":"6","cl":[{"zhu_part_id":"240","par_id":"zhu_part_6_lanya","par_name":"蓝牙板","zhu_part_id_up":"239","cl":[]},{"zhu_part_id":"241","par_id":"zhu_part_6_GPRS","par_name":"GPRS","zhu_part_id_up":"239","cl":[]},{"zhu_part_id":"242","par_id":"zhu_part_6_GPS","par_name":"GPS","zhu_part_id_up":"239","cl":[]}]}]},{"par_name":"控制开关","zhu_part_config":"7","zhu_part_id":"7","cl":[{"zhu_part_id":"243","vender_id":"5","par_name":"其他","zhu_part_id_up":"7","cl":[{"zhu_part_id":"244","par_id":"zhu_part_7_5","par_name":"旋转数码开关","zhu_part_id_up":"243","cl":[]},{"zhu_part_id":"245","par_id":"zhu_part_7_4","par_name":"五键液晶开关","zhu_part_id_up":"243","cl":[]},{"zhu_part_id":"246","par_id":"zhu_part_7_3","par_name":"横版数码开关","zhu_part_id_up":"243","cl":[]},{"zhu_part_id":"247","par_id":"zhu_part_7_2","par_name":"横版液晶开关","zhu_part_id_up":"243","cl":[]},{"zhu_part_id":"248","par_id":"zhu_part_7_1","par_name":"竖版语音数码开关","zhu_part_id_up":"243","cl":[]}]}]},{"par_name":"散热体","zhu_part_config":"8","zhu_part_id":"8","cl":[{"zhu_part_id":"249","vender_id":"17","par_name":"马东浩","zhu_part_id_up":"8","cl":[{"zhu_part_id":"252","par_id":"zhu_part_8_2","par_name":"2kw","zhu_part_id_up":"249","cl":[]},{"zhu_part_id":"253","par_id":"zhu_part_8_5","par_name":"5kw","zhu_part_id_up":"249","cl":[]},{"zhu_part_id":"374","par_id":"zhu_part_8_3","par_name":"3kw","zhu_part_id_up":"249","cl":[]}]},{"zhu_part_id":"250","vender_id":"18","par_name":"侯书超","zhu_part_id_up":"8","cl":[{"zhu_part_id":"254","par_id":"zhu_part_8_2","par_name":"2kw","zhu_part_id_up":"250","cl":[]},{"zhu_part_id":"255","par_id":"zhu_part_8_5","par_name":"5kw","zhu_part_id_up":"250","cl":[]},{"zhu_part_id":"375","par_id":"zhu_part_8_3","par_name":"3kw","zhu_part_id_up":"250","cl":[]}]},{"zhu_part_id":"251","vender_id":"5","par_name":"其他","zhu_part_id_up":"8","cl":[{"zhu_part_id":"256","par_id":"zhu_part_8_2","par_name":"2kw","zhu_part_id_up":"251","cl":[]},{"zhu_part_id":"257","par_id":"zhu_part_8_5","par_name":"5kw","zhu_part_id_up":"251","cl":[]},{"zhu_part_id":"376","par_id":"zhu_part_8_3","par_name":"3kw","zhu_part_id_up":"251","cl":[]}]}]},{"par_name":"线束","zhu_part_config":"9","zhu_part_id":"9","cl":[{"zhu_part_id":"258","vender_id":"7","par_name":"王怀素","zhu_part_id_up":"9","cl":[{"zhu_part_id":"263","par_id":"zhu_part_9_3","par_name":"标准","zhu_part_id_up":"258","cl":[]},{"zhu_part_id":"264","par_id":"zhu_part_9_2","par_name":"短","zhu_part_id_up":"258","cl":[]},{"zhu_part_id":"265","par_id":"zhu_part_9_1","par_name":"长","zhu_part_id_up":"258","cl":[]}]},{"zhu_part_id":"259","vender_id":"19","par_name":"陆志宽","zhu_part_id_up":"9","cl":[{"zhu_part_id":"266","par_id":"zhu_part_9_3","par_name":"标准","zhu_part_id_up":"259","cl":[]},{"zhu_part_id":"267","par_id":"zhu_part_9_2","par_name":"短","zhu_part_id_up":"259","cl":[]},{"zhu_part_id":"268","par_id":"zhu_part_9_1","par_name":"长","zhu_part_id_up":"259","cl":[]}]},{"zhu_part_id":"260","vender_id":"20","par_name":"王印清","zhu_part_id_up":"9","cl":[{"zhu_part_id":"269","par_id":"zhu_part_9_3","par_name":"标准","zhu_part_id_up":"260","cl":[]},{"zhu_part_id":"270","par_id":"zhu_part_9_2","par_name":"短","zhu_part_id_up":"260","cl":[]},{"zhu_part_id":"271","par_id":"zhu_part_9_1","par_name":"长","zhu_part_id_up":"260","cl":[]}]},{"zhu_part_id":"261","vender_id":"21","par_name":"高恒洁","zhu_part_id_up":"9","cl":[{"zhu_part_id":"272","par_id":"zhu_part_9_3","par_name":"标准","zhu_part_id_up":"261","cl":[]},{"zhu_part_id":"273","par_id":"zhu_part_9_2","par_name":"短","zhu_part_id_up":"261","cl":[]},{"zhu_part_id":"274","par_id":"zhu_part_9_1","par_name":"长","zhu_part_id_up":"261","cl":[]}]},{"zhu_part_id":"262","vender_id":"5","par_name":"其他","zhu_part_id_up":"9","cl":[{"zhu_part_id":"275","par_id":"zhu_part_9_3","par_name":"标准","zhu_part_id_up":"262","cl":[]},{"zhu_part_id":"276","par_id":"zhu_part_9_2","par_name":"短","zhu_part_id_up":"262","cl":[]},{"zhu_part_id":"277","par_id":"zhu_part_9_1","par_name":"长","zhu_part_id_up":"262","cl":[]}]}]},{"par_name":"塑料外壳(铝)","zhu_part_config":"10","zhu_part_id":"10","cl":[{"zhu_part_id":"278","vender_id":"22","par_name":"武曾喜","zhu_part_id_up":"10","cl":[]},{"zhu_part_id":"279","vender_id":"23","par_name":"蒋佩波","zhu_part_id_up":"10","cl":[]},{"zhu_part_id":"280","vender_id":"5","par_name":"其他","zhu_part_id_up":"10","cl":[]}]},{"par_name":"橡胶件","zhu_part_config":"11","zhu_part_id":"11","cl":[{"zhu_part_id":"281","vender_id":"24","par_name":"田强来","zhu_part_id_up":"11","cl":[]},{"zhu_part_id":"282","vender_id":"5","par_name":"其他","zhu_part_id_up":"11","cl":[]}]}]
     */

    private String msg_code;
    private String msg;
    private List<DataBean> data;

    public String getMsg_code() {
        return msg_code;
    }

    public void setMsg_code(String msg_code) {
        this.msg_code = msg_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * par_name : 油泵
         * zhu_part_config : 3
         * zhu_part_id : 3
         * cl : [{"zhu_part_id":"228","vender_id":"9","par_name":"托马斯","zhu_part_id_up":"3","cl":[{"zhu_part_id":"286","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"228","cl":[{"zhu_part_id":"294","par_id":"zhu_part_3_32","par_name":"32","zhu_part_id_up":"286"},{"zhu_part_id":"295","par_id":"zhu_part_3_28","par_name":"28","zhu_part_id_up":"286"},{"zhu_part_id":"296","par_id":"zhu_part_3_22","par_name":"22","zhu_part_id_up":"286"},{"zhu_part_id":"297","par_id":"zhu_part_3_16","par_name":"16","zhu_part_id_up":"286"}]},{"zhu_part_id":"287","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"228","cl":[{"zhu_part_id":"298","par_id":"zhu_part_3_32","par_name":"32","zhu_part_id_up":"287"},{"zhu_part_id":"299","par_id":"zhu_part_3_28","par_name":"28","zhu_part_id_up":"287"},{"zhu_part_id":"300","par_id":"zhu_part_3_22","par_name":"22","zhu_part_id_up":"287"},{"zhu_part_id":"301","par_id":"zhu_part_3_16","par_name":"16","zhu_part_id_up":"287"}]}]},{"zhu_part_id":"229","vender_id":"10","par_name":"刘云","zhu_part_id_up":"3","cl":[{"zhu_part_id":"288","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"229","cl":[{"zhu_part_id":"302","par_id":"zhu_part_3_32","par_name":"32","zhu_part_id_up":"288"},{"zhu_part_id":"303","par_id":"zhu_part_3_28","par_name":"28","zhu_part_id_up":"288"},{"zhu_part_id":"304","par_id":"zhu_part_3_22","par_name":"22","zhu_part_id_up":"288"},{"zhu_part_id":"305","par_id":"zhu_part_3_16","par_name":"16","zhu_part_id_up":"288"}]},{"zhu_part_id":"289","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"229","cl":[{"zhu_part_id":"306","par_id":"zhu_part_3_32","par_name":"32","zhu_part_id_up":"289"},{"zhu_part_id":"307","par_id":"zhu_part_3_28","par_name":"28","zhu_part_id_up":"289"},{"zhu_part_id":"308","par_id":"zhu_part_3_22","par_name":"22","zhu_part_id_up":"289"},{"zhu_part_id":"309","par_id":"zhu_part_3_16","par_name":"16","zhu_part_id_up":"289"}]}]},{"zhu_part_id":"230","vender_id":"11","par_name":"那忠纫","zhu_part_id_up":"3","cl":[{"zhu_part_id":"290","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"230","cl":[{"zhu_part_id":"310","par_id":"zhu_part_3_32","par_name":"32","zhu_part_id_up":"290"},{"zhu_part_id":"311","par_id":"zhu_part_3_28","par_name":"28","zhu_part_id_up":"290"},{"zhu_part_id":"312","par_id":"zhu_part_3_22","par_name":"22","zhu_part_id_up":"290"},{"zhu_part_id":"313","par_id":"zhu_part_3_16","par_name":"16","zhu_part_id_up":"290"}]},{"zhu_part_id":"291","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"230","cl":[{"zhu_part_id":"314","par_id":"zhu_part_3_32","par_name":"32","zhu_part_id_up":"291"},{"zhu_part_id":"315","par_id":"zhu_part_3_28","par_name":"28","zhu_part_id_up":"291"},{"zhu_part_id":"316","par_id":"zhu_part_3_22","par_name":"22","zhu_part_id_up":"291"},{"zhu_part_id":"317","par_id":"zhu_part_3_16","par_name":"16","zhu_part_id_up":"291"}]}]},{"zhu_part_id":"231","vender_id":"5","par_name":"其他","zhu_part_id_up":"3","cl":[{"zhu_part_id":"292","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"231","cl":[{"zhu_part_id":"318","par_id":"zhu_part_3_32","par_name":"32","zhu_part_id_up":"292"},{"zhu_part_id":"319","par_id":"zhu_part_3_28","par_name":"28","zhu_part_id_up":"292"},{"zhu_part_id":"320","par_id":"zhu_part_3_22","par_name":"22","zhu_part_id_up":"292"},{"zhu_part_id":"321","par_id":"zhu_part_3_16","par_name":"16","zhu_part_id_up":"292"}]},{"zhu_part_id":"293","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"231","cl":[{"zhu_part_id":"322","par_id":"zhu_part_3_32","par_name":"32","zhu_part_id_up":"293"},{"zhu_part_id":"323","par_id":"zhu_part_3_28","par_name":"28","zhu_part_id_up":"293"},{"zhu_part_id":"324","par_id":"zhu_part_3_22","par_name":"22","zhu_part_id_up":"293"},{"zhu_part_id":"325","par_id":"zhu_part_3_16","par_name":"16","zhu_part_id_up":"293"}]}]}]
         */

        private String par_name;
        private String zhu_part_config;
        private String zhu_part_id;
        private List<ClBeanXX> cl;

        public String getPar_name() {
            return par_name;
        }

        public void setPar_name(String par_name) {
            this.par_name = par_name;
        }

        public String getZhu_part_config() {
            return zhu_part_config;
        }

        public void setZhu_part_config(String zhu_part_config) {
            this.zhu_part_config = zhu_part_config;
        }

        public String getZhu_part_id() {
            return zhu_part_id;
        }

        public void setZhu_part_id(String zhu_part_id) {
            this.zhu_part_id = zhu_part_id;
        }

        public List<ClBeanXX> getCl() {
            return cl;
        }

        public void setCl(List<ClBeanXX> cl) {
            this.cl = cl;
        }

        public static class ClBeanXX {
            /**
             * zhu_part_id : 228
             * vender_id : 9
             * par_name : 托马斯
             * zhu_part_id_up : 3
             * cl : [{"zhu_part_id":"286","par_id":"zhu_voltage_24","par_name":"24V","zhu_part_id_up":"228","cl":[{"zhu_part_id":"294","par_id":"zhu_part_3_32","par_name":"32","zhu_part_id_up":"286"},{"zhu_part_id":"295","par_id":"zhu_part_3_28","par_name":"28","zhu_part_id_up":"286"},{"zhu_part_id":"296","par_id":"zhu_part_3_22","par_name":"22","zhu_part_id_up":"286"},{"zhu_part_id":"297","par_id":"zhu_part_3_16","par_name":"16","zhu_part_id_up":"286"}]},{"zhu_part_id":"287","par_id":"zhu_voltage_12","par_name":"12V","zhu_part_id_up":"228","cl":[{"zhu_part_id":"298","par_id":"zhu_part_3_32","par_name":"32","zhu_part_id_up":"287"},{"zhu_part_id":"299","par_id":"zhu_part_3_28","par_name":"28","zhu_part_id_up":"287"},{"zhu_part_id":"300","par_id":"zhu_part_3_22","par_name":"22","zhu_part_id_up":"287"},{"zhu_part_id":"301","par_id":"zhu_part_3_16","par_name":"16","zhu_part_id_up":"287"}]}]
             */

            private String zhu_part_id;
            private String vender_id;
            private String par_name;
            private String zhu_part_id_up;
            private List<ClBeanX> cl;

            public String getZhu_part_id() {
                return zhu_part_id;
            }

            public void setZhu_part_id(String zhu_part_id) {
                this.zhu_part_id = zhu_part_id;
            }

            public String getVender_id() {
                return vender_id;
            }

            public void setVender_id(String vender_id) {
                this.vender_id = vender_id;
            }

            public String getPar_name() {
                return par_name;
            }

            public void setPar_name(String par_name) {
                this.par_name = par_name;
            }

            public String getZhu_part_id_up() {
                return zhu_part_id_up;
            }

            public void setZhu_part_id_up(String zhu_part_id_up) {
                this.zhu_part_id_up = zhu_part_id_up;
            }

            public List<ClBeanX> getCl() {
                return cl;
            }

            public void setCl(List<ClBeanX> cl) {
                this.cl = cl;
            }

            public static class ClBeanX {
                /**
                 * zhu_part_id : 286
                 * par_id : zhu_voltage_24
                 * par_name : 24V
                 * zhu_part_id_up : 228
                 * cl : [{"zhu_part_id":"294","par_id":"zhu_part_3_32","par_name":"32","zhu_part_id_up":"286"},{"zhu_part_id":"295","par_id":"zhu_part_3_28","par_name":"28","zhu_part_id_up":"286"},{"zhu_part_id":"296","par_id":"zhu_part_3_22","par_name":"22","zhu_part_id_up":"286"},{"zhu_part_id":"297","par_id":"zhu_part_3_16","par_name":"16","zhu_part_id_up":"286"}]
                 */

                private String zhu_part_id;
                private String par_id;
                private String par_name;
                private String zhu_part_id_up;
                private List<ClBean> cl;

                public String getZhu_part_id() {
                    return zhu_part_id;
                }

                public void setZhu_part_id(String zhu_part_id) {
                    this.zhu_part_id = zhu_part_id;
                }

                public String getPar_id() {
                    return par_id;
                }

                public void setPar_id(String par_id) {
                    this.par_id = par_id;
                }

                public String getPar_name() {
                    return par_name;
                }

                public void setPar_name(String par_name) {
                    this.par_name = par_name;
                }

                public String getZhu_part_id_up() {
                    return zhu_part_id_up;
                }

                public void setZhu_part_id_up(String zhu_part_id_up) {
                    this.zhu_part_id_up = zhu_part_id_up;
                }

                public List<ClBean> getCl() {
                    return cl;
                }

                public void setCl(List<ClBean> cl) {
                    this.cl = cl;
                }

                public static class ClBean {
                    /**
                     * zhu_part_id : 294
                     * par_id : zhu_part_3_32
                     * par_name : 32
                     * zhu_part_id_up : 286
                     */

                    private String zhu_part_id;
                    private String par_id;
                    private String par_name;
                    private String zhu_part_id_up;

                    public String getZhu_part_id() {
                        return zhu_part_id;
                    }

                    public void setZhu_part_id(String zhu_part_id) {
                        this.zhu_part_id = zhu_part_id;
                    }

                    public String getPar_id() {
                        return par_id;
                    }

                    public void setPar_id(String par_id) {
                        this.par_id = par_id;
                    }

                    public String getPar_name() {
                        return par_name;
                    }

                    public void setPar_name(String par_name) {
                        this.par_name = par_name;
                    }

                    public String getZhu_part_id_up() {
                        return zhu_part_id_up;
                    }

                    public void setZhu_part_id_up(String zhu_part_id_up) {
                        this.zhu_part_id_up = zhu_part_id_up;
                    }
                }
            }
        }
    }
}
