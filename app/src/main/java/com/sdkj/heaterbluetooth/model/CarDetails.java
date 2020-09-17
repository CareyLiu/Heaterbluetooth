package com.sdkj.heaterbluetooth.model;

import java.util.List;

public class CarDetails {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"oil_wear":"","car_trunk":"","gearbox_temperature":"","cut_time_s":"","car_acc":"1","benzin_total":"50","gps_state":"2","urgency_start":"","gps_addr":"哈尔滨市南岗区电缆街170","cut_thread":"","rev":"0","mph":"0","bath_glass":"","cbox_battery":"","car_brand_url_one":"https://shop.hljsdkj.com/commons/images/siye_autoparts/brand/130131578038733348.jpg","car_window":"","cbox_state_name":"静止","car_orientations":"1","plate_number":"黑E6666654","car_acc_start":"2","car_high_beam":"","coolant":"-30","gps_x":"45.6679638","per_benzin":"0.0","gps_y":"126.611563","car_foglight":"","traffic_collision":"","car_electric":"","benzin":"20","cbox_signal":"","car_flasher":"","cbox_state":"1","ccid":"aaaaaaaaaaaaaaaaaaaaaa05","tyre_pressure":"","car_air":"","car_scuttle":"","gaode_y":"126.611563","car_oil":"","car_light":"","total_km":"","cut_electricity":"","weilan_url":"https://shop.hljsdkj.com/wit/web/weilan?u=27","user_car_id":"27","air_conditioner":"","urgency_stop":"","gps_mi":"0","remaining_km":"15200","car_brand_name_one":"阿斯顿·马丁","car_door":"","gaode_x":"45.6679638"}]
     */

    private String msg_code;
    private String msg;
    private String row_num;
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

    public String getRow_num() {
        return row_num;
    }

    public void setRow_num(String row_num) {
        this.row_num = row_num;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * oil_wear :
         * car_trunk :
         * gearbox_temperature :
         * cut_time_s :
         * car_acc : 1
         * benzin_total : 50
         * gps_state : 2
         * urgency_start :
         * gps_addr : 哈尔滨市南岗区电缆街170
         * cut_thread :
         * rev : 0
         * mph : 0
         * bath_glass :
         * cbox_battery :
         * car_brand_url_one : https://shop.hljsdkj.com/commons/images/siye_autoparts/brand/130131578038733348.jpg
         * car_window :
         * cbox_state_name : 静止
         * car_orientations : 1
         * plate_number : 黑E6666654
         * car_acc_start : 2
         * car_high_beam :
         * coolant : -30
         * gps_x : 45.6679638
         * per_benzin : 0.0
         * gps_y : 126.611563
         * car_foglight :
         * traffic_collision :
         * car_electric :
         * benzin : 20
         * cbox_signal :
         * car_flasher :
         * cbox_state : 1
         * ccid : aaaaaaaaaaaaaaaaaaaaaa05
         * tyre_pressure :
         * car_air :
         * car_scuttle :
         * gaode_y : 126.611563
         * car_oil :
         * car_light :
         * total_km :
         * cut_electricity :
         * weilan_url : https://shop.hljsdkj.com/wit/web/weilan?u=27
         * user_car_id : 27
         * air_conditioner :
         * urgency_stop :
         * gps_mi : 0
         * remaining_km : 15200
         * car_brand_name_one : 阿斯顿·马丁
         * car_door :
         * gaode_x : 45.6679638
         */

        private String oil_wear;
        private String car_trunk;
        private String gearbox_temperature;
        private String cut_time_s;
        private String car_acc;
        private String benzin_total;
        private String gps_state;
        private String urgency_start;
        private String gps_addr;
        private String cut_thread;
        private String rev;
        private String mph;
        private String bath_glass;
        private String cbox_battery;
        private String car_brand_url_one;
        private String car_window;
        private String cbox_state_name;
        private String car_orientations;
        private String plate_number;
        private String car_acc_start;
        private String car_high_beam;
        private String coolant;
        private String gps_x;
        private String per_benzin;
        private String gps_y;
        private String car_foglight;
        private String traffic_collision;
        private String car_electric;
        private String benzin;
        private String cbox_signal;
        private String car_flasher;
        private String cbox_state;
        private String ccid;
        private String tyre_pressure;
        private String car_air;
        private String car_scuttle;
        private String gaode_y;
        private String car_oil;
        private String car_light;
        private String total_km;
        private String cut_electricity;
        private String weilan_url;
        private String user_car_id;
        private String air_conditioner;
        private String urgency_stop;
        private String gps_mi;
        private String remaining_km;
        private String car_brand_name_one;
        private String car_door;
        private String gaode_x;

        public String getOil_wear() {
            return oil_wear;
        }

        public void setOil_wear(String oil_wear) {
            this.oil_wear = oil_wear;
        }

        public String getCar_trunk() {
            return car_trunk;
        }

        public void setCar_trunk(String car_trunk) {
            this.car_trunk = car_trunk;
        }

        public String getGearbox_temperature() {
            return gearbox_temperature;
        }

        public void setGearbox_temperature(String gearbox_temperature) {
            this.gearbox_temperature = gearbox_temperature;
        }

        public String getCut_time_s() {
            return cut_time_s;
        }

        public void setCut_time_s(String cut_time_s) {
            this.cut_time_s = cut_time_s;
        }

        public String getCar_acc() {
            return car_acc;
        }

        public void setCar_acc(String car_acc) {
            this.car_acc = car_acc;
        }

        public String getBenzin_total() {
            return benzin_total;
        }

        public void setBenzin_total(String benzin_total) {
            this.benzin_total = benzin_total;
        }

        public String getGps_state() {
            return gps_state;
        }

        public void setGps_state(String gps_state) {
            this.gps_state = gps_state;
        }

        public String getUrgency_start() {
            return urgency_start;
        }

        public void setUrgency_start(String urgency_start) {
            this.urgency_start = urgency_start;
        }

        public String getGps_addr() {
            return gps_addr;
        }

        public void setGps_addr(String gps_addr) {
            this.gps_addr = gps_addr;
        }

        public String getCut_thread() {
            return cut_thread;
        }

        public void setCut_thread(String cut_thread) {
            this.cut_thread = cut_thread;
        }

        public String getRev() {
            return rev;
        }

        public void setRev(String rev) {
            this.rev = rev;
        }

        public String getMph() {
            return mph;
        }

        public void setMph(String mph) {
            this.mph = mph;
        }

        public String getBath_glass() {
            return bath_glass;
        }

        public void setBath_glass(String bath_glass) {
            this.bath_glass = bath_glass;
        }

        public String getCbox_battery() {
            return cbox_battery;
        }

        public void setCbox_battery(String cbox_battery) {
            this.cbox_battery = cbox_battery;
        }

        public String getCar_brand_url_one() {
            return car_brand_url_one;
        }

        public void setCar_brand_url_one(String car_brand_url_one) {
            this.car_brand_url_one = car_brand_url_one;
        }

        public String getCar_window() {
            return car_window;
        }

        public void setCar_window(String car_window) {
            this.car_window = car_window;
        }

        public String getCbox_state_name() {
            return cbox_state_name;
        }

        public void setCbox_state_name(String cbox_state_name) {
            this.cbox_state_name = cbox_state_name;
        }

        public String getCar_orientations() {
            return car_orientations;
        }

        public void setCar_orientations(String car_orientations) {
            this.car_orientations = car_orientations;
        }

        public String getPlate_number() {
            return plate_number;
        }

        public void setPlate_number(String plate_number) {
            this.plate_number = plate_number;
        }

        public String getCar_acc_start() {
            return car_acc_start;
        }

        public void setCar_acc_start(String car_acc_start) {
            this.car_acc_start = car_acc_start;
        }

        public String getCar_high_beam() {
            return car_high_beam;
        }

        public void setCar_high_beam(String car_high_beam) {
            this.car_high_beam = car_high_beam;
        }

        public String getCoolant() {
            return coolant;
        }

        public void setCoolant(String coolant) {
            this.coolant = coolant;
        }

        public String getGps_x() {
            return gps_x;
        }

        public void setGps_x(String gps_x) {
            this.gps_x = gps_x;
        }

        public String getPer_benzin() {
            return per_benzin;
        }

        public void setPer_benzin(String per_benzin) {
            this.per_benzin = per_benzin;
        }

        public String getGps_y() {
            return gps_y;
        }

        public void setGps_y(String gps_y) {
            this.gps_y = gps_y;
        }

        public String getCar_foglight() {
            return car_foglight;
        }

        public void setCar_foglight(String car_foglight) {
            this.car_foglight = car_foglight;
        }

        public String getTraffic_collision() {
            return traffic_collision;
        }

        public void setTraffic_collision(String traffic_collision) {
            this.traffic_collision = traffic_collision;
        }

        public String getCar_electric() {
            return car_electric;
        }

        public void setCar_electric(String car_electric) {
            this.car_electric = car_electric;
        }

        public String getBenzin() {
            return benzin;
        }

        public void setBenzin(String benzin) {
            this.benzin = benzin;
        }

        public String getCbox_signal() {
            return cbox_signal;
        }

        public void setCbox_signal(String cbox_signal) {
            this.cbox_signal = cbox_signal;
        }

        public String getCar_flasher() {
            return car_flasher;
        }

        public void setCar_flasher(String car_flasher) {
            this.car_flasher = car_flasher;
        }

        public String getCbox_state() {
            return cbox_state;
        }

        public void setCbox_state(String cbox_state) {
            this.cbox_state = cbox_state;
        }

        public String getCcid() {
            return ccid;
        }

        public void setCcid(String ccid) {
            this.ccid = ccid;
        }

        public String getTyre_pressure() {
            return tyre_pressure;
        }

        public void setTyre_pressure(String tyre_pressure) {
            this.tyre_pressure = tyre_pressure;
        }

        public String getCar_air() {
            return car_air;
        }

        public void setCar_air(String car_air) {
            this.car_air = car_air;
        }

        public String getCar_scuttle() {
            return car_scuttle;
        }

        public void setCar_scuttle(String car_scuttle) {
            this.car_scuttle = car_scuttle;
        }

        public String getGaode_y() {
            return gaode_y;
        }

        public void setGaode_y(String gaode_y) {
            this.gaode_y = gaode_y;
        }

        public String getCar_oil() {
            return car_oil;
        }

        public void setCar_oil(String car_oil) {
            this.car_oil = car_oil;
        }

        public String getCar_light() {
            return car_light;
        }

        public void setCar_light(String car_light) {
            this.car_light = car_light;
        }

        public String getTotal_km() {
            return total_km;
        }

        public void setTotal_km(String total_km) {
            this.total_km = total_km;
        }

        public String getCut_electricity() {
            return cut_electricity;
        }

        public void setCut_electricity(String cut_electricity) {
            this.cut_electricity = cut_electricity;
        }

        public String getWeilan_url() {
            return weilan_url;
        }

        public void setWeilan_url(String weilan_url) {
            this.weilan_url = weilan_url;
        }

        public String getUser_car_id() {
            return user_car_id;
        }

        public void setUser_car_id(String user_car_id) {
            this.user_car_id = user_car_id;
        }

        public String getAir_conditioner() {
            return air_conditioner;
        }

        public void setAir_conditioner(String air_conditioner) {
            this.air_conditioner = air_conditioner;
        }

        public String getUrgency_stop() {
            return urgency_stop;
        }

        public void setUrgency_stop(String urgency_stop) {
            this.urgency_stop = urgency_stop;
        }

        public String getGps_mi() {
            return gps_mi;
        }

        public void setGps_mi(String gps_mi) {
            this.gps_mi = gps_mi;
        }

        public String getRemaining_km() {
            return remaining_km;
        }

        public void setRemaining_km(String remaining_km) {
            this.remaining_km = remaining_km;
        }

        public String getCar_brand_name_one() {
            return car_brand_name_one;
        }

        public void setCar_brand_name_one(String car_brand_name_one) {
            this.car_brand_name_one = car_brand_name_one;
        }

        public String getCar_door() {
            return car_door;
        }

        public void setCar_door(String car_door) {
            this.car_door = car_door;
        }

        public String getGaode_x() {
            return gaode_x;
        }

        public void setGaode_x(String gaode_x) {
            this.gaode_x = gaode_x;
        }
    }
}
