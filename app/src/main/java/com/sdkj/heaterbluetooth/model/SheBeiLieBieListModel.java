package com.sdkj.heaterbluetooth.model;

import java.util.List;

public class SheBeiLieBieListModel {
    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"control_device_name":"风暖加热器","control_device_list":[{"ccid":"aaaaaaaaaaaaaaaa10090018","validity_term":"使用中","validity_state":"1","validity_time":"2021-08-31 17:11:14","device_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11709","device_name":"风暖加热器"}]}]
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
         * control_device_name : 风暖加热器
         * control_device_list : [{"ccid":"aaaaaaaaaaaaaaaa10090018","validity_term":"使用中","validity_state":"1","validity_time":"2021-08-31 17:11:14","device_img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11709","device_name":"风暖加热器"}]
         */

        private String control_device_name;
        public String control_type_id;
        private List<ControlDeviceListBean> control_device_list;

        public String getControl_device_name() {
            return control_device_name;
        }

        public void setControl_device_name(String control_device_name) {
            this.control_device_name = control_device_name;
        }

        public List<ControlDeviceListBean> getControl_device_list() {
            return control_device_list;
        }

        public void setControl_device_list(List<ControlDeviceListBean> control_device_list) {
            this.control_device_list = control_device_list;
        }

        public static class ControlDeviceListBean {
            /**
             * ccid : aaaaaaaaaaaaaaaa10090018
             * validity_term : 使用中
             * validity_state : 1
             * validity_time : 2021-08-31 17:11:14
             * device_img_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11709
             * device_name : 风暖加热器
             */

            private String ccid;
            private String validity_term;
            private String validity_state;
            private String validity_time;
            private String device_img_url;
            private String device_name;
            public String control_type_id;

            public String getCcid() {
                return ccid;
            }

            public void setCcid(String ccid) {
                this.ccid = ccid;
            }

            public String getValidity_term() {
                return validity_term;
            }

            public void setValidity_term(String validity_term) {
                this.validity_term = validity_term;
            }

            public String getValidity_state() {
                return validity_state;
            }

            public void setValidity_state(String validity_state) {
                this.validity_state = validity_state;
            }

            public String getValidity_time() {
                return validity_time;
            }

            public void setValidity_time(String validity_time) {
                this.validity_time = validity_time;
            }

            public String getDevice_img_url() {
                return device_img_url;
            }

            public void setDevice_img_url(String device_img_url) {
                this.device_img_url = device_img_url;
            }

            public String getDevice_name() {
                return device_name;
            }

            public void setDevice_name(String device_name) {
                this.device_name = device_name;
            }
        }
    }
}
