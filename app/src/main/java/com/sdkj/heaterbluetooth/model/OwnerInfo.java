package com.sdkj.heaterbluetooth.model;

import java.util.List;

/**
 * Created by Sl on 2019/6/27.
 *
 */

public class OwnerInfo {

    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"insurance":"","car_type_name":"","area_name":"","outlet_sensor_all_name":"","oil_pump_all_name":"","user_name":"未设置","outermost_shell_all_name":"","control_switch_all_name":"","city_name":"","sell_phone":"13946026970","radiator_all_name":"","car_brand_name_two":"奥迪A3","country_name":"","user_phone":"13054274182","ignition_all_name":"","wiring_harness_all_name":"","dash_board_all_name":"","install_time":"2019-06-19","draught_fan_all_name":"","fuel_type_name":"","firebox_all_name":"","car_t_name":"","car_brand_url_one":"https://shop.hljsdkj.com/commons/images/siye_autoparts/brand/autohomecar__wKgH11cVh1WADo76AAAK8dMrElU714.jpg","province_name":"","vulcanizate_all_name":"","car_brand_name_one":"奥迪","plate_number":""}]
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
         * insurance :
         * car_type_name :
         * area_name :
         * outlet_sensor_all_name :
         * oil_pump_all_name :
         * user_name : 未设置
         * outermost_shell_all_name :
         * control_switch_all_name :
         * city_name :
         * sell_phone : 13946026970
         * radiator_all_name :
         * car_brand_name_two : 奥迪A3
         * country_name :
         * user_phone : 13054274182
         * ignition_all_name :
         * wiring_harness_all_name :
         * dash_board_all_name :
         * install_time : 2019-06-19
         * draught_fan_all_name :
         * fuel_type_name :
         * firebox_all_name :
         * car_t_name :
         * car_brand_url_one : https://shop.hljsdkj.com/commons/images/siye_autoparts/brand/autohomecar__wKgH11cVh1WADo76AAAK8dMrElU714.jpg
         * province_name :
         * vulcanizate_all_name :
         * car_brand_name_one : 奥迪
         * plate_number :
         */

        private String insurance;
        private String car_type_name;
        private String area_name;
        private String outlet_sensor_all_name;
        private String oil_pump_all_name;
        private String user_name;
        private String outermost_shell_all_name;
        private String control_switch_all_name;
        private String city_name;
        private String sell_phone;
        private String radiator_all_name;
        private String car_brand_name_two;
        private String country_name;
        private String user_phone;
        private String ignition_all_name;
        private String wiring_harness_all_name;
        private String dash_board_all_name;
        private String install_time;
        private String draught_fan_all_name;
        private String fuel_type_name;
        private String firebox_all_name;
        private String car_t_name;
        private String car_brand_url_one;
        private String province_name;
        private String vulcanizate_all_name;
        private String car_brand_name_one;
        private String plate_number;

        public String getInsurance() {
            return insurance;
        }

        public void setInsurance(String insurance) {
            this.insurance = insurance;
        }

        public String getCar_type_name() {
            return car_type_name;
        }

        public void setCar_type_name(String car_type_name) {
            this.car_type_name = car_type_name;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getOutlet_sensor_all_name() {
            return outlet_sensor_all_name;
        }

        public void setOutlet_sensor_all_name(String outlet_sensor_all_name) {
            this.outlet_sensor_all_name = outlet_sensor_all_name;
        }

        public String getOil_pump_all_name() {
            return oil_pump_all_name;
        }

        public void setOil_pump_all_name(String oil_pump_all_name) {
            this.oil_pump_all_name = oil_pump_all_name;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getOutermost_shell_all_name() {
            return outermost_shell_all_name;
        }

        public void setOutermost_shell_all_name(String outermost_shell_all_name) {
            this.outermost_shell_all_name = outermost_shell_all_name;
        }

        public String getControl_switch_all_name() {
            return control_switch_all_name;
        }

        public void setControl_switch_all_name(String control_switch_all_name) {
            this.control_switch_all_name = control_switch_all_name;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getSell_phone() {
            return sell_phone;
        }

        public void setSell_phone(String sell_phone) {
            this.sell_phone = sell_phone;
        }

        public String getRadiator_all_name() {
            return radiator_all_name;
        }

        public void setRadiator_all_name(String radiator_all_name) {
            this.radiator_all_name = radiator_all_name;
        }

        public String getCar_brand_name_two() {
            return car_brand_name_two;
        }

        public void setCar_brand_name_two(String car_brand_name_two) {
            this.car_brand_name_two = car_brand_name_two;
        }

        public String getCountry_name() {
            return country_name;
        }

        public void setCountry_name(String country_name) {
            this.country_name = country_name;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getIgnition_all_name() {
            return ignition_all_name;
        }

        public void setIgnition_all_name(String ignition_all_name) {
            this.ignition_all_name = ignition_all_name;
        }

        public String getWiring_harness_all_name() {
            return wiring_harness_all_name;
        }

        public void setWiring_harness_all_name(String wiring_harness_all_name) {
            this.wiring_harness_all_name = wiring_harness_all_name;
        }

        public String getDash_board_all_name() {
            return dash_board_all_name;
        }

        public void setDash_board_all_name(String dash_board_all_name) {
            this.dash_board_all_name = dash_board_all_name;
        }

        public String getInstall_time() {
            return install_time;
        }

        public void setInstall_time(String install_time) {
            this.install_time = install_time;
        }

        public String getDraught_fan_all_name() {
            return draught_fan_all_name;
        }

        public void setDraught_fan_all_name(String draught_fan_all_name) {
            this.draught_fan_all_name = draught_fan_all_name;
        }

        public String getFuel_type_name() {
            return fuel_type_name;
        }

        public void setFuel_type_name(String fuel_type_name) {
            this.fuel_type_name = fuel_type_name;
        }

        public String getFirebox_all_name() {
            return firebox_all_name;
        }

        public void setFirebox_all_name(String firebox_all_name) {
            this.firebox_all_name = firebox_all_name;
        }

        public String getCar_t_name() {
            return car_t_name;
        }

        public void setCar_t_name(String car_t_name) {
            this.car_t_name = car_t_name;
        }

        public String getCar_brand_url_one() {
            return car_brand_url_one;
        }

        public void setCar_brand_url_one(String car_brand_url_one) {
            this.car_brand_url_one = car_brand_url_one;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getVulcanizate_all_name() {
            return vulcanizate_all_name;
        }

        public void setVulcanizate_all_name(String vulcanizate_all_name) {
            this.vulcanizate_all_name = vulcanizate_all_name;
        }

        public String getCar_brand_name_one() {
            return car_brand_name_one;
        }

        public void setCar_brand_name_one(String car_brand_name_one) {
            this.car_brand_name_one = car_brand_name_one;
        }

        public String getPlate_number() {
            return plate_number;
        }

        public void setPlate_number(String plate_number) {
            this.plate_number = plate_number;
        }
    }
}
