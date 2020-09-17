package com.sdkj.heaterbluetooth.model;

import java.util.List;

public class DingShiResultModel {
    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"sell_state":"2","device_version":"fle-01","undervoltage_threshold":"","create_time":"2020-09-09","shifen_time":"13:16","sub_inst_id":"419","device_no":"fle-20200908","weeks_time":"0000100","throttle_condition":"","volume":"","ccid":"aaaaaaaaaaaaaaaa90020018","go_time":"2020-09-26","sub_user_id":"243"}]
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
         * sell_state : 2
         * device_version : fle-01
         * undervoltage_threshold :
         * create_time : 2020-09-09
         * shifen_time : 13:16
         * sub_inst_id : 419
         * device_no : fle-20200908
         * weeks_time : 0000100
         * throttle_condition :
         * volume :
         * ccid : aaaaaaaaaaaaaaaa90020018
         * go_time : 2020-09-26
         * sub_user_id : 243
         */

        private String sell_state;
        private String device_version;
        private String undervoltage_threshold;
        private String create_time;
        private String shifen_time;
        private String sub_inst_id;
        private String device_no;
        private String weeks_time;
        private String throttle_condition;
        private String volume;
        private String ccid;
        private String go_time;
        private String sub_user_id;

        public String getSell_state() {
            return sell_state;
        }

        public void setSell_state(String sell_state) {
            this.sell_state = sell_state;
        }

        public String getDevice_version() {
            return device_version;
        }

        public void setDevice_version(String device_version) {
            this.device_version = device_version;
        }

        public String getUndervoltage_threshold() {
            return undervoltage_threshold;
        }

        public void setUndervoltage_threshold(String undervoltage_threshold) {
            this.undervoltage_threshold = undervoltage_threshold;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getShifen_time() {
            return shifen_time;
        }

        public void setShifen_time(String shifen_time) {
            this.shifen_time = shifen_time;
        }

        public String getSub_inst_id() {
            return sub_inst_id;
        }

        public void setSub_inst_id(String sub_inst_id) {
            this.sub_inst_id = sub_inst_id;
        }

        public String getDevice_no() {
            return device_no;
        }

        public void setDevice_no(String device_no) {
            this.device_no = device_no;
        }

        public String getWeeks_time() {
            return weeks_time;
        }

        public void setWeeks_time(String weeks_time) {
            this.weeks_time = weeks_time;
        }

        public String getThrottle_condition() {
            return throttle_condition;
        }

        public void setThrottle_condition(String throttle_condition) {
            this.throttle_condition = throttle_condition;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getCcid() {
            return ccid;
        }

        public void setCcid(String ccid) {
            this.ccid = ccid;
        }

        public String getGo_time() {
            return go_time;
        }

        public void setGo_time(String go_time) {
            this.go_time = go_time;
        }

        public String getSub_user_id() {
            return sub_user_id;
        }

        public void setSub_user_id(String sub_user_id) {
            this.sub_user_id = sub_user_id;
        }
    }
}
