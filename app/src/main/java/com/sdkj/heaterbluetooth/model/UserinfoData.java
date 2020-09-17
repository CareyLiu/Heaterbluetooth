package com.sdkj.heaterbluetooth.model;

import java.util.List;

/**
 * Created by Android on 2018/5/31.
 */

public class UserinfoData {

    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"revolution":"4520","control_switch_name_one":"","draught_fan_name_four":"","firebox_id_three":"","radiator_name_three":"","country_name":"中国","vulcanizate_id_one":"","oil_pump_id_one":"","fuel_type_id":"2","out_temperature":"12","firebox_name_three":"","ignition_name_two":"","firebox_id_two":"","user_car_img_id":"4354","draught_fan_id_one":"","user_x":"","user_y":"","draught_fan_all_name":"","zhu_car_stoppage_code_10":"","oil_pump_id_four":"","zhu_car_stoppage_code_11":"","province_id":"23","wiring_harness_all_name":"","zhu_car_stoppage_code_18":"","vulcanizate_all_name":"","wiring_harness_name_three":"","user_name":"柚子","province_name":"黑龙江省","voltage":"","radiator_all_name":"","user_phone":"18345033821","control_switch_name_two":"","draught_fan_id_three":"","draught_fan_name_two":"","outermost_shell_id_one":"","city_name":"哈尔滨市","outlet_sensor_name_three":"","radiator_name_one":"","oil_pump_name_one":"","fuel_type_name":"柴油","firebox_name_two":"","control_switch_all_name":"","outermost_shell_name_one":"","outermost_shell_id_two":"","car_t_name":"客车","wiring_harness_id_two":"","oil_pump_all_name":"","car_type_name":"大型车","zhu_car_stoppage_code_9":"","firebox_name_one":"","control_switch_id_two":"","zhu_car_stoppage_code_8":"","zhu_car_stoppage_code_7":"","draught_fan_id_four":"","country_id":"0","oil_pump_name_four":"","zhu_user_car_id":"11","oil_pump_name_three":"","gear_parameters_id":"","zhu_car_stoppage_code_1":"","zhu_car_stoppage_code_2":"","vulcanizate_name_two":"","car_type_id":"1","dash_board_id_one":"","zhu_car_stoppage_code_5":"","zhu_car_stoppage_code_6":"","zhu_car_stoppage_code_3":"","zhu_car_stoppage_code_4":"","dash_board_id_two":"","zhu_car_stoppage_code_default":"","start_state":"1","radiator_name_two":"","wiring_harness_id_three":"","ignition_name_three":"","gear":"","login_time":"1530690396864","power":"75","vulcanizate_id_two":"","outlet_sensor_name_two":"","ignition_id_two":"","ignition_id_one":"","outlet_sensor_id_two":"","outermost_shell_name_two":"","machine_voltage":"","oil_pump_id_two":"","in_temperature":"11","outermost_shell_all_name":"","outlet_sensor_name_one":"","vulcanizate_name_one":"","dash_board_name_one":"","update_time":"","car_t_id":"2","wiring_harness_name_two":"","create_time":"2018-07-04","radiator_id_two":"","current_temperature":"36","control_switch_id_one":"","insurance":"58358358358357","of_user_id":"422","area_id":"230110","wiring_harness_name_one":"","outlet_sensor_id_three":"","ignition_id_three":"","install_time":"2018-07-04","frequency":"","plate_number":"京A00000","user_car_img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=4354","city_id":"2301","dash_board_all_name":"","radiator_id_three":"","area_name":"香坊区","draught_fan_name_three":"","wiring_harness_id_one":"","ignition_all_name":"","oil_pump_id_three":"","firebox_id_one":"","outlet_sensor_id_one":"","firebox_all_name":"","sell_phone":"15585868686","ignition_name_one":"","user_car_state":"1","oil_pump_name_two":"","draught_fan_id_two":"","dash_board_name_two":"","draught_fan_name_one":"","radiator_id_one":"","outlet_sensor_all_name":""}]
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
         * revolution : 4520
         * control_switch_name_one :
         * draught_fan_name_four :
         * firebox_id_three :
         * radiator_name_three :
         * country_name : 中国
         * vulcanizate_id_one :
         * oil_pump_id_one :
         * fuel_type_id : 2
         * out_temperature : 12
         * firebox_name_three :
         * ignition_name_two :
         * firebox_id_two :
         * user_car_img_id : 4354
         * draught_fan_id_one :
         * user_x :
         * user_y :
         * draught_fan_all_name :
         * zhu_car_stoppage_code_10 :
         * oil_pump_id_four :
         * zhu_car_stoppage_code_11 :
         * province_id : 23
         * wiring_harness_all_name :
         * zhu_car_stoppage_code_18 :
         * vulcanizate_all_name :
         * wiring_harness_name_three :
         * user_name : 柚子
         * province_name : 黑龙江省
         * voltage :
         * radiator_all_name :
         * user_phone : 18345033821
         * control_switch_name_two :
         * draught_fan_id_three :
         * draught_fan_name_two :
         * outermost_shell_id_one :
         * city_name : 哈尔滨市
         * outlet_sensor_name_three :
         * radiator_name_one :
         * oil_pump_name_one :
         * fuel_type_name : 柴油
         * firebox_name_two :
         * control_switch_all_name :
         * outermost_shell_name_one :
         * outermost_shell_id_two :
         * car_t_name : 客车
         * wiring_harness_id_two :
         * oil_pump_all_name :
         * car_type_name : 大型车
         * zhu_car_stoppage_code_9 :
         * firebox_name_one :
         * control_switch_id_two :
         * zhu_car_stoppage_code_8 :
         * zhu_car_stoppage_code_7 :
         * draught_fan_id_four :
         * country_id : 0
         * oil_pump_name_four :
         * zhu_user_car_id : 11
         * oil_pump_name_three :
         * gear_parameters_id :
         * zhu_car_stoppage_code_1 :
         * zhu_car_stoppage_code_2 :
         * vulcanizate_name_two :
         * car_type_id : 1
         * dash_board_id_one :
         * zhu_car_stoppage_code_5 :
         * zhu_car_stoppage_code_6 :
         * zhu_car_stoppage_code_3 :
         * zhu_car_stoppage_code_4 :
         * dash_board_id_two :
         * zhu_car_stoppage_code_default :
         * start_state : 1
         * radiator_name_two :
         * wiring_harness_id_three :
         * ignition_name_three :
         * gear :
         * login_time : 1530690396864
         * power : 75
         * vulcanizate_id_two :
         * outlet_sensor_name_two :
         * ignition_id_two :
         * ignition_id_one :
         * outlet_sensor_id_two :
         * outermost_shell_name_two :
         * machine_voltage :
         * oil_pump_id_two :
         * in_temperature : 11
         * outermost_shell_all_name :
         * outlet_sensor_name_one :
         * vulcanizate_name_one :
         * dash_board_name_one :
         * update_time :
         * car_t_id : 2
         * wiring_harness_name_two :
         * create_time : 2018-07-04
         * radiator_id_two :
         * current_temperature : 36
         * control_switch_id_one :
         * insurance : 58358358358357
         * of_user_id : 422
         * area_id : 230110
         * wiring_harness_name_one :
         * outlet_sensor_id_three :
         * ignition_id_three :
         * install_time : 2018-07-04
         * frequency :
         * plate_number : 京A00000
         * user_car_img_url : https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=4354
         * city_id : 2301
         * dash_board_all_name :
         * radiator_id_three :
         * area_name : 香坊区
         * draught_fan_name_three :
         * wiring_harness_id_one :
         * ignition_all_name :
         * oil_pump_id_three :
         * firebox_id_one :
         * outlet_sensor_id_one :
         * firebox_all_name :
         * sell_phone : 15585868686
         * ignition_name_one :
         * user_car_state : 1
         * oil_pump_name_two :
         * draught_fan_id_two :
         * dash_board_name_two :
         * draught_fan_name_one :
         * radiator_id_one :
         * outlet_sensor_all_name :
         */

        private String revolution;
        private String control_switch_name_one;
        private String draught_fan_name_four;
        private String firebox_id_three;
        private String radiator_name_three;
        private String country_name;
        private String vulcanizate_id_one;
        private String oil_pump_id_one;
        private String fuel_type_id;
        private String out_temperature;
        private String firebox_name_three;
        private String ignition_name_two;
        private String firebox_id_two;
        private String user_car_img_id;
        private String draught_fan_id_one;
        private String user_x;
        private String user_y;
        private String draught_fan_all_name;
        private String zhu_car_stoppage_code_10;
        private String oil_pump_id_four;
        private String zhu_car_stoppage_code_11;
        private String province_id;
        private String wiring_harness_all_name;
        private String zhu_car_stoppage_code_18;
        private String vulcanizate_all_name;
        private String wiring_harness_name_three;
        private String user_name;
        private String province_name;
        private String voltage;
        private String radiator_all_name;
        private String user_phone;
        private String control_switch_name_two;
        private String draught_fan_id_three;
        private String draught_fan_name_two;
        private String outermost_shell_id_one;
        private String city_name;
        private String outlet_sensor_name_three;
        private String radiator_name_one;
        private String oil_pump_name_one;
        private String fuel_type_name;
        private String firebox_name_two;
        private String control_switch_all_name;
        private String outermost_shell_name_one;
        private String outermost_shell_id_two;
        private String car_t_name;
        private String wiring_harness_id_two;
        private String oil_pump_all_name;
        private String car_type_name;
        private String zhu_car_stoppage_code_9;
        private String firebox_name_one;
        private String control_switch_id_two;
        private String zhu_car_stoppage_code_8;
        private String zhu_car_stoppage_code_7;
        private String draught_fan_id_four;
        private String country_id;
        private String oil_pump_name_four;
        private String zhu_user_car_id;
        private String oil_pump_name_three;
        private String gear_parameters_id;
        private String zhu_car_stoppage_code_1;
        private String zhu_car_stoppage_code_2;
        private String vulcanizate_name_two;
        private String car_type_id;
        private String dash_board_id_one;
        private String zhu_car_stoppage_code_5;
        private String zhu_car_stoppage_code_6;
        private String zhu_car_stoppage_code_3;
        private String zhu_car_stoppage_code_4;
        private String dash_board_id_two;
        private String zhu_car_stoppage_code_default;
        private String start_state;
        private String radiator_name_two;
        private String wiring_harness_id_three;
        private String ignition_name_three;
        private String gear;
        private String login_time;
        private String power;
        private String vulcanizate_id_two;
        private String outlet_sensor_name_two;
        private String ignition_id_two;
        private String ignition_id_one;
        private String outlet_sensor_id_two;
        private String outermost_shell_name_two;
        private String machine_voltage;
        private String oil_pump_id_two;
        private String in_temperature;
        private String outermost_shell_all_name;
        private String outlet_sensor_name_one;
        private String vulcanizate_name_one;
        private String dash_board_name_one;
        private String update_time;
        private String car_t_id;
        private String wiring_harness_name_two;
        private String create_time;
        private String radiator_id_two;
        private String current_temperature;
        private String control_switch_id_one;
        private String insurance;
        private String of_user_id;
        private String area_id;
        private String wiring_harness_name_one;
        private String outlet_sensor_id_three;
        private String ignition_id_three;
        private String install_time;
        private String frequency;
        private String plate_number;
        private String user_car_img_url;
        private String city_id;
        private String dash_board_all_name;
        private String radiator_id_three;
        private String area_name;
        private String draught_fan_name_three;
        private String wiring_harness_id_one;
        private String ignition_all_name;
        private String oil_pump_id_three;
        private String firebox_id_one;
        private String outlet_sensor_id_one;
        private String firebox_all_name;
        private String sell_phone;
        private String ignition_name_one;
        private String user_car_state;
        private String oil_pump_name_two;
        private String draught_fan_id_two;
        private String dash_board_name_two;
        private String draught_fan_name_one;
        private String radiator_id_one;
        private String outlet_sensor_all_name;

        public String getRevolution() {
            return revolution;
        }

        public void setRevolution(String revolution) {
            this.revolution = revolution;
        }

        public String getControl_switch_name_one() {
            return control_switch_name_one;
        }

        public void setControl_switch_name_one(String control_switch_name_one) {
            this.control_switch_name_one = control_switch_name_one;
        }

        public String getDraught_fan_name_four() {
            return draught_fan_name_four;
        }

        public void setDraught_fan_name_four(String draught_fan_name_four) {
            this.draught_fan_name_four = draught_fan_name_four;
        }

        public String getFirebox_id_three() {
            return firebox_id_three;
        }

        public void setFirebox_id_three(String firebox_id_three) {
            this.firebox_id_three = firebox_id_three;
        }

        public String getRadiator_name_three() {
            return radiator_name_three;
        }

        public void setRadiator_name_three(String radiator_name_three) {
            this.radiator_name_three = radiator_name_three;
        }

        public String getCountry_name() {
            return country_name;
        }

        public void setCountry_name(String country_name) {
            this.country_name = country_name;
        }

        public String getVulcanizate_id_one() {
            return vulcanizate_id_one;
        }

        public void setVulcanizate_id_one(String vulcanizate_id_one) {
            this.vulcanizate_id_one = vulcanizate_id_one;
        }

        public String getOil_pump_id_one() {
            return oil_pump_id_one;
        }

        public void setOil_pump_id_one(String oil_pump_id_one) {
            this.oil_pump_id_one = oil_pump_id_one;
        }

        public String getFuel_type_id() {
            return fuel_type_id;
        }

        public void setFuel_type_id(String fuel_type_id) {
            this.fuel_type_id = fuel_type_id;
        }

        public String getOut_temperature() {
            return out_temperature;
        }

        public void setOut_temperature(String out_temperature) {
            this.out_temperature = out_temperature;
        }

        public String getFirebox_name_three() {
            return firebox_name_three;
        }

        public void setFirebox_name_three(String firebox_name_three) {
            this.firebox_name_three = firebox_name_three;
        }

        public String getIgnition_name_two() {
            return ignition_name_two;
        }

        public void setIgnition_name_two(String ignition_name_two) {
            this.ignition_name_two = ignition_name_two;
        }

        public String getFirebox_id_two() {
            return firebox_id_two;
        }

        public void setFirebox_id_two(String firebox_id_two) {
            this.firebox_id_two = firebox_id_two;
        }

        public String getUser_car_img_id() {
            return user_car_img_id;
        }

        public void setUser_car_img_id(String user_car_img_id) {
            this.user_car_img_id = user_car_img_id;
        }

        public String getDraught_fan_id_one() {
            return draught_fan_id_one;
        }

        public void setDraught_fan_id_one(String draught_fan_id_one) {
            this.draught_fan_id_one = draught_fan_id_one;
        }

        public String getUser_x() {
            return user_x;
        }

        public void setUser_x(String user_x) {
            this.user_x = user_x;
        }

        public String getUser_y() {
            return user_y;
        }

        public void setUser_y(String user_y) {
            this.user_y = user_y;
        }

        public String getDraught_fan_all_name() {
            return draught_fan_all_name;
        }

        public void setDraught_fan_all_name(String draught_fan_all_name) {
            this.draught_fan_all_name = draught_fan_all_name;
        }

        public String getZhu_car_stoppage_code_10() {
            return zhu_car_stoppage_code_10;
        }

        public void setZhu_car_stoppage_code_10(String zhu_car_stoppage_code_10) {
            this.zhu_car_stoppage_code_10 = zhu_car_stoppage_code_10;
        }

        public String getOil_pump_id_four() {
            return oil_pump_id_four;
        }

        public void setOil_pump_id_four(String oil_pump_id_four) {
            this.oil_pump_id_four = oil_pump_id_four;
        }

        public String getZhu_car_stoppage_code_11() {
            return zhu_car_stoppage_code_11;
        }

        public void setZhu_car_stoppage_code_11(String zhu_car_stoppage_code_11) {
            this.zhu_car_stoppage_code_11 = zhu_car_stoppage_code_11;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getWiring_harness_all_name() {
            return wiring_harness_all_name;
        }

        public void setWiring_harness_all_name(String wiring_harness_all_name) {
            this.wiring_harness_all_name = wiring_harness_all_name;
        }

        public String getZhu_car_stoppage_code_18() {
            return zhu_car_stoppage_code_18;
        }

        public void setZhu_car_stoppage_code_18(String zhu_car_stoppage_code_18) {
            this.zhu_car_stoppage_code_18 = zhu_car_stoppage_code_18;
        }

        public String getVulcanizate_all_name() {
            return vulcanizate_all_name;
        }

        public void setVulcanizate_all_name(String vulcanizate_all_name) {
            this.vulcanizate_all_name = vulcanizate_all_name;
        }

        public String getWiring_harness_name_three() {
            return wiring_harness_name_three;
        }

        public void setWiring_harness_name_three(String wiring_harness_name_three) {
            this.wiring_harness_name_three = wiring_harness_name_three;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getVoltage() {
            return voltage;
        }

        public void setVoltage(String voltage) {
            this.voltage = voltage;
        }

        public String getRadiator_all_name() {
            return radiator_all_name;
        }

        public void setRadiator_all_name(String radiator_all_name) {
            this.radiator_all_name = radiator_all_name;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getControl_switch_name_two() {
            return control_switch_name_two;
        }

        public void setControl_switch_name_two(String control_switch_name_two) {
            this.control_switch_name_two = control_switch_name_two;
        }

        public String getDraught_fan_id_three() {
            return draught_fan_id_three;
        }

        public void setDraught_fan_id_three(String draught_fan_id_three) {
            this.draught_fan_id_three = draught_fan_id_three;
        }

        public String getDraught_fan_name_two() {
            return draught_fan_name_two;
        }

        public void setDraught_fan_name_two(String draught_fan_name_two) {
            this.draught_fan_name_two = draught_fan_name_two;
        }

        public String getOutermost_shell_id_one() {
            return outermost_shell_id_one;
        }

        public void setOutermost_shell_id_one(String outermost_shell_id_one) {
            this.outermost_shell_id_one = outermost_shell_id_one;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getOutlet_sensor_name_three() {
            return outlet_sensor_name_three;
        }

        public void setOutlet_sensor_name_three(String outlet_sensor_name_three) {
            this.outlet_sensor_name_three = outlet_sensor_name_three;
        }

        public String getRadiator_name_one() {
            return radiator_name_one;
        }

        public void setRadiator_name_one(String radiator_name_one) {
            this.radiator_name_one = radiator_name_one;
        }

        public String getOil_pump_name_one() {
            return oil_pump_name_one;
        }

        public void setOil_pump_name_one(String oil_pump_name_one) {
            this.oil_pump_name_one = oil_pump_name_one;
        }

        public String getFuel_type_name() {
            return fuel_type_name;
        }

        public void setFuel_type_name(String fuel_type_name) {
            this.fuel_type_name = fuel_type_name;
        }

        public String getFirebox_name_two() {
            return firebox_name_two;
        }

        public void setFirebox_name_two(String firebox_name_two) {
            this.firebox_name_two = firebox_name_two;
        }

        public String getControl_switch_all_name() {
            return control_switch_all_name;
        }

        public void setControl_switch_all_name(String control_switch_all_name) {
            this.control_switch_all_name = control_switch_all_name;
        }

        public String getOutermost_shell_name_one() {
            return outermost_shell_name_one;
        }

        public void setOutermost_shell_name_one(String outermost_shell_name_one) {
            this.outermost_shell_name_one = outermost_shell_name_one;
        }

        public String getOutermost_shell_id_two() {
            return outermost_shell_id_two;
        }

        public void setOutermost_shell_id_two(String outermost_shell_id_two) {
            this.outermost_shell_id_two = outermost_shell_id_two;
        }

        public String getCar_t_name() {
            return car_t_name;
        }

        public void setCar_t_name(String car_t_name) {
            this.car_t_name = car_t_name;
        }

        public String getWiring_harness_id_two() {
            return wiring_harness_id_two;
        }

        public void setWiring_harness_id_two(String wiring_harness_id_two) {
            this.wiring_harness_id_two = wiring_harness_id_two;
        }

        public String getOil_pump_all_name() {
            return oil_pump_all_name;
        }

        public void setOil_pump_all_name(String oil_pump_all_name) {
            this.oil_pump_all_name = oil_pump_all_name;
        }

        public String getCar_type_name() {
            return car_type_name;
        }

        public void setCar_type_name(String car_type_name) {
            this.car_type_name = car_type_name;
        }

        public String getZhu_car_stoppage_code_9() {
            return zhu_car_stoppage_code_9;
        }

        public void setZhu_car_stoppage_code_9(String zhu_car_stoppage_code_9) {
            this.zhu_car_stoppage_code_9 = zhu_car_stoppage_code_9;
        }

        public String getFirebox_name_one() {
            return firebox_name_one;
        }

        public void setFirebox_name_one(String firebox_name_one) {
            this.firebox_name_one = firebox_name_one;
        }

        public String getControl_switch_id_two() {
            return control_switch_id_two;
        }

        public void setControl_switch_id_two(String control_switch_id_two) {
            this.control_switch_id_two = control_switch_id_two;
        }

        public String getZhu_car_stoppage_code_8() {
            return zhu_car_stoppage_code_8;
        }

        public void setZhu_car_stoppage_code_8(String zhu_car_stoppage_code_8) {
            this.zhu_car_stoppage_code_8 = zhu_car_stoppage_code_8;
        }

        public String getZhu_car_stoppage_code_7() {
            return zhu_car_stoppage_code_7;
        }

        public void setZhu_car_stoppage_code_7(String zhu_car_stoppage_code_7) {
            this.zhu_car_stoppage_code_7 = zhu_car_stoppage_code_7;
        }

        public String getDraught_fan_id_four() {
            return draught_fan_id_four;
        }

        public void setDraught_fan_id_four(String draught_fan_id_four) {
            this.draught_fan_id_four = draught_fan_id_four;
        }

        public String getCountry_id() {
            return country_id;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }

        public String getOil_pump_name_four() {
            return oil_pump_name_four;
        }

        public void setOil_pump_name_four(String oil_pump_name_four) {
            this.oil_pump_name_four = oil_pump_name_four;
        }

        public String getZhu_user_car_id() {
            return zhu_user_car_id;
        }

        public void setZhu_user_car_id(String zhu_user_car_id) {
            this.zhu_user_car_id = zhu_user_car_id;
        }

        public String getOil_pump_name_three() {
            return oil_pump_name_three;
        }

        public void setOil_pump_name_three(String oil_pump_name_three) {
            this.oil_pump_name_three = oil_pump_name_three;
        }

        public String getGear_parameters_id() {
            return gear_parameters_id;
        }

        public void setGear_parameters_id(String gear_parameters_id) {
            this.gear_parameters_id = gear_parameters_id;
        }

        public String getZhu_car_stoppage_code_1() {
            return zhu_car_stoppage_code_1;
        }

        public void setZhu_car_stoppage_code_1(String zhu_car_stoppage_code_1) {
            this.zhu_car_stoppage_code_1 = zhu_car_stoppage_code_1;
        }

        public String getZhu_car_stoppage_code_2() {
            return zhu_car_stoppage_code_2;
        }

        public void setZhu_car_stoppage_code_2(String zhu_car_stoppage_code_2) {
            this.zhu_car_stoppage_code_2 = zhu_car_stoppage_code_2;
        }

        public String getVulcanizate_name_two() {
            return vulcanizate_name_two;
        }

        public void setVulcanizate_name_two(String vulcanizate_name_two) {
            this.vulcanizate_name_two = vulcanizate_name_two;
        }

        public String getCar_type_id() {
            return car_type_id;
        }

        public void setCar_type_id(String car_type_id) {
            this.car_type_id = car_type_id;
        }

        public String getDash_board_id_one() {
            return dash_board_id_one;
        }

        public void setDash_board_id_one(String dash_board_id_one) {
            this.dash_board_id_one = dash_board_id_one;
        }

        public String getZhu_car_stoppage_code_5() {
            return zhu_car_stoppage_code_5;
        }

        public void setZhu_car_stoppage_code_5(String zhu_car_stoppage_code_5) {
            this.zhu_car_stoppage_code_5 = zhu_car_stoppage_code_5;
        }

        public String getZhu_car_stoppage_code_6() {
            return zhu_car_stoppage_code_6;
        }

        public void setZhu_car_stoppage_code_6(String zhu_car_stoppage_code_6) {
            this.zhu_car_stoppage_code_6 = zhu_car_stoppage_code_6;
        }

        public String getZhu_car_stoppage_code_3() {
            return zhu_car_stoppage_code_3;
        }

        public void setZhu_car_stoppage_code_3(String zhu_car_stoppage_code_3) {
            this.zhu_car_stoppage_code_3 = zhu_car_stoppage_code_3;
        }

        public String getZhu_car_stoppage_code_4() {
            return zhu_car_stoppage_code_4;
        }

        public void setZhu_car_stoppage_code_4(String zhu_car_stoppage_code_4) {
            this.zhu_car_stoppage_code_4 = zhu_car_stoppage_code_4;
        }

        public String getDash_board_id_two() {
            return dash_board_id_two;
        }

        public void setDash_board_id_two(String dash_board_id_two) {
            this.dash_board_id_two = dash_board_id_two;
        }

        public String getZhu_car_stoppage_code_default() {
            return zhu_car_stoppage_code_default;
        }

        public void setZhu_car_stoppage_code_default(String zhu_car_stoppage_code_default) {
            this.zhu_car_stoppage_code_default = zhu_car_stoppage_code_default;
        }

        public String getStart_state() {
            return start_state;
        }

        public void setStart_state(String start_state) {
            this.start_state = start_state;
        }

        public String getRadiator_name_two() {
            return radiator_name_two;
        }

        public void setRadiator_name_two(String radiator_name_two) {
            this.radiator_name_two = radiator_name_two;
        }

        public String getWiring_harness_id_three() {
            return wiring_harness_id_three;
        }

        public void setWiring_harness_id_three(String wiring_harness_id_three) {
            this.wiring_harness_id_three = wiring_harness_id_three;
        }

        public String getIgnition_name_three() {
            return ignition_name_three;
        }

        public void setIgnition_name_three(String ignition_name_three) {
            this.ignition_name_three = ignition_name_three;
        }

        public String getGear() {
            return gear;
        }

        public void setGear(String gear) {
            this.gear = gear;
        }

        public String getLogin_time() {
            return login_time;
        }

        public void setLogin_time(String login_time) {
            this.login_time = login_time;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public String getVulcanizate_id_two() {
            return vulcanizate_id_two;
        }

        public void setVulcanizate_id_two(String vulcanizate_id_two) {
            this.vulcanizate_id_two = vulcanizate_id_two;
        }

        public String getOutlet_sensor_name_two() {
            return outlet_sensor_name_two;
        }

        public void setOutlet_sensor_name_two(String outlet_sensor_name_two) {
            this.outlet_sensor_name_two = outlet_sensor_name_two;
        }

        public String getIgnition_id_two() {
            return ignition_id_two;
        }

        public void setIgnition_id_two(String ignition_id_two) {
            this.ignition_id_two = ignition_id_two;
        }

        public String getIgnition_id_one() {
            return ignition_id_one;
        }

        public void setIgnition_id_one(String ignition_id_one) {
            this.ignition_id_one = ignition_id_one;
        }

        public String getOutlet_sensor_id_two() {
            return outlet_sensor_id_two;
        }

        public void setOutlet_sensor_id_two(String outlet_sensor_id_two) {
            this.outlet_sensor_id_two = outlet_sensor_id_two;
        }

        public String getOutermost_shell_name_two() {
            return outermost_shell_name_two;
        }

        public void setOutermost_shell_name_two(String outermost_shell_name_two) {
            this.outermost_shell_name_two = outermost_shell_name_two;
        }

        public String getMachine_voltage() {
            return machine_voltage;
        }

        public void setMachine_voltage(String machine_voltage) {
            this.machine_voltage = machine_voltage;
        }

        public String getOil_pump_id_two() {
            return oil_pump_id_two;
        }

        public void setOil_pump_id_two(String oil_pump_id_two) {
            this.oil_pump_id_two = oil_pump_id_two;
        }

        public String getIn_temperature() {
            return in_temperature;
        }

        public void setIn_temperature(String in_temperature) {
            this.in_temperature = in_temperature;
        }

        public String getOutermost_shell_all_name() {
            return outermost_shell_all_name;
        }

        public void setOutermost_shell_all_name(String outermost_shell_all_name) {
            this.outermost_shell_all_name = outermost_shell_all_name;
        }

        public String getOutlet_sensor_name_one() {
            return outlet_sensor_name_one;
        }

        public void setOutlet_sensor_name_one(String outlet_sensor_name_one) {
            this.outlet_sensor_name_one = outlet_sensor_name_one;
        }

        public String getVulcanizate_name_one() {
            return vulcanizate_name_one;
        }

        public void setVulcanizate_name_one(String vulcanizate_name_one) {
            this.vulcanizate_name_one = vulcanizate_name_one;
        }

        public String getDash_board_name_one() {
            return dash_board_name_one;
        }

        public void setDash_board_name_one(String dash_board_name_one) {
            this.dash_board_name_one = dash_board_name_one;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getCar_t_id() {
            return car_t_id;
        }

        public void setCar_t_id(String car_t_id) {
            this.car_t_id = car_t_id;
        }

        public String getWiring_harness_name_two() {
            return wiring_harness_name_two;
        }

        public void setWiring_harness_name_two(String wiring_harness_name_two) {
            this.wiring_harness_name_two = wiring_harness_name_two;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getRadiator_id_two() {
            return radiator_id_two;
        }

        public void setRadiator_id_two(String radiator_id_two) {
            this.radiator_id_two = radiator_id_two;
        }

        public String getCurrent_temperature() {
            return current_temperature;
        }

        public void setCurrent_temperature(String current_temperature) {
            this.current_temperature = current_temperature;
        }

        public String getControl_switch_id_one() {
            return control_switch_id_one;
        }

        public void setControl_switch_id_one(String control_switch_id_one) {
            this.control_switch_id_one = control_switch_id_one;
        }

        public String getInsurance() {
            return insurance;
        }

        public void setInsurance(String insurance) {
            this.insurance = insurance;
        }

        public String getOf_user_id() {
            return of_user_id;
        }

        public void setOf_user_id(String of_user_id) {
            this.of_user_id = of_user_id;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getWiring_harness_name_one() {
            return wiring_harness_name_one;
        }

        public void setWiring_harness_name_one(String wiring_harness_name_one) {
            this.wiring_harness_name_one = wiring_harness_name_one;
        }

        public String getOutlet_sensor_id_three() {
            return outlet_sensor_id_three;
        }

        public void setOutlet_sensor_id_three(String outlet_sensor_id_three) {
            this.outlet_sensor_id_three = outlet_sensor_id_three;
        }

        public String getIgnition_id_three() {
            return ignition_id_three;
        }

        public void setIgnition_id_three(String ignition_id_three) {
            this.ignition_id_three = ignition_id_three;
        }

        public String getInstall_time() {
            return install_time;
        }

        public void setInstall_time(String install_time) {
            this.install_time = install_time;
        }

        public String getFrequency() {
            return frequency;
        }

        public void setFrequency(String frequency) {
            this.frequency = frequency;
        }

        public String getPlate_number() {
            return plate_number;
        }

        public void setPlate_number(String plate_number) {
            this.plate_number = plate_number;
        }

        public String getUser_car_img_url() {
            return user_car_img_url;
        }

        public void setUser_car_img_url(String user_car_img_url) {
            this.user_car_img_url = user_car_img_url;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getDash_board_all_name() {
            return dash_board_all_name;
        }

        public void setDash_board_all_name(String dash_board_all_name) {
            this.dash_board_all_name = dash_board_all_name;
        }

        public String getRadiator_id_three() {
            return radiator_id_three;
        }

        public void setRadiator_id_three(String radiator_id_three) {
            this.radiator_id_three = radiator_id_three;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getDraught_fan_name_three() {
            return draught_fan_name_three;
        }

        public void setDraught_fan_name_three(String draught_fan_name_three) {
            this.draught_fan_name_three = draught_fan_name_three;
        }

        public String getWiring_harness_id_one() {
            return wiring_harness_id_one;
        }

        public void setWiring_harness_id_one(String wiring_harness_id_one) {
            this.wiring_harness_id_one = wiring_harness_id_one;
        }

        public String getIgnition_all_name() {
            return ignition_all_name;
        }

        public void setIgnition_all_name(String ignition_all_name) {
            this.ignition_all_name = ignition_all_name;
        }

        public String getOil_pump_id_three() {
            return oil_pump_id_three;
        }

        public void setOil_pump_id_three(String oil_pump_id_three) {
            this.oil_pump_id_three = oil_pump_id_three;
        }

        public String getFirebox_id_one() {
            return firebox_id_one;
        }

        public void setFirebox_id_one(String firebox_id_one) {
            this.firebox_id_one = firebox_id_one;
        }

        public String getOutlet_sensor_id_one() {
            return outlet_sensor_id_one;
        }

        public void setOutlet_sensor_id_one(String outlet_sensor_id_one) {
            this.outlet_sensor_id_one = outlet_sensor_id_one;
        }

        public String getFirebox_all_name() {
            return firebox_all_name;
        }

        public void setFirebox_all_name(String firebox_all_name) {
            this.firebox_all_name = firebox_all_name;
        }

        public String getSell_phone() {
            return sell_phone;
        }

        public void setSell_phone(String sell_phone) {
            this.sell_phone = sell_phone;
        }

        public String getIgnition_name_one() {
            return ignition_name_one;
        }

        public void setIgnition_name_one(String ignition_name_one) {
            this.ignition_name_one = ignition_name_one;
        }

        public String getUser_car_state() {
            return user_car_state;
        }

        public void setUser_car_state(String user_car_state) {
            this.user_car_state = user_car_state;
        }

        public String getOil_pump_name_two() {
            return oil_pump_name_two;
        }

        public void setOil_pump_name_two(String oil_pump_name_two) {
            this.oil_pump_name_two = oil_pump_name_two;
        }

        public String getDraught_fan_id_two() {
            return draught_fan_id_two;
        }

        public void setDraught_fan_id_two(String draught_fan_id_two) {
            this.draught_fan_id_two = draught_fan_id_two;
        }

        public String getDash_board_name_two() {
            return dash_board_name_two;
        }

        public void setDash_board_name_two(String dash_board_name_two) {
            this.dash_board_name_two = dash_board_name_two;
        }

        public String getDraught_fan_name_one() {
            return draught_fan_name_one;
        }

        public void setDraught_fan_name_one(String draught_fan_name_one) {
            this.draught_fan_name_one = draught_fan_name_one;
        }

        public String getRadiator_id_one() {
            return radiator_id_one;
        }

        public void setRadiator_id_one(String radiator_id_one) {
            this.radiator_id_one = radiator_id_one;
        }

        public String getOutlet_sensor_all_name() {
            return outlet_sensor_all_name;
        }

        public void setOutlet_sensor_all_name(String outlet_sensor_all_name) {
            this.outlet_sensor_all_name = outlet_sensor_all_name;
        }
    }
}
