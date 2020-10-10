package com.sdkj.heaterbluetooth.model;

import java.util.List;

public class SheBeiLieBieListModel {

    /**
     * mqtt_connect_state : 1
     * mqtt_connect_prompt : 为了给您提供更优质、完善的服务，我公司拟于9月22日08:00~17:00对系统进行升级维护，期间您可能无法正常使用微信小程序、手机APP等业务，由此给您带来的不便，敬请谅解！
     * msg_code : 0000
     * msg : ok
     * data : [{"control_device_name":"水暖加热器","control_device_list":[{"available_check":"2","online_status":"","validity_state":"2","shifen_time":"","lock_detail":"已失效","device_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11710","server_id":"8/","weeks_time":"","user_car_id":"140","zhu_apc":"aaa","device_name":"水暖加热器","share_type":"2","ccid":"aaaaaaaaaaaaaaaa90040018","sim_ccid_save_type":"2","plate_number":"","validity_term":"已失效","validity_time":"2020-09-24"}],"control_type_id":"6"}]
     */

    private String mqtt_connect_state;
    private String mqtt_connect_prompt;
    private String msg_code;
    private String msg;
    private List<DataBean> data;

    public String getMqtt_connect_state() {
        return mqtt_connect_state;
    }

    public void setMqtt_connect_state(String mqtt_connect_state) {
        this.mqtt_connect_state = mqtt_connect_state;
    }

    public String getMqtt_connect_prompt() {
        return mqtt_connect_prompt;
    }

    public void setMqtt_connect_prompt(String mqtt_connect_prompt) {
        this.mqtt_connect_prompt = mqtt_connect_prompt;
    }

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
         * control_device_name : 水暖加热器
         * control_device_list : [{"available_check":"2","online_status":"","validity_state":"2","shifen_time":"","lock_detail":"已失效","device_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11710","server_id":"8/","weeks_time":"","user_car_id":"140","zhu_apc":"aaa","device_name":"水暖加热器","share_type":"2","ccid":"aaaaaaaaaaaaaaaa90040018","sim_ccid_save_type":"2","plate_number":"","validity_term":"已失效","validity_time":"2020-09-24"}]
         * control_type_id : 6
         */

        private String control_device_name;
        private String control_type_id;
        private List<ControlDeviceListBean> control_device_list;

        public String getControl_device_name() {
            return control_device_name;
        }

        public void setControl_device_name(String control_device_name) {
            this.control_device_name = control_device_name;
        }

        public String getControl_type_id() {
            return control_type_id;
        }

        public void setControl_type_id(String control_type_id) {
            this.control_type_id = control_type_id;
        }

        public List<ControlDeviceListBean> getControl_device_list() {
            return control_device_list;
        }

        public void setControl_device_list(List<ControlDeviceListBean> control_device_list) {
            this.control_device_list = control_device_list;
        }

        public static class ControlDeviceListBean {
            /**
             * available_check : 2
             * online_status :
             * validity_state : 2
             * shifen_time :
             * lock_detail : 已失效
             * device_img_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11710
             * server_id : 8/
             * weeks_time :
             * user_car_id : 140
             * zhu_apc : aaa
             * device_name : 水暖加热器
             * share_type : 2
             * ccid : aaaaaaaaaaaaaaaa90040018
             * sim_ccid_save_type : 2
             * plate_number :
             * validity_term : 已失效
             * validity_time : 2020-09-24
             */

            private String available_check;
            private String online_status;
            private String validity_state;
            private String shifen_time;
            private String lock_detail;
            private String device_img_url;
            private String server_id;
            private String weeks_time;
            private String user_car_id;
            private String zhu_apc;
            private String device_name;
            private String share_type;
            private String ccid;
            private String sim_ccid_save_type;
            private String plate_number;
            private String validity_term;
            private String validity_time;

            public String getAvailable_check() {
                return available_check;
            }

            public void setAvailable_check(String available_check) {
                this.available_check = available_check;
            }

            public String getOnline_status() {
                return online_status;
            }

            public void setOnline_status(String online_status) {
                this.online_status = online_status;
            }

            public String getValidity_state() {
                return validity_state;
            }

            public void setValidity_state(String validity_state) {
                this.validity_state = validity_state;
            }

            public String getShifen_time() {
                return shifen_time;
            }

            public void setShifen_time(String shifen_time) {
                this.shifen_time = shifen_time;
            }

            public String getLock_detail() {
                return lock_detail;
            }

            public void setLock_detail(String lock_detail) {
                this.lock_detail = lock_detail;
            }

            public String getDevice_img_url() {
                return device_img_url;
            }

            public void setDevice_img_url(String device_img_url) {
                this.device_img_url = device_img_url;
            }

            public String getServer_id() {
                return server_id;
            }

            public void setServer_id(String server_id) {
                this.server_id = server_id;
            }

            public String getWeeks_time() {
                return weeks_time;
            }

            public void setWeeks_time(String weeks_time) {
                this.weeks_time = weeks_time;
            }

            public String getUser_car_id() {
                return user_car_id;
            }

            public void setUser_car_id(String user_car_id) {
                this.user_car_id = user_car_id;
            }

            public String getZhu_apc() {
                return zhu_apc;
            }

            public void setZhu_apc(String zhu_apc) {
                this.zhu_apc = zhu_apc;
            }

            public String getDevice_name() {
                return device_name;
            }

            public void setDevice_name(String device_name) {
                this.device_name = device_name;
            }

            public String getShare_type() {
                return share_type;
            }

            public void setShare_type(String share_type) {
                this.share_type = share_type;
            }

            public String getCcid() {
                return ccid;
            }

            public void setCcid(String ccid) {
                this.ccid = ccid;
            }

            public String getSim_ccid_save_type() {
                return sim_ccid_save_type;
            }

            public void setSim_ccid_save_type(String sim_ccid_save_type) {
                this.sim_ccid_save_type = sim_ccid_save_type;
            }

            public String getPlate_number() {
                return plate_number;
            }

            public void setPlate_number(String plate_number) {
                this.plate_number = plate_number;
            }

            public String getValidity_term() {
                return validity_term;
            }

            public void setValidity_term(String validity_term) {
                this.validity_term = validity_term;
            }

            public String getValidity_time() {
                return validity_time;
            }

            public void setValidity_time(String validity_time) {
                this.validity_time = validity_time;
            }
        }
    }
}
