package com.sdkj.heaterbluetooth.model;


import java.util.List;

public class SmartDevice_car_0364 {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 5
     * data : [{"car_brand_name":"奥迪A3","gps_x":"","gaode_y":"","available_check":"1","gps_y":"","car_brand_url":"https://shop.hljsdkj.com/commons/images/siye_autoparts/brand/autohomecar__wKgH11cVh1WADo76AAAK8dMrElU714.jpg","lock_detail":"-","baidu_y":"","baidu_x":"","server_id":"1/","user_car_id":"80","zhu_apc":"aaa","ccid":"bbbbbbbbbbbbbbbbbbbbbb01","plate_number":"黑A06599","gaode_x":"","gps_addr":""},{"car_brand_name":"阿斯顿·马丁V12 Vantage","gps_x":"","gaode_y":"","available_check":"1","gps_y":"","car_brand_url":"https://shop.hljsdkj.com/commons/images/siye_autoparts/brand/130131578038733348.jpg","lock_detail":"-","baidu_y":"","baidu_x":"","server_id":"1/","user_car_id":"86","zhu_apc":"aaa","ccid":"aaaaaaaaaaaaaaaaaaaaaaa1","plate_number":"","gaode_x":"","gps_addr":""},{"car_brand_name":"奔驰C级","gps_x":"","gaode_y":"","available_check":"1","gps_y":"","car_brand_url":"https://shop.hljsdkj.com/commons/images/siye_autoparts/brand/autohomecar__wKjBxlgEi2OACNA1AAAN8O5z018868.jpg","lock_detail":"-","baidu_y":"","baidu_x":"","server_id":"8/","user_car_id":"87","zhu_apc":"aaa","ccid":"aaaaaaaaaaaaaaaaaaaaaaa8","plate_number":"12345","gaode_x":"","gps_addr":""},{"car_brand_name":"奔驰E级","gps_x":"","gaode_y":"","available_check":"1","gps_y":"","car_brand_url":"https://shop.hljsdkj.com/commons/images/siye_autoparts/brand/autohomecar__wKjBxlgEi2OACNA1AAAN8O5z018868.jpg","lock_detail":"-","baidu_y":"","baidu_x":"","server_id":"7/","user_car_id":"89","zhu_apc":"aaa","ccid":"aaaaaaaaaaaaaaaaaaaaaaa7","plate_number":"","gaode_x":"","gps_addr":""},{"car_brand_name":"奔驰A级","gps_x":"","gaode_y":"","available_check":"1","gps_y":"","car_brand_url":"https://shop.hljsdkj.com/commons/images/siye_autoparts/brand/autohomecar__wKjBxlgEi2OACNA1AAAN8O5z018868.jpg","lock_detail":"-","baidu_y":"","baidu_x":"","server_id":"8/","user_car_id":"90","zhu_apc":"aaa","ccid":"aaaaaaaaaaaaaaaaaaaaaa18","plate_number":"888999","gaode_x":"","gps_addr":""}]
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
         * car_brand_name : 奥迪A3
         * gps_x :
         * gaode_y :
         * available_check : 1
         * gps_y :
         * car_brand_url : https://shop.hljsdkj.com/commons/images/siye_autoparts/brand/autohomecar__wKgH11cVh1WADo76AAAK8dMrElU714.jpg
         * lock_detail : -
         * baidu_y :
         * baidu_x :
         * server_id : 1/
         * user_car_id : 80
         * zhu_apc : aaa
         * ccid : bbbbbbbbbbbbbbbbbbbbbb01
         * plate_number : 黑A06599
         * gaode_x :
         * gps_addr :
         */

        private String car_brand_name;
        private String gps_x;
        private String gaode_y;
        private String available_check;
        private String gps_y;
        private String car_brand_url;
        private String lock_detail;
        private String baidu_y;
        private String baidu_x;
        private String server_id;
        private String user_car_id;
        private String zhu_apc;
        private String ccid;
        private String plate_number;
        private String gaode_x;
        private String gps_addr;

        public String getCar_brand_name() {
            return car_brand_name;
        }

        public void setCar_brand_name(String car_brand_name) {
            this.car_brand_name = car_brand_name;
        }

        public String getGps_x() {
            return gps_x;
        }

        public void setGps_x(String gps_x) {
            this.gps_x = gps_x;
        }

        public String getGaode_y() {
            return gaode_y;
        }

        public void setGaode_y(String gaode_y) {
            this.gaode_y = gaode_y;
        }

        public String getAvailable_check() {
            return available_check;
        }

        public void setAvailable_check(String available_check) {
            this.available_check = available_check;
        }

        public String getGps_y() {
            return gps_y;
        }

        public void setGps_y(String gps_y) {
            this.gps_y = gps_y;
        }

        public String getCar_brand_url() {
            return car_brand_url;
        }

        public void setCar_brand_url(String car_brand_url) {
            this.car_brand_url = car_brand_url;
        }

        public String getLock_detail() {
            return lock_detail;
        }

        public void setLock_detail(String lock_detail) {
            this.lock_detail = lock_detail;
        }

        public String getBaidu_y() {
            return baidu_y;
        }

        public void setBaidu_y(String baidu_y) {
            this.baidu_y = baidu_y;
        }

        public String getBaidu_x() {
            return baidu_x;
        }

        public void setBaidu_x(String baidu_x) {
            this.baidu_x = baidu_x;
        }

        public String getServer_id() {
            return server_id;
        }

        public void setServer_id(String server_id) {
            this.server_id = server_id;
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

        public String getCcid() {
            return ccid;
        }

        public void setCcid(String ccid) {
            this.ccid = ccid;
        }

        public String getPlate_number() {
            return plate_number;
        }

        public void setPlate_number(String plate_number) {
            this.plate_number = plate_number;
        }

        public String getGaode_x() {
            return gaode_x;
        }

        public void setGaode_x(String gaode_x) {
            this.gaode_x = gaode_x;
        }

        public String getGps_addr() {
            return gps_addr;
        }

        public void setGps_addr(String gps_addr) {
            this.gps_addr = gps_addr;
        }
    }
}
